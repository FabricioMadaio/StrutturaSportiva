package core.filtri;

import core.elementi.Partita;
import core.elementi.Stadio;
/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 */
public class FiltroPartitaSuStadio implements FiltroPartita {
	/**
	 * Il costruttore della classe FiltroPartitaSuStadio inizializza un nuovo oggetto prendendo
	 * come riferimento uno stadio.
	 * @param Stadio s
	 */
	public FiltroPartitaSuStadio(Stadio s){
		stadio = s;
	}

	@Override
	public boolean check(Partita p) {

		return p.getStadio()==stadio;
	}

	private Stadio stadio;
}
