/**
 * 
 */
package Question_3;



import java.util.Arrays;
import java.util.List;

/**
 * 
 */
public class Test {

	/**	
	 * @param args
	 */
	public static void main(String[] args) {
		PropertyManagement pm = new PropertyManagement();
		Property house = new House(1, "Percy Road", "Guildford", "GU2 7XH", 2);

		Room room1 = new Room(900);
		Room room2 = new Room(1000);

		Tenant tenant1 = new Tenant("Alice", "Wonderland", 18, TenantType.STUDENT);
		Tenant tenant2 = new Tenant("Cheshire", "Cat", 20, TenantType.STUDENT);

		pm.addProperty(house);
		pm.addTenant(house, room1, tenant1);
		pm.addTenant(house, room2, tenant2);

		Property flat = new Flat(1, "The Chase", "Guildford", "GU2 7UB", 2, 2);

		Room room3 = new Room(1000);

		Tenant tenant3 = new Tenant("Humpty", "Dumpty", 52, TenantType.STUDENT);

		pm.addProperty(flat);
		pm.addTenant(flat, room3, tenant3);

	}

}
