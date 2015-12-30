package core.sconti;

import core.elementi.*;
import core.utente.*;

public interface Sconto
{
	/**
	 * Metodo che genenera lo sconto e verica condizione sul clente
	 * @param p
	 * @param c
	 * @return
	 */
	
	int getPercentualeSconto(Partita p, Cliente c);
	/**
	 * Verifica se lo sconto globale si puo inserire in una partita 
	 * @param p
	 * @return
	 */
	boolean verificaApplicabilita(Partita p);
	
}
