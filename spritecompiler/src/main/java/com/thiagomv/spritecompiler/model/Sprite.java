package com.thiagomv.spritecompiler.model;

/**
 * Define um sprite.
 * 
 * @author Thiago Mendes Vieira - thiagomv.0301.developer@gmail.com
 */
public class Sprite {
	private Image image;
	private SpriteSettings settings;

	/**
	 * Obtém a imagem que representa o conteúdo gráfico do sprite.
	 * 
	 * @return Imagem.
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Estabelece a imagem que representa o conteúdo gráfico do sprite.
	 * 
	 * @param image
	 *            Imagem.
	 */
	public void setImage(Image image) {
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
