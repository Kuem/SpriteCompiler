package com.thiagomv.spritecompiler.business;

import java.util.List;

import com.thiagomv.spritecompiler.commons.BaseBusiness;
import com.thiagomv.spritecompiler.data.Rectangle;
import com.thiagomv.spritecompiler.data.Size;

public interface OtimizacaoBusiness extends BaseBusiness {
	/**
	 * Obtém a lista de retângulos que estão contidos em uma região retangular
	 * comum. Os retângulos nesta lista não se sobrepõem e possuem as dimensões
	 * de cada região passada por parâmetro. A área retangular que contém os
	 * retângulos possui área mínima necessária para conter todas as regiões sem
	 * sobreposição entre elas.
	 * 
	 * @param sizes
	 *            Regiões retangulares que devem ser organizadas, sem
	 *            sobreposição, de forma que ocupem uma região retangular
	 *            mínima.
	 * @return Lista de retângulos.
	 */
	List<Rectangle> otimizarAreaRetangularComRegioesRetangularesSemSobreposicao(
			List<Size> sizes);
}
