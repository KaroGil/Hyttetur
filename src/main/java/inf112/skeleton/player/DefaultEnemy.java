package inf112.skeleton.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import inf112.skeleton.map.MapHandler;
import inf112.skeleton.projectile.AreaOfEffect;
import inf112.skeleton.projectile.Effect;
import inf112.skeleton.projectile.Particle;
import inf112.skeleton.projectile.Projectile;
import inf112.skeleton.ui.Hud;

import java.util.ArrayList;

public class DefaultEnemy extends Entity {

    private Sprite sprite;
    private int speed;
    private int attackDamage;
    private Player targetPlayer;
    private Texture hurtTex;
    private Texture poisonedTex;
    private long lastattack;

    private int xp;

    public DefaultEnemy(int x, int y, int speed, int attackDamage, MapHandler map,
                        int maxHP, ArrayList<DefaultEnemy> enemyList, Player targetPlayer, int xp) {
        super(map, x, y, maxHP, speed);

        this.x = x;
        this.y = y;

        this.height = 30;
        this.width = 15;
        hitbox = new Rectangle(x, y, width,height);
        this.speed = speed;
        this.attackDamage = attackDamage;
        this.HP = maxHP;

        this.xp = xp;

        this.targetPlayer = targetPlayer;

        this.tex = new Texture("assets/deafult_enemy_1.png");
        this.hurtTex = new Texture("assets/deafult_enemy_1_hurt.png");
        this.poisonedTex = new Texture("assets/deafult_enemy_1_poisoned.png");
        this.sprite = new Sprite(tex);

        hurtSound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/deafult_hurt.ogg"));

        enemyList.add(this);
    }

    public void render(SpriteBatch b) {
        sprite.draw(b);
    }

    public int getAttackDamage() {
        return this.attackDamage;
    }

    public Sprite getSprite() {
        return sprite;
    } //Added


    public void update(ArrayList<Projectile> projectileList, ArrayList<DefaultEnemy> enemyList, ArrayList<Rectangle> objectList,
                       Hud hud, ArrayList<Particle> particleList, ArrayList<AreaOfEffect> areaOfEffectList) {
        checkProjectileCollision(projectileList, particleList, areaOfEffectList);
        checkEdge();
        checkMapCollision(objectList);
        checkEnemyCollision(enemyList);
        checkHitbox();
        attack(particleList);
        updateSprite();
        die(hud);
        applyEffects(particleList);

    }





    /**
     * updates the enemies sprite depending on time since lastDamage was taken
     */
    private void updateSprite(){
        if ((TimeUtils.millis() - lastDamaged) > 100)
            setSprite(new Sprite(tex));
        else {
            setSprite(new Sprite(hurtTex));
        }
        if ((TimeUtils.millis() - lastPoisoned) < 200) {
            setSprite(new Sprite(poisonedTex));
        }
    }


    /**
     * checks the hitbox and updates it accordingly
     */
    public void checkHitbox(){
        x = hitbox.x;
        y = hitbox.y;

        if (targetPlayer.x - x > 0) {
            hitbox.x += 10 * speed * movementSpeedMultiplier * Gdx.graphics.getDeltaTime();
        }
        if (targetPlayer.x - x < 0) {
            hitbox.x -= 10 * speed * movementSpeedMultiplier * Gdx.graphics.getDeltaTime();
        }
        if (targetPlayer.y - y > 0) {
            hitbox.y += 10 * speed * movementSpeedMultiplier * Gdx.graphics.getDeltaTime();
        }
        if (targetPlayer.y - y < 0) {
            hitbox.y -= 10 * speed * movementSpeedMultiplier * Gdx.graphics.getDeltaTime();
        }
        if (hitbox.overlaps(targetPlayer.hitbox)) {
            if (TimeUtils.millis() - lastattack > 500L) {
                lastattack = TimeUtils.nanoTime();
            }
        }
    }

    public void attack(ArrayList<Particle> particleList) {
        if (hitbox.overlaps(targetPlayer.hitbox)) {
            targetPlayer.damage(attackDamage, particleList);
        }
}


    protected void checkEnemyCollision(ArrayList<DefaultEnemy> enemyList) {
        for (DefaultEnemy enemy: enemyList) {
            if (hitbox.overlaps(enemy.hitbox)) {
                float downValue = hitbox.y + hitbox.height - enemy.hitbox.y;
                float upValue = hitbox.y - (enemy.hitbox.y + enemy.hitbox.height);
                float leftValue = (hitbox.x + hitbox.width) - enemy.hitbox.x;
                float rightValue = hitbox.x - (enemy.hitbox.x + enemy.hitbox.width);
                float absRightValue = Math.abs(rightValue);
                float absLeftValue = Math.abs(leftValue);
                float absUpValue = Math.abs(upValue);
                float absDownValue = Math.abs(downValue);

                // LEFT VALUE
                if (absLeftValue < absDownValue && absLeftValue < absRightValue && absLeftValue < absUpValue){
                    hitbox.x = enemy.x - hitbox.width;
                }
                // DOWN VALUE
                if (absDownValue < absRightValue && absDownValue < absLeftValue && absDownValue < absUpValue){
                    hitbox.y = enemy.y - hitbox.height;
                }
                // RIGHT VALUE
                if (absRightValue < absLeftValue && absRightValue < absDownValue && absRightValue < absUpValue){
                    hitbox.x = enemy.x + enemy.width;
                }
                // UP VALUE
                if (absUpValue < absLeftValue && absUpValue < absDownValue && absUpValue < absRightValue){
                    hitbox.y = enemy.y + enemy.height;
                }
            }
        }
    }


    public void die(Hud hud) {
        if (HP <= 0) {
            hurtSound.play();
            isAlive = false;
            targetPlayer.addXp(xp);
            hud.setScore(hud.getScore() + 1);
        }
    }


    /**
     * method to set the Hp of the enemy to the given value
     * @param newHp value
     */
    public void setHp(int newHp){
        this.HP = newHp;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
