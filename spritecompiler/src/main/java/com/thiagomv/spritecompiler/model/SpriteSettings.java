package com.thiagomv.spritecompiler.model;

import java.io.Serializable;
import java.util.List;

/**
 * Define as configurações de um Sprite.
 * 
 * @author Thiago Mendes Vieira - thiagomv.0301.developer@gmail.com
 */
public class SpriteSettings implements Serializable {
	private static final long serialVersionUID = 1L;

	private Size size;
	private List<Rectangle2D> regions;
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
	 * @return Lista de {@link Rectangle2D}.
	 */
	public List<Rectangle2D> getFrameRegions() {
		return regions;
	}

	/**
	 * Estabelece a lista de regiões dos frames do sprite.
	 * 
	 * @param regions
	 *            Lista de regiões dos frames do sprite.
	 */
	public void setRegions(List<Rectangle2D> regions) {
		this.regions = regions;
	}

	/**
	 * Obtém a cor de fundo do sprite.
	 * 
	 * @return Cor de fundo.
	 */
	public Color getBackColor() {
		return backColor;
	}

	/**
	 * Estabelece a cor de fundo do sprite.
	 * 
	 * @param backColor
	 *            Cor de fundo.
	 */
	public void setBackColor(Color backColor) {
		this.backColor = backColor;
	}
}
