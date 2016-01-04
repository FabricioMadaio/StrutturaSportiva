package core.filtri;

import core.elementi.Partita;
import core.elementi.Stadio;

public class FiltroPartitaSuStadio implements FiltroPartita {

	public FiltroPartitaSuStadio(Stadio s){
		stadio = s;
	}
	
	@Override
	public boolean check(Partita p) {
		// TODO Auto-generated method stub
		return p.getStadio()==stadio;
	}

	private Stadio stadio;
}
