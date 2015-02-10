package com.thiagomv.spritecompiler.data;

import java.io.Serializable;

/**
 * Define as dimens�es de uma �rea retangular.
 * 
 * @author Thiago Mendes Vieira
 * 
 *         05/02/2015
 */
public class Size implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int width;
	private int height;

	/**
	 * Cria uma �rea retangular com dimens�es nulas.
	 */
	public Size() {
		this(0, 0);
	}

	/**
	 * Cria uma �rea retangular com determinadas dimens�es.
	 * 
	 * @param width
	 *            Largura.
	 * @param height
	 *            Altura.
	 */
	public Size(int width, int height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * Obt�m a largura.
	 * 
	 * @return Largura.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Estabelece a largura.
	 * 
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Obt�m a altura.
	 * 
	 * @return Altura.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Estabelece a altura.
	 * 
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}
}
