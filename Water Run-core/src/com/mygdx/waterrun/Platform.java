package com.mygdx.waterrun;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
public class Platform {
	private float a, b;
	private Rectangle bound;
	private Texture tex;
	
	public Platform(float a, float b)
	{
		this.a = a;
		this.b = b;
		tex = new Texture("platform.png");
		bound = new Rectangle(a, b, tex.getWidth(), tex.getHeight());
	}
	
	public Rectangle getBounds()
	{
		return bound;
	}
	
	public void render(SpriteBatch batch)
	{
		batch.draw(tex, a, b);
	}
	
	public void dispose()
	{
		tex.dispose();
	}
}
