package core.elementi;
import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;

public class Stadio implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5L;
	private String nome;
	private int capienza;
	private double prezzoBase;
	private double incasso;

	private String pathImmagine;
	private transient Image image;

	private ArrayList<Posto> posti;

	/**
	 * ######################## FABRI VEDI TU #####################
	 * @param nome
	 * @param prezzoBase
	 * @param pathImmagine
	 */
	public Stadio(String nome, int prezzoBase,String pathImmagine) {

		this.posti = new ArrayList<Posto>();
		this.nome = nome;
		this.incasso = 0;
		this.pathImmagine = pathImmagine;
		this.setPrezzoBase(prezzoBase);
	}

	/**
	 * Il metodo ritorna il nome dello stadio.
	 * @return String nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Il metodo ritorna la capienza dello stadio.
	 * @return int capienza
	 */
	public int getCapienza()
	{
		capienza = posti.size();
		return capienza;
	}

	/**
	 * Il metodo ritorna il prezzo base del biglietto dello stadio.
	 * @return double prezzoBase
	 */
	public double getPrezzoBase() {
		return prezzoBase;
	}
	/**
	 * Il metodo permette di settare il prezzo base dello stadio.
	 * @param double prezzoBase
	 */
	public void setPrezzoBase(double prezzoBase) {
		this.prezzoBase = prezzoBase;
	}
	/**
	 * Il metodo ritorna la lista di posti associati allo stadio.
	 * @return ArrayList<Posto> posti
	 */
	public ArrayList<Posto> getPosti() {
		return posti;
	}
	/**
	 * Il metto permette di settare la lista dei posti dello stadio.
	 * @param ArrayList<Posto> posti
	 */
	public void setPosti(ArrayList<Posto> posti) {
		this.posti = posti;
	}
	/**
	 * Il metodo permette di aggiungere un posto alla lista dei posto dello stadio.
	 * @param Posto posto
	 */
	public void addPosto(Posto posto) {
		this.posti.add(posto);
	}
	/**
	 * Il metodo ritorna l'incasso dello stadio.
	 * @return double incasso
	 */
	public double getIncasso() {
		return incasso;
	}
	/**
	 * Il metodo permette di settare l'incasso dello stadio.
	 * @param double incasso
	 */
	public void setIncasso(double incasso) {
		this.incasso = incasso;
	}
	/**
	 * Il metodo serve aricavare l'incasso totale.
	 * @param double incasso
	 */
	public void aggiungiIncasso(double incasso) {
		this.incasso += incasso;
	}

	/**
	 * ######################## Fabri i metodi restanti guardali tu ############
	 * @return
	 */
	public String getPathImmagine() {
		return pathImmagine;
	}

	public void setPathImmagine(String pathImmagine) {
		this.pathImmagine = pathImmagine;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}
