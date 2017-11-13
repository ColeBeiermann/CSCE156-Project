
//This class creates a ParkingPassObject of type Product
public class ParkingPass extends Product{

	//data member
	private double parkingFee;
	private String ticketCode;
	private int discount;
	
	//Constructor
	public ParkingPass(String productCode, String productType, double parkingFee) {
		super(productCode, productType);
		this.parkingFee = parkingFee;
		
		//this.productType = "Parking Pass";
	}

	//Getters and Setters	
	public double getParkingFee() {
		return parkingFee;
	}

	public void setParkingFee(double parkingFee) {
		this.parkingFee = parkingFee;
	}
	
	public String getTicketCode() {
		return this.ticketCode;
	}
	
	public void setTicketCode(String code) {
		this.ticketCode = code;
	}
	
	@Override
	public double getSubtotal() {
		double subtotal = 0.0;
		if (this.productQuantity > this.discount) {
			subtotal = parkingFee * (this.productQuantity- this.discount);
		}
		return subtotal;
	}
	
	@Override
	public double getTaxes() {
		double taxes = parkingFee * (this.productQuantity- this.discount) * 0.04;
		return taxes;
	}

	@Override
	public String getTypeStr() {
		return "Parking Pass";
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	
}
