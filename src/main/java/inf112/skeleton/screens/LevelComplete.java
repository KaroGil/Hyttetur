package inf112.skeleton.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class LevelComplete  {
    
    public Stage stage;
    private Viewport viewport;
    
    private SpriteBatch batch;

    private Texture arrowTextureRight;
    private Texture arrowTextureLeft;
    private Sprite arrowSpriteRight;
    private Sprite arrowSpriteLeft;

    LevelComplete(SpriteBatch batch) {
        
        this.batch = batch;
        
        // Load the arrow texture
        arrowTextureRight = new Texture("assets/arrow_level_comeplete.png");
        arrowTextureLeft = new Texture("assets/arrow_level_comeplete.png");

        // Create a new sprite for the arrow
        arrowSpriteRight = new Sprite(arrowTextureRight);
        arrowSpriteLeft = new Sprite(arrowTextureLeft);

        arrowSpriteRight.setSize(10, 18); 
        arrowSpriteLeft.setSize(10, 18);
        


        
        // Level Complete text 
        viewport = new FitViewport(GameScreen.S_WIDTH, GameScreen.S_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, batch);


        Table table = new Table();
        table.top();
        table.setFillParent(true);
        table.setDebug(false);

        Label levelComplete = new Label("Level Complete", new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        Label goToNextLevel = new Label("Follow the arrows to keep on partying", new Label.LabelStyle(new BitmapFont(), Color.BLUE));
        goToNextLevel.setFontScale(.5f); 
        
        table.add(levelComplete).padTop(20);
        table.row();
        table.add(goToNextLevel);
        

        stage.addActor(table);
        

        
    }

    /*
     * Draw arrows around player
     */
    public void drawBothArrows(float CameraX, float cameraY, float targetX, float targetY) {
        if(targetX < 0 || targetY < 0 ) return;
        drawArrow(arrowSpriteRight, CameraX, cameraY, targetX, targetY, 20, 5);
        drawArrow(arrowSpriteLeft, CameraX, cameraY, targetX, targetY, -20, 5);
    }
    
    /*
     * Draws a single arrow
     */
    private void drawArrow(Sprite arrowSprite, float CameraX, float cameraY, float targetX, float targetY, float offsetX, float offsetY) {
        
        arrowSprite.setPosition(CameraX + offsetX, cameraY - offsetY);
         
        // Calculate the angle between the arrow and the target position
        float angle = MathUtils.atan2(targetX - arrowSprite.getX(), targetY - arrowSprite.getY());


        // Convert the angle to degrees and set the rotation of the arrow sprite
        arrowSprite.setRotation(-MathUtils.radiansToDegrees * angle);
        

        arrowSprite.draw(batch);

    }
    
    /*
     * Render level complete text
     */
    public void drawLevel() {
        stage.act();
        stage.draw();
    }

    /*
     * Disposes rendering
     */
    public void dispose() {
        stage.dispose();
    }
    
}
