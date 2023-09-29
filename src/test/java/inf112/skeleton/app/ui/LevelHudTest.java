package inf112.skeleton.app.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.TestApp;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import inf112.skeleton.map.MapHandler;
import inf112.skeleton.player.Player;
import inf112.skeleton.screens.GameScreen;
import inf112.skeleton.screens.Hyttetur;
import inf112.skeleton.ui.LevelHud;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class LevelHudTest {

    private final Hyttetur game;
    private OrthographicCamera camera;
    private final LevelHud mockHud;
    private final Player mockplayer;
    private final MapHandler map;
    private final GameScreen gameScreen;


    public LevelHudTest() {
        this.game = new Hyttetur();
        new TestApp(game);

        this.camera = new OrthographicCamera();
        this.gameScreen = new GameScreen(game, camera);

        this.map = new MapHandler(1);
        this.mockplayer = new Player(25,25,25,25,1,map, new Rectangle(), 100, gameScreen );

        this.mockHud = new LevelHud(new SpriteBatch(), mockplayer);

        game.create();
    }


    @Test
    void showTest(){
        mockHud.setShow(false);
        assertFalse(mockHud.show());
    }

    @AfterClass
    public static void afterAll() {
        Gdx.app.exit();
    }
}
