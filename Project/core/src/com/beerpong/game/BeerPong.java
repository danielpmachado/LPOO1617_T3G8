package com.beerpong.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.beerpong.game.Screens.PlayScreen;

public class BeerPong extends Game {

	private AssetManager assetManager;
	private SpriteBatch batch;


	@Override
	public void create () {
		assetManager = new AssetManager();
		batch = new SpriteBatch();

		setScreen(new PlayScreen(this));
	}

	public AssetManager getAssetManager(){
		return  assetManager;
	}

	public SpriteBatch getSpriteBatch(){return batch;}
}
