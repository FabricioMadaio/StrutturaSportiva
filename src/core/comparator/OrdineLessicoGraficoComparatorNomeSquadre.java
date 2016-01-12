package core.comparator;

import java.util.Comparator;

import core.elementi.Partita;
/**
 *Ordina le partite secondo il nome del match in modo crescente.
 */
public class OrdineLessicoGraficoComparatorNomeSquadre implements Comparator<Partita> 
{
	/**
	 * Il metodo prende proprio la stringa "SquadraA vs SquadraB" e inizia a confrontare 
	 * in questo modo da ordinare in modo giusto le partite.
	 * Ritorna 1 se la prima partita è "maggiore" della seconda,-1 se la senconda è "maggiore"
	 * della prima altrimenti ritorna 0 perchè sono uguali.  
	 * @param Partita p1
	 * @param Partita p2
	 * @return int
	 */
	@Override
	public int compare(Partita p1, Partita p2) 
	{
		if(p1.getGame().compareTo(p2.getGame()) > 0) return 1;
		if(p1.getGame().compareTo(p2.getGame()) < 0) return -1;
		return 0;
	}

}
