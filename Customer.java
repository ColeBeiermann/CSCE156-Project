//This class creates a customer object
public class Customer{

	//Data Members
	private String customerCode;
	private String type;
	private Person contact;
	private String name;
	private Address address; 

	//Constructor
	public Customer(String customerCode, String type, Person contact, String name, Address address) {
		this.customerCode = customerCode;
		this.type = type;
		this.contact = contact;		
		this.name = name;
		this.address = address;
	}

	//Getters and Setters
	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public Person getContact() {
		return contact;
	}

	public void setContact(Person contact) {
		this.contact = contact;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}
	public String getTypeStr() {
		if(this.type.equals("S")){
			return "Student";
		}
		else if(this.type.equals("G")) {
			return "General";
		}
		return null;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public double getFees() {
		if (this.type.equals("S")) {
			return 6.75;
		}
		else {
			return 0.0;
		}
	}

	public double getDisount(double subtotal, double taxes) {
		if (this.type.equals("S")) {
			double discount = -(.08*subtotal) - taxes;
			return discount;
		}
		else {
			return 0.0;
		}
	}
	

}
