package com.samuel;

import org.newdawn.slick.Color;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuad;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlResetRotation;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlRotate;

import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class Cubes {
	
	public float x;
	public float y;
	public float speed;
	public float startAngle;
	
	public Cubes(float x, float y, float speed, float startAngle) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.startAngle = startAngle;
	}
	public void display(float delta){
		//float colorArg = HvlMath.map(speed, 20, 100, .05f , 1.0f);
		this.x -= this.speed * delta;
		//draw the enemy
		hvlRotate(x+(75/2), y+(75/2), startAngle);
		hvlDrawQuad(x, y, 75, 75, Main.getTexture(7));
		hvlResetRotation();
	}
}
