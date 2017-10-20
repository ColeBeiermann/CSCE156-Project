
//Creates a Refreshment object of the type Product
public class Refreshment extends Product{

	//data members
	private String name;
	private double cost;
	private double discount;
	private double taxes;
	
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
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	@Override
	public double getSubtotal() {
		double subtotal = (this.cost * productQuantity) * (1.00 - discount);
		return subtotal;
	}
	
	@Override
	public double getTaxes() {
		taxes = (this.cost * productQuantity) * (1.00 - discount) * 0.04;
		return taxes;
	}

	@Override
	public String getTypeStr() {
		return "Refreshment";
	}
}
