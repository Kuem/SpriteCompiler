package com.thiagomv.spritecompiler.model;

import java.io.Serializable;

/**
 * Representação de cor no formato ARGB.
 * 
 * @author Thiago Mendes Vieira - thiagomv.0301.developer@gmail.com
 *
 */
public class Color implements Serializable {
	private static final long serialVersionUID = 1L;

	public int a, r, g, b;

	/**
	 * Cria uma representação da cor preta, que possui todos os componentes A,
	 * R, G e B com valor zero.
	 */
	public Color() {
		a = r = g = b = 0;
	}

	/**
	 * Cria uma representação de cor, informando cada um dos seus componentes.
	 * 
	 * @param a
	 *            componente alpha
	 * @param r
	 *            componente vermelho
	 * @param g
	 *            componente verde
	 * @param b
	 *            componente azul
	 */
	public Color(int a, int r, int g, int b) {
		this.a = a;
		this.r = r;
		this.g = g;
		this.b = b;
	}

	/**
	 * Cria uma representação de cor, informando seu valor haxadecimal.
	 * 
	 * @param argbColor
	 *            Representação hexadecimal da cor, no formato AARRGGBB.
	 */
	public Color(int argbColor) {
		this.a = ((argbColor & 0xFF000000) >>> 24) % 256;
		this.r = ((argbColor & 0x00FF0000) >>> 16) % 256;
		this.g = ((argbColor & 0x0000FF00) >>> 8) % 256;
		this.b = (argbColor & 0x000000FF) % 256;
	}

	/**
	 * Obtém a representaçção da cor em valor hexadecimal.
	 * 
	 * @return Valor hexadecimal da representação da cor.
	 */
	public int toInt() {
		return ((a << 24) + (r << 16) + (g << 8) + b);
		// TODO: testar -> return ((a << 24) | (r << 16) | (g << 8) | b);
	}
}
