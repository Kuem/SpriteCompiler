package com.thiagomv.spritecompiler.commons.factories;

import java.util.Map;

import com.thiagomv.spritecompiler.business.GeometryBusiness;
import com.thiagomv.spritecompiler.business.GeometryBusinessImpl;
import com.thiagomv.spritecompiler.business.ImageBusiness;
import com.thiagomv.spritecompiler.business.ImageBusinessImpl;
import com.thiagomv.spritecompiler.business.OtimizacaoBusiness;
import com.thiagomv.spritecompiler.business.OtimizacaoBusinessImpl;
import com.thiagomv.spritecompiler.business.SpriteBusiness;
import com.thiagomv.spritecompiler.business.SpriteBusinessImpl;
import com.thiagomv.spritecompiler.commons.bases.BaseBusiness;
import com.thiagomv.spritecompiler.commons.bases.BaseBusinessImpl;

/**
 * Factory do pacote business.
 * 
 * @author Thiago Mendes Vieira
 * 
 *         06/02/2015
 */
public class BusinessFactory extends
		BaseFactory<BaseBusiness, BaseBusinessImpl> {
	private static final BusinessFactory factoryInstance = new BusinessFactory();

	private BusinessFactory() {
		super();
	}

	/** {@inheritDoc} */
	@Override
	protected void mapearClasses(
			Map<Class<? extends BaseBusiness>, Class<? extends BaseBusinessImpl>> map) {
		map.put(SpriteBusiness.class, SpriteBusinessImpl.class);
		map.put(OtimizacaoBusiness.class, OtimizacaoBusinessImpl.class);
		map.put(ImageBusiness.class, ImageBusinessImpl.class);
		map.put(GeometryBusiness.class, GeometryBusinessImpl.class);
	}

	public static <T extends BaseBusiness> T getBusinessInstance(Class<T> clazz) {
		return factoryInstance.getInstance(clazz);
	}
}
