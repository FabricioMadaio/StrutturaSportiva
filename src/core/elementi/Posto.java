package core.elementi;

import java.io.Serializable;

/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 *
 * Classe che modella il Posto di uno Stadio
 */

public class Posto implements Serializable{

	
	
	/**
	 * costruttore del posto
	 * di default lo stato viene settato a disponibile
	 * 
	 * @param x
	 * @param y
	 * @param sigla descrittore del posto
	 */
	public Posto(int x, int y, String sigla) {
		this.x = x;
		this.y = y;
		this.sigla = sigla;
		this.stato = Stato.DISPONIBILE;
	}

	/**
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return sigla
	 */
	public String getSigla() {
		return sigla;
	}

	/**
	 * @param sigla
	 */
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	/**
	 * @return stato del posto
	 */
	public Stato getStato() {
		return stato;
	}

	/**
	 * @param stato
	 */
	public void setStato(Stato stato) {
		this.stato = stato;
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Posto p = new Posto(x,y,sigla);
		//setto lo stato poiche nel costruttore viene settato a disponibile
		p.setStato(stato);
		
		return p;
	};
	
	private static final long serialVersionUID = 4L;
	private int x;
	private int y;
	private String sigla;
	
	private Stato stato;
	public enum Stato{PRENOTATO,VENDUTO,DISPONIBILE,NON_DISPONIBILE}
}
