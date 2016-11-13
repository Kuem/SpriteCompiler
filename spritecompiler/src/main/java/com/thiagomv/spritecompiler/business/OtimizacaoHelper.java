package com.thiagomv.spritecompiler.business;

import java.util.Comparator;

import com.thiagomv.spritecompiler.data.PontoAncora;
import com.thiagomv.spritecompiler.data.Size;

public class OtimizacaoHelper {
	private static final Comparator<? super Size> COMPARATOR_SIZES = new Comparator<Size>() {

		/**
		 * Compara dois tamanhos, que ordena do maior para o menor. Um tamanho �
		 * ordenado pela maior medida lateral (largura ou altura). Se ambos t�m
		 * a mesma maior medida lateral, ent�o s�o ordenados pela segunda maior
		 * medida lateral.
		 * 
		 * @param s1
		 *            Tamanho 1.
		 * @param s2
		 *            Tamanho 2.
		 * @return -1 se o tamanho 1 for menor que o tamanho 2, 0 se ambos t�m a
		 *         mesma ordem, 1 se o tamanho 2 for menor que o tamanho 1.
		 */
		public int compare(Size s1, Size s2) {
			int maiorMedida1 = Math.max(s1.getWidth(), s1.getHeight());
			int maiorMedida2 = Math.max(s2.getWidth(), s2.getHeight());

			if (maiorMedida1 < maiorMedida2) {
				return 1;
			} else if (maiorMedida1 == maiorMedida2) {
				int menorMedida1 = Math.min(s1.getWidth(), s1.getHeight());
				int menorMedida2 = Math.min(s2.getWidth(), s2.getHeight());

				if (menorMedida1 < menorMedida2) {
					return 1;
				} else if (menorMedida1 == menorMedida2) {
					return 0;
				} else {
					return -1;
				}
			} else {
				return -1;
			}
		}
	};

	private static final Comparator<? super PontoAncora> COMPARATOR_ANCORAS = new Comparator<PontoAncora>() {

		/**
		 * Compara duas �ncoras, que ordena do menor para o maior. Uma �ncora �
		 * ordenada pelo menor valor de y. Se ambos t�m o mesmo valor de y,
		 * ent�o s�o ordenados pelo menor valor de x.
		 * 
		 * @param s1
		 *            Tamanho 1.
		 * @param s2
		 *            Tamanho 2.
		 * @return -1 se o tamanho 1 for menor que o tamanho 2, 0 se ambos t�m a
		 *         mesma ordem, 1 se o tamanho 2 for menor que o tamanho 1.
		 */
		public int compare(PontoAncora a1, PontoAncora a2) {
			int y1 = a1.getY();
			int y2 = a2.getY();

			if (y1 < y2) {
				return -1;
			} else if (y1 == y2) {
				int x1 = a1.getX();
				int x2 = a2.getX();

				if (x1 < x2) {
					return -1;
				} else if (x1 == x2) {
					return 0;
				} else {
					return 1;
				}
			} else {
				return 1;
			}
		}
	};

	/**
	 * Obt�m o comparador de tamanhos que ordenada, do maior para o menor, pela
	 * maior medida lateral (largura ou altura). Se ambos t�m a mesma maior
	 * medida lateral, ent�o s�o ordenados pela segunda maior medida lateral.
	 * 
	 * @return Comparador de tamanhos.
	 */
	public static Comparator<? super Size> getComparatorSizes() {
		return COMPARATOR_SIZES;
	}

	/**
	 * Obt�m o comparador de �ncoras que ordena, do menor para o maior, pelo
	 * menor valor de y. Se ambos t�m o mesmo valor de y, ent�o s�o ordenados
	 * pelo menor valor de x.
	 * 
	 * @return Comparador de �ncoras.
	 */
	public static Comparator<? super PontoAncora> getComparatorAncoras() {
		return COMPARATOR_ANCORAS;
	}
}
