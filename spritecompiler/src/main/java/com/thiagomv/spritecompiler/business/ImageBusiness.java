package com.thiagomv.spritecompiler.business;

import java.awt.image.BufferedImage;
import java.util.List;

import com.thiagomv.spritecompiler.commons.bases.BaseBusiness;
import com.thiagomv.spritecompiler.data.Color;
import com.thiagomv.spritecompiler.data.FrameImage;
import com.thiagomv.spritecompiler.data.Rectangle2D;
import com.thiagomv.spritecompiler.data.Size;

public interface ImageBusiness extends BaseBusiness {

	/**
	 * Cria uma imagem vazia com tamanho e cor definido.
	 * 
	 * @param size
	 *            Tamanho da imagem.
	 * @param backColor
	 *            Cor aplicada na imagem.
	 * @return Imagem.
	 */
	BufferedImage createImage(Size size, Color backColor);

	/**
	 * Insere frames em uma imagem existente.
	 * 
	 * @param image
	 *            Imagem.
	 * @param frames
	 *            Frames.
	 * @param frameRegions
	 *            Regiões de cada frame na imagem.
	 */
	void insertFrames(BufferedImage image, List<FrameImage> frames,
			List<Rectangle2D> frameRegions);

}
