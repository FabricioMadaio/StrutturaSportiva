package core.comparator;
import java.util.Comparator;

import core.elementi.Partita;

public class OrdineIdStadioComparator  implements Comparator<Partita> 
{

	@Override
	public int compare(Partita p1, Partita p2) 
	{
		if(p1.getIdStadio().compareTo(p2.getIdStadio()) > 0) return 1;
		if(p1.getIdStadio().compareTo(p2.getIdStadio()) < 0) return -1;
		return 0;
	}

}
