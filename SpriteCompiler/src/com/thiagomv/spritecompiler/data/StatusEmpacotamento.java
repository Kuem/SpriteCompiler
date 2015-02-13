package com.thiagomv.spritecompiler.data;

import java.util.List;

/**
 * Define o status de um empacotamento 2D. Define-se como empacotamento o
 * processo de organizar certa quantidade de regiões retangulares em uma região
 * retangular maior. Este classe armazena informações sobre algum processamento
 * de empacotamento 2D.
 * 
 * @author Thiago Mendes Vieira
 * 
 *         12/02/2015
 */
public class StatusEmpacotamento {
	/**
	 * Região retangular do recipiente que contém as regiões retangulares.
	 */
	public Rectangle2D recipiente;

	/**
	 * Pontos de âncoras no recipiente.
	 */
	public List<PontoAncora> ancoras;

	/**
	 * Regiões do recipiente que já estão ocupadas.
	 */
	public List<Rectangle2D> regioesOcupadas;

	/**
	 * Dimensões das regiões retangulares que ainda não estão definidas no
	 * recipiente.
	 */
	public List<Size> pendencia;
}
