package com.thiagomv.spritecompiler.business;

import java.util.List;

import com.thiagomv.spritecompiler.commons.BaseBusinessImpl;
import com.thiagomv.spritecompiler.data.Rectangle2D;

public class GeometryBusinessImpl extends BaseBusinessImpl implements
		GeometryBusiness {

	/** {@inheritDoc} */
	@Override
	public boolean temIntersecao(Rectangle2D region1, Rectangle2D region2) {
		return (region2.getLeft() <= region1.getRight()
				&& region2.getRight() >= region1.getLeft()
				&& region2.getBottom() <= region1.getTop() && region2.getTop() >= region1
				.getBottom());
	}

	/** {@inheritDoc} */
	@Override
	public boolean pertenceAoIntervalo(int left, int right, int v) {
		return (v >= left && v <= right);
	}

	/** {@inheritDoc} */
	@Override
	public int somarAreas(List<Rectangle2D> regions) {
		int area = 0;
		for (Rectangle2D region : regions) {
			area += region.getArea();
		}
		return area;
	}

}
