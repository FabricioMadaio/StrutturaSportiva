package core.comparator;
import java.util.Comparator;

import core.elementi.Partita;

/**
 *Ordina le partite sencondo l'id stadio in modo crescente.
 */
public class OrdineIdStadioComparator  implements Comparator<Partita> 
{
	
	/**
	 * Il metodo ordina lessicograficamente le partite secondo l'id dello stadio il quale è 
	 * una stringa ritorna 1 se l'id dello stadio dove si svolge la prima partita
	 * è "maggiore"  dell'id dello stadio dove si svolge la seconda partita , -1 se l'id dello stadio dove si svolge la seconda partita 
	 * è "maggiore " dell'id dello stadio dove si svolge la seconda partita poi se sono uguali ritorna 0.
	 * @param Partita p1
	 * @param Partita p1
	 * @return int
	 */
	
	@Override
	public int compare(Partita p1, Partita p2) 
	{

		if(p1.getStadio().getNome().compareTo(p2.getStadio().getNome()) > 0) return 1;
		if(p1.getStadio().getNome().compareTo(p2.getStadio().getNome()) < 0) return -1;
		return 0;
	}

}
