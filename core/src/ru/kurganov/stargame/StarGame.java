package ru.kurganov.stargame;

import com.badlogic.gdx.Game;

import ru.kurganov.stargame.screen.MenuScreen;

public class StarGame extends Game {

	@Override
	public void create() {
		setScreen(new MenuScreen(this));
	}
}
