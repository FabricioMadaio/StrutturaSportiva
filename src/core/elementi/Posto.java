package core.elementi;

import java.io.Serializable;

public class Posto implements Serializable{

	/**
	 * ######################### FABRI VEDI TU ######################
	 */
	private static final long serialVersionUID = 4L;
	private int x;
	private int y;
	private String sigla;
	
	private Stato stato;
	public enum Stato{PRENOTATO,VENDUTO,DISPONIBILE,NON_DISPONIBILE}
	
	public Posto(int x, int y, String sigla) {
		this.x = x;
		this.y = y;
		this.sigla = sigla;
		this.stato = Stato.DISPONIBILE;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}
	

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Posto p = new Posto(x,y,sigla);
		p.setStato(stato);
		
		return p;
	};
	
	
}
