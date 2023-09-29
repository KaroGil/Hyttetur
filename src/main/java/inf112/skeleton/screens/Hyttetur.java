package inf112.skeleton.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;


public class Hyttetur extends Game {

    public static Hyttetur INSTANCE;
    protected int widthScreen, heightScreen;
    private OrthographicCamera orthographicCamera;
    public SpriteBatch batch;
    public GameScreen game;
    public MainMenu menu;
    public List<String> levels = List.of("assets/maps/mainMap.tmx");

    /**
     * Creates an instance of the game
     */
    public Hyttetur() {
        INSTANCE = this;
    }


    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.widthScreen = Gdx.graphics.getWidth();
        this.heightScreen = Gdx.graphics.getHeight();
        this.orthographicCamera = new OrthographicCamera();
        this.orthographicCamera.setToOrtho(false, widthScreen, heightScreen);
        setScreen(new MainMenu(INSTANCE, orthographicCamera));

    }


    @Override
    public void dispose() {
        batch.dispose();
    }


    /**
     * gets the game
     * @return game
     */
    public GameScreen getGame() {return this.game;}


    /**
     * gets the orthographic camera
     * @return orthographicCamera
     */
    public OrthographicCamera getOrthographicCamera() {
        return this.orthographicCamera;
    }


    /**
     * gets the menu
     * @return menu
     */
    public MainMenu getMenu() {
        return this.menu;
    }

}

