package inf112.skeleton.inGameMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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

public class IG_howToPlay {

    public Stage stage;
    private Viewport viewport;
    private Boolean show;
    private Table table;
    private Skin skin;
    private TextureAtlas textureAtlas;
    private SpriteBatch batch;

    public IG_howToPlay(SpriteBatch batch){
        this.show = true;
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
     * sets the values of the rows in the table
     */
    private void fillTable(Table table) {

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        Label movementLabel = new Label("Move with WASD", font);
        movementLabel.setFontScale(2,2);
        Label shootLabel = new Label("LeftClick mouse/mousePad to shoot", font);
        shootLabel.setFontScale(2,2);
        Label pauseLabel = new Label("Press 'P' to pause game, and open menu", font);
        pauseLabel.setFontScale(2,2);
        Label quitGameLabel = new Label("Press 'esc' to quit the game", font);
        quitGameLabel.setFontScale(2,2);

        TextButton backButton = new TextButton("Back", skin);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setShow(false);
            }
        });

        table.add(movementLabel).expandX().padTop(10);
        table.row();
        table.add(shootLabel).expandX().padTop(30);
        table.row();
        table.add(pauseLabel).expandX().padTop(30);
        table.row();
        table.add(quitGameLabel).expandX().padTop(30);
        table.row();
        table.add(backButton).expandX().padTop(30);

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
