package com.thiagomv.spritecompiler.model;

import java.io.Serializable;

/**
 * Define os pixels que limitam a região de um retângulo ortogonal ao plano
 * bidimensional. O plano bidimensional é definido de tal forma que o eixo X
 * cresce para a direita e o eixo Y cresce para cima. Desta forma, em um
 * retângulo convencional, a coordenada {@code right} deverá ser maior que a
 * {@code left}, e a coordenada {@code top} maior que a {@code bottom}.
 * 
 * @author Thiago Mendes Vieira - thiagomv.0301.developer@gmail.com
 */
public class Rectangle2D implements Serializable, WithArea {
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

	public Rectangle2D(Rectangle2D rectangle) {
		definirLimites(rectangle.getLeft(), rectangle.getBottom(), rectangle.getRight(), rectangle.getTop());
	}

	/**
	 * Cria um retângulo, definindo seus limites laterais.
	 * 
	 * @param left
	 *            Limite esquerdo.
	 * @param bottom
	 *            Limite inferior.
	 * @param right
	 *            Limite direito.
	 * @param top
	 *            Limite superior.
	 */
	public Rectangle2D(int left, int bottom, int right, int top) {
		definirLimites(left, bottom, right, top);
	}

	/**
	 * Cria um retângulo com o ponto inferior esquerdo localizado na origem do
	 * plano bidimensional e com limite superior direito apropriado para a
	 * largura e altura definidas.
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
	 * Cria um retângulo com determinadas dimensões que se encaixe exatamente no
	 * vértice passado por parâmetro.
	 * 
	 * @param size
	 *            Tamanho do retângulo.
	 * @param vertice
	 *            Vértice.
	 */
	public Rectangle2D(Size size, VerticeRetangulo2D vertice) {
		int limiteLeft = 0;
		int limiteRight = 0;
		int limiteTop = 0;
		int limiteBottom = 0;
		switch (vertice.getLocalRetangulo()) {
		case NORDESTE:
			limiteRight = vertice.getX();
			limiteTop = vertice.getY();
			limiteLeft = vertice.getX() - size.getWidth() + 1;
			limiteBottom = vertice.getY() - size.getHeight() + 1;
			break;
		case NOROESTE:
			limiteLeft = vertice.getX();
			limiteTop = vertice.getY();
			limiteRight = vertice.getX() + size.getWidth() - 1;
			limiteBottom = vertice.getY() - size.getHeight() + 1;
			break;
		case SUDESTE:
			limiteRight = vertice.getX();
			limiteBottom = vertice.getY();
			limiteLeft = vertice.getX() - size.getWidth() + 1;
			limiteTop = vertice.getY() + size.getHeight() - 1;
			break;
		case SUDOESTE:
			limiteLeft = vertice.getX();
			limiteBottom = vertice.getY();
			limiteRight = vertice.getX() + size.getWidth() - 1;
			limiteTop = vertice.getY() + size.getHeight() - 1;
			break;
		}
		definirLimites(limiteLeft, limiteBottom, limiteRight, limiteTop);
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
	 * Obtém a altura do retângulo, em pixels. Note que a quantidade de pixels
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
}
