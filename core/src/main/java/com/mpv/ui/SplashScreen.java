package com.mpv.ui;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Quad;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mpv.data.Assets;
import com.mpv.data.GVars;
import com.mpv.tween.SpriteAccessor;

public class SplashScreen implements Screen {
    private float w, h;
    private SpriteBatch spriteBatch;
    private Sprite splash;
    private TweenManager tweenManager;
    Texture texture;

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(255, 255, 255, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tweenManager.update(delta);
        spriteBatch.begin();
        splash.draw(spriteBatch);
        spriteBatch.end();
        if (tweenManager.getRunningTweensCount() == 0 || Gdx.input.justTouched())
            GVars.app.setScreen(new LogoScreen());
    }

    @Override
    public void resize(int width, int height) {
        // No-op
    }

    @Override
    public void show() {
        tweenManager = new TweenManager();
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
        texture = new Texture(Gdx.files.internal("data/logo.png"));
        texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        spriteBatch = new SpriteBatch();
        splash = new Sprite(texture);
        splash.setSize(w / 2, w / 2);
        splash.setPosition(w / 2 - w / 4, h / 2 - w / 4);
        splash.setOrigin(w / 4, w / 4);
        // Fade && Rotate
        Tween.set(splash, SpriteAccessor.ALPHA).target(0).start(tweenManager);
        Tween.set(splash, SpriteAccessor.SCALE).target(0).start(tweenManager);
        Tween.to(splash, SpriteAccessor.ALPHA, 2).target(1).delay(2).start(tweenManager);
        Tween.to(splash, SpriteAccessor.SCALE, 2).target(1).delay(2).ease(Quad.INOUT).start(tweenManager);
        Tween.to(splash, SpriteAccessor.ALPHA, 2).target(0).delay(6).start(tweenManager);
        Assets.load();
    }

    @Override
    public void hide() {
        tweenManager.killAll();
        spriteBatch.dispose();
        texture.dispose();
    }

    @Override
    public void pause() {
        // No-op
    }

    @Override
    public void resume() {
        // No-op
    }

    @Override
    public void dispose() {
        // No-op
    }

}
