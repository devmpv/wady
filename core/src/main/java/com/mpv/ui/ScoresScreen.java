package com.mpv.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mpv.data.Assets;
import com.mpv.data.Const;
import com.mpv.data.Sounds;
import com.mpv.data.Sounds.ID;
import com.mpv.data.GVars;
import com.mpv.data.Settings;

public class ScoresScreen implements Screen {

    private final Stage stage;
    private final Label label1;
    private final Dialog nameDialog;
    private static final List<String> list = new List<>(Assets.skin);

    public ScoresScreen() {
        float buttonSize = Const.BLOCK_SIZE * GVars.BOX_TO_WORLD * 1.6f;
        float itemSize = Const.BLOCK_SIZE * GVars.BOX_TO_WORLD * 1.6f;
        stage = new Stage();
        label1 = new Label("Your score: ", Assets.skin, "default");
        Button okButton = new TextButton("OK", Assets.skin);
        Widget widget = new Widget();
        Table scoresTable = new Table();
        Table table1 = new Table();

        Image image = new Image(Assets.skin.getDrawable("menu-screen"));
        image.setSize(stage.getWidth(), stage.getWidth() / image.getWidth() * image.getHeight());
        image.setPosition(0, 0);
        stage.addActor(image);

        table1.setFillParent(true);

        table1.add(scoresTable).width(stage.getWidth() / 1.1f).row();
        // scoresTable.setFillParent(true);
        scoresTable.setBackground(Assets.skin.getDrawable("edit"));
        final TextButton buttonExit = new TextButton("Menu", Assets.skin, "default");
        final TextField textField = new TextField(Settings.name, Assets.skin);
        textField.setMaxLength(10);
        scoresTable.add(new Label("High scores", Assets.skin, "default")).row();
        scoresTable.add(widget).height(itemSize / 2).row();
        scoresTable.add(list).row();
        scoresTable.add(widget).height(itemSize).row();
        scoresTable.add(label1).height(itemSize / 2).row();
        table1.add(widget).height(itemSize / 4).row();
        table1.add(buttonExit).size(buttonSize * 1.6f, buttonSize);

        stage.addActor(table1);
        nameDialog = new Dialog("", Assets.skin, "default");
        nameDialog.getContentTable().add("Your name:").row();
        nameDialog.getContentTable().add(textField).height(itemSize).width(buttonSize * 3);
        nameDialog.getButtonTable().add(okButton).size(buttonSize);
        // nameDialog.button(okButton, true);
        okButton.addListener(new ClickListener() {
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                // super.touchDown(event, x, y, pointer, button);
                Sounds.play(ID.BUTTON);
                Settings.name = textField.getText();
                // Settings.addScore(Settings.name, GameApp.gameObject.getMoves());
                label1.setText("Your score: " + Settings.getTotalScore());
                getScoreList();
                nameDialog.hide();
            }
        });
        buttonExit.addListener(new ClickListener() {
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                // super.touchDown(event, x, y, pointer, button);
                Sounds.play(ID.BUTTON);
                GVars.app.setScreen(GVars.app.menuScreen);
            }
        });
        stage.addListener(new InputListener() {
            public boolean keyUp(InputEvent event, int keycode) {
                if (keycode == Keys.BACK || keycode == Keys.ESCAPE) {
                    GVars.app.setScreen(GVars.app.menuScreen);
                }
                return false;
            }
        });
    }

    public void resize(int width, int height) {
        stage.getViewport().setWorldSize(width, height);
    }

    public void dispose() {
        stage.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        // Table.drawDebug(stage); // Enables debug lines for tables.
    }

    @Override
    public void show() {
        getScoreList();
        nameDialog.show(stage);
        Gdx.input.setInputProcessor(stage);
        Assets.pauseMusic();
        Assets.playMusic(Assets.menuMusic);
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {
        Assets.pauseMusic();
    }

    @Override
    public void resume() {
        Assets.playMusic(Assets.menuMusic);
    }

    private void getScoreList() {
        String[] tmplist = new String[5];
        for (int i = 0; i < 5; i++) {
            tmplist[i] = Integer.toString(Settings.highscores[i]).concat("   ").concat(Settings.scorenames[i]);
        }
        ScoresScreen.list.setItems(tmplist);
    }
}