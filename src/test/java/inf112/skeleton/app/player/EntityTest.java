package inf112.skeleton.app.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.TestApp;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import inf112.skeleton.map.MapHandler;
import inf112.skeleton.player.Entity;
import inf112.skeleton.player.Player;
import inf112.skeleton.screens.GameScreen;
import inf112.skeleton.screens.Hyttetur;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EntityTest {

    private final Hyttetur game;
    private final Entity mockEntity;
    private final MapHandler map;
    private final GameScreen gameScreen;
    private OrthographicCamera camera;

    public EntityTest() {
        this.game = new Hyttetur();
        new TestApp(game);
        this.camera = new OrthographicCamera();
        this.gameScreen = new GameScreen(game, camera);

        this.map = new MapHandler(1);
        this.mockEntity = new Player(25,25,25,25,1,map, new Rectangle(), 100, gameScreen );

        game.create();
    }


    @Test
    void isAliveTest(){
        assertTrue(mockEntity.isAlive());
    }


    @Test
    void checkEdgeTest(){
        assertFalse(mockEntity.checkEdge());

        mockEntity.getHitbox().x = -1;

        assertTrue(mockEntity.checkEdge());
    }


    @Test
    void aktSpeedTest(){
        mockEntity.setAtkSpeed(2);
        assertEquals(2, mockEntity.getAtkSpeed());
    }


    @Test
    void aktDamageTest(){
        mockEntity.setAtkDamage(2);
        assertEquals(2, mockEntity.getAtkDamage());
    }


    @Test
    void centerTest(){
        mockEntity.setCenterY(10);
        assertEquals(10,mockEntity.getCenterY());
        mockEntity.setCenterX(10);
        assertEquals(10,mockEntity.getCenterX());
    }

    @Test
    void lastPoisonedTest(){
        mockEntity.setLastPoisoned(1);
        assertEquals(1, mockEntity.getLastPoisoned());
    }


    @Test
    void damageTest(){
        mockEntity.damage(50, null);
        assertEquals(50,mockEntity.getHP());
    }


    @Test
    void lastShotTimeTest(){
        mockEntity.setLastShotTime(50);
        assertEquals(50, mockEntity.getLastShotTime());
    }


    @AfterClass
    public static void afterAll() {
        Gdx.app.exit();
    }

}
