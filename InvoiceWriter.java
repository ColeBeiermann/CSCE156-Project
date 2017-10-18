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
			
			//////////////////////////////////////////////////////
			
			
			
			double Fees = 0.0;
			double Subtotals = 0.0;
			double Taxes = 0.0;
			double Discount = 0.0;
			double Overall = 0.0;
			
			
			for (Product aProduct : invoiceList.get(x).getProductList()) {		
				String type = aProduct.getProductType();
				
				int movieTrue = 0;
				for (Product bProduct : invoiceList.get(x).getProductList()) {
					if (bProduct.getProductType().equals("M")){
						movieTrue = 1;
					}
				}
				
				
				if(type.equals("P")) {
					ParkingPass pass = (ParkingPass) aProduct;
					Subtotals += pass.getSubtotal();
					Taxes += pass.getTaxes(pass.getSubtotal());
				}
				else if(type.equals("M")) {
					MovieTicket ticket = (MovieTicket) aProduct;
					Subtotals += ticket.getSubtotal();
					Taxes += ticket.getTaxes(ticket.getSubtotal());
				}
				else if(type.equals("R")) {
					Refreshment refresh = (Refreshment) aProduct;
					double refreshmentDiscount = 0.0;
					if (movieTrue == 1) {
					refreshmentDiscount = 0.5;
					}
					Subtotals += refresh.getSubtotal(refreshmentDiscount);
					Taxes += refresh.getTaxes(refresh.getSubtotal(refreshmentDiscount));
				}
				else if(type.equals("S")) {
					SeasonPass pass = (SeasonPass) aProduct;
					Subtotals += pass.getSubtotal(8.0);
					Taxes += pass.getTaxes(pass.getSubtotal(8.0));
				}
			}
			
			
			Fees = aCustomer.getFees();
			Discount = aCustomer.getDisount(Subtotals, Taxes);
			Overall = Subtotals + Fees + Taxes + Discount;
				
			//////////////////////////////////////////////////////
			
			
			
			

			System.out.println(String.format("%-8s %-28s (%s)%-4s %-24s $%10.2f\t $%10.2f\t $%10.2f\t $%10.2f\t $%10.2f\n",
					invoiceList.get(x).getInvoiceCode(), aCustomer.getName(), aCustomer.getType(), "",aPerson.getName(), Subtotals, Fees, Taxes, Discount, Overall));	

			//Add all monetary values to totals
			FeesTotal += Fees;
			SubtotalsTotal += Subtotals;
			TaxesTotal += Taxes;
			DiscountTotal += Discount;
			OverallTotal += Overall;

		}		

		System.out.println(String.format("====================================================================================================================================================="));
		System.out.println(String.format("%-70s $%10.2f\t $%10.2f\t $%10.2f\t $%10.2f\t $%10.2f\n\n", "TOTAL", SubtotalsTotal, FeesTotal, TaxesTotal, DiscountTotal, OverallTotal));	

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
				
				double subtotal = 0.0;
				double taxes = 0.0;
				double total = 0.0;
				
				//Depending on type, creates the corresponding object of type Product
				if(type.equals("P")) {
					ParkingPass pass = (ParkingPass) aProduct;
					subtotal = pass.getSubtotal();
					taxes += pass.getTaxes(pass.getSubtotal());
					total = subtotal + taxes;
					
					System.out.println(String.format("%-8s %s %s (%d units @ $%.2f/unit)\t\t $%10.2f\t $%10.2f\t $%10.2f\n", 
							pass.getProductCode(), pass.getProductType(),pass.getTicketCode(),pass.getProductQuantity(),pass.getParkingFee(), subtotal, taxes, total));
					
				}
				else if(type.equals("M")) {
					MovieTicket ticket = (MovieTicket) aProduct;
					subtotal = ticket.getSubtotal();
					taxes += ticket.getTaxes(ticket.getSubtotal());
					total = subtotal + taxes;
					
					System.out.println(String.format("%-8s %s '%s' @ %-24s \t\t $%10.2f\t $%10.2f\t $%10.2f", 
							ticket.getProductCode(),ticket.getProductType(),ticket.getMovieName(),ticket.getAddress(), subtotal, taxes, total));
					System.out.println(String.format("%-8s %s (%d units @ $%.2f/unit)\n", "", ticket.getDateTime(), ticket.getProductQuantity(), ticket.getPricePerUnit()));
					

				}
				else if(type.equals("R")) {
					int movieTrue = 0;
					double refreshmentDiscount = 0.0;
					for (Product bProduct : invoiceList.get(x).getProductList()) {
						if (bProduct.getProductType().equals("M")){
							movieTrue = 1;
							refreshmentDiscount = .05;
						}
					}
					
					Refreshment refresh = (Refreshment) aProduct;
					subtotal = refresh.getSubtotal(refreshmentDiscount);
					taxes += refresh.getTaxes(refresh.getSubtotal(refreshmentDiscount));
					total = subtotal + taxes;
					
					if (movieTrue == 1) {
					System.out.println(String.format("%-8s %s (%d units @ $%.2f/unit %-11s) \t\t $%10.2f\t $%10.2f\t $%10.2f\n", 
						refresh.getProductCode(),refresh.getName(), refresh.getProductQuantity(),refresh.getCost(), "with 5% off", subtotal, taxes, total));
						}
					else {
					System.out.println(String.format("%-8s %s (%d units @ $%.2f/unit) \t\t $%10.2f\t $%10.2f\t $%10.2f\n", 
							refresh.getProductCode(),refresh.getName(), refresh.getProductQuantity(),refresh.getCost(), subtotal, taxes, total));
					}
					
				}
				else if(type.equals("S")) {
					SeasonPass pass = (SeasonPass) aProduct;
					subtotal = pass.getSubtotal(8.0);
					taxes += pass.getTaxes(pass.getSubtotal(8.0));
					total = subtotal + taxes;
					
					System.out.println(String.format("%-8s %s %-30s $%10.2f\t $%10.2f\t $%10.2f", 
							pass.getProductCode(),pass.getProductType(),pass.getName(), subtotal, taxes, total));
					System.out.println(String.format("%-8s (%d units @ $%.2f/unit + $8 fee/unit)\n", "", pass.getProductQuantity(), pass.getCost()));
					
				}
				
				SubtotalsTotal += subtotal;
				TaxesTotal += taxes;
				OverallTotal += total;
				
				
			}
			System.out.println(String.format("%-60s $%10.2f \t $%10.2f \t $%10.2f", "SUB-TOTALS", SubtotalsTotal, TaxesTotal, OverallTotal));
			if (aCustomer.getType().equals("S")) {
				double discount = -((.08 * SubtotalsTotal) - TaxesTotal);
				System.out.println(String.format("%-80s $%10.2f", "DISCOUNT (8% STUDENT & NO TAX)", discount));
				double fee = 6.75;
				System.out.println(String.format("%-80s $%10.2f", "ADDITIONAL FEE (Student)", fee));
				OverallTotal += (fee - discount);
			}
			System.out.println(String.format("%-80s $%10.2f\n\n", "TOTAL", OverallTotal));

			System.out.println(String.format("Thank you for your purchase!\n\n\n"));


			System.out.println(String.format("====================================================="));

		}

	}
}



