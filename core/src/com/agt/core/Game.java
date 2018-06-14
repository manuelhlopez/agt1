package com.agt.core;

import com.agt.utils.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Game {

	//Objetos para dibujar.
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	
	//Objetos del juego
	private Board board;
	private MyDeck myDeck;
	
	public Game (SpriteBatch batch, ShapeRenderer shapeRenderer) {
		
		this.batch = batch;
		this.shapeRenderer = shapeRenderer;
		
		board = new Board(50, 150);
		board.cellSize = 50;
		
		myDeck = new MyDeck();
		myDeck.cellSize = 64;
		myDeck.position = new Vector2(0,0);
		myDeck.addCard(new Card(1));
		myDeck.addCard(new Card(2));
		myDeck.addCard(new Card(3));
		myDeck.addCard(new Card(4));
		myDeck.addCard(new Card(1));
	}
	
	public void update() {
		if (Gdx.input.justTouched()) {
			int x = Gdx.input.getX();
			int y = Gdx.graphics.getHeight() - Gdx.input.getY();
			
			myDeck.selectCard(x, y);
			
			Vector2 vec = board.getCell(x, y);
			if (vec.x > -1 && vec.y > -1) {
				//System.out.println(vec.x + "  " + vec.y);
				if (myDeck.currentCard != null) {
					Card c = myDeck.currentCard;
					board.setCard(c, vec.x, vec.y);
				}
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.A)) {
			board.clearBoard();
		}
		
		board.draw(shapeRenderer);
		myDeck.draw(shapeRenderer);
	}
	
	public void dispose() {
		
	}
	
}
