package com.thiagomv.spritecompiler.business;

import java.util.List;

import com.thiagomv.spritecompiler.commons.bases.BaseBusiness;
import com.thiagomv.spritecompiler.data.Point2D;
import com.thiagomv.spritecompiler.data.PontoAncora;
import com.thiagomv.spritecompiler.data.Rectangle2D;
import com.thiagomv.spritecompiler.data.WithArea;

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
	 * @return {@code true} se as regi�es se intersectam, ou {@code false}, caso
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
	 * Calcula a soma das �reas de objetos em uma lista.
	 * 
	 * @param regions
	 *            Lista de objetos.
	 * @return Soma das �reas dos objetos da lista.
	 */
	int somarAreas(List<? extends WithArea> regions);

	/**
	 * Verifica se um ponto � interno a um ret�ngulo.
	 * 
	 * @param ponto
	 *            Ponto.
	 * @param region
	 *            Ret�ngulo.
	 * @return {@code true} se o ponto for interno ao ret�ngulo, ou
	 *         {@code false}, caso contr�rio.
	 */
	boolean isPontoInterno(Point2D ponto, Rectangle2D region);

	/**
	 * Verifica se um ponto � interno a algum ret�ngulo de uma lista de
	 * ret�ngulos.
	 * 
	 * @param ponto
	 *            Ponto.
	 * @param regioes
	 *            Lista de ret�ngulos.
	 * @return {@code true} se o ponto for interno a algum ret�ngulo da lista,
	 *         ou {@code false}, caso contr�rio.
	 */
	boolean isPontoInterno(Point2D ponto, List<Rectangle2D> regioes);

	/**
	 * Verifica se um ponto �ncora � um ponto �ncora de um ret�ngulo.
	 * 
	 * @param ancora
	 *            Ponto �ncora.
	 * @param region
	 *            Ret�ngulo.
	 * @return {@code true} se o ponto �ncora pertencer a uma quina do
	 *         ret�ngulo, ou {@code false}, caso contr�rio.
	 */
	boolean isPontoAncoraDeRetangulo(PontoAncora ancora, Rectangle2D region);

	/**
	 * Verifica se um ret�ngulo � interno a outro ret�ngulo, chamado de
	 * recipiente.
	 * 
	 * @param region
	 *            Ret�ngulo que dever� ser interno a outro.
	 * @param recipiente
	 *            Ret�ngulo que dever� conter outro ret�ngulo.
	 * @return {@code true} se o ret�ngulo estiver interno ao recipiente, ou
	 *         {@code false}, caso contr�rio.
	 */
	boolean isRetanguloInterno(Rectangle2D region, Rectangle2D recipiente);

	/**
	 * Cria um ret�ngulo com dimens�es invertidas.
	 * 
	 * @param retangulo
	 *            Ret�ngulo original.
	 * @return Ret�ngulo invertido
	 */
	Rectangle2D inverterRetangulo(final Rectangle2D retangulo);

}
