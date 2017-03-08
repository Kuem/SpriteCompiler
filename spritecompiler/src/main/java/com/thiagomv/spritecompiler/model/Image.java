package com.thiagomv.spritecompiler.model;

import java.awt.image.BufferedImage;

/**
 * Define uma imagem.
 * 
 * @author Thiago Mendes Vieira - thiagomv.0301.developer@gmail.com
 */
public class Image {
	private BufferedImage frame;

	/**
	 * Estabelece dados da imagem.
	 * 
	 * @param image
	 *            Imagem.
	 */
	public void setBufferedImage(BufferedImage image) {
		this.frame = image;
	}

	/**
	 * Obtém a imagem.
	 * 
	 * @return Imagem.
	 */
	public BufferedImage getBufferedImage() {
		return frame;
	}
}
