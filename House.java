package Question_3;

public class House extends Property {
	public House(int houseNumber, String street, String city,
			String postCode, int numberOfRooms) {
		super(houseNumber, street, city, postCode, numberOfRooms);
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
		boolean empty = false;
		if (this.getAvailableRooms() > 0) {
			empty = true;
		
		}
		return empty;
	}
	@Override
	public void occupy(Room r, ITenant t) {
		// Checks whether or not Room is available first
		// If it is, the room will remain "Available"
		if (this.isAvailable() == true) {
			if (r == null) {
			} else if (r.getPrice() <= 0) {
			} else if (t.getType() == null) {
			} else {
				ITenant currentTenant = super.rooms.get(r);
				if (currentTenant == null) {
					super.rooms.put(r, t);
				} else {
					System.out.println(
					"Room is already occupied");
				}
			}
		}
	}
	  @Override
	  public String displayOccupiedProperty() {
	  double totalPrice = 0;
	  for (Room room: rooms.keySet()) {
		  totalPrice += room.getPrice();
	  }
	  totalPrice = (totalPrice * 12);
	  String output = super.toString() + " house :"
	  + super.getAvailableRooms() + " available)\n";
	  for (Room room : super.rooms.keySet()) {
		  output += "\tRoom: " + room.getPrice() + "\n";
	  }
	  if (super.getAvailableRooms() == 0) {
		  output += "\t" + "Total: £"
				  + String.format("%.2f", totalPrice)
				  + " (Council Tax: £" + super.getCouncilTax() + ")";		  
	  }
	  return output;
	  }
	@Override
	public String toString() {
		return super.toString() + " house :"
	+ super.getAvailableRooms() + " available)";
	}
}
