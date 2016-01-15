package core.filtri;

import core.elementi.Partita;
/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 */
public interface FiltroPartita {
	/**
	 * Il metodo controlla se una partita si disputa nella settimana selezionata 
	 * o nello stadio selezionato.
	 * 
	 * @param Partita p
	 * @return boolean
	 */
	boolean check(Partita p);
}
