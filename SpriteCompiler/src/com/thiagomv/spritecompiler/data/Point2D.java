package com.thiagomv.spritecompiler.data;

/**
 * Define as coordenadas de um ponto no plano bidimensional.
 * 
 * @author Thiago Mendes Vieira
 * 
 *         12/02/2015
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

}
