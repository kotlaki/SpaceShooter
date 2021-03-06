package ru.kurganov.stargame.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.kurganov.stargame.base.ScaledButton;
import ru.kurganov.stargame.math.Rect;

public class ButtonExit extends ScaledButton {
    private static final float PADDING = 0.05f;
    public ButtonExit(TextureAtlas atlas) {
        super(atlas.findRegion("btnExit"));
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.15f);
        setRight(worldBounds.getRight() - PADDING);
        setBottom(worldBounds.getBottom() + PADDING);
    }

    @Override
    public void action() {
        Gdx.app.exit();
    }
}
