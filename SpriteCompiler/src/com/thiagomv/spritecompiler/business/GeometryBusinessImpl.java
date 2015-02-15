package com.thiagomv.spritecompiler.business;

import java.util.List;

import com.thiagomv.spritecompiler.commons.bases.BaseBusinessImpl;
import com.thiagomv.spritecompiler.data.Point2D;
import com.thiagomv.spritecompiler.data.PontoAncora;
import com.thiagomv.spritecompiler.data.Rectangle2D;
import com.thiagomv.spritecompiler.data.WithArea;

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
	public int somarAreas(List<? extends WithArea> regions) {
		int area = 0;
		for (WithArea region : regions) {
			area += region.getArea();
		}
		return area;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isPontoInterno(Point2D ponto, Rectangle2D region) {
		return (pertenceAoIntervalo(region.getLeft(), region.getRight(),
				ponto.getX()) && pertenceAoIntervalo(region.getBottom(),
				region.getTop(), ponto.getY()));
	}

	/** {@inheritDoc} */
	@Override
	public boolean isPontoInterno(Point2D ponto, List<Rectangle2D> regioes) {
		for (Rectangle2D region : regioes) {
			if (isPontoInterno(ponto, region)) {
				return true;
			}
		}
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isPontoAncoraDeRetangulo(PontoAncora ancora,
			Rectangle2D region) {
		switch (ancora.getLocalRetangulo()) {
		case NORDESTE:
			return (ancora.getX() == region.getRight() && ancora.getY() == region
					.getTop());
		case NOROESTE:
			return (ancora.getX() == region.getLeft() && ancora.getY() == region
					.getTop());
		case SUDESTE:
			return (ancora.getX() == region.getRight() && ancora.getY() == region
					.getBottom());
		case SUDOESTE:
			return (ancora.getX() == region.getLeft() && ancora.getY() == region
					.getBottom());
		}
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isRetanguloInterno(Rectangle2D region, Rectangle2D recipiente) {
		int left = recipiente.getLeft();
		int right = recipiente.getRight();
		int top = recipiente.getTop();
		int bottom = recipiente.getBottom();

		return (pertenceAoIntervalo(left, right, region.getLeft())
				&& pertenceAoIntervalo(left, right, region.getRight())
				&& pertenceAoIntervalo(bottom, top, region.getBottom()) && pertenceAoIntervalo(
					bottom, top, region.getTop()));
	}

}
