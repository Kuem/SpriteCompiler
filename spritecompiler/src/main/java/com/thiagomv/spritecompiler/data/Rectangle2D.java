package com.thiagomv.spritecompiler.data;

import java.io.Serializable;

/**
 * Define os pixels que limitam a regi�o de um ret�ngulo ortogonal ao plano
 * bidimensional. O plano bidimensional � definido de tal forma que o eixo X
 * cresce para a direita e o eixo Y cresce para cima. Desta forma, em um
 * ret�ngulo convencional, a coordenada {@code right} dever� ser maior que a
 * {@code left}, e a coordenada {@code top} maior que a {@code bottom}.
 * 
 * @author Thiago Mendes Vieira
 * 
 *         05/02/2015
 */
public class Rectangle2D implements Serializable, WithArea, Cloneable {
	private static final long serialVersionUID = 1L;

	private int left;
	private int top;
	private int right;
	private int bottom;

	/**
	 * Cria um ret�ngulo sem �rea na origem do plano bidimensional.
	 */
	public Rectangle2D() {
		left = top = right = bottom = 0;
	}

	/**
	 * Cria um ret�ngulo.
	 * 
	 * @param left
	 *            Pixel limitador esquerdo.
	 * @param bottom
	 *            Pixel limitador inferior.
	 * @param right
	 *            Pixel limitador direito.
	 * @param top
	 *            Pixel limitador superior.
	 */
	public Rectangle2D(int left, int bottom, int right, int top) {
		definirLimites(left, bottom, right, top);
	}

	/**
	 * Cria um ret�ngulo com o ponto left-bottom localizado na origem do plano
	 * bidimensional e com pixels limitadores right e top apropriados para um
	 * ret�ngulo com largura e altura definidos.
	 * 
	 * @param width
	 *            Largura do ret�ngulo.
	 * @param height
	 *            Altura do ret�ngulo.
	 */
	public Rectangle2D(int width, int height) {
		definirLimites(0, 0, width - 1, height - 1);
	}

	/**
	 * Cria um ret�ngulo com determinado tamanho e que se encaixe exatamento no
	 * ponto �ncora passado por par�metro.
	 * 
	 * @param size
	 *            Tamanho do ret�ngulo.
	 * @param ancora
	 *            Ponto �ncora.
	 */
	public Rectangle2D(Size size, PontoAncora ancora) {
		int left = 0;
		int right = 0;
		int top = 0;
		int bottom = 0;
		switch (ancora.getLocalRetangulo()) {
		case NORDESTE:
			right = ancora.getX();
			top = ancora.getY();
			left = ancora.getX() - size.getWidth() + 1;
			bottom = ancora.getY() - size.getHeight() + 1;
			break;
		case NOROESTE:
			left = ancora.getX();
			top = ancora.getY();
			right = ancora.getX() + size.getWidth() - 1;
			bottom = ancora.getY() - size.getHeight() + 1;
			break;
		case SUDESTE:
			right = ancora.getX();
			bottom = ancora.getY();
			left = ancora.getX() - size.getWidth() + 1;
			top = ancora.getY() + size.getHeight() - 1;
			break;
		case SUDOESTE:
			left = ancora.getX();
			bottom = ancora.getY();
			right = ancora.getX() + size.getWidth() - 1;
			top = ancora.getY() + size.getHeight() - 1;
			break;
		}
		definirLimites(left, bottom, right, top);
	}

	/**
	 * Define os limites do ret�ngulo.
	 * 
	 * @param left
	 *            Pixel limitador esquerdo.
	 * @param bottom
	 *            Pixel limitador inferior.
	 * @param right
	 *            Pixel limitador direito.
	 * @param top
	 *            Pixel limitador superior.
	 */
	private void definirLimites(int left, int bottom, int right, int top) {
		this.left = left;
		this.bottom = bottom;
		this.right = right;
		this.top = top;
	}

	/**
	 * Obt�m o pixel limitador esquerdo.
	 * 
	 * @return Pixel esquerdo.
	 */
	public int getLeft() {
		return left;
	}

	/**
	 * Obt�m o pixel limitador superior.
	 * 
	 * @return Pixel superior.
	 */
	public int getTop() {
		return top;
	}

	/**
	 * Obt�m o pixel limitador direito.
	 * 
	 * @return Pixel direito.
	 */
	public int getRight() {
		return right;
	}

	/**
	 * Obt�m o pixel limitador inferior.
	 * 
	 * @return Pixel inferior.
	 */
	public int getBottom() {
		return bottom;
	}

	/**
	 * Obt�m a largura do ret�ngulo, em pixels. Note que a quantidade de pixels
	 * ao longo da largura do ret�ngulo � dada pela f�rmula
	 * {@code right - left + 1}, pois as medidas dos extremos limitam os pixels
	 * inclu�dos no ret�ngulo.
	 * 
	 * @return Largura.
	 */
	public int getWidth() {
		return this.right - this.left + 1;
	}

	/**
	 * Obt�m a altura do ret�ngulo, em pixels. Note que a quantidade de pixels
	 * ao longo da altura do ret�ngulo � dada pela f�rmula
	 * {@code top - bottom + 1}, pois as medidas dos extremos limitam os pixels
	 * inclu�dos no ret�ngulo.
	 * 
	 * @return Altura.
	 */
	public int getHeight() {
		return this.top - this.bottom + 1;
	}

	/** {@inheritDoc} */
	@Override
	public int getArea() {
		return getWidth() * getHeight();
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bottom;
		result = prime * result + left;
		result = prime * result + right;
		result = prime * result + top;
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Rectangle2D))
			return false;
		Rectangle2D other = (Rectangle2D) obj;
		if (bottom != other.bottom)
			return false;
		if (left != other.left)
			return false;
		if (right != other.right)
			return false;
		if (top != other.top)
			return false;
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
