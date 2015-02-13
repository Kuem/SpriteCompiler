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

		// Calculando regi�es...
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

		// TODO iterar em cada regi�o pendente.
		// TODO obter lista de pontos �ncoras e percorrar cada caso em
		// profundidade.
		// TODO inserir a regi�o pendente na lista de ret�ngulos, anexando-a no
		// ponto �ncora, somente se for poss�vel, ou seja, se n�o houver
		// interse��o com nenhum ret�ngulo ocupado.
		// TODO se n�o for poss�vel inserir ret�ngulo, cancela a solu��o para
		// este caso em profundidade.
		// TODO continua a itera��o at� acabar as regi�es, retornando o
		// resultado se todas as regi�es pendentes serem eliminadas e terem seus
		// respectivos ret�ngulos no recipiente.
		return null;
	}

	private StatusEmpacotamento criarStatusEmpacotamentoInicial(List<Size> sizes) {
		// TODO obter �rea m�nima.
		// TODO obter dimens�es da �rea opengl m�nima.
		// TODO inserir pontos �ncoras iniciais nos 4 cantos do recipiente.
		// TODO inserir lista de pend�ncia (sizes) de forma ordenada por largura
		// e depois altura.
		// TODO e lista vazia de regi�es ocupadas.
		return null;
	}
}
