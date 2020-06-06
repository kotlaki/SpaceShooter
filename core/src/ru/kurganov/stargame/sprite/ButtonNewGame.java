package ru.kurganov.stargame.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.kurganov.stargame.base.ScaledButton;
import ru.kurganov.stargame.math.Rect;
import ru.kurganov.stargame.screen.GameScreen;

public class ButtonNewGame extends ScaledButton {

    private Game game;

    public ButtonNewGame(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("button_new_game"));
        this.game = game;
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(0.06f);
        setTop(-0.1f);
    }

    @Override
    public void action() {
       game.setScreen(new GameScreen(game));
    }

}
