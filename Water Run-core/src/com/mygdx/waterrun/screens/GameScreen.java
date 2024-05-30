package com.mygdx.waterrun.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.waterrun.PlatformGenerator;
import com.mygdx.waterrun.Player;
import com.mygdx.waterrun.WaterRunMain;

public class GameScreen extends WaterRunScreens{
	public SpriteBatch batch;
    public Player player;
    public Texture background;
    public PlatformGenerator platformGenerator;
    public OrthographicCamera camera;
    public BitmapFont font;
    public GlyphLayout layout;
    
	public GameScreen (WaterRunMain game) {
		super(game);
	}
	
	@Override
	public void show () {
		batch = new SpriteBatch();
        player = new Player(100,300);
        background = new Texture("background.png");
     
        // Load and generate the font using FreeTypeFontGenerator
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Water-Regular.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 56; // Set the desired font size
        font = generator.generateFont(parameter);
        generator.dispose(); // Dispose the generator to free resources
        font.setColor(Color.WHITE); // Set the font color
        layout = new GlyphLayout();
        
        platformGenerator = new PlatformGenerator(player);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
	}
	
	@Override
	public void render (float delta) {
		player.handleInput();
        player.update();

        if (player.getY() <= 0) {
            game.setScreen(new GameOverScreen(game, player.getScore()));
            return;
        }
        
        // Update camera position to follow the player
        camera.position.x = player.getX() + Gdx.graphics.getWidth() / 4; // Adjust as needed
        camera.update();
        
        platformGenerator.update();
        platformGenerator.checkCollisions(player);
        
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, camera.position.x - camera.viewportWidth / 2, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        player.render(batch, 0.3f);
        platformGenerator.render(batch);
        
     // Render the score
        String scoreText = "Score: " + player.getScore();
        layout.setText(font, scoreText);
        
     // Calculate the position for the score based on the camera
        float scoreX = camera.position.x + (Gdx.graphics.getWidth() / 2) - layout.width - 20;
        float scoreY = camera.position.y + (Gdx.graphics.getHeight() / 2) - 20;
        font.draw(batch, scoreText, scoreX, scoreY);
        
        batch.end();
	}
	
	@Override
	public void hide () {
		batch.dispose();
        background.dispose();
        player.dispose();
        platformGenerator.dispose();
	}
}
