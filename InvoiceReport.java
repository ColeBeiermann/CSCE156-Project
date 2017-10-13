import java.util.List;

public class InvoiceReport {
	
	public static void main(String[] args) {
		
		//Create a FlatFileReader Object to read from data files
		FlatFileReader fr = new FlatFileReader();
		
		//Read from the data files and creates an ArrayList of objects
		
		List<Person> personList = fr.readPersons();		
		
		List<Customer> customerList = fr.readCustomers();		
		
		List<Product> productList = fr.readProducts();
		
		List<Invoice> invoiceList = fr.readInvoices();
		
		
		//TO DO
	     //Create Output Writer for Invoice Data
	}

}
