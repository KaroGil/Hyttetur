package inf112.skeleton.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.inGameMenus.IG_howToPlay;
import inf112.skeleton.map.MapHandler;
import inf112.skeleton.player.DefaultEnemy;
import inf112.skeleton.player.Player;
import inf112.skeleton.projectile.Projectile;
import inf112.skeleton.inGameMenus.InGameMenu;
import inf112.skeleton.projectile.*;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import inf112.skeleton.ui.DeathScreen;
import inf112.skeleton.ui.Hud;
import inf112.skeleton.ui.LevelHud;


public class GameScreen extends Game implements Screen {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    Texture crosshair;
    public Player player;
    Rectangle crossbox;

    ArrayList<Particle> particleList = new ArrayList<>();

    ArrayList<Weapon> weaponList = new ArrayList<>();

    ArrayList<AreaOfEffect> areaOfEffectList = new ArrayList<>();
    MapHandler map;

    com.badlogic.gdx.scenes.scene2d.ui.Window levelWindow;
    private boolean levelComplete = false;

    public DefaultEnemy enemy;  //added enemy

    ShapeRenderer renderer;

    Hyttetur hyttetur;
    Viewport viewport;
    Stage stage;

    Hud hud;

    DeathScreen deathScreen;
    LevelHud levelHud;
    InGameMenu inGameMenu;
    public IG_howToPlay ig_howToPlay;


    boolean hRender = false;
    boolean paused = false;
    private boolean gameOver = false;
    private long gameEndedTime = 0;



    public static final int S_WIDTH = 480;
    public static final int S_HEIGHT = 250;

    private LevelComplete levelCompleteUI;

    public GameScreen(Hyttetur game, OrthographicCamera camera) {
        this.hyttetur = game;
        this.camera = camera;
        this.batch = new SpriteBatch();

        viewport = new FitViewport(S_WIDTH, S_HEIGHT, camera);


        this.stage = new Stage(viewport, batch);
        map = new MapHandler(1);


        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.BLUE);
        crosshair = new Texture("assets/crosshair.png");
        renderer = new ShapeRenderer();


        crossbox = new Rectangle();

        player = new Player(30, 23, map.getPlayerSpawnX(), map.getPlayerSpawnY(), 4, map, crossbox, 100, this);
        player.setAtkSpeed(0);
        player.setAtkDamage(0);
        player.setShotgun(0);
        player.setFreezerRace(0);
        player.setRainbowShoes(0);
        player.setAcidicCider(0);
        player.setPressureChampagne(0);

        hud = new Hud(batch);
        deathScreen = new DeathScreen(batch);
        levelHud = new LevelHud(batch, player);
        levelHud.setShow(false);


        crossbox = new Rectangle();

        player = new Player(30, 23, map.getPlayerSpawnX(), map.getPlayerSpawnY(), 4, map, crossbox, 100, this);


        levelHud = new LevelHud(batch, player);
        levelHud.setShow(false);

        inGameMenu = new InGameMenu(batch, hyttetur);
        inGameMenu.setShow(false);
        ig_howToPlay = new IG_howToPlay(batch);
        ig_howToPlay.setShow(false);

        levelCompleteUI = new LevelComplete(batch);
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.BLUE);
        crosshair = new Texture("assets/crosshair.png");
        renderer = new ShapeRenderer();


        crossbox = new Rectangle();


        stage.addActor(levelWindow);


    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        map.getTiledMap().dispose();
        map.getMapRenderer().dispose();
        hud.stage.dispose();
        levelHud.stage.dispose();
        levelCompleteUI.dispose();

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setProjectionMatrix(camera.combined);

        // Handle key input
        handleKeyInput();

        // Handles game over
        if (gameOver) {
            gameOver();
        }

        // Updating camera
        map.getMapRenderer().setView(camera);
        
        float cameraX = checkStopCameraX();
        float cameraY = checkStopCameraY();

        updateCamera(cameraX, cameraY);

        
        // Updates map
        map.renderMap(levelComplete); 

        
        // Update player scores for UI
        hud.update(player);

        
        // Drawing sprites
        // Begin batch for drawing sprites
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        
        drawSprites(batch);
        

        // Handles and sets level completed and what batch should been drawn
        levelComplete(levelComplete, cameraX, cameraY);


        // Updates rendering for player
        updatePlayerOnScreen(particleList, weaponList, areaOfEffectList);
        
        // batch drawing ends
        batch.end();

        // Set mouse position
        Vector3 mousePos = new Vector3();
        mousePos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mousePos);
        crossbox.x = mousePos.x - 8;
        crossbox.y = mousePos.y - 10;

        // Render hitboxes for debugging
        debugHitbox();

        // End renderer
        renderer.end();

        // Handle when player dies
        handlePlayerDie(player);

        // Draw hud
        hud.stage.draw();
        
        // Draw menus when called on
        drawInGameMenus();

        // Handle when leveled up
        drawLevelUp();

        // Handles when game is set to pause
        setGamePause();
        

        // Render extra for screen when level completed
        if (levelComplete) {
            levelCompleteUI.drawLevel();
        }


    }




    /**
     * method to be able to set the state of the game to game over
     */
    public void setGameOver(){
        this.gameOver = true;
        if (gameEndedTime == 0) {
            gameEndedTime = TimeUtils.millis();
        }
        if (TimeUtils.millis() - gameEndedTime > 3000) {
            setScreen(new GameOverScreen(hyttetur, camera));
        }
    }

    /**
     * If any menus is shown set game to pause
     */
    private void setGamePause() {
        if (!inGameMenu.show() && !ig_howToPlay.show() && !levelHud.show()) {
            paused = false;
        } else {
            paused = true;
            Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
        }

    }

    /**
     * If all enemies are cleared on level, level complte is set to true
     */
    private void setLevelCompleted() {
        if((map.getEnemyList().size() <= 0) && (map.getSpawnLimit() <= 0)) {
            levelComplete = true;
        } else {
            levelComplete = false;
        }
    }


    @Override
    public void show() {


    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {

    }

    /**
     * Draw level up hud if player leveled up 
     * */     
    private void drawLevelUp() {
        if (player.isLeveling()) {
            levelHud = new LevelHud(batch, player);
            levelHud.setShow(true);
            player.setLeveling(false);
        }
    }

    /**
     * Update player with new parameters
     * 
     * @param particleList 
     * @param weaponList
     * @param areaOfEffectList
     */
    private void updatePlayerOnScreen(ArrayList<Particle> particleList, ArrayList<Weapon> weaponList, ArrayList<AreaOfEffect> areaOfEffectList) {
        if ((!paused) && (!levelHud.show()) && player.isAlive) {
            player.update(map.getObjectList(), map.getProjectileList(), particleList, map.getEnemyList(), weaponList, areaOfEffectList);
            camera.update();

                
        
        }
    }
    
    /**
     * Handle player and game screen when player dies
     * @param player in game
     */
    private void handlePlayerDie(Player player) {
        playerDie(player);
        drawDeathScreen(player);
    }
    
    /**
     * set player to dead if HP below zero and set game to game over
     * @param player in game
     */
    private void playerDie(Player player) {
        if (player.getHP() <= 0) {
            if (map.getMapMusic() != null) {
                map.getMapMusic().stop();
            }
            player.isAlive = false;
            setGameOver();
        }

    }

    /**
     * Draws player has dies on screen
     * @param player to check if dead
     */
    private void drawDeathScreen(Player player) {
        if (!player.isAlive) {
            deathScreen.stage.draw();
            deathScreen.update();
            if (TimeUtils.millis() - player.getLastDamaged() > 10000) {
                player.getDeathSound().play();
                player.setLastDamaged(TimeUtils.millis());
            }
        }
    }
    
    /**
     * Updates camera with x and y position
     * @param cameraX current x position
     * @param cameraY curren y position
     */
    private void updateCamera(float cameraX,  float cameraY) {
        
        cameraX = checkStopCameraX();
        cameraY = checkStopCameraY();


        camera.position.set(cameraX, cameraY, 0);
    }

    /**
     * Gives x coordinate for camera, stops if at edge of map
     * @return x coordinate as float
     */
    private float checkStopCameraX() {

        float stopCameraXRight = stopCameraXRight();
        float stopCameraXLeft = stopCameraXLeft();

        if(player.getHitbox().x < stopCameraXLeft) {
            return stopCameraXLeft;
        }

        if(player.getHitbox().x > stopCameraXRight) {
            return stopCameraXRight;
        }

        return player.getHitbox().x;

    }

    
    /**
     * Checks where camera should stop width
     * @return max x coordinate
     */
    private int stopCameraXRight() {
        int mapWidthInPixels = map.getMapInPixels("width");
        int stopCameraXpos = mapWidthInPixels - S_WIDTH/2;

        return stopCameraXpos;
    };

    /**
     * Ges screen width
     * @return screen width as integer
     */
    private int stopCameraXLeft() {
        return S_WIDTH/2;
    };

    /**
     * Gives y coordinate for camera, stops if at edge of map
     * @return y coordinate as float
     */
    private float checkStopCameraY() {

        float stopCameraYTop = stopCameraYTop();
        
        float stopCameraYBot = stopCameraYBot();
  

        if(player.getHitbox().y < stopCameraYBot) {
            return stopCameraYBot;
        }

        if(player.getHitbox().y > stopCameraYTop) {
            return stopCameraYTop;
        }

        return player.getHitbox().y;


    }
    
    /**
     * Checks where camera should stop height
     * @return max y coordinate
     */
    private int stopCameraYTop() {
        int mapHeightInPixels = map.getMapInPixels("height");
        int stopCameraYpos = mapHeightInPixels - S_HEIGHT/2;

        return stopCameraYpos;
    }
    /**
     * Ges screen height
     * @return screen width as integer
     */
    private int stopCameraYBot() {
        return S_HEIGHT/2;
    }

        
    /**
     * Sets screen to game over if game finished
     */
    private void gameOver(){
        if(TimeUtils.millis() - gameEndedTime > 3000){
            GameOverScreen gameOver = new GameOverScreen(hyttetur, camera);
            hyttetur.setScreen(gameOver);
        }
    }

    /**
     * Sets screen to game complete if game finished
     */
    private void gameComplete() {

        GameComepletedScreen gameCompleted = new GameComepletedScreen(hyttetur, camera);
        hyttetur.setScreen(gameCompleted);
    }

    
    /**
     * Handles level complete - draw arrows to show exit on map. If new level exists it is loaded if not game completed is laoded
     * @param levelComplete true if level completed
     * @param cameraX x coordinate for camera
     * @param cameraY y coordinate for camera
     */
    private void levelComplete(boolean levelComplete, float cameraX, float cameraY) {
        setLevelCompleted();
        if (levelComplete && !paused) {
            levelCompleteUI.drawBothArrows(cameraX, cameraY, map.getExitX(), map.getExitY());
            int nextLevel = player.checkNextLevel(map.getNextLevel());

            if (nextLevel == map.getMaxLevel()) {
                gameComplete();
                return;
            }

            if ((nextLevel > 0)) { // if player stands on next level and level cleared - new map is loaded
                map.loadNewMap(nextLevel);
                player.movePlayerToSpawn();
                
            }

        }
    }

    /**
     * Draw interfaces
     */
    private void drawInGameMenus() {
        if (levelHud.show()) {
            levelHud.stage.draw();
            levelHud.stage.act();
        }

        if (inGameMenu.show()) {
            inGameMenu.stage.act();
            inGameMenu.stage.draw();
        }


        if (ig_howToPlay.show()) {
            ig_howToPlay.stage.act();
            ig_howToPlay.stage.draw();
        }
    }

    /**
     * Draw spritebatches
     * @param batch to draw with
     */
    private void drawSprites(SpriteBatch batch) {
        // Drawing player
        batch.draw(player, player.getX() - 3.5f, player.getY(), player.getWidth() + 7, player.getHeight());
        
        // Drawing area affects
        drawAreaEffects(batch);
        // Drawing projectiles
        drawProjectiles(batch);
        // Drawing enemies
        renderEntities(batch);

        // Drawing weapons
        for (Weapon weapon : weaponList) {
            weapon.render(batch);
        }

        // Drawing particles
        for (Particle particle : particleList) {
            particle.render(batch);
            particle.update();
        }

        // Drawing system cursor
        if(!paused && !levelHud.show()) {
            batch.draw(crosshair, crossbox.x, crossbox.y);
            Gdx.graphics.setSystemCursor(Cursor.SystemCursor.None);
        }

        
    }
    
    /**
     * Draw area effects
     * @param batch 
     */
    private void drawAreaEffects(SpriteBatch batch) {
        for (AreaOfEffect areaOfEffect : areaOfEffectList) {
            areaOfEffect.render(batch);
            areaOfEffect.update(map.getEnemyList(), player);
            if (hRender) {
                if (areaOfEffect.isActive()) {
                    renderer.rect(areaOfEffect.getHitbox().x, areaOfEffect.getHitbox().y, areaOfEffect.getHitbox().width, areaOfEffect.getHitbox().height);
                }
            }
        }
    }

    /**
     * Draw projectiles 
     * @param batch 
     */
    private void drawProjectiles(SpriteBatch batch) {
        /*
        For every projectile currently spawned, update every projectiles location and draws them
        */
        for (Projectile projectile : map.getProjectileList()) {
            if (projectile.isAlive) {
                //batch.draw(projectile.getTexture(), projectile.getX(), projectile.getY(), projectile.getWidth(), projectile.getHeight());
                projectile.render(batch);
                if (!paused) {
                    if (!levelHud.show()) {
                        projectile.update(particleList);
                    }
                }
                if (hRender) {
                    renderer.rect(projectile.getHitbox().x, projectile.getHitbox().getY(), projectile.getHitbox().getWidth(), projectile.getHitbox().getHeight());
                }
            }
        }
    }

    /**
     * Draw enemies and update alive enemies
     * @param batch 
     */
    private void renderEntities(SpriteBatch batch) {
        
        map.spawnEntities(player);

        ArrayList<DefaultEnemy> aliveEnimies = new ArrayList<>();

        for (DefaultEnemy enemy : map.getEnemyList()) {
            if (enemy.getSprite() == null) {
                throw new IllegalArgumentException("no sprite");
            }
            if (enemy.isAlive) {
                batch.draw(enemy.getSprite(), enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
                if (!paused) {
                    if (!levelHud.show()) {
                        enemy.update(map.getProjectileList(), map.getEnemyList(), map.getObjectList(), hud, particleList, areaOfEffectList);
                        aliveEnimies.add(enemy);
                    }
                }
                if (hRender) {
                    renderer.rect(enemy.getHitbox().x, enemy.getHitbox().y, enemy.getHitbox().width, enemy.getHitbox().height);
                }
            }

        }

        if(!paused) {
            map.setEnemyList(aliveEnimies);  // updating enenmy list 
        } 
        
    }

    
    /**
     * Keys to handle input with
     */
    private void handleKeyInput() {
        // Debug hitboxer
        if (Gdx.input.isKeyPressed(Input.Keys.L)) {
            hRender = !hRender;
        }
        
        if (Gdx.input.isKeyPressed(Input.Keys.P)) {
            inGameMenu = new InGameMenu(batch, hyttetur);
            inGameMenu.setShow(true);
        }
        
        if (Gdx.input.isKeyPressed(Input.Keys.H) && inGameMenu.show()) {
            ig_howToPlay = new IG_howToPlay(batch);
            ig_howToPlay.setShow(true);
            if (inGameMenu.show()) {
                inGameMenu.setShow(false);
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

    }

    /**
     * Render hitboxes for debugging
     */
    private void debugHitbox() {
        if (hRender) {
            renderer.rect(player.getHitbox().x, player.getHitbox().y, player.getHitbox().width, player.getHitbox().height);
            renderer.rect(crossbox.x, crossbox.y, crosshair.getWidth(), crosshair.getHeight());
            for (Rectangle object : map.getObjectList()) {
                renderer.rect(object.x, object.y, object.width, object.height);
            }
        }
    }

}
