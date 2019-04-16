// Savvas Giortsis Sagi2536 (19980111-4852)

package application;

public class Apparater extends Vardesaker {

	private int retailPrice;
	private int condition;

	public Apparater(String namn, int retailPrice, int condition) {
		super(namn);
		this.retailPrice = retailPrice;
		if(condition >= 10)
			this.condition = 10;
		else if(condition <= 1)
			this.condition = 1;
		else
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
