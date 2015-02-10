package com.thiagomv.spritecompiler.repository;

import java.io.File;
import java.util.List;

import com.thiagomv.spritecompiler.commons.BaseRepository;
import com.thiagomv.spritecompiler.data.FrameImage;
import com.thiagomv.spritecompiler.data.Sprite;

/**
 * Consultas relacionadas a {@link Sprite}.
 * 
 * @author Thiago Mendes Vieira
 * 
 *         05/02/2015
 */
public interface SpriteRepository extends BaseRepository {

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
}
