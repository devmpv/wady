package com.mpv.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mpv.screens.stages.LevelStage;

public class LevelScreen implements Screen {

	private LevelStage levelStage;
	
	public LevelScreen() {
		levelStage = new LevelStage();
	}

	@Override
	public void render(float delta) {
		Gdx.gl20.glClearColor(0f, 0f, 0f, 1);    
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		levelStage.act(Gdx.graphics.getDeltaTime());
		levelStage.draw();
		Table.drawDebug(levelStage); // This is optional, but enables debug lines for tables.
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(levelStage);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		levelStage.dispose();
	}

}