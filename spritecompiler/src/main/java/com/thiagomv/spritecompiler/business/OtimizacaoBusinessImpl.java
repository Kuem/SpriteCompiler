package com.thiagomv.spritecompiler.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.thiagomv.spritecompiler.commons.bases.BaseBusinessImpl;
import com.thiagomv.spritecompiler.commons.factories.BusinessFactory;
import com.thiagomv.spritecompiler.data.Point2D;
import com.thiagomv.spritecompiler.data.PontoAncora;
import com.thiagomv.spritecompiler.data.Rectangle2D;
import com.thiagomv.spritecompiler.data.Size;
import com.thiagomv.spritecompiler.data.StatusEmpacotamento;
import com.thiagomv.spritecompiler.enums.IndicadorLocalRetangulo;

public class OtimizacaoBusinessImpl extends BaseBusinessImpl implements OtimizacaoBusiness {

	private static final int LIMITE_AREA_OPENGL = 4096 * 4096;
	private static final double BASE_OPENGL = 2;
	private GeometryBusiness geometryBusiness = BusinessFactory.getBusinessInstance(GeometryBusiness.class);

	/** {@inheritDoc} */
	public List<Rectangle2D> otimizarAreaRetangularComRegioesRetangularesSemSobreposicao(final List<Size> sizes) {
		StatusEmpacotamento status = criarStatusEmpacotamentoInicial(sizes, false, false);
		StatusEmpacotamento resultado = processarStatus(status);

		if (resultado == null) {
			if (status.recipiente.getWidth() != status.recipiente.getHeight()) {
				status = criarStatusEmpacotamentoInicial(sizes, false, true);
				resultado = processarStatus(status);
				if (resultado == null) {
					status = criarStatusEmpacotamentoInicial(sizes, true, false);
					resultado = processarStatus(status);
				}
			} else {
				status = criarStatusEmpacotamentoInicial(sizes, true, false);
				resultado = processarStatus(status);
				if (resultado == null) {
					status = criarStatusEmpacotamentoInicial(sizes, true, true);
					resultado = processarStatus(status);
				}
			}
		}

		if (resultado != null) {
			return resultado.regioesOcupadas;
		} else {
			return null;
		}
	}

	/**
	 * Cria o status inicial de empacotamento que possui recipiente com
	 * dimens�es da �rea OpenGL m�nima. A lista de pend�ncias � definida pela
	 * lista de tamanhos passadas por par�metro, ordenados pela largura e pela
	 * altura, nesta ordem. As �ncoras iniciais s�o estabelecidas nos 4 cantos
	 * do recipiente. A lista de regi�es ocupadas � criada sem nenhum elemento.
	 * 
	 * @param sizes
	 *            Lista de tamanhos usadas como lista de pend�ncia para o status
	 *            de empacotamento.
	 * @param areaDobrada
	 *            Se for {@code true} ent�o a �rea do recipiente ter� o dobro da
	 *            �rea m�nima necess�ria.
	 * @param arestasInvertidas
	 *            Se for {@code true} ent�o as dimens�es do recipiente ser�o
	 *            invertidas.
	 * @return Status inicial de empacotamento.
	 */
	private StatusEmpacotamento criarStatusEmpacotamentoInicial(final List<Size> sizes, boolean areaDobrada,
			boolean arestasInvertidas) {
		int areaMinima = geometryBusiness.somarAreas(sizes);
		int areaMinimaOpenGL = calcularAreaMinimaOpenGL(areaMinima);
		if (areaDobrada) {
			areaMinimaOpenGL *= 2;
		}
		Rectangle2D region = calcularRetanguloEquilibradoOpenGL(areaMinimaOpenGL);
		if (arestasInvertidas) {
			region = geometryBusiness.inverterRetangulo(region);
		}
		return criarStatusEmpacotamentoInicial(sizes, region);
	}

	/**
	 * Cria um status de empacotamento inicial com recipiente e pendencia
	 * definidos.
	 * 
	 * @param pendencia
	 *            Lista de pend�ncia.
	 * @param recipiente
	 *            Recipiente para empacotamento.
	 * @return Status inicial de empacotamento.
	 */
	private StatusEmpacotamento criarStatusEmpacotamentoInicial(final List<Size> pendencia, Rectangle2D recipiente) {
		Set<PontoAncora> ancoras = new HashSet<>();
		ancoras.add(new PontoAncora(recipiente.getLeft(), recipiente.getBottom(), IndicadorLocalRetangulo.SUDOESTE));
		ancoras.add(new PontoAncora(recipiente.getLeft(), recipiente.getTop(), IndicadorLocalRetangulo.NOROESTE));
		ancoras.add(new PontoAncora(recipiente.getRight(), recipiente.getTop(), IndicadorLocalRetangulo.NORDESTE));
		ancoras.add(new PontoAncora(recipiente.getRight(), recipiente.getBottom(), IndicadorLocalRetangulo.SUDESTE));

		List<Size> sizesOrdenados = new ArrayList<>(pendencia);
		ordenarSizes(sizesOrdenados);

		StatusEmpacotamento status = new StatusEmpacotamento();
		status.recipiente = recipiente;
		status.ancoras = ancoras;
		status.pendencia = sizesOrdenados;
		status.regioesOcupadas = new ArrayList<>();

		return status;
	}

	/** {@inheritDoc} */
	public int calcularAreaMinimaOpenGL(int areaMinima) {
		if (areaMinima <= 0) {
			return 0;
		}
		int areaOpenGL = 1;
		while (areaOpenGL < areaMinima && areaOpenGL < LIMITE_AREA_OPENGL) {
			areaOpenGL *= BASE_OPENGL;
		}
		if (areaOpenGL >= areaMinima && areaOpenGL <= LIMITE_AREA_OPENGL) {
			return areaOpenGL;
		} else {
			return 0;
		}
	}

	/** {@inheritDoc} */
	public Rectangle2D calcularRetanguloEquilibradoOpenGL(int areaOpenGL) {
		int potenciaTotal = (int) Math.round(Math.log(areaOpenGL) / Math.log(BASE_OPENGL));
		int potenciaMenor = potenciaTotal >>> 1;
		int potenciaMaior = potenciaTotal - potenciaMenor;
		int largura = (int) Math.round(Math.pow(BASE_OPENGL, potenciaMaior));
		int altura = (int) Math.round(Math.pow(BASE_OPENGL, potenciaMenor));
		return new Rectangle2D(largura, altura);
	}

	/** {@inheritDoc} */
	public void ordenarSizes(List<Size> sizes) {
		Collections.sort(sizes, OtimizacaoHelper.getComparatorSizes());
	}

	/**
	 * Processa o status atual de empacotamento at� encontrar a solu��o final,
	 * com uso completo da lista de pend�ncia.
	 * 
	 * @param status
	 *            Status atual.
	 * @return Status de empacotamento final, com a solu��o do empacotamento.
	 *         Caso n�o encontre solu��o esta fun��o retornar� {@code null}.
	 */
	private StatusEmpacotamento processarStatus(final StatusEmpacotamento status) {
		if (status.pendencia.size() == 0) {
			// Empacotamento finalizado!
			return status;
		}

		Size size = status.pendencia.get(0);
		Set<PontoAncora> ancorasSemRedundancia = obterAncorasSemRedundancia(status.ancoras, size);
		List<PontoAncora> ancorasOrdenadas = new ArrayList<>(ancorasSemRedundancia);
		ordenarAncoras(ancorasOrdenadas);
		for (PontoAncora ancora : ancorasOrdenadas) {
			StatusEmpacotamento novoStatus = transitarStatus(status, ancora);
			if (novoStatus == null) {
				// N�o foi poss�vel preencher a regi�o no ponto �ncora.
				continue;
			}
			StatusEmpacotamento resultado = processarStatus(novoStatus);
			if (resultado != null) {
				return resultado;
			}
		}

		return null;
	}

	/** {@inheritDoc} */
	public Set<PontoAncora> obterAncorasSemRedundancia(final Set<PontoAncora> ancoras, final Size size) {
		List<PontoAncora> clone = new ArrayList<>(ancoras);
		Set<PontoAncora> set = new HashSet<>(ancoras.size());

		for (int index = 0; index < clone.size(); index++) {
			PontoAncora ancora = clone.get(index);
			set.add(ancora);

			Rectangle2D region = new Rectangle2D(size, ancora);

			for (PontoAncora redundancia : ancoras) {
				if (redundancia.equals(ancora)) {
					continue;
				}
				if (geometryBusiness.isPontoAncoraDeRetangulo(redundancia, region)) {
					clone.remove(redundancia);
				}
			}
		}

		return set;
	}

	/**
	 * Ordena as �ncoras do menor para o maior. Uma �ncora � ordenado pelo menor
	 * valor de y. Se ambos t�m o mesmo valor de y, ent�o s�o ordenados pelo
	 * menor valor de x.
	 * 
	 * @param ancoras
	 *            Lista de �ncoras.
	 */
	private void ordenarAncoras(List<PontoAncora> ancoras) {
		Collections.sort(ancoras, OtimizacaoHelper.getComparatorAncoras());
	}

	/**
	 * Executa a transi��o do status atual para o novo status, inserindo ao
	 * ponto �ncora do recipiente a primeira regi�o pendente da lista de
	 * tamanhos, mas somente se for poss�vel, ou seja, se n�o houver interse��o
	 * com nenhum ret�ngulo ocupado.
	 * 
	 * @param status
	 *            Status atual.
	 * @param ancora
	 *            Ponto �ncora.
	 * @return O novo status ap�s a transi��o. Este m�todo poder� retornar
	 *         {@code null} se n�o for poss�vel executar a transi��o.
	 */
	private StatusEmpacotamento transitarStatus(final StatusEmpacotamento status, final PontoAncora ancora) {
		Rectangle2D region = new Rectangle2D(status.pendencia.get(0), ancora);
		if (!geometryBusiness.isRetanguloInterno(region, status.recipiente)) {
			return null;
		}
		for (Rectangle2D ocupado : status.regioesOcupadas) {
			if (geometryBusiness.temIntersecao(ocupado, region)) {
				return null;
			}
		}
		Set<PontoAncora> novasAncoras = criarAncoras(status.recipiente, status.regioesOcupadas, region);

		StatusEmpacotamento novoStatus = null;
		try {
			novoStatus = (StatusEmpacotamento) status.clone();
			novoStatus.pendencia.remove(0);
			novoStatus.ancoras.remove(ancora);
			novoStatus.regioesOcupadas.add(region);
			novoStatus.ancoras.addAll(novasAncoras);
			eliminarAncorasInvalidas(novoStatus);
			return novoStatus;
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	/** {@inheritDoc} */
	public Set<PontoAncora> criarAncoras(Rectangle2D recipiente, List<Rectangle2D> regioesOcupadas,
			Rectangle2D region) {
		Set<PontoAncora> ancoras = new HashSet<>();

		// Rastreando pontos superiores � regi�o...
		rastreamentoDeAncoraHorizontal(region.getLeft() - 1, region.getRight() + 1, region.getTop() + 1, recipiente,
				regioesOcupadas, IndicadorLocalRetangulo.SUDOESTE, IndicadorLocalRetangulo.SUDESTE, ancoras);

		// Rastreando pontos inferiores � regi�o...
		rastreamentoDeAncoraHorizontal(region.getLeft() - 1, region.getRight() + 1, region.getBottom() - 1, recipiente,
				regioesOcupadas, IndicadorLocalRetangulo.NOROESTE, IndicadorLocalRetangulo.NORDESTE, ancoras);

		// Rastreando pontos � esquerda da regi�o...
		rastreamentoDeAncoraVertical(region.getTop() + 1, region.getBottom() - 1, region.getLeft() - 1, recipiente,
				regioesOcupadas, IndicadorLocalRetangulo.NORDESTE, IndicadorLocalRetangulo.SUDESTE, ancoras);

		// Rastreando pontos � direita da regi�o...
		rastreamentoDeAncoraVertical(region.getTop() + 1, region.getBottom() - 1, region.getRight() + 1, recipiente,
				regioesOcupadas, IndicadorLocalRetangulo.NOROESTE, IndicadorLocalRetangulo.SUDOESTE, ancoras);

		return ancoras;
	}

	/**
	 * Executa o restramento de ponto na horizontal da esquerda para a direita.
	 * Verifica se os pontos rastrados s�o livres ou n�o no recipiente. Quando
	 * uma transi��o de estados de pontos (livre para ocupado e vice-versa) �
	 * encontrada, � criada uma nova �ncora no local livre da transi��o. O
	 * indicador de local do ret�ngulo estabelecido para a nova �ncora depende
	 * da transi��o.
	 * 
	 * @param limiteLeft
	 *            Limite esquerdo do rastreamento.
	 * @param limiteRight
	 *            Limite direito do rastreamento.
	 * @param y
	 *            Coordenada y do rastreamento..
	 * @param recipiente
	 *            Recipiente.
	 * @param regioesOcupadas
	 *            Regi�o ocupadas do recipiente.
	 * @param onLocalLivre
	 *            Indicador de local de ret�ngulo escolhdo para a nova �ncora
	 *            quando � encontrado um ponto livre logo ap�s o rastreamento de
	 *            um ponto ocupado.
	 * @param onLocalOcupado
	 *            Indicador de local de ret�ngulo escolhdo para a nova �ncora
	 *            quando � encontrado um ponto ocupado logo ap�s o rastreamento
	 *            de um ponto livre.
	 * @param ancoras
	 *            Conjunto de �ncoras, onde as novas �ncoras s�o adicionadas.
	 */
	private void rastreamentoDeAncoraHorizontal(int limiteLeft, int limiteRight, int y, Rectangle2D recipiente,
			List<Rectangle2D> regioesOcupadas, IndicadorLocalRetangulo onLocalLivre,
			IndicadorLocalRetangulo onLocalOcupado, Set<PontoAncora> ancoras) {
		boolean isLastPontoLivre = isPontoLivre(new Point2D(limiteLeft, y), recipiente, regioesOcupadas);
		boolean pontoLivre;
		for (int x = limiteLeft + 1; x <= limiteRight; x++) {
			pontoLivre = isPontoLivre(new Point2D(x, y), recipiente, regioesOcupadas);
			if (pontoLivre != isLastPontoLivre) {
				if (pontoLivre) {
					ancoras.add(new PontoAncora(x, y, onLocalLivre));
				} else {
					ancoras.add(new PontoAncora(x - 1, y, onLocalOcupado));
				}
			}
			isLastPontoLivre = pontoLivre;
		}
	}

	/**
	 * Executa o restramento de ponto na horizontal da esquerda para a direita.
	 * Verifica se os pontos rastrados s�o livres ou n�o no recipiente. Quando
	 * uma transi��o de estados de pontos (livre para ocupado e vice-versa) �
	 * encontrada, � criada uma nova �ncora no local livre da transi��o. O
	 * indicador de local do ret�ngulo estabelecido para a nova �ncora depende
	 * da transi��o.
	 * 
	 * @param limiteTop
	 *            Limite superior do rastreamento.
	 * @param limiteBottom
	 *            Limite inferior do rastreamento.
	 * @param x
	 *            Coordenada x do rastreamento..
	 * @param recipiente
	 *            Recipiente.
	 * @param regioesOcupadas
	 *            Regi�o ocupadas do recipiente.
	 * @param onLocalLivre
	 *            Indicador de local de ret�ngulo escolhdo para a nova �ncora
	 *            quando � encontrado um ponto livre logo ap�s o rastreamento de
	 *            um ponto ocupado.
	 * @param onLocalOcupado
	 *            Indicador de local de ret�ngulo escolhdo para a nova �ncora
	 *            quando � encontrado um ponto ocupado logo ap�s o rastreamento
	 *            de um ponto livre.
	 * @param ancoras
	 *            Conjunto de �ncoras, onde as novas �ncoras s�o adicionadas.
	 */
	private void rastreamentoDeAncoraVertical(int limiteTop, int limiteBottom, int x, Rectangle2D recipiente,
			List<Rectangle2D> regioesOcupadas, IndicadorLocalRetangulo onLocalLivre,
			IndicadorLocalRetangulo onLocalOcupado, Set<PontoAncora> ancoras) {
		boolean isLastPontoLivre = isPontoLivre(new Point2D(x, limiteTop), recipiente, regioesOcupadas);
		boolean pontoLivre;
		for (int y = limiteTop + 1; y >= limiteBottom; y--) {
			pontoLivre = isPontoLivre(new Point2D(x, y), recipiente, regioesOcupadas);
			if (pontoLivre != isLastPontoLivre) {
				if (pontoLivre) {
					ancoras.add(new PontoAncora(x, y, onLocalLivre));
				} else {
					ancoras.add(new PontoAncora(x, y + 1, onLocalOcupado));
				}
			}
			isLastPontoLivre = pontoLivre;
		}
	}

	/**
	 * Verifica se um ponto � livre dentro de um recipiente com regi�es
	 * ocupadas.
	 * 
	 * @param ponto
	 *            Ponto.
	 * @param recipiente
	 *            Recipiente.
	 * @param regioesOcupadas
	 *            Regi�es ocupadas do recipiente.
	 * @return {@code true} se o ponto informado estiver livre no recipiente, ou
	 *         {@code false}, caso contr�rio.
	 */
	private boolean isPontoLivre(Point2D ponto, Rectangle2D recipiente, List<Rectangle2D> regioesOcupadas) {
		return geometryBusiness.isPontoInterno(ponto, recipiente)
				&& !geometryBusiness.isPontoInterno(ponto, regioesOcupadas);
	}

	/** {@inheritDoc} */
	public void eliminarAncorasInvalidas(StatusEmpacotamento status) {
		Set<PontoAncora> ancoras = status.ancoras;

		for (PontoAncora ancora : ancoras) {
			if (geometryBusiness.isPontoInterno(ancora, status.regioesOcupadas)) {
				ancoras.remove(ancora);
				break;
			}
		}
	}

}
