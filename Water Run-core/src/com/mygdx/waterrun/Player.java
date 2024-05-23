package com.mygdx.waterrun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {
	private Texture texture;
    private float x, y;
    private float velocityX, velocityY;
    private float gravity = -0.5f;
    private Rectangle bounds;
    private boolean isOnGround;
    private boolean isOnPlatform;
    private static final float JUMP_SPEED = 18.0f; // Initial jump velocity
    private static final float GRAVITY = -0.5f; // Gravity affecting the player
    private static final float MAX_SPEED = 5.0f; // Maximum speed
    private static final float ACCELERATION = 0.5f; // Rate of acceleration
    private static final float DECELERATION = 0.3f; // Rate of deceleration
    private static final float FRICTION = 0.1f; // Friction to stop the player gradually
    private Vector2 playerPosition = new Vector2(); // Player's position
    public Player(float x, float y) {
        this.x = x;
        this.y = y;
        texture = new Texture("WaterSprite.png");
        isOnGround = true;
        isOnPlatform = false;
        bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
    }

    public void handleInput() {
        // Check if the left or right keys are pressed and adjust velocity accordingly
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (velocityX > -MAX_SPEED) {
                velocityX -= ACCELERATION;
            } else {
                velocityX = -MAX_SPEED;
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (velocityX < MAX_SPEED) {
                velocityX += ACCELERATION;
            } else {
                velocityX = MAX_SPEED;
            }
        } else {
            // Apply friction to slow down the player gradually when no keys are pressed
            if (velocityX > 0) {
                velocityX -= FRICTION;
                if (velocityX < 0) {
                    velocityX = 0;
                }
            } else if (velocityX < 0) {
                velocityX += FRICTION;
                if (velocityX > 0) {
                    velocityX = 0;
                }
            }
        }

        // Apply the velocity to the player's position
        x += velocityX;

        // Jumping logic
        if ((Gdx.input.isKeyPressed(Input.Keys.SPACE) && isOnGround) ||
            (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && isOnPlatform)) {
            velocityY = JUMP_SPEED;
            isOnGround = false;
            isOnPlatform = false;
        }

        // Apply gravity
        if (!isOnGround) {
            velocityY += GRAVITY;
        }

        // Update the player's position
        playerPosition.x += velocityX;
        playerPosition.y += velocityY;
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
