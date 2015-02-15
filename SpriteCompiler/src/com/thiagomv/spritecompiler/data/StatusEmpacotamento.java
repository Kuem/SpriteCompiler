package com.thiagomv.spritecompiler.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
public class StatusEmpacotamento implements Cloneable {
	/**
	 * Região retangular do recipiente que contém as regiões retangulares.
	 */
	public Rectangle2D recipiente;

	/**
	 * Pontos de âncoras no recipiente.
	 */
	public Set<PontoAncora> ancoras;

	/**
	 * Regiões do recipiente que já estão ocupadas.
	 */
	public List<Rectangle2D> regioesOcupadas;

	/**
	 * Dimensões das regiões retangulares que ainda não estão definidas no
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
