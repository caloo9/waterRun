package com.mygdx.waterrun;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Platform {
	private Texture texture;
	private float x, y;
    private Rectangle bounds;

    public Platform(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
    	texture = new Texture("platform.png");
        bounds = new Rectangle(x, y, width, height);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, bounds.width, bounds.height);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        texture.dispose();
    }
    
    public float getX()
    {
    	return x;
    }
}
