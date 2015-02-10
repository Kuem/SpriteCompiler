package com.thiagomv.spritecompiler.commons;

/**
 * Exceção lançada no pacote repository.
 * 
 * @author Thiago Mendes Vieira
 * 
 *         05/02/2015
 */
public class RepositoryException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RepositoryException(Exception e) {
		super(e);
	}
}
