package com.agt.core;

import com.agt.utils.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Card extends Sprite {

	public int[][] area;
	public Vector2 center;
	public int type;
	
	public static int[][] TYPE_1 =  {
			{0,0,1,0,0},
			{0,1,1,1,0},
			{1,1,1,1,1},
			{0,1,1,1,0},
			{0,0,1,0,0}
			};
	
	public static int[][] TYPE_2 =  {
			{0,1,0},
			{0,1,0},
			{0,1,0},
			{0,1,0},
			{0,1,0}
			};
	
	public static int[][] TYPE_3 =  {
			{0,1,0},
			{1,1,1},
			{0,1,0}
			};
	public static int[][] TYPE_4 =  {
			{1,1,0},
			{0,1,0},
			{0,1,0}
			};
	
	public Card(Texture texture, int type) {
		super(texture);
		this.type = type;
		if (type == 1) {
			center = new Vector2(2, 2);
			area = Card.TYPE_1;
		}
		else if (type == 2) {
			center = new Vector2(1, 2);
			area = Card.TYPE_2;
		}
		else if (type == 3) {
			center = new Vector2(1, 1);
			area = Card.TYPE_3;
		}
		else if (type == 4) {
			center = new Vector2(1, 2);
			area = Card.TYPE_4;
		}
		
	}
}
