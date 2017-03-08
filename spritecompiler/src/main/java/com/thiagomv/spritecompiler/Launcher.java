package com.thiagomv.spritecompiler;

import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import com.thiagomv.spritecompiler.business.SpriteBusiness;
import com.thiagomv.spritecompiler.factories.BusinessFactory;
import com.thiagomv.spritecompiler.model.Image;
import com.thiagomv.spritecompiler.model.Sprite;

public class Launcher {
	private static final File CURRENT_DIR = new File(
			System.getProperty("user.dir"));

	private static final SpriteBusiness spriteBusiness = BusinessFactory
			.getBusinessInstance(SpriteBusiness.class);

	private static final String DESCRIPTION = "Diret�rios principais de Sprites.";

	private static final FileFilter DIR_FILTER = new FileFilter() {

		/** {@inheritDoc} */
		@Override
		public String getDescription() {
			return DESCRIPTION;
		}

		/** {@inheritDoc} */
		@Override
		public boolean accept(File f) {
			return f.isDirectory();
		}
	};

	public static void main(String[] args) {
		File rootSpriteDirectory = obterRootDir();
		if (rootSpriteDirectory == null) {
			JOptionPane.showMessageDialog(null,
					"Nenhum diret�rio selecionado! O programa ser� fechado.");
			System.exit(0);
		}

		criarSprite(rootSpriteDirectory);
	}

	private static File obterRootDir() {
		JFileChooser chooser = new JFileChooser(CURRENT_DIR);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setFileFilter(DIR_FILTER);
		int returnVal = chooser.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile();
		}

		return null;
	}

	private static void criarSprite(File rootSpriteDirectory) {
		List<Image> frames = spriteBusiness
				.loadFrames(rootSpriteDirectory);
		Sprite sprite = spriteBusiness.createSprite(frames, 1);

		if (sprite != null) {
			spriteBusiness.saveSprite(sprite, rootSpriteDirectory);
			System.out.print("Sprite criado com sucesso!");
		} else {
			System.out.print("N�o foi poss�vel criar Sprite!");
		}
	}
}