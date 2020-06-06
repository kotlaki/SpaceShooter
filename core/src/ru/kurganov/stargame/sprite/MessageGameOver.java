package ru.kurganov.stargame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.kurganov.stargame.base.Sprite;
import ru.kurganov.stargame.math.Rect;

public class MessageGameOver extends Sprite {
    public MessageGameOver(TextureAtlas atlas) {
        super(atlas.findRegion("message_game_over"));
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(0.05f);
        setTop(0.1f);
    }
}
