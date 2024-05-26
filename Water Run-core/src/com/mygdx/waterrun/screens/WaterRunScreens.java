package com.mygdx.waterrun.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.mygdx.waterrun.WaterRunMain;

public abstract class WaterRunScreens implements Screen{
	WaterRunMain game;
	
	public WaterRunScreens(WaterRunMain game)
	{
		this.game = game;
	}
	
	@Override
	public void resize(int width, int height)
	{
	
	}
	
	@Override
	public void show () {
	}

	@Override
	public void hide () {
	}

	@Override
	public void pause () {
	}

	@Override
	public void resume () {
	}

	@Override
	public void dispose () {
	}
}
