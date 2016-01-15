package core.comparator;

import java.util.Comparator;

import core.elementi.Partita;
/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 *
 * Ordina le partite secondo il nome del match in modo crescente.
 */
public class OrdineLessicoGraficoComparatorNomeSquadre implements Comparator<Partita> 
{
	/**
	 * Il metodo prende proprio la stringa "SquadraA vs SquadraB" restituita da getGame
	 * in seguito avviene un semplice confronto di tipo lessicografico tra stringe.
	 * @param Partita p1
	 * @param Partita p2
	 * @return int tipo della relazione di ricorrenza, vale -1 se il primo parametro è maggiore del secondo,
	 * 1 se il primo parametro è minore del secondo, 0 se sono uguali
	 */
	@Override
	public int compare(Partita p1, Partita p2) 
	{
		if(p1.getGame().compareTo(p2.getGame()) > 0) return 1;
		if(p1.getGame().compareTo(p2.getGame()) < 0) return -1;
		return 0;
	}

}
