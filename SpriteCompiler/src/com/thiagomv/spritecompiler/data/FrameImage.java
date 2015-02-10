package com.thiagomv.spritecompiler.data;

import java.awt.image.BufferedImage;

/**
 * Define a imagem de um frame.
 * 
 * @author Thiago Mendes Vieira
 * 
 *         05/02/2015
 */
public class FrameImage {
	private BufferedImage frame;

	/**
	 * Estabelece uma imagem ao frame.
	 * 
	 * @param image
	 */
	public void setBufferedImage(BufferedImage image) {
		this.frame = image;
	}

	/**
	 * Obtém a imagem do frame.
	 * 
	 * @return Imagem do frame.
	 */
	public BufferedImage getBufferedImage() {
		return frame;
	}
}
