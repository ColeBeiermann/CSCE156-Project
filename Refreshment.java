
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
		
		//this.productType = "Refreshment";
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
	
	/*
	@Override
	public String getProductType() {
		return productType;
	}
	*/
	
	//@Override
	public double getSubtotal() {
		double subtotal = this.cost * productQuantity;
		return subtotal;
	}
	
	//@Override
	public double getTaxes() {
		double subtotal = this.cost * productQuantity;
		double taxes = subtotal * 0.04;
		return taxes;
	}
}
