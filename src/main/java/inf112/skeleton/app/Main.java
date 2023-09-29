package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import inf112.skeleton.screens.Hyttetur;


public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("Hyttetur");
        cfg.setWindowedMode(1920, 1080);
        cfg.useVsync(true);

        new Lwjgl3Application(new Hyttetur(), cfg);
    }
}
