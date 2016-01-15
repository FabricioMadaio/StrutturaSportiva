package gui.graphics;


/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 * 
 * la classe Animation gestisce una animazione generica intesa come un insieme di 
 * indici interi che rappresentano i fotogrammi (frames)
 */

public class Animation {
		
	/**
	 * @param name	nome della animazione
	 * @param frames	numero di sotto-immagini
	 * @param duration	durata fotogramma
	 * @param nloops	numero di ripetizioni, -1 loop infinito
	 * @param endFunc	listener di fine animazione
	 */
	public Animation(String name, int[] frames,float duration, float nloops, Executable endFunc) {
		this.name = name;
		this.frames = frames;
		this.duration = duration;
		this.nloops = nloops;
		
		this.endFunc = endFunc;
		
		currentLoops = 1;
		currentIndex = -1;
	}

	/**
	 * @return nome assegnato alla animazione
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return lista di frames
	 */
	public int[] getFrames() {
		return frames;
	}

	/**
	 * @return numero di cicli
	 */
	public float getLoops() {
		return nloops;
	}
	
	/**
	 * @return numero di cicli compiuti
	 */
	public float getCurrentLoops() {
		return currentLoops;
	}

	/**
	 * @return durata fotogramma
	 */
	public float getDuration() {
		return duration;
	}
	
	/**
	 *  passa al frame successivo, gestisce il numero di cicli e lancia il endFunc alla fine
	 */
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
	
	/**
	 * passa al frame precedente
	 */
	public void prev(){
		if(currentIndex>0)
		currentIndex-=1;
	}
	
	/**
	 * resetta l'animazione
	 */
	public void reset(){
		this.currentLoops = 1;
		setAnimationIndex(0);
	}
	
	/**
	 * @param n setta l'indice di posizione per l'animazione
	 */
	public void setAnimationIndex(int n){
		if(n>=0 && n<this.frames.length){
			currentIndex = n;
		}
	}
	
	/**
	 * @return indice di posizione per l'animazione
	 */
	public int getAnimationIndex(){
		return currentIndex;
	}

	/**
	 * @return frame corrente
	 */
	public int getFrame() {
		// TODO Auto-generated method stub
		return frames[currentIndex];
	}
	
	private String name;
	private int[] frames;
	private float duration;
	private float nloops;
	
	private int currentIndex;
	private float currentLoops;
	
	private Executable endFunc;
}
