package inf112.skeleton.app.screens;

import static org.junit.jupiter.api.Assertions.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.TestApp;
import com.badlogic.gdx.graphics.OrthographicCamera;
import org.junit.jupiter.api.Test;
import inf112.skeleton.screens.Hyttetur;
import org.testng.annotations.AfterClass;


public class HytteturTest {

    private final Hyttetur game;
    private final OrthographicCamera camera;

    public HytteturTest() {
        this.game = new Hyttetur();
        new TestApp(game);
        game.create();

        this.camera = game.getOrthographicCamera();
    }


    @Test
    void gameNotNullTest(){
        assertNotNull(game);
    }


    @Test
    void getOrthographicCameraTest(){
        assertNotNull(camera);
    }


    @AfterClass
    public static void afterAll() {
        Gdx.app.exit();
    }
}
