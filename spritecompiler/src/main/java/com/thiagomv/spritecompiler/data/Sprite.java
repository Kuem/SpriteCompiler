package com.thiagomv.spritecompiler.data;

/**
 * Define um spprite.
 * 
 * @author Thiago Mendes Vieira
 * 
 *         05/02/2015
 */
public class Sprite {
	private FrameImage image;
	private SpriteSettings settings;

	/**
	 * Obt�m a imagem que representa o conte�do gr�fico do sprite.
	 * 
	 * @return Imagem.
	 */
	public FrameImage getImage() {
		return image;
	}

	/**
	 * Estabelece a imagem que representa o conte�do gr�fico do sprite.
	 * 
	 * @param image
	 *            Imagem.
	 */
	public void setImage(FrameImage image) {
		this.image = image;
	}

	/**
	 * Obt�m as configura��es do sprite.
	 * 
	 * @return {@link SpriteSettings}.
	 */
	public SpriteSettings getSettings() {
		return settings;
	}

	/**
	 * Estabelece as configura��es do sprite.
	 * 
	 * @param settings
	 *            Configura��es do sprite.
	 */
	public void setSettings(SpriteSettings settings) {
		this.settings = settings;
	}
}
