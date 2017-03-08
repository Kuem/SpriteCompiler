package com.thiagomv.spritecompiler.factories;

import java.util.Map;

import com.kuemsoftwares.util.commons.base.BaseBusiness;
import com.kuemsoftwares.util.commons.base.BaseBusinessImpl;
import com.kuemsoftwares.util.commons.base.BaseFactory;
import com.thiagomv.spritecompiler.business.GeometryBusiness;
import com.thiagomv.spritecompiler.business.ImageBusiness;
import com.thiagomv.spritecompiler.business.OtimizacaoBusiness;
import com.thiagomv.spritecompiler.business.SpriteBusiness;
import com.thiagomv.spritecompiler.business.impl.GeometryBusinessImpl;
import com.thiagomv.spritecompiler.business.impl.ImageBusinessImpl;
import com.thiagomv.spritecompiler.business.impl.OtimizacaoBusinessImpl;
import com.thiagomv.spritecompiler.business.impl.SpriteBusinessImpl;

/**
 * Factory do pacote business.
 * 
 * @author Thiago Mendes Vieira - thiagomv.0301.developer@gmail.com
 */
public class BusinessFactory extends BaseFactory<BaseBusiness, BaseBusinessImpl> {
	private static final BusinessFactory factoryInstance = new BusinessFactory();

	private BusinessFactory() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void mapearClasses(Map<Class<? extends BaseBusiness>, Class<? extends BaseBusinessImpl>> map) {
		map.put(SpriteBusiness.class, SpriteBusinessImpl.class);
		map.put(OtimizacaoBusiness.class, OtimizacaoBusinessImpl.class);
		map.put(ImageBusiness.class, ImageBusinessImpl.class);
		map.put(GeometryBusiness.class, GeometryBusinessImpl.class);
	}

	/**
	 * Obtém a instância da implementação de determinada interface de business.
	 * 
	 * @param clazz
	 *            Classe da interface
	 * @return Instância da implementação da interface.
	 */
	public static <T extends BaseBusiness> T getBusinessInstance(Class<T> clazz) {
		return factoryInstance.getInstance(clazz);
	}
}
