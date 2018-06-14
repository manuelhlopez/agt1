package com.agt;

import com.agt.core.Game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Agt extends ApplicationAdapter {
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	private Game game;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		game = new Game(batch, shapeRenderer);
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.61f, 0.76f, 0.90f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.update();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		game.dispose();
	}
}
