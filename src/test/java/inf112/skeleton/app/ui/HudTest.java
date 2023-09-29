package inf112.skeleton.app.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.TestApp;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.screens.Hyttetur;
import inf112.skeleton.ui.Hud;
import org.junit.jupiter.api.Test;
import org.testng.annotations.AfterClass;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HudTest {

    private final Hyttetur game;
    private OrthographicCamera camera;
    private Hud mockHud;

    public HudTest() {
        this.game = new Hyttetur();
        new TestApp(game);
        this.mockHud = new Hud(new SpriteBatch());

        game.create();
    }

    @Test
    void scoreTest(){
        mockHud.setScore(10);
        assertEquals(10, mockHud.getScore());
    }


    @AfterClass
    public static void afterAll() {
        Gdx.app.exit();
    }
}
