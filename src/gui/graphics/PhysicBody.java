package gui.graphics;

/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 * 
 * PhysicBody definisce un oggetto utile per una simulazione fisica
 * sfrutta il metodo di Eulero per risolvere le equazioni del moto
 */
public class PhysicBody {
	
	public PhysicBody(){
	}
	
	/**
	 * inizializza l'oggetto fisico rettangolare
	 * 
	 * @param xPos coordinata x
	 * @param yPos coordinata y
	 * @param width	larghezza
	 * @param height altezza
	 * @param mass	massa
	 */
	public void Init(float xPos,float yPos,float width,int height,float mass){
		posX = xPos;
		posY = yPos;
		velX  = 0;
		velY = 0;
		accX  = 0;
		accY = GRAVITY;
		friction = 0.59f;
		airFriction = 1.0f;
		restitution = 0.0f;
		
		forceX = 0;
		forceY = 0;
		
		this.width = width;
		this.height = height;
		this.mass = mass;
	}
	
	/**
	 * @return x
	 */
	public float getPosX() {
		return posX;
	}

	/**
	 * @return y
	 */
	public float getPosY() {
		return posY;
	}
	
	/**
	 * @param x
	 * @param y
	 */
	public void setPosXY(float x,float y){
		this.posX = x;
		this.posY = y;
	}
	
	/**
	 * metodo di integrazione di Eulero
	 * 
	 * @param dt step della simulazione (tempo in secondi)
	 */
	public void simulationStep(float dt){
	
		velX += accX*dt + (forceX/this.mass)*dt;
		velY += accY*dt + (forceY/this.mass)*dt;
		
		posX += airFriction*velX*dt;
		posY += airFriction*velY*dt;
		
		forceX = 0;
		forceY = 0;
	}
	
	/**
	 * applica la forza
	 * 
	 * @param fx
	 * @param fy
	 */
	public void applyForce(float fx,float fy){
		forceX += fx;
		forceY += fy;
	}
	
	/**
	 * @return vx
	 */
	public float getVelX(){
		return velX;
	}
	
	/**
	 * @return vy
	 */
	public float getVelY(){
		return velY;
	}
	
	/**
	 * fa rimbalzare l'oggetto verso la direzione x opposta
	 */
	public void wallHit(){
		velX*=-(1*restitution);
	}
	
	/**
	 * fa rimbalzare l'oggetto verso la direzione y opposta
	 */
	public void groundHit(){
		velY*=-1*restitution;
		velX*=friction;
	}

	/**
	 * @param vx
	 */
	public void setVelX(float vx){
		velX = vx;
	}
	
	/**
	 * @param vy
	 */
	public void setVelY(float vy){
		velY = vy;
	}
	
	/**
	 * @return attrito statico
	 */
	public float getFriction() {
		return friction;
	}

	/**
	 * @param friction attrito statico
	 */
	public void setFriction(float friction) {
		this.friction = friction;
	}

	/**
	 * @return attrito con l'aria
	 */
	public float getAirFriction() {
		return airFriction;
	}

	/**
	 * @param airFriction attrito con l'aria
	 */
	public void setAirFriction(float airFriction) {
		this.airFriction = airFriction;
	}

	/**
	 * @return coefficiente di rimbalzo
	 */
	public float getRestitution() {
		return restitution;
	}

	/**
	 * @param restitution coefficiente di rimbalzo
	 */
	public void setRestitution(float restitution) {
		this.restitution = restitution;
	}
	
	private float posX;
	private float posY;
	private float velX;
	private float velY;
	private float accX;
	private float accY;
	
	private float forceX;
	private float forceY;
	
	private float friction;
	

	private float airFriction;
	private float restitution;
	
	private float mass;
	private float width;
	private float height;
	
	public final static float GRAVITY = 9.8f;
}
