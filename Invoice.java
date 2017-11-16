import java.util.ArrayList;

//This class creates an invoice object
public class Invoice implements Comparable <Invoice>{

	//Data Members
	private String invoiceCode;
	private String customerCode;
	private String personCode;
	private String invoiceDate;
	private ArrayList<Product> productList;

	//Constructor
	public Invoice(String invoiceCode, String customerCode, String personCode, String invoiceDate, ArrayList<Product> productList) {
		this.invoiceCode = invoiceCode;
		this.customerCode = customerCode;
		this.personCode = personCode;		
		this.invoiceDate = invoiceDate;
		this.setProductList(productList);
	}


	//Getters and Setters
	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public ArrayList<Product> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<Product> productList) {
		this.productList = productList;
	}

	@Override
	public int compareTo(Invoice o) {
		
		return 0;
	}
}
