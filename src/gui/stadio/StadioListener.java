package gui.stadio;

/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 * 
 * listener che gestisce le operazioni di selezione su StadioCanvas
 */
public interface StadioListener {

	/**
	 * viene chiamata quando viene selezionata una poltroncina
	 * @param p poltroncina
	 */
	public void onSelected(Poltroncina p);
	/**
	 * viene chiama quando viene annullata la selezione di una poltroncina
	 * @param p poltroncina
	 */
	public void onReleased(Poltroncina p);
}
