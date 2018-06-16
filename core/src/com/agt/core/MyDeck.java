package com.agt.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class MyDeck {
	public Vector2 position;
	private Card[] cardList;
	public Card currentCard;
	public int currentCardInt;
	public int cellSize;
	public int width;
	public int height;
	
	private int index;

	public MyDeck() {
		cardList = new Card[5];
		currentCard = null;
		currentCardInt = -1;
		cellSize = 32;
		this.height = cellSize;
		this.width = 0;
		index= 0;
	}
	
	public void addCard(Card c) {
		c.setScale(3);
		cardList[index] = c;
		index++;
		this.width += cellSize; 
		this.height = cellSize;
	}
	
	public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer) {
		
		for (int i=0;i<cardList.length;i++) {
			
			/*sr.begin(ShapeType.Filled);
			if (cardList.get(i).type == 1) sr.setColor(Color.WHITE);
			if (cardList.get(i).type == 2) sr.setColor(Color.BLUE);
			if (cardList.get(i).type == 3) sr.setColor(Color.GREEN);
			if (cardList.get(i).type == 4) sr.setColor(Color.CORAL);*/
			
			Card card = cardList[i];
			if (card != null) {
				batch.begin();
				card.setPosition(position.x + (i*cellSize) + (i*3), position.y);
				card.draw(batch);
				batch.end();
			}
//			sr.rect(position.x + (i*cellSize) + (i*3), position.y, cellSize, cellSize);
			/*sr.rect(position.x + (i*cellSize), position.y, cellSize, cellSize);
			sr.end();*/
		}
	}
	
	public void deleteCard() {
		if (this.currentCard != null && this.currentCardInt != -1) {
			cardList[this.currentCardInt] = null;
			this.currentCard = null;
		}
	}
	
	public void selectCard(int xglobal, int yglobal) {
		
		int xtemp = (xglobal-(int)position.x);
		if (xtemp<0) xtemp = -1;
		else xtemp = xtemp/cellSize;

		int ytemp = (yglobal-(int)position.y);
		
		if (xtemp < 0 || xtemp >= this.width) {
			xtemp = -1;
		}
		if (ytemp < 0 || ytemp >= this.height) {
			ytemp = -1;
		}

		if (xtemp != -1 && ytemp != -1 && xtemp < this.cardList.length) {
			this.currentCard = this.cardList[xtemp];
			this.currentCardInt = xtemp;
		}
	}
	
}
