package com.thiagomv.spritecompiler.business;

import java.util.List;
import java.util.Set;

import com.thiagomv.spritecompiler.commons.bases.BaseBusiness;
import com.thiagomv.spritecompiler.data.PontoAncora;
import com.thiagomv.spritecompiler.data.Rectangle2D;
import com.thiagomv.spritecompiler.data.Size;
import com.thiagomv.spritecompiler.data.StatusEmpacotamento;

public interface OtimizacaoBusiness extends BaseBusiness {
	/**
	 * Obtém a lista de retângulos que estão contidos em uma região retangular
	 * comum. Os retângulos nesta lista não se sobrepõem e possuem as dimensões
	 * de cada região passada por parâmetro. A área retangular que contém os
	 * retângulos possui área mínima necessária para conter todas as regiões sem
	 * sobreposição entre elas.
	 * 
	 * @param sizes
	 *            Regiões retangulares que devem ser organizadas, sem
	 *            sobreposição, de forma que ocupem uma região retangular
	 *            mínima.
	 * @return Lista de retângulos.
	 */
	List<Rectangle2D> otimizarAreaRetangularComRegioesRetangularesSemSobreposicao(
			List<Size> sizes);

	/**
	 * Obtém a área mínima, compatível com OpenGL, que suporte a área informada
	 * por parâmetro. As dimensões de uma imagem OpenGL devem ser potências de
	 * 2. Desta forma, a área de uma imagem no OpenGL também terá a medida da
	 * área como uma potência de 2.
	 * 
	 * @param areaMinima
	 *            Área que a região OpenGL deverá suportar.
	 * @return Área mínima no OpenGL que suporte a área informada por parâmetro.
	 *         Se a área informada é maior que a área suportada pelo OpenGL esta
	 *         função retornará o valor 0.
	 */
	int calcularAreaMinimaOpenGL(int areaMinima);

	/**
	 * Cria um retângulo com dimensões compatíveis com o OpenGL (potências de
	 * 2), de tal forma que o retângulo fique o mais "quadrado" possìvel. Se não
	 * for possível criar um quadrado, o retângulo terá largura maior que
	 * altura. A area informada por parâmetro deferá ser uma área compatível com
	 * OpenGL, ou seja, uma potência de 2.
	 * 
	 * @param areaOpenGL
	 *            Área que a região deverá conter.
	 * @return Região compatível com OpenGL, que contenha exatamente a área
	 *         passada por parâmetro.
	 */
	Rectangle2D calcularRetanguloEquilibradoOpenGL(int areaOpenGL);

	/**
	 * Ordena os tamanhos do maior para o menor. Um tamanho é ordenado pela
	 * maior medida lateral (largura ou altura). Se ambos têm a mesma maior
	 * medida lateral, então são ordenados pela segunda maior medida lateral.
	 * 
	 * @param sizes
	 *            Lista de tamanhos.
	 * @return Lista ordenada
	 */
	void ordenarSizes(List<Size> sizes);

	/**
	 * Obtém o conjunto de âncoras que podem ser utilizadas, após aplicar o
	 * tamanho informado ao conjunto de âncoras. Caso a utilização de uma âncora
	 * encaixe o tamanho exatamente na posição de outra âncora, as duas àncoras
	 * se tornam redundantes. Neste caso apenas uma das âncoras já é suficiente
	 * para abordar os dois casos possíveis.
	 * 
	 * @param ancoras
	 *            Lista de âncoras.
	 * @param size
	 *            Tamanho.
	 * @return Lista com âncoras suficientes para abordar todos os casos sem
	 *         redundância.
	 */
	Set<PontoAncora> obterAncorasSemRedundancia(final Set<PontoAncora> ancoras,
			final Size size);

	/**
	 * Obtém o conjunto de âncoras que surgem após inserir uma nova região no
	 * recipiente. Supõe-se que a região esteja completamente contida na região
	 * do recipiente e que possa ser inserida no recipiente sem conflitos de
	 * interseção com as regiões já ocupadas.
	 * 
	 * @param recipiente
	 *            Região do recipiente.
	 * @param regioesOcupadas
	 *            Regiões ocupadas no recipiente.
	 * @param region
	 *            Nova região a ser inserida no recipiente.
	 * @return Conjunto de novas âncoras.
	 */
	Set<PontoAncora> criarAncoras(Rectangle2D recipiente,
			List<Rectangle2D> regioesOcupadas, Rectangle2D region);

	/**
	 * Elimina âncoras inválidas de um status de empacotamento. Um ponto âncora
	 * é considerado inválido se estiver interno a alguma região ocupada do
	 * recipiente.
	 */
	void eliminarAncorasInvalidas(StatusEmpacotamento status);
}
