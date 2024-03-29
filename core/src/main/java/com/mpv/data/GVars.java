package com.mpv.data;

import aurelienribon.tweenengine.TweenManager;
import box2dLight.ConeLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.World;
import com.mpv.GameStarter;
import com.mpv.game.actors.Player;
import com.mpv.game.world.GameObj;
import com.mpv.game.world.GameTimer;

public class GVars {

    // Screen parameters
    public static float scrWidth = 1;
    public static float scrHeight = 1;
    // Scale
    public static float BOX_TO_WORLD; // = widthInPixels/widthInMeters;
    public static float WORLD_TO_BOX; // = 1/BOX_TO_WORLD;
    // Global items
    public static float widthInMeters;
    public static float heightInMeters;
    public static GameStarter app;
    public static OrthographicCamera frCam;
    public static OrthographicCamera bgCam;
    public static World world;
    public static RayHandler rayHandler;
    public static ConeLight playerLight;
    public static ConeLight sceneryLight;
    // Tweens
    public static TweenManager tweenManager;
    // Player
    public static OrthogonalTiledMapRenderer otmRendered;
    public static SpriteBatch spriteBatch = new SpriteBatch();
    private static Matrix4 camLight;

    public static void resize(float width, float height) {
        scrWidth = width;
        scrHeight = height;

        if (frCam != null) {
            frCam.viewportWidth = width;
            frCam.viewportHeight = height;
        } else {
            GVars.frCam = new OrthographicCamera(scrWidth, scrHeight);
        }

        if (bgCam != null) {
            bgCam.viewportWidth = width;
            bgCam.viewportHeight = height;
        } else {
            GVars.bgCam = new OrthographicCamera(scrWidth, scrHeight);
        }
        // Scale
        BOX_TO_WORLD = scrWidth / Const.VIEWPORT_METERS;
        WORLD_TO_BOX = 1 / BOX_TO_WORLD;
    }

    public static void dispose() {
        if (otmRendered != null)
            otmRendered.dispose();
        spriteBatch.dispose();
        if (world != null)
            world.dispose();
        if (rayHandler != null)
            rayHandler.dispose();
        world = null;
        frCam = null;
        bgCam = null;
        tweenManager = null;
    }

    public static void update() {
        // Calculating cam position to not overlap map borders
        frCam.position.set(
                Math.min(Math.max(MathUtils.round(Player.get().getX()), (int) scrWidth / 2), Assets.mapScaledWidth
                        - (int) scrWidth / 2),
                Math.min(Math.max(MathUtils.round(Player.get().getY()), (int) scrHeight / 2), Assets.mapScaledHeight
                        - (int) scrHeight / 2), 0);
        frCam.update();
        // Dim light on low battery :)
        int sec = GameTimer.get().getLeftSec();
        if (sec < 10) {
            playerLight.setDistance(Const.VIEWPORT_METERS * 0.1f * sec);
        } else {
            playerLight.setDistance(Const.VIEWPORT_METERS);
        }

        camLight = new Matrix4(frCam.combined);
        rayHandler.setCombinedMatrix(camLight.scl(BOX_TO_WORLD));
        GameObj.get().clearBodies();
    }
}
