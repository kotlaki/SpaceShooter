package ru.kurganov.stargame.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.kurganov.stargame.base.Ship;
import ru.kurganov.stargame.math.Rect;
import ru.kurganov.stargame.pool.BulletPool;
import ru.kurganov.stargame.pool.ExplosionPool;

public class MainShip extends Ship {

    private final int HP = 100;
    private Vector2 touch;
//    private Vector2 v;
    private static final float V_LEN = 0.5f;

    private boolean pressedLeft;
    private boolean pressedRight;
    private boolean pressedUp;
    private boolean pressedDown;


    public MainShip(TextureAtlas atlas, BulletPool bulletPool, ExplosionPool explosionPool) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.bulletRegion = atlas.findRegion("bulletMainShip");
        this.bulletV = new Vector2(0,0.6f);
        this.bulletPos = new Vector2();
        vH = new Vector2(V_LEN, 0);
        vV = new Vector2(0, V_LEN);
        touch = new Vector2();
        this.v = new Vector2();
        this.v0 = new Vector2(0.5f, 0);
        this.bulletHeight = 0.01f;
        this.reloadInterval = 0.2f;
        this.damage = 1;
        this.hp = HP;
        this.shotSound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.1f);
        this.worldBounds = worldBounds;
        setBottom(worldBounds.getBottom() + 0.05f);
    }

    @Override
    public void touchDown(Vector2 touch, int pointer, int button) {
        this.touch.set(touch);
        v.set(touch.sub(pos).setLength(V_LEN));
    }

    @Override
    public void touchUp(Vector2 touch, int pointer, int button) {

    }

    public void keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = true;
                moveLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = true;
                moveRight();
                break;
        }
        switch (keycode) {
            case Input.Keys.W:
            case Input.Keys.UP:
                pressedUp = true;
                moveUp();
                break;
            case Input.Keys.S:
            case Input.Keys.DOWN:
                pressedDown = true;
                moveDown();
                break;
        }
    }

    public void keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if (pressedRight) {
                    moveRight();
                } else {
                    stop();
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if (pressedLeft) {
                    moveLeft();
                } else {
                    stop();
                }
                break;
        }
        switch (keycode) {
            case Input.Keys.W:
            case Input.Keys.UP:
                pressedUp = false;
                if (pressedDown) {
                    moveUp();
                } else {
                    stop();
                }
                break;
            case Input.Keys.S:
            case Input.Keys.DOWN:
                pressedDown = false;
                if (pressedUp) {
                    moveDown();
                } else {
                    stop();
                }
                break;
        }
    }


    @Override
    public void update(float delta) {
        bulletPos.set(pos.x, getTop());
        reloadTimer += delta;
        if (reloadTimer >= reloadInterval) {
            reloadTimer = 0f;
            shot();
        }
        super.update(delta);
        // проверяем границы экрана
        if (getRight() > worldBounds.getRight()) {
            setRight(worldBounds.getRight());
            stop();
        }
        if (getLeft() < worldBounds.getLeft()) {
            setLeft(worldBounds.getLeft());
            stop();
        }
        if (getTop() > worldBounds.getTop()) {
            setTop(worldBounds.getTop());
            stop();
        }
        if (getBottom() < worldBounds.getBottom()) {
            setBottom(worldBounds.getBottom());
            stop();
        }
    }

    public boolean isBulletCollision(Rect bullet) {
        return !(bullet.getRight() < getLeft()
                || bullet.getLeft() > getRight()
                || bullet.getBottom() > pos.y
                || bullet.getTop() < getBottom());
    }


    private void moveRight() {
        v.set(vH);
    }

    private void moveLeft() {
        v.set(vH).rotate(180);
    }

    private void moveUp() {
        v.set(vV);
    }

    private void moveDown() {
        v.set(vV).rotate(180);
    }

    private void stop() {
        v.setZero();
    }

}
