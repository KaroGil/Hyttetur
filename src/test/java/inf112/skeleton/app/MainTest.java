package inf112.skeleton.app;

import static org.junit.jupiter.api.Assertions.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.TestApp;
import org.junit.jupiter.api.Test;
import inf112.skeleton.screens.Hyttetur;
import org.testng.annotations.AfterClass;


public class MainTest {

    private final Hyttetur game;

    public MainTest() {
        this.game = new Hyttetur();
        new TestApp(game);

        game.create();
    }


    @Test
    void gameNotNullTest(){
        assertNotNull(game);
    }


    @AfterClass
    public static void afterAll() {
        Gdx.app.exit();
    }
}
