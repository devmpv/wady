package com.mpv.data;

import aurelienribon.tweenengine.TweenManager;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mpv.game.ApplicationHandler;
import com.mpv.game.players.Player;

public class GVars {

	//Screen parameters
	public static float scrWidth=1;
	public static float scrHeight=1;
	//Scale
	public static float BOX_TO_WORLD; //= widthInPixels/widthInMeters;
	public static float WORLD_TO_BOX; //= 1/BOX_TO_WORLD;
	//Global items	
	public static ApplicationHandler app;
	public static OrthographicCamera frCam;
	public static OrthographicCamera bgCam;
	public static World world;
	public static RayHandler rayHandler;
	public static PointLight pointLight;
	//Tweens
	public static TweenManager tweenManager;
	//Player
	public static Player activePlayer = null;
	//Gameplay
	public static int gameTimeSec;
	public static int gameTimeMin;
	public static OrthogonalTiledMapRenderer otmRendered;
	public static SpriteBatch spriteBatch = new SpriteBatch();
	
	public static void resize(float width, float height) {
		scrWidth = width;
		scrHeight = height;
		
		if (frCam!=null) {
			frCam.viewportWidth = width;
			frCam.viewportHeight = height;
		} else {
			GVars.frCam = new OrthographicCamera(scrWidth, scrHeight);
		}
		if (bgCam!=null) {
			bgCam.viewportWidth = width;
			bgCam.viewportHeight = height;
		} else {
			GVars.bgCam = new OrthographicCamera(scrWidth, scrHeight);
		}
		//Scale
		BOX_TO_WORLD = scrWidth/Const.widthInMeters;
		WORLD_TO_BOX = 1/BOX_TO_WORLD;
	}
	public static void dispose () {
		if (otmRendered !=null) otmRendered.dispose();
		spriteBatch.dispose();
		if (world != null) world.dispose();
		if (rayHandler != null ) rayHandler.dispose();
		world = null;
		frCam = null;
		bgCam = null;
		tweenManager = null;
		activePlayer = null;
	}
	
}
