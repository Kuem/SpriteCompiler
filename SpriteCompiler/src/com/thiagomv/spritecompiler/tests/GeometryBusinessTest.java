package com.thiagomv.spritecompiler.tests;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import com.thiagomv.spritecompiler.business.GeometryBusiness;
import com.thiagomv.spritecompiler.commons.BusinessFactory;
import com.thiagomv.spritecompiler.data.Rectangle2D;

public class GeometryBusinessTest {
	private GeometryBusiness geometryBusiness = BusinessFactory
			.getBusinessInstance(GeometryBusiness.class);

	@Test
	public void regioesComIntersecao() {
		Rectangle2D regionFixo = new Rectangle2D(10, 20, 18, 12);
		Rectangle2D limiteLeftTop = new Rectangle2D(2, 22, 10, 20);
		Rectangle2D limiteCenterTop = new Rectangle2D(12, 22, 16, 20);
		Rectangle2D limiteRightTop = new Rectangle2D(18, 22, 20, 20);
		Rectangle2D limiteRigthCenter = new Rectangle2D(18, 18, 20, 14);
		Rectangle2D limiteRightBottom = new Rectangle2D(18, 12, 20, 10);
		Rectangle2D limiteCenterBottom = new Rectangle2D(12, 12, 16, 10);
		Rectangle2D limiteLeftBottom = new Rectangle2D(8, 12, 10, 2);
		Rectangle2D limiteLeftCenter = new Rectangle2D(2, 18, 10, 14);

		Assert.assertThat(
				geometryBusiness.intersect(regionFixo, limiteLeftTop),
				CoreMatchers.is(true));
		Assert.assertThat(
				geometryBusiness.intersect(regionFixo, limiteCenterTop),
				CoreMatchers.is(true));
		Assert.assertThat(
				geometryBusiness.intersect(regionFixo, limiteRightTop),
				CoreMatchers.is(true));
		Assert.assertThat(
				geometryBusiness.intersect(regionFixo, limiteRigthCenter),
				CoreMatchers.is(true));
		Assert.assertThat(
				geometryBusiness.intersect(regionFixo, limiteRightBottom),
				CoreMatchers.is(true));
		Assert.assertThat(
				geometryBusiness.intersect(regionFixo, limiteCenterBottom),
				CoreMatchers.is(true));
		Assert.assertThat(
				geometryBusiness.intersect(regionFixo, limiteLeftBottom),
				CoreMatchers.is(true));
		Assert.assertThat(
				geometryBusiness.intersect(regionFixo, limiteLeftCenter),
				CoreMatchers.is(true));
	}

	@Test
	public void regioesComAreasInternas() {
		Rectangle2D regionFixo = new Rectangle2D(10, 20, 18, 12);
		Rectangle2D inTotal = new Rectangle2D(12, 18, 16, 14);
		Rectangle2D inParcial = new Rectangle2D(8, 22, 20, 8);
		Rectangle2D inExato = new Rectangle2D(10, 20, 18, 12);
		Rectangle2D inRightBottom = new Rectangle2D(5, 40, 14, 16);
		Rectangle2D inLeftBottom = new Rectangle2D(14, 40, 30, 16);
		Rectangle2D inRightTop = new Rectangle2D(5, 16, 14, 8);
		Rectangle2D inLeftTop = new Rectangle2D(14, 16, 30, 8);
		Rectangle2D inLeftLine = new Rectangle2D(14, 30, 30, 8);
		Rectangle2D inRightLine = new Rectangle2D(5, 30, 14, 8);
		Rectangle2D inTopLine = new Rectangle2D(5, 16, 30, 8);
		Rectangle2D inBottomLine = new Rectangle2D(5, 30, 22, 16);

		Assert.assertThat(geometryBusiness.intersect(regionFixo, inTotal),
				CoreMatchers.is(true));
		Assert.assertThat(geometryBusiness.intersect(regionFixo, inParcial),
				CoreMatchers.is(true));
		Assert.assertThat(geometryBusiness.intersect(regionFixo, inExato),
				CoreMatchers.is(true));
		Assert.assertThat(
				geometryBusiness.intersect(regionFixo, inRightBottom),
				CoreMatchers.is(true));
		Assert.assertThat(geometryBusiness.intersect(regionFixo, inLeftBottom),
				CoreMatchers.is(true));
		Assert.assertThat(geometryBusiness.intersect(regionFixo, inRightTop),
				CoreMatchers.is(true));
		Assert.assertThat(geometryBusiness.intersect(regionFixo, inLeftTop),
				CoreMatchers.is(true));
		Assert.assertThat(geometryBusiness.intersect(regionFixo, inLeftLine),
				CoreMatchers.is(true));
		Assert.assertThat(geometryBusiness.intersect(regionFixo, inRightLine),
				CoreMatchers.is(true));
		Assert.assertThat(geometryBusiness.intersect(regionFixo, inTopLine),
				CoreMatchers.is(true));
		Assert.assertThat(geometryBusiness.intersect(regionFixo, inBottomLine),
				CoreMatchers.is(true));
	}

	@Test
	public void regioesSemIntersecaoNemAreasInternas() {
		Rectangle2D regionFixo = new Rectangle2D(10, 20, 18, 12);
		Rectangle2D leftCenter = new Rectangle2D(2, 18, 8, 14);
		Rectangle2D topCenter = new Rectangle2D(12, 30, 16, 22);
		Rectangle2D rightCenter = new Rectangle2D(20, 18, 28, 14);
		Rectangle2D bottomCenter = new Rectangle2D(12, 8, 16, 4);

		Assert.assertThat(geometryBusiness.intersect(regionFixo, leftCenter),
				CoreMatchers.is(false));
		Assert.assertThat(geometryBusiness.intersect(regionFixo, topCenter),
				CoreMatchers.is(false));
		Assert.assertThat(geometryBusiness.intersect(regionFixo, rightCenter),
				CoreMatchers.is(false));
		Assert.assertThat(geometryBusiness.intersect(regionFixo, bottomCenter),
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
}
