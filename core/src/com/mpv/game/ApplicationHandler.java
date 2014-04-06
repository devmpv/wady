package com.mpv.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.GdxNativesLoader;
import com.mpv.data.Assets;
import com.mpv.data.GVars;
import com.mpv.screens.GameScreen;
import com.mpv.screens.MenuScreen;

public class ApplicationHandler extends Game {

	public MenuScreen menuScreen;
	public GameScreen gameScreen;
	public GameObject gameObject;
	
	public static IReqHandler ExternalHandler;
	
	public ApplicationHandler(IReqHandler irh) {
		ApplicationHandler.ExternalHandler = irh;
	}
	public ApplicationHandler() {
		
	}
	@Override
	public void create() {
		//Loading native libraries
		GdxNativesLoader.load();
		
		//Initialize configuration and resources
		Assets.Load();
		//
		Gdx.input.setCatchBackKey(true);
		Gdx.input.setCatchMenuKey(true);
		
		GVars.app = this;
		GVars.resize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		gameObject = new GameObject();		
		gameScreen = new GameScreen();
		menuScreen = new MenuScreen();
		//Sleep for 2 seconds
		this.setScreen(gameScreen);

	}
	@Override
	public void dispose() {		
		GVars.dispose();
		Assets.dispose();
		super.dispose();
		menuScreen.dispose();		
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		super.pause();
	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		super.resume();
	}
	@Override
	public void render() {
		// TODO Auto-generated method stub
		super.render();
	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		super.resize(width, height);
	}
}