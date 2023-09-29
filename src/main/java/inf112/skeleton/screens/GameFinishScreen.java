package inf112.skeleton.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public class GameFinishScreen implements Screen {
    
    private final Hyttetur game;
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final Texture gameOverTexture;
    
    private final BitmapFont fontGameOver;
    private final GlyphLayout layoutGameOver;
    
    private final BitmapFont fontInstruction;
    private final GlyphLayout layoutInstruction;



    public GameFinishScreen(Hyttetur game, OrthographicCamera camera, String bg_file, String finishText) {
        this.game = game;
        batch = new SpriteBatch();
        this.camera = camera;
        gameOverTexture = new Texture(bg_file);
        
        fontGameOver = createFont(5f, Color.YELLOW);
        layoutGameOver = createLayout(fontGameOver, finishText);

        fontInstruction = createFont(3f, Color.YELLOW);
        layoutInstruction = createLayout(fontInstruction, "(Press any key to return to main menu)");

        
    }

    @Override
    public void show() {
        
        
    }

    @Override
    public void render(float delta) {
        
        // Clear the screen with a black color
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        // Draw the game over texture in the center of the screen
        batch.begin();
        batch.draw(gameOverTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
        // Draw the "Game Over" text in the center of the screen
        fontGameOver.draw(batch, layoutGameOver, (Gdx.graphics.getWidth() - layoutGameOver.width) / 2f,
                (Gdx.graphics.getHeight() + layoutGameOver.height) / 1.7f);

        fontInstruction.draw(batch, layoutInstruction, (Gdx.graphics.getWidth() - layoutInstruction.width) / 2f,
                (Gdx.graphics.getHeight() + layoutInstruction.height) / (2f));

        batch.end();
        

        // If the user touches the screen, go back to the main menu
        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            game.setScreen(new MainMenu(game, camera));
            dispose();
            
        }

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    @Override
    public void resize(int width, int height) {

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
        batch.dispose();
        gameOverTexture.dispose();
        fontGameOver.dispose();
        fontInstruction.dispose();
    }

    /**
     * Creates font
     * 
     * @param scale float indicating scale of the font
     * @param color color of font
     * @return font object
     */
    private BitmapFont createFont(Float scale, Color color) {
        
        BitmapFont font = new BitmapFont();
        font.getData().setScale(scale);
        font.setColor(color);

        return font;
        
        
    }

    /**
     * Creates layout
     * @param font to use
     * @param text displaying on screen
     * @return layout object
     */
    private GlyphLayout createLayout(BitmapFont font, String text) {
        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, text);

        return layout;
    }
}
