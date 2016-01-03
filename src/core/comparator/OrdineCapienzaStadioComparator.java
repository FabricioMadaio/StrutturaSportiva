package core.comparator;

import java.util.Comparator;

import core.elementi.*;

public class OrdineCapienzaStadioComparator  implements Comparator<Partita> 
{

	@Override
	public int compare(Partita p1, Partita p2) {
		if(p1.getStadio().getCapienza() > p2.getStadio().getCapienza()) return -1;
		if(p1.getStadio().getCapienza() < p2.getStadio().getCapienza()) return 1;
		return 0;
	}

}
