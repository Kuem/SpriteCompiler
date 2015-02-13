package com.thiagomv.spritecompiler.data;

import java.util.List;

/**
 * Define o status de um empacotamento 2D. Define-se como empacotamento o
 * processo de organizar certa quantidade de regi�es retangulares em uma regi�o
 * retangular maior. Este classe armazena informa��es sobre algum processamento
 * de empacotamento 2D.
 * 
 * @author Thiago Mendes Vieira
 * 
 *         12/02/2015
 */
public class StatusEmpacotamento {
	/**
	 * Regi�o retangular do recipiente que cont�m as regi�es retangulares.
	 */
	public Rectangle2D recipiente;

	/**
	 * Pontos de �ncoras no recipiente.
	 */
	public List<PontoAncora> ancoras;

	/**
	 * Regi�es do recipiente que j� est�o ocupadas.
	 */
	public List<Rectangle2D> regioesOcupadas;

	/**
	 * Dimens�es das regi�es retangulares que ainda n�o est�o definidas no
	 * recipiente.
	 */
	public List<Size> pendencia;
}
