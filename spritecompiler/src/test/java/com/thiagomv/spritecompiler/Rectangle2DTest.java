package com.thiagomv.spritecompiler;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import com.thiagomv.spritecompiler.model.Rectangle2D;

public class Rectangle2DTest {
	@Test
	public void clones() {
		Rectangle2D region1 = new Rectangle2D(10, 12, 20, 22);
		Rectangle2D region2 = new Rectangle2D(region1);

		Assert.assertThat(region1 != region2, CoreMatchers.is(Boolean.TRUE));
		Assert.assertThat(region1.equals(region2), CoreMatchers.is(Boolean.TRUE));
		Assert.assertThat(region1.getClass().equals(region2.getClass()), CoreMatchers.is(Boolean.TRUE));
		Assert.assertThat(region1.hashCode(), CoreMatchers.is(region2.hashCode()));
	}
}
