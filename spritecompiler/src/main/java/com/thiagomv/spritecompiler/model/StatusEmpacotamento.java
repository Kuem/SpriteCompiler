package com.thiagomv.spritecompiler.model;

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
 * @author Thiago Mendes Vieira - thiagomv.0301.developer@gmail.com
 */
public class StatusEmpacotamento implements Cloneable {
	/**
	 * Região retangular do recipiente que contém as regiões retangulares
	 * menores.
	 */
	public Rectangle2D recipiente;

	/**
	 * Pontos de vértices disponíveis no recipiente. Estes são pontos onde pode
	 * ser posicionado determinado tipo de vértice de retângulo.
	 */
	public Set<VerticeRetangulo2D> verticesDisponiveis;

	/**
	 * Regiões do recipiente que já estão ocupadas.
	 */
	public List<Rectangle2D> regioesOcupadas;

	/**
	 * Dimensões das regiões retangulares que ainda não estão definidas no
	 * recipiente.
	 */
	public List<Size> pendencia;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		StatusEmpacotamento novo = new StatusEmpacotamento();
		novo.recipiente = new Rectangle2D(this.recipiente);
		novo.verticesDisponiveis = new HashSet<>(this.verticesDisponiveis);
		novo.regioesOcupadas = new ArrayList<>(regioesOcupadas);
		novo.pendencia = new ArrayList<>(pendencia);
		return novo;
	}

}
