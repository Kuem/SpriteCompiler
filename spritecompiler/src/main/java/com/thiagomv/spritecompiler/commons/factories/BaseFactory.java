package com.thiagomv.spritecompiler.commons.factories;

import java.util.HashMap;
import java.util.Map;

import com.thiagomv.spritecompiler.commons.exceptions.ArchitectureException;

/**
 * Definição de base para Factories que fazem o mapeamento um-para-um de classes
 * de implementação para cada classe de interface.
 * 
 * @author Thiago Mendes Vieira
 * 
 *         08/02/2015
 * @param <A>
 *            Classe base de Interface.
 * @param <B>
 *            Classe base de Implementação.
 */
abstract class BaseFactory<A, B extends A> {
	protected final Map<Class<? extends A>, Class<? extends B>> mapClasses = new HashMap<>();
	private final Map<Class<? extends A>, B> mapInstances = new HashMap<>();

	protected BaseFactory() {
		mapearClasses(mapClasses);
	}

	/**
	 * Reliza o mapeamento de classes de interfaces e suas implementações.
	 * 
	 * @param map
	 */
	protected abstract void mapearClasses(
			Map<Class<? extends A>, Class<? extends B>> map);

	/**
	 * Obtém a implementação de uma interface.
	 * 
	 * @param clazz
	 *            Classe da interface.
	 * @return Implementação da interface.
	 */
	@SuppressWarnings("unchecked")
	protected final <T extends A> T getInstance(Class<T> clazz) {
		T instance = (T) mapInstances.get(clazz);
		if (instance == null) {
			B impl;
			try {
				impl = (B) mapClasses.get(clazz).newInstance();
			} catch (InstantiationException e) {
				throw new ArchitectureException(e);
			} catch (IllegalAccessException e) {
				throw new ArchitectureException(e);
			}
			mapInstances.put(clazz, impl);
			instance = (T) impl;
		}
		return instance;
	}

}
