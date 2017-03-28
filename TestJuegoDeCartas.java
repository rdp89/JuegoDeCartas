package juegoDeCartas;

import java.util.*;

import utiles.*;

/**
 * 
 * 
 * Dise�a e implementa un juego de cartas. Para ello, comienza implementando el
 * juego de las siete y media. Es opcional implementar otro juego. Recuerda que:
 * 
 * A las siete y media se juega con una baraja espa�ola Pueden jugar tantos
 * jugadores como se quiera. Al principio del juego se indicar� el alias de los
 * jugadores implicados. Se podr� averiguar el n�mero de partidas ganadas y
 * perdidas por cada jugador. Se podr� mostrar el ranking de las partidas
 * jugadas Al iniciar cada partida se preguntar� qu� jugador juega y cu�l no.
 * Utiliza el m�todo shuffle para barajar las cartas
 * 
 * Para que te plantees el dise�o te dejo estas preguntas:
 * 
 * El hecho de pedir una carta, �a qu� m�todo se refiere? �De qu� objeto/clase?
 * El hecho de plantarse un jugador, �a qu� m�todo se refiere? �De qu�
 * objeto/clase? El hecho de plantarse, �a qu� m�todo se refiere? �De qu�
 * objeto/clase? El hecho de preguntar el nombre al jugador, �a qu� m�todo se
 * refiere? �De qu� objeto/clase? El hecho de preguntarle a un usuario si va a
 * jugar o no, �a qu� m�todo se refiere? �De qu� objeto/clase? �En qu� se
 * diferencia una partida de otra? �Coincide la baraja? �Qu� tienen en com�n
 * todas las partidas de siete y media? �Utilizas alg�n valor
 * constante?�Utilizas enumeraciones?
 * 
 * @author Rafael Delgado Pe�a
 * @version 1.0
 *
 */

public class TestJuegoDeCartas {

	static ArrayList<Jugador> jugadoresApuntados = new ArrayList<Jugador>();
	static ArrayList<Jugador> jugadoresListosParaJugar = new ArrayList<Jugador>();
	static Partida partida;

	public static void main(String[] args) {
		bienvenida();
	}

	private static void bienvenida() {
		Menu menuBienvenida = new Menu("---Bienvenido/a al juego de las 7 y media---",
				new String[] { "Crear/Eliminar jugadores", "A�adir/Quitar participantes", "Jugar" });
		do {
			int opcion = menuBienvenida.gestionar();
			switch (opcion) {
			case 1:
				gestionarJugadores();
				break;
			case 2:
				gestionParticipantes();
				break;
			case 3:
				empezarPartida();
				break;
			default:
				return;
			}
		} while (true);
	}

	private static void gestionParticipantes() {
		if (jugadoresApuntados.isEmpty())
			System.err.println("No hay jugadores creados a�n");
		else {
			Menu menuParticipantes = new Menu("--Gesti�n participantes---",
					new String[] { "A�adir", "Quitar", "Mostrar participantes" });
			do {
				int opcion = menuParticipantes.gestionar();
				switch (opcion) {
				case 1:
					addParticipe();
					break;
				case 2:
					delParticipe();
					break;
				case 3:
					mostrarParticipantes();
					break;
				default:
					return;
				}
			} while (true);
		}
	}

	private static void delParticipe() {
		if (jugadoresListosParaJugar.isEmpty())
			System.out.println("No hay participantes...");
		else {
			mostrarParticipantes();
			int sacar = Teclado.leerEntero("Introduce el n�mero del jugador:");
			if (sacar > jugadoresListosParaJugar.size() || sacar < 1)
				System.out.println("N�mero inv�lido");
			else {
				System.out.println(jugadoresListosParaJugar.get(sacar - 1).getAlias() + " abandon� la partida...");
				jugadoresApuntados.add(jugadoresListosParaJugar.get(sacar - 1));
				jugadoresListosParaJugar.remove(sacar - 1);
			}

		}

	}

	private static void mostrarParticipantes() {
		System.out.println("---Participantes---");
		if (jugadoresListosParaJugar.isEmpty())
			System.out.println("Sin participantes");
		else {
			Iterator<Jugador> it = jugadoresListosParaJugar.iterator();
			int contadorParaIterador = 1;
			while (it.hasNext())
				System.out.println(contadorParaIterador++ + ". " + it.next());
		}
	}

	private static void addParticipe() {
		if (!(jugadoresApuntados.isEmpty()))
			mostrarJugadoresApuntados();
		else {
			System.out.println("No hay jugadores que a�adir... ve al men� anterior y crea uno nuevo...");
			return;
		}
		int meter = Teclado.leerEntero("Introduce el n�mero del jugador:");
		if (meter > jugadoresApuntados.size() || meter < 1)
			System.out.println("N�mero inv�lido");
		else {
			jugadoresListosParaJugar.add(jugadoresApuntados.get(meter - 1));
			System.out.println(jugadoresApuntados.get(meter - 1).getAlias() + " se ha unido al juego!");
			jugadoresApuntados.remove(meter - 1);
		}

	}

	private static void empezarPartida() {
		if (jugadoresListosParaJugar.size() < 2)
			System.out.println("Debe haber 2 participantes m�nimo para jugar...");
		else {
			partida = new Partida(jugadoresListosParaJugar);
			partida.start();
			//partida.sacarGanador(jugadoresListosParaJugar);
		}

	}

	private static void gestionarJugadores() {
		Menu menuJugadores = new Menu("---Gesti�n de jugadores---",
				new String[] { "A�adir jugador", "Eliminar jugador", "Mostrar jugadores apuntados" });
		do {
			int opcion = menuJugadores.gestionar();
			switch (opcion) {
			case 1:
				String addJ = Teclado.leerCadena("Introduce el alias: ");
				jugadoresApuntados.add(new Jugador(addJ));
				System.out.println("Jugador " + addJ + " creado!");
				break;
			case 2:
				if (jugadoresApuntados.isEmpty()) {
					System.err.println("No hay jugadores a eliminar...\n");
					break;
				}
				mostrarJugadoresApuntados();
				int indice = Teclado.leerEntero("Introduce el n�mero de jugador que desea borrar:");
				if (indice > jugadoresApuntados.size() || indice < 1) {
					System.out.println(indice + " es un n�mero inv�lido");
					break;
				}
				System.out.println("Borrando jugador " + jugadoresApuntados.get(indice - 1).getAlias() + "...");
				jugadoresApuntados.remove(indice - 1);
				System.out.println("Borrado!");
				break;
			case 3:
				mostrarJugadoresApuntados();
				break;
			default:
				return;
			}
		} while (true);
	}

	private static void mostrarJugadoresApuntados() {
		System.out.println("---Jugadores apuntados---");
		if (jugadoresApuntados.isEmpty())
			System.out.println("Sin jugadores");
		else {
			Iterator<Jugador> it = jugadoresApuntados.iterator();
			int contadorParaIterador = 1;
			while (it.hasNext())
				System.out.println(contadorParaIterador++ + ". " + it.next());
		}
	}

}
