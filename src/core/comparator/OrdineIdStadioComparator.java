package core.comparator;
import java.util.Comparator;

import core.elementi.Partita;

/**
 *Ordina le partite sencondo l'id stadio in modo crescente.
 */
public class OrdineIdStadioComparator  implements Comparator<Partita> 
{
	
	/**
	 * Il metodo ordina lessicograficamente le partite secondo l'id dello stadio il quale 
	 * coincide con il nome (di tipo Stringa) 
	 * @param Partita p1
	 * @param Partita p1
	 * @return int tipo della relazione di ricorrenza, vale -1 se il primo parametro � maggiore del secondo,
	 * 1 se il primo parametro � minore del secondo, 0 se sono uguali
	 */
	
	@Override
	public int compare(Partita p1, Partita p2) 
	{

		if(p1.getStadio().getNome().compareTo(p2.getStadio().getNome()) > 0) return 1;
		if(p1.getStadio().getNome().compareTo(p2.getStadio().getNome()) < 0) return -1;
		return 0;
	}

}
