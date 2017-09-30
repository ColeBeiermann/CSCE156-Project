
//Creates a Refreshment object of the type Product
public class Refreshment extends Product{

	//data members
	private String name;
	private double cost;
	
	//constructor
	public Refreshment(String productCode, String productType, String name, double cost) {
		super(productCode, productType);
		this.name = name;
		this.cost = cost;
	}

	//getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

}
