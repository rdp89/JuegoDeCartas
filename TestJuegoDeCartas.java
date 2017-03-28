package juegoDeCartas;

import java.util.*;

import utiles.*;

/**
 * 
 * 
 * Diseña e implementa un juego de cartas. Para ello, comienza implementando el
 * juego de las siete y media. Es opcional implementar otro juego. Recuerda que:
 * 
 * A las siete y media se juega con una baraja española Pueden jugar tantos
 * jugadores como se quiera. Al principio del juego se indicará el alias de los
 * jugadores implicados. Se podrá averiguar el número de partidas ganadas y
 * perdidas por cada jugador. Se podrá mostrar el ranking de las partidas
 * jugadas Al iniciar cada partida se preguntará qué jugador juega y cuál no.
 * Utiliza el método shuffle para barajar las cartas
 * 
 * Para que te plantees el diseño te dejo estas preguntas:
 * 
 * El hecho de pedir una carta, ¿a qué método se refiere? ¿De qué objeto/clase?
 * El hecho de plantarse un jugador, ¿a qué método se refiere? ¿De qué
 * objeto/clase? El hecho de plantarse, ¿a qué método se refiere? ¿De qué
 * objeto/clase? El hecho de preguntar el nombre al jugador, ¿a qué método se
 * refiere? ¿De qué objeto/clase? El hecho de preguntarle a un usuario si va a
 * jugar o no, ¿a qué método se refiere? ¿De qué objeto/clase? ¿En qué se
 * diferencia una partida de otra? ¿Coincide la baraja? ¿Qué tienen en común
 * todas las partidas de siete y media? ¿Utilizas algún valor
 * constante?¿Utilizas enumeraciones?
 * 
 * @author Rafael Delgado Peña
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
				new String[] { "Crear/Eliminar jugadores", "Añadir/Quitar participantes", "Jugar" });
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
			System.err.println("No hay jugadores creados aún");
		else {
			Menu menuParticipantes = new Menu("--Gestión participantes---",
					new String[] { "Añadir", "Quitar", "Mostrar participantes" });
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
			int sacar = Teclado.leerEntero("Introduce el número del jugador:");
			if (sacar > jugadoresListosParaJugar.size() || sacar < 1)
				System.out.println("Número inválido");
			else {
				System.out.println(jugadoresListosParaJugar.get(sacar - 1).getAlias() + " abandonó la partida...");
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
			System.out.println("No hay jugadores que añadir... ve al menú anterior y crea uno nuevo...");
			return;
		}
		int meter = Teclado.leerEntero("Introduce el número del jugador:");
		if (meter > jugadoresApuntados.size() || meter < 1)
			System.out.println("Número inválido");
		else {
			jugadoresListosParaJugar.add(jugadoresApuntados.get(meter - 1));
			System.out.println(jugadoresApuntados.get(meter - 1).getAlias() + " se ha unido al juego!");
			jugadoresApuntados.remove(meter - 1);
		}

	}

	private static void empezarPartida() {
		if (jugadoresListosParaJugar.size() < 2)
			System.out.println("Debe haber 2 participantes mínimo para jugar...");
		else {
			partida = new Partida(jugadoresListosParaJugar);
			partida.start();
			//partida.sacarGanador(jugadoresListosParaJugar);
		}

	}

	private static void gestionarJugadores() {
		Menu menuJugadores = new Menu("---Gestión de jugadores---",
				new String[] { "Añadir jugador", "Eliminar jugador", "Mostrar jugadores apuntados" });
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
				int indice = Teclado.leerEntero("Introduce el número de jugador que desea borrar:");
				if (indice > jugadoresApuntados.size() || indice < 1) {
					System.out.println(indice + " es un número inválido");
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
