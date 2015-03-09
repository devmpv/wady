package com.mpv;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.GdxNativesLoader;
import com.mpv.data.Assets;
import com.mpv.data.GVars;
import com.mpv.data.Settings;
import com.mpv.game.IReqHandler;
import com.mpv.game.world.GameObj;
import com.mpv.screens.CreditsScreen;
import com.mpv.screens.GameScreen;
import com.mpv.screens.LevelScreen;
import com.mpv.screens.MenuScreen;
import com.mpv.screens.SplashScreen;

public class ApplicationHandler extends Game {

	public MenuScreen menuScreen;
	public GameScreen gameScreen;
	public LevelScreen levelScreen;
	public CreditsScreen creditsScreen;
	public static IReqHandler ExternalHandler;

	public ApplicationHandler(IReqHandler irh) {
		ApplicationHandler.ExternalHandler = irh;
	}

	public ApplicationHandler() {
	}

	@Override
	public void create() {
		// Loading native libraries
		GdxNativesLoader.load();

		// GVars initial setup
		GVars.app = this;
		GVars.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		this.setScreen(new SplashScreen());
		// Initialize configuration and resources
		Settings.load();
		Assets.load();

		// Custom buttons
		Gdx.input.setCatchBackKey(true);
		Gdx.input.setCatchMenuKey(true);

		GameObj.get();
		gameScreen = new GameScreen();
		menuScreen = new MenuScreen();
		creditsScreen = new CreditsScreen();
		levelScreen = new LevelScreen();
	}

	@Override
	public void dispose() {
		GVars.dispose();
		Assets.dispose();
		super.dispose();
		menuScreen.dispose();
		// scoresScreen.dispose();
		levelScreen.dispose();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}
}