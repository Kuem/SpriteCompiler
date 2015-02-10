package com.thiagomv.spritecompiler.business;

import java.util.List;

import com.thiagomv.spritecompiler.commons.BaseBusiness;
import com.thiagomv.spritecompiler.data.Rectangle;
import com.thiagomv.spritecompiler.data.Size;

public interface OtimizacaoBusiness extends BaseBusiness {
	/**
	 * Obt�m a lista de ret�ngulos que est�o contidos em uma regi�o retangular
	 * comum. Os ret�ngulos nesta lista n�o se sobrep�em e possuem as dimens�es
	 * de cada regi�o passada por par�metro. A �rea retangular que cont�m os
	 * ret�ngulos possui �rea m�nima necess�ria para conter todas as regi�es sem
	 * sobreposi��o entre elas.
	 * 
	 * @param sizes
	 *            Regi�es retangulares que devem ser organizadas, sem
	 *            sobreposi��o, de forma que ocupem uma regi�o retangular
	 *            m�nima.
	 * @return Lista de ret�ngulos.
	 */
	List<Rectangle> otimizarAreaRetangularComRegioesRetangularesSemSobreposicao(
			List<Size> sizes);
}
