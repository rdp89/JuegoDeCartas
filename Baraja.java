package juegoDeCartas;

import java.util.*;

public class Baraja {
	
	private ArrayList<Carta> baraja = new ArrayList<Carta>();
	
	
	Baraja(){
		for(Palos palo : Palos.values())
			for(Figuras figura : Figuras.values())
				baraja.add(new Carta(palo, figura));
		Collections.shuffle(baraja);
	} 
	
	public ArrayList<Carta> getBaraja() {
		return baraja;
	}
	
	/**
	 * Da una mano valida
	 * @return
	 */
	public Carta darCarta(){
//		if (baraja.isEmpty()) {
//			throw new NoHayCartasException();
//		return baraja.remove(0);
//		} else {
//			return null;
//		}
		
		if (!baraja.isEmpty()) {
			return baraja.remove(0);
		} else {
			return null;
		}
	}
	
	@Override
	public String toString() {
		return "" + getBaraja().toString() + "";
	}
}
