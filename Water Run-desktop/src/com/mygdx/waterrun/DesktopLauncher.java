package com.mygdx.waterrun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.waterrun.WaterRunMain;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Water Run");
		config.setWindowedMode(1500, 1000);
		config.useVsync(true);
		config.setForegroundFPS(60);
		new Lwjgl3Application(new WaterRunMain(), config);
	}
}
