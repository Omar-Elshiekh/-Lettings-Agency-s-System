package Question_3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Property {
	private int houseNumber = 0;
	private String street = "";
	private String city = "";
	private String postCode = "";
	private int numberOfRooms = 0;
	protected Map<Room, ITenant> rooms;
	protected double councilTax = 0;
	private List<Complaint> complaints;
	public Map<Room, ITenant> getRooms() {
		return rooms;
	}
	public double getCouncilTax() {
		return this.councilTax;
	}
	public Property(int houseNumber, String street, String city,
			String postCode, int numberOfRooms) {
		if (houseNumber > 0) {
			this.houseNumber = houseNumber;
		} else {
			throw new IllegalArgumentException("House number must"
					+ " be less than zero");
		}
		if (street != "" && !street.equals("")) {
			this.street = street;
		} else {
			throw new NullPointerException("Street can't"
					+ " be null");
		}
		if (this.validateCity(city)) {
			this.city = city;
		}
		if (this.validatePostCode(postCode)) {
			this.postCode = postCode;
		}
		this.rooms = new HashMap<Room, ITenant>();
		this.numberOfRooms = numberOfRooms;
		this.complaints = new ArrayList<Complaint>();
		}
	public void addComplaint(Complaint c) {
		this.complaints.add(c);
	}
	public int calculateImpact() {
		int totalWeight = 0;
		for (Complaint complaint : this.complaints) {
			totalWeight += complaint.getSeverity().getWeight();
		}
		return totalWeight;
	}
	public int getAvailableRooms() {
		int occupiedRooms = 0;
		for (ITenant tenant : this.rooms.values()) {
	        if (tenant != null) {
	            occupiedRooms++;
	        }
		}
		return this.numberOfRooms - occupiedRooms;
	}
	public void setCouncilTax(double tax) {
		int noOfStudents = 0;
		int noOfProfessionals = 0;
		for (ITenant tenant: this.rooms.values()) {
			if (tenant.getType().equals(TenantType.STUDENT)) {
				noOfStudents++;
			} else if (tenant.getType()
					.equals(TenantType.PROFESSIONAL)) {
				noOfProfessionals++;
			}
		}
		if (this.getAvailableRooms() == 0) {
			if (noOfStudents > 0 && noOfProfessionals == 0) {
				this.councilTax = 0;
			} else if (noOfProfessionals == 1 && noOfStudents == 0) {
				this.councilTax = tax * 0.75;
			} else if (noOfProfessionals <= 1 && noOfStudents >= 1) {
				this.councilTax = tax * 0.75;
			} else if (noOfProfessionals >= 1 && noOfStudents == 0) {
				this.councilTax = tax * 1;
			}			
		}
	}
	@Override
	public String toString() {
		return this.houseNumber + " " + this.street + ", "
				+ this.city + " "
				+ this.postCode + " (" + this.numberOfRooms
				+ " bedroom";
	}
	private boolean validateCity(String input) {
		String validCity = "Guildford";
		boolean valid = false;
		if (input == null) {
			throw new NullPointerException("City name can't"
					+ " be null");
		}
		if (input.matches(validCity)) {
			valid = true;
		} else {
			throw new IllegalArgumentException("City name"
					+ " is invalid");
		}
		return valid;
	}
	private boolean validatePostCode(String input) {
		boolean valid = false;
		String validPostCode = "[G][U][0-9]{1,2}[ ]?"
				+ "[0-9]?[A-Z]{2}";
		if (input == null) {
			throw new NullPointerException("Postcode can't"
					+ " be null");
		}
		if (input.matches(validPostCode)) {
			valid = true;
		} else {
			throw new IllegalArgumentException("Invalid"
					+ " Postcode");
		}
		return valid;
	}
	public abstract double getPrice();
	public abstract boolean isAvailable();
	public abstract void occupy(Room r, ITenant t);
	public abstract String displayOccupiedProperty();
}
