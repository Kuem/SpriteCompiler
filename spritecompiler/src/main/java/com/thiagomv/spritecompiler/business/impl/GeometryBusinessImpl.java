package com.thiagomv.spritecompiler.business.impl;

import java.util.List;

import com.kuemsoftwares.util.commons.base.BaseBusinessImpl;
import com.thiagomv.spritecompiler.business.GeometryBusiness;
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
public class GeometryBusinessImpl extends BaseBusinessImpl implements GeometryBusiness {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean temIntersecao(Rectangle2D region1, Rectangle2D region2) {
		return region2.getLeft() <= region1.getRight() && region1.getLeft() <= region2.getRight()
				&& region2.getBottom() <= region1.getTop() && region1.getBottom() <= region2.getTop();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean pertenceAoIntervalo(int left, int right, int v) {
		return v >= left && v <= right;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int somarAreas(List<? extends WithArea> regions) {
		int area = 0;
		for (WithArea region : regions) {
			area += region.getArea();
		}
		return area;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isPontoInterno(Point2D ponto, Rectangle2D region) {
		return pertenceAoIntervalo(region.getLeft(), region.getRight(), ponto.getX())
				&& pertenceAoIntervalo(region.getBottom(), region.getTop(), ponto.getY());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isPontoInterno(Point2D ponto, List<Rectangle2D> regioes) {
		for (Rectangle2D region : regioes) {
			if (isPontoInterno(ponto, region)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isVerticeDeRetangulo(VerticeRetangulo2D ancora, Rectangle2D region) {
		switch (ancora.getLocalRetangulo()) {
		case NORDESTE:
			return ancora.getX() == region.getRight() && ancora.getY() == region.getTop();
		case NOROESTE:
			return ancora.getX() == region.getLeft() && ancora.getY() == region.getTop();
		case SUDESTE:
			return ancora.getX() == region.getRight() && ancora.getY() == region.getBottom();
		case SUDOESTE:
			return ancora.getX() == region.getLeft() && ancora.getY() == region.getBottom();
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isRetanguloInterno(Rectangle2D region, Rectangle2D recipiente) {
		int left = recipiente.getLeft();
		int right = recipiente.getRight();
		int top = recipiente.getTop();
		int bottom = recipiente.getBottom();

		return pertenceAoIntervalo(left, right, region.getLeft()) && pertenceAoIntervalo(left, right, region.getRight())
				&& pertenceAoIntervalo(bottom, top, region.getBottom())
				&& pertenceAoIntervalo(bottom, top, region.getTop());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Rectangle2D inverterRetangulo(Rectangle2D retangulo) {
		int left = retangulo.getLeft();
		int bottom = retangulo.getBottom();
		int right = left + retangulo.getHeight() - 1;
		int top = bottom + retangulo.getWidth() - 1;
		return new Rectangle2D(left, bottom, right, top);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Rectangle2D redimensionar(Rectangle2D region, int lenght) {
		return new Rectangle2D(region.getLeft() - lenght, region.getBottom() - lenght, region.getRight() + lenght,
				region.getTop() + lenght);
	}

}
