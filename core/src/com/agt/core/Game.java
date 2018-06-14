package com.agt.core;

import java.util.ArrayList;

import com.agt.actors.Soldier;
import com.agt.utils.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Game {

	public static int STATUS_SELECTED_STANDBY = 0;
	public static int STATUS_SELECTED_PLAYER = 1;
	public static int STATUS_SELECTED_CARD = 2;
	
	//Objetos para dibujar.
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	
	
	//Objetos del juego
	private Board board;
	private MyDeck myDeck;
	private ArrayList<Soldier> soldierList;
	private Soldier currentSoldier;
	private int status;
	
	
	public Game (SpriteBatch batch, ShapeRenderer shapeRenderer) {
		
		this.batch = batch;
		this.shapeRenderer = shapeRenderer;
		this.status = Game.STATUS_SELECTED_STANDBY;
		this.soldierList = new ArrayList<Soldier>();
		
		board = new Board(50, 150);
		board.cellSize = 50;
		
		myDeck = new MyDeck();
		myDeck.cellSize = 64;
		myDeck.position = new Vector2(80,50);
		myDeck.addCard(new Card(new Texture("card1.png"), 1));
		myDeck.addCard(new Card(new Texture("card2.png"),2));
		myDeck.addCard(new Card(new Texture("card3.png"),3));
		myDeck.addCard(new Card(new Texture("card4.png"),4));
		myDeck.addCard(new Card(new Texture("card1.png"),1));
		
		//soldado 1
		Vector2 pos = board.getGlobalPosition(5, 5);
		Soldier soldier = new Soldier(new Texture("soldier.png"));
		soldier.setPosition(pos.x, pos.y);
		soldier.setScale(2);
		soldierList.add(soldier);
		//soldado 2
		pos = board.getGlobalPosition(2, 2);
		Soldier soldier2 = new Soldier(new Texture("soldier.png"));
		soldier2.setPosition(pos.x, pos.y);
		soldier2.setScale(2);
		soldierList.add(soldier2);
		
	}
	
	public void update() {
		
		if (Gdx.input.justTouched()) {
			int xglobal = Gdx.input.getX();
			int yglobal = Gdx.graphics.getHeight() - Gdx.input.getY();
			
			if (status == Game.STATUS_SELECTED_STANDBY) {
				for (Soldier soldier : soldierList) {
					if (soldier.getBoundingRectangle().contains(xglobal, yglobal)) {
						this.currentSoldier = soldier;
						Vector2 v = board.getCell((int)this.currentSoldier.getX(), (int)this.currentSoldier.getY());
						board.area[v.y][v.x] = 1;
						status = Game.STATUS_SELECTED_PLAYER;
						break;
					}
				}
			}
			else if (status == Game.STATUS_SELECTED_PLAYER) {
				this.selectCard(xglobal, yglobal);
			}
			else if (status == Game.STATUS_SELECTED_CARD) {
				this.selectCard(xglobal, yglobal);
				Vector2 vec = board.getCell(xglobal, yglobal);
				if (vec.x != -1 && vec.y != -1) {
					if (board.area[vec.y][vec.x] == 1) {
						
						Vector2 v = board.getCell((int)this.currentSoldier.getX(), (int)this.currentSoldier.getY());
						board.area[v.y][v.x] = 0;
						
						Vector2 pos = board.getGlobalPosition(vec.x, vec.y);
						this.currentSoldier.setPosition(pos.x, pos.y);
						this.currentSoldier.setScale(2);
						board.clearBoard();
						status = Game.STATUS_SELECTED_STANDBY;
						myDeck.deleteCard();
					}
				}
			}
			//}
		}
		if (Gdx.input.isKeyJustPressed(Keys.A)) {
			board.clearBoard();
		}
		
		board.draw(shapeRenderer);
		myDeck.draw(batch);
		batch.begin();
		for (Soldier soldier : soldierList) {			
			soldier.draw(batch);
		}
		batch.end();
	}
	
	public void selectCard(int xglobal, int yglobal) {
		myDeck.selectCard(xglobal, yglobal);
		if (myDeck.currentCard != null) {
			Vector2 vec = board.getCell((int)this.currentSoldier.getX(), (int)this.currentSoldier.getY());
			Card c = myDeck.currentCard;
			board.clearBoard();
			board.setCard(c, vec.x, vec.y);
			status = Game.STATUS_SELECTED_CARD;
		}
	}
	
	public void dispose() {
		
	}
	
}
