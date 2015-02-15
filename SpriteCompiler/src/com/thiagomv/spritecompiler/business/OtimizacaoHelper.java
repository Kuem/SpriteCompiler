package com.thiagomv.spritecompiler.business;

import java.util.Comparator;

import com.thiagomv.spritecompiler.data.Size;

public class OtimizacaoHelper {
	private static final Comparator<Size> COMPARATOR_SIZES = new Comparator<Size>() {

		/**
		 * Compara dois tamanhos, que ordena do maior para o menor. Um tamanho é
		 * ordenado pela maior medida lateral (largura ou altura). Se ambos têm
		 * a mesma maior medida lateral, então são ordenados pela segunda maior
		 * medida lateral.
		 * 
		 * @param s1
		 *            Tamanho 1.
		 * @param s2
		 *            Tamanho 2.
		 * @return -1 se o tamanho 1 for menor que o tamanho 2, 0 se ambos têm a
		 *         mesma ordem, 1 se o tamanho 2 for menor que o tamanho 1.
		 */
		@Override
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

	/**
	 * Obtém o comparador de tamanhos que ordenada, do maior para o menor, pela
	 * maior medida lateral (largura ou altura). Se ambos têm a mesma maior
	 * medida lateral, então são ordenados pela segunda maior medida lateral.
	 * 
	 * @return Comparador de tamanhos.
	 */
	public static Comparator<Size> getComparatorSizes() {
		return COMPARATOR_SIZES;
	}
}
