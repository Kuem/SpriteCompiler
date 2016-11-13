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
	 * Obtém a imagem que representa o conteúdo gráfico do sprite.
	 * 
	 * @return Imagem.
	 */
	public FrameImage getImage() {
		return image;
	}

	/**
	 * Estabelece a imagem que representa o conteúdo gráfico do sprite.
	 * 
	 * @param image
	 *            Imagem.
	 */
	public void setImage(FrameImage image) {
		this.image = image;
	}

	/**
	 * Obtém as configurações do sprite.
	 * 
	 * @return {@link SpriteSettings}.
	 */
	public SpriteSettings getSettings() {
		return settings;
	}

	/**
	 * Estabelece as configurações do sprite.
	 * 
	 * @param settings
	 *            Configurações do sprite.
	 */
	public void setSettings(SpriteSettings settings) {
		this.settings = settings;
	}
}
