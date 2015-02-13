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
public class Rectangle2D implements Serializable {
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
	 * Define o pixel limitador esquerdo.
	 * 
	 * @param left
	 *            Pixel esquerdo.
	 */
	public void setLeft(int left) {
		this.left = left;
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
	 * Define o pixel limitador superior.
	 * 
	 * @param left
	 *            Pixel superior.
	 */
	public void setTop(int top) {
		this.top = top;
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
	 * Define o pixel limitador direito.
	 * 
	 * @param left
	 *            Pixel direito.
	 */
	public void setRight(int right) {
		this.right = right;
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
	 * Define o pixel limitador inferior.
	 * 
	 * @param left
	 *            Pixel inferior.
	 */
	public void setBottom(int bottom) {
		this.bottom = bottom;
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

	/**
	 * Obtém a área do retângulo.
	 * 
	 * @return Área.
	 */
	public int getArea() {
		return getWidth() * getHeight();
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Rectangle2D) {
			Rectangle2D rect = (Rectangle2D) obj;
			return (rect.left == this.left && rect.top == this.top
					&& rect.right == this.right && rect.bottom == this.bottom);
		}
		return false;
	}
}
