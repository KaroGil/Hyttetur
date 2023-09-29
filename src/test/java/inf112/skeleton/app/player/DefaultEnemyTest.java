package inf112.skeleton.app.player;

import static org.junit.jupiter.api.Assertions.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.TestApp;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import inf112.skeleton.map.MapHandler;
import inf112.skeleton.player.DefaultEnemy;
import inf112.skeleton.player.Player;
import inf112.skeleton.screens.GameScreen;
import inf112.skeleton.screens.Hyttetur;
import inf112.skeleton.ui.Hud;
import org.junit.jupiter.api.Test;
import org.testng.annotations.AfterClass;
import java.util.ArrayList;


public class DefaultEnemyTest {
    private final Hyttetur game;
    private final Player mockplayer;
    private final MapHandler map;
    private final GameScreen gameScreen;
    private OrthographicCamera camera;
    private final DefaultEnemy mockEnemy;
    private Texture tex;
    private Sprite sprite;
    private Hud hud;



    public DefaultEnemyTest() {
        this.game = new Hyttetur();
        new TestApp(game);
        this.camera = new OrthographicCamera();
        this.gameScreen = new GameScreen(game, camera);

        this.hud = new Hud(new SpriteBatch());

        this.tex = new Texture("assets/deafult_enemy_1.png");
        this.sprite = new Sprite(tex);

        this.map = new MapHandler(1);
        this.mockplayer = new Player(25,25,25,25,1,map, new Rectangle(), 100, gameScreen );

        this.mockEnemy = new DefaultEnemy(10,10,1,10, map, 100, new ArrayList<DefaultEnemy>(), mockplayer, 5);
        game.create();
    }


    @Test
    void dieTest() {
        mockEnemy.setHp(0);
        mockEnemy.die(hud);
        assertFalse(mockEnemy.isAlive());
        assertEquals(5, mockplayer.getXp());

    }

    @Test
    void setHpTest(){
        mockEnemy.setHp(1000);
        assertEquals(1000, mockEnemy.HP);
    }


    @Test
    void spriteTest(){
        assertEquals(sprite.getTexture().toString(), mockEnemy.getSprite().getTexture().toString());

        Sprite sprite2 = new Sprite(new Texture("assets/hp.png"));
        mockEnemy.setSprite(sprite2);

        assertEquals(sprite2, mockEnemy.getSprite());
    }


    @Test
    void getAttackDamage(){
        assertEquals(10,mockEnemy.getAttackDamage());
    }


    @Test
    void checkHitboxTest(){
        assertEquals(mockEnemy.getHitbox().x, 10);
        assertEquals(mockEnemy.getHitbox().y,10);

        mockplayer.setX(11);
        mockplayer.setY(11);

        mockEnemy.checkHitbox();
    }


    @AfterClass
    public static void afterAll() {
        Gdx.app.exit();
    }

}
