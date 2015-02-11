package com.thiagomv.spritecompiler.business;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.thiagomv.spritecompiler.commons.BaseBusinessImpl;
import com.thiagomv.spritecompiler.commons.BusinessFactory;
import com.thiagomv.spritecompiler.commons.RepositoryFactory;
import com.thiagomv.spritecompiler.data.Color;
import com.thiagomv.spritecompiler.data.FrameImage;
import com.thiagomv.spritecompiler.data.Rectangle2D;
import com.thiagomv.spritecompiler.data.Size;
import com.thiagomv.spritecompiler.data.Sprite;
import com.thiagomv.spritecompiler.data.SpriteSettings;
import com.thiagomv.spritecompiler.repository.SpriteRepository;

public class SpriteBusinessImpl extends BaseBusinessImpl implements
		SpriteBusiness {
	private final SpriteRepository repository = RepositoryFactory
			.getRepositoryInstance(SpriteRepository.class);

	private final OtimizacaoBusiness otimizacaoBusiness = BusinessFactory
			.getBusinessInstance(OtimizacaoBusiness.class);

	private final ImageBusiness imageBusiness = BusinessFactory
			.getBusinessInstance(ImageBusiness.class);

	/** {@inheritDoc} */
	@Override
	public List<FrameImage> loadFrames(File rootSpriteDirectory) {
		return repository.loadFrames(rootSpriteDirectory);
	}

	/** {@inheritDoc} */
	@Override
	public Sprite loadSprite(File rootSpriteDirectory) {
		return repository.loadSprite(rootSpriteDirectory);
	}

	/** {@inheritDoc} */
	@Override
	public void saveSprite(Sprite sprite, File rootSpriteDirectory) {
		repository.saveSprite(sprite, rootSpriteDirectory);
	}

	/** {@inheritDoc} */
	@Override
	public Sprite createSprite(List<FrameImage> frames) {
		List<Size> sizes = getSizesImages(frames);
		List<Rectangle2D> regions = otimizacaoBusiness
				.otimizarAreaRetangularComRegioesRetangularesSemSobreposicao(sizes);
		SpriteSettings settings = createSpriteSettings(regions);

		FrameImage spriteImage = createSpriteImage(frames, settings);

		Sprite sprite = new Sprite();
		sprite.setImage(spriteImage);
		sprite.setSettings(settings);

		return sprite;
	}

	/**
	 * Obtém o tamanho de cada frame em uma lista de frames.
	 * 
	 * @param frames
	 *            Lista de frames.
	 * @return Lista e tamanhos.
	 */
	private List<Size> getSizesImages(List<FrameImage> frames) {
		List<Size> sizes = new ArrayList<>();
		for (FrameImage frame : frames) {
			RenderedImage image = frame.getBufferedImage();
			sizes.add(new Size(image.getWidth(), image.getHeight()));
		}
		return sizes;
	}

	/**
	 * Cria o arquivo de configurações que forma o sprite.
	 * 
	 * @param regions
	 *            Regiões de cada frame no sprite.
	 * @return {@link SpriteSettings}.
	 */
	private SpriteSettings createSpriteSettings(List<Rectangle2D> regions) {
		int spriteRight = 0;
		int spriteBottom = 0;
		for (Rectangle2D rect : regions) {
			if (rect.getRight() > spriteRight) {
				spriteRight = rect.getRight();
			}
			if (rect.getBottom() > spriteBottom) {
				spriteBottom = rect.getBottom();
			}
		}

		int spriteWidth = ajustarDimensaoOpenGL(spriteRight + 1);
		int spriteHeight = ajustarDimensaoOpenGL(spriteBottom + 1);

		SpriteSettings settings = new SpriteSettings();
		settings.setSize(new Size(spriteWidth, spriteHeight));
		settings.setRegions(regions);
		settings.setBackColor(new Color(0x0));

		return settings;
	}

	/**
	 * Ajusta uma dimensão de imagem para ficar compatível com o OpenGL. Uma
	 * dimensão de imagem deve ter um tamanho igual a 2^n, onde n é um número
	 * inteiro. O ajuste é feito aumentando a dimensão até chegar a um valor
	 * compatível.
	 * 
	 * @param value
	 *            Valor.
	 * @return Valor ajustado para 2^n.
	 */
	private static int ajustarDimensaoOpenGL(int value) {
		double n = Math.log(value) / Math.log(2);
		double ret = Math.pow(2, Math.ceil(n));
		return (int) Math.round(Math.floor(ret));
	}

	/**
	 * Cria a imagem do sprite baseado em suas configurações.
	 * 
	 * @param frames
	 *            Frames.
	 * @param settings
	 *            Configurações do sprite.
	 * @return Imagem do sprite.
	 */
	private FrameImage createSpriteImage(List<FrameImage> frames,
			SpriteSettings settings) {
		BufferedImage image = imageBusiness.createImage(settings.getSize(),
				settings.getBackColor());
		imageBusiness.insertFrames(image, frames, settings.getFrameRegions());

		FrameImage spriteImage = new FrameImage();
		spriteImage.setBufferedImage(image);

		return spriteImage;
	}
}
