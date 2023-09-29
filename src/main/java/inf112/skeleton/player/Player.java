package inf112.skeleton.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import inf112.skeleton.map.MapHandler;
import inf112.skeleton.projectile.AreaOfEffect;
import inf112.skeleton.projectile.Particle;
import inf112.skeleton.projectile.Projectile;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import inf112.skeleton.projectile.Weapon;
import inf112.skeleton.screens.GameScreen;
import inf112.skeleton.ui.Hud;

import javax.management.DescriptorAccess;
import java.util.ArrayList;
import java.util.Random;


public class Player extends Entity {

    public enum State {STANDING, RUNNINGRIGHT, RUNNINGLEFT, RUNNINGUP, RUNNINGDOWN}
    private State currentState;
    private State prevState;
    private float stateTimer;

    private TextureRegion playerStand;
    private Animation<TextureRegion> playerRunDown;
    private Animation<TextureRegion> playerRunUp;
    private Animation<TextureRegion> playerRunLeft;
    private Animation<TextureRegion> playerRunRight;
    private Texture player;

    private IController playerController;
    private Rectangle mouse;
    protected Sound levelUpSound;

    protected Sound deathSound;
    private GameScreen gameScreen;

    private int xp;
    private int xpGoal;
    private  int level;
    private boolean isLeveling;
    private long lastDamaged;

    /**POWERS*/
    protected int shotgun;
    protected int freezerRace;
    protected long lastFreezer;
    protected int rainbowShoes;
    protected long lastRainbow;
    protected int acidicCider;
    protected long lastCider;
    protected int pressureChampagne;
    protected long lastChampagne;


    public Player(float height, float width, float x, float y, float speed, MapHandler map, Rectangle mouse, int maxHP, GameScreen gameScreen) {
        super(map, x, y, maxHP, speed);

        this.height = height;
        this.width = width;
        hitbox = new Rectangle(x, y, width,height);
        this.x = x;
        this.y = y;
        this.movementSpeed = speed;
        this.mouse = mouse;
        this.gameScreen = gameScreen;

        this.player = new Texture("assets/main_character_moment.png");
        currentState = State.STANDING;
        prevState = State.STANDING;
        stateTimer = 0;

        //setting default state
        playerStand = new TextureRegion(getTexture(), 0, 0, 256, 256);
        setRegion(playerStand);

        setAnimations();

        playerController = new Controller();

        level = 1;
        HP = maxHP;
        hurtSound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/player_hurt.ogg"));
        levelUpSound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/level_up_sound.ogg"));
        deathSound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/death_sound.ogg"));

        xpGoal = 25;
        xp = 0;

    }


    /**
     * gets the x coordinate of the player
     * @return X
     */
    public float getX() {
        return x;
    }


    /**
     * gets the y coordinate of the player
     * @return Y
     */
    public float getY() {
        return y;
    }

    public void update(ArrayList<Rectangle> objectList, ArrayList<Projectile> projectileList, ArrayList<Particle> particleList,
                       ArrayList<DefaultEnemy> enemyList, ArrayList<Weapon> weaponList, ArrayList<AreaOfEffect> areaOfEffectList) {
        checkUserInput(Gdx.graphics.getDeltaTime(), projectileList);
        x = getHitbox().x;
        y = getHitbox().y;
        checkEdge();
        checkMapCollision(objectList);
        checkProjectileCollision(projectileList, particleList, areaOfEffectList);
        setCenterX(hitbox.x + (hitbox.width / 2) - 2);
        setCenterY(hitbox.y + (hitbox.height / 2) - 2);
        automaticSpawns(projectileList, enemyList, weaponList, particleList);
        levelUp();
        die(null);
        applyEffects(particleList);


    }


    /**
     * checks the hp and kills the player if the hp is 0 or under.
     * plays sound for death
     * switches isAlive state to false since the player is no longer alive.
     * sets the screen to the gameOverscreen
     * @param hud
     */
    @Override
    public void die(Hud hud) {
        if (HP <= 0) {
            isAlive = false;
        }
    }



    /*
     * Checks if player is standing on tile to next level
     * 
     * returns level
     */
    public int checkNextLevel(ArrayList<MapObject> nextLevel) {
        
        int level = -1;

        for (MapObject object: nextLevel) {

            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            if (hitbox.overlaps(rect)) {
                level = (int) object.getProperties().get("Level");

            }
        }


        return level;
    }

    /**
     * Moves player to start spawn in map
     */
    
    public void movePlayerToSpawn() {
        getHitbox().x = map.getPlayerSpawnX();
        getHitbox().y = map.getPlayerSpawnY();
     
    }

    public void automaticSpawns(ArrayList<Projectile> projectileList, ArrayList<DefaultEnemy> enemyList, ArrayList<Weapon> weaponList,
                                ArrayList<Particle> particleList) {
        Random random = new Random();
        /**
         * FREEZER MECHANIC
         * the code for the freezer bottles that spawns after a certain period of time.
         * It aims for a random location in the map
         */
        int freezerColor = random.nextInt(4) + 1;
        switch (freezerRace) {
            case 1 -> freezerRace(projectileList, 1.6f, 5, 5, freezerColor);
            case 2 -> freezerRace(projectileList, 1.2f, 5, 5, freezerColor);
            case 3 -> freezerRace(projectileList, 1.2f, 5, 7, freezerColor);
            case 4 -> freezerRace(projectileList, 0.8f, 5, 7, freezerColor);
            case 5 -> freezerRace(projectileList, 0.8f, 7, 7, freezerColor);
            case 6 -> freezerRace(projectileList, 0.5f, 7, 7, freezerColor);
        }

        /**
         * CIDER MECHANIC
         * the code for the acidic ciders that shoot out at the
         * direction of your cursor and poisons enemies
         */
        switch (acidicCider) {
            case 1 -> acidicCider(projectileList, 1.6f, 2, 0, 3);
            case 2 -> acidicCider(projectileList, 1.6f, 2, 0, 5);
            case 3 -> acidicCider(projectileList, 1.6f, 2, 1, 5);
            case 4 -> acidicCider(projectileList, 1.2f, 2, 1, 5);
            case 5 -> acidicCider(projectileList, 1.2f, 2, 2, 5);
        }

        /**
         * CIDER MECHANIC
         * the code for the champagne that shoots out high-speed corks
         * at the nearest enemy and has a chance to critical strike
         */
        switch (pressureChampagne) {
            case 1 -> pressureChampagne(enemyList, weaponList, projectileList, 4, 20, 0);
            case 2 -> pressureChampagne(enemyList, weaponList, projectileList,  4, 30, 0);
            case 3 -> pressureChampagne(enemyList, weaponList, projectileList,4, 30, 5);
            case 4 -> pressureChampagne(enemyList, weaponList, projectileList, 3, 30, 5);
            case 5 -> pressureChampagne(enemyList, weaponList, projectileList, 3, 40, 5);
        }

        /**
         * Particles for the rainbow shoes
         */
        if (rainbowShoes > 10) {
            if (TimeUtils.millis() - lastRainbow > 20) {
                Texture texture = new Texture("assets/particles/breezer1.png");
                Particle.TRAIL1(2, x + (width / 2) - 2, y, particleList, 2, 8, 8, texture, false);
                lastRainbow = TimeUtils.millis();
            }
        }
        else if (rainbowShoes > 0) {
            if (TimeUtils.millis() - lastRainbow > 100) {
                Texture texture = new Texture("assets/particles/breezer1.png");
                Particle.TRAIL1(2, x + (width / 2) - 2, y, particleList, 2, 8, 8, texture, false);
                lastRainbow = TimeUtils.millis();
            }
        }
    }




    /**
     * This method sets the different animations into texture regions and saves them as animations
     */
    private void setAnimations(){
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 1; i <4; i++){ //walk down
            frames.add(new TextureRegion(getTexture(), i*250, 0, 250, 250));
        }
        playerRunDown = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();
        for(int i = 1; i < 4; i++){ //walk  up
            frames.add(new TextureRegion(getTexture(), i*250, 250, 250,250));
        }
        playerRunUp = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();
        for(int i = 0; i < 3; i++){ //walk left
            frames.add(new TextureRegion(getTexture(), i*250, 2*250, 250,250));
        }
        playerRunLeft = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();
        //run right
        frames.add(new TextureRegion(getTexture(), 3*250, 2*250, 250,250));
        for(int i = 0; i < 2; i++){
            frames.add(new TextureRegion(getTexture(), i*250, 3*250, 250,250));
            System.out.println(width + height);
        }
        playerRunRight = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();
    }


    /**
     * Checks the input from the user and changes the state of the player and throws bottles
     * @param dt delatatime
     * @param projectileList list with Projectiles
     */
    private void checkUserInput(float dt, ArrayList<Projectile> projectileList) {
        setRegion(getFrame(dt));

        currentState = playerController.movePlayer(hitbox, currentState, this);
        playerController.throwBottle(mouse, projectileList, this);
    }


    /**
     * gets the player controller
     * @return playerController
     */
    public IController getPlayerController(){
        return this.playerController;
    }


    /**
     * selects the correct textureRegion depending on the state
     * changes the statetimer depending on the state
     * sets the prevState variable to the current state, since this will be the new previous state
     *
     * @param dt deltatime
     * @return textureRegion of the given direction
     */
    public TextureRegion getFrame(float dt) {
        currentState = getState();
        TextureRegion region = switch (currentState) {
            case RUNNINGRIGHT -> playerRunRight.getKeyFrame(stateTimer, true);
            case RUNNINGDOWN -> playerRunDown.getKeyFrame(stateTimer, true);
            case RUNNINGLEFT -> playerRunLeft.getKeyFrame(stateTimer, true);
            case RUNNINGUP -> playerRunUp.getKeyFrame(stateTimer, true);
            default -> playerStand;
        };

        stateTimer = currentState == prevState ? stateTimer + dt: 0; //if the state changes the timer needs to restart
        prevState = currentState;
        return region;
    }


    @Override
    public void damage(float damage, ArrayList<Particle> particleList) {
        if (TimeUtils.millis() - lastDamaged > 300) {
            HP -= damage;
            lastDamaged = TimeUtils.millis();
            if (hurtSound!=null && isAlive) hurtSound.play(); //for testing constructor
        }

    }


    /**
     * levels up the player when they have gained a set xp value.
     * sets the isleveling to true so the game shows the leveling screen
     * subtracts the xp down to the right value
     * adds a level to the player
     * sets the new XpGoal value
     * Playes leveling sound.
     */
    public void levelUp() {
        if (xp >= xpGoal) {
            setLeveling(true);
            xp = xp - xpGoal;
            level +=1;
            xpGoal = (int) (xpGoal * 1.2);
            levelUpSound.play(0.5f);
        }
    }

    /**
     * Calculates the closest enemy to the player
     */
    public DefaultEnemy closestEnemy(ArrayList<DefaultEnemy> enemyList) {

        DefaultEnemy closest = null;
        float previousDistance = 99999999999f;
        for (DefaultEnemy enemy : enemyList) {
            if (enemy.isAlive) {
            float distanceX = Math.abs(hitbox.x - enemy.hitbox.x);
            float distanceY = Math.abs(hitbox.y - enemy.hitbox.y);
            float distance = distanceX + distanceY;
            System.out.println(distance);
            if (distance < previousDistance) {
                previousDistance = distance;
                closest = enemy;
                System.out.println("SUC");
            }
            }
        }
        return closest;
    }

    public void pressureChampagne(ArrayList<DefaultEnemy> enemyList, ArrayList<Weapon> weaponList, ArrayList<Projectile> projectileList, int cooldown,
                                  int damage, int chance) {
        DefaultEnemy closestEnemy = closestEnemy(enemyList);
        if (closestEnemy != null) {
            if (TimeUtils.millis() - lastChampagne > 1000f * cooldown) {
                Projectile.createChampagne(closestEnemy.x, closestEnemy.y, this, projectileList, damage, 4, weaponList, chance);
                lastChampagne = TimeUtils.millis();
            }
        }
    }

    public void acidicCider(ArrayList<Projectile> projectileList, float cooldown,
                            int damage, int amount, int poisonDuration) {
        if (TimeUtils.millis() - lastCider > 1000 * cooldown) {
            Projectile.createCiderCan(mouse.x, mouse.y, this, projectileList, poisonDuration, damage, amount);
            lastCider = TimeUtils.millis();
        }
    }

    public void freezerRace(ArrayList<Projectile> projectileList, float cooldown,
                            int damage, int poolDuration, int color) {
        if (TimeUtils.millis() - lastFreezer > 1000 * cooldown) {
            Projectile.createFreezer(this, projectileList, color, poolDuration);
            lastFreezer = TimeUtils.millis();
        }
    }






    /**
     * gets the currentstate the player is in
     * This is used for the animation
     * @return currentState of the player
     */
    public State getState() {
        return this.currentState;
    }


    /**
     * sets the currentState variable to the given value
     * @param state value
     */
    public void setState(State state) {
        this.currentState = state;
    }

        /**
         * gets the texture of the player
         * @return texture of player
         */
    public Texture getTexture(){
        return this.player;
    }


    /**
     * Adds xp to the player
     * @param amount of xp added
     */
    public void addXp(int amount) {
            xp += amount;
    }


    public int getXp() {
            return xp;
    }


    /**
     * sets the value of the shotgun variable.
     * the int value refers to which level of the powerup the player has achieved.
     * @param shotgun
     */
    public void setShotgun(int shotgun) {
        this.shotgun = shotgun;
    }


    public int getShotgun() {
        return shotgun;
    }


    public int getFreezerRace() {
        return freezerRace;
    }


    /**
     * sets the value of the freezerRace variable.
     * the int value refers to which level of the powerup the player has achieved.
     * @param freezerRace
     */
    public void setFreezerRace(int freezerRace) {
        this.freezerRace = freezerRace;
    }


    public int getRainbowShoes() {
        return rainbowShoes;
    }


    /**
     * sets the value of the rainbowShoes variable.
     * the int value refers to which level of the powerup the player has achieved.
     * @param rainbowShoes the int variable
     */
    public void setRainbowShoes(int rainbowShoes) {
        this.rainbowShoes = rainbowShoes;
    }


    /**
     * gets set xp goal the player has to reach
     * @return xp goal for player
     */
    public int getXpGoal() {
        return xpGoal;
    }


    /**
     * @return value of isLeveling variable that tells the game if the player is leveling up
     * if it returns true the player is in the leveling up stage and the level up menu should pop out.
     */
    public boolean isLeveling() {
        return isLeveling;
    }


    public void setLeveling(boolean leveling) {
        isLeveling = leveling;
    }


    public int getAcidicCider() {
        return acidicCider;
    }


    public void setAcidicCider(int acidicCider) {
        this.acidicCider = acidicCider;
    }


    public int getLevel() {
        return level;
    }

    public int getPressureChampagne() {
        return pressureChampagne;
    }

    public void setPressureChampagne(int pressureChampagne) {
        this.pressureChampagne = pressureChampagne;
    }

    public Sound getDeathSound() {
        return deathSound;
    }

    public void setDeathSound(Sound deathSound) {
        this.deathSound = deathSound;
    }
}



