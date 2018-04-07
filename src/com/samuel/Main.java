package com.samuel;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuad;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.menu.HvlMenu;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

public class Main extends HvlTemplateInteg2D{
	HvlFontPainter2D gameFont;
	HvlMenu game;
	HvlMenu end;
	HvlMenu start;
	HvlMenu dead;
	float xPos;
	float yPos;
	float sizeY;
	float speed;
	float jumpCounter;
	boolean maxHeight;
	double megaCounter;
	float signX;
	float rotAngle;
	
	Color brown;
	
	float graceCount;
	int health;
	
	float endCounter;
	
	ArrayList<Cubes> cubes;
	ArrayList<Math> maths;
	ArrayList<Donkey>donkeys;
	ArrayList<Star>stars;
	
	float counterCubes;
	
	public void jump() {
		//jumpCounter = 1;
		if(jumpCounter >= 0 && jumpCounter < 1000 && maxHeight == false) {
			yPos-=200;
			jumpCounter ++;
		}else if(jumpCounter >= 10000) {
			maxHeight = true;
		}else if(maxHeight == true) {
			//yPos+=4;
			jumpCounter --;
		}else if(jumpCounter < 0) {
			maxHeight = false;
		}else if(maxHeight == false) {
			yPos += 0;
			yPos = 330;
			jumpCounter = 0;
		}
	}
	
	public static void main(String [] args){
		new Main();
	}
	public Main(){
		super(60, 800, 600, "What's this?", new HvlDisplayModeDefault());
	}
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		getTextureLoader().loadResource("osFont");//0
		getTextureLoader().loadResource("Sam2");//1
		getTextureLoader().loadResource("sky");//2
		getTextureLoader().loadResource("floor");//3
		getTextureLoader().loadResource("arrow-keys");//4
		getTextureLoader().loadResource("space");//5
		getTextureLoader().loadResource("speechbub");//6
		getTextureLoader().loadResource("8bitcubeFINAL");//7
		getTextureLoader().loadResource("mathjpeg");//8
		getTextureLoader().loadResource("dem");//9
		getTextureLoader().loadResource("arrow");//10
		getTextureLoader().loadResource("sign");//11
		getTextureLoader().loadResource("star");//12
		gameFont =  new HvlFontPainter2D(getTexture(0), HvlFontPainter2D.Preset.FP_INOFFICIAL,.5f,8f,0);
		
		xPos = 10;
		yPos = 330;
		sizeY = 160;
		speed = 0;
		jumpCounter = 0;
		maxHeight = false;
		counterCubes = 0;
		rotAngle = 0;
		graceCount = 15f;
		signX = 300;
		health = 5;
		brown = new Color(107, 88, 58);
		megaCounter = 00;
		
		endCounter = 0;
		
		cubes = new ArrayList<Cubes>();
		cubes.clear();
		maths = new ArrayList<Math>();
		maths.clear();
		donkeys = new ArrayList<Donkey>();
		donkeys.clear();
		stars = new ArrayList<Star>();
		stars.clear();
		start = new HvlMenu() {
			@Override
			public void draw(float delta) {
				hvlDrawQuad(0,0,800,600, Color.white);
				hvlDrawQuadc(400,200,245,152, getTexture(4));
				hvlDrawQuadc(400, 400, 600, 100, getTexture(5));
				gameFont.drawWordc("Press G to continue...", 400, 550, Color.black, 0.5f);
				super.draw(delta);
				if(Keyboard.isKeyDown(Keyboard.KEY_G)) {
					HvlMenu.setCurrent(game);
				}

			}
		};
		game = new HvlMenu(){  //main menu
			@Override
			public void draw(float delta){
				
				megaCounter+=delta*10;
				
				 hvlDrawQuad(0, 0, 800, 450, getTexture(2));
				 hvlDrawQuad(0, 450, 800, 150, getTexture(3));
				 hvlDrawQuad(xPos, yPos, 90, sizeY, getTexture(1));
				//ALL GAME TEXT AND CONTENT GOES HERE \/\/\/\/\///ALL GAME EVENT LOGIC HERE \/\/\/\/\/
				//gameFont.drawWordc("Game Counter: "+megaCounter, 200, 100, Color.black, 0.3f);
				
				if(megaCounter > 20 && megaCounter < 60) {//start pt1
					hvlDrawQuad(xPos, yPos - 130, 200, 140, getTexture(6));
					gameFont.drawWordc("Wait... \n where am I?", xPos + 105, yPos - 80, Color.black, 0.3f);
				}
				if(megaCounter > 70 && megaCounter < 80) {//start pt2
					hvlDrawQuad(xPos, yPos - 70, 100, 70, getTexture(6));
					gameFont.drawWordc("Hmmm..", xPos + 50, yPos - 40, Color.black, 0.3f);
				}
				if(megaCounter > 100 && megaCounter < 300) { //20 secs of power cubes
					if(megaCounter > 110 && megaCounter < 150) {
						hvlDrawQuad(xPos, yPos - 130, 200, 140, getTexture(6));
						gameFont.drawWordc("AHH!! Power\n cubes!!!", xPos + 105, yPos - 80, Color.black, 0.3f);
					}
					counterCubes +=delta * 10;
					if(counterCubes > 15) {
						Cubes cubeGame = new Cubes(900,HvlMath.randomFloatBetween(100, 400), HvlMath.randomFloatBetween(20, 100),rotAngle);
						cubes.add(cubeGame);
						counterCubes = 0;
					}
					rotAngle += HvlMath.randomFloatBetween(4, 10);
					graceCount -= 10*delta;
					gameFont.drawWordc("Don't Let them touch you!!!!    Health: "+health, 400, 50, Color.black, 0.4f);
					for(Cubes wave : cubes) {
						wave.display(delta);
						if(wave.x >= xPos && wave.x <= (xPos + 90) 
								|| (wave.x + 75) >= xPos && (wave.x+75) <= (xPos + 90) 
								|| wave.x <= xPos && (wave.x + 75) > (xPos + 90)){
							if((wave.y + 75) >= yPos && (wave.y+75) <= (yPos + sizeY)
									|| wave.y >= yPos && wave.y <= (yPos + sizeY)
									|| wave.y <= yPos && (wave.y + 75) >= (yPos + sizeY)){
								
								
								if(graceCount <= 0){
									hvlDrawQuad(xPos, yPos, 90, sizeY, getTexture(1), Color.red);
									health -= 1; //subtract from player health
									graceCount = 15f; //grant invincibility
								}
								
							}
							
						}
						if(graceCount <= 0){
							graceCount = 0;
						}
						if(health == 0) {
						
							hvlDrawQuad(xPos, yPos, 90, sizeY, getTexture(1), Color.red);
						}
						if(health == 0 && graceCount == 0) {
							HvlMenu.setCurrent(dead);
						}
					}
					
			
				}
				if(megaCounter > 300 && megaCounter < 400) {//safe Space after cubes
					if(megaCounter > 310 && megaCounter < 350) {
						hvlDrawQuad(xPos, yPos - 130, 240, 140, getTexture(6));
						gameFont.drawWordc("Whew.. \n that was close", xPos + 105, yPos - 80, Color.black, 0.3f);
					}
					health = 5;
					counterCubes = 0;
				}
				if(megaCounter > 400 && megaCounter < 600) {
					//20 secs of math books
					if(megaCounter > 410 && megaCounter < 450) {
						hvlDrawQuad(xPos, yPos - 130, 200, 140, getTexture(6));
						gameFont.drawWordc("AHH!! Math\n Books!!!", xPos + 105, yPos - 80, Color.black, 0.3f);
					}
					counterCubes +=delta * 10;
					if(counterCubes > 15) {
						Math mathGame = new Math(-100,HvlMath.randomFloatBetween(100, 400), HvlMath.randomFloatBetween(20, 100), rotAngle);
 						maths.add(mathGame);
 						counterCubes = 0;
 					}
					rotAngle += HvlMath.randomFloatBetween(4, 10);
 					graceCount -= 10*delta;
 					gameFont.drawWordc("Don't Let them touch you!!!!    Health: "+health, 400, 50, Color.black, 0.4f);
 					for(Math wave : maths) {
 						wave.display(delta);
 						if(wave.x >= xPos && wave.x <= (xPos + 90) 
 								|| (wave.x + 65) >= xPos && (wave.x+65) <= (xPos + 90) 
 								|| wave.x <= xPos && (wave.x + 65) > (xPos + 90)){
 							if((wave.y + 75) >= yPos && (wave.y+75) <= (yPos + sizeY)
 									|| wave.y >= yPos && wave.y <= (yPos + sizeY)
 									|| wave.y <= yPos && (wave.y + 75) >= (yPos + sizeY)){
 								
 								
 								if(graceCount <= 0){
 									 hvlDrawQuad(xPos, yPos, 90, sizeY, getTexture(1), Color.red);
 									health -= 1; //subtract from player health
 									graceCount = 15f; //grant invincibility
 								}
 								
 							}
 							
 						}
 						if(graceCount <= 0){
 							graceCount = 0;
 						}
 						if(health == 0) {
 						
 							 hvlDrawQuad(xPos, yPos, 90, sizeY, getTexture(1), Color.red);
 						}
 						if(health == 0 && graceCount == 0) {
 							HvlMenu.setCurrent(dead);
 						}
 					}
 				}
				if(megaCounter > 600 && megaCounter < 700) {//safe Space after math
					if(megaCounter > 610 && megaCounter < 650) {
						hvlDrawQuad(xPos, yPos - 130, 240, 140, getTexture(6));
						gameFont.drawWordc("What the heck \n was that?", xPos + 105, yPos - 80, Color.black, 0.3f);
					}
					health = 5;
					counterCubes = 0;
				}
				if(megaCounter > 700 && megaCounter < 900) {
					//20 secs of math books
					if(megaCounter > 710 && megaCounter < 750) {
						hvlDrawQuad(xPos, yPos - 130, 200, 140, getTexture(6));
						gameFont.drawWordc("Oh no, liberals!!!", xPos + 105, yPos - 80, Color.black, 0.3f);
					}
					counterCubes +=delta * 10;
					if(counterCubes > 4) {
						Donkey donkGame = new Donkey(HvlMath.randomFloatBetween(300, 500),-100, HvlMath.randomFloatBetween(-100, 100),HvlMath.randomFloatBetween(60, 100), rotAngle);
 						donkeys.add(donkGame);
 						counterCubes = 0;
 					}
					rotAngle += HvlMath.randomFloatBetween(4, 10);
 					graceCount -= 10*delta;
 					gameFont.drawWordc("Don't Let them touch you!!!!    Health: "+health, 400, 50, Color.black, 0.4f);
 					for(Donkey wave : donkeys) {
 						wave.display(delta);
 						if(wave.x >= xPos && wave.x <= (xPos + 90) 
 								|| (wave.x + 75) >= xPos && (wave.x+75) <= (xPos + 90) 
 								|| wave.x <= xPos && (wave.x + 75) > (xPos + 90)){
 							if((wave.y + 75) >= yPos && (wave.y+75) <= (yPos + sizeY)
 									|| wave.y >= yPos && wave.y <= (yPos + sizeY)
 									|| wave.y <= yPos && (wave.y + 75) >= (yPos + sizeY)){
 								
 								
 								if(graceCount <= 0){
 									 hvlDrawQuad(xPos, yPos, 90, sizeY, getTexture(1), Color.red);
 									health -= 1; //subtract from player health
 									graceCount = 15f; //grant invincibility
 								}
 								
 							}
 							
 						}
 						if(graceCount <= 0){
 							graceCount = 0;
 						}
 						if(health == 0) {
 						
 							 hvlDrawQuad(xPos, yPos, 90, sizeY, getTexture(1), Color.red);
 						}
 						if(health == 0 && graceCount == 0) {
 							HvlMenu.setCurrent(dead);
 						}
 					}
 				}
				if(megaCounter > 900 && megaCounter < 1000) {//safe Space after donkeys
					if(megaCounter > 910 && megaCounter < 950) {
						hvlDrawQuad(xPos, yPos - 130, 240, 140, getTexture(6));
						gameFont.drawWordc("  They almost got\n   me, yikes!", xPos + 105, yPos - 80, Color.black, 0.3f);
					}
					health = 5;
					counterCubes = 0;
				}
				if(megaCounter > 1000) {
					hvlDrawQuad(signX, 200, 200, 125, getTexture(10));
					signX+=4;
					if(signX > 500) {
						signX = 300;
					}
					if(xPos + 100 > 800) {
						xPos = 20;
						counterCubes = 0;
						sizeY = 160;
						HvlMenu.setCurrent(end);
					}
				}
				
				if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
					speed = -3;
				}
				if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
					speed = 3;
				}
				if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
					sizeY = 100;
					yPos = 380;
				}else if(!(Keyboard.isKeyDown(Keyboard.KEY_DOWN))){
					yPos = 330;
					sizeY = 160;
					
				}
				if(!(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) && !(Keyboard.isKeyDown(Keyboard.KEY_LEFT))){
					speed = 0;
				}
				if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
					jump();
				}
				xPos+=speed;
				super.draw(delta);
			}

		};
		
		end = new HvlMenu(){  //main menu
			@Override
			public void draw(float delta){
				 hvlDrawQuad(0, 0, 800, 450, getTexture(2));
				 hvlDrawQuad(0, 450, 800, 150, getTexture(3));
				 hvlDrawQuad(xPos, yPos, 90, sizeY, getTexture(1));
				 endCounter+= delta *10;
				 if(endCounter > 20) {
					 counterCubes+=delta*10;
					 
						if(counterCubes > 1) {
							Star starGame = new Star(500, 300, HvlMath.randomFloatBetween(-200, 200),HvlMath.randomFloatBetween(-200, 200), rotAngle);
	 						stars.add(starGame);
	 						counterCubes = 0;
	 					}
						rotAngle += HvlMath.randomFloatBetween(4, 10);
						for(Star wave : stars) {
	 						wave.display(delta);
	 						 if(endCounter > 80) {
	 							 hvlDrawQuad(500, 300, 30, 150, brown);
	 							 hvlDrawQuadc(500, 300,320, 140, Color.black);
	 							 hvlDrawQuadc(500, 300,300, 120, getTexture(11));
	 						 }
						}
	
				 }
				 
				
				
				if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
					sizeY = 100;
					yPos = 380;
				}else if(!(Keyboard.isKeyDown(Keyboard.KEY_DOWN))){
					yPos = 330;
					sizeY = 160;
					
				}
				super.draw(delta);
			}
		};
		dead = new HvlMenu() {
			@Override
			public void draw(float delta) {
				
				hvlDrawQuad(0, 0, 800, 450, getTexture(2));
				hvlDrawQuad(0, 450, 800, 150, getTexture(3));
				gameFont.drawWordc("You died!!!, Press R to restart", 400, 300, Color.black, 0.6f);
				if(Keyboard.isKeyDown(Keyboard.KEY_R)) {
					initialize();
	
					HvlMenu.setCurrent(start);
				}
				super.draw(delta);
			}
		};
		
		HvlMenu.setCurrent(start);
	}
	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		HvlMenu.updateMenus(delta);
	}

}
