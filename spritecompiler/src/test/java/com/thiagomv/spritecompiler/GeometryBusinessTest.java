package com.thiagomv.spritecompiler;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.thiagomv.spritecompiler.business.GeometryBusiness;
import com.thiagomv.spritecompiler.factories.BusinessFactory;
import com.thiagomv.spritecompiler.model.IndicadorVerticeRetangulo2D;
import com.thiagomv.spritecompiler.model.Point2D;
import com.thiagomv.spritecompiler.model.Rectangle2D;
import com.thiagomv.spritecompiler.model.VerticeRetangulo2D;

public class GeometryBusinessTest {
	private GeometryBusiness geometryBusiness = BusinessFactory.getBusinessInstance(GeometryBusiness.class);

	/**
	 * Verifica os casos de interseção em ponto tangente nos vértices e linhas
	 * tangentes nas laterais dos retângulos.
	 */
	@Test
	public void verificaRegioesComIntersecaoTangente() {
		Rectangle2D regionFixo = new Rectangle2D(10, 12, 18, 20);
		Rectangle2D limiteLeftTop = new Rectangle2D(2, 20, 10, 22);
		Rectangle2D limiteCenterTop = new Rectangle2D(12, 20, 16, 22);
		Rectangle2D limiteRightTop = new Rectangle2D(18, 20, 20, 22);
		Rectangle2D limiteRigthCenter = new Rectangle2D(18, 14, 20, 18);
		Rectangle2D limiteRightBottom = new Rectangle2D(18, 10, 20, 12);
		Rectangle2D limiteCenterBottom = new Rectangle2D(12, 10, 16, 12);
		Rectangle2D limiteLeftBottom = new Rectangle2D(8, 2, 10, 12);
		Rectangle2D limiteLeftCenter = new Rectangle2D(2, 14, 10, 18);

		assertThat(geometryBusiness.temIntersecao(regionFixo, limiteLeftTop), equalTo(true));
		assertThat(geometryBusiness.temIntersecao(regionFixo, limiteCenterTop), equalTo(true));
		assertThat(geometryBusiness.temIntersecao(regionFixo, limiteRightTop), equalTo(true));
		assertThat(geometryBusiness.temIntersecao(regionFixo, limiteRigthCenter), equalTo(true));
		assertThat(geometryBusiness.temIntersecao(regionFixo, limiteRightBottom), equalTo(true));
		assertThat(geometryBusiness.temIntersecao(regionFixo, limiteCenterBottom), equalTo(true));
		assertThat(geometryBusiness.temIntersecao(regionFixo, limiteLeftBottom), equalTo(true));
		assertThat(geometryBusiness.temIntersecao(regionFixo, limiteLeftCenter), equalTo(true));
	}

	/**
	 * Verifica os casos de interseção por áreas.
	 */
	@Test
	public void verificaRegioesComAreasInternas() {
		Rectangle2D regionFixo = new Rectangle2D(10, 12, 18, 20);
		Rectangle2D inTotal = new Rectangle2D(12, 14, 16, 18);
		Rectangle2D inParcial = new Rectangle2D(8, 8, 20, 22);
		Rectangle2D inExato = new Rectangle2D(10, 12, 18, 20);
		Rectangle2D inRightBottom = new Rectangle2D(5, 16, 14, 40);
		Rectangle2D inLeftBottom = new Rectangle2D(14, 16, 30, 40);
		Rectangle2D inRightTop = new Rectangle2D(5, 8, 14, 16);
		Rectangle2D inLeftTop = new Rectangle2D(14, 8, 30, 16);
		Rectangle2D inLeftLineTotal = new Rectangle2D(14, 8, 30, 30);
		Rectangle2D inRightLineTotal = new Rectangle2D(5, 8, 14, 30);
		Rectangle2D inTopLineTotal = new Rectangle2D(5, 8, 30, 16);
		Rectangle2D inBottomLineTotal = new Rectangle2D(5, 16, 22, 30);
		Rectangle2D inLeftLineParcial = new Rectangle2D(14, 14, 30, 16);
		Rectangle2D inRightLineParcial = new Rectangle2D(5, 14, 14, 16);
		Rectangle2D inTopLineParcial = new Rectangle2D(15, 8, 17, 16);
		Rectangle2D inBottomLineParcial = new Rectangle2D(15, 16, 17, 30);

		assertThat(geometryBusiness.temIntersecao(regionFixo, inTotal), equalTo(true));
		assertThat(geometryBusiness.temIntersecao(regionFixo, inParcial), equalTo(true));
		assertThat(geometryBusiness.temIntersecao(regionFixo, inExato), equalTo(true));
		assertThat(geometryBusiness.temIntersecao(regionFixo, inRightBottom), equalTo(true));
		assertThat(geometryBusiness.temIntersecao(regionFixo, inLeftBottom), equalTo(true));
		assertThat(geometryBusiness.temIntersecao(regionFixo, inRightTop), equalTo(true));
		assertThat(geometryBusiness.temIntersecao(regionFixo, inLeftTop), equalTo(true));
		assertThat(geometryBusiness.temIntersecao(regionFixo, inLeftLineTotal), equalTo(true));
		assertThat(geometryBusiness.temIntersecao(regionFixo, inRightLineTotal), equalTo(true));
		assertThat(geometryBusiness.temIntersecao(regionFixo, inTopLineTotal), equalTo(true));
		assertThat(geometryBusiness.temIntersecao(regionFixo, inBottomLineTotal), equalTo(true));
		assertThat(geometryBusiness.temIntersecao(regionFixo, inLeftLineParcial), equalTo(true));
		assertThat(geometryBusiness.temIntersecao(regionFixo, inRightLineParcial), equalTo(true));
		assertThat(geometryBusiness.temIntersecao(regionFixo, inTopLineParcial), equalTo(true));
		assertThat(geometryBusiness.temIntersecao(regionFixo, inBottomLineParcial), equalTo(true));
	}

	/**
	 * Verifica casos sem interseção.
	 */
	@Test
	public void verificaRegioesSemIntersecaoNemAreasInternas() {
		Rectangle2D regionFixo = new Rectangle2D(10, 12, 18, 20);
		Rectangle2D leftCenter = new Rectangle2D(2, 14, 8, 18);
		Rectangle2D topCenter = new Rectangle2D(12, 22, 16, 30);
		Rectangle2D rightCenter = new Rectangle2D(20, 14, 28, 18);
		Rectangle2D bottomCenter = new Rectangle2D(12, 4, 16, 8);

		assertThat(geometryBusiness.temIntersecao(regionFixo, leftCenter), equalTo(false));
		assertThat(geometryBusiness.temIntersecao(regionFixo, topCenter), equalTo(false));
		assertThat(geometryBusiness.temIntersecao(regionFixo, rightCenter), equalTo(false));
		assertThat(geometryBusiness.temIntersecao(regionFixo, bottomCenter), equalTo(false));
	}

	/**
	 * Verifica os casos de teste de intervalos envolvendo intervalos positivos,
	 * negativos e mistos.
	 */
	@Test
	public void verificaIntervalos() {
		assertThat(geometryBusiness.pertenceAoIntervalo(-2, 8, -1), equalTo(true));
		assertThat(geometryBusiness.pertenceAoIntervalo(-2, 8, 0), equalTo(true));
		assertThat(geometryBusiness.pertenceAoIntervalo(-2, 8, 1), equalTo(true));
		assertThat(geometryBusiness.pertenceAoIntervalo(2, 8, -1), equalTo(false));
		assertThat(geometryBusiness.pertenceAoIntervalo(2, 8, 0), equalTo(false));
		assertThat(geometryBusiness.pertenceAoIntervalo(2, 8, 1), equalTo(false));
		assertThat(geometryBusiness.pertenceAoIntervalo(-8, -2, -1), equalTo(false));
		assertThat(geometryBusiness.pertenceAoIntervalo(-8, -2, 0), equalTo(false));
		assertThat(geometryBusiness.pertenceAoIntervalo(-8, -2, 1), equalTo(false));
	}

	/**
	 * Verifica a soma de áreas de retângulos.
	 */
	@Test
	public void somarAreasComSucesso() {
		// area = 11 * 3 = 33
		Rectangle2D region1 = new Rectangle2D(0, 10, 10, 12);
		// area = 2 * 20 = 40
		Rectangle2D region2 = new Rectangle2D(-2, -5, -1, 14);
		// area = 8 * 4 = 32
		Rectangle2D region3 = new Rectangle2D(-7, -5, 0, -2);
		int area = geometryBusiness.somarAreas(Arrays.asList(region1, region2, region3));
		assertThat(area, equalTo(105));
	}

	/**
	 * Verifica os casos de pontos internos e externos ao retêngulo.
	 */
	@Test
	public void verificaPontoInternoEExternoAoRetangulo() {
		Rectangle2D region = new Rectangle2D(10, 2, 18, 43);

		// Ponto na origem do plano
		assertThat(geometryBusiness.isPontoInterno(new Point2D(0, 0), region), equalTo(false));

		// Pontos tangentes ao vértice
		assertThat(geometryBusiness.isPontoInterno(new Point2D(10, 2), region), equalTo(true));
		assertThat(geometryBusiness.isPontoInterno(new Point2D(10, 43), region), equalTo(true));
		assertThat(geometryBusiness.isPontoInterno(new Point2D(18, 43), region), equalTo(true));
		assertThat(geometryBusiness.isPontoInterno(new Point2D(18, 2), region), equalTo(true));

		// Pontos tangentes às laterais
		assertThat(geometryBusiness.isPontoInterno(new Point2D(10, 5), region), equalTo(true));
		assertThat(geometryBusiness.isPontoInterno(new Point2D(17, 43), region), equalTo(true));
		assertThat(geometryBusiness.isPontoInterno(new Point2D(18, 21), region), equalTo(true));
		assertThat(geometryBusiness.isPontoInterno(new Point2D(15, 2), region), equalTo(true));

		// Pontos externos
		assertThat(geometryBusiness.isPontoInterno(new Point2D(2, 10), region), equalTo(false));
		assertThat(geometryBusiness.isPontoInterno(new Point2D(2, 18), region), equalTo(false));
		assertThat(geometryBusiness.isPontoInterno(new Point2D(2, 43), region), equalTo(false));
		assertThat(geometryBusiness.isPontoInterno(new Point2D(43, 2), region), equalTo(false));
		assertThat(geometryBusiness.isPontoInterno(new Point2D(43, 18), region), equalTo(false));
		assertThat(geometryBusiness.isPontoInterno(new Point2D(43, 43), region), equalTo(false));
		assertThat(geometryBusiness.isPontoInterno(new Point2D(19, 2), region), equalTo(false));

		// Pontos internos
		assertThat(geometryBusiness.isPontoInterno(new Point2D(12, 12), region), equalTo(true));
		assertThat(geometryBusiness.isPontoInterno(new Point2D(17, 40), region), equalTo(true));
		assertThat(geometryBusiness.isPontoInterno(new Point2D(11, 3), region), equalTo(true));
		assertThat(geometryBusiness.isPontoInterno(new Point2D(11, 20), region), equalTo(true));
		assertThat(geometryBusiness.isPontoInterno(new Point2D(11, 42), region), equalTo(true));
		assertThat(geometryBusiness.isPontoInterno(new Point2D(17, 3), region), equalTo(true));
	}

	/**
	 * Verifica os casos de pontos internos e externos a algum retângulo da
	 * lista.
	 */
	@Test
	public void verificaPontoInternoEExternoAMuitosRetangulos() {
		List<Rectangle2D> regions = Arrays.asList(new Rectangle2D(10, 2, 18, 43), new Rectangle2D(2, 10, 9, 18));

		// Pontos internos ao primeiro retângulo
		assertThat(geometryBusiness.isPontoInterno(new Point2D(10, 2), regions), equalTo(true));
		assertThat(geometryBusiness.isPontoInterno(new Point2D(10, 43), regions), equalTo(true));
		assertThat(geometryBusiness.isPontoInterno(new Point2D(18, 43), regions), equalTo(true));
		assertThat(geometryBusiness.isPontoInterno(new Point2D(18, 2), regions), equalTo(true));

		// Pontos internos ao segundo retângulo
		assertThat(geometryBusiness.isPontoInterno(new Point2D(2, 10), regions), equalTo(true));
		assertThat(geometryBusiness.isPontoInterno(new Point2D(2, 18), regions), equalTo(true));

		// Pontos externos
		assertThat(geometryBusiness.isPontoInterno(new Point2D(0, 0), regions), equalTo(false));
		assertThat(geometryBusiness.isPontoInterno(new Point2D(2, 43), regions), equalTo(false));
		assertThat(geometryBusiness.isPontoInterno(new Point2D(43, 2), regions), equalTo(false));
		assertThat(geometryBusiness.isPontoInterno(new Point2D(43, 18), regions), equalTo(false));
		assertThat(geometryBusiness.isPontoInterno(new Point2D(43, 43), regions), equalTo(false));
		assertThat(geometryBusiness.isPontoInterno(new Point2D(19, 2), regions), equalTo(false));
	}

	/**
	 * Verifica os casos em que o vértice pertence ao retângulo ou não.
	 */
	@Test
	public void verificaVerticesDeRetangulo() {
		Rectangle2D region = new Rectangle2D(10, 2, 18, 43);

		assertThat(geometryBusiness.isVerticeDeRetangulo(
				new VerticeRetangulo2D(0, 0, IndicadorVerticeRetangulo2D.NORDESTE), region), equalTo(false));
		assertThat(geometryBusiness.isVerticeDeRetangulo(
				new VerticeRetangulo2D(10, 2, IndicadorVerticeRetangulo2D.SUDOESTE), region), equalTo(true));
		assertThat(geometryBusiness.isVerticeDeRetangulo(
				new VerticeRetangulo2D(10, 43, IndicadorVerticeRetangulo2D.NOROESTE), region), equalTo(true));
		assertThat(geometryBusiness.isVerticeDeRetangulo(
				new VerticeRetangulo2D(18, 43, IndicadorVerticeRetangulo2D.NORDESTE), region), equalTo(true));
		assertThat(geometryBusiness.isVerticeDeRetangulo(
				new VerticeRetangulo2D(18, 2, IndicadorVerticeRetangulo2D.SUDESTE), region), equalTo(true));
		assertThat(geometryBusiness.isVerticeDeRetangulo(
				new VerticeRetangulo2D(10, 2, IndicadorVerticeRetangulo2D.SUDESTE), region), equalTo(false));
		assertThat(geometryBusiness.isVerticeDeRetangulo(
				new VerticeRetangulo2D(10, 43, IndicadorVerticeRetangulo2D.NORDESTE), region), equalTo(false));
		assertThat(geometryBusiness.isVerticeDeRetangulo(
				new VerticeRetangulo2D(18, 43, IndicadorVerticeRetangulo2D.NOROESTE), region), equalTo(false));
		assertThat(geometryBusiness.isVerticeDeRetangulo(
				new VerticeRetangulo2D(18, 2, IndicadorVerticeRetangulo2D.SUDOESTE), region), equalTo(false));
		assertThat(geometryBusiness.isVerticeDeRetangulo(
				new VerticeRetangulo2D(2, 10, IndicadorVerticeRetangulo2D.NORDESTE), region), equalTo(false));
		assertThat(geometryBusiness.isVerticeDeRetangulo(
				new VerticeRetangulo2D(2, 18, IndicadorVerticeRetangulo2D.NORDESTE), region), equalTo(false));
		assertThat(geometryBusiness.isVerticeDeRetangulo(
				new VerticeRetangulo2D(2, 43, IndicadorVerticeRetangulo2D.NORDESTE), region), equalTo(false));
		assertThat(geometryBusiness.isVerticeDeRetangulo(
				new VerticeRetangulo2D(43, 2, IndicadorVerticeRetangulo2D.NORDESTE), region), equalTo(false));
		assertThat(geometryBusiness.isVerticeDeRetangulo(
				new VerticeRetangulo2D(43, 18, IndicadorVerticeRetangulo2D.NORDESTE), region), equalTo(false));
		assertThat(geometryBusiness.isVerticeDeRetangulo(
				new VerticeRetangulo2D(43, 43, IndicadorVerticeRetangulo2D.NORDESTE), region), equalTo(false));
		assertThat(geometryBusiness.isVerticeDeRetangulo(
				new VerticeRetangulo2D(19, 2, IndicadorVerticeRetangulo2D.NORDESTE), region), equalTo(false));
	}

	/**
	 * Verifica os casos em que a região intersecta o recipiente de forma
	 * tangente, contendo região externa ao recipiente.
	 */
	@Test
	public void verificarRetanguloInternoUtilizandoIntersecaoTangente() {
		Rectangle2D recipiente = new Rectangle2D(10, 12, 18, 20);

		Rectangle2D limiteLeftTop = new Rectangle2D(2, 20, 10, 22);
		Rectangle2D limiteCenterTop = new Rectangle2D(12, 20, 16, 22);
		Rectangle2D limiteRightTop = new Rectangle2D(18, 20, 20, 22);
		Rectangle2D limiteRigthCenter = new Rectangle2D(18, 14, 20, 18);
		Rectangle2D limiteRightBottom = new Rectangle2D(18, 10, 20, 12);
		Rectangle2D limiteCenterBottom = new Rectangle2D(12, 10, 16, 12);
		Rectangle2D limiteLeftBottom = new Rectangle2D(8, 2, 10, 12);
		Rectangle2D limiteLeftCenter = new Rectangle2D(2, 14, 10, 18);

		assertThat(geometryBusiness.isRetanguloInterno(limiteLeftTop, recipiente), equalTo(false));
		assertThat(geometryBusiness.isRetanguloInterno(limiteCenterTop, recipiente), equalTo(false));
		assertThat(geometryBusiness.isRetanguloInterno(limiteRightTop, recipiente), equalTo(false));
		assertThat(geometryBusiness.isRetanguloInterno(limiteRigthCenter, recipiente), equalTo(false));
		assertThat(geometryBusiness.isRetanguloInterno(limiteRightBottom, recipiente), equalTo(false));
		assertThat(geometryBusiness.isRetanguloInterno(limiteCenterBottom, recipiente), equalTo(false));
		assertThat(geometryBusiness.isRetanguloInterno(limiteLeftBottom, recipiente), equalTo(false));
		assertThat(geometryBusiness.isRetanguloInterno(limiteLeftCenter, recipiente), equalTo(false));
	}

	/**
	 * Verifica os casos em que a região cobre parcialmente o recipiente,
	 * contendo região externa ao recipiente.
	 * 
	 * Valida também o caso em que a região está interna ao recipiente.
	 */
	@Test
	public void verificarRetanguloInternoUtilizandoAreasTangentesEInternas() {
		Rectangle2D recipiente = new Rectangle2D(10, 12, 18, 20);

		Rectangle2D inTotal = new Rectangle2D(12, 14, 16, 18);
		Rectangle2D inParcial = new Rectangle2D(8, 8, 20, 22);
		Rectangle2D inExato = new Rectangle2D(10, 12, 18, 20);
		Rectangle2D inRightBottom = new Rectangle2D(5, 16, 14, 40);
		Rectangle2D inLeftBottom = new Rectangle2D(14, 16, 30, 40);
		Rectangle2D inRightTop = new Rectangle2D(5, 8, 14, 16);
		Rectangle2D inLeftTop = new Rectangle2D(14, 8, 30, 16);
		Rectangle2D inLeftLineTotal = new Rectangle2D(14, 8, 30, 30);
		Rectangle2D inRightLineTotal = new Rectangle2D(5, 8, 14, 30);
		Rectangle2D inTopLineTotal = new Rectangle2D(5, 8, 30, 16);
		Rectangle2D inBottomLineTotal = new Rectangle2D(5, 16, 22, 30);
		Rectangle2D inLeftLineParcial = new Rectangle2D(14, 14, 30, 16);
		Rectangle2D inRightLineParcial = new Rectangle2D(5, 14, 14, 16);
		Rectangle2D inTopLineParcial = new Rectangle2D(15, 8, 17, 16);
		Rectangle2D inBottomLineParcial = new Rectangle2D(15, 16, 17, 30);

		assertThat(geometryBusiness.isRetanguloInterno(inParcial, recipiente), equalTo(false));
		assertThat(geometryBusiness.isRetanguloInterno(inRightBottom, recipiente), equalTo(false));
		assertThat(geometryBusiness.isRetanguloInterno(inLeftBottom, recipiente), equalTo(false));
		assertThat(geometryBusiness.isRetanguloInterno(inRightTop, recipiente), equalTo(false));
		assertThat(geometryBusiness.isRetanguloInterno(inLeftTop, recipiente), equalTo(false));
		assertThat(geometryBusiness.isRetanguloInterno(inLeftLineTotal, recipiente), equalTo(false));
		assertThat(geometryBusiness.isRetanguloInterno(inRightLineTotal, recipiente), equalTo(false));
		assertThat(geometryBusiness.isRetanguloInterno(inTopLineTotal, recipiente), equalTo(false));
		assertThat(geometryBusiness.isRetanguloInterno(inBottomLineTotal, recipiente), equalTo(false));
		assertThat(geometryBusiness.isRetanguloInterno(inLeftLineParcial, recipiente), equalTo(false));
		assertThat(geometryBusiness.isRetanguloInterno(inRightLineParcial, recipiente), equalTo(false));
		assertThat(geometryBusiness.isRetanguloInterno(inTopLineParcial, recipiente), equalTo(false));
		assertThat(geometryBusiness.isRetanguloInterno(inBottomLineParcial, recipiente), equalTo(false));

		assertThat(geometryBusiness.isRetanguloInterno(inTotal, recipiente), equalTo(true));
		assertThat(geometryBusiness.isRetanguloInterno(inExato, recipiente), equalTo(true));
	}

	/**
	 * Verifica os casos em que a região está completamente externa ao
	 * recipiente.
	 */
	@Test
	public void verificarRetanguloInternoUtilizandoRegioesSemIntersecaoNemAreasInternas() {
		Rectangle2D recipiente = new Rectangle2D(10, 12, 18, 20);

		Rectangle2D leftCenter = new Rectangle2D(2, 14, 8, 18);
		Rectangle2D topCenter = new Rectangle2D(12, 22, 16, 30);
		Rectangle2D rightCenter = new Rectangle2D(20, 14, 28, 18);
		Rectangle2D bottomCenter = new Rectangle2D(12, 4, 16, 8);

		assertThat(geometryBusiness.isRetanguloInterno(leftCenter, recipiente), equalTo(false));
		assertThat(geometryBusiness.isRetanguloInterno(topCenter, recipiente), equalTo(false));
		assertThat(geometryBusiness.isRetanguloInterno(rightCenter, recipiente), equalTo(false));
		assertThat(geometryBusiness.isRetanguloInterno(bottomCenter, recipiente), equalTo(false));
	}

	/**
	 * Verifica a inversão de retângulos.
	 */
	@Test
	public void verificaInversaoDeRetangulo() {
		assertThat(geometryBusiness.inverterRetangulo(new Rectangle2D(100, 200)), equalTo(new Rectangle2D(200, 100)));
		assertThat(geometryBusiness.inverterRetangulo(new Rectangle2D(17, 23, 18, 30)),
				equalTo(new Rectangle2D(17, 23, 24, 24)));
		assertThat(geometryBusiness.inverterRetangulo(new Rectangle2D(-1, -7, 5, 10)),
				equalTo(new Rectangle2D(-1, -7, 16, -1)));
		assertThat(geometryBusiness.inverterRetangulo(new Rectangle2D(-5, -7, -2, -2)),
				equalTo(new Rectangle2D(-5, -7, 0, -4)));
	}

	/**
	 * Verifica se o retângulo será expandido e reduzido corretamente.
	 */
	@Test
	public void redimensionarRetanguloCorretamente() {
		assertThat(geometryBusiness.redimensionar(new Rectangle2D(0, 0, 99, 199), 1),
				equalTo(new Rectangle2D(-1, -1, 100, 200)));
		assertThat(geometryBusiness.redimensionar(new Rectangle2D(0, 0, 99, 199), -1),
				equalTo(new Rectangle2D(1, 1, 98, 198)));
		assertThat(geometryBusiness.redimensionar(new Rectangle2D(0, 0, 99, 199), 0),
				equalTo(new Rectangle2D(0, 0, 99, 199)));
	}
}
