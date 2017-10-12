
//This class creates a Product object
public abstract class Product {

	//data members
	private String productCode;
	private String productType;
	private int productQuantity;

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

}
