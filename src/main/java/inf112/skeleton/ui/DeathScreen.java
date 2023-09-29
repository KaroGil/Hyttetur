package inf112.skeleton.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.screens.GameScreen;

public class DeathScreen {
    public Stage stage;
    private Label youDiedLabel;

    private float scale;

    private Table table;

    private Viewport viewport;

    private Label.LabelStyle fontStyle;

    public DeathScreen(SpriteBatch sb) {
        scale = 4;
        viewport = new FitViewport(GameScreen.S_WIDTH, GameScreen.S_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        table = new Table();
        table.center();
        table.setFillParent(true);
        table.setDebug(false);

        fontStyle = new Label.LabelStyle(new BitmapFont(), new Color(Color.RED));
        fontStyle.font.getData().setScale(scale);
        youDiedLabel = new Label("YOU DIED", new Label.LabelStyle(fontStyle));

        table.add(youDiedLabel);

        stage.addActor(table);
    }

    public void update() {
        fontStyle.font.getData().setScale(scale += 0.1);
    }
}
