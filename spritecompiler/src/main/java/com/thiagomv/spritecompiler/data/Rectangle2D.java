package com.thiagomv.spritecompiler.data;

import java.io.Serializable;

/**
 * Define os pixels que limitam a região de um retângulo ortogonal ao plano
 * bidimensional. O plano bidimensional é definido de tal forma que o eixo X
 * cresce para a direita e o eixo Y cresce para cima. Desta forma, em um
 * retângulo convencional, a coordenada {@code right} deverá ser maior que a
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
	 * Cria um retângulo sem área na origem do plano bidimensional.
	 */
	public Rectangle2D() {
		left = top = right = bottom = 0;
	}

	/**
	 * Cria um retângulo.
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
	 * Cria um retângulo com o ponto left-bottom localizado na origem do plano
	 * bidimensional e com pixels limitadores right e top apropriados para um
	 * retângulo com largura e altura definidos.
	 * 
	 * @param width
	 *            Largura do retângulo.
	 * @param height
	 *            Altura do retângulo.
	 */
	public Rectangle2D(int width, int height) {
		definirLimites(0, 0, width - 1, height - 1);
	}

	/**
	 * Cria um retângulo com determinado tamanho e que se encaixe exatamento no
	 * ponto âncora passado por parâmetro.
	 * 
	 * @param size
	 *            Tamanho do retângulo.
	 * @param ancora
	 *            Ponto âncora.
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
	 * Define os limites do retângulo.
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
	 * Obtém o pixel limitador esquerdo.
	 * 
	 * @return Pixel esquerdo.
	 */
	public int getLeft() {
		return left;
	}

	/**
	 * Obtém o pixel limitador superior.
	 * 
	 * @return Pixel superior.
	 */
	public int getTop() {
		return top;
	}

	/**
	 * Obtém o pixel limitador direito.
	 * 
	 * @return Pixel direito.
	 */
	public int getRight() {
		return right;
	}

	/**
	 * Obtém o pixel limitador inferior.
	 * 
	 * @return Pixel inferior.
	 */
	public int getBottom() {
		return bottom;
	}

	/**
	 * Obtém a largura do retângulo, em pixels. Note que a quantidade de pixels
	 * ao longo da largura do retângulo é dada pela fórmula
	 * {@code right - left + 1}, pois as medidas dos extremos limitam os pixels
	 * incluídos no retângulo.
	 * 
	 * @return Largura.
	 */
	public int getWidth() {
		return this.right - this.left + 1;
	}

	/**
	 * Obtém a altura do retêngulo, em pixels. Note que a quantidade de pixels
	 * ao longo da altura do retângulo é dada pela fórmula
	 * {@code top - bottom + 1}, pois as medidas dos extremos limitam os pixels
	 * incluídos no retângulo.
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
