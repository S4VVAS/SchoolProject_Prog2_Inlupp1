// Savvas Giortsis Sagi2536 (19980111-4852)

package application;

public abstract class Vardesaker {

	private final String NAME;
	private final double MOMS = 1.25;

	public Vardesaker(String name) {
		NAME = name;
	}

	public String getName() {
		return NAME;
	}

	public double getValue() {
		return calcValue() * MOMS;
	}

	abstract double calcValue();
}
 