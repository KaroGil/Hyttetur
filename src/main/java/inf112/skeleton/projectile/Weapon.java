package inf112.skeleton.projectile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import inf112.skeleton.player.Entity;

public class Weapon {

    protected Sprite sprite;

    protected int rotation, width, height;

    protected int lifetime;

    protected Entity owner;

    protected long timeBorn;

    public Weapon(Sprite sprite, int rotation, int width, int height, int lifetime, Entity owner) {
        this.sprite = sprite;
        this.rotation = rotation;
        this.lifetime = lifetime;
        this.owner = owner;
        this.width = width;
        this.height = height;
        this.timeBorn = TimeUtils.millis();
    }

    public void render(SpriteBatch sb) {
        if (TimeUtils.millis() - timeBorn < 1000L * lifetime) {
            sprite.setRotation(rotation);
            sprite.setSize(width, height);
            sprite.setPosition(owner.getX() + 4, owner.getY() + 4);
            sprite.draw(sb);
            //Particle.TRAIL1();
        }
    }
}
