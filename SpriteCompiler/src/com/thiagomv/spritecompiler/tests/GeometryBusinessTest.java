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
		Rectangle2D in = new Rectangle2D(12, 18, 16, 14);
		Rectangle2D out = new Rectangle2D(8, 22, 20, 8);
		Rectangle2D limiteIn = new Rectangle2D(10, 20, 18, 12);

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
		Assert.assertThat(geometryBusiness.intersect(regionFixo, in),
				CoreMatchers.is(true));
		Assert.assertThat(geometryBusiness.intersect(regionFixo, out),
				CoreMatchers.is(true));
		Assert.assertThat(geometryBusiness.intersect(regionFixo, limiteIn),
				CoreMatchers.is(true));
	}

	@Test
	public void regioesSemIntersecao() {

	}
}
