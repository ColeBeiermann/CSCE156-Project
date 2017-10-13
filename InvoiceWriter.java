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


		//Begin writing the summary of all invoices
		System.out.println(String.format("=========================\n"
				+ "Executive Summary Report\n"
				+ "=========================\n"));

		//Headers for Invoice Summary
		System.out.println(String.format("Invoice \t Customer \t\t Salesperson \t\t Subtotal \t\t Fees \t\t Taxes \t\t Discount \t\t Total"));

		//Find the corresponding customer, person, and products given the respective codes

		for (int x=0; x < invoiceList.size(); x++) {

			Customer aCustomer = null;
			for(int y=0; y < customerList.size(); y++) {

				if(customerList.get(y).getCustomerCode().equals(invoiceList.get(x).getCustomerCode())){
					aCustomer = customerList.get(y);
				}
			}

			Person aPerson = null;
			for(int j=0; j < personList.size(); j++) {
				if(personList.get(j).getPersonCode().equals(invoiceList.get(x).getPersonCode())){
					aPerson = personList.get(j);
				}
			}

			System.out.println(String.format("%s, \t %s(%s) \t %s \n", invoiceList.get(x).getInvoiceCode(), aCustomer.getName(), aCustomer.getType(), aPerson.getName()));	

			//Add all monetary values to totals
			FeesTotal += 0.0;
			SubtotalsTotal += 0.0;
			TaxesTotal += 0.0;
			DiscountTotal += 0.0;
			OverallTotal += 0.0;

		}		

		System.out.println(String.format("===================================================================================="));
		System.out.println(String.format("TOTALS \t\t\t\t\t\t\t\t\t %.2f, \t\t %.2f \t\t %.2f \t\t %.2f \t\t %.2f", SubtotalsTotal, FeesTotal, TaxesTotal, DiscountTotal, OverallTotal));


	}

	public void writeReport() {		

		//Begin writing the detailed report of each invoice
		System.out.println(String.format("Individual Invoice Detail Reports\n"
				+ "====================================\n"));

		//For loop to go through each invoice
		for (int x=0; x < invoiceList.size(); x++) {

			Customer aCustomer = null;

			for(int y=0; y < customerList.size(); y++) {

				if(customerList.get(y).getCustomerCode().equals(invoiceList.get(x).getCustomerCode())){
					aCustomer = customerList.get(y);
				}
			}

			Person aPerson = null;
			for(int j=0; j < personList.size(); j++) {
				if(personList.get(j).getPersonCode().equals(invoiceList.get(x).getPersonCode())){
					aPerson = personList.get(j);
				}
			}

			System.out.println(String.format("Invoice %-7s\n"
					+ "======================\n", invoiceList.get(x).getInvoiceCode()));

			//Display information regarding the salesperson and the customer
			Address address = aCustomer.getAddress();

			System.out.println(String.format("Salesperson: %s \n"
					+ "Customer Info:\n"
					+ "\t %s (%s)\n"
					+ "\t [%s] \n"
					+ "\t %s \n", aPerson.getName(),aCustomer.getName(),aCustomer.getCustomerCode(), aCustomer.getType(), aCustomer.getContact().getName()));
			System.out.println(String.format("\t %s %s %s %s %s",address.getStreet(),address.getCity(),address.getState(),address.getZip(),address.getCountry()));
			System.out.println(String.format("-------------------------"));


			//Display the details of all products for this invoice
			System.out.println(String.format("Code \t\t Item \t\t SubTotal \t\t Tax \t\t Total\n"));

			for (Product aProduct : invoiceList.get(x).getProductList()) {		
				String type = aProduct.getProductType();
				//Depending on type, creates the corresponding object of type Product
				if(type.equals("P")) {
					ParkingPass pass = (ParkingPass) aProduct;
					System.out.println(String.format("%s \t\t %s %s (%d units @ $%.2f/unit)\t\t SubTotal \t\t Tax \t\t Total\n", pass.getProductCode(), pass.getProductType(),pass.getTicketCode(),pass.getProductQuantity(),pass.getParkingFee()));
				}
				else if(type.equals("M")) {
					MovieTicket ticket = (MovieTicket) aProduct;
					System.out.println(String.format("%s \t\t %s %s @ %s \n %s (%d @ $%.2f/unit) \t\t SubTotal \t\t Tax \t\t Total\n", ticket.getProductCode(),ticket.getProductType(),ticket.getMovieName(),ticket.getAddress(), ticket.getDateTime(),ticket.getProductQuantity(),ticket.getPricePerUnit()));
				}
				else if(type.equals("R")) {
					Refreshment refresh = (Refreshment) aProduct;
					System.out.println(String.format("%s \t\t %s (%d units @ $%.2f/unit) \t\t SubTotal \t\t Tax \t\t Total\n", refresh.getProductCode(),refresh.getName(), refresh.getProductQuantity(),refresh.getCost()));
				}
				else if(type.equals("S")) {
					SeasonPass pass = (SeasonPass) aProduct;
					System.out.println(String.format("%s \t\t %s %s \n (%d units @ $%.2f/unit) \t\t SubTotal \t\t Tax \t\t Total\n", pass.getProductCode(),pass.getProductType(),pass.getName(),pass.getProductQuantity(),pass.getCost()));

				}
				
			}
			System.out.println(String.format("TOTAL \t\t\t\t\t\t $%.2f\n\n", OverallTotal));

			System.out.println(String.format("Thank you for your purchase!\n\n\n"));


			System.out.println(String.format("====================================================="));

		}

	}
}



