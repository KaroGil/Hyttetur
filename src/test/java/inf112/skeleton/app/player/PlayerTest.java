
package inf112.skeleton.app.player;

import static org.junit.jupiter.api.Assertions.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.TestApp;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import inf112.skeleton.map.MapHandler;
import inf112.skeleton.projectile.Particle;
import inf112.skeleton.screens.GameScreen;
import inf112.skeleton.screens.Hyttetur;
import inf112.skeleton.ui.Hud;
import org.junit.jupiter.api.Test;
import inf112.skeleton.player.Player;
import org.testng.annotations.AfterClass;
import java.util.ArrayList;


public class PlayerTest {

    private final Hyttetur game;
    private final Player mockplayer;
    private final MapHandler map;
    private final GameScreen gameScreen;
    private OrthographicCamera camera;

    private Hud hud;

    public PlayerTest() {
        this.game = new Hyttetur();
        new TestApp(game);
        this.camera = new OrthographicCamera();
        this.gameScreen = new GameScreen(game, camera);

        this.map = new MapHandler(1);
        this.mockplayer = new Player(25,25,25,25,1,map, new Rectangle(), 100, gameScreen );

        game.create();
    }


    @Test
    void getXgetYTest() {
        assertEquals(25, mockplayer.getX());
        assertEquals(25, mockplayer.getY());
    }

    @Test
    void getHeightandWidthTest() {
        assertEquals(25, mockplayer.getHeight());
        assertEquals(25, mockplayer.getWidth());
    }

    @Test
    void mapNotNull(){
        assertNotNull(map);
    }

    @Test
    void getTextureTest() {
        Texture mockTex = new Texture("assets/main_character_moment.png");
        Texture mockTex2 = new Texture("assets/crosshair.png");
        assertEquals(mockplayer.getTexture().toString(), mockTex.toString());
        assertNotEquals(mockplayer.getTexture().toString(), mockTex2.toString());
    }


    @Test
    void getFrameTest() {
        Player.State mockState = Player.State.RUNNINGDOWN;
        TextureRegion mockRegion = mockplayer.getFrame(1);
        mockplayer.setState(Player.State.RUNNINGDOWN);

        assertEquals(mockRegion.getTexture().hashCode(), mockplayer.getFrame(1).getTexture().hashCode());
    }

    @Test
    void getStateTest() {
        mockplayer.setState(Player.State.RUNNINGDOWN);
        assertEquals(Player.State.RUNNINGDOWN, mockplayer.getState());
    }

    @Test
    void getIsAliveTest() {
        assertTrue(mockplayer.isAlive);
    }

    @Test
    void damageTest(){
        mockplayer.damage(50, new ArrayList<Particle>());
        assertEquals(50, mockplayer.HP);
    }

    @Test
    void xpTest(){
        assertEquals(0, mockplayer.getXp());

        mockplayer.addXp(10);

        assertEquals(10, mockplayer.getXp());
    }

    @Test
    void speedTest(){
       assertEquals(1, mockplayer.getMovementSpeed());
       mockplayer.setMovementSpeed(5);
       assertEquals(5, mockplayer.getMovementSpeed());

    }


    @AfterClass
    public static void afterAll() {
        Gdx.app.exit();
    }
}

/* * # Manual Test
 * 1. Play the game and check that the player is facing the way you are moving, ex. pressing D should make the player sprite face the right direction.
 * 2. Play the game and check that when you aren't pressing any keys the player should look straight at you, out of the screen.
 */



