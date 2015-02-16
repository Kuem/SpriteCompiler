package com.thiagomv.spritecompiler.business;

import java.util.List;

import com.thiagomv.spritecompiler.commons.bases.BaseBusiness;
import com.thiagomv.spritecompiler.data.Point2D;
import com.thiagomv.spritecompiler.data.PontoAncora;
import com.thiagomv.spritecompiler.data.Rectangle2D;
import com.thiagomv.spritecompiler.data.WithArea;

public interface GeometryBusiness extends BaseBusiness {

	/**
	 * Verifica se duas regiões retangulares possuem interseção no plano
	 * bidimensional. Note que se duas regiões possuirem os mesmos pixels
	 * limitadores left, top, right ou bottom, isso pode significar intersessão.
	 * Quando uma região está logo ao lado de outra (uma possível tangência),
	 * elas terão medidas de limitadores com diferênça de 1 pixel.
	 * 
	 * @param region1
	 *            Uma região.
	 * @param region2
	 *            Outra região.
	 * @return {@code true} se as regiões se intersectam, ou {@code false}, caso
	 *         contrário.
	 */
	boolean temIntersecao(Rectangle2D region1, Rectangle2D region2);

	/**
	 * Verifica se um valor {@code v} pertence a um intervalo de valores,
	 * incluindo seus limites.
	 * 
	 * @param left
	 *            Limite esquerdo.
	 * @param right
	 *            Limite direito.
	 * @param v
	 *            Valor.
	 * @return {@code true} se o valor em questão pertence ao intervalo, ou
	 *         {@code false}, caso contrário.
	 */
	boolean pertenceAoIntervalo(int left, int right, int v);

	/**
	 * Calcula a soma das áreas de objetos em uma lista.
	 * 
	 * @param regions
	 *            Lista de objetos.
	 * @return Soma das áreas dos objetos da lista.
	 */
	int somarAreas(List<? extends WithArea> regions);

	/**
	 * Verifica se um ponto é interno a um retângulo.
	 * 
	 * @param ponto
	 *            Ponto.
	 * @param region
	 *            Retângulo.
	 * @return {@code true} se o ponto for interno ao retângulo, ou
	 *         {@code false}, caso contrário.
	 */
	boolean isPontoInterno(Point2D ponto, Rectangle2D region);

	/**
	 * Verifica se um ponto é interno a algum retângulo de uma lista de
	 * retângulos.
	 * 
	 * @param ponto
	 *            Ponto.
	 * @param regioes
	 *            Lista de retângulos.
	 * @return {@code true} se o ponto for interno a algum retângulo da lista,
	 *         ou {@code false}, caso contrário.
	 */
	boolean isPontoInterno(Point2D ponto, List<Rectangle2D> regioes);

	/**
	 * Verifica se um ponto âncora é um ponto âncora de um retângulo.
	 * 
	 * @param ancora
	 *            Ponto âncora.
	 * @param region
	 *            Retângulo.
	 * @return {@code true} se o ponto âncora pertencer a uma quina do
	 *         retângulo, ou {@code false}, caso contrário.
	 */
	boolean isPontoAncoraDeRetangulo(PontoAncora ancora, Rectangle2D region);

	/**
	 * Verifica se um retângulo é interno a outro retângulo, chamado de
	 * recipiente.
	 * 
	 * @param region
	 *            Retângulo que deverá ser interno a outro.
	 * @param recipiente
	 *            Retângulo que deverá conter outro retângulo.
	 * @return {@code true} se o retângulo estiver interno ao recipiente, ou
	 *         {@code false}, caso contrário.
	 */
	boolean isRetanguloInterno(Rectangle2D region, Rectangle2D recipiente);

	/**
	 * Cria um retângulo com dimensões invertidas.
	 * 
	 * @param retangulo
	 *            Retângulo original.
	 * @return Retângulo invertido
	 */
	Rectangle2D inverterRetangulo(final Rectangle2D retangulo);

}
