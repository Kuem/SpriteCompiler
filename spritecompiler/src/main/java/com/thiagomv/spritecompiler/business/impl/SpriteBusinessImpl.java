package com.thiagomv.spritecompiler.business.impl;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.kuemsoftwares.util.commons.base.BaseBusinessImpl;
import com.thiagomv.spritecompiler.business.GeometryBusiness;
import com.thiagomv.spritecompiler.business.ImageBusiness;
import com.thiagomv.spritecompiler.business.OtimizacaoBusiness;
import com.thiagomv.spritecompiler.business.SpriteBusiness;
import com.thiagomv.spritecompiler.factories.BusinessFactory;
import com.thiagomv.spritecompiler.factories.RepositoryFactory;
import com.thiagomv.spritecompiler.model.Color;
import com.thiagomv.spritecompiler.model.Image;
import com.thiagomv.spritecompiler.model.Rectangle2D;
import com.thiagomv.spritecompiler.model.Size;
import com.thiagomv.spritecompiler.model.Sprite;
import com.thiagomv.spritecompiler.model.SpriteSettings;
import com.thiagomv.spritecompiler.repository.SpriteRepository;

public class SpriteBusinessImpl extends BaseBusinessImpl implements SpriteBusiness {
	private final SpriteRepository repository = RepositoryFactory.getRepositoryInstance(SpriteRepository.class);

	private final OtimizacaoBusiness otimizacaoBusiness = BusinessFactory.getBusinessInstance(OtimizacaoBusiness.class);

	private final ImageBusiness imageBusiness = BusinessFactory.getBusinessInstance(ImageBusiness.class);

	private final GeometryBusiness geometryBusiness = BusinessFactory.getBusinessInstance(GeometryBusiness.class);

	/** {@inheritDoc} */
	public List<Image> loadFrames(File rootSpriteDirectory) {
		return repository.loadFrames(rootSpriteDirectory);
	}

	/** {@inheritDoc} */
	public Sprite loadSprite(File rootSpriteDirectory) {
		return repository.loadSprite(rootSpriteDirectory);
	}

	/** {@inheritDoc} */
	public void saveSprite(Sprite sprite, File rootSpriteDirectory) {
		repository.saveSprite(sprite, rootSpriteDirectory);
	}

	/** {@inheritDoc} */
	public Sprite createSprite(List<Image> frames) {
		return createSprite(frames, 0);
	}

	/** {@inheritDoc} */
	public Sprite createSprite(List<Image> frames, int frameSpace) {
		List<Size> sizes = getSizesImages(frames, frameSpace);
		List<Rectangle2D> regions = otimizacaoBusiness
				.otimizarAreaRetangularComRegioesRetangularesSemSobreposicao(sizes);

		if (regions == null) {
			return null;
		}
		organizarRegioesComSizes(regions, sizes);

		eliminarFrameSpaces(regions, frameSpace);
		SpriteSettings settings = createSpriteSettings(regions, frameSpace);

		Image spriteImage = createSpriteImage(frames, settings);

		Sprite sprite = new Sprite();
		sprite.setImage(spriteImage);
		sprite.setSettings(settings);

		return sprite;
	}

	/**
	 * Elimina os pixels de espa�amento entre frames que foram adicionados �s
	 * regi�es.
	 * 
	 * @param regions
	 *            Regi�es dos frames.
	 */
	private void eliminarFrameSpaces(List<Rectangle2D> regions, int frameSpace) {
		List<Rectangle2D> regionsSemFrameSpaces = new ArrayList<>(regions);
		regions.clear();
		for (Rectangle2D region : regionsSemFrameSpaces) {
			regions.add(geometryBusiness.redimensionar(region, -frameSpace));
		}
	}

	/**
	 * Obt�m o tamanho de cada frame em uma lista de frames.
	 * 
	 * @param frames
	 *            Lista de frames.
	 * @param frameSpace
	 *            Espa�amento entre frames.
	 * @return Lista e tamanhos.
	 */
	private List<Size> getSizesImages(List<Image> frames, int frameSpace) {
		int pixelsFrameSpace = 2 * frameSpace;
		List<Size> sizes = new ArrayList<>();
		for (Image frame : frames) {
			RenderedImage image = frame.getBufferedImage();
			sizes.add(new Size(image.getWidth() + pixelsFrameSpace, image.getHeight() + pixelsFrameSpace));
		}
		return sizes;
	}

	/**
	 * As regioes podem vir embaralhadas ap�s o processamento. Pos este motivo
	 * reorganizamos as regi�es para que estejam na mesma ordem de seus
	 * respectivos tamanhos.
	 * 
	 * @param regions
	 *            Lista de regi�es.
	 * @param sizes
	 *            Lista de tamanhos.
	 */
	private void organizarRegioesComSizes(List<Rectangle2D> regions, final List<Size> sizes) {
		List<Rectangle2D> copiaRegions = new ArrayList<>(regions);
		for (int index = 0; index < sizes.size(); index++) {
			Size size = sizes.get(index);
			for (Rectangle2D region : copiaRegions) {
				if (region.getWidth() == size.getWidth() && region.getHeight() == size.getHeight()) {
					regions.set(index, region);
					copiaRegions.remove(region);
					break;
				}
			}
		}
	}

	/**
	 * Cria o arquivo de configura��es que forma o sprite.
	 * 
	 * @param regions
	 *            Regi�es de cada frame no sprite.
	 * @param frameSpace
	 *            Espa�amento entre frames.
	 * @return {@link SpriteSettings}.
	 */
	private SpriteSettings createSpriteSettings(List<Rectangle2D> regions, int frameSpace) {
		int spriteRight = 0;
		int spriteTop = 0;
		for (Rectangle2D rect : regions) {
			if (rect.getRight() > spriteRight) {
				spriteRight = rect.getRight();
			}
			if (rect.getTop() > spriteTop) {
				spriteTop = rect.getTop();
			}
		}

		int spriteWidth = ajustarDimensaoOpenGL(spriteRight + frameSpace + 1);
		int spriteHeight = ajustarDimensaoOpenGL(spriteTop + frameSpace + 1);

		SpriteSettings settings = new SpriteSettings();
		settings.setSize(new Size(spriteWidth, spriteHeight));
		settings.setRegions(regions);
		settings.setBackColor(new Color(0x0));

		return settings;
	}

	/**
	 * Ajusta uma dimens�o de imagem para ficar compat�vel com o OpenGL. Uma
	 * dimens�o de imagem deve ter um tamanho igual a 2^n, onde n � um n�mero
	 * inteiro. O ajuste � feito aumentando a dimens�o at� chegar a um valor
	 * compat�vel.
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
	 * Cria a imagem do sprite baseado em suas configura��es.
	 * 
	 * @param frames
	 *            Frames.
	 * @param settings
	 *            Configura��es do sprite.
	 * @return Imagem do sprite.
	 */
	private Image createSpriteImage(List<Image> frames, SpriteSettings settings) {
		BufferedImage image = imageBusiness.createImage(settings.getSize(), settings.getBackColor());
		imageBusiness.insertFrames(image, frames, settings.getFrameRegions());

		Image spriteImage = new Image();
		spriteImage.setBufferedImage(image);

		return spriteImage;
	}
}
