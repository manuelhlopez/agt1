package com.agt.core;

import com.agt.utils.Vector2;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Board  {
	public Vector2 position;
	public int  cellSize;
	
	//area.length es Y
	//area[0].length es X
	public int[][] area = {
			{0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0},
			};
	
	/**
	 * Este metodo inicia el tablero
	 * @param x posicion en X donde aparece
	 * @param y posicion en Y donde aparece
	 */
	public Board(int x, int y) {
		position = new Vector2(x,y);
		cellSize = 16;
	}
	public Board() {
		position = new Vector2();
		cellSize = 16;
	}
	/**
	 * Dibuja el board con dibujos de cuadrados.
	 * @param shapeRenderer
	 */
	public void draw(ShapeRenderer shapeRenderer) {

		for (int j=0;j<area.length;j++) {
			for (int i=0;i<area[0].length;i++) {
				
				if (area[area.length-1-j][i] == 1) {
					shapeRenderer.setColor(Color.GREEN);
					shapeRenderer.begin(ShapeType.Filled);					
				}
				else {
					shapeRenderer.setColor(Color.WHITE);
					shapeRenderer.begin(ShapeType.Line);										
				}
				shapeRenderer.rect(position.x + (i*cellSize), position.y + (j*cellSize), cellSize, cellSize);
				shapeRenderer.end();
			}
		}
	}
	
	/**
	 * Este metodo se encarga de cambiar los valores a la matriz por lo que cambia el color cuando se pinta
	 * el board. 
	 * @param card la carta de movimiento que se quiere usar
	 * @param x en que posicion x del tablero se coloca el centro de la carta (inicia en 0)
	 * @param y en que posicion y del tablero se coloca el centro de la carta (inicia en 0)
	 */
	public void drawCard(Card card, int x, int y) {
		
		int[][] areaCard = card.area;
		
		for (int j=0;j<areaCard.length;j++) {
			for (int i=0;i<areaCard[0].length;i++) {
				if (areaCard[j][i] == 1) {
					int yTemp = j + y - card.center.y;
					int xTemp = i + x - card.center.x;
					if ((xTemp >= 0 && xTemp < area[0].length) && (yTemp >= 0 && yTemp < area.length)) {						
						area[yTemp][xTemp] = 1;
					}
				}
			}
		}
		
	}
	/**
	 * Devuelve la celda del board donde se hizo click
	 * @param x posicion x donde se hizo click
	 * @param y posicion y donde se hizo click
	 * @return retorna un Vector2, que tiene x y y que denotan
	 * las coordenadas del board.
	 */
	public Vector2 getCell(int x, int y) {
		int xtemp = (x-position.x)/cellSize;
		int ytemp = (y-position.y)/cellSize;
		if (xtemp < 0 || xtemp >= area[0].length) {
			xtemp = -1;
		}
		if (ytemp < 0 || ytemp >= area.length) {
			ytemp = -1;
		}
		
		return new Vector2(xtemp, ytemp);
	}
}
