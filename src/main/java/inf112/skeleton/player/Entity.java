package inf112.skeleton.player;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import inf112.skeleton.map.MapHandler;
import inf112.skeleton.projectile.AreaOfEffect;
import inf112.skeleton.projectile.Effect;
import inf112.skeleton.projectile.Particle;
import inf112.skeleton.projectile.Projectile;
import inf112.skeleton.ui.Hud;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class to determine the common properties of the player and all other entities.
 */
public abstract class Entity extends Sprite{

    protected float width, height;
    protected float x, y, centerX, centerY, movementSpeed;
    public int HP;
    public boolean isAlive;
    Texture tex;
    SpriteBatch batch;
    private int maxHP;
    Sprite sprite;
    protected Rectangle hitbox;
    protected int atkSpeed;
    protected int atkDamage;
    protected Sound attackSound, hurtSound;
    protected long lastDamaged;
    protected long lastPoisoned;
    protected int spread = 0;
    protected MapHandler map;
    protected long lastShotTime;
    protected ArrayList<Projectile> projectileList;
    protected ArrayList<Effect> effectList;

    protected float movementSpeedMultiplier = 1;

    protected long lastAreaOfEffect;


    public Entity(MapHandler map, float x, float y, int maxHP, float movementSpeed) {
        this.map = map;
        this.movementSpeed = 0;
        this.isAlive = true;
        this.batch = new SpriteBatch();
        this.maxHP = maxHP;
        this.movementSpeed = movementSpeed;
        atkSpeed = 1;
        atkDamage = 1;
        this.effectList = new ArrayList<>();
    }
    public void damage(float damage, ArrayList<Particle> particleList) {
        HP -= damage;
        Particle.NUMBER((int)damage, x, y, particleList);
    }


    /**
     * gets the height
     * @return height
     */
    public float getHeight() {
        return this.height;
    }


    /**
     * gets the width
     * @return width
     */
    public float getWidth() {
        return this.width;
    }

    /**
     * gets the HP of the entity
     * @return HP value
     */
    public float getHP() {
        return this.HP;
    }


    /**
     * gets the value of the isAlive variable
     * tells us if the player is alive or dead
     * @return isAlive value
     */
    public boolean isAlive() {
        return this.isAlive;
    }


    /**
     * gets the entities hitbox
     * @return hitbox
     */
    public Rectangle getHitbox() {
        return hitbox;
    }


    /**
     * sets the hitbox of the entity with the given value
     * @param hitbox value
     */
    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }


    /**
     * gets X coordinate of the entity
     * @return X
     */
    public float getX() {
        return x;
    }


    /**
     * gets the Y coordinate of the entity
     * @return Y
     */
    public float getY() {
        return y;
    }


    /**
     * Method for the death of an entity
     * @param hud
     */
    public void die(Hud hud) {}


    protected void applyEffects(ArrayList<Particle> particleList) {
        if (effectList != null) {
            effectList.removeIf(effect -> !effect.isActive());
            for (Effect effect : effectList) {
                effect.apply(this, particleList);
                if (effect.getName().equals("SLOW")) {
                    if (!effect.isActive()) {
                        movementSpeedMultiplier = 1;
                    }
            }
        }
        }


    }



    /**
     * checks for map collisions with the entities
     * @param objectList hitbox list
     */
    protected void checkMapCollision(ArrayList<Rectangle> objectList) {
        for (Rectangle object: objectList) {
            if (hitbox.overlaps(object)) {
                float downValue = hitbox.y + hitbox.height - object.y;
                float upValue = hitbox.y - (object.y + object.height);
                float leftValue = (hitbox.x + hitbox.width) - object.x;
                float rightValue = hitbox.x - (object.x + object.width);
                float absRightValue = Math.abs(rightValue);
                float absLeftValue = Math.abs(leftValue);
                float absUpValue = Math.abs(upValue);
                float absDownValue = Math.abs(downValue);

                // LEFT VALUE
                if (absLeftValue < absDownValue && absLeftValue < absRightValue && absLeftValue < absUpValue){
                    hitbox.x = object.x - hitbox.width;
                }
                // DOWN VALUE
                if (absDownValue < absRightValue && absDownValue < absLeftValue && absDownValue < absUpValue){
                    hitbox.y = object.y - hitbox.height;
                }
                // RIGHT VALUE
                if (absRightValue < absLeftValue && absRightValue < absDownValue && absRightValue < absUpValue){
                    hitbox.x = object.x + object.width;
                }
                // UP VALUE
                if (absUpValue < absLeftValue && absUpValue < absDownValue && absUpValue < absRightValue){
                    hitbox.y = object.y + object.height;
                }
            }
        }
    }

    protected void checkProjectileCollision(ArrayList<Projectile> projectileList, ArrayList<Particle> particleList, ArrayList<AreaOfEffect> areaOfEffectList) {
        for (Projectile projectile: projectileList) {
                if (projectile.getHitbox().overlaps(hitbox)) {
                    if (projectile.getOwner() != this)
                        if (projectile.isAlive) {
                            damage(projectile.getDamage() * (1 + 0.1f * projectile.getOwner().atkDamage), particleList);
                            if (projectile.getEffect() != null) {
                                effectList.removeIf(effect -> effect.getName().equals(projectile.getEffect().getName()));
                                effectList.add(projectile.getEffect());
                            }
                            if (projectile.getAreaOfEffect() != null) {
                                areaOfEffectList.add(projectile.getAreaOfEffect());
                            }
                            projectile.getHitSound().play(0.3f);
                            projectile.isAlive = false;
                            lastDamaged = TimeUtils.millis();

                            if (projectile.getOwner().getSpread() > 0) {
                                Random r = new Random();
                                for (int i = 0; i < projectile.getOwner().getSpread(); i++) {
                                    float number1 = r.nextFloat(20);
                                    float number2 = r.nextFloat(20);


                                }
                            }
                        }
                    }
            }
        }


    /**
     * checks if player is inside map
     */
    public boolean checkEdge() {
        boolean onEdge = false;
        float widthMax = map.getMapInPixels("width") - hitbox.width;
        float heightMax = map.getMapInPixels("height") - hitbox.height;

        // Checking width
        if(hitbox.x < 0) {
            hitbox.x = 0;
            onEdge = true;
        }
        if(hitbox.x > widthMax) {
            hitbox.x = widthMax;
            onEdge = true;
        }
        // Checking height
        if(hitbox.y < 0) {
            hitbox.y = 0;
            onEdge = true;
        }
        if(hitbox.y > heightMax) {
            hitbox.y = heightMax;
            onEdge = true;
        }

        return onEdge;
    }



    /**
     * sets the akt speed to the given value
     * @param atkSpeed the given value
     */
    public void setAtkSpeed(int atkSpeed) {
        this.atkSpeed = atkSpeed;
    }


    /**
     * sets the akt damange to the given value
     * @param atkDamage the given value
     */
    public void setAtkDamage(int atkDamage) {
        this.atkDamage = atkDamage;
    }


    /**
     * gets the akt speed
     * @return aktspeed value
     */
    public int getAtkSpeed() {
        return atkSpeed;
    }


    /**
     * gets the akt damage
     * @return aktdamage value
     */
    public int getAtkDamage() {
        return atkDamage;
    }


    /**
     * gets the movement speed
     * @return movement speed of the entity
     */
    public float getMovementSpeed() {
        return movementSpeed;
    }


    /**
     * sets the movement speed of the entity
     * @param movementSpeed value of movement speed
     */
    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }


    /**
     * gets the spread value
     * @return spread value
     */
    public int getSpread() {
        return spread;
    }


    /**
     * sets the spread to the given value
     * @param spread value
     */
    public void setSpread(int spread) {
        this.spread = spread;
    }


    /**
     * gets the last shot time value
     * @return lastShotTime value
     */
    public long getLastShotTime () {
        return lastShotTime;
    }


    /**
     * sets the lastShotTime parameter to the give value
     * @param lastShotTime value
     */
    public void setLastShotTime ( long lastShotTime){
        this.lastShotTime = lastShotTime;
    }


    /**
     * gets the centerX value
     * @return centerX value
     */
    public float getCenterX() {
        return centerX;
    }


    @Override
    public void setCenterX(float centerX) {
        this.centerX = centerX;
    }


    /**
     * gets the centerY value
     * @return centerY value
     */
    public float getCenterY() {
        return centerY;
    }


    @Override
    public void setCenterY(float centerY) {
        this.centerY = centerY;
    }


    /**
     * gets the lastDamaged value
     * @return lastDamaged value
     */
    public long getLastDamaged() {
        return lastDamaged;
    }


    /**
     * sets the lastDamanged value to the given value
     * @param lastDamaged value
     */
    public void setLastDamaged(long lastDamaged) {
        this.lastDamaged = lastDamaged;
    }


    /**
     * gets the lastPoisoned value
     * @return lastPoisoned value
     */
    public long getLastPoisoned() {
        return lastPoisoned;
    }


    /**
     * sets lastPoisoned to the given value
     * @param lastPoisoned value
     */
    public void setLastPoisoned(long lastPoisoned) {
        this.lastPoisoned = lastPoisoned;
    }

    public float getMovementSpeedMultiplier() {
        return movementSpeedMultiplier;
    }

    public void setMovementSpeedMultiplier(float movementSpeedMultiplier) {
        this.movementSpeedMultiplier = movementSpeedMultiplier;
    }

    public ArrayList<Effect> getEffectList() {
        return effectList;
    }

    public void setEffectList(ArrayList<Effect> effectList) {
        this.effectList = effectList;
    }
}



