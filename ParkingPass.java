
//This class creates a ParkingPassObject of type Product
public class ParkingPass extends Product{

	//data member
	private double parkingFee;
	
	//Constructor
	public ParkingPass(String productCode, String productType, double parkingFee) {
		super(productCode, productType);
		this.parkingFee = parkingFee;
	}

	//Getters and Setters	
	public double getParkingFee() {
		return parkingFee;
	}

	public void setParkingFee(double parkingFee) {
		this.parkingFee = parkingFee;
	}
}
