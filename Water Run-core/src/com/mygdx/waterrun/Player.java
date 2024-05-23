package com.mygdx.waterrun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Player {
	private Texture texture;
    private float x, y;
    private float velocityX, velocityY;
    private float gravity = -0.5f;
    private Rectangle bounds;

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
        texture = new Texture("WaterSprite.png");
        bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
    }

    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            velocityX = -5;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            velocityX = 5;
        } else {
            velocityX = 0;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && y == 0) {
            velocityY = 10;
        }
    }

    public void update() {
        x += velocityX;
        y += velocityY;
        velocityY += gravity;

        if (y < 0) {
            y = 0;
            velocityY = 0;
        }

        bounds.setPosition(x, y);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public void dispose() {
        texture.dispose();
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void checkPlatformCollision(Platform platform) {
        Rectangle platformBounds = platform.getBounds();
        if (x + texture.getWidth() > platformBounds.x && x < platformBounds.x + platformBounds.width &&
            y > platformBounds.y && y < platformBounds.y + platformBounds.height) {
            y = platformBounds.y + platformBounds.height;
            velocityY = 0;
        }
    }
}
