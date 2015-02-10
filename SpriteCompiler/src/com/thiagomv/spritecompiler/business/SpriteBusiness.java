package com.thiagomv.spritecompiler.business;

import java.io.File;
import java.util.List;

import com.thiagomv.spritecompiler.commons.BaseBusiness;
import com.thiagomv.spritecompiler.data.FrameImage;
import com.thiagomv.spritecompiler.data.Sprite;

public interface SpriteBusiness extends BaseBusiness {

	/**
	 * Obt�m a lista de frames no diret�rio principal de um sprite.
	 * 
	 * @param rootSpriteDirectory
	 *            Diret�rio principal do sprite.
	 * @return Lista de {@link FrameImage}.
	 */
	List<FrameImage> loadFrames(File rootSpriteDirectory);

	/**
	 * Obt�m o sprite a partir do seu diret�rio principal.
	 * 
	 * @param rootSpriteDirectory
	 *            Diret�rio principal do sprite.
	 * @return {@link Sprite}.
	 */
	Sprite loadSprite(File rootSpriteDirectory);

	/**
	 * Salva um sprite em seu diret�rio principal.
	 * 
	 * @param sprite
	 *            Sprite.
	 * @param rootSpriteDirectory
	 *            Diret�rio principal do sprite.
	 * @return true se o sprite for salvo com sucesso, ou false, caso contr�rio.
	 */
	void saveSprite(Sprite sprite, File rootSpriteDirectory);

	/**
	 * Cria um sprite a partir de uma lista de frames. O arquivo de
	 * configura��es do sprite deve listar os frames na mesma ordem que aparecem
	 * na lista passado por par�metro.
	 * 
	 * @param frames
	 *            Lista de frames.
	 * @return {@link Sprite}.
	 */
	Sprite createSprite(List<FrameImage> frames);
}
