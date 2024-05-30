package com.mygdx.waterrun.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.waterrun.Player;
import com.mygdx.waterrun.WaterRunMain;

public class GameOverScreen extends WaterRunScreens{
	Texture overTexture;
	TextureRegion over;
	SpriteBatch batch;
	float time = 0;
	OrthographicCamera camera;
	BitmapFont font;
	int finalScore;
	Player player;

	public GameOverScreen (WaterRunMain game, int finalScore) {
		super(game);
		this.finalScore = finalScore;
	}

	@Override
	public void show () {
		overTexture = new Texture(Gdx.files.internal("gameover.png"));
		over = new TextureRegion(overTexture);
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
        
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Water-Regular.otf")); 
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 62;
        font = generator.generateFont(parameter);
        generator.dispose(); // Dispose to avoid memory leaks
	}

	@Override
	public void render (float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(over, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		

		// Draw the score
		String scoreText = "Final Score: " + finalScore;
        float textWidth = font.getRegion().getRegionWidth();
        float x = (Gdx.graphics.getWidth()) / 2 - 170;
        float y = Gdx.graphics.getHeight() / 2 - 250;
        
        font.draw(batch, scoreText, x, y);

        batch.end();
		time += delta;
		if (time > 1) {
			if (Gdx.input.isKeyPressed(Keys.SPACE)) {
				game.setScreen(new MainMenu(game));
			}
		}
	}

	@Override
	public void hide () {
		Gdx.app.debug("WaterRun", "dispose intro");
		batch.dispose();
		over.getTexture().dispose();
	}
}
