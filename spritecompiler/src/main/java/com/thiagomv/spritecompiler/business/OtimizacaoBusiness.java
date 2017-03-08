package com.thiagomv.spritecompiler.business;

import java.util.List;
import java.util.Set;

import com.kuemsoftwares.util.commons.base.BaseBusiness;
import com.thiagomv.spritecompiler.model.Rectangle2D;
import com.thiagomv.spritecompiler.model.Size;
import com.thiagomv.spritecompiler.model.StatusEmpacotamento;
import com.thiagomv.spritecompiler.model.VerticeRetangulo2D;

public interface OtimizacaoBusiness extends BaseBusiness {
	/**
	 * Obt�m a lista de ret�ngulos que est�o contidos em uma regi�o retangular
	 * comum. Os ret�ngulos nesta lista n�o se sobrep�em e possuem as dimens�es
	 * de cada regi�o passada por par�metro. A �rea retangular que cont�m os
	 * ret�ngulos possui �rea m�nima necess�ria para conter todas as regi�es sem
	 * sobreposi��o entre elas.
	 * 
	 * @param sizes
	 *            Regi�es retangulares que devem ser organizadas, sem
	 *            sobreposi��o, de forma que ocupem uma regi�o retangular
	 *            m�nima.
	 * @return Lista de ret�ngulos.
	 */
	List<Rectangle2D> otimizarAreaRetangularComRegioesRetangularesSemSobreposicao(
			List<Size> sizes);

	/**
	 * Obt�m a �rea m�nima, compat�vel com OpenGL, que suporte a �rea informada
	 * por par�metro. As dimens�es de uma imagem OpenGL devem ser pot�ncias de
	 * 2. Desta forma, a �rea de uma imagem no OpenGL tamb�m ter� a medida da
	 * �rea como uma pot�ncia de 2.
	 * 
	 * @param areaMinima
	 *            �rea que a regi�o OpenGL dever� suportar.
	 * @return �rea m�nima no OpenGL que suporte a �rea informada por par�metro.
	 *         Se a �rea informada � maior que a �rea suportada pelo OpenGL esta
	 *         fun��o retornar� o valor 0.
	 */
	int calcularAreaMinimaOpenGL(int areaMinima);

	/**
	 * Cria um ret�ngulo com dimens�es compat�veis com o OpenGL (pot�ncias de
	 * 2), de tal forma que o ret�ngulo fique o mais "quadrado" poss�vel. Se n�o
	 * for poss�vel criar um quadrado, o ret�ngulo ter� largura maior que
	 * altura. A area informada por par�metro defer� ser uma �rea compat�vel com
	 * OpenGL, ou seja, uma pot�ncia de 2.
	 * 
	 * @param areaOpenGL
	 *            �rea que a regi�o dever� conter.
	 * @return Regi�o compat�vel com OpenGL, que contenha exatamente a �rea
	 *         passada por par�metro.
	 */
	Rectangle2D calcularRetanguloEquilibradoOpenGL(int areaOpenGL);

	/**
	 * Ordena os tamanhos do maior para o menor. Um tamanho � ordenado pela
	 * maior medida lateral (largura ou altura). Se ambos t�m a mesma maior
	 * medida lateral, ent�o s�o ordenados pela segunda maior medida lateral.
	 * 
	 * @param sizes
	 *            Lista de tamanhos.
	 * @return Lista ordenada
	 */
	void ordenarSizes(List<Size> sizes);

	/**
	 * Obt�m o conjunto de �ncoras que podem ser utilizadas, ap�s aplicar o
	 * tamanho informado ao conjunto de �ncoras. Caso a utiliza��o de uma �ncora
	 * encaixe o tamanho exatamente na posi��o de outra �ncora, as duas �ncoras
	 * se tornam redundantes. Neste caso apenas uma das �ncoras j� � suficiente
	 * para abordar os dois casos poss�veis.
	 * 
	 * @param ancoras
	 *            Lista de �ncoras.
	 * @param size
	 *            Tamanho.
	 * @return Lista com �ncoras suficientes para abordar todos os casos sem
	 *         redund�ncia.
	 */
	Set<VerticeRetangulo2D> obterAncorasSemRedundancia(final Set<VerticeRetangulo2D> ancoras,
			final Size size);

	/**
	 * Obt�m o conjunto de �ncoras que surgem ap�s inserir uma nova regi�o no
	 * recipiente. Sup�e-se que a regi�o esteja completamente contida na regi�o
	 * do recipiente e que possa ser inserida no recipiente sem conflitos de
	 * interse��o com as regi�es j� ocupadas.
	 * 
	 * @param recipiente
	 *            Regi�o do recipiente.
	 * @param regioesOcupadas
	 *            Regi�es ocupadas no recipiente.
	 * @param region
	 *            Nova regi�o a ser inserida no recipiente.
	 * @return Conjunto de novas �ncoras.
	 */
	Set<VerticeRetangulo2D> criarAncoras(Rectangle2D recipiente,
			List<Rectangle2D> regioesOcupadas, Rectangle2D region);

	/**
	 * Elimina �ncoras inv�lidas de um status de empacotamento. Um ponto �ncora
	 * � considerado inv�lido se estiver interno a alguma regi�o ocupada do
	 * recipiente.
	 */
	void eliminarAncorasInvalidas(StatusEmpacotamento status);
}
