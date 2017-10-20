//This class creates a SeasonPass object of the type Product
public class SeasonPass extends Product {

	//Class members
	private String name;
	private String startDate;
	private String endDate;
	private double cost;
	private double fee = 8;
	
	//Constructor
	public SeasonPass(String productCode, String productType, String name, String startDate, String endDate, double cost) {
		super(productCode, productType);
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.cost = cost;
	}
	
	//Getters and Setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	@Override
	public double getSubtotal() {
		double subtotal = (this.cost + fee) * productQuantity;
		return subtotal;
	}
	
	@Override
	public double getTaxes() {
		double taxes = ((this.cost + fee) * productQuantity) * 0.06;
		return taxes;
	}

	@Override
	public String getTypeStr() {
		return "Season Pass";
	}
	

}
