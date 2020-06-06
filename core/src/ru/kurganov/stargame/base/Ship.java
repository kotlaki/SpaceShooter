package ru.kurganov.stargame.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;

import ru.kurganov.stargame.math.Rect;
import ru.kurganov.stargame.pool.BulletPool;
import ru.kurganov.stargame.pool.ExplosionPool;
import ru.kurganov.stargame.sprite.Bullet;
import ru.kurganov.stargame.sprite.Explosion;

public class Ship extends Sprite {


    protected final float DAMAGE_ANIMATE_INTERVAL = 0.1f;

    protected float damageAnimateTimer = DAMAGE_ANIMATE_INTERVAL;


    protected Vector2 v;
    protected Vector2 v0;
    protected Vector2 vH;
    protected Vector2 vV;

    protected Rect worldBounds;

    protected BulletPool bulletPool;
    protected ExplosionPool explosionPool;
    protected TextureRegion bulletRegion;
    protected Vector2 bulletV;
    protected Vector2 bulletPos;
    protected float bulletHeight;
    protected int damage;

    protected float reloadTimer;
    protected float reloadInterval;

    protected int hp;

    protected Sound shotSound;

    public Ship() {
    }

    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
    }

    @Override
    public void update(float delta) {
        // перемещаем спрайт по нажатию клавиш
        if (getTop() > worldBounds.getHalfHeight()) {
            pos.mulAdd(v0, delta);
        } else {
            pos.mulAdd(v, delta);
        }
        damageAnimateTimer += delta;
        if (damageAnimateTimer >= DAMAGE_ANIMATE_INTERVAL) {
            frame = 0;
        }

    }

    protected void shot() {
        long  id = shotSound.play();
        shotSound.setVolume(id, 0.05f);
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, bulletPos, bulletV, bulletHeight, worldBounds, damage);
    }

    @Override
    public void destroy() {
        super.destroy();
        this.hp = 0;
        boom();
    }

    public void dispose() {
        shotSound.dispose();
    }

    protected void boom() {
        Explosion explosion = explosionPool.obtain();
        explosion.set(getHeight(), pos);
    }

    public void damage(int dmg) {
        this.hp -= dmg;
        if(hp <= 0) {
            destroy();
        }
        damageAnimateTimer = 0f;
        frame = 1;
    }

    public int getDamage() {
        return damage;
    }

    public Vector2 getV() {
        return v;
    }

    public int getHp() {
        return hp;
    }
}
