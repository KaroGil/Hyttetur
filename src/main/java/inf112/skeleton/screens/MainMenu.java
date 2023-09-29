package inf112.skeleton.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.graphics.GL20;


public class MainMenu implements Screen {

    private Viewport viewport;
    private Stage stage;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Hyttetur hyttetur;
    private Skin skin;
    private TextureAtlas textureAtlas;
    private Texture background;
    private Music menuMusic;



    public MainMenu(Hyttetur hyttetur, OrthographicCamera camera) {
        this.hyttetur = hyttetur;
        this.camera = camera;
        this.batch = new SpriteBatch();

        this.textureAtlas = new TextureAtlas(Gdx.files.internal("assets/menu/glassy-ui.atlas"));
        this.skin = new Skin(Gdx.files.internal("assets/menu/glassy-ui.json"));
        skin.addRegions(textureAtlas);

        this.viewport = new ScreenViewport();
        viewport.apply();

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        batch.setProjectionMatrix(camera.combined);
        camera.update();

        this.stage = new Stage(viewport, batch);
        this.background = new Texture("assets/menu/MenuBackground.jpg");
        this.menuMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/sounds/music/the-incident-soundroll-main-version-16775-01-32.mp3"));
    }

    /**
     * Creates the table with MainMenu, and gives the buttons their functions
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        TextButton playButton = new TextButton("Play", skin);
        TextButton OptionsButton = new TextButton("Whats your mission?", skin);
        TextButton howToPlayButton = new TextButton("How to play", skin);
        TextButton ExitButton = new TextButton("Exit game", skin);

        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hyttetur.setScreen(new GameScreen(hyttetur, camera)); // sets the screen to the game screen
                dispose();
                playButton.remove();
            }
        });
        OptionsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hyttetur.setScreen(new YourMissionScreen(hyttetur, camera)); // sets the screen to the options screen
                dispose();
                OptionsButton.remove();
            }
        });
        howToPlayButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hyttetur.setScreen(new HowToPlayScreen(hyttetur, camera)); // sets the screen to the howToPlay screen
                dispose();
                howToPlayButton.remove();
            }
        });
        ExitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        table.add(playButton).expandX().pad(10).size(300, 150);
        table.row();
        table.add(OptionsButton).expandX().pad(10).size(600, 150);
        table.row();
        table.add(howToPlayButton).expandX().pad(10).size(400, 150);
        table.row();
        table.add(ExitButton).expandX().pad(10).size(350, 150);

        stage.addActor(table);
    }

    /**
     * Draws and puts the table on the screen
     */
    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow); // Needed since cursor is removed in game screen
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, 1920, 1080);
        stage.getBatch().end();

        if (menuMusic != null) {
            menuMusic.setLooping(true);
            menuMusic.setVolume(0.2f);
            menuMusic.play();
        }

        stage.act();
        stage.draw();

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

    }


    @Override
    public void resize(int i, int i1) {

    }


    @Override
    public void pause() {

    }


    @Override
    public void resume() {

    }


    @Override
    public void hide() {

    }


    @Override
    public void dispose() {
        skin.dispose();
        textureAtlas.dispose();
        stage.dispose();
        batch.dispose();
        menuMusic.dispose();
    }
}
