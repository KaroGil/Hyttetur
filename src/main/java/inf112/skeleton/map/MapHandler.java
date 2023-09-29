package inf112.skeleton.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import inf112.skeleton.player.DefaultEnemy;
import inf112.skeleton.player.Player;
import inf112.skeleton.projectile.Projectile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class MapHandler {

    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer mapRenderer;
    private ArrayList<Rectangle> objectList;
    private ArrayList<MapObject> nextLevel;

    ArrayList<DefaultEnemy> enemyList; // Her må det endres på
    ArrayList<Projectile> projectileList;

    private int currentLevel;
    private long lastSpawnTime = 0L;

    private int spawnLimit;

    Rectangle crossbox;

    private float playerSpawnX;
    private float playerSpawnY;

    private int maxLevel;

    private static HashMap<Integer, String> levels;
    private static HashMap<Integer, Integer> levelSpawnLimit;

    private Music mapMusic;

    
    static {
        levels = new HashMap<>();
        levels.put(0, "assets/maps/mainMap.tmx");
        levels.put(1, "assets/maps/level_1.tmx");
        levels.put(2, "assets/maps/level_2.tmx");
        levels.put(3, "assets/maps/level_3.tmx");

    }
    
    static {
        levelSpawnLimit = new HashMap<>();
        levelSpawnLimit.put(0, 9999);
        levelSpawnLimit.put(1, 5);
        levelSpawnLimit.put(2, 5);
        levelSpawnLimit.put(3, 10);

    }





    public MapHandler(int level) {
        this.currentLevel = level;
        this.crossbox = new Rectangle();
        
        loadNewMap(currentLevel);
        

    }

    /**
     * Loads new map and updates instance variables according to maps layers and objects
     * @param level to load
     */
    public void loadNewMap(int level) {
        this.currentLevel = level;
        this.enemyList = new ArrayList<>();
        this.projectileList = new ArrayList<>();

        this.tiledMap = new TmxMapLoader().load(levels.get(level));
        this.spawnLimit = levelSpawnLimit.get(level);
        
        int checkMaxLevel = level + 1;

        if(levels.get(checkMaxLevel) == null) this.maxLevel = checkMaxLevel;
        
        this.mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        this.objectList = getRectanglesLayer("Collide");

        music();

        
        setLocSpawnPlayer();

        nextLevel = new ArrayList<>();
        try {
            for (MapObject object : tiledMap.getLayers().get("NextLevel").getObjects().getByType(RectangleMapObject.class)) {
                nextLevel.add(object);
            }
        } catch  (Exception e) {

        }
    }

    /**
     * method for testing purposes
     * @return currentlevel
     */
    public int getLevel(){
        return this.currentLevel;
    }


    /**
     * spawns entities to the map
     * @param targetPlayer player the enemies will target
     */
    public void spawnEntities(Player targetPlayer) {
        if (currentLevel == 0) {
            if (spawnLimit > 0) {
                if ((TimeUtils.millis() - 1000) > lastSpawnTime) {
                    Random r = new Random();
                    int randomX = r.nextInt(getTiledMap().getProperties().get("width", Integer.class) * tiledMap.getProperties().get("tile".concat("width"), Integer.class));
                    DefaultEnemy enemy = new DefaultEnemy(randomX, 0, 3, 5, this, 25, enemyList, targetPlayer, 5);
                    lastSpawnTime = TimeUtils.millis();
                }
            }
        }
        if (currentLevel == 1) {
            if (spawnLimit > 0) {
                if ((TimeUtils.millis() - 4000) > lastSpawnTime) {
                    DefaultEnemy enemy = new DefaultEnemy(550, 650, 3, 5, this, 25, enemyList, targetPlayer, 5);
                    spawnLimit -=1;
                    lastSpawnTime = TimeUtils.millis();
                    }
                }
            }
        if (currentLevel == 2) {
            if (spawnLimit > 0) {
                if ((TimeUtils.millis() - 2000) > lastSpawnTime) {
                    DefaultEnemy enemy = new DefaultEnemy(900, 50, 3, 5, this, 25, enemyList, targetPlayer, 5);
                    DefaultEnemy enemy2 = new DefaultEnemy(400, 500, 3, 5, this, 25, enemyList, targetPlayer, 5);
                    DefaultEnemy enemy3 = new DefaultEnemy(500, 800, 3, 5, this, 25, enemyList, targetPlayer, 5);
                    spawnLimit -=1;
                    lastSpawnTime = TimeUtils.millis();
                }
            }
        }
        if (currentLevel == 3) {
            if (spawnLimit > 0) {
                if ((TimeUtils.millis() - 2000) > lastSpawnTime) {
                    DefaultEnemy enemy2 = new DefaultEnemy(350, 600, 3, 5, this, 25, enemyList, targetPlayer, 5);
                    DefaultEnemy enemy3 = new DefaultEnemy(1200, 600, 3, 5, this, 25, enemyList, targetPlayer, 5);
                    spawnLimit -=1;
                    lastSpawnTime = TimeUtils.millis();
                }
            }
        }


    }

    /**
     * Code for the music that plays in the
     * background during the different levels
     */

    public void music() {
        if (mapMusic != null) {
            mapMusic.stop();
        }
        if (currentLevel == 0) {
            mapMusic = null;
        }
        if (currentLevel == 1) {
            mapMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/sounds/music/cans_and_bottles_1.mp3"));
        }
        if (currentLevel == 2) {
            mapMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/sounds/music/cans_and_bottles_2.mp3"));
        }
        if (currentLevel == 3) {
            mapMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/sounds/music/cans_and_bottles_3.mp3"));
        }
        if (currentLevel > 3) {
            mapMusic.stop();
        }

        if (mapMusic != null) {
            mapMusic.setLooping(true);
            mapMusic.setVolume(0.5f);
            mapMusic.play();
        }
    }


    /**
     * gets the tiled map
     * @return tiledMap
     */
    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public int getMaxLevel() {
        return this.maxLevel;
    }


    /**
     * gets the map properties
     * @return properties of the tiled map
     */
    public MapProperties getMapProperties() {
        return tiledMap.getProperties();
    }

    /*
     * Returns map coordinates in pixel
     */
    public int getMapInPixels(String dimension) {
        MapProperties mapProperties = getMapProperties();
        return mapProperties.get(dimension, Integer.class) * mapProperties.get("tile".concat(dimension), Integer.class);
    }

    /**
     * Get rectangles in object layer
     * @param layer
     * @return list with rectangels
     */
    private ArrayList<Rectangle> getRectanglesLayer(String layer) {
        
        ArrayList<Rectangle> rectangles = new ArrayList<>();
        
        for (MapObject object : tiledMap.getLayers().get(layer).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            rectangles.add(rect);
        }

        return rectangles;
    }
    
    
    /**
     * Sets x and y coordinate for player spawn - assuming only one spawn
     */
    private void setLocSpawnPlayer() {
        ArrayList<Rectangle> rectangles = getRectanglesLayer("PlayerSpawn");

        for (Rectangle rec : rectangles) {
            setPlayerSpawnX(rec);
            setPlayerSpawnY(rec);
        }

    }
    
    /**
     * Sets x coordinate for player spawn
     * @param rec
     */
    private void setPlayerSpawnX(Rectangle rec) {
        this.playerSpawnX = rec.getX();
    }

    /**
     * Sets y coordinate for player spawn
     * @param rec
     */
    private void setPlayerSpawnY(Rectangle rec) {
        this.playerSpawnY = rec.getY();
    }

    /**
     * 
     * @return x coordinate for player spawn
     */
    public float getPlayerSpawnX() {
        return playerSpawnX;
    }

    /**
     * 
     * @return y coordinate for player spawn
     */
    public float getPlayerSpawnY() {
        return playerSpawnY;
    }


    public OrthogonalTiledMapRenderer getMapRenderer() {
        return mapRenderer;
    }

    /*
     * Stops rendering layer NewLevelLayer when level is true
     */
    public void renderMap(boolean levelComplete) {
        
        MapLayer layerToExclude = null;

        if(levelComplete) {
            layerToExclude = tiledMap.getLayers().get("NewLevelLayer");
        
        }
        
        OrthogonalTiledMapRenderer renderer = getMapRenderer();
        
        renderer.getBatch().begin();
        for (MapLayer layer : tiledMap.getLayers()) {
            if (layer == layerToExclude) {
                continue;
            }
            if (layer instanceof TiledMapTileLayer) {
                // Render each tile layer individually
                renderer.renderTileLayer((TiledMapTileLayer) layer);
            }
        
        }

        renderer.getBatch().end();
    }

    /*
     * Gets x coordinate of exit
     */
    public float getExitX() {
        if(nextLevel.size() == 0) {
            return -1;
        }
        MapObject objectLevel = nextLevel.get(0);

        Rectangle rect = ((RectangleMapObject) objectLevel).getRectangle();

        return rect.getX();
    }

    /*
     * Gets y coordinate of exit
     */
    public float getExitY() {
        if(nextLevel.size() == 0) {
            return -1;
        }
        
        MapObject objectLevel = nextLevel.get(0);

        Rectangle rect = ((RectangleMapObject) objectLevel).getRectangle();

        return rect.getY();

    }

    public ArrayList<Rectangle> getObjectList() {
        return objectList;
    }

    public ArrayList<MapObject> getNextLevel() {
        return nextLevel;
    }

    public int getSpawnLimit() {
        return spawnLimit;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setTiledMap(TiledMap tiledMap) {
        this.tiledMap = tiledMap;
    }


    /**
     * gets the enemy list
     * @return enemyList
     */
    public ArrayList<DefaultEnemy> getEnemyList() {
        return enemyList;
    }

    public void setEnemyList(ArrayList<DefaultEnemy> enemies) {
        this.enemyList = enemies;
    }

    public ArrayList<Projectile> getProjectileList() {
        return projectileList;
    }

    public void setProjectileList(ArrayList<Projectile> projectileList) {
        this.projectileList = projectileList;
    }

    /**
     * gets the crossbox
     * @return crossbox
     */
    public Rectangle getCrossbox() {
        return crossbox;
    }

    public Music getMapMusic() {
        return mapMusic;
    }

    public void setMapMusic(Music mapMusic) {
        this.mapMusic = mapMusic;
    }
}

