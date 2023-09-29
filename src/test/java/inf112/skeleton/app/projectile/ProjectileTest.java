package inf112.skeleton.app.projectile;

import static org.junit.jupiter.api.Assertions.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.backends.lwjgl3.TestApp;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import inf112.skeleton.map.MapHandler;
import inf112.skeleton.player.Player;
import inf112.skeleton.projectile.Effect;
import inf112.skeleton.projectile.Projectile;
import inf112.skeleton.screens.GameScreen;
import inf112.skeleton.screens.Hyttetur;
import org.junit.jupiter.api.Test;
import org.testng.annotations.AfterClass;

import java.util.ArrayList;

public class ProjectileTest {

    private final Hyttetur game;
    private final Player mockplayer;
    private final MapHandler map;
    private final GameScreen gameScreen;
    private OrthographicCamera camera;
    private static ArrayList<Projectile> projectileList;
    private Projectile mockProjectile;
    private Rectangle mouse;
    private Sound tSound;
    private Sound hSound;


    public ProjectileTest() {
        this.game = new Hyttetur();
        new TestApp(game);
        this.camera = new OrthographicCamera();
        this.gameScreen = new GameScreen(game, camera);
        this.mouse = new Rectangle();
        this.map = new MapHandler(1);
        this.mockplayer = new Player(25,25,25,25,1,map, new Rectangle(), 100, gameScreen );

        this.tSound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/beer_throw.ogg"));
        this.hSound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/beer_hit.ogg"));
        this.mockProjectile = new Projectile("beer",new Texture("assets/projectiles/beer.png"), 10,10,10,10,1,10,10,mockplayer, 10, tSound,hSound );

        game.create();
    }


    @Test
    void getSpeedTest(){
        assertEquals(1, mockProjectile.getSpeed());
    }

    @Test
    void setSpeed(){
        mockProjectile.setSpeed(10);
        assertEquals(10, mockProjectile.getSpeed());
    }

    @Test
    void getAngle(){
        double angle = Math.atan2(mockProjectile.getDistanceY(), mockProjectile.getDistanceX());
        assertEquals(angle, mockProjectile.getAngle());
    }

    @Test
    void getOwnerTest(){
        assertEquals(mockplayer, mockProjectile.getOwner());
    }

    @Test
    void getTextureTest() {
        Texture mockTex = new Texture("assets/projectiles/beer.png");
        Texture mockTex2 = new Texture("assets/crosshair.png");
        assertEquals(mockProjectile.getTexture().toString(), mockTex.toString());
        assertNotEquals(mockProjectile.getTexture().toString(), mockTex2.toString());
    }


    @Test
    void setTextureTest() {
        Texture mockTex = new Texture("assets/projectiles/beer.png");
        assertEquals(mockProjectile.getTexture().toString(), mockTex.toString());

        Texture mockTex2 = new Texture("assets/crosshair.png");

        mockProjectile.setTexture(mockTex2);

        assertEquals(mockProjectile.getTexture(), mockTex2);
        assertNotEquals(mockProjectile.getTexture().toString(), mockTex.toString());
    }

    @Test
    void getDamageTest(){
        assertEquals(mockProjectile.getDamage(), 10);
    }

    @Test
    void getSoundTest(){
        assertEquals(tSound, mockProjectile.getThrowSound());
        assertEquals(hSound, mockProjectile.getHitSound());
    }


    @Test
    void effectTest(){
        mockProjectile.setEffect(new Effect(10,10,"POISON"));
        assertEquals("POISON", mockProjectile.getEffect().getName());
    }

    @Test
    void dimensionsTest(){
        assertEquals(10, mockProjectile.getWidth());
        assertEquals(10, mockProjectile.getHeight());
    }

    @Test
    void getHitbox(){
        Rectangle hitbox = new Rectangle(mockProjectile.getX(), mockProjectile.getY(), mockProjectile.getWidth(), mockProjectile.getHeight());
        assertEquals(hitbox, mockProjectile.getHitbox());
    }

    @AfterClass
    public static void afterAll() {
        Gdx.app.exit();
    }
}
