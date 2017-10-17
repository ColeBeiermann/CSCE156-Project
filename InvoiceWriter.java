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
		System.out.println(String.format("%-8s %-36s %-24s %-16s %-16s %-16s %-16s %-16s", "Invoice", "Customer", "Salesperson", "Subtotal", "Fees", "Taxes", "Discount", "Total"));

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

			System.out.println(String.format("%-8s %-28s (%s)%-4s %-24s $%10.2f\t $%10.2f\t $%10.2f\t $%10.2f\t $%10.2f\n", invoiceList.get(x).getInvoiceCode(), aCustomer.getName(), aCustomer.getType(), "",aPerson.getName(), 0.00, 0.00, 0.00, 0.00, 0.00));	

			//Add all monetary values to totals
			FeesTotal += 0.0;
			SubtotalsTotal += 0.0;
			TaxesTotal += 0.0;
			DiscountTotal += 0.0;
			OverallTotal += 0.0;

		}		

		System.out.println(String.format("====================================================================================================================================================="));
		System.out.println(String.format("%-70s $%10.2f\t $%10.2f\t $%10.2f\t $%10.2f\t $%10.2f\n", "TOTAL", 0.00, 0.00, 0.00, 0.00, 0.00));	

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
					+ "\t %s", aPerson.getName(),aCustomer.getName(),aCustomer.getCustomerCode(), aCustomer.getType(), aCustomer.getContact().getName()));
			System.out.println(String.format("\t %s %s %s %s %s",address.getStreet(),address.getCity(),address.getState(),address.getZip(),address.getCountry()));
			System.out.println(String.format("--------------------------------------------------------"));


			//Display the details of all products for this invoice
			System.out.println(String.format("%-8s %-60s %-16s %-16s %-16s", "Code", "Item", "Subtotal", "Tax", "Total"));

			for (Product aProduct : invoiceList.get(x).getProductList()) {		
				String type = aProduct.getProductType();
				//Depending on type, creates the corresponding object of type Product
				if(type.equals("P")) {
					ParkingPass pass = (ParkingPass) aProduct;
					System.out.println(String.format("%-8s %s %s (%d units @ $%.2f/unit)\t\t $%10.2f\t $%10.2f\t $%10.2f\n", 
							pass.getProductCode(), pass.getProductType(),pass.getTicketCode(),pass.getProductQuantity(),pass.getParkingFee(), pass.getSubtotal(), pass.getTaxes(), 0.00));
					//OverallTotal = OverallTotal + pass.getTotal();
				}
				else if(type.equals("M")) {
					MovieTicket ticket = (MovieTicket) aProduct;
					System.out.println(String.format("%-8s %s '%s' @ %-24s \t\t $%10.2f\t $%10.2f\t $%10.2f", 
							ticket.getProductCode(),ticket.getProductType(),ticket.getMovieName(),ticket.getAddress(), ticket.getSubtotal(), ticket.getTaxes(), 0.00));
					System.out.println(String.format("%-8s %s (%d units @ $%.2f/unit)\n", "", ticket.getDateTime(), ticket.getProductQuantity(), ticket.getPricePerUnit()));
					//OverallTotal = OverallTotal + ticket.getTotal();
				}
				else if(type.equals("R")) {
					Refreshment refresh = (Refreshment) aProduct;
					System.out.println(String.format("%-8s %s (%d units @ $%.2f/unit) \t\t $%10.2f\t $%10.2f\t $%10.2f\n", 
							refresh.getProductCode(),refresh.getName(), refresh.getProductQuantity(),refresh.getCost(), refresh.getSubtotal(), refresh.getTaxes(), 0.00));
					//OverallTotal = OverallTotal + refresh.getTotal();
				}
				else if(type.equals("S")) {
					SeasonPass pass = (SeasonPass) aProduct;
					System.out.println(String.format("%-8s %s %-30s $%10.2f\t $%10.2f\t $%10.2f", 
							pass.getProductCode(),pass.getProductType(),pass.getName(), pass.getSubtotal(), pass.getTaxes(), 0.00));
					System.out.println(String.format("%-8s (%d units @ $%.2f/unit)\n", "", pass.getProductQuantity(), pass.getCost()));
					//OverallTotal = OverallTotal + pass.getTotal();
				}
				
			}
			System.out.println(String.format("%-80s $%10.2f\n\n", "TOTAL", 0.00));

			System.out.println(String.format("Thank you for your purchase!\n\n\n"));


			System.out.println(String.format("====================================================="));

		}

	}
}



