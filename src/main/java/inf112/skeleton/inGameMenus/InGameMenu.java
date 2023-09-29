package inf112.skeleton.inGameMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.screens.GameScreen;
import inf112.skeleton.screens.Hyttetur;

public class InGameMenu {

    public Stage stage;
    private Viewport viewport;
    private Boolean show;
    private Table table;
    private Skin skin;
    private TextureAtlas textureAtlas;
    Hyttetur hyttetur;
    private SpriteBatch batch;

    public InGameMenu(SpriteBatch batch, Hyttetur hyttetur) {
        this.show = true;
        this.hyttetur = hyttetur;
        this.viewport = new ScreenViewport();

        this.batch = batch;
        this.stage = new Stage(viewport, batch);
        this.textureAtlas = new TextureAtlas(Gdx.files.internal("assets/menu/glassy-ui.atlas"));
        this.skin = new Skin(Gdx.files.internal("assets/menu/glassy-ui.json"));
        skin.addRegions(textureAtlas);

        Gdx.input.setInputProcessor(stage);

        this.table = makeTable();
        fillTable(table);

        stage.addActor(table);
    }

    /**
     * makes a table for the content of the inGameMenu
     * @return table
     */
    private Table makeTable(){
        table = new Table();
        table.center();
        table.setFillParent(true);
        table.setDebug(false);
        return table;
    }

    /**
     * makes a table for the content of the inGameMenu
     */

    private void fillTable(Table table) {

        TextButton ResumeButton = new TextButton("Resume", skin);
        TextButton RestartButton = new TextButton("Restart game", skin);
        TextButton HowToPlayButton = new TextButton("How to play (press H)", skin);
        TextButton ExitButton = new TextButton("Exit game", skin);

        ResumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setShow(false);
            }
        });
        HowToPlayButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });

        RestartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setShow(false);
                hyttetur.setScreen(new GameScreen(hyttetur, hyttetur.getOrthographicCamera())); // sets the screen to a new game screen
            }
        });

        ExitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        table.add(ResumeButton).expandX().pad(10).size(300, 150);
        table.row();
        table.add(RestartButton).expandX().pad(10).size(400, 150);
        table.row();
        table.add(HowToPlayButton).expandX().pad(10).size(620, 150);
        table.row();
        table.add(ExitButton).expandX().pad(10).size(350, 150);

    }


    /**
     * gets the value of the show variable
     * @return show
     */
    public Boolean show() {
        return show;
    }

    /**
     * sets the value of the show variable to the given value
     * @param show value
     */
    public void setShow(Boolean show) {
        this.show = show;
    }

}
