package com.thiagomv.spritecompiler.tests;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import com.thiagomv.spritecompiler.business.SpriteBusiness;
import com.thiagomv.spritecompiler.commons.BusinessFactory;
import com.thiagomv.spritecompiler.data.FrameImage;
import com.thiagomv.spritecompiler.data.Rectangle2D;
import com.thiagomv.spritecompiler.data.Sprite;

public class SpriteBusinessTest {
	private SpriteBusiness spriteBusiness = BusinessFactory
			.getBusinessInstance(SpriteBusiness.class);

	private File rootSpriteDirectory = new File(System.getProperty("user.dir")
			+ File.separator + "testFolder" + File.separator + "testSprite");

	@Test
	public void criarspriteComSucesso() {
		List<FrameImage> frames = spriteBusiness
				.loadFrames(rootSpriteDirectory);
		Sprite sprite = spriteBusiness.createSprite(frames);

		assertSprite(frames, sprite);

		spriteBusiness.saveSprite(sprite, rootSpriteDirectory);

		Sprite savedSprite = spriteBusiness.loadSprite(rootSpriteDirectory);

		assertSprite(sprite, savedSprite);
	}

	private void assertSprite(List<FrameImage> frames, Sprite sprite) {
		List<Rectangle2D> regions = sprite.getSettings().getFrameRegions();
		Assert.assertThat(frames.size(), CoreMatchers.equalTo(regions.size()));

		BufferedImage spriteImage = sprite.getImage().getBufferedImage();
		assertOpenGLSizeSprite(spriteImage.getWidth());
		assertOpenGLSizeSprite(spriteImage.getHeight());

		for (int index = 0; index < frames.size(); index++) {
			Rectangle2D region = regions.get(index);
			BufferedImage frame = frames.get(index).getBufferedImage();
			Assert.assertThat(region.getWidth(),
					CoreMatchers.equalTo(frame.getWidth()));
			Assert.assertThat(region.getHeight(),
					CoreMatchers.equalTo(frame.getHeight()));

			int[] bufferFrame = frame.getRGB(0, 0, region.getWidth(),
					region.getHeight(), null, 0, region.getWidth());
			int[] bufferSprite = spriteImage.getRGB(region.getLeft(),
					region.getTop(), region.getWidth(), region.getHeight(),
					null, 0, region.getWidth());
			Assert.assertArrayEquals(bufferFrame, bufferSprite);
		}
	}

	/**
	 * Verifica se a dimensão de uma imagem é compatível com OpenGL. Para ser
	 * compatível a dimensão deve ter tamanho 2^n, onde n é um número inteiro,
	 * com valor máximo de 4096.
	 * 
	 * @param size
	 *            Tamanho da dimensão.
	 */
	private void assertOpenGLSizeSprite(int size) {
		int v = 1;
		for (int n = 0; n <= 12; n++) {
			if (size == v)
				return;
			v *= 2;
		}
		Assert.fail();
	}

	private void assertSprite(Sprite sprite, Sprite savedSprite) {
		// Verificano imagens...
		BufferedImage spriteImage = sprite.getImage().getBufferedImage();
		BufferedImage savedSpriteImage = savedSprite.getImage()
				.getBufferedImage();
		Assert.assertThat(spriteImage.getWidth(),
				CoreMatchers.equalTo(savedSpriteImage.getWidth()));
		Assert.assertThat(spriteImage.getHeight(),
				CoreMatchers.equalTo(savedSpriteImage.getHeight()));

		int[] bufferSprite = spriteImage.getRGB(0, 0, spriteImage.getWidth(),
				spriteImage.getHeight(), null, 0, spriteImage.getWidth());
		int[] bufferSavedSprite = spriteImage.getRGB(0, 0,
				savedSpriteImage.getWidth(), savedSpriteImage.getHeight(),
				null, 0, savedSpriteImage.getWidth());
		Assert.assertArrayEquals(bufferSprite, bufferSavedSprite);

		// Verificando regiões...
		List<Rectangle2D> regionsSprite = sprite.getSettings().getFrameRegions();
		List<Rectangle2D> regionsSavedSprite = savedSprite.getSettings()
				.getFrameRegions();
		Assert.assertArrayEquals(regionsSprite.toArray(),
				regionsSavedSprite.toArray());
	}
}
