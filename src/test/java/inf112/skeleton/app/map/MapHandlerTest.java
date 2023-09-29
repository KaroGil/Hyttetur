package inf112.skeleton.app.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.TestApp;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import inf112.skeleton.map.MapHandler;
import inf112.skeleton.screens.GameScreen;
import inf112.skeleton.screens.Hyttetur;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapHandlerTest {


    private final Hyttetur game;
    private final MapHandler map;
    private final GameScreen gameScreen;
    private OrthographicCamera camera;
    private TiledMap tiledMap1;


    public MapHandlerTest() {
        this.game = new Hyttetur();
        new TestApp(game);
        this.camera = new OrthographicCamera();
        this.gameScreen = new GameScreen(game, camera);

        this.tiledMap1 = new TmxMapLoader().load("assets/maps/level_1.tmx");

        this.map = new MapHandler(1);

        game.create();
    }




    @Test
    void loadNewMapTest(){
        map.loadNewMap(0);
        assertEquals(0,map.getLevel());
    }

    @Test
    void getMapHeightTest() {
        assertEquals(640, map.getMapInPixels("height"));
    }

    @Test
    void getMapWitdthTest() {
        assertEquals(640, map.getMapInPixels("width"));
    }


    @Test
    void getSpawnLimitTest() {
        assertEquals(5, map.getSpawnLimit());
    }
    
    @Test
    void getMaxLevelTest() {
        map.loadNewMap(1);
        assertEquals(0, map.getMaxLevel());

        map.loadNewMap(2);
        assertEquals(0, map.getMaxLevel());

        // Max level is recorded when last map is loaded
        map.loadNewMap(3);
        assertEquals(map.getLevel() + 1, map.getMaxLevel());
    }

    @Test
    void getPlayerSpawnXTest() {
        assertEquals(315.33, Math.round(map.getPlayerSpawnX() * 100.0) / 100.0); // From tiled coorinates two spawn obejct
        
        map.loadNewMap(2);
        assertEquals(610.5, Math.round(map.getPlayerSpawnX() * 100.0) / 100.0); // From tiled coorinates two spawn obejct
        
        map.loadNewMap(3);
        assertEquals(890.00, Math.round(map.getPlayerSpawnX() * 100.0) / 100.0); // From tiled coorinates two spawn obejct
        
    }

    @Test
    void getPlayerSpawnYTest() {
        // Map is inverted in height, expected value is height: - y coordinate - height of spawn rec
        assertEquals(Math.round((640-601.33-8) * 100.0) / 100.0, Math.round(map.getPlayerSpawnY() * 100.0) / 100.0); // From tiled coordinates two spawn object
    
        map.loadNewMap(2);
        assertEquals(Math.round((960-938-11) * 100.0) / 100.0, Math.round(map.getPlayerSpawnY() * 100.0) / 100.0);

        map.loadNewMap(3);
        assertEquals(Math.round((960 - 939.25 -11.25) * 100.0) / 100.0, Math.round(map.getPlayerSpawnY() * 100.0) / 100.0);
    }

    @Test
    void getExitMapXTest() {
        assertEquals(544.75,  Math.round(map.getExitX() * 100.0) / 100.0);
    }

    @Test 
    void getExitMapYtest() {
        // Map is inverted in height, expected value is height: - y coordinate - height of spawn rec
        assertEquals(Math.round((640-0.5-46.75) * 100.0) / 100.0, Math.round(map.getExitY() * 100.0) / 100.0);
    }


    @AfterClass
    public static void afterAll() {
        Gdx.app.exit();
    }
}
