package inf112.skeleton.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameOverScreen extends GameFinishScreen {
    
    GameOverScreen(Hyttetur game, OrthographicCamera camera) {
        super(game, camera, "assets/menu/game_over_bg.png", "GAME OVER");
    }
}
