package inf112.skeleton.projectile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import inf112.skeleton.player.DefaultEnemy;
import inf112.skeleton.player.Entity;
import inf112.skeleton.player.Player;

import javax.management.DescriptorAccess;
import java.util.ArrayList;


public class AreaOfEffect {


    protected Rectangle hitbox;

    protected Sprite sprite;

    protected Particle particle;

    protected int width, height;

    protected float x, y;

    protected int lifetime;

    protected long timeBorn;

    protected boolean active;

    protected Entity owner;

    protected long lastApply;

    public AreaOfEffect(Texture texture, Particle particle, int width, int height, float x, float y, int lifetime, Entity owner) {

        this.hitbox = new Rectangle(x, y, width, height);
        this.sprite = new Sprite(texture);
        this.particle = particle;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.lifetime = lifetime;
        this.timeBorn = TimeUtils.millis();
        this.active = true;
        this.owner = owner;
    }

    public void render(SpriteBatch sb) {
        if (active) {
            sprite.setPosition(x, y);
            sprite.setSize(width, height);
            sprite.draw(sb);
        }
    }

    public void update(ArrayList<DefaultEnemy> enemyList, Player player) {
        hitbox.x = (int) x;
        hitbox.y = (int) y;
        if (TimeUtils.millis()- timeBorn > 1000L * lifetime) {
            active = false;

        }
        if (TimeUtils.millis() - lastApply > 700) {
            if (active) {
                apply(enemyList, player);
                lastApply = TimeUtils.millis();
            }
        }
    }

    public static AreaOfEffect FREEZER_POOL(int size, float x, float y, int lifetime, int color, Entity owner) {
        new Texture("assets/projectiles/strawberry_pool.png");
        Texture texture = switch (color) {
            case 1 -> new Texture("assets/projectiles/orange_pool.png");
            case 2 -> new Texture("assets/projectiles/strawberry_pool.png");
            case 3 -> new Texture("assets/projectiles/lime_pool.png");
            case 4 -> new Texture("assets/projectiles/blueberry_pool.png");
            default -> new Texture("assets/projectiles/strawberry_pool.png");
        };



        return new AreaOfEffect(texture, null, 20 + size, 20 + size,
                x, y, lifetime, owner) {


            @Override
            public void apply(ArrayList<DefaultEnemy> enemyList, Player player) {
                Effect effect = Effect.SLOW(4, 2);
                for (DefaultEnemy enemy : enemyList) {
                    if (enemy.getHitbox().overlaps(hitbox)) {
                        if (owner != enemy) {
                            enemy.getEffectList().removeIf(effect1 -> effect.getName().equals(effect.name));
                            enemy.getEffectList().add(Effect.SLOW(2, 2));
                        }
                    }

                }
                if (player.getHitbox().overlaps(hitbox)){
                    if (owner != player) {
                        player.getEffectList().add(Effect.SLOW(4, 2));
                    }
                }
            }
        };




    }


    public void apply(ArrayList<DefaultEnemy> enemyList, Player player) {

    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
