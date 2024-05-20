package com.mygdx.waterrun;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;

public class GameScreen implements Screen{
	private WaterRunMain game;
	private Texture image;
	
	public GameScreen(WaterRunMain game)
	{
		this.game = game;
		image = new Texture ("badlogic.jpg");
	}
		
	@Override
	public void show()
	{
	}
	
	@Override
	public void render(float num)
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		game.batch.draw(image, 100, 100);
		game.batch.end();
	}
	
	@Override
	public void resize(int width, int height)
	{
	}
	
	@Override
	public void dispose()
	{
		image.dispose();
	}
	
	@Override
	public void hide()
	{
	}
	
	@Override
	public void resume()
	{
		
	}
	
	public void pause()
	{
		
	}
}
