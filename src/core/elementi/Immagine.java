package core.elementi;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 * 
 * Classe che contiene la coppia immagine - percorso
 * (Vedi ListaUtenti.java per dettagli sulla implementazione)
 */
public class Immagine {
	
	
	/**
	 * costruttore
	 * @param path
	 */
	public Immagine(String path) {
		this.path = path;
	}
	
	/**
	 * carica l'immagine specificata in path
	 * 
	 * @return immagine caricata
	 * @throws IOException
	 */
	public Image load() throws IOException{
		File imageFile = new File(this.path);
		image = ImageIO.read(imageFile);
		return image;
	}
	
	/**
	 * @return immagine
	 */
	public Image getImage() {
		return image;
	}
	/**
	 * @return percorso immagine
	 */
	public String getPath() {
		return path;
	}

	
	private Image image;
	private String path;
}
