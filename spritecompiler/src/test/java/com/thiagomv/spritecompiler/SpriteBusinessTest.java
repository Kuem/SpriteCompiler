package com.thiagomv.spritecompiler;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import com.thiagomv.spritecompiler.business.SpriteBusiness;
import com.thiagomv.spritecompiler.commons.factories.BusinessFactory;
import com.thiagomv.spritecompiler.data.FrameImage;
import com.thiagomv.spritecompiler.data.Rectangle2D;
import com.thiagomv.spritecompiler.data.Sprite;

public class SpriteBusinessTest {
	private static final String resourcesPath = "src" + File.separator + "test" + File.separator + "resources";

	private SpriteBusiness spriteBusiness = BusinessFactory.getBusinessInstance(SpriteBusiness.class);

	private File rootSpriteExplosion = new File(resourcesPath + File.separator + "explosion");

	private File rootSpriteCubos1 = new File(resourcesPath + File.separator + "cubos1");

	private File rootSpriteCubos2 = new File(resourcesPath + File.separator + "cubos2");

	@Test
	public void criarSpriteExplosion() {
		criarSprite(rootSpriteExplosion, 1);
	}

	@Test
	public void criarSpriteCubos1() {
		criarSprite(rootSpriteCubos1, 1);
	}

	@Test
	public void criarSpriteCubos2() {
		criarSprite(rootSpriteCubos2);
	}

	private void criarSprite(File root) {
		List<FrameImage> frames = spriteBusiness.loadFrames(root);
		Sprite sprite = spriteBusiness.createSprite(frames);
		assertSaveSprite(root, frames, sprite);
	}

	private void criarSprite(File root, int tileSpace) {
		List<FrameImage> frames = spriteBusiness.loadFrames(root);
		Sprite sprite = spriteBusiness.createSprite(frames, tileSpace);
		assertSaveSprite(root, frames, sprite);
	}

	private void assertSaveSprite(File root, List<FrameImage> frames, Sprite sprite) {
		assertSprite(frames, sprite);

		spriteBusiness.saveSprite(sprite, root);

		Sprite savedSprite = spriteBusiness.loadSprite(root);

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
			Assert.assertThat(region.getWidth(), CoreMatchers.equalTo(frame.getWidth()));
			Assert.assertThat(region.getHeight(), CoreMatchers.equalTo(frame.getHeight()));

			int[] bufferFrame = frame.getRGB(0, 0, region.getWidth(), region.getHeight(), null, 0, region.getWidth());
			int[] bufferSprite = spriteImage.getRGB(region.getLeft(), region.getBottom(), region.getWidth(),
					region.getHeight(), null, 0, region.getWidth());
			Assert.assertArrayEquals(bufferFrame, bufferSprite);
		}
	}

	/**
	 * Verifica se a dimens�o de uma imagem � compat�vel com OpenGL. Para ser
	 * compat�vel a dimens�o deve ter tamanho 2^n, onde n � um n�mero inteiro,
	 * com valor m�ximo de 4096.
	 * 
	 * @param size
	 *            Tamanho da dimens�o.
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
		BufferedImage savedSpriteImage = savedSprite.getImage().getBufferedImage();
		Assert.assertThat(spriteImage.getWidth(), CoreMatchers.equalTo(savedSpriteImage.getWidth()));
		Assert.assertThat(spriteImage.getHeight(), CoreMatchers.equalTo(savedSpriteImage.getHeight()));

		int[] bufferSprite = spriteImage.getRGB(0, 0, spriteImage.getWidth(), spriteImage.getHeight(), null, 0,
				spriteImage.getWidth());
		int[] bufferSavedSprite = spriteImage.getRGB(0, 0, savedSpriteImage.getWidth(), savedSpriteImage.getHeight(),
				null, 0, savedSpriteImage.getWidth());
		Assert.assertArrayEquals(bufferSprite, bufferSavedSprite);

		// Verificando regi�es...
		List<Rectangle2D> regionsSprite = sprite.getSettings().getFrameRegions();
		List<Rectangle2D> regionsSavedSprite = savedSprite.getSettings().getFrameRegions();
		Assert.assertArrayEquals(regionsSprite.toArray(), regionsSavedSprite.toArray());
	}
}
