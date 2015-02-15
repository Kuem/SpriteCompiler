package com.thiagomv.spritecompiler.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
public class StatusEmpacotamento implements Cloneable {
	/**
	 * Regi�o retangular do recipiente que cont�m as regi�es retangulares.
	 */
	public Rectangle2D recipiente;

	/**
	 * Pontos de �ncoras no recipiente.
	 */
	public Set<PontoAncora> ancoras;

	/**
	 * Regi�es do recipiente que j� est�o ocupadas.
	 */
	public List<Rectangle2D> regioesOcupadas;

	/**
	 * Dimens�es das regi�es retangulares que ainda n�o est�o definidas no
	 * recipiente.
	 */
	public List<Size> pendencia;

	/** {@inheritDoc} */
	@Override
	public Object clone() throws CloneNotSupportedException {
		StatusEmpacotamento novo = new StatusEmpacotamento();
		novo.recipiente = (Rectangle2D) this.recipiente.clone();
		novo.ancoras = new HashSet<>(this.ancoras);
		novo.regioesOcupadas = new ArrayList<>(regioesOcupadas);
		novo.pendencia = new ArrayList<>(pendencia);
		return novo;
	}

}
