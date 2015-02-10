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
}
