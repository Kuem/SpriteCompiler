package com.thiagomv.spritecompiler.data;

import java.io.Serializable;

public class Color implements Serializable {
	private static final long serialVersionUID = 1L;

	public int a, r, g, b;

	public Color() {
		a = r = g = b = 0;
	}

	public Color(int a, int r, int g, int b) {
		this.a = a;
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public Color(int argbColor) {
		this.a = ((argbColor & 0xFF000000) >>> 24) % 256;
		this.r = ((argbColor & 0x00FF0000) >>> 16) % 256;
		this.g = ((argbColor & 0x0000FF00) >>> 8) % 256;
		this.b = (argbColor & 0x000000FF) % 256;
	}

	public int toInt() {
		return ((a << 24) + (r << 16) + (g << 8) + b);
	}
}
