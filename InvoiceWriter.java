import java.io.FileNotFoundException;
import java.util.List;

public class InvoiceWriter {

	private List<Person> personList;
	private List<Customer> customerList;
	private List<Product> productList;
	private List<Invoice> invoiceList;

	public InvoiceWriter(List<Person> personList, List<Customer> customerList, List<Product> productList, 
			List<Invoice> invoiceList) {
		this.personList = personList;
		this.customerList = customerList;
		this.productList = productList;
		this.invoiceList = invoiceList;		
	}

	public void writeSummary() throws FileNotFoundException {

		double FeesTotal = 0.0;
		double SubtotalsTotal = 0.0;
		double TaxesTotal = 0.0;
		double DiscountTotal = 0.0;
		double OverallTotal = 0.0;

		//Begin writing the summary of all invoices
		System.out.println(String.format("=========================\n"
				+ "Executive Summary Report\n"
				+ "=========================\n"));

		//Headers for Invoice Summary
		System.out.println(String.format("%-8s %-36s %-25s %-23s %-14s %-14s %-14s %-16s", "Invoice", "Customer", "Salesperson", "Subtotal", "Fees", "Taxes", "Discount", "Total"));

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
					Taxes += pass.getTaxes();
				}
				else if(type.equals("M")) {
					MovieTicket ticket = (MovieTicket) aProduct;
					Subtotals += ticket.getSubtotal();
					Taxes += ticket.getTaxes();
				}
				else if(type.equals("R")) {
					Refreshment refresh = (Refreshment) aProduct;
					refresh.setDiscount(0.0);
					if (movieTrue == 1) {
						refresh.setDiscount(0.5);
					}
					Subtotals += refresh.getSubtotal();
					Taxes += refresh.getTaxes();
				}
				else if(type.equals("S")) {
					SeasonPass pass = (SeasonPass) aProduct;
					Subtotals += pass.getSubtotal();
					Taxes += pass.getTaxes();
				}
			}


			Fees = aCustomer.getFees();
			Discount = aCustomer.getDisount(Subtotals, Taxes);
			Overall = Subtotals + Fees + Taxes + Discount;

			//////////////////////////////////////////////////////





			System.out.printf("%-8s %-36s %-22s \t $%10.2f\t $%10.2f\t $%10.2f\t $%12.2f\t $%9.2f\n",
					invoiceList.get(x).getInvoiceCode(),  aCustomer.getName() + "[" + aCustomer.getTypeStr() + "]",aPerson.getName(), Subtotals, Fees, Taxes, Discount, Overall);	

			//Add all monetary values to totals
			FeesTotal += Fees;
			SubtotalsTotal += Subtotals;
			TaxesTotal += Taxes;
			DiscountTotal += Discount;
			OverallTotal += Overall;

		}		

		System.out.println(String.format("====================================================================================================================================================="));
		System.out.println(String.format("%-68s \t $%10.2f\t $%10.2f\t $%10.2f\t $%12.2f\t $%9.2f\n\n", "TOTAL", SubtotalsTotal, FeesTotal, TaxesTotal, DiscountTotal, OverallTotal));	



		//Begin writing the detailed report of each invoice
		System.out.println(String.format("Individual Invoice Detail Reports\n"
				+ "====================================\n"));

		//For loop to go through each invoice
		for (int x=0; x < invoiceList.size(); x++) {

			SubtotalsTotal = 0.0;
			TaxesTotal = 0.0;
			OverallTotal = 0.0;

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
					+ "\t %s", aPerson.getName(),aCustomer.getName(),aCustomer.getCustomerCode(), aCustomer.getTypeStr(), aCustomer.getContact().getName()));
			System.out.println(String.format("\t %s %s %s %s %s",address.getStreet(),address.getCity(),address.getState(),address.getZip(),address.getCountry()));
			System.out.println(String.format("--------------------------------------------------------"));


			//Display the details of all products for this invoice
			System.out.printf("%-8s %-60s\t %8s\t\t %8s\t\t %8s\n", "Code", "Item", "Subtotal", "Tax", "Total");

			for (Product aProduct : invoiceList.get(x).getProductList()) {		
				String type = aProduct.getProductType();



				double subtotal = 0.0;
				double taxes = 0.0;
				double total = 0.0;

				//Depending on type, creates the corresponding object of type Product
				if(type.equals("P")) {
					ParkingPass pass = (ParkingPass) aProduct;
					int ParkingDiscount = 0;

					String PassTicketCode = pass.getTicketCode();
					if(PassTicketCode == null) {
						pass.setDiscount(ParkingDiscount);
						subtotal = pass.getSubtotal();
						taxes += pass.getTaxes();
						total = subtotal + taxes;
						System.out.printf("%-8s %-60s \t $%8.2f\t\t $%8.2f\t\t $%8.2f\n", 
								pass.getProductCode(), pass.getTypeStr() + " (" + pass.getProductQuantity() + " units @ $" + String.format("%.2f", pass.getParkingFee()) + "/unit)", subtotal, taxes, total);
					}
					else {
						for (Product bProduct : invoiceList.get(x).getProductList()) {
							if ((bProduct.getProductType()).equals("M")){
								MovieTicket ticketB = (MovieTicket) bProduct;
								if(ticketB.getProductCode().equals(PassTicketCode)) {
									if (ticketB.getProductQuantity() > pass.getProductQuantity()) {
										ParkingDiscount = pass.getProductQuantity();
									}
									else if (ticketB.getProductQuantity() <= pass.getProductQuantity()){
										ParkingDiscount = ticketB.getProductQuantity();
									}
								}
							}
							else if ((bProduct.getProductType()).equals("S")) {
								SeasonPass seasonB = (SeasonPass) bProduct;
								if(seasonB.getProductCode().equals(PassTicketCode)) {
									if (seasonB.getProductQuantity() > pass.getProductQuantity()) {
										ParkingDiscount = pass.getProductQuantity();
									}
									else if (seasonB.getProductQuantity() <= pass.getProductQuantity()) {
										ParkingDiscount = seasonB.getProductQuantity();
									}
								}
							}
						}
						pass.setDiscount(ParkingDiscount);
						subtotal = pass.getSubtotal();
						taxes += pass.getTaxes();
						total = subtotal + taxes;;

						System.out.printf("%-8s %-13s %-5s (%-2d units @ $%4.2f/unit)\t\t\t $%8.2f\t\t $%8.2f\t\t $%8.2f\n", 
								pass.getProductCode(), pass.getTypeStr(),pass.getTicketCode(),pass.getProductQuantity(),pass.getParkingFee(), subtotal, taxes, total);

					}
				}
				else if(type.equals("M")) {
					MovieTicket aTicket = null;
					for(int j=0; j < productList.size(); j++) {
						if(productList.get(j).getProductCode().equals(aProduct.getProductCode())){
							aTicket = (MovieTicket)productList.get(j);
						}
					}
					MovieTicket ticket = aTicket;
					double movieDiscount = 0.0;
					int dayOfWeek = ticket.checkDate();
					if(dayOfWeek == 5 || dayOfWeek == 7) {
						movieDiscount = .07;
					}
					ticket.setDiscount(movieDiscount);
					subtotal = ticket.getSubtotal();
					taxes += ticket.getTaxes();
					total = subtotal + taxes;

					System.out.printf("%-8s %-60s\t $%8.2f\t\t $%8.2f\t\t $%8.2f\n",
							ticket.getProductCode(),ticket.getTypeStr() + " '" + ticket.getMovieName() + "' @ " + ticket.getAddress().getStreet(), subtotal, taxes, total);
					if(movieDiscount == .07) {
						System.out.printf("%-8s %s (%d units @ $%.2f/unit - %-14s)\n", "", ticket.getDateTime(), ticket.getProductQuantity(), ticket.getPricePerUnit(), "Tue/Thu 7% off");
					}
					else {
						System.out.printf("%-8s %s (%d units @ $%.2f/unit)\n", "", ticket.getDateTime(), ticket.getProductQuantity(), ticket.getPricePerUnit());
					}

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
					refresh.setDiscount(refreshmentDiscount);
					subtotal = refresh.getSubtotal();
					taxes += refresh.getTaxes();
					total = subtotal + taxes;

					if (movieTrue == 1) {
						System.out.printf("%-8s %-19s (%-2d units @ $%5.2f/unit with 5%% off)\t $%8.2f\t\t $%8.2f\t\t $%8.2f\n", 
								refresh.getProductCode(),refresh.getName(), refresh.getProductQuantity(),refresh.getCost(), subtotal, taxes, total);
					}
					else {
						System.out.printf("%-8s %-15s (%-2d units @ $%5.2f/unit)\t\t\t $%8.2f\t\t $%8.2f\t\t $%8.2f\n", 
								refresh.getProductCode(),refresh.getName(), refresh.getProductQuantity(),refresh.getCost(), subtotal, taxes, total);
					}

				}
				else if(type.equals("S")) {
					SeasonPass pass = (SeasonPass) aProduct;
					subtotal = pass.getSubtotal();
					taxes += pass.getTaxes();
					total = subtotal + taxes;

					System.out.printf("%-8s %-15s %-40s \t $%8.2f \t\t $%8.2f \t\t $%8.2f\n", 
							pass.getProductCode(),pass.getTypeStr(),pass.getName(), subtotal, taxes, total);
					System.out.printf("%-8s (%d units @ $%.2f/unit + $8 fee/unit)\n", "", pass.getProductQuantity(), pass.getCost());

				}

				SubtotalsTotal += subtotal;
				TaxesTotal += taxes;
				OverallTotal += total;


			}
			System.out.printf("\t\t\t\t\t\t\t\t\t==========================================================\n\n");
			System.out.println(String.format("%-60s \t\t $%8.2f \t\t $%8.2f \t\t $%8.2f", "SUB-TOTALS", SubtotalsTotal, TaxesTotal, OverallTotal));
			if (aCustomer.getType().equals("S")) {
				double discount = -((.08 * SubtotalsTotal) + TaxesTotal);
				System.out.printf("%-80s \t\t\t\t\t $%8.2f\n", "DISCOUNT (8% STUDENT & NO TAX)", discount);
				double fee = 6.75;
				System.out.printf("%-80s \t\t\t\t\t $%8.2f\n", "ADDITIONAL FEE (Student)", fee);
				OverallTotal += (fee + discount);
			}
			System.out.printf("%-80s \t\t\t\t\t $%8.2f\n\n", "TOTAL", OverallTotal);

			System.out.printf("Thank you for your purchase!\n\n\n");




		}
		System.out.println(String.format("======================================================================================================================"));
	}
}



