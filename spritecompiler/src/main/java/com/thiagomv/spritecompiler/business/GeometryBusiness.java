package com.thiagomv.spritecompiler.business;

import java.util.List;

import com.kuemsoftwares.util.commons.base.BaseBusiness;
import com.thiagomv.spritecompiler.model.Point2D;
import com.thiagomv.spritecompiler.model.Rectangle2D;
import com.thiagomv.spritecompiler.model.VerticeRetangulo2D;
import com.thiagomv.spritecompiler.model.WithArea;

/**
 * Serviços relacionados a geometria.
 * 
 * @author Thiago Mendes Vieira - thiagomv.0301.developer@gmail.com
 *
 */
public interface GeometryBusiness extends BaseBusiness {

	/**
	 * Verifica se duas regiões retangulares possuem interseção no plano
	 * bidimensional. Note que se duas regiões possuirem os mesmos pixels
	 * limitadores left, top, right ou bottom, isso significará intersessão.
	 * Quando uma região estiver tangente a outra, sem intersessão, elas terão
	 * medidas de limitadores com diferênça de 1 pixel.
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
	 * Verifica se o valor {@code v} pertence ao intervalo de valores, incluindo
	 * seus limites.
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
	 * Verifica se o ponto é interno ao retângulo.
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
	 * Verifica se o ponto é interno a algum retângulo de uma lista de
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
	 * Verifica se o vértice pertence ao retângulo.
	 * 
	 * @param vertice
	 *            Vértice.
	 * @param region
	 *            Retângulo.
	 * @return {@code true} se o vértice pertencer ao retângulo, ou
	 *         {@code false}, caso contrário.
	 */
	boolean isVerticeDeRetangulo(VerticeRetangulo2D vertice, Rectangle2D region);

	/**
	 * Verifica se o retângulo é interno a outro retângulo, chamado de
	 * recipiente.
	 * 
	 * @param region
	 *            Retângulo menor, que deverá ser interno ao outro.
	 * @param recipiente
	 *            Retângulo maior, que deverá conter o outro retângulo.
	 * @return {@code true} se o retângulo estiver interno ao recipiente, ou
	 *         {@code false}, caso contrário.
	 */
	boolean isRetanguloInterno(Rectangle2D region, Rectangle2D recipiente);

	/**
	 * Cria um retângulo com dimensões invertidas, mantendo fixo o vértice
	 * inferior esquerdo. Em outras palavras, um retângulo com as dimensões
	 * [width=100, height=200] terá dimensões [width=200, height=100] ao ser
	 * invertido e continuará contendo o vértice inferior esquerdo.
	 * 
	 * @param retangulo
	 *            Retângulo original.
	 * @return Retângulo invertido
	 */
	Rectangle2D inverterRetangulo(final Rectangle2D retangulo);

	/**
	 * Cria um retângulo a partir de outro, expandindo ou reduzindo suas
	 * laterais. Não verifica se as laterais podem ser reduzidas com o
	 * comprimento em pixels informado.
	 * 
	 * @param region
	 *            Retângulo original.
	 * @param lenght
	 *            Quantidade de pixels que cada lateral do retângulo deve ser
	 *            expandida ou reduzida. Valors positivos indicam expansão das
	 *            laterais, enquanto valores negativos indicam redução.
	 * @return Retângulo redimensionado.
	 */
	Rectangle2D redimensionar(Rectangle2D region, int lenght);

}
