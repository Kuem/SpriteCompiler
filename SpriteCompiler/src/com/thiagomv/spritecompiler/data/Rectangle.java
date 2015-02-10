package com.thiagomv.spritecompiler.data;

import java.io.Serializable;

/**
 * Define os pixels que limitam a regi�o de um ret�ngulo no plano bidimensional.
 * O plano bidimensional � definido de tal forma que o eixo X cresce para a
 * direita e o eixo Y cresce para baixo. Desta forma, em um ret�ngulo
 * convencional, a coordenada {@code right} dever� ser maior que a {@code left},
 * e a coordenada {@code bottom} maior que a {@code top}.
 * 
 * @author Thiago Mendes Vieira
 * 
 *         05/02/2015
 */
public class Rectangle implements Serializable {
	private static final long serialVersionUID = 1L;

	private int left;
	private int top;
	private int right;
	private int bottom;

	/**
	 * Cria um ret�ngulo sem �rea na origem do plano bidimensional.
	 */
	public Rectangle() {
		left = top = right = bottom = 0;
	}

	/**
	 * Cria um ret�ngulo.
	 * 
	 * @param left
	 *            Pixel limitador esquerdo.
	 * @param top
	 *            Pixel limitador superior.
	 * @param right
	 *            Pixel limitador direito.
	 * @param bottom
	 *            Pixel limitador inferior.
	 */
	public Rectangle(int left, int top, int right, int bottom) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
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
	 * Define o pixel limitador esquerdo.
	 * 
	 * @param left
	 *            Pixel esquerdo.
	 */
	public void setLeft(int left) {
		this.left = left;
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
	 * Define o pixel limitador superior.
	 * 
	 * @param left
	 *            Pixel superior.
	 */
	public void setTop(int top) {
		this.top = top;
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
	 * Define o pixel limitador direito.
	 * 
	 * @param left
	 *            Pixel direito.
	 */
	public void setRight(int right) {
		this.right = right;
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
	 * Define o pixel limitador inferior.
	 * 
	 * @param left
	 *            Pixel inferior.
	 */
	public void setBottom(int bottom) {
		this.bottom = bottom;
	}

	/**
	 * Obt�m a largura do ret�ngulo.
	 * 
	 * @return Largura.
	 */
	public int getWidth() {
		return this.right - this.left + 1;
	}

	/**
	 * Obt�m a altura do ret�ngulo.
	 * 
	 * @return Altura.
	 */
	public int getHeight() {
		return this.bottom - this.top + 1;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle rect = (Rectangle) obj;
			return (rect.left == this.left && rect.top == this.top
					&& rect.right == this.right && rect.bottom == this.bottom);
		}
		return false;
	}
}
