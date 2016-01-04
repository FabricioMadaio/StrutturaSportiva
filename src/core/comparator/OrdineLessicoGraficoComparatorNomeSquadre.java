package core.comparator;

import java.util.Comparator;

import core.elementi.Partita;

public class OrdineLessicoGraficoComparatorNomeSquadre implements Comparator<Partita> 
{

	@Override
	public int compare(Partita p1, Partita p2) 
	{
		if(p1.getGame().compareTo(p2.getGame()) > 0) return 1;
		if(p1.getGame().compareTo(p2.getGame()) < 0) return -1;
		return 0;
	}

}
