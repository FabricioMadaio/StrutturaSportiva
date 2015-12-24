package Graphics;

public class PhysicBody {
	
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
	
	public PhysicBody(){
	}
	
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
	
	public float getPosX() {
		return posX;
	}

	public float getPosY() {
		return posY;
	}
	
	public void setPosXY(float x,float y){
		this.posX = x;
		this.posY = y;
	}
	
	public void simulationStep(float dt){
	
		velX += accX*dt + (forceX/this.mass)*dt;
		velY += accY*dt + (forceY/this.mass)*dt;
		
		posX += airFriction*velX*dt;
		posY += airFriction*velY*dt;
		
		forceX = 0;
		forceY = 0;
	}
	
	public void applyForce(float fx,float fy){
		forceX += fx;
		forceY += fy;
	}
	
	public float getVelX(){
		return velX;
	}
	
	public float getVelY(){
		return velY;
	}
	
	public void wallHit(){
		velX*=-(1*restitution);
	}
	
	public void groundHit(){
		velY*=-1*restitution;
		velX*=friction;
	}

	public void setVelX(float vx){
		velX = vx;
	}
	
	public void setVelY(float vy){
		velY = vy;
	}
	
	public float getFriction() {
		return friction;
	}

	public void setFriction(float friction) {
		this.friction = friction;
	}

	public float getAirFriction() {
		return airFriction;
	}

	public void setAirFriction(float airFriction) {
		this.airFriction = airFriction;
	}

	public float getRestitution() {
		return restitution;
	}

	public void setRestitution(float restitution) {
		this.restitution = restitution;
	}
}
