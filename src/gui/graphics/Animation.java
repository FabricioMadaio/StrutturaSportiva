package gui.graphics;

public class Animation {
		
	private String name;
	private int[] frames;
	private float speed;
	private float nloops;
	
	private int currentIndex;
	private float currentLoops;
	
	private Executable endFunc;

	public Animation(String name, int[] frames,float speed, float nloops, Executable endFunc) {
		this.name = name;
		this.frames = frames;
		this.speed = speed;
		this.nloops = nloops;
		
		this.endFunc = endFunc;
		
		currentLoops = 1;
		currentIndex = -1;
	}

	public String getName() {
		return name;
	}

	public int[] getFrames() {
		return frames;
	}

	public float getLoops() {
		return nloops;
	}
	
	public float getCurrentLoops() {
		return currentLoops;
	}

	public float getSpeed() {
		return speed;
	}
	
	public void next(){
		if(currentIndex<(this.frames.length-1))
			currentIndex+=1;
		else{
			if(this.nloops<0){
				setAnimationIndex(0);
			}else{
				
				if(this.nloops>this.currentLoops){
					this.currentLoops+=1;
					setAnimationIndex(0);
				}else{
					if(this.nloops==this.currentLoops)
						if(this.endFunc!=null){
							this.endFunc.func(this);
							this.currentLoops+=1;
						}
				}
			}
		}
	}
	
	public void prev(){
		if(currentIndex>0)
		currentIndex-=1;
	}
	
	public void reset(){
		this.currentLoops = 1;
		setAnimationIndex(0);
	}
	
	public void setAnimationIndex(int n){
		if(n>=0 && n<this.frames.length){
			currentIndex = n;
		}
	}
	
	public int getAnimationIndex(){
		return currentIndex;
	}

	public int getFrame() {
		// TODO Auto-generated method stub
		return frames[currentIndex];
	}
	
}
