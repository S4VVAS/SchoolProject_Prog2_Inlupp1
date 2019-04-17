// Savvas Giortsis Sagi2536

package application;

public class Aktier extends Vardesaker {

	private int antal;
	private double kurs;

	public Aktier(String namn, int antal, double kurs) {
		super(namn);
		this.antal = antal;
		this.kurs = kurs;
	}

	double calcValue() {
		return antal * kurs;
	}

	public void crash() {
		kurs = 0;
	}

	@Override
	public String toString() {
		return getName() + " värde: " + Double.toString(getValue()) + " antal: " + antal + " kurs: " + kurs;
	}

}
