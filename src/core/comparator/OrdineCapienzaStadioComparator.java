package core.comparator;

import java.util.Comparator;

import core.elementi.*;
/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 * 
 * Ordina le partite secondo la capienza dello stadio in modo crescente.
 */
public class OrdineCapienzaStadioComparator  implements Comparator<Partita> 
{
	/**
	 * Il metodo serve ad ordinare le partite in ordine crescente a seconda della capienza 
	 * dello stadio
	 * 
	 * @param Partita p1
	 * @param Partita p2
	 * @return int tipo della relazione di ricorrenza, vale -1 se il primo parametro è maggiore del secondo,
	 * 1 se il primo parametro è minore del secondo, 0 se sono uguali
	 * 
	 */
	@Override
	public int compare(Partita p1, Partita p2) {
		if(p1.getStadio().getCapienza() > p2.getStadio().getCapienza()) return -1;
		if(p1.getStadio().getCapienza() < p2.getStadio().getCapienza()) return 1;
		return 0;
	}

}
