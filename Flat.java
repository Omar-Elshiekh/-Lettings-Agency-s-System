package Question_3;

public class Flat extends Property {
	private static final double MAINTENANCE_COSTS = 500;
	private int floor = 0;

	public Flat(int houseNumber, String street, String city, String postCode, int numberOfRooms, int floor) {
		super(houseNumber, street, city, postCode, numberOfRooms);
		this.floor = floor;

	}

	@Override
	public double getPrice() {
		double totalPrice = 0;
		for (Room room : super.rooms.keySet()) {
			totalPrice += room.getPrice();
		}
		return totalPrice;
	}

	@Override
	public boolean isAvailable() {
		boolean empty = true;
		for (ITenant tenant : super.rooms.values()) {
			if (tenant.getType() == TenantType.PROFESSIONAL) {
				empty = false;
				break;
			}
		}
		return empty;
	}

	@Override
	public void occupy(Room r, ITenant t) {
		boolean tenantIsPro = true;
		for (ITenant tenant : super.rooms.values()) {
			if (!tenant.getType().equals(TenantType.PROFESSIONAL)) {
				tenantIsPro = false;
			}
		}
		if (this.isAvailable() == true) {
			if (tenantIsPro) {
					ITenant currentTenant = super.rooms.get(r);
					if (currentTenant == null) {
						rooms.put(r, t);
					} else {
						System.out.println("Room is already occupied");
					}
				}
			}
		}

	@Override
	public String displayOccupiedProperty() {
		double totalPrice = getPrice();
		totalPrice = ((totalPrice * 12) + MAINTENANCE_COSTS);
		String output = super.toString() + " flat on " + getFloor() + " floor :" + super.getAvailableRooms()
				+ " available" + "\n";
		for (Room room : super.rooms.keySet()) {
			output += "\t" + "Room: " + room.getPrice() + "\n";
		}
		output += "Total: £" + totalPrice + " (Council Tax: £" + super.getCouncilTax() + ")";
		return output;
	}

	@Override
	public String toString() {
		return super.toString() + " flat on " + this.getFloor() + " floor :" + super.getAvailableRooms()
				+ " available)";
	}

	private int getFloor() {
		return floor;
	}
}
