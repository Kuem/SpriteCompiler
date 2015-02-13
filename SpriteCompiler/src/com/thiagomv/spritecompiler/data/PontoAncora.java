package com.thiagomv.spritecompiler.data;

import com.thiagomv.spritecompiler.enums.IndicadorLocalRetangulo;

/**
 * Define um ponto âncora. Um ponto âncora auxilia a inserção de retângulos ao
 * plano bidimensional, indicando qual parte do retângulo pode ser anexada a
 * determinado ponto do plano.
 * 
 * @author Thiago Mendes Vieira
 * 
 *         12/02/2015
 */
public class PontoAncora {
	/**
	 * Ponto âncora do plano bidimensional.
	 */
	Point2D ponto;

	/**
	 * Local do retângulo que pode ser anexado a este ponto.
	 */
	IndicadorLocalRetangulo localRetangulo;
}
