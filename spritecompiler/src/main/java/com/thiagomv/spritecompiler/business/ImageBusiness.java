package com.thiagomv.spritecompiler.business;

import java.awt.image.BufferedImage;
import java.util.List;

import com.kuemsoftwares.util.commons.base.BaseBusiness;
import com.thiagomv.spritecompiler.model.Color;
import com.thiagomv.spritecompiler.model.Image;
import com.thiagomv.spritecompiler.model.Rectangle2D;
import com.thiagomv.spritecompiler.model.Size;

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
	 *            Regi�es de cada frame na imagem.
	 */
	void insertFrames(BufferedImage image, List<Image> frames,
			List<Rectangle2D> frameRegions);

}
