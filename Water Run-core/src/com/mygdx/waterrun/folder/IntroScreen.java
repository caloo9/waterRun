package com.mygdx.waterrun.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.waterrun.WaterRunMain;

public class IntroScreen extends WaterRunScreens {
	private Texture introTexture;
	TextureRegion intro;
	SpriteBatch batch;
	float time = 0;
	OrthographicCamera camera;

	public IntroScreen (WaterRunMain game) {
		super(game);
	}

	@Override
	public void show () {
		introTexture = new Texture(Gdx.files.internal("start.png"));
        intro = new TextureRegion(introTexture);
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void render (float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(intro, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end();

		time += delta;
		if (time > 1) {
			if (Gdx.input.isKeyPressed(Keys.ANY_KEY) || Gdx.input.justTouched()) {
				game.setScreen(new GameScreen(game));
			}
		}
	}

	@Override
	public void hide () {
		Gdx.app.debug("WaterRun", "dispose intro");
		batch.dispose();
		intro.getTexture().dispose();
	}
}
