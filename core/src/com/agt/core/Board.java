package com.agt.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class Board  {
	public static int CELL_SIZE = 16;
	public Vector2 position;
	public int  cellSize;
	public int width;
	public int height;
	
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
		cellSize = CELL_SIZE;
		this.width = area[0].length;
		this.height = area.length;
		
	}
	public Board() {
		position = new Vector2();
		cellSize = CELL_SIZE;
	}
	/**
	 * Dibuja el board con dibujos de cuadrados.
	 * @param shapeRenderer
	 */
	public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer) {

		for (int j=0;j<this.height;j++) {
			for (int i=0;i<this.width;i++) {
				
				if (area[this.height-1-j][i] == 1) {
					shapeRenderer.setColor(new Color(0.62f, 0.89f, 0.66f, 1));
					shapeRenderer.begin(ShapeType.Filled);
					shapeRenderer.rect(position.x + (i*cellSize), position.y + (j*cellSize), cellSize, cellSize);
					shapeRenderer.end();
				}
				Gdx.gl.glLineWidth(3);
				shapeRenderer.setColor(new Color(0.26f, 0.44f, 0.61f, 1));
				shapeRenderer.begin(ShapeType.Line);
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
					int yTemp = j + y - (int)card.center.y;
					int xTemp = i + x - (int)card.center.x;
					if ((xTemp >= 0 && xTemp < area[0].length) && (yTemp >= 0 && yTemp < area.length)) {						
						area[yTemp][xTemp] = 1;
					}
				}
			}
		}
		
	}
	/**
	 * Devuelve la celda del board donde se hizo click
	 * @param xglobal posicion x donde se hizo click
	 * @param yglobal posicion y donde se hizo click
	 * @return retorna un Vector2, que tiene x y y que denotan
	 * las celdas del board.
	 */
	public Vector2 getCell(int xglobal, int yglobal) {
		
		int xtemp = (xglobal-(int)position.x);
		if (xtemp<0) xtemp = -1;
		else xtemp = xtemp/cellSize;
		
		int ytemp = (yglobal-(int)position.y);
		if (ytemp<0) ytemp = -1;
		else ytemp = ytemp/cellSize;
		
		if (xtemp < 0 || xtemp >= this.width) {
			xtemp = -1;
		}
		if (ytemp < 0 || ytemp >= this.height) {
			ytemp = -1;
		}
		this.getGlobalPosition(xtemp, ytemp);
		return new Vector2(xtemp, (ytemp == -1)? -1 : (this.height)-ytemp-1);
	}
	/**
	 * Esta funcion devuelve las coordenadas de pantalla cuando se ingresa una
	 * coordenada de tablero (esquina inferior izquierda
	 * @param x coordenada de tablero x
	 * @param y coordenada de tabler y
	 * @return Retorna un vector2 con las coordenadas de pantalla.
	 */
	public Vector2 getGlobalPosition(int x, int y) {
		int xtemp = (x * cellSize) + (int)position.x;
		int ytemp = Gdx.graphics.getHeight() - ((y * cellSize) + (int)position.y) + cellSize;
		
		return new Vector2(xtemp, ytemp);
	}
	public void clearBoard() {
		for (int j=0;j<area.length;j++) {
			for (int i=0;i<area[0].length;i++) {
				area[j][i] = 0; 
			}
		}
	}
}
