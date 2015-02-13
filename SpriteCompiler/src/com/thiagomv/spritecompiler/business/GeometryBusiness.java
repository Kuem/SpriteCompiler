package com.thiagomv.spritecompiler.business;

import java.util.List;

import com.thiagomv.spritecompiler.commons.BaseBusiness;
import com.thiagomv.spritecompiler.data.Rectangle2D;

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
	 * @return [@code true} se as regiões se intersectam, ou {@code false}, caso
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
	 * Calcula a soma das áreas de retângulos em uma lista.
	 * 
	 * @param regions
	 *            Lista de retângulos.
	 * @return Soma das áreas dos retângulos da lista.
	 */
	int somarAreas(List<Rectangle2D> regions);
}
