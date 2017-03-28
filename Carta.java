package juegoDeCartas;

public class Carta {
	private Palos palo;
	private Figuras figura;
	
	public Carta(Palos palo, Figuras figura) {
		setPalo(palo);
		setFigura(figura);
	}
	public Palos getPalo() {
		return palo;
	}
	public void setPalo(Palos palo) {
		this.palo = palo;
	}
	public Figuras getFigura() {
		return figura;
	}
	public void setFigura(Figuras figura) {
		this.figura = figura;
	}
	
	@Override
	public String toString() {
		return "" + figura + " de " + palo + "";
	}
	public double getValor() {
		return figura.getValor();
	}
}
