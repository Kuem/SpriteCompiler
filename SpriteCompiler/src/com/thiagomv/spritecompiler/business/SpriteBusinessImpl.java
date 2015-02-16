package com.thiagomv.spritecompiler.business;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.thiagomv.spritecompiler.commons.bases.BaseBusinessImpl;
import com.thiagomv.spritecompiler.commons.factories.BusinessFactory;
import com.thiagomv.spritecompiler.commons.factories.RepositoryFactory;
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

	private final GeometryBusiness geometryBusiness = BusinessFactory
			.getBusinessInstance(GeometryBusiness.class);

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
		return createSprite(frames, 0);
	}

	/** {@inheritDoc} */
	@Override
	public Sprite createSprite(List<FrameImage> frames, int frameSpace) {
		List<Size> sizes = getSizesImages(frames, frameSpace);
		List<Rectangle2D> regions = otimizacaoBusiness
				.otimizarAreaRetangularComRegioesRetangularesSemSobreposicao(sizes);

		if (regions == null) {
			return null;
		}
		organizarRegioesComSizes(regions, sizes);

		eliminarFrameSpaces(regions, frameSpace);
		SpriteSettings settings = createSpriteSettings(regions, frameSpace);

		FrameImage spriteImage = createSpriteImage(frames, settings);

		Sprite sprite = new Sprite();
		sprite.setImage(spriteImage);
		sprite.setSettings(settings);

		return sprite;
	}

	/**
	 * Elimina os pixels de espaçamento entre frames que foram adicionados às
	 * regiões.
	 * 
	 * @param regions
	 *            Regiões dos frames.
	 */
	private void eliminarFrameSpaces(List<Rectangle2D> regions, int frameSpace) {
		List<Rectangle2D> regionsSemFrameSpaces = new ArrayList<>(regions);
		regions.clear();
		for (Rectangle2D region : regionsSemFrameSpaces) {
			regions.add(geometryBusiness.encolher(region, frameSpace));
		}
	}

	/**
	 * Obtém o tamanho de cada frame em uma lista de frames.
	 * 
	 * @param frames
	 *            Lista de frames.
	 * @param frameSpace
	 *            Espaçamento entre frames.
	 * @return Lista e tamanhos.
	 */
	private List<Size> getSizesImages(List<FrameImage> frames, int frameSpace) {
		int pixelsFrameSpace = 2 * frameSpace;
		List<Size> sizes = new ArrayList<>();
		for (FrameImage frame : frames) {
			RenderedImage image = frame.getBufferedImage();
			sizes.add(new Size(image.getWidth() + pixelsFrameSpace, image
					.getHeight() + pixelsFrameSpace));
		}
		return sizes;
	}

	/**
	 * As regioes podem vir embaralhadas após o processamento. Pos este motivo
	 * reorganizamos as regiões para que estejam na mesma ordem de seus
	 * respectivos tamanhos.
	 * 
	 * @param regions
	 *            Lista de regiões.
	 * @param sizes
	 *            Lista de tamanhos.
	 */
	private void organizarRegioesComSizes(List<Rectangle2D> regions,
			final List<Size> sizes) {
		List<Rectangle2D> copiaRegions = new ArrayList<>(regions);
		for (int index = 0; index < sizes.size(); index++) {
			Size size = sizes.get(index);
			for (Rectangle2D region : copiaRegions) {
				if (region.getWidth() == size.getWidth()
						&& region.getHeight() == size.getHeight()) {
					regions.set(index, region);
					copiaRegions.remove(region);
					break;
				}
			}
		}
	}

	/**
	 * Cria o arquivo de configurações que forma o sprite.
	 * 
	 * @param regions
	 *            Regiões de cada frame no sprite.
	 * @param frameSpace
	 *            Espaçamento entre frames.
	 * @return {@link SpriteSettings}.
	 */
	private SpriteSettings createSpriteSettings(List<Rectangle2D> regions,
			int frameSpace) {
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
