package inf112.skeleton.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import inf112.skeleton.projectile.Projectile;

import java.util.ArrayList;

public class Controller implements IController {

    @Override
    public Player.State movePlayer(Rectangle hitbox, Player.State state, Player player) {
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            hitbox.x += 10 * (player.getMovementSpeed() * 1.5 + 0.5 * player.getRainbowShoes()) * player.movementSpeedMultiplier * Gdx.graphics.getDeltaTime();
            state = Player.State.RUNNINGRIGHT;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            hitbox.x -= 10 * (player.getMovementSpeed() * 1.5 + 0.5 * player.getRainbowShoes()) * player.movementSpeedMultiplier * Gdx.graphics.getDeltaTime();
            state = Player.State.RUNNINGLEFT;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            hitbox.y -= 10 * (player.getMovementSpeed() * 1.5 + 0.5 * player.getRainbowShoes()) * player.movementSpeedMultiplier * Gdx.graphics.getDeltaTime();
            state = Player.State.RUNNINGDOWN;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            hitbox.y += 10 * (player.getMovementSpeed() * 1.5 + 0.5 * player.getRainbowShoes()) * player.movementSpeedMultiplier * Gdx.graphics.getDeltaTime();
            state = Player.State.RUNNINGUP;
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            state = Player.State.STANDING;
        }

        return state;
    }

    @Override
    public void throwBottle(Rectangle mouse, ArrayList<Projectile> projectileList, Player player) {
        if (Gdx.input.isTouched() && (TimeUtils.millis() - player.getLastShotTime() > 1000 * (1 - (0.1 * player.getAtkSpeed())))) {
            if (player.getShotgun() <= 0) {
                Projectile.createBeerBottle(mouse.x, mouse.y, player, projectileList);
                player.setLastShotTime(TimeUtils.millis());
            } else {
                switch (player.getShotgun()) {
                    case 1 -> Projectile.createBeerCan(mouse.x, mouse.y, player, projectileList, 3, 0);
                    case 2 -> Projectile.createBeerCan(mouse.x, mouse.y, player, projectileList, 4, 0);
                    case 3 -> Projectile.createBeerCan(mouse.x, mouse.y, player, projectileList, 4, 1);
                    case 4 -> Projectile.createBeerCan(mouse.x, mouse.y, player, projectileList, 6, 1);
                    case 5 -> Projectile.createBeerCan(mouse.x, mouse.y, player, projectileList, 6, 2);
                }
                player.setLastShotTime(TimeUtils.millis());
            }
        }


    }}
