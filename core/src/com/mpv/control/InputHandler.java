package com.mpv.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.mpv.data.GVars;
import com.mpv.game.ApplicationHandler;

public class InputHandler implements InputProcessor {

	ApplicationHandler app;
	
	public InputHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		if (keycode == Keys.BACK) {
			Gdx.app.exit();
			//GVars.app.setScreen(GVars.app.menuScreen);
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		float newZoom = GVars.cam.zoom + amount*0.3f;
		if (newZoom < 4f && newZoom > 1f) {
			GVars.cam.zoom = newZoom;
		}
		return false;
	}

}
