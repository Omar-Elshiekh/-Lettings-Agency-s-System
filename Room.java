package Question_3;

public class Room {
	private double price = 0;

	public Room(double price) {
		if (price > 0) {
			this.price = price;
		}
	}

	public double getPrice() {
		return this.price;
	}
}
