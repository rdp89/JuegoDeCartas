package juegoDeCartas;

import java.util.*;

import utiles.Teclado;

public class Partida {
	private ArrayList<Jugador> players = new ArrayList<Jugador>();
	private Baraja baraja = new Baraja();
	private final static double PUNTUACION_MAX = 7.5;

	public Partida(ArrayList<Jugador> players) {
		setPlayers(players);
	}

	private void setPlayers(ArrayList<Jugador> players) {
		this.players = players;

	}

//	public ArrayList<Jugador> getPlayers() {
//		return players;
//	}

//	public Baraja getBaraja() {
//		return baraja;
//	}

	public void start() {
	//	int ronda = 0;
//		do {
//			ronda++;
//			System.out.println("Ronda " + ronda);

			for (int i = 0; i < players.size(); i++) {
				String alias = players.get(i).getAlias();
				System.out.println("Es el turno de " + alias);
				do {
					Carta cartaObtenida = baraja.darCarta();
					if (cartaObtenida == null)
						break;
					System.out.println(alias + " saca " + cartaObtenida.toString());
					players.get(i).setPuntuacion(players.get(i).getPuntuacion() + cartaObtenida.getValor());
					
					if (players.get(i).getPuntuacion() > PUNTUACION_MAX)
						System.out.println("\n" + players.get(i).getPuntuacion() + " . Te has pasado!");
					else if (players.get(i).getPuntuacion() == PUNTUACION_MAX)
						System.out.println("Has ganado!!");
					else
						System.out.println("\nPuntuación: " + players.get(i).getPuntuacion());

				} while (players.get(i).getPuntuacion() <= PUNTUACION_MAX && otraRonda("Quiere otra carta?(S/N)"));
				System.out.println("---Puntuaciones actuales---");
				for (int j = 0; j < players.size(); j++) {
					System.out.println(players.get(j).getAlias() + ": " + players.get(j).getPuntuacion());
				}

			}
		//} while (otraRonda("Otra ronda??(S/N)"));

		checkearGanador();

	}

	private void checkearGanador() {
		System.out.println("Ganador/es: ");
		//más de un ganador con return
		//todos pasados return
		//buscar el que sin pasarse el valor más alto 
		if (sacarGanador().length == 1)
			sacarGanador()[0].getAlias();
		else {
			for (int i = 0; i < sacarGanador().length; i++) {
				sacarGanador()[i].getAlias();
			}
		}
	}

	public Jugador[] sacarGanador() {
		// Jugador ganador = new Jugador("Banca");
		Jugador[] ganadores;
		int i = 0;
		if (players.size() > 1)
			ganadores = new Jugador[players.size()];
		else
			ganadores = new Jugador[1];
		ganadores[0] = new Jugador("Banca");
		for (Jugador jugador : players) {
			if (jugador.getPuntuacion() == 7.5) {
				ganadores[0] = jugador;

			} else if (jugador.getPuntuacion() > ganadores[0].getPuntuacion() && jugador.getPuntuacion() < 7.5) {
				ganadores[0] = jugador;
				System.out.println(jugador);

			} else if (jugador.getPuntuacion() == ganadores[0].getPuntuacion() && jugador.getPuntuacion() < 7.5) {
				ganadores[i++] = jugador;
			}
		}
		return ganadores;

	}

	/*
	 * private void sacarGanador(double puntos) { if(puntos < 7.5)
	 * System.out.println("No sé qué hacer"); }
	 */

	private boolean otraRonda(String msj) {
		char respuesta = Teclado.leerCaracter(msj);
		if (respuesta == 's' || respuesta == 'S')
			return true;
		return false;
	}

	/*
	 * public void sacarGanador(ArrayList<Jugador> players) { Iterator<Jugador>
	 * it = players.iterator(); System.out.println("---Puntuaciones---"); int
	 * index = 0; while (it.hasNext()) {
	 * System.out.println(players.get(index++).getAlias()+": "+players.get(index
	 * ).getPuntuacion()); }
	 * 
	 * }
	 */

}
