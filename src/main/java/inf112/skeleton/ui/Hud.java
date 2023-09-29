package inf112.skeleton.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.player.Player;
import inf112.skeleton.screens.GameScreen;


public class Hud {
    public Stage stage;
    private Viewport viewport;
    private Image hpBar;
    private Integer Score;
    private Label scoreLabel;
    private Image scoreImage;
    private Label xpLabel;
    private Label hpLabel;
    private Label levelLabel;

    private Table table;


    public Hud(SpriteBatch sb) {
        Score = 0;

        viewport = new FitViewport(GameScreen.S_WIDTH, GameScreen.S_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        table = new Table();
        table.top();
        table.setFillParent(true);
        table.setDebug(false);

        levelLabel = new Label(String.format("", 0), new Label.LabelStyle(new BitmapFont(), Color.BLUE));
        scoreLabel = new Label(String.format("%02d", Score), new Label.LabelStyle(new BitmapFont(), Color.ROYAL));
        xpLabel = new Label(String.format("", 0), new Label.LabelStyle(new BitmapFont(), Color.BLUE));
        hpBar = new Image(new Texture("assets/hpBar.png"));
        scoreImage = new Image(new Texture("assets/kill_count.png"));
        hpLabel = new Label(String.format("%01d",  0), new Label.LabelStyle(new BitmapFont(), Color.GREEN));

        table.add(levelLabel).padBottom(10).left().top().padLeft(5);
        table.add(hpBar).padBottom(10).width(18).height(20).left().padLeft(10);
        table.add(hpLabel).expandX().left().padBottom(10);
        table.add(xpLabel).expandX().top().left();
        table.add(scoreImage).padBottom(10).right();
        table.add(scoreLabel).padBottom(10).right().padRight(10);

        stage.addActor(table);

    }


    /**
     * update method for the hud
     * this method updates the values of the items shown in the hud
     * @param player player the hud displays values for
     */
    public void update(Player player) {
        scoreLabel.setText(getScore());
        hpLabel.setText(player.HP);
        xpLabel.setText(player.getXp() + " / " + player.getXpGoal());
        levelLabel.setText(player.getLevel());
    }

    public void dispose() {
        stage.dispose();
    }

    public void setScore(Integer score) {
        Score = score;
    }


    /**
     * gets the score value
     * @return score
     */
    public Integer getScore() {
        return Score;
    }

}
