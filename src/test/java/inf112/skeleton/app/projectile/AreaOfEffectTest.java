package inf112.skeleton.app.projectile;

import com.badlogic.gdx.graphics.Texture;
import inf112.skeleton.player.Player;
import inf112.skeleton.projectile.AreaOfEffect;
import inf112.skeleton.projectile.Effect;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AreaOfEffectTest {

    private static AreaOfEffect mockAreaOfEffect;

    private static Player player;


    @BeforeAll
    static void setUp() {
        mockAreaOfEffect = new AreaOfEffect(new Texture("assets/projectiles/strawberry_pool.png"),null, 5, 5, 0, 0,
                3, player);
    }


    @Test
    void setActive(){
        mockAreaOfEffect.setActive(true);
        assertTrue(mockAreaOfEffect.isActive());
    }


}