package inf112.skeleton.app.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.TestApp;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import inf112.skeleton.screens.Hyttetur;
import inf112.skeleton.ui.Power;
import org.junit.jupiter.api.Test;
import org.testng.annotations.AfterClass;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PowerTest {

    private final Hyttetur game;
    private final Power mockPower;
    private final Image image;


    public PowerTest() {
        this.game = new Hyttetur();
        new TestApp(game);

        this.image = new Image(new Texture("assets/hud/attack_damage_power.png"));
        this.mockPower = new Power("pewpew", "goesPewPew", 1, image, 1);

        game.create();
    }


    @Test
    void getNameTest(){
        assertEquals("pewpew", mockPower.getName());
    }


    @Test
    void getDescriptionTest(){
        assertEquals("goesPewPew", mockPower.getDescription());
    }


    @Test
    void getLevelTest(){
        assertEquals(1, mockPower.getLevel());
    }


    @Test
    void getRarityTest(){
        assertEquals(1, mockPower.getRarity());
    }


    @Test
    void getPictureTest(){
        assertEquals(image, mockPower.getPicture());
    }

    @Test
    void acidic_ciderTest(){
        Power cider = Power.ACIDIC_CIDER(1);

        assertEquals("Acidic Cider",cider.getName());
        assertEquals("Occasionally shoots out a poisonous cider where you're aiming",cider.getDescription());
        assertEquals(1,cider.getRarity());
        assertEquals(1,cider.getLevel());


        Power cider2 = Power.ACIDIC_CIDER(2);
        assertEquals("Poison lasts longer",cider2.getDescription());


        Power cider3 = Power.ACIDIC_CIDER(3);
        assertEquals("+ 1 cider can",cider3.getDescription());


        Power cider4 = Power.ACIDIC_CIDER(4);
        assertEquals("Shoots cider cans more frequently",cider4.getDescription());

    }


    @Test
    void attackSpeedTest(){
        Power speed = Power.ATTACK_SPEED(1);

        assertEquals("Syringe",speed.getName());
        assertEquals("Increases attack speed of your regular fire",speed.getDescription());
        assertEquals(0,speed.getRarity());
        assertEquals(1,speed.getLevel());

    }


    @AfterClass
    public static void afterAll() {
        Gdx.app.exit();
    }

}
