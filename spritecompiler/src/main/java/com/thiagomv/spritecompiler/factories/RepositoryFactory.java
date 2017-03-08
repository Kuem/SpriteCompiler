package com.thiagomv.spritecompiler.factories;

import java.util.Map;

import com.kuemsoftwares.util.commons.base.BaseFactory;
import com.kuemsoftwares.util.commons.base.BaseRepository;
import com.kuemsoftwares.util.commons.base.BaseRepositoryImpl;
import com.thiagomv.spritecompiler.repository.SpriteRepository;
import com.thiagomv.spritecompiler.repository.impl.SpriteRepositoryImpl;

/**
 * Factory do pacote repository.
 * 
 * @author Thiago Mendes Vieira - thiagomv.0301.developer@gmail.com
 */
public class RepositoryFactory extends BaseFactory<BaseRepository, BaseRepositoryImpl> {
	private static final RepositoryFactory factoryInstance = new RepositoryFactory();

	private RepositoryFactory() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void mapearClasses(Map<Class<? extends BaseRepository>, Class<? extends BaseRepositoryImpl>> map) {
		map.put(SpriteRepository.class, SpriteRepositoryImpl.class);
	}

	/**
	 * Obtém a instância da implementação de determinada interface de
	 * repositório.
	 * 
	 * @param clazz
	 *            Classe da interface
	 * @return Instância da implementação da interface.
	 */
	public static <T extends BaseRepository> T getRepositoryInstance(Class<T> clazz) {
		return factoryInstance.getInstance(clazz);
	}
}