package com.thiagomv.spritecompiler;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import com.thiagomv.spritecompiler.model.Color;

public class ColorTest {
	private void assertColors(Color color, int a, int r, int g, int b) {
		Assert.assertThat(color.b, CoreMatchers.is(b));
		Assert.assertThat(color.g, CoreMatchers.is(g));
		Assert.assertThat(color.r, CoreMatchers.is(r));
		Assert.assertThat(color.a, CoreMatchers.is(a));
	}

	@Test
	public void numero0x00000000() {
		Color c1 = new Color();
		assertColors(c1, 0, 0, 0, 0);
		Assert.assertThat(c1.toInt(), CoreMatchers.is(0));

		Color c2 = new Color(0, 0, 0, 0);
		assertColors(c2, 0, 0, 0, 0);
		Assert.assertThat(c2.toInt(), CoreMatchers.is(0));

		Color c3 = new Color(0);
		assertColors(c3, 0, 0, 0, 0);
		Assert.assertThat(c3.toInt(), CoreMatchers.is(0));
	}

	@Test
	public void numero0xFFFFFFFF() {
		Color c4 = new Color(255, 255, 255, 255);
		assertColors(c4, 255, 255, 255, 255);
		Assert.assertThat(c4.toInt(), CoreMatchers.is(0xFFFFFFFF));

		Color c5 = new Color(0xFFFFFFFF);
		assertColors(c5, 255, 255, 255, 255);
		Assert.assertThat(c5.toInt(), CoreMatchers.is(0xFFFFFFFF));
	}

	@Test
	public void numero0xAABBCCDD() {
		Color c6 = new Color(0xAA, 0xBB, 0xCC, 0xDD);
		assertColors(c6, 0xAA, 0xBB, 0xCC, 0xDD);
		Assert.assertThat(c6.toInt(), CoreMatchers.is(0xAABBCCDD));

		Color c7 = new Color(0xAABBCCDD);
		assertColors(c7, 0xAA, 0xBB, 0xCC, 0xDD);
		Assert.assertThat(c7.toInt(), CoreMatchers.is(0xAABBCCDD));
	}
}
