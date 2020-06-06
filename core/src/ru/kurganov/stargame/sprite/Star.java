package ru.kurganov.stargame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.kurganov.stargame.base.Sprite;
import ru.kurganov.stargame.math.Rect;
import ru.kurganov.stargame.math.Rnd;

public class Star extends Sprite {

    protected final Vector2 v; // вектор скорости звезды
    private Rect worldBound;    // границы текущего игрового мира
    private float animateTimer;
    private float animeteInterval = 1f;
    private static final float STAR_HEIGHT = 0.004f;

    public Star(TextureAtlas atlas) {
        super(atlas.findRegion("star"));
        v = new Vector2();
        v.set(Rnd.nextFloat(-0.005f, 0.005f), Rnd.nextFloat(-0.2f, -0.01f));  // скорость движения звезд
        animateTimer = Rnd.nextFloat(0, 1f);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(STAR_HEIGHT);
        float posX = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
        float posY = Rnd.nextFloat(worldBounds.getBottom(), worldBounds.getTop());
        pos.set(posX, posY);
        this.worldBound = worldBounds;
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);

        checkAndHandleBounds();

        animateTimer += delta;
        if (animateTimer >= animeteInterval) {
            animateTimer = 0;
            setHeightProportion(STAR_HEIGHT);
        } else {
            setHeightProportion(getHeight() + 0.0001f);
        }
    }

    public void checkAndHandleBounds() {
        if(getRight() < worldBound.getLeft()) {
            setLeft(worldBound.getRight());
        }
        if(getLeft() > worldBound.getRight()) {
            setRight((worldBound.getLeft()));
        }
        if(getTop() < worldBound.getBottom()) {
            setBottom(worldBound.getTop());
        }
        if(getBottom() > worldBound.getTop()) {
            setTop(worldBound.getBottom());
        }
    }
}
