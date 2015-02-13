package com.thiagomv.spritecompiler.business;

import java.util.List;

import com.thiagomv.spritecompiler.commons.BaseBusiness;
import com.thiagomv.spritecompiler.data.Rectangle2D;

public interface GeometryBusiness extends BaseBusiness {

	/**
	 * Verifica se duas regi�es retangulares possuem interse��o no plano
	 * bidimensional. Note que se duas regi�es possuirem os mesmos pixels
	 * limitadores left, top, right ou bottom, isso pode significar intersess�o.
	 * Quando uma regi�o est� logo ao lado de outra (uma poss�vel tang�ncia),
	 * elas ter�o medidas de limitadores com difer�n�a de 1 pixel.
	 * 
	 * @param region1
	 *            Uma regi�o.
	 * @param region2
	 *            Outra regi�o.
	 * @return [@code true} se as regi�es se intersectam, ou {@code false}, caso
	 *         contr�rio.
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
	 * @return {@code true} se o valor em quest�o pertence ao intervalo, ou
	 *         {@code false}, caso contr�rio.
	 */
	boolean pertenceAoIntervalo(int left, int right, int v);

	/**
	 * Calcula a soma das �reas de ret�ngulos em uma lista.
	 * 
	 * @param regions
	 *            Lista de ret�ngulos.
	 * @return Soma das �reas dos ret�ngulos da lista.
	 */
	int somarAreas(List<Rectangle2D> regions);
}
