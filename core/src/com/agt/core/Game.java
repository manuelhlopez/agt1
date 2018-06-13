package com.agt.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Game {

	//Objetos para dibujar.
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	
	//Objetos del juego
	private Board board;
	private MyDeck mydeck;
	
	public Game (SpriteBatch batch, ShapeRenderer shapeRenderer) {
		
		this.batch = batch;
		this.shapeRenderer = shapeRenderer;
		
		Card card = new Card(4);
		
		board = new Board(10, 180);
		board.cellSize = 32;
		board.drawCard(card, 1, 5);
	}
	
	public void update() {
		if (Gdx.input.justTouched()) {
			int x = Gdx.input.getX();
			int y = Gdx.graphics.getHeight() - Gdx.input.getY();	
			board.getCell(x, y);
		}
		
		board.draw(shapeRenderer);
	}
	
	public void dispose() {
		
	}
	
}
