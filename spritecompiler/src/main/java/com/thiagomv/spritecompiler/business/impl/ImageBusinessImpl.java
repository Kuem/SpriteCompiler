package com.thiagomv.spritecompiler.business.impl;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

import com.kuemsoftwares.util.commons.base.BaseBusinessImpl;
import com.thiagomv.spritecompiler.business.ImageBusiness;
import com.thiagomv.spritecompiler.model.Color;
import com.thiagomv.spritecompiler.model.Image;
import com.thiagomv.spritecompiler.model.Rectangle2D;
import com.thiagomv.spritecompiler.model.Size;

public class ImageBusinessImpl extends BaseBusinessImpl implements ImageBusiness {

	/** {@inheritDoc} */
	public BufferedImage createImage(Size size, Color backColor) {
		BufferedImage image = new BufferedImage(size.getWidth(), size.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);

		int[] pixels = getPixels(image);
		Arrays.fill(pixels, backColor.toInt());
		image.setRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
		return image;
	}

	/** {@inheritDoc} */
	public void insertFrames(BufferedImage image, List<Image> frames, List<Rectangle2D> frameRegions) {
		for (int index = 0; index < frameRegions.size(); index++) {
			Rectangle2D region = frameRegions.get(index);
			BufferedImage frame = frames.get(index).getBufferedImage();

			image.setRGB(region.getLeft(), region.getBottom(), region.getWidth(), region.getHeight(), getPixels(frame),
					0, frame.getWidth());
		}
	}

	/**
	 * Obt�m o buffer de pixels de uma imagem.
	 * 
	 * @param image
	 *            Imagem.
	 * @return Buffer de pixels no formato RGB.
	 */
	private int[] getPixels(BufferedImage image) {
		return image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
	}

}
