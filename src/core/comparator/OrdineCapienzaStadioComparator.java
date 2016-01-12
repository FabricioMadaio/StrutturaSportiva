package core.comparator;

import java.util.Comparator;

import core.elementi.*;
/**
 * Ordina le partite secondo la capienza dello stadio in modo crescente.
 */
public class OrdineCapienzaStadioComparator  implements Comparator<Partita> 
{
/**
 * Il metodo serve ad ordinare le partite in ordine crescente a secondo della capienza 
 * dello stadio, ritorna un intero che ssta ad indicare il tipo di relazione di ricorrenza 
 * -1 se il primo paramtro è maggiore del secondo ,1 se il primo parametro è minore del 
 * secondo altrimenti 0 se sono uguali
 * 
 * @param Partita p1
 * @param Partita p2
 * @return int
 * 
 */
	@Override
	public int compare(Partita p1, Partita p2) {
		if(p1.getStadio().getCapienza() > p2.getStadio().getCapienza()) return -1;
		if(p1.getStadio().getCapienza() < p2.getStadio().getCapienza()) return 1;
		return 0;
	}

}
