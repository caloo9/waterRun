package com.mygdx.waterrun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class PlatformGenerator {
	private ArrayList<Platform> platforms;
    private Player player;
    private float lastPlatformX;
    private float lastPlatformY;
    private float minPlatformHeight = 20;
    private float maxPlatformHeight = 40;
    private float minPlatformWidth = 100;
    private float maxPlatformWidth = 200;
    private float minPlatformDistance = 150;
    private float maxPlatformDistance = 300;
    private float minPlatformY = 50;
    private float maxPlatformY = 400;
    private float maxPlatVerticalDistance = 130;
    private Random random;
    
    public PlatformGenerator(Player player) {
        this.platforms = new ArrayList<>();
        this.player = player;
        this.lastPlatformX = player.getX();
        this.lastPlatformY = 100;
        this.random = new Random();
        // Initial platform
        platforms.add(new Platform(100, 100, 200, 20));
        lastPlatformX = 300;
    }

    public void update() {
    	float threshold = Gdx.graphics.getWidth() * 4;
        // Remove platforms that are far behind the player
        Iterator<Platform> iterator = platforms.iterator();
        while (iterator.hasNext()) {
            Platform platform = iterator.next();
            if (platform.getX() < player.getX() - threshold) {
                iterator.remove();
            }
        }

        // Generate new platforms ahead of the player
        if (lastPlatformX < player.getX() + Gdx.graphics.getWidth()) {
            float platformWidth = minPlatformWidth + random.nextFloat() * (maxPlatformWidth - minPlatformWidth);
            float platformHeight = minPlatformHeight + random.nextFloat() * (maxPlatformHeight - minPlatformHeight);
            float platformX = lastPlatformX + minPlatformDistance + random.nextFloat() * (maxPlatformDistance - minPlatformDistance);
            float newPlatformY;
            do {
                newPlatformY = lastPlatformY + (random.nextFloat() * 2 - 1) * maxPlatVerticalDistance;
            } while (newPlatformY < minPlatformY || newPlatformY > maxPlatformY);
            platforms.add(new Platform(platformX, newPlatformY, platformWidth, platformHeight));
            lastPlatformX = platformX + platformWidth;
            lastPlatformY = newPlatformY;
        }
    }

    public void render(SpriteBatch batch) {
        for (Platform platform : platforms) {
            platform.render(batch);
        }
    }

    public void checkCollisions(Player player) {
        for (Platform platform : platforms) {
            player.checkPlatformCollision(platform);
        }
    }

    public void dispose() {
        for (Platform platform : platforms) {
            platform.dispose();
        }
    }

	public Platform getCurrentPlatform() {
		if (platforms.size() > 0) {
            return platforms.get(0);
        } else {
            return null; // No platforms generated yet
        }
	}
}
