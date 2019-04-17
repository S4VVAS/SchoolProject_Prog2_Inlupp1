// Savvas Giortsis Sagi2536

package application;

public class Apparater extends Vardesaker {

	private int retailPrice;
	private int condition;

	public Apparater(String namn, int retailPrice, int condition) {
		super(namn);
		this.retailPrice = retailPrice;
		this.condition = condition;
	}

	double calcValue() {
		return (retailPrice / 10.0) * condition;
	}

	@Override
	public String toString() {
		return getName() + " värde: " + Double.toString(getValue()) + " inköpspris: " + retailPrice + " skick: " + condition;
	}

}
