package core.comparator;
import java.util.Comparator;

import core.elementi.Partita;

public class OrdineIdStadioComparator  implements Comparator<Partita> 
{

	@Override
	public int compare(Partita p1, Partita p2) 
	{
		if(p1.getStadio().getNome().compareTo(p2.getStadio().getNome()) > 0) return 1;
		if(p1.getStadio().getNome().compareTo(p2.getStadio().getNome()) < 0) return -1;
		return 0;
	}

}
