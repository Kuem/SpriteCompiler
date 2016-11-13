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
public class PontoAncora extends Point2D {
	/**
	 * Local do retângulo que pode ser anexado a este ponto.
	 */
	IndicadorLocalRetangulo localRetangulo;

	/**
	 * 
	 * @param x
	 *            Valor de x.
	 * @param y
	 *            Valor de y.
	 * @param localRetangulo
	 *            Local do retângulo que pode ser anexado a este ponto.
	 */
	public PontoAncora(int x, int y, IndicadorLocalRetangulo localRetangulo) {
		super(x, y);
		this.localRetangulo = localRetangulo;
	}

	/**
	 * Obtém o local do retângulo que pode ser anexado a este ponto.
	 * 
	 * @return {@link IndicadorLocalRetangulo}.
	 */
	public IndicadorLocalRetangulo getLocalRetangulo() {
		return localRetangulo;
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((localRetangulo == null) ? 0 : localRetangulo.hashCode());
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof PontoAncora))
			return false;
		PontoAncora other = (PontoAncora) obj;
		if (localRetangulo != other.localRetangulo)
			return false;
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(super.toString());
		builder.append(" - LocalRetângulo: ")
				.append(this.localRetangulo.name());
		return builder.toString();
	}

}
