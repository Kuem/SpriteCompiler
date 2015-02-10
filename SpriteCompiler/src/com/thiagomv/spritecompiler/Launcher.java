package com.thiagomv.spritecompiler;

import java.io.File;
import java.util.List;

import com.thiagomv.spritecompiler.business.SpriteBusiness;
import com.thiagomv.spritecompiler.commons.BusinessFactory;
import com.thiagomv.spritecompiler.data.FrameImage;
import com.thiagomv.spritecompiler.data.Sprite;

public class Launcher {
	private static final String finalDir = "teste2";
	private static final String dir = "C:\\Users\\Thiago\\Desktop\\SPRITES\\"
			+ finalDir;

	private static final SpriteBusiness spriteBusiness = BusinessFactory
			.getBusinessInstance(SpriteBusiness.class);

	public static void main(String[] args) {
		File rootSpriteDirectory = new File(dir);

		List<FrameImage> frames = spriteBusiness
				.loadFrames(rootSpriteDirectory);
		Sprite sprite = spriteBusiness.createSprite(frames);
		spriteBusiness.saveSprite(sprite, rootSpriteDirectory);

		System.out.print("Sprite criado com sucesso!");
	}
}
