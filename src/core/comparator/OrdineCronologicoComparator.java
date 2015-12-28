package core.comparator;

import java.util.Comparator;

import core.elementi.Partita;

public class OrdineCronologicoComparator  implements Comparator<Partita> 
{

	@Override
	public int compare(Partita p1, Partita p2)
	{
		if(p1.getData().after(p2.getData())) return 1;
		if(p1.getData().before(p2.getData())) return -1;
		return 0;
	}

}
