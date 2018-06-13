package com.agt.core;

import java.util.ArrayList;

import com.agt.utils.Vector2;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class MyDeck {
	public Vector2 position;
	private ArrayList<Card> cardList;
	public Card currentCard;
	public int cellSize;
	public int width;
	public int height;
	
	public MyDeck() {
		cardList = new ArrayList<Card>();
		currentCard = null;
		cellSize = 32;
		this.height = cellSize;
		this.width = 0;
	}
	
	public void addCard(Card c) {
		cardList.add(c);
		this.width += cellSize; 
	}
	
	public void draw(ShapeRenderer sr) {
		
		for (int i=0;i<cardList.size();i++) {
			
			sr.begin(ShapeType.Filled);
			if (cardList.get(i).type == 1) sr.setColor(Color.WHITE);
			if (cardList.get(i).type == 2) sr.setColor(Color.BLUE);
			if (cardList.get(i).type == 3) sr.setColor(Color.GREEN);
			if (cardList.get(i).type == 4) sr.setColor(Color.CORAL);
			
//			sr.rect(position.x + (i*cellSize) + (i*3), position.y, cellSize, cellSize);
			sr.rect(position.x + (i*cellSize), position.y, cellSize, cellSize);
			sr.end();
		}
	}
	
	public void selectCard(int x, int y) {
		if (x > this.width || y > this.height)
			return;
		
		int card = x/cellSize;
		if (card < cardList.size()) {
			this.currentCard = cardList.get(card);
		}
	}
	
}
