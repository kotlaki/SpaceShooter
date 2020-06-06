package ru.kurganov.stargame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.kurganov.stargame.StarGame;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.fullscreen = false;
		config.height = 1920;
		config.width = 1080;
		config.resizable = false;
		new LwjglApplication(new StarGame(), config);
	}
}