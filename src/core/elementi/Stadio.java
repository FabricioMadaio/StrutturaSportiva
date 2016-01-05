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
	
	
	public Stadio(String nome, int prezzoBase,String pathImmagine) {
		
		this.posti = new ArrayList<Posto>();
		this.nome = nome;
		this.incasso = 0;
		this.pathImmagine = pathImmagine;
		this.setPrezzoBase(prezzoBase);
	}
	
	public String getNome() {
		return nome;
	}

	public int getCapienza()
	{
		capienza = posti.size();
		return capienza;
	}
	
	public double getPrezzoBase() {
		return prezzoBase;
	}

	public void setPrezzoBase(double prezzoBase) {
		this.prezzoBase = prezzoBase;
	}
	
	public ArrayList<Posto> getPosti() {
		return posti;
	}

	public void setPosti(ArrayList<Posto> posti) {
		this.posti = posti;
	}
	
	public void addPosto(Posto posto) {
		this.posti.add(posto);
	}

	public double getIncasso() {
		return incasso;
	}

	public void setIncasso(double incasso) {
		this.incasso = incasso;
	}
	
	public void aggiungiIncasso(double incasso) {
		this.incasso += incasso;
	}
	

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
