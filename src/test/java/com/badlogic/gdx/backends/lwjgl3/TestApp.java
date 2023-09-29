package com.badlogic.gdx.backends.lwjgl3;

import com.badlogic.gdx.*;
import com.badlogic.gdx.backends.headless.*;
import com.badlogic.gdx.backends.headless.mock.audio.MockAudio;
import com.badlogic.gdx.backends.lwjgl3.audio.Lwjgl3Audio;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Clipboard;

import static com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application.createGlfwWindow;
import static com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application.initializeGlfw;
import static org.lwjgl.glfw.GLFW.glfwTerminate;

public class TestApp implements Application, Lwjgl3ApplicationBase{

    private final ApplicationListener listener;
    private final Lwjgl3ApplicationConfiguration config;
    private final Lwjgl3Window window;
    private final Array<Runnable> runnables;
    private final Array<Runnable> executedRunnables;


    public TestApp(ApplicationListener appListener){
        HeadlessNativesLoader.load();
        Lwjgl3NativesLoader.load();
        initializeGlfw();
        setApplicationLogger(new HeadlessApplicationLogger());

        this.config = new Lwjgl3ApplicationConfiguration();
        config.setInitialVisible(false);
        config.setWindowedMode(500,500);
        config.setTitle("testApp");

        this.listener = appListener;
        this.runnables = new Array<>();
        this.executedRunnables = new Array<>();

        this.window = new Lwjgl3Window(listener, config, this);
        window.create(createGlfwWindow(config, 0));
        window.makeCurrent();

        Gdx.app = this;
        Gdx.files = new HeadlessFiles();
        Gdx.audio = new MockAudio();
        Gdx.net = new HeadlessNet(new HeadlessApplicationConfiguration());
        Gdx.input = new DefaultLwjgl3Input(window);
    }


    public void render(){
        boolean shouldRequestRendering;

        synchronized (runnables) {
            shouldRequestRendering = runnables.size > 0;
            executedRunnables.clear();
            executedRunnables.addAll(runnables);
            runnables.clear();
        }

        for (final Runnable runnable : new Array.ArrayIterator<>(executedRunnables)) {
            runnable.run();
        }

        if (shouldRequestRendering && !window.getGraphics().isContinuousRendering())
            window.requestRendering();
    }

    @Override
    public Lwjgl3Audio createAudio(Lwjgl3ApplicationConfiguration lwjgl3ApplicationConfiguration) {
        return null;
    }

    @Override
    public Lwjgl3Input createInput(Lwjgl3Window lwjgl3Window) {
        return null;
    }

    @Override
    public ApplicationListener getApplicationListener() {
        return null;
    }

    @Override
    public Graphics getGraphics() {
        return null;
    }

    @Override
    public Audio getAudio() {
        return Gdx.audio;
    }

    @Override
    public Input getInput() {
        return window.getInput();
    }

    @Override
    public Files getFiles() {
        return Gdx.files;
    }

    @Override
    public Net getNet() {
        return Gdx.net;
    }

    @Override
    public void log(String s, String s1) {

    }

    @Override
    public void log(String s, String s1, Throwable throwable) {

    }

    @Override
    public void error(String s, String s1) {

    }

    @Override
    public void error(String s, String s1, Throwable throwable) {

    }

    @Override
    public void debug(String s, String s1) {

    }

    @Override
    public void debug(String s, String s1, Throwable throwable) {

    }

    @Override
    public void setLogLevel(int i) {

    }

    @Override
    public int getLogLevel() {
        return 0;
    }

    @Override
    public void setApplicationLogger(ApplicationLogger applicationLogger) {

    }

    @Override
    public ApplicationLogger getApplicationLogger() {
        return null;
    }

    @Override
    public ApplicationType getType() {
        return null;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public long getJavaHeap() {
        return 0;
    }

    @Override
    public long getNativeHeap() {
        return 0;
    }

    @Override
    public Preferences getPreferences(String s) {
        return null;
    }

    @Override
    public Clipboard getClipboard() {
        return null;
    }

    @Override
    public void postRunnable(Runnable runnable) {
        synchronized (runnables) {
            runnables.add(runnable);
        }
    }

    @Override
    public void exit() {
        window.dispose();

        glfwTerminate();
    }

    @Override
    public void addLifecycleListener(LifecycleListener lifecycleListener) {

    }

    @Override
    public void removeLifecycleListener(LifecycleListener lifecycleListener) {

    }
}
