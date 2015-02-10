package com.thiagomv.spritecompiler.repository;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import com.thiagomv.spritecompiler.commons.BaseRepositoryImpl;
import com.thiagomv.spritecompiler.commons.RepositoryException;
import com.thiagomv.spritecompiler.data.FrameImage;
import com.thiagomv.spritecompiler.data.Sprite;
import com.thiagomv.spritecompiler.data.SpriteSettings;

/**
 * Implementação da interface {@link SpriteRepositoryImpl}.
 * 
 * @author Thiago Mendes Vieira
 * 
 *         05/02/2015
 */
public class SpriteRepositoryImpl extends BaseRepositoryImpl implements
		SpriteRepository {

	private static final List<String> VALID_FORMAT_FRAME_FILE_ENDS = Arrays
			.asList(".BMP", ".PNG");

	private static final String DIRECTORY_SPRITE = "sprite";

	private static final String SPRITE_IMAGE_FILE_FORMAT_NAME = "PNG";
	private static final String SPRITE_IMAGE_FILE_FORMAT_ENDS = ".PNG";
	private static final String SPRITE_SETTINGS_FILE_FORMAT_ENDS = ".STT";

	/** {@inheritDoc} */
	@Override
	public List<FrameImage> loadFrames(File rootSpriteDirectory) {
		List<FrameImage> lista = new ArrayList<>();

		List<File> filesFrame = getFramesFiles(rootSpriteDirectory);
		for (File file : filesFrame) {
			lista.add(loadImageFromFile(file));
		}

		return lista;
	}

	/**
	 * Obtém a lista de arquivos dos frames de um Sprite. A estrutura de pasta
	 * de um Sprite é definida de tal forma que todos os frames que compõe um
	 * sprite ficam separados em arquivos de imagens distintos no diretório
	 * principal do sprite. A imagem final do sprite, assim como seu arquivo de
	 * configuração, ficam em um sub-diretório chamado gen.
	 * 
	 * @param rootSpriteDirectory
	 *            Diretório principal do sprite.
	 * @return Lista de arquivos de frames que formam o sprite.
	 */
	private List<File> getFramesFiles(File rootSpriteDirectory) {
		File[] files = rootSpriteDirectory.listFiles();
		List<File> lista = new ArrayList<File>();

		for (File file : files) {
			if (file.isFile() && file.canRead()) {
				if (isValidFrameFile(file)) {
					lista.add(file);
				}
			}
		}
		return lista;
	}

	/**
	 * Verifica se o arquivo é um arquivo de frame de sprite válido.
	 * 
	 * @param file
	 *            Arquivo.
	 * @return true se o arquivo for válido, ou false, caso contrário.
	 */
	private boolean isValidFrameFile(File file) {
		String name = file.getName().toUpperCase();
		for (String format : VALID_FORMAT_FRAME_FILE_ENDS) {
			if (name.endsWith(format.toUpperCase())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Obtém uma imagem a partir de um arquivo.
	 * 
	 * @param file
	 *            Arquivo.
	 * @return {@link FrameImage}.
	 */
	private FrameImage loadImageFromFile(File file) {
		FrameImage frame = new FrameImage();
		try {
			frame.setBufferedImage(ImageIO.read(file));
		} catch (IOException e) {
			throw new RepositoryException(e);
		}
		return frame;
	}

	/** {@inheritDoc} */
	@Override
	public Sprite loadSprite(File rootSpriteDirectory) {
		Sprite sprite = new Sprite();
		sprite.setImage(loadImageFromFile(getSpriteImageFile(rootSpriteDirectory)));
		sprite.setSettings(loadSpriteSettingsFromFile(getSpriteSettingsFile(rootSpriteDirectory)));
		return sprite;
	}

	/**
	 * Obtém a localização do diretório do sprite.
	 * 
	 * @param rootSpriteDirectory
	 *            Diretório principal do sprite.
	 * @return {@link File}.
	 */
	private File getSpriteDirectory(File rootSpriteDirectory) {
		return new File(rootSpriteDirectory.getAbsolutePath() + File.separator
				+ DIRECTORY_SPRITE);
	}

	/**
	 * Obtém a localização do arquivo de imagem do sprite.
	 * 
	 * @param rootSpriteDirectory
	 *            Diretório principal do sprite.
	 * @return {@link File}.
	 */
	private File getSpriteImageFile(File rootSpriteDirectory) {
		return new File(getSpriteDirectory(rootSpriteDirectory)
				.getAbsolutePath()
				+ File.separator
				+ rootSpriteDirectory.getName() + SPRITE_IMAGE_FILE_FORMAT_ENDS);
	}

	/**
	 * Obtém a localização do arquivo de configurações do sprite.
	 * 
	 * @param rootSpriteDirectory
	 *            Diretório principal do sprite.
	 * @return {@link File}.
	 */
	private File getSpriteSettingsFile(File rootSpriteDirectory) {
		return new File(getSpriteDirectory(rootSpriteDirectory)
				.getAbsolutePath()
				+ File.separator
				+ rootSpriteDirectory.getName()
				+ SPRITE_SETTINGS_FILE_FORMAT_ENDS);
	}

	/**
	 * Obtém as configurações de um sprite a partir de um arquivo.
	 * 
	 * @param settingsFile
	 * @return
	 */
	private SpriteSettings loadSpriteSettingsFromFile(File settingsFile) {
		SpriteSettings settings = null;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(settingsFile));
			settings = (SpriteSettings) ois.readObject();
		} catch (FileNotFoundException e) {
			throw new RepositoryException(e);
		} catch (IOException e) {
			throw new RepositoryException(e);
		} catch (ClassNotFoundException e) {
			throw new RepositoryException(e);
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					throw new RepositoryException(e);
				}
			}
		}
		return settings;
	}

	/** {@inheritDoc} */
	@Override
	public void saveSprite(Sprite sprite, File rootSpriteDirectory) {
		createSpriteDirectoryIfNotExist(rootSpriteDirectory);

		RenderedImage image = sprite.getImage().getBufferedImage();
		File imageFile = getSpriteImageFile(rootSpriteDirectory);
		saveImage(image, imageFile);

		SpriteSettings settings = sprite.getSettings();
		File settingsFile = getSpriteSettingsFile(rootSpriteDirectory);
		saveSpriteSettings(settings, settingsFile);
	}

	/**
	 * Cria o diretório de sprite caso ele não exista.
	 * 
	 * @param rootSpriteDirectory
	 *            Diretório principal do sprite.
	 */
	private void createSpriteDirectoryIfNotExist(File rootSpriteDirectory) {
		File spriteDirectory = getSpriteDirectory(rootSpriteDirectory);
		if (!spriteDirectory.exists()) {
			spriteDirectory.mkdir();
		}
	}

	/**
	 * Salva uma imagem em um arquivo.
	 * 
	 * @param image
	 *            Imagem.
	 * @param file
	 *            Arquivo.
	 */
	private void saveImage(RenderedImage image, File file) {
		if (file.exists()) {
			file.delete();
		}

		try {
			ImageIO.write(image, SPRITE_IMAGE_FILE_FORMAT_NAME, file);
		} catch (IOException e) {
			throw new RepositoryException(e);
		}
	}

	/**
	 * Salva as configurações de um sprite em um arquivo.
	 * 
	 * @param settings
	 *            {@link SpriteSettings}.
	 * @param file
	 *            [@link File}.
	 */
	private void saveSpriteSettings(SpriteSettings settings, File file) {
		if (file.exists()) {
			file.delete();
		}

		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(settings);
		} catch (FileNotFoundException e) {
			throw new RepositoryException(e);
		} catch (IOException e) {
			throw new RepositoryException(e);
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					throw new RepositoryException(e);
				}
			}
		}
	}
}
