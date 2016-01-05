package core;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gui.graphics.Sprite;

public class Immagine {
	
	
	public Immagine(String path) {
		this.path = path;
	}
	
	public Image load() throws IOException{
		File imageFile = new File(this.path);
		image = ImageIO.read(imageFile);
		return image;
	}
	
	public Image getImage() {
		return image;
	}
	public String getPath() {
		return path;
	}

	
	private Image image;
	private String path;
}
