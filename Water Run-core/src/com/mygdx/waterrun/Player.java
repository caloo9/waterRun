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
    private boolean isOnGround;
    private boolean isOnPlatform;

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
        texture = new Texture("WaterSprite.png");
        bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
        isOnGround = false;
    }

    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            velocityX = -5;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            velocityX = 5;
        } else {
            velocityX = 0;
        }

        if ((Gdx.input.isKeyPressed(Input.Keys.SPACE) && isOnGround) ||
        	(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && isOnPlatform)) {
            velocityY = 14;
            isOnGround = false;
            isOnPlatform = false;
        }
    }

    public void update() {
        x += velocityX;
        y += velocityY;
        velocityY += gravity;

        if (y < 0) {
            y = 0;
            velocityY = 0;
            isOnGround = true;
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
    
    public float getX() {
        return x;
    }

    public void checkPlatformCollision(Platform platform) {
    	Rectangle platformBounds = platform.getBounds();

        if (bounds.overlaps(platformBounds)) {
            float prevY = y - velocityY; // Previous Y position
            if (prevY >= platformBounds.y + platformBounds.height && velocityY <= 0) {
                // If the player was above the platform and is falling onto it
                y = platformBounds.y + platformBounds.height;
                velocityY = 0;
                isOnGround = true;
                isOnPlatform = true;
            } else if (y + bounds.height < platformBounds.y) {
                // If the player is below the platform and jumping into it
                y = platformBounds.y - bounds.height;
                velocityY = 0;
            } else if (x < platformBounds.x) {
                // If the player is to the left of the platform
                x = platformBounds.x - bounds.width;
                velocityX = 0;
            } else if (x + bounds.width > platformBounds.x + platformBounds.width) {
                // If the player is to the right of the platform
                x = platformBounds.x + platformBounds.width;
                velocityX = 0;
            }
        }
        bounds.setPosition(x, y);
    }
}
