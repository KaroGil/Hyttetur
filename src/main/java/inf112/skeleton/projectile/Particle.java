package inf112.skeleton.projectile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import inf112.skeleton.player.Entity;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Particle {

    protected int lifetime;

    protected Sprite sprite;

    protected float x, y;

    protected int width, height;

    protected float speed, xSpeed, ySpeed;

    protected long timeBorn;

    protected boolean rotating;

    protected int rotation;

    protected Entity owner;





    public Particle(int lifetime, float x, float y, int width, int height, float speed, long timeBorn) {
        this.lifetime = lifetime;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.timeBorn = timeBorn;
    }

    public Particle(int lifetime, float x, float y, int width, int height, float speed, long timeBorn, Sprite sprite,
                    boolean rotating, float xSpeed, float ySpeed) {
        this.lifetime = lifetime;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.timeBorn = timeBorn;
        this.sprite = sprite;
        this.rotating = rotating;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public Particle(int lifetime, float x, float y, int width, int height, float speed, long timeBorn, Entity owner) {
        this.lifetime = lifetime;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.timeBorn = timeBorn;
        this.owner = owner;
    }

    public static void NUMBER(int damage, float x, float y, ArrayList<Particle> particleList) {
        Random random = new Random();
        int random1 = random.nextInt(15);
        int random2 = random.nextInt(15);
        Particle number = new Particle(2, x + random1, y + 20 + random2, 0, 0, 2, TimeUtils.millis()) {
            @Override
            public void render(SpriteBatch sb) {
                if (TimeUtils.millis() - timeBorn < 1000L * lifetime) {
                    BitmapFont font = new BitmapFont();
                    font.setColor(Color.WHITE);
                    if (damage > 100) {
                        font.setColor(Color.RED);
                    }
                    else if (damage > 40) {
                        font.setColor(Color.YELLOW);
                    }

                    font.getData().setScale(0.6f);
                    y += 1 * speed * Gdx.graphics.getDeltaTime();
                    font.draw(sb, String.valueOf(damage), x, y);
                    speed -= 0.3;
                }

            }
        };
        particleList.add(number);
    }

    public static void TRAIL1(int lifetime, float x, float y, ArrayList<Particle> particleList, int speed, int width, int height,
                              Texture texture, boolean rotating) {
        Random random = new Random();
        float xSpeed = random.nextFloat(1) - 0.5f;
        float ySpeed = random.nextFloat(1) - 0.5f;
        int random1 = random.nextInt(4) - 2;
        int random2 = random.nextInt(4) - 2;
        Sprite sprite = new Sprite(texture);
        Particle trail = new Particle(lifetime, x + random1, y + random2, width, height, speed, TimeUtils.millis(), sprite, rotating, xSpeed, ySpeed) {
            @Override
            public void render(SpriteBatch sb) {
                if (TimeUtils.millis() - timeBorn < 1000L * lifetime) {
                    sprite.setSize(width, height);
                    sprite.setPosition(x, y);
                    if (rotating) {
                        sprite.setRotation(rotation += 1);
                    }
                    sprite.draw(sb);
                }

            }

            @Override
            public void update() {
                x += xSpeed * speed * Gdx.graphics.getDeltaTime();
                y += ySpeed * speed * Gdx.graphics.getDeltaTime();
            }
        };
        particleList.add(trail);
    }



    public void render(SpriteBatch sb) {
    }

    public void update() {
    }


    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }


}

