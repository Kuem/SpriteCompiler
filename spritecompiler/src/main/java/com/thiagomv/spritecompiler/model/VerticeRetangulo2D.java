package com.thiagomv.spritecompiler.model;

/**
 * Define um vértice de retângulo.
 * 
 * @author Thiago Mendes Vieira - thiagomv.0301.developer@gmail.com
 */
public class VerticeRetangulo2D extends Point2D {
	/**
	 * Indicador de tipo de vértice.
	 */
	IndicadorVerticeRetangulo2D indicadorVertice;

	/**
	 * Cria um vértice de retângulo informando sua localização e o tipo do
	 * vértice.
	 * 
	 * @param x
	 *            Valor de x.
	 * @param y
	 *            Valor de y.
	 * @param indicadorVertice
	 *            Indicador de tipo de vértice.
	 */
	public VerticeRetangulo2D(int x, int y, IndicadorVerticeRetangulo2D indicadorVertice) {
		super(x, y);
		this.indicadorVertice = indicadorVertice;
	}

	/**
	 * Obtém o indicador de vértice.
	 * 
	 * @return {@link IndicadorVerticeRetangulo2D}.
	 */
	public IndicadorVerticeRetangulo2D getLocalRetangulo() {
		return indicadorVertice;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((indicadorVertice == null) ? 0 : indicadorVertice.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof VerticeRetangulo2D)) {
			return false;
		}
		VerticeRetangulo2D other = (VerticeRetangulo2D) obj;
		if (indicadorVertice != other.indicadorVertice) {
			return false;
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("VerticeRetangulo2D [indicadorVertice=");
		builder.append(indicadorVertice);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
