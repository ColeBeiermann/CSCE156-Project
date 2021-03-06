
//This class creates a Product object
public abstract class Product {

	//data members
	private String productCode;
	private String productType;
	int productQuantity;
	double subtotal;
	double taxes;

	//Constructor
	public Product(String productCode, String productType) {
		this.productType = productType;
		this.productCode = productCode;
	}
	
	//Getters and Setters
	public String getProductCode() {
		return productCode;
	}

	public String getProductType() {
		return productType;
	}

	public int getProductQuantity() {
		return productQuantity;
	}
	
	public void setQuantity(int quantity) {
		this.productQuantity = quantity;
	}
	
	public abstract String getTypeStr();	
	
	public abstract double getSubtotal();
		
	public abstract double getTaxes();

}
