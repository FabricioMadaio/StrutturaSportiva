package core.comparator;

import java.util.Comparator;

import core.elementi.Partita;

/**
 * Ordina le partite in modo cronologico crecente.
 */
public class OrdineCronologicoComparator  implements Comparator<Partita> 
{
	/**
	 * Il metodo serve ad ordinare le partite in ordine crescente a secondo della data, ritorna un intero che ssta ad indicare il tipo di relazione di ricorrenza 
	 * 1 se la prima data viene dopo la seconda ,-1 se la prima data viene prima della seconda altrimenti
	 *  0 se sono uguali.
	 * 
	 * @param Partita p1
	 * @param Partita p2
	 * @return int
	 * 
	 */
	@Override
	public int compare(Partita p1, Partita p2)
	{
		if(p1.getData().after(p2.getData())) return 1;
		if(p1.getData().before(p2.getData())) return -1;
		return 0;
	}

}
