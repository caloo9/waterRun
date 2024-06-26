package com.mygdx.waterrun;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.mygdx.waterrun.screens.IntroScreen;
import com.mygdx.waterrun.screens.GameScreen;
import com.mygdx.waterrun.screens.MainMenu;
import com.mygdx.waterrun.screens.GameOverScreen;

public class WaterRunMain extends ApplicationAdapter {
	private GameScreen game;
	public IntroScreen intro;
	private GameOverScreen gameover;
	private MainMenu menu;
	private Screen screen;
	private Music bgMusic;
    
    @Override
    public void create() {
    	game = new GameScreen(this);
        gameover = new GameOverScreen(this, 0);
        intro = new IntroScreen(this);
        menu = new MainMenu(this);
        
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("bgmusic.mp3"));
        bgMusic.setLooping(true);
        bgMusic.play();

        
        setScreen(intro);
    }
    
    public void setScreen(Screen screen) {
    	if (this.screen != null) {
            this.screen.hide();
        }
        this.screen = screen;
        if (this.screen != null) {
            this.screen.show();
        }
    }
    
    @Override
    public void render() {
    	if (screen != null) {
            screen.render(Gdx.graphics.getDeltaTime());
        }
    }

    public void gameOver() {
        setScreen(gameover);
    }
    
    @Override
    public void dispose() {
    	if (intro != null) {
            intro.dispose();
        }
        if (game != null) {
            game.dispose();
        }
        if (gameover != null) {
            gameover.dispose();
        }
        if (menu != null)
        {
        	menu.dispose();
        }
        if (bgMusic != null)
        {
        	bgMusic.dispose();
        }
    }
}
