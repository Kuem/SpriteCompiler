package com.thiagomv.spritecompiler.tests;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import com.thiagomv.spritecompiler.business.GeometryBusiness;
import com.thiagomv.spritecompiler.commons.factories.BusinessFactory;
import com.thiagomv.spritecompiler.data.Point2D;
import com.thiagomv.spritecompiler.data.PontoAncora;
import com.thiagomv.spritecompiler.data.Rectangle2D;
import com.thiagomv.spritecompiler.enums.IndicadorLocalRetangulo;

public class GeometryBusinessTest {
	private GeometryBusiness geometryBusiness = BusinessFactory
			.getBusinessInstance(GeometryBusiness.class);

	@Test
	public void regioesComIntersecao() {
		Rectangle2D regionFixo = new Rectangle2D(10, 12, 18, 20);
		Rectangle2D limiteLeftTop = new Rectangle2D(2, 20, 10, 22);
		Rectangle2D limiteCenterTop = new Rectangle2D(12, 20, 16, 22);
		Rectangle2D limiteRightTop = new Rectangle2D(18, 20, 20, 22);
		Rectangle2D limiteRigthCenter = new Rectangle2D(18, 14, 20, 18);
		Rectangle2D limiteRightBottom = new Rectangle2D(18, 10, 20, 12);
		Rectangle2D limiteCenterBottom = new Rectangle2D(12, 10, 16, 12);
		Rectangle2D limiteLeftBottom = new Rectangle2D(8, 2, 10, 12);
		Rectangle2D limiteLeftCenter = new Rectangle2D(2, 14, 10, 18);

		Assert.assertThat(
				geometryBusiness.temIntersecao(regionFixo, limiteLeftTop),
				CoreMatchers.is(true));
		Assert.assertThat(
				geometryBusiness.temIntersecao(regionFixo, limiteCenterTop),
				CoreMatchers.is(true));
		Assert.assertThat(
				geometryBusiness.temIntersecao(regionFixo, limiteRightTop),
				CoreMatchers.is(true));
		Assert.assertThat(
				geometryBusiness.temIntersecao(regionFixo, limiteRigthCenter),
				CoreMatchers.is(true));
		Assert.assertThat(
				geometryBusiness.temIntersecao(regionFixo, limiteRightBottom),
				CoreMatchers.is(true));
		Assert.assertThat(
				geometryBusiness.temIntersecao(regionFixo, limiteCenterBottom),
				CoreMatchers.is(true));
		Assert.assertThat(
				geometryBusiness.temIntersecao(regionFixo, limiteLeftBottom),
				CoreMatchers.is(true));
		Assert.assertThat(
				geometryBusiness.temIntersecao(regionFixo, limiteLeftCenter),
				CoreMatchers.is(true));
	}

	@Test
	public void regioesComAreasInternas() {
		Rectangle2D regionFixo = new Rectangle2D(10, 12, 18, 20);
		Rectangle2D inTotal = new Rectangle2D(12, 14, 16, 18);
		Rectangle2D inParcial = new Rectangle2D(8, 8, 20, 22);
		Rectangle2D inExato = new Rectangle2D(10, 12, 18, 20);
		Rectangle2D inRightBottom = new Rectangle2D(5, 16, 14, 40);
		Rectangle2D inLeftBottom = new Rectangle2D(14, 16, 30, 40);
		Rectangle2D inRightTop = new Rectangle2D(5, 8, 14, 16);
		Rectangle2D inLeftTop = new Rectangle2D(14, 8, 30, 16);
		Rectangle2D inLeftLine = new Rectangle2D(14, 8, 30, 30);
		Rectangle2D inRightLine = new Rectangle2D(5, 8, 14, 30);
		Rectangle2D inTopLine = new Rectangle2D(5, 8, 30, 16);
		Rectangle2D inBottomLine = new Rectangle2D(5, 16, 22, 30);

		Assert.assertThat(geometryBusiness.temIntersecao(regionFixo, inTotal),
				CoreMatchers.is(true));
		Assert.assertThat(
				geometryBusiness.temIntersecao(regionFixo, inParcial),
				CoreMatchers.is(true));
		Assert.assertThat(geometryBusiness.temIntersecao(regionFixo, inExato),
				CoreMatchers.is(true));
		Assert.assertThat(
				geometryBusiness.temIntersecao(regionFixo, inRightBottom),
				CoreMatchers.is(true));
		Assert.assertThat(
				geometryBusiness.temIntersecao(regionFixo, inLeftBottom),
				CoreMatchers.is(true));
		Assert.assertThat(
				geometryBusiness.temIntersecao(regionFixo, inRightTop),
				CoreMatchers.is(true));
		Assert.assertThat(
				geometryBusiness.temIntersecao(regionFixo, inLeftTop),
				CoreMatchers.is(true));
		Assert.assertThat(
				geometryBusiness.temIntersecao(regionFixo, inLeftLine),
				CoreMatchers.is(true));
		Assert.assertThat(
				geometryBusiness.temIntersecao(regionFixo, inRightLine),
				CoreMatchers.is(true));
		Assert.assertThat(
				geometryBusiness.temIntersecao(regionFixo, inTopLine),
				CoreMatchers.is(true));
		Assert.assertThat(
				geometryBusiness.temIntersecao(regionFixo, inBottomLine),
				CoreMatchers.is(true));
	}

	@Test
	public void regioesSemIntersecaoNemAreasInternas() {
		Rectangle2D regionFixo = new Rectangle2D(10, 12, 18, 20);
		Rectangle2D leftCenter = new Rectangle2D(2, 14, 8, 18);
		Rectangle2D topCenter = new Rectangle2D(12, 22, 16, 30);
		Rectangle2D rightCenter = new Rectangle2D(20, 14, 28, 18);
		Rectangle2D bottomCenter = new Rectangle2D(12, 4, 16, 8);

		Assert.assertThat(
				geometryBusiness.temIntersecao(regionFixo, leftCenter),
				CoreMatchers.is(false));
		Assert.assertThat(
				geometryBusiness.temIntersecao(regionFixo, topCenter),
				CoreMatchers.is(false));
		Assert.assertThat(
				geometryBusiness.temIntersecao(regionFixo, rightCenter),
				CoreMatchers.is(false));
		Assert.assertThat(
				geometryBusiness.temIntersecao(regionFixo, bottomCenter),
				CoreMatchers.is(false));
	}

	@Test
	public void intervalos() {
		Assert.assertThat(geometryBusiness.pertenceAoIntervalo(-2, 8, -1),
				CoreMatchers.is(true));
		Assert.assertThat(geometryBusiness.pertenceAoIntervalo(-2, 8, 0),
				CoreMatchers.is(true));
		Assert.assertThat(geometryBusiness.pertenceAoIntervalo(-2, 8, 1),
				CoreMatchers.is(true));
		Assert.assertThat(geometryBusiness.pertenceAoIntervalo(2, 8, -1),
				CoreMatchers.is(false));
		Assert.assertThat(geometryBusiness.pertenceAoIntervalo(2, 8, 0),
				CoreMatchers.is(false));
		Assert.assertThat(geometryBusiness.pertenceAoIntervalo(2, 8, 1),
				CoreMatchers.is(false));
		Assert.assertThat(geometryBusiness.pertenceAoIntervalo(-8, -2, -1),
				CoreMatchers.is(false));
		Assert.assertThat(geometryBusiness.pertenceAoIntervalo(-8, -2, 0),
				CoreMatchers.is(false));
		Assert.assertThat(geometryBusiness.pertenceAoIntervalo(-8, -2, 1),
				CoreMatchers.is(false));
	}

	@Test
	public void somarAreasComSucesso() {
		// area = 11 * 3 = 33
		Rectangle2D region1 = new Rectangle2D(0, 10, 10, 12);
		// area = 2 * 20 = 40
		Rectangle2D region2 = new Rectangle2D(-2, -5, -1, 14);
		// area = 8 * 4 = 32
		Rectangle2D region3 = new Rectangle2D(-7, -5, 0, -2);
		int area = geometryBusiness.somarAreas(Arrays.asList(region1, region2,
				region3));
		Assert.assertThat(area, CoreMatchers.equalTo(105));
	}

	@Test
	public void pontoInternoEExternoAoRetangulo() {
		Rectangle2D region = new Rectangle2D(10, 2, 18, 43);

		Map<Point2D, Boolean> mapa = new HashMap<Point2D, Boolean>();
		mapa.put(new Point2D(0, 0), false);
		mapa.put(new Point2D(10, 2), true);
		mapa.put(new Point2D(10, 43), true);
		mapa.put(new Point2D(18, 43), true);
		mapa.put(new Point2D(18, 2), true);
		mapa.put(new Point2D(2, 10), false);
		mapa.put(new Point2D(2, 18), false);
		mapa.put(new Point2D(2, 43), false);
		mapa.put(new Point2D(43, 2), false);
		mapa.put(new Point2D(43, 18), false);
		mapa.put(new Point2D(43, 43), false);
		mapa.put(new Point2D(19, 2), false);

		for (Point2D key : mapa.keySet()) {
			Assert.assertThat(geometryBusiness.isPontoInterno(key, region),
					CoreMatchers.is(mapa.get(key)));
		}
	}

	@Test
	public void pontoInternoEExternoAoMuitosRetangulos() {
		List<Rectangle2D> regions = Arrays.asList(
				new Rectangle2D(10, 2, 18, 43), new Rectangle2D(2, 10, 9, 18));

		Map<Point2D, Boolean> mapa = new HashMap<Point2D, Boolean>();
		mapa.put(new Point2D(0, 0), false);
		mapa.put(new Point2D(10, 2), true);
		mapa.put(new Point2D(10, 43), true);
		mapa.put(new Point2D(18, 43), true);
		mapa.put(new Point2D(18, 2), true);
		mapa.put(new Point2D(2, 10), true);
		mapa.put(new Point2D(2, 18), true);
		mapa.put(new Point2D(2, 43), false);
		mapa.put(new Point2D(43, 2), false);
		mapa.put(new Point2D(43, 18), false);
		mapa.put(new Point2D(43, 43), false);
		mapa.put(new Point2D(19, 2), false);

		for (Point2D key : mapa.keySet()) {
			Assert.assertThat(geometryBusiness.isPontoInterno(key, regions),
					CoreMatchers.is(mapa.get(key)));
		}
	}

	@Test
	public void pontosAncorasDeRetangulo() {
		Rectangle2D region = new Rectangle2D(10, 2, 18, 43);

		Map<PontoAncora, Boolean> mapa = new HashMap<PontoAncora, Boolean>();
		mapa.put(new PontoAncora(0, 0, IndicadorLocalRetangulo.NORDESTE), false);
		mapa.put(new PontoAncora(10, 2, IndicadorLocalRetangulo.SUDOESTE), true);
		mapa.put(new PontoAncora(10, 43, IndicadorLocalRetangulo.NOROESTE),
				true);
		mapa.put(new PontoAncora(18, 43, IndicadorLocalRetangulo.NORDESTE),
				true);
		mapa.put(new PontoAncora(18, 2, IndicadorLocalRetangulo.SUDESTE), true);
		mapa.put(new PontoAncora(10, 2, IndicadorLocalRetangulo.SUDESTE), false);
		mapa.put(new PontoAncora(10, 43, IndicadorLocalRetangulo.NORDESTE),
				false);
		mapa.put(new PontoAncora(18, 43, IndicadorLocalRetangulo.NOROESTE),
				false);
		mapa.put(new PontoAncora(18, 2, IndicadorLocalRetangulo.SUDOESTE),
				false);
		mapa.put(new PontoAncora(2, 10, IndicadorLocalRetangulo.NORDESTE),
				false);
		mapa.put(new PontoAncora(2, 18, IndicadorLocalRetangulo.NORDESTE),
				false);
		mapa.put(new PontoAncora(2, 43, IndicadorLocalRetangulo.NORDESTE),
				false);
		mapa.put(new PontoAncora(43, 2, IndicadorLocalRetangulo.NORDESTE),
				false);
		mapa.put(new PontoAncora(43, 18, IndicadorLocalRetangulo.NORDESTE),
				false);
		mapa.put(new PontoAncora(43, 43, IndicadorLocalRetangulo.NORDESTE),
				false);
		mapa.put(new PontoAncora(19, 2, IndicadorLocalRetangulo.NORDESTE),
				false);

		for (PontoAncora key : mapa.keySet()) {
			Assert.assertThat(
					geometryBusiness.isPontoAncoraDeRetangulo(key, region),
					CoreMatchers.is(mapa.get(key)));
		}
	}
}
