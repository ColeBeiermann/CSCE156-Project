import java.io.FileNotFoundException;
import java.util.List;

public class InvoiceReport {

	//	public static void main(String[] args) throws FileNotFoundException {
	//
	//		//Create a FlatFileReader Object to read from data files
	//		FlatFileReader fr = new FlatFileReader();
	//
	//		//Read from the data files and creates an ArrayList of objects
	//
	//		List<Person> personList = fr.readPersons();		
	//
	//		List<Customer> customerList = fr.readCustomers();		
	//
	//		List<Product> productList = fr.readProducts();
	//
	//		List<Invoice> invoiceList = fr.readInvoices();
	//
	//
	//		//TO DO
	//		//Create Output Writer for Invoice Data
	//		InvoiceWriter writer = new InvoiceWriter(personList, customerList, productList, invoiceList);
	//		
	//		writer.writeSummary();
	//	}

	public static void main(String[] args) throws FileNotFoundException {

		//read from database
		DatabaseReader dbr = new DatabaseReader();
		dbr.connectMeIn();
		List<Person> personList = dbr.readPersons();		

		List<Customer> customerList = dbr.readCustomers();		

		List<Product> productList = dbr.readProducts();

		List<Invoice> invoiceList = dbr.readInvoices();

		InvoiceWriter writer = new InvoiceWriter(personList, customerList, productList, invoiceList);

		writer.writeSummary();

		//InvoiceList invoiceOrderedList = new InvoiceList(new TotalComparator());

		dbr.closeConnection();
	}

}
