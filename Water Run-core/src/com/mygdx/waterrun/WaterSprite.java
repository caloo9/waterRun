package com.mygdx.waterrun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class WaterSprite {
	private Rectangle bound;
	private float a, b;
	private Texture tex;
	private float speed = 200;
	
	public WaterSprite(float a, float b)
	{
		this.a = a;
		this.b = b;
		tex = new Texture("WaterSprite.png");
		bound = new Rectangle(a, b, tex.getWidth(), tex.getHeight());
	}
	
	public void update (float num)
	{
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT));
		{	
			a -= speed * num;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT));
		{
			a += speed * num;
		}
		bound.setPosition(a, b);
	}
	
	public Rectangle getBounds()
	{
		return bound;
	}
	
	public void dispose()
	{
		tex.dispose();
	}
	
	public void render (SpriteBatch batch)
	{
		batch.draw(tex, a, b);
	}
}
