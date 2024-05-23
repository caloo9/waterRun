package com.mygdx.waterrun;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class WaterRunMain extends ApplicationAdapter {
	SpriteBatch batch;
    Player player;
    Texture background;
    ArrayList<Platform> platforms;

    @Override
    public void create() {
        batch = new SpriteBatch();
        player = new Player(100, 300);
        background = new Texture("background.png");
        
        platforms = new ArrayList<>();
        platforms.add(new Platform(100, 100, 200, 20));
        platforms.add(new Platform(400, 200, 200, 20));
    }

    @Override
    public void render() {
        player.handleInput();
        player.update();

        for (Platform platform : platforms) {
            player.checkPlatformCollision(platform);
        }
        
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        player.render(batch);
        for (Platform platform : platforms) {
            platform.render(batch);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        player.dispose();
        for (Platform platform : platforms) {
            platform.dispose();
        }
    }
}