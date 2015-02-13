package com.thiagomv.spritecompiler.data;

import com.thiagomv.spritecompiler.enums.IndicadorLocalRetangulo;

/**
 * Define um ponto �ncora. Um ponto �ncora auxilia a inser��o de ret�ngulos ao
 * plano bidimensional, indicando qual parte do ret�ngulo pode ser anexada a
 * determinado ponto do plano.
 * 
 * @author Thiago Mendes Vieira
 * 
 *         12/02/2015
 */
public class PontoAncora {
	/**
	 * Ponto �ncora do plano bidimensional.
	 */
	Point2D ponto;

	/**
	 * Local do ret�ngulo que pode ser anexado a este ponto.
	 */
	IndicadorLocalRetangulo localRetangulo;
}
