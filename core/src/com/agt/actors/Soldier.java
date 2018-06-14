package com.agt.actors;

import com.agt.utils.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Soldier extends Sprite {
		
	public Soldier(Texture texture) {
		super(texture);
		this.setOrigin(0, 0);
	}
	
	public void move(Vector2 vector2) {
		this.setX(vector2.x);
		this.setY(vector2.y);
	}
}
