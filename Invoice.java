//This class creates an invoice object
public class Invoice {

	//Data Members
	private String invoiceCode;
	private String customerCode;
	private String personCode;
	private String invoiceDate;
	private Product product;
	
	//Constructor
	public Invoice(String invoiceCode, String customerCode, String personCode, String invoiceDate, Product product) {
		this.invoiceCode = invoiceCode;
		this.customerCode = customerCode;
		this.personCode = personCode;		
		this.invoiceDate = invoiceDate;
		this.product = product;
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
		return customerCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}
	
	public String getInvoiceDate() {
		return customerCode;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	
}