package ru.kurganov.stargame.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.kurganov.stargame.base.Ship;
import ru.kurganov.stargame.math.Rect;
import ru.kurganov.stargame.pool.BulletPool;
import ru.kurganov.stargame.pool.ExplosionPool;
import ru.kurganov.stargame.util.EnemyEmitter;

public class Enemy extends Ship {

    public Enemy(BulletPool bulletPool, ExplosionPool explosionPool, Sound shotSound, Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.shotSound = shotSound;
        this.worldBounds = worldBounds;
        this.v = new Vector2();
        this.v0 = new Vector2();
        this.bulletV = new Vector2();
        this.bulletPos = new Vector2();
    }

    @Override
    public void update(float delta) {
        // корабль не стреляет пока полностью не вылетит на экран
        if (getTop() > worldBounds.getHalfHeight()) {
            super.update(delta);
            bulletPos.set(pos.x, getBottom());
        } else {
            super.update(delta);
            reloadTimer += delta;
            if (reloadTimer >= reloadInterval) {
                reloadTimer = 0f;
                bulletPos.set(pos.x, getBottom());
                shot();
            }
        }
        // уничтожение корабля вылетевшего за экран
        if (getBottom() < worldBounds.getBottom()) {
            destroy();
        }
    }

    public void set(
            TextureRegion[] regions,
            TextureRegion bulletRegion,
            Vector2 v0,
            Vector2 v,
            float bulletHeight,
            float bulletVY,
            int damage,
            float reloadInterval,
            float height,
            int hp
    ) {
        this.regions = regions;
        this.bulletRegion = bulletRegion;
        this.v0.set(v0);
        this.v.set(v);
        this.bulletHeight = bulletHeight;
        this.bulletV.set(0, bulletVY);
        this.damage = damage;
        this.reloadInterval = reloadInterval;
        this.reloadTimer = reloadInterval;
        setHeightProportion(height);
        this.hp = hp;
    }

    public boolean isBulletCollision(Rect bullet) {
        return !(bullet.getRight() < getLeft()
                || bullet.getLeft() > getRight()
                || bullet.getBottom() > getTop()
                || bullet.getTop() < pos.y);
    }

}
