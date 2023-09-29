package inf112.skeleton.player;

import com.badlogic.gdx.math.Rectangle;
import inf112.skeleton.projectile.Projectile;
import java.util.ArrayList;

public interface IController {

    /**
     * Moves the player accordingly to the key's pressed.
     * This method also updates the hitbox of the player and changes the state of the player, such that the animations work.
     * @param hitbox of the player
     * @param state of the player (animation)
     */
    public Player.State movePlayer(Rectangle hitbox, Player.State state, Player player);

    /**
     * Throws a bottle when the player clicks
     * @param mouse
     * @param projectileList
     * @param player
     */
    public void throwBottle(Rectangle mouse, ArrayList<Projectile> projectileList, Player player);


}
