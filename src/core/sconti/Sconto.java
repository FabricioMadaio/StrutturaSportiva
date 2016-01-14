package core.sconti;

import core.elementi.*;
import core.utente.*;

public interface Sconto
{
	/**
	 * Metodo che ritorna la percentuale dello sconto,se un cliente
	 * usufruisce di un particolare sconto la percentuale ritornata 
	 * terrà conto di tale sconto
	 * 
	 * @param Partita p
	 * @param Cliente c
	 * @return
	 */
	
	int getPercentualeSconto(Partita p, Cliente c);
	/**
	 * Verifica se lo sconto globale si puo inserire in una partita 
	 * @param Partita p
	 * @return boolean
	 */
	boolean verificaApplicabilita(Partita p);
	
}
