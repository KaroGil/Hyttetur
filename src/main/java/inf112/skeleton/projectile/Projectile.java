package inf112.skeleton.projectile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import inf112.skeleton.player.Entity;
import java.util.ArrayList;
import java.util.Random;


public class Projectile {


    private String name;
    private float damage;
    private Effect effect;

    private AreaOfEffect areaOfEffect;
    private double speed;
    private float distanceX;
    private float distanceY;
    private double angle;
    private double xSpeed;
    private double ySpeed;
    private float x;
    private float y;
    private int width;
    private int height;
    private int rotation;
    private Texture texture;
    private Sprite sprite;
    protected Rectangle hitbox;
    private final Entity owner;
    public boolean isAlive;
    protected Sound throwSound;
    protected Sound hitSound;

    protected long lastParticle;


    public Projectile(String name, Texture texture, float x, float y, int width, int height, double speed, float targetX, float
            targetY, Entity owner, float damage, Sound throwSound, Sound hitSound) {
        this.name = name;
        this.isAlive = true;
        this.owner = owner;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.distanceX = targetX - x;
        this.distanceY = targetY - y;
        this.angle = Math.atan2(distanceY, distanceX);
        this.xSpeed = speed * Math.cos(angle);
        this.ySpeed = speed * Math.sin(angle);
        this.texture = texture;
        this.hitbox = new Rectangle(x, y, width, height);

        this.damage = damage;
        this.rotation = 0;
        this.sprite = new Sprite(texture);

        this.throwSound = throwSound;
        this.hitSound = hitSound;
    }


    /*
    Updates the x and y value of the current projectile
     */
    public void update(ArrayList<Particle> particleList) {
        x += xSpeed * 70 * Gdx.graphics.getDeltaTime();
        y += ySpeed * 70 * Gdx.graphics.getDeltaTime();
        hitbox.x = x + 1.7f;
        hitbox.y = y;
        hitbox.width = width - 3.5f;
        if (name.equals("cider_can")) {
            if (TimeUtils.millis() - lastParticle > 250) {
                Texture texture = new Texture("assets/particles/poison1.png");
                Particle.TRAIL1(1, getHitbox().x + (width / 2f), getHitbox().y + (height / 2f), particleList, 20,
                            8, 8, texture, true);
                lastParticle = TimeUtils.millis();
            }
        }
        if (name.equals("critical_cork")) {
            if (TimeUtils.millis() - lastParticle > 25) {
                Texture texture = new Texture("assets/particles/champagne1.png");
                Particle.TRAIL1(1, getHitbox().x + (width / 2f), getHitbox().y + (height / 2f), particleList, 20,
                        8, 8, texture, false);
                lastParticle = TimeUtils.millis();
            }
        }

        if (areaOfEffect != null) {
            areaOfEffect.setX(x);
            areaOfEffect.setY(y);
        }
    }


    /*BEER BOTTLE
    Makes a new beer projectile at the players location
     */
    public static void createBeerBottle(float mouseX, float mouseY, Entity player, ArrayList<Projectile> projectileList) {
        Texture texture = new Texture("assets/projectiles/beer.png");
        Sound tSound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/beer_throw.ogg"));
        Sound hSound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/beer_hit.ogg"));

        Projectile beerBottle = new Projectile("beer_bottle", texture, player.getCenterX(),
                player.getCenterY(),
                12, 12, 0.8, mouseX, mouseY, player, 5, tSound, hSound);

        beerBottle.throwSound.play(1.0f);
        player.setLastShotTime(TimeUtils.nanoTime());
        projectileList.add(beerBottle);
    }


    /**
     * creates a beer can with the corresponding textures and sounds
     * @param mouseX x value of the mouse
     * @param mouseY y value of the mouse
     * @param player which player / entity throws the beer can
     * @param projectileList list of projectiles
     * @param damage the amount of damahe the can makes
     * @param amount the amount of beer cans to generate
     */
    public static void createBeerCan(float mouseX, float mouseY, Entity player, ArrayList<Projectile> projectileList, int damage, int amount) {
        Texture texture = new Texture("assets/projectiles/beer_can.png");
        Sound tSound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/beer_throw2.ogg"));
        Sound hSound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/beer_hit2.ogg"));
        int deviation = 10;
        for (int i = 0; i < 3 + amount; i++) {
            deviation *= -1.5;
            Projectile beerCan = new Projectile("beer_can", texture, player.getCenterX(),
                    player.getCenterY(),
                    10, 10, 1, mouseX + deviation, mouseY + deviation, player, damage, tSound, hSound);


            beerCan.throwSound.play(0.2f);
            projectileList.add(beerCan);
        }
            player.setLastShotTime(TimeUtils.nanoTime());
    }




        public static void createFreezer(Entity player, ArrayList<Projectile> projectileList, int color, int poolDuration) {
            Texture texture = switch (color) {
                case 1 -> new Texture("assets/projectiles/orange_freezer.png");
                case 2 -> new Texture("assets/projectiles/strawberry_freezer.png");
                case 3 -> new Texture("assets/projectiles/lime_freezer.png");
                case 4 -> new Texture("assets/projectiles/blueberry_freezer.png");
                default -> new Texture("assets/projectiles/orange_freezer.png");
            };
            Sound tSound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/freezer_throw.ogg"));
            Sound hSound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/freezer_hit.ogg"));

            Random random = new Random();
            float randomCoordinate1 = random.nextFloat() * 200.0f - 100.0f;
            float randomCoordinate2 = random.nextFloat() * 200.0f - 100.0f;
            System.out.println(randomCoordinate1 + " , " + randomCoordinate2);

            Projectile freezer = new Projectile("freezer_bottle", texture, player.getCenterX(),
                    player.getCenterY(), 10, 10,
                    1.2, (int) player.getX() + (player.getWidth() / 2 - 5) + randomCoordinate1,
                    (int) player.getY() + (player.getHeight() / 2 - 7) + randomCoordinate2, player, 4,
                    tSound, hSound);
        freezer.throwSound.play(1f);

        freezer.setAreaOfEffect(AreaOfEffect.FREEZER_POOL(10, freezer.x, freezer.y, poolDuration, color, freezer.owner));
        projectileList.add(freezer);
        System.out.println(freezer.getEffect());

        }


    /**
     * creates a cider can for the entity to throw
     * @param targetX X value of the target
     * @param targetY Y value of the target
     * @param player which player / entity that throws the projectile
     * @param projectileList list of projectiles
     * @param duration the duration of the effect from the projectile (cider can)
     * @param damage amount of damage the can will deal
     * @param amount of cider can to throw
     */
    public static void createCiderCan(float targetX, float targetY, Entity player, ArrayList<Projectile> projectileList, int duration, int damage, int amount) {
        Texture texture = new Texture("assets/projectiles/cider_can.png");
        Sound tSound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/cider_throw.ogg"));
        Sound hSound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/cider_hit.ogg"));
        int deviation = 0;
        for (int i = 0; i < 1 + amount; i++) {
            Projectile ciderCan = new Projectile("cider_can", texture, player.getCenterX(),
                    player.getCenterY(),
                    10, 10, 1, targetX + deviation, targetY + deviation, player, 3, tSound, hSound);

            ciderCan.throwSound.play(0.5f);
            ciderCan.setEffect(Effect.POISON(duration, damage));
            projectileList.add(ciderCan);
            deviation +=20;
        }
    }

    public static void createChampagne(float targetX, float targetY, Entity player, ArrayList<Projectile> projectileList,
                                       int damage, int speed, ArrayList<Weapon> weaponList, int chance) {
        Texture champagneTexture = new Texture("assets/projectiles/champagne_bottle.png");
        Texture corkTexture = new Texture("assets/projectiles/champagne_cork.png");
        Sound tSound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/champagne_throw.ogg"));
        Sound hSound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/champagne_hit.ogg"));

        Random random = new Random();

        int criticalChance = random.nextInt(10 - chance);


        Projectile cork = new Projectile("cork", corkTexture, player.getCenterX(), player.getCenterY(), 14, 14, speed, targetX, targetY,
                player, damage, tSound, hSound);

        if (criticalChance == 4) {
            hSound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/champagne_crit.ogg"));
            cork = new Projectile("critical_cork", corkTexture, player.getCenterX(), player.getCenterY(), 14, 14, speed, targetX, targetY,
                    player, damage * 2, tSound, hSound);


        }

        int rotation = (int)(cork.angle * (180 / Math.PI) + 270);
        Weapon champagne = new Weapon(new Sprite(champagneTexture), rotation, 12, 12, 1, player);


        cork.throwSound.play();
        projectileList.add(cork);
        weaponList.add(champagne);

    }


    /**
     * render method for the projectile
     * @param batch SpriteBatch
     */
    public void render(SpriteBatch batch) {
        sprite.setOriginCenter();
        sprite.setPosition(x - 1, y);
        sprite.setSize(width + 1, height + 1);
        sprite.setOrigin(sprite.getOriginX(), sprite.getOriginY());
        sprite.setRotation(rotation += 1);
        sprite.draw(batch);
    }


    /**
     * gets the speed of the projectile
     * @return speed
     */
    public double getSpeed() {
        return speed;
    }


    /**
     * sets the speed of the projectile by the given value
     * @param speed value
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }


    /**
     * gets the angle the projectile has
     * @return angle
     */
    public double getAngle() {
        return angle;
    }


    /**
     * sets the angle of the projectile by the given value
     * @param angle value
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }


    /**
     * gets the X coordinate of the projectile
     * @return X value
     */
    public float getX() {
        return x;
    }


    /**
     * sets the X value by the given amount
     * @param x value
     */
    public void setX(float x) {
        this.x = x;
    }


    /**
     * gets the Y coordinate of the projectile
     * @return Y value
     */
    public float getY() {
        return y;
    }


    /**
     * sets the Y value to the given value
     * @param y value
     */
    public void setY(float y) {
        this.y = y;
    }


    /**
     * gets the width of the projectile
     * @return width value
     */
    public int getWidth() {
        return width;
    }


    /**
     * sets the width value by the given amount
     * @param width value
     */
    public void setWidth(int width) {
        this.width = width;
    }


    /**
     * gets the height of the projectile
     * @return height value
     */
    public int getHeight() {
        return height;
    }


    /**
     * sets the height of the projectile by the given amount
     * @param height value
     */
    public void setHeight(int height) {
        this.height = height;
    }


    /**
     * gets the texture of the projectile
     * @return texture
     */
    public Texture getTexture() {
        return texture;
    }


    /**
     * sets the texture to the given value
     * @param texture value
     */
    public void setTexture(Texture texture) {
        this.texture = texture;
    }


    /**
     * gets the hitbox of the projectile
     * @return hitbox
     */
    public Rectangle getHitbox() {
        return hitbox;
    }


    /**
     * gets the owner
     * @return owner
     */
    public Entity getOwner() {
        return owner;
    }


    /**
     * gets the damage value of the projectile
     * @return damage value
     */
    public float getDamage() {
        return damage;
    }


    /**
     * gets the sound of the throw
     * @return throwSound
     */
    public Sound getThrowSound() {
        return throwSound;
    }


    /**
     * gets the sound the projectile makes on hit
     * @return hitSound
     */
    public Sound getHitSound() {
        return hitSound;
    }


    /**
     * gets the effect the projectile has
     * @return effect
     */
    public Effect getEffect() {
        return effect;
    }


    /**
     * sets the effect the projectile has to the given value
     * @param effect
     */
    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public AreaOfEffect getAreaOfEffect() {
        return areaOfEffect;
    }

    public void setAreaOfEffect(AreaOfEffect areaOfEffect) {
        this.areaOfEffect = areaOfEffect;
    }


    /**
     * method for testing purposes
     * @return distanceX
     */
    public float getDistanceX(){
        return this.distanceX;
    }


    /**
     * method for testing purposes
     * @return distanceY
     */
    public float getDistanceY(){
        return this.distanceY;
    }
}


