package juegoDeCartas;

public class Jugador {
	private String alias;
	private int partidasGanadas; //Por defecto int es 0
	private int partidasPerdidas;
	private int partidasJugadas;
	private double puntuacion;
	
	
	public int getPartidasJugadas() {
		return partidasJugadas;
	}
	public void setPartidasJugadas(int partidasJugadas) {
		this.partidasJugadas = partidasJugadas;
	}
	public Jugador(String alias) {
		setAlias(alias);
		setPuntuacion(0.0);
		/*Esto no es necesario	setPartidasGanadas(0);
			setPartidasPerdidas(0);
			setPartidasJugadas(0)*/
		
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public int getPartidasGanadas() {
		return partidasGanadas;
	}
	public void setPartidasGanadas(int partidasGanadas) {
		this.partidasGanadas = partidasGanadas;
	}
	public int getPartidasPerdidas() {
		return partidasPerdidas;
	}
	public void setPartidasPerdidas(int partidasPerdidas) {
		this.partidasPerdidas = partidasPerdidas;
	}
	@Override
	public String toString() {
		return "Jugador [alias=" + alias + ", partidasGanadas=" + partidasGanadas + ", partidasPerdidas="
				+ partidasPerdidas + " partidasJugadas=" + partidasJugadas+" puntuación="+puntuacion+"]";
	}
	public double getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}
}
