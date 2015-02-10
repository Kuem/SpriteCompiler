package com.thiagomv.spritecompiler.business;

import java.util.ArrayList;
import java.util.List;

import com.thiagomv.spritecompiler.commons.BaseBusinessImpl;
import com.thiagomv.spritecompiler.data.Rectangle;
import com.thiagomv.spritecompiler.data.Size;

public class OtimizacaoBusinessImpl extends BaseBusinessImpl implements
		OtimizacaoBusiness {

	/** {@inheritDoc} */
	@Override
	public List<Rectangle> otimizarAreaRetangularComRegioesRetangularesSemSobreposicao(
			List<Size> sizes) {
		// Quantidade de colunas de frames do sprite.
		final int columns = 6;

		// Calculando largura e altura de cada linha...
		int numLines = (int) Math
				.ceil((double) sizes.size() / (double) columns);
		int[] widthLines = new int[numLines];
		int[] heightLines = new int[numLines];

		Size bi;
		int height;
		int line = -1;
		int column = columns;

		for (int I = 0; I < sizes.size(); I++, column++) {
			if (column == columns) {
				column = 0;
				line++;
				widthLines[line] = 0;
				heightLines[line] = 0;
			}

			bi = sizes.get(I);
			widthLines[line] += bi.getWidth();

			height = bi.getHeight();
			if (height > heightLines[line]) {
				heightLines[line] = height;
			}
		}

		// Calculando regiões...
		List<Rectangle> regions = new ArrayList<>(sizes.size());

		line = -1;
		column = columns;
		int w, h;
		int pX = 0, pY;
		int sumLine = 0;
		for (int I = 0; I < sizes.size(); I++, column++) {
			if (column == columns) {
				column = 0;
				line++;

				pX = 0;
				sumLine += heightLines[line];
			}

			bi = sizes.get(I);
			w = bi.getWidth();
			h = bi.getHeight();

			pY = sumLine - bi.getHeight();

			Rectangle frame = new Rectangle();
			frame.setLeft(pX);
			frame.setTop(pY);
			frame.setRight(pX + w - 1);
			frame.setBottom(pY + h - 1);
			regions.add(frame);

			pX += bi.getWidth();
		}

		return regions;
	}

}
