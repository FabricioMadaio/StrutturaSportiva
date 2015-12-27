package gui.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import gui.graphics.PhysicBody;
import gui.graphics.Animation;
import gui.graphics.Executable;

public class Sprite extends PhysicBody{
	
	private float x;
	private float y;
	
	private int framesRow;
	private int frame;
	private int frameW;
	private int frameH;
	private float sx,sy;
		
	private int direction;
	private boolean visible;
	
	private Image img;
	private Color c;
	
	private ArrayList<Animation> animations;
	private Animation currentAnim;
	private float accumulator;
	
	public Sprite(int xPos,int yPos,int frameW,int frameH,Image img){
			
		Random r = new Random();
		this.img = img;
		
		x = (int)xPos;
		y = (int)yPos;
		
		direction = -1;
		visible = true;
		
		this.sx = 1;
		this.sy = 1;
		this.framesRow = (frameW==0)?1:img.getWidth(null)/frameW;
		
		this.frameW = (frameW==0)?img.getWidth(null):frameW;
		this.frameH = (frameH==0)?img.getHeight(null):frameH;
		
		frame = 0;
		this.currentAnim = null;
		
		c = new Color(r.nextFloat(), r.nextFloat(), r.nextFloat());
		animations = new ArrayList<Animation>(); 
	
		super.Init(x,y,frameW,frameH,1.0f);
	}
	
	public float getScaleX() {
		return sx;
	}

	public float getScaleY() {
		return sy;
	}

	public int getFrameW() {
		return frameW;
	}

	public int getFrameH() {
		return frameH;
	}

	public void setDirection(boolean right){
		if(right==true)
			direction = -1;
		else
			direction = 1;
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public boolean getDirection(){
		if(direction>0) return true;
		else return false;
	}
	
	public void setPos(float x,float y){
		this.x = x;
		this.y = y;
	}
	
	public void setFrame(int f){
		frame = f;
	}
		
	public void setScale(float sx,float sy){
		this.sx = sx;
		this.sy = sy;
	}
	
	public boolean isVisible(){
		return visible;
	}
	
	public void setVisible(boolean v){
		visible = v;
	}
	
	public void draw(Graphics g)
    {
		
		if(!visible) return;
		int X = (int)x + ((int)(sx*frameW))/2*direction;
		int Y = (int)y - ((int)(sy*frameH))/2;
		
		int i = (frame%framesRow)*frameW;
		int j = (frame/framesRow)*frameH;
	
		float scaleH = sy;
		float scaleW = (direction>0)?-sx:sx;
		
		g.setColor(c);
		g.drawImage(img, (int)X, (int)Y, (int)(frameW*scaleW+X),(int)(frameH*scaleH+Y),i, j, i+frameW, j+frameH, null);
		
    }
	
	public void setStaticAnimation(int f) {
		// TODO Auto-generated method stub
		currentAnim = null;
		frame = f;
	}
	
	public void setAnimation(String name){
		
		if(currentAnim!= null && currentAnim.getName().equals(name)) return;
		
		for(Animation a : animations){
			if(a.getName().equals(name)){
				a.reset();
				currentAnim = a;
				return;
			}
		}
		
	}
	
	public void addAnimation(String string, int[] is,float speed,float duration) {
		// TODO Auto-generated method stub
		this.addAnimation(string,is,speed,duration,null);
	}

	public void addAnimation(String string, int[] is,float speed,float duration,Executable func) {
		// TODO Auto-generated method stub
		Animation a = new Animation(string,is,speed,duration,func);
		animations.add(a);
	}
	
	public void animate(float dt){
		
		if(currentAnim==null) return;
		
		accumulator+=dt;
		
		if(accumulator> currentAnim.getSpeed()){
			currentAnim.next();
			frame = currentAnim.getFrame();
			accumulator = 0;
		}
		
	}


}
