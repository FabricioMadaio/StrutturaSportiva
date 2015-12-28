package core.comparator;

import java.util.Comparator;

import core.elementi.*;

public class OrdineCapienzaStadioComparator  implements Comparator<Stadio> 
{

	@Override
	public int compare(Stadio s1, Stadio s2) {
		if(s1.getCapienza() > s2.getCapienza()) return 1;
		if(s1.getCapienza() < s2.getCapienza()) return -1;
		return 0;
	}

}
