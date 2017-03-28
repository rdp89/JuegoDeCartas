package juegoDeCartas;

public enum Figuras {
	AS(1),
	DOS(2),
	TRES(3),
	CUATRO(4),
	CINCO(5),
	SEIS(6),
	SIETE(7),
	SOTA(0.5),
	CABALLO(0.5),
	REY(0.5);
	
	private final double valor;

	public double getValor() {
		return valor;
	}

	private Figuras(double valor) {
		this.valor = valor;
	}
	
	

	
	
	}