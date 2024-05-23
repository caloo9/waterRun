package com.mygdx.waterrun;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class WaterRunMain extends ApplicationAdapter {
	SpriteBatch batch;
    Player player;
    Texture background;
    PlatformGenerator platformGenerator;
    OrthographicCamera camera;

    @Override
    public void create() {
        batch = new SpriteBatch();
        player = new Player(100, 300);
        background = new Texture("background.png");
        
        platformGenerator = new PlatformGenerator(player);
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void render() {
        player.handleInput();
        player.update();

        // Update camera position to follow the player
        camera.position.x = player.getX() + Gdx.graphics.getWidth() / 4; // Adjust as needed
        camera.update();
        
        platformGenerator.update();
        platformGenerator.checkCollisions(player);
        
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, camera.position.x - camera.viewportWidth / 2, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        player.render(batch);
        platformGenerator.render(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        player.dispose();
        platformGenerator.dispose();
    }
}