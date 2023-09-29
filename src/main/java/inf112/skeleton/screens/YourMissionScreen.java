package inf112.skeleton.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class YourMissionScreen implements Screen {

    private Viewport viewport;
    private Stage stage;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Hyttetur hyttetur;
    private Skin skin;
    private TextureAtlas textureAtlas;
    private Texture background;
    private Music menuMusic;

    public YourMissionScreen(Hyttetur hyttetur, OrthographicCamera camera) {
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

        stage = new Stage(viewport, batch);

        background = new Texture("assets/menu/MenuBackground.jpg");
        this.menuMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/sounds/music/the-incident-soundroll-main-version-16775-01-32.mp3"));
    }

    /**
     * Creates the table with YourMission
     */
    @Override
    public void show() {

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        Label one = new Label("Welcome to Hyttetur!", font);
        one.setFontScale(2,2);
        Label two = new Label("The goal of the game is to get through the cabin and pour all the other guests", font);
        two.setFontScale(2,2);
        Label three = new Label("But be careful, they will also try to pour you!", font);
        three.setFontScale(2,2);
        Label four = new Label("Throw alcohol on them to make them disappear", font);
        four.setFontScale(2,2);
        Label five = new Label("Fight all of them on each level and you will be shown the way to the next level", font);
        five.setFontScale(2,2);
        Label six = new Label("After a certain number of pours you will get to choose powerups, which make you even more efficient!", font);
        six.setFontScale(2,2);
        Label seven = new Label("Good luck!", font);
        seven.setFontScale(2,2);

        TextButton backButton = new TextButton("Back", skin);
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        table.add(one).expandX().padTop(10);
        table.row();
        table.add(two).expandX().padTop(30);
        table.row();
        table.add(three).expandX().padTop(30);
        table.row();
        table.add(four).expandX().padTop(30);
        table.row();
        table.add(five).expandX().padTop(30);
        table.row();
        table.add(six).expandX().padTop(30);
        table.row();
        table.add(seven).expandX().padTop(30);
        table.row();
        table.add(backButton).expandX().padTop(30);

        stage.addActor(table);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hyttetur.setScreen(new MainMenu(hyttetur, camera));
                dispose();
            }
        });
    }


    /**
     * Draws and puts the table on the screen
     */
    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
