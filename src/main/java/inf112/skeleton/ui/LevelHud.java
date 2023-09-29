package inf112.skeleton.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.player.Player;
import inf112.skeleton.screens.GameScreen;
import java.util.ArrayList;
import java.util.Random;


public class LevelHud {
    public Stage stage;
    private Viewport viewport;
    private Power choice1;
    private Power choice2;
    private Power choice3;
    private Label choice1Text;
    private Label choice2Text;
    private Label choice3Text;
    private ImageButton choice1Button;
    private ImageButton choice2Button;
    private ImageButton choice3Button;
    private Image background;
    private Label.LabelStyle textLabel;
    private ArrayList<Power> powerList;
    private Random random;
    private Boolean show;
    private Table table;
    private Image buttonImage;


    public LevelHud(SpriteBatch sb, Player player) {
        this.show = true;
        this.viewport = new FitViewport(GameScreen.S_WIDTH, GameScreen.S_HEIGHT, new OrthographicCamera());
        this.stage = new Stage(viewport, sb);
        this.powerList = new ArrayList<>();
        this.buttonImage = new Image(new Texture("assets/hud/choose_button.png"));
        this.random = new Random();

        Gdx.input.setInputProcessor(stage);

        addPowers(player, powerList);

        this.background = new Image(new Texture("assets/hud/lbl_background_1.png"));
        this.textLabel = new Label.LabelStyle(new BitmapFont(), Color.ROYAL);
        this.textLabel.background = background.getDrawable();
        this.textLabel.font.getData().setScale(0.5f);

        this.choice1 = addChoice();
        this.choice2 = addChoice();
        this.choice3 = addChoice();

        this.table = makeTable();

        this.choice1Text = addText(choice1);
        this.choice2Text = addText(choice2);
        this.choice3Text = addText(choice3);

        this.choice1Button = makeButton(player, choice1);
        this.choice2Button = makeButton(player, choice2);
        this.choice3Button = makeButton(player, choice3);

        stage.addActor(table);

        setTable(choice1, choice1Text, choice1Button);
        setTable(choice2, choice2Text, choice2Button);
        setTable(choice3, choice3Text, choice3Button);

    }


    /**
     * makes a table for the content of the levelHud
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
     * @param choice picture of the choice of levelup
     * @param choiceText text corresponding to the levelup item
     * @param choiceButton button to click to choose the powerup
     */
    private void setTable(Power choice, Label choiceText, ImageButton choiceButton){
        table.add(choice.getPicture()).left().size(50f);
        table.add(choiceText).fill().padLeft(10);
        table.add(choiceButton).padLeft(5);
        table.row();
    }


    /**
     * adds buttons to the table in the levelHud
     * @param player to which the power will be added to
     * @param choice which power the button refers to
     */
    private ImageButton makeButton(Player player, Power choice){
        ImageButton choiceButton = new ImageButton(buttonImage.getDrawable());
        choiceButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (choice.getName().equals("Syringe")) {
                    player.setAtkSpeed(player.getAtkSpeed() + 1);
                }
                if (choice.getName().equals("Shattering")) {
                    player.setAtkDamage(player.getAtkDamage() + 1);
                }
                if (choice.getName().equals("Freezer Race")) {
                    player.setFreezerRace(player.getFreezerRace() + 1);
                }
                if (choice.getName().equals("Shotgun")) {
                    player.setShotgun(player.getShotgun() + 1);
                }
                if (choice.getName().equals("Rainbow Shoes")) {
                    player.setRainbowShoes(player.getRainbowShoes() + 1);
                }
                if (choice.getName().equals("Acidic Cider")) {
                    player.setAcidicCider(player.getAcidicCider() + 1);
                }
                if (choice.getName().equals("Pressure Champagne")) {
                    player.setPressureChampagne(player.getPressureChampagne() + 1);
                }
                choiceButton.remove();
                show = false;
            }
        });
        return choiceButton;
    }


    /**
     * adds text to the levelHud table
     * @param choice the power in reference
     * @return label to be added to the table
     */
    private Label addText(Power choice){
        return new Label("       " + choice.getName() + "\n" + "       " + choice.getDescription() + "\n" + "       " + "Level: " + choice.getLevel(), textLabel);
    }


    /**
     * adds a choice to the levelHud table
     * @return choice of power
     */
    private Power addChoice(){
        Power choice = powerList.get(random.nextInt(powerList.size()));
        powerList.remove(choice);
        return choice;
    }


    /**
     * adds powers to the powerList
     * @param player that can have the power
     * @param powerList list to add the powers to
     */
    private void addPowers(Player player, ArrayList<Power> powerList){
        powerList.add(Power.FREEZER_RACE(player.getFreezerRace() + 1));
        powerList.add(Power.SHOTGUN(player.getShotgun() + 1));
        powerList.add(Power.ATTACK_DAMAGE(player.getAtkDamage() + 1));
        powerList.add(Power.ATTACK_SPEED(player.getAtkSpeed() + 1));
        powerList.add(Power.MOVEMENT_SPEED(player.getRainbowShoes() + 1));
        powerList.add(Power.ACIDIC_CIDER(player.getAcidicCider() + 1));
        powerList.add(Power.PRESSURE_CHAMPAGNE(player.getPressureChampagne() + 1));
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
