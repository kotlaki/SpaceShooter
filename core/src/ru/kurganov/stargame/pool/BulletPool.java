package ru.kurganov.stargame.pool;

import ru.kurganov.stargame.base.SpritesPool;
import ru.kurganov.stargame.sprite.Bullet;

public class BulletPool extends SpritesPool<Bullet> {
    @Override
    protected Bullet newObject() {
        return new Bullet();
    }
}
