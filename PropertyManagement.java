package Question_3;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

public class PropertyManagement {
	private List<Property> properties;
	private List<ITenant> tenants;

	public PropertyManagement(List<Property> properties) {
		this.properties = properties;
		this.properties.addAll(properties);
	}

	public PropertyManagement() {
		this.properties = new ArrayList<Property>();
		this.tenants = new ArrayList<ITenant>();
	}

	public List<ITenant> getTenants() {
		return tenants;
	}

	public void addProperty(Property p) {
		properties.add(p);
	}

	public void addTenant(Property p, Room r, ITenant t) {
		if (this.properties.contains(p)) {
			if (p.getClass().getSimpleName().equals("House")) {
				p.occupy(r, t);
			} else if (p.getClass().getSimpleName().equalsIgnoreCase("Flat")
					&& t.getType().equals(TenantType.PROFESSIONAL)) {
				p.occupy(r, t);
			}
		}
		if (p.getClass().getSimpleName().equalsIgnoreCase("Flat") && t.getType().equals(TenantType.PROFESSIONAL)) {
			tenants.add(t);
		} else if (p.getClass().getSimpleName().equals("House")) {
			tenants.add(t);
		}
	}

	public String displayProperties() {
		String propertiesDisplayed = "";
		for (Property property : this.properties) {
			if (property.isAvailable()) {
				propertiesDisplayed += (property.toString() + "\n");
			}
			if (!property.isAvailable()) {
				propertiesDisplayed += property.displayOccupiedProperty() + "\n";
			}
		}
		return propertiesDisplayed;
	}

	public int numberOfPropertyType(String type) {
		int count = 0;
		for (Property property : this.properties) {
			if (property.getClass().getSimpleName().equalsIgnoreCase(type)) {
				count++;
			}
		}
		return count;
	}

	public void removeProperty(Property p) {
		if (this.properties.contains(p)) {
			properties.remove(p);
		}
	}

	public double percentageCouncilTaxExemption() {
		double exemptCount = 0;
		for (Property property : this.properties) {
			if (isExemptFromCouncilTax()) {
				exemptCount++;
				break;
			}
		}
		return (exemptCount / properties.size()) * 100;
	}

	/**
	 * checks if a property is exempt from council tax.
	 * 
	 * @return true if property if exempt.
	 */
	private boolean isExemptFromCouncilTax() {
		boolean valid = false;
		for (ITenant tenant : tenants) {
			if (tenant.getType().equals(TenantType.STUDENT)) {
				valid = true;
			} else if (tenant.getType().equals(TenantType.PROFESSIONAL)) {
				valid = false;
			}
		}
		return valid;
	}

	public String findProblematicProperty() {
		Property problematicProperty = null;
		for (Property property : this.properties) {
			problematicProperty = property;
			if (property.calculateImpact() > problematicProperty.calculateImpact()) {
				problematicProperty = property;
			}
		}
		return problematicProperty.toString();
	}

	public String displayInfographics() {
		LinkedHashMap<String, Integer> ages = new LinkedHashMap<String, Integer>();
		ages.put("17-25", 0);
		ages.put("26-35", 0);
		ages.put("36-49", 0);
		ages.put("50-60", 0);
		ages.put("60+", 0);
		int noOfPros = 0;
		int noOfStudents = 0;

		String output = "Confirmed Tenant Summary:\n";
		for (ITenant tenant : this.tenants) {
			if (tenant.getType().equals(TenantType.PROFESSIONAL)) {
				noOfPros++;
			} else if (tenant.getType().equals(TenantType.STUDENT)) {
				noOfStudents++;
			}
			String ageRange = getAgeRange(tenant.getAge());
			ages.put(ageRange, ages.get(ageRange) + 1);
		}

		for (Entry<String, Integer> data : ages.entrySet()) {
			output += data.getKey() + ":" + data.getValue() + "\n";
		}

		output += "*****\nProfessional:" + noOfPros + "\nStudent:" + noOfStudents;
		return output;
	}

	private String getAgeRange(int age) {
		if (age >= 17 && age <= 25) {
			return "17-25";
		} else if (age >= 26 && age <= 35) {
			return "26-35";
		} else if (age >= 36 && age <= 49) {
			return "36-49";
		} else if (age >= 50 && age <= 60) {
			return "50-60";
		} else {
			return "60+";
		}
	}

}
