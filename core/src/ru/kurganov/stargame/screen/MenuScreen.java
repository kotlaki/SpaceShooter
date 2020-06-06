package ru.kurganov.stargame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.kurganov.stargame.base.BaseScreen;
import ru.kurganov.stargame.math.Rect;
import ru.kurganov.stargame.sprite.Background;
import ru.kurganov.stargame.sprite.ButtonExit;
import ru.kurganov.stargame.sprite.ButtonMusic;
import ru.kurganov.stargame.sprite.ButtonPlay;
import ru.kurganov.stargame.sprite.Star;

public class MenuScreen extends BaseScreen {

    private final Game game;
    private Texture img;
    private Background background;
    private Texture bg;
    private TextureAtlas atlas;
    private Star[] stars;
    private static final int STAR_COUNT = 256;
    private ButtonExit buttonExit;
    private ButtonPlay buttonPlay;


    public MenuScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/space.jpg");
        background = new Background(bg);
        img = new Texture("badlogic.jpg");
        atlas = new TextureAtlas(Gdx.files.internal("textures/newMainButton.tpack"));

        buttonExit = new ButtonExit(atlas);
        buttonPlay = new ButtonPlay(atlas, game);
        stars = new Star[STAR_COUNT];
        for (int i = 0; i < STAR_COUNT; i++) {
            stars[i] = new Star(atlas);
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star o : stars) {
            o.resize(worldBounds);
        }
        buttonExit.resize(worldBounds);
        buttonPlay.resize(worldBounds);
    }

    @Override
    public void dispose() {
        img.dispose();
        bg.dispose();
        atlas.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        buttonExit.touchDown(touch, pointer, button);
        buttonPlay.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        buttonExit.touchUp(touch, pointer, button);
        buttonPlay.touchUp(touch, pointer, button);
        return false;
    }

    private void update(float delta) {
        for (Star o : stars) {
            o.update(delta);
        }
    }

    private void draw() {
        Gdx.gl.glClearColor(0.5f, 0.9f, 0.4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (Star o : stars) {
            o.draw(batch);
        }
        buttonExit.draw(batch);
        buttonPlay.draw(batch);
        batch.end();
    }

}