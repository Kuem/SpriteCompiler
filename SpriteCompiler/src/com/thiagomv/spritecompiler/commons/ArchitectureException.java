package com.thiagomv.spritecompiler.commons;

/**
 * Exce��o de arquitetura do sistema.
 * 
 * @author Thiago Mendes Vieira
 * 
 *         08/02/2015
 */
public class ArchitectureException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ArchitectureException(Exception e) {
		super(e);
	}
}
