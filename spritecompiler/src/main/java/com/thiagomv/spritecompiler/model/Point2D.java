package com.thiagomv.spritecompiler.model;

/**
 * Define as coordenadas de um ponto no plano bidimensional.
 * 
 * @author Thiago Mendes Vieira - thiagomv.0301.developer@gmail.com
 */
public class Point2D {
	private int x;
	private int y;

	/**
	 * Cria um ponto na origem do plano bidimensional.
	 */
	public Point2D() {
		x = y = 0;
	}

	/**
	 * Cria um ponto no plano bidimensional.
	 * 
	 * @param x
	 *            Valor de x.
	 * @param y
	 *            Valor de y.
	 */
	public Point2D(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Obtém o valor de x.
	 * 
	 * @return Valor de x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Estabelece o valor de x.
	 * 
	 * @param x
	 *            Valor de x.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Obtém o valor de y.
	 * 
	 * @return Valor de y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Estabelece o valor de y.
	 * 
	 * @param y
	 *            Valor de y.
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Point2D)) {
			return false;
		}
		Point2D other = (Point2D) obj;
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Point2D [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append("]");
		return builder.toString();
	}

}
