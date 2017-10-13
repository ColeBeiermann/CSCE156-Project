import java.util.ArrayList;
import java.util.List;

public class InvoiceWriter {

	private List<Person> personList;
	private List<Customer> customerList;
	private List<Product> productList;
	private List<Invoice> invoiceList;

	private double FeesTotal;
	private double SubtotalsTotal;
	private double TaxesTotal;
	private double DiscountTotal;
	private double OverallTotal;

	public InvoiceWriter(List<Person> personList, List<Customer> customerList, List<Product> productList, 
			List<Invoice> invoiceList) {
		this.personList = personList;
		this.customerList = customerList;
		this.productList = productList;
		this.invoiceList = invoiceList;		
	}

	public void writeSummary() {
		StringBuilder sb = new StringBuilder();


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
				if (customer.getCustomerCode().equals(anInvoice.getCustomerCode())) {
					aCustomer = customer; }
			}

			Person aPerson = null;
			for(Person person : personList) {
				String personCode = person.getPersonCode();
				if (personCode.equals(anInvoice.getPersonCode())) {
					aPerson = person; }
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

		System.out.println(sb);

	}

	public void writeReport() {		
		StringBuilder sb = new StringBuilder();

		//Begin writing the detailed report of each invoice
		sb.append(String.format("Individual Invoice Detail Reports\n"
				+ "====================================\n"));

		//For loop to go through each invoice
		for(Invoice anInvoice : invoiceList) {

			sb.append(String.format("Inovice %-7s\n"
					+ "======================\n", anInvoice.getInvoiceCode()));

			//Display information regarding the salesperson and the customer
			sb.append(String.format("Salesperson: %s \n"
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

		//Print everything to output
		System.out.println(sb);
	}



}


