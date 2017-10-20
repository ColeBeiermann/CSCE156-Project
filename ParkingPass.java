
//This class creates a ParkingPassObject of type Product
public class ParkingPass extends Product{

	//data member
	private double parkingFee;
	private String ticketCode;
	
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
		double subtotal = parkingFee * productQuantity;
		return subtotal;
	}
	
	@Override
	public double getTaxes() {
		double taxes = parkingFee * productQuantity * 0.04;
		return taxes;
	}

	@Override
	public String getTypeStr() {
		return "Parking Pass";
	}
	
	
}
