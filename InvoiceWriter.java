
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class InvoiceWriter {

	private List<Person> personList;
	private List<Customer> customerList;
	private List<Product> productList;
	private List<Invoice> invoiceList;

	private double Fees = 0.0;
	private double Subtotal = 0.0;
	private double Taxes = 0.0;
	private double Discount = 0.0;
	private double Total = 0.0;
	
	private double FeesTotal = 0.0;
	private double SubtotalsTotal = 0.0;
	private double TaxesTotal = 0.0;
	private double DiscountTotal = 0.0;
	private double OverallTotal = 0.0;

	public InvoiceWriter(List<Person> personList, List<Customer> customerList, List<Product> productList, 
			List<Invoice> invoiceList) {
		this.personList = personList;
		this.customerList = customerList;
		this.productList = productList;
		this.invoiceList = invoiceList;		
	}

/*
	public void writeSummary() {


		//Begin writing the summary of all invoices
		System.out.println(String.format("=========================\n"
				+ "Executive Summary Report\n"
				+ "=========================\n"));

		//Headers for Invoice Summary
		System.out.println(String.format("Invoice \t Customer \t\t Salesperson \t\t Subtotal \t\t Fees \t\t Taxes \t\t Discount \t\t Total"));

		//Find the corresponding customer, person, and products given the respective codes

		Invoice anInvoice = null;
		for (int x=0; x < invoiceList.size(); x++) {
			
			Customer aCustomer = null;
			for(int y=0; y < customerList.size(); y++) {
				Customer customer = null;
				if(customerList.get(y).getCustomerCode().equals(invoiceList.get(x).getCustomerCode())){
					aCustomer = customerList.get(x);
					aCustomer = customer;
				}
			}
			
			Person aPerson = null;
			for(int j=0; j < personList.size(); j++) {
				Person person = null;
				if(personList.get(j).getPersonCode().equals(invoiceList.get(x).getPersonCode())){
					aPerson = personList.get(x);
					aPerson = person;
				}
			}
			
			if (aCustomer.getType = "S") {
				Fees = 6.75;
			}
			else {
				Fees = 0.0;
			}
			
			
			
			System.out.println(String.format("%s \t %s \t\t %s\n", invoiceList.get(x).getInvoiceCode(), aCustomer.getName(), aPerson.getName()));	

			//Add all monetary values to totals
			FeesTotal += Fees;
			SubtotalsTotal += Subtotal;
			TaxesTotal += Taxes;
			DiscountTotal += Discount;
			OverallTotal += Total;
			
		}		
		
		System.out.println(String.format("===================================================================================="));
		System.out.println(String.format("TOTALS \t\t\t\t\t\t\t\t\t %.2f, \t\t %.2f \t\t %.2f \t\t %.2f \t\t %.2f", SubtotalsTotal, FeesTotal, TaxesTotal, DiscountTotal, OverallTotal));


	}
*/

	public void writeReport() throws FileNotFoundException {		
		
		PrintWriter writer = new PrintWriter("InvoiceReport.txt");
		
		//Begin writing the detailed report of each invoice
		System.out.println(String.format("Individual Invoice Detail Reports\n"
									+ "====================================\n"));
				
		Invoice anInvoice = null;
		for (int x=0; x < invoiceList.size(); x++) {
			anInvoice = invoiceList.get(x);
			
			Customer aCustomer = null;
			for(int y=0; y < customerList.size(); y++) {
				Customer customer = null;
				if(customerList.get(y).getCustomerCode().equals(invoiceList.get(x).getCustomerCode())){
					aCustomer = customerList.get(x);
				}
			}
			
			Person aPerson = null;
			for(int j=0; j < personList.size(); j++) {
				Person person = null;
				if(personList.get(j).getPersonCode().equals(invoiceList.get(x).getPersonCode())){
					aPerson = personList.get(x);
				}
			}



			System.out.println(String.format("Invoice %s\n"
					+ "======================\n", anInvoice.getInvoiceCode()));

			
			
			Address anAddress = aCustomer.getAddress();
			//Display information regarding the salesperson and the customer
			System.out.println(String.format("Salesperson: %s \n"
					+ "Customer Info:\n"
					+ "\t %s (%s)\n"
					+ "\t [%s] \n", aPerson.getName(), aCustomer.getName(), aCustomer.getCustomerCode(), aCustomer.getType()));
			System.out.println(String.format("\t %s %s %s %s %s",  anAddress.getStreet(), anAddress.getCity(), anAddress.getState(), anAddress.getZip(), anAddress.getCountry()));
			System.out.println(String.format("-------------------------"));


			//Display the details of all products for this invoice
			
			System.out.println(String.format("Code \t\t Item \t\t SubTotal \t\t Tax \t\t Total\n"));	
			
			for(Product aProduct : anInvoice.getIProductList()){
				for(int q=0; q < productList.size(); q++) {
					Product person = null;
					if(personList.get(q).getPersonCode().equals(aProduct.getProductCode())){
						aProduct = productList.get(q);
				}
			}

			
			if (aProduct.getProductType().equals("M")) {
				System.out.println(String.format("%s \t\t %s \t\t %f \t\t $f \t\t $f\n", aProduct.getProductCode()));
			}
			if (aProduct.getProductType().equals("P")) {
				System.out.println(String.format("%s \t\t %s \t\t %f \t\t $f \t\t $f\n", aProduct.getProductCode()));
			}
			if (aProduct.getProductType().equals("R")) {
				
			}
			if (aProduct.getProductType().equals("S")) {
				
			}
			
			}
			
			//Print Total Costs for Invoice
			System.out.println(String.format("SUB-TOTALS \t\t %.2f \t\t %.2f \t\t %.2f\n", SubtotalsTotal, TaxesTotal, OverallTotal));
			System.out.println(String.format("TOTAL \t\t\t\t\t\t %.2f\n\n", OverallTotal));
			
			System.out.println(String.format("Thank you for your purchase!\n\n\n"));
		}
		
		writer.close();
		}

		System.out.println(String.format("====================================================="));


}

