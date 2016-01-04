package core.comparator;

import java.util.Comparator;

import core.elementi.Partita;

public class OrdineLessicoGraficoComparatorNomeSquadre implements Comparator<Partita> 
{

	@Override
	public int compare(Partita p1, Partita p2) 
	{
		if(p1.getSquadraA().compareTo(p2.getSquadraB()) > 0) return 1;
		if(p1.getSquadraA().compareTo(p2.getSquadraB()) < 0) return -1;
		return 0;
	}

}
