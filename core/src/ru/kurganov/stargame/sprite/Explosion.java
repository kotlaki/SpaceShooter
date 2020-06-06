package ru.kurganov.stargame.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.kurganov.stargame.base.Sprite;
import ru.kurganov.stargame.math.Rect;

public class Explosion extends Sprite {
    private float animateInterval = 0.017f;
    private float animateTimer;
    private Sound explosionSound;

    public Explosion(TextureAtlas atlas, Sound explosionSound) {
        super(atlas.findRegion("explosion"), 9, 9, 74);
        this.explosionSound = explosionSound;
    }

    public void set (float height, Vector2 pos) {
        this.pos.set(pos);
        setHeightProportion(height);
        long id = explosionSound.play();
        explosionSound.setVolume(id, 0.05f);
    }

    @Override
    public void update(float delta) {
        animateTimer += delta;
        if (animateTimer >= animateInterval) {
            animateTimer = 0f;
            if (++frame == regions.length) {
                destroy();
            }
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        frame = 0;
    }
}
