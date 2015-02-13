package com.thiagomv.spritecompiler.tests;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import com.thiagomv.spritecompiler.business.ImageBusiness;
import com.thiagomv.spritecompiler.commons.BusinessFactory;
import com.thiagomv.spritecompiler.data.Color;
import com.thiagomv.spritecompiler.data.FrameImage;
import com.thiagomv.spritecompiler.data.Rectangle2D;
import com.thiagomv.spritecompiler.data.Size;

public class ImageBusinessTest {
	private ImageBusiness imageBusiness = BusinessFactory
			.getBusinessInstance(ImageBusiness.class);

	private Color branco = new Color(0xFFFFFFFF);
	private Color azul = new Color(0xFF0000FF);
	private Color vermelho = new Color(0xFFFF0000);
	private Color amarelo = new Color(0xFFFFFF00);
	private Color verde = new Color(0xFF00FF00);

	@Test
	public void createImageComSucesso() {
		int w = 20;
		int h = 30;
		int argb = 0xAABBCCDD;
		Size size = new Size(w, h);
		Color backColor = new Color(argb);

		BufferedImage image = imageBusiness.createImage(size, backColor);
		Assert.assertThat(image.getWidth(), CoreMatchers.equalTo(w));
		Assert.assertThat(image.getHeight(), CoreMatchers.equalTo(h));

		int lenght = w * h;
		int[] expecteds = new int[lenght];
		Arrays.fill(expecteds, argb);

		int[] buffer = image.getRGB(0, 0, w, h, null, 0, w);
		Assert.assertArrayEquals(expecteds, buffer);
	}

	@Test
	public void insertFramesComSucesso() {
		int w = 3;
		int h = 4;
		Size size = new Size(w, h);
		BufferedImage img1 = imageBusiness.createImage(size, azul);
		BufferedImage img2 = imageBusiness.createImage(size, vermelho);
		BufferedImage img3 = imageBusiness.createImage(size, amarelo);
		BufferedImage img4 = imageBusiness.createImage(size, verde);

		Size sizeTotal = new Size(w * 2, h * 2);
		BufferedImage img5 = imageBusiness.createImage(sizeTotal, branco);

		List<Rectangle2D> regions = new ArrayList<>();
		regions.add(new Rectangle2D(0, 0, w - 1, h - 1));
		regions.add(new Rectangle2D(w, 0, 2 * w - 1, h - 1));
		regions.add(new Rectangle2D(0, h, w - 1, 2 * h - 1));
		regions.add(new Rectangle2D(w, h, 2 * w - 1, 2 * h - 1));

		imageBusiness.insertFrames(img5,
				getFrameList(Arrays.asList(img1, img2, img3, img4)), regions);

		int[] bufferSrc;
		int[] bufferDst;

		bufferSrc = img1.getRGB(0, 0, w, h, null, 0, w);
		bufferDst = img5.getRGB(0, 0, w, h, null, 0, w);
		Assert.assertArrayEquals(bufferSrc, bufferDst);

		bufferSrc = img2.getRGB(0, 0, w, h, null, 0, w);
		bufferDst = img5.getRGB(w, 0, w, h, null, 0, w);
		Assert.assertArrayEquals(bufferSrc, bufferDst);

		bufferSrc = img3.getRGB(0, 0, w, h, null, 0, w);
		bufferDst = img5.getRGB(0, h, w, h, null, 0, w);
		Assert.assertArrayEquals(bufferSrc, bufferDst);

		bufferSrc = img4.getRGB(0, 0, w, h, null, 0, w);
		bufferDst = img5.getRGB(w, h, w, h, null, 0, w);
		Assert.assertArrayEquals(bufferSrc, bufferDst);
	}

	private List<FrameImage> getFrameList(List<BufferedImage> images) {
		List<FrameImage> lista = new ArrayList<>();
		for (BufferedImage image : images) {
			FrameImage frame = new FrameImage();
			frame.setBufferedImage(image);
			lista.add(frame);
		}
		return lista;
	}
}
