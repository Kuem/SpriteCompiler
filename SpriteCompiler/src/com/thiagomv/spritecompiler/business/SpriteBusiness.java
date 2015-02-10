package com.thiagomv.spritecompiler.business;

import java.io.File;
import java.util.List;

import com.thiagomv.spritecompiler.commons.BaseBusiness;
import com.thiagomv.spritecompiler.data.FrameImage;
import com.thiagomv.spritecompiler.data.Sprite;

public interface SpriteBusiness extends BaseBusiness {

	/**
	 * Obtém a lista de frames no diretório principal de um sprite.
	 * 
	 * @param rootSpriteDirectory
	 *            Diretório principal do sprite.
	 * @return Lista de {@link FrameImage}.
	 */
	List<FrameImage> loadFrames(File rootSpriteDirectory);

	/**
	 * Obtém o sprite a partir do seu diretório principal.
	 * 
	 * @param rootSpriteDirectory
	 *            Diretório principal do sprite.
	 * @return {@link Sprite}.
	 */
	Sprite loadSprite(File rootSpriteDirectory);

	/**
	 * Salva um sprite em seu diretório principal.
	 * 
	 * @param sprite
	 *            Sprite.
	 * @param rootSpriteDirectory
	 *            Diretório principal do sprite.
	 * @return true se o sprite for salvo com sucesso, ou false, caso contrário.
	 */
	void saveSprite(Sprite sprite, File rootSpriteDirectory);

	/**
	 * Cria um sprite a partir de uma lista de frames. O arquivo de
	 * configurações do sprite deve listar os frames na mesma ordem que aparecem
	 * na lista passado por parâmetro.
	 * 
	 * @param frames
	 *            Lista de frames.
	 * @return {@link Sprite}.
	 */
	Sprite createSprite(List<FrameImage> frames);
}
