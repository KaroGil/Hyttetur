package inf112.skeleton.projectile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.TimeUtils;
import inf112.skeleton.player.Entity;

import java.util.ArrayList;

public class Effect {

    protected String name;
    protected int duration;
    protected long firstInitiation;
    protected long lastInitiation;
    public int value;
    protected Sound sound1;

    protected boolean active;

    public Effect(int duration, int value, String name) {
        this.duration = duration;
        this.value = value;
        firstInitiation = TimeUtils.millis();
        this.name = name;
        active = true;
    }


    public static Effect POISON(int duration, int damage) {


        return new Effect(duration, damage, "POISON"){
            @Override
            public void apply(Entity target, ArrayList<Particle> particleList) {
                if (active) {
                    if (TimeUtils.millis() - lastInitiation > 1000) {
                        target.damage(damage, particleList);
                        sound1 = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/poison_hurt.ogg"));
                        sound1.play(0.5f);
                        lastInitiation = TimeUtils.millis();
                        target.setLastPoisoned(TimeUtils.millis());
                    }
                    }
                if (TimeUtils.millis() - firstInitiation > 1000L * duration) {
                    active = false;
                }
            }
        };
    }

    public static Effect SLOW(int duration, int slowLevel) {


        return new Effect(duration, slowLevel, "SLOW"){
            @Override
            public void apply(Entity target, ArrayList<Particle> particleList) {
                if (active) {
                    target.setMovementSpeedMultiplier(0.5f - (0.1f * slowLevel));
                }
                if (TimeUtils.millis() - firstInitiation > 1000L * duration) {
                    target.setMovementSpeedMultiplier(1);
                    active = false;
                }

            }
        };
    }


    public void apply(Entity target, ArrayList<Particle> particleList) {
    }

    public String getName() {
        return name;
    }

    public int getValue() {return value;}

    public int getDuration() {
        return duration;
    }

    public boolean isActive() {
        return active;
    }
}
