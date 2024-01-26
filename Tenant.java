package Question_3;

public class Tenant implements ITenant {
	private String forename = "";
	private String surname = "";
	private int age = 0;
	private TenantType type;

	public Tenant(String forename, String surname, int age,
			TenantType type) {
		if (forename != "") {
			this.forename = forename;
		} else {
			throw new NullPointerException("First name can't"
					+ " be null");
		}
		if (surname != "") {
			this.surname = surname;
		} else {
			throw new NullPointerException("Last name can't"
					+ " be null");
		}
		if (age >= 17) {
			this.age = age;
		} else {
			throw new IllegalArgumentException("Tenant has to be"
					+ " at least 17 years old");
		}
		if (type == TenantType.PROFESSIONAL
			||	type == TenantType.STUDENT) {
			this.type = type;
		} else {
			throw new IllegalArgumentException(
					"Tenant has to be either"
					+ " a student or a professional");
		}
	}

	@Override
	public int getAge() {
		return this.age;
	}

	@Override
	public String getName() {
		return this.forename + "\s" + this.surname;
	}

	@Override
	public TenantType getType() {
		return this.type;
	}
}
