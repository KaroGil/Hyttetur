package inf112.skeleton.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameComepletedScreen extends GameFinishScreen {

    public GameComepletedScreen(Hyttetur game, OrthographicCamera camera) {
        super(game, camera, "assets/menu/game_completed_bg.png", "CONGRATULATIONS! YOU SURVIVED HYTTETUR");
        
    }
    
}
