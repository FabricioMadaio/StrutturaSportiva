package core.comparator;

import java.util.Comparator;

import core.elementi.Partita;

/**
 * Ordina le partite in modo cronologico crecente.
 */
public class OrdineCronologicoComparator  implements Comparator<Partita> 
{
	/**
	 * Il metodo serve ad ordinare le partite in ordine crescente a secondo della data
	 * 
	 * @param Partita p1
	 * @param Partita p2
	 * @return int tipo della relazione di ricorrenza, vale -1 se il primo parametro è maggiore del secondo,
	 * 1 se il primo parametro è minore del secondo, 0 se sono uguali
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
