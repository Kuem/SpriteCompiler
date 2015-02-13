package com.thiagomv.spritecompiler.business;

import java.util.ArrayList;
import java.util.List;

import com.thiagomv.spritecompiler.commons.BaseBusinessImpl;
import com.thiagomv.spritecompiler.data.Rectangle2D;
import com.thiagomv.spritecompiler.data.Size;
import com.thiagomv.spritecompiler.data.StatusEmpacotamento;

public class OtimizacaoBusinessImpl extends BaseBusinessImpl implements
		OtimizacaoBusiness {

	/** {@inheritDoc} */
	@Override
	public List<Rectangle2D> otimizarAreaRetangularComRegioesRetangularesSemSobreposicao(
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
		List<Rectangle2D> regions = new ArrayList<>(sizes.size());

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

			Rectangle2D frame = new Rectangle2D();
			frame.setLeft(pX);
			frame.setBottom(pY);
			frame.setRight(pX + w - 1);
			frame.setTop(pY + h - 1);
			regions.add(frame);

			pX += bi.getWidth();
		}

		return regions;
	}

	public List<Rectangle2D> otimizarAreaRetangularComRegioesRetangularesSemSobreposicao2(
			List<Size> sizes) {
		StatusEmpacotamento status = criarStatusEmpacotamentoInicial(sizes);

		// TODO iterar em cada região pendente.
		// TODO obter lista de pontos âncoras e percorrar cada caso em
		// profundidade.
		// TODO inserir a região pendente na lista de retângulos, anexando-a no
		// ponto âncora, somente se for possível, ou seja, se não houver
		// interseção com nenhum retângulo ocupado.
		// TODO se não for possível inserir retângulo, cancela a solução para
		// este caso em profundidade.
		// TODO continua a iteração até acabar as regiões, retornando o
		// resultado se todas as regiões pendentes serem eliminadas e terem seus
		// respectivos retângulos no recipiente.
		return null;
	}

	private StatusEmpacotamento criarStatusEmpacotamentoInicial(List<Size> sizes) {
		// TODO obter área mínima.
		// TODO obter dimensões da área opengl mínima.
		// TODO inserir pontos âncoras iniciais nos 4 cantos do recipiente.
		// TODO inserir lista de pendência (sizes) de forma ordenada por largura
		// e depois altura.
		// TODO e lista vazia de regiões ocupadas.
		return null;
	}
}
