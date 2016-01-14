package core.sconti;

import core.elementi.*;
import core.utente.*;

/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 */
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
