package com.thiagomv.spritecompiler.data;

import java.io.Serializable;
import java.util.List;

/**
 * Define as configurações de um Sprite.
 * 
 * @author Thiago Mendes Vieira
 * 
 *         05/02/2015
 */
public class SpriteSettings implements Serializable {
	private static final long serialVersionUID = 1L;

	private Size size;
	private List<Rectangle> regions;
	private Color backColor;

	/**
	 * Obtém o tamanho do sprite.
	 * 
	 * @return {@link Size}.
	 */
	public Size getSize() {
		return size;
	}

	/**
	 * Estabelece o tamanho do sprite.
	 * 
	 * @param size
	 *            Tamanho do sprite.
	 */
	public void setSize(Size size) {
		this.size = size;
	}

	/**
	 * Obtém a lista de regiões dos frames do sprite.
	 * 
	 * @return Lista de {@link Rectangle}.
	 */
	public List<Rectangle> getFrameRegions() {
		return regions;
	}

	/**
	 * Estabelece a lista de regiões dos frames do sprite.
	 * 
	 * @param regions
	 *            Lista de regiões dos frames do sprite.
	 */
	public void setRegions(List<Rectangle> regions) {
		this.regions = regions;
	}

	public Color getBackColor() {
		return backColor;
	}

	public void setBackColor(Color backColor) {
		this.backColor = backColor;
	}
}
