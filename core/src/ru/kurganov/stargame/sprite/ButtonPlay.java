package ru.kurganov.stargame.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.kurganov.stargame.base.ScaledButton;
import ru.kurganov.stargame.math.Rect;
import ru.kurganov.stargame.screen.GameScreen;

public class ButtonPlay extends ScaledButton {

    private static final float PADDING = 0.05f;
    private final Game game;

    public ButtonPlay(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("btnPlay"));
        this.game = game;
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.2f);
        setLeft(worldBounds.getLeft() + PADDING);
        setBottom(worldBounds.getBottom() + PADDING);
    }

    @Override
    public void action() {
        game.setScreen(new GameScreen(game));
    }

}
