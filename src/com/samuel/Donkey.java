package com.samuel;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuad;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlResetRotation;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlRotate;

public class Donkey {
	public float x;
	public float y;
	public float speed;
	public float speedY;
	public float startAngle;
	public float direction = 0;
	
	public Donkey(float x, float y, float speed, float speedY, float startAngle) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.speedY = speedY;
		this.startAngle = startAngle;
	}
	public void display(float delta){
		//float colorArg = HvlMath.map(speed, 20, 100, .05f , 1.0f);
		 
			
				//this.x -= this.speed * delta;
			
		
				this.x += this.speed * delta;
		
			
		
		this.y += this.speedY * delta;
		//draw the enemy
		hvlRotate(x+(75/2), y+(75/2), startAngle);
		hvlDrawQuad(x, y, 75, 75, Main.getTexture(9));
		hvlResetRotation();
	}

}
