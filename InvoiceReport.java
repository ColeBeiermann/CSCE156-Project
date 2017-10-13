import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class InvoiceReport {
	
	public static void main(String[] args) {
		
		
		FlatFileReader fr = new FlatFileReader();
		List<Invoice> invoiceList = fr.readInvoices();
		
		List<Person> personList = fr.readPersons();		
		List<Customer> customerList = fr.readCustomers();		
		List<Product> productList = fr.readProducts();	
		
		
		
		File InvoiceOutput = new File("data/InvoiceOutput.txt");
		
		PrintWriter InvoiceTxtWriter = null;
		try {
			InvoiceTxtWriter = new PrintWriter(InvoiceOutput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		StringBuilder sb = new StringBuilder();
		
		//Keep track of overall totals
		Double FeesTotal = 0.0;
		Double SubtotalsTotal = 0.0;
		Double TaxesTotal = 0.0;
		Double DiscountTotal = 0.0;
		Double OverallTotal = 0.0;
		
		//Begin writing the summary of all invoices
		sb.append(String.format("=========================\n"
							+ "Executive Summary Report\n"
							+ "=========================\n"));
		
		//Headers for Invoice Summary
		sb.append(String.format("Invoice \t Customer \t\t Salesperson \t\t Subtotal \t\t Fees \t\t Taxes \t\t Discount \t\t Total"));
		
		//Find the corresponding customer, person, and products given the respective codes
		for(Invoice anInvoice : invoiceList) {
			Customer aCustomer = null;
			for(Customer customer : customerList) {
				if (customer.getCustomerCode() = anInvoice.getCustomerCode()) {
				aCustomer = customer; }
				}
				
			for(Person person : personList) {
				String personCode = person.getPersonCode();
				if (personCode = anInvoice.getCustomerCode()) {
					aCustomer = customer; }
				}
				
			for(Customer customer : customerList) {
				if (customer.getCustomerCode() = anInvoice.getCustomerCode()) {
				aCustomer = customer; }
			}
			
			sb.append(String.format("%-7s,\n", anInvoice.getInvoiceCode()));	
			
			//Add all monetary values to totals
			FeesTotal += 0.0;
			SubtotalsTotal += 0.0;
			TaxesTotal += 0.0;
			DiscountTotal += 0.0;
			OverallTotal += 0.0;
			
		}
		sb.append(String.format("===================================================================================="));
		sb.append(String.format("TOTALS \t\t\t\t\t\t\t\t\t %.2f, \t\t %.2f \t\t %.2f \t\t %.2f \t\t %.2f", SubtotalsTotal, FeesTotal, TaxesTotal, DiscountTotal, OverallTotal));
		
		
		////////////////////////////////////////////////////////////////////////////////////
		
		//Begin writing the detailed report of each invoice
		sb.append(String.format("Individual Invoice Detail Reports\n"
						+ "====================================\n"));
		
		//For loop to go through each invoice
		for(Invoice anInvoice : invoiceList) {
			
			sb.append(String.format("Inovice %-7s\n"
										+ "======================\n", anInvoice.getInvoiceCode()));
		
			//Display information regarding the salesperson and the customer
			sb.append(String.format("Salesperson: %s\n"
								+ "Customer Info:\n"
								+ "\t %s (%s)\n"
								+ "\t [%s] \n"
								+ "\t %s \n"
								+ "\t %s \n"
								+ "\t %s %s %s %s %s"));
			sb.append(String.format("-------------------------"));
			
			
			//Display the details of all products for this invoice
			sb.append(String.format("Code \t\t Item \t\t SubTotal \t\t Tax \t\t Total\n"));	
			for (Product aProduct : anInvoice.getProductList()) {
				
				sb.append(String.format("%s \t\t %s \t\t SubTotal \t\t Tax \t\t Total\n"));
				
			}
			
			sb.append(String.format("Thank you for your purchase!\n\n\n"));
		}
		
		
		sb.append(String.format("====================================================="));
		
		//Print everything to our output file
		System.out.println(sb);
	}

}