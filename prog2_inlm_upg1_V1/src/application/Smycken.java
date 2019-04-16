// Savvas Giortsis Sagi2536 (19980111-4852)

package application;

public class Smycken extends Vardesaker {

	private int amtStones = 0;
	private Boolean material;
	private String materialName;

	public Smycken(String namn, Boolean material, int amtStones) {
		super(namn);
		this.amtStones = amtStones;
		this.material = material;
	}

	double calcValue() {
		if (material) { // guld
			materialName = "Guld";
			return 2000 + (500 * amtStones);
		} else if (!material) { // silver
			materialName = "Silver";
			return 700 + (500 * amtStones);
		}
		return -1;
	}

	@Override
	public String toString() {
		return getName() + " värde: " + Double.toString(getValue()) + " av: " + materialName + ", antal ädelstenar: " + amtStones;
	}

}
