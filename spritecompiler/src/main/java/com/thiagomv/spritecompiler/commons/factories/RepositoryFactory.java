package com.thiagomv.spritecompiler.commons.factories;

import java.util.Map;

import com.thiagomv.spritecompiler.commons.bases.BaseRepository;
import com.thiagomv.spritecompiler.commons.bases.BaseRepositoryImpl;
import com.thiagomv.spritecompiler.repository.SpriteRepository;
import com.thiagomv.spritecompiler.repository.SpriteRepositoryImpl;

/**
 * Factory do pacote repository.
 * 
 * @author Thiago Mendes Vieira
 * 
 *         06/02/2015
 */
public class RepositoryFactory extends
		BaseFactory<BaseRepository, BaseRepositoryImpl> {
	private static final RepositoryFactory factoryInstance = new RepositoryFactory();

	private RepositoryFactory() {
		super();
	}

	/** {@inheritDoc} */
	@Override
	protected void mapearClasses(
			Map<Class<? extends BaseRepository>, Class<? extends BaseRepositoryImpl>> map) {
		map.put(SpriteRepository.class, SpriteRepositoryImpl.class);
	}

	public static <T extends BaseRepository> T getRepositoryInstance(
			Class<T> clazz) {
		return factoryInstance.getInstance(clazz);
	}
}