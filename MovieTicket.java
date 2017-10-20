import java.util.Calendar;

//This class creates a MovieTicket object of type product
public class MovieTicket extends Product {

	//Data Members
	private String dateTime;
	private Address address;
	private String movieName;
	private String screenNo;
	private double pricePerUnit;
	private double discount;

	//Constructor
	public MovieTicket(String productCode, String productType, String dateTime, Address address, String movieName,
			String screenNo, double pricePerUnit) {
		super(productCode, productType);
		this.dateTime = dateTime;
		this.movieName = movieName;
		this.screenNo = screenNo;
		this.pricePerUnit = pricePerUnit;
		this.address = address;
	}

	//Getters and Setters
	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getScreenNo() {
		return screenNo;
	}

	public void setScreenNo(String screenNo) {
		this.screenNo = screenNo;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	
	public void setDiscout(double discount) {
		this.discount = discount;
	}
	
	public int checkDate() {
		int dayOfWeek = 0;
		String fullDate = this.dateTime;
		
		String dateString [] = fullDate.split("-");
		int Year = Integer.parseInt(dateString[0]);
		int Month = Integer.parseInt(dateString[1]);
		String DayTime = dateString[2];
			String dayTimeString [] = DayTime.split(" ");
			int Day = Integer.parseInt(dayTimeString[0]);
			
			Calendar c = Calendar.getInstance();
			c.set(Year, Month, Day);

			dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		
		return dayOfWeek;
	}
	

	@Override
	public String getTypeStr() {
		return "Movie Ticket";
	}

	@Override
	public double getSubtotal() {
		double subtotal = (this.pricePerUnit * productQuantity) * (1.00 - this.discount);
		return subtotal;
	}

	@Override
	public double getTaxes() {
		double taxes = (this.pricePerUnit * productQuantity) * (1.00 - this.discount) * 0.06;
		return taxes;
	}


}
