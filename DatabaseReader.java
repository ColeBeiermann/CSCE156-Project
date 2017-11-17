import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseReader {	
	private Connection conn = null;
	private Statement stmt = null;
	
	private ArrayList<Invoice> invoiceList = null;


	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://cse.unl.edu:3306/dhoppe";

	//  Database credentials
	static final String USER = "dhoppe";
	static final String PASS = "Jpv:n6";

	public void connectMeIn() {
		try{
			Class.forName("com.mysql.jdbc.Driver");			
		}catch(ClassNotFoundException e){
			System.err.println(e);
			System.exit (-1);
		}
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e){
			e.printStackTrace();
		}
	}


	public ArrayList<Invoice> readInvoices() {
		
		ArrayList<Invoice> invoiceList = null;
		String query = "SELECT InvoiceCode FROM Invoice";
		Statement stat;

		ArrayList <String> invCodeList = null;

		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(query);

			while (rs.next()){
				String code = rs.getString("InvoiceCode");
				invCodeList.add(code);
			}

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		for(int i = 0; i < invCodeList.size(); i ++) {

			String SQL = "SELECT * FROM Invoice WHERE InvoiceCode = ?";

			PreparedStatement ps = null;
			ResultSet rs = null;

			String invoiceCode = invCodeList.get(i);
			String customerCode = null;
			String personCode = null;
			String invoiceDate = null;
			ArrayList<Product> productList = null;


			try {
				ps = conn.prepareStatement(SQL);
				ps.setString(1, invoiceCode);
				rs = ps.executeQuery();
				if(rs.next()) {
					customerCode = rs.getString("CustomerCode");
					personCode = rs.getString("PersonCode");
					invoiceDate = rs.getString("InvoiceDate");

					productList = getProductList(invoiceCode);

					Invoice invoice = new Invoice(invoiceCode, customerCode, personCode, invoiceDate, productList);
					invoiceList.add(invoice);
				}
				rs.close();

				ps.close();			

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return invoiceList;
	}

	private ArrayList<Product> getProductList(String invoiceCode){
		ArrayList<Product> productList = null;
		String SQL = "SELECT * FROM InvoiceProducts as IP JOIN Product as P ON IP.ProductCode = P.ProductCode WHERE IP.InvoiceCode = ?";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(SQL);
			ps.setString(1, invoiceCode);
			rs = ps.executeQuery();
			if(rs.next()) {
				String productCode = rs.getString("ProductCode");
				String productType = rs.getString("ProductType");
				int quantity = rs.getInt("ProductCount");

				productList.add(getProduct(productCode,productType, quantity));
			}
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productList;
	}

	private Product getProduct(String productCode, String type, int quantity) {
		String SQL = null;

		Product product = null;
		String productType = null;

		if(type.equals("P")) {
			productType = "ParkingPass";
		}
		else if(type.equals("M")) {
			productType = "MovieTicket";
		}
		else if(type.equals("R")) {
			productType = "Refreshment";
		}
		else if(type.equals("S")) {
			productType = "SeasonPass";
		}

		SQL = "SELECT * FROM ?";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(SQL);
			ps.setString(1, productType);
			rs = ps.executeQuery();
			if(rs.next()) {
				if(type.equals("P")) {
					product = new ParkingPass(productCode, productType, rs.getDouble("ParkingFee"));
					product.setQuantity(quantity);

				}
				else if(type.equals("M")) {
					
					String fullAddress = rs.getString("Address");
					String addressStr [] = fullAddress.split(",");
					String street = addressStr[0];
					String city = addressStr[1];
					String state = addressStr[2];
					String zip = addressStr[3];
					String country = addressStr[4];

					Address address = new Address(street,city,state,zip,country);
					
					product = new MovieTicket(productCode,productType,rs.getString("DateTime"), address, rs.getString("MovieName"),
							rs.getString("ScreenNo"), rs.getDouble("PricePerUnit"));
					product.setQuantity(quantity);

				}
				else if(type.equals("R")) {
					product = new Refreshment(productCode, productType,rs.getString("RefreshName"), rs.getDouble("Price"));
					product.setQuantity(quantity);

				}
				else if(type.equals("S")) {
					product = new SeasonPass(productCode, productType, rs.getString("ProductName"), rs.getString("StartDate"), rs.getString("EndDate"), 
							rs.getDouble("Price"));
					product.setQuantity(quantity);
				}
			}
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

public void closeConnection(){
	try {
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
}



