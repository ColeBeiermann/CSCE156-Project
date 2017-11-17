package com.ceg.ext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/*
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 * 15 methods in total, add more if required.
 * Donot change any method signatures or the package name.
 * 
 */

public class InvoiceData {

	/**
	 * 1. Method that removes every person record from the database
	 */
	public static void removeAllPersons() {
		Connection conn = DatabaseInfo.getConnection();
		Statement stat;
		ResultSet rs;

		String delete = "delete * From Person";
		String safeUpdates = "set SQL_SAFE_UPDATES = ?";

		try {
			PreparedStatement safePS = conn.prepareStatement(safeUpdates);
			safePS.setInt(1, 0);
			safePS.executeUpdate();

			stat = conn.createStatement();
			rs = stat.executeQuery(delete);

			rs.close();
			stat.close();
			conn.close();
		}
		catch (SQLException e) 
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 2. Method to add a person record to the database with the provided data.
	 * 
	 * @param personCode
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addPerson(String personCode, String firstName, String lastName, String street, String city, String state, String zip, String country) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;

		String query = "INSERT INTO Person VALUES(?, ?, ?, ?)";
		String safeUpdates = "set SQL_SAFE_UPDATES = ?";

		try {
			PreparedStatement safePS = conn.prepareStatement(safeUpdates);
			safePS.setInt(1, 0);
			safePS.executeUpdate();

			ps = conn.prepareStatement(query);
			ps.setString(1, personCode);
			ps.setString(2, firstName + "," + lastName);
			ps.setString(3, street +"," + city + "," + state + "," + zip + "," + country);
			ps.setString(4, "");

			rs = ps.executeQuery();

			rs.close();
			ps.close();
			conn.close();
		}
		catch (SQLException e) 
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 3. Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 * 
	 * @param personCode
	 * @param email
	 */
	public static void addEmail(String personCode, String email) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;

		String query = "UPDATE Person Set Email = ? WHERE PersonCode = ?";
		String safeUpdates = "set SQL_SAFE_UPDATES = ?";

		try {
			PreparedStatement safePS = conn.prepareStatement(safeUpdates);
			safePS.setInt(1, 0);
			safePS.executeUpdate();

			ps = conn.prepareStatement(query);
			ps.setString(1, personCode);
			ps.setString(2, email);			

			rs = ps.executeQuery();

			rs.close();
			ps.close();
			conn.close();
		}
		catch (SQLException e) 
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 4. Method that removes every customer record from the database
	 */
	public static void removeAllCustomers() {
		Connection conn = DatabaseInfo.getConnection();
		Statement stat;
		ResultSet rs;

		String delete = "delete * From Customer";
		String safeUpdates = "set SQL_SAFE_UPDATES = ?";

		try {
			PreparedStatement safePS = conn.prepareStatement(safeUpdates);
			safePS.setInt(1, 0);
			safePS.executeUpdate();

			stat = conn.createStatement();
			rs = stat.executeQuery(delete);

			rs.close();
			stat.close();
			conn.close();
		}
		catch (SQLException e) 
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static void addCustomer(String customerCode, String customerType, String primaryContactPersonCode,String name, String street, String city, String state, String zip, String country) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;

		String query = "INSERT INTO Customer VALUES(?, ?, ?, ?, ?)";
		String safeUpdates = "set SQL_SAFE_UPDATES = ?";

		try {
			PreparedStatement safePS = conn.prepareStatement(safeUpdates);
			safePS.setInt(1, 0);
			safePS.executeUpdate();

			ps = conn.prepareStatement(query);
			ps.setString(1, customerCode);
			ps.setString(2, customerType);
			ps.setString(3, primaryContactPersonCode);
			ps.setString(4, name);
			ps.setString(5, street +"," + city + "," + state + "," + zip + "," + country);

			rs = ps.executeQuery();

			rs.close();
			ps.close();
			conn.close();
		}
		catch (SQLException e) 
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 5. Removes all product records from the database
	 */
	public static void removeAllProducts() {
		Connection conn = DatabaseInfo.getConnection();
		Statement stat;
		ResultSet rs;

		String delete = "delete * From Product";
		String safeUpdates = "set SQL_SAFE_UPDATES = ?";

		try {
			PreparedStatement safePS = conn.prepareStatement(safeUpdates);
			safePS.setInt(1, 0);
			safePS.executeUpdate();

			stat = conn.createStatement();
			rs = stat.executeQuery(delete);

			rs.close();
			stat.close();
			conn.close();
		}
		catch (SQLException e) 
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 6. Adds an movieTicket record to the database with the provided data.
	 */
	public static void addMovieTicket(String productCode, String dateTime, String movieName, String street, String city,String state, String zip, String country, String screenNo, double pricePerUnit) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;

		String query = "INSERT INTO MovieTicket VALUES(?, ?, ?, ?, ?, ?)";
		String safeUpdates = "set SQL_SAFE_UPDATES = ?";

		try {
			PreparedStatement safePS = conn.prepareStatement(safeUpdates);
			safePS.setInt(1, 0);
			safePS.executeUpdate();

			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			ps.setString(2, dateTime);
			ps.setString(3, movieName);
			ps.setString(4, street +"," + city + "," + state + "," + zip + "," + country);
			ps.setString(5, screenNo);
			ps.setDouble(6, pricePerUnit);

			rs = ps.executeQuery();

			rs.close();
			ps.close();
			conn.close();
		}
		catch (SQLException e) 
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 7. Adds a seasonPass record to the database with the provided data.
	 */
	public static void addSeasonPass(String productCode, String name, String seasonStartDate, String seasonEndDate,	double cost) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;

		String query = "INSERT INTO SeasonPass VALUES(?, ?, ?, ?, ?, ?)";
		String safeUpdates = "set SQL_SAFE_UPDATES = ?";

		try {
			PreparedStatement safePS = conn.prepareStatement(safeUpdates);
			safePS.setInt(1, 0);
			safePS.executeUpdate();

			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			ps.setString(2, name);
			ps.setString(3, seasonStartDate);
			ps.setString(4, seasonEndDate);
			ps.setDouble(5, cost);
			ps.setString(4, "");

			rs = ps.executeQuery();

			rs.close();
			ps.close();
			conn.close();
		}
		catch (SQLException e) 
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 8. Adds a ParkingPass record to the database with the provided data.
	 */
	public static void addParkingPass(String productCode, double parkingFee) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;

		String query = "INSERT INTO ParkingPass VALUES(?, ?, null)";
		String safeUpdates = "set SQL_SAFE_UPDATES = ?";

		try {
			PreparedStatement safePS = conn.prepareStatement(safeUpdates);
			safePS.setInt(1, 0);
			safePS.executeUpdate();

			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			ps.setDouble(2, parkingFee);

			rs = ps.executeQuery();

			rs.close();
			ps.close();
			conn.close();
		}
		catch (SQLException e) 
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


	/**
	 * 9. Adds a refreshment record to the database with the provided data.
	 */
	public static void addRefreshment(String productCode, String name, double cost) {

		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;

		String query = "INSERT INTO Refreshment VALUES(?, ?, ?)";
		String safeUpdates = "set SQL_SAFE_UPDATES = ?";

		try {
			PreparedStatement safePS = conn.prepareStatement(safeUpdates);
			safePS.setInt(1, 0);
			safePS.executeUpdate();

			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			ps.setString(2, name);
			ps.setDouble(3, cost);

			rs = ps.executeQuery();

			rs.close();
			ps.close();
			conn.close();
		}
		catch (SQLException e) 
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 10. Removes all invoice records from the database
	 */
	public static void removeAllInvoices() {
		Connection conn = DatabaseInfo.getConnection();
		Statement stat;
		ResultSet rs;

		String delete = "DELETE * FROM Invoice";
		String safeUpdates = "set SQL_SAFE_UPDATES = ?";

		try {
			PreparedStatement safePS = conn.prepareStatement(safeUpdates);
			safePS.setInt(1, 0);
			safePS.executeUpdate();

			stat = conn.createStatement();
			rs = stat.executeQuery(delete);

			rs.close();
			stat.close();
			conn.close();
		}
		catch (SQLException e) 
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 11. Adds an invoice record to the database with the given data.
	 */
	public static void addInvoice(String invoiceCode, String customerCode, String salesPersonCode, String invoiceDate) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;

		String query = "INSERT INTO Invoice VALUES (?, ?, ?, ?)";
		String safeUpdates = "set SQL_SAFE_UPDATES = ?";

		try {
			PreparedStatement safePS = conn.prepareStatement(safeUpdates);
			safePS.setInt(1, 0);
			safePS.executeUpdate();

			ps = conn.prepareStatement(query);
			ps.setString(1, invoiceCode);
			ps.setString(2, customerCode);
			ps.setString(3, salesPersonCode);
			ps.setString(4, invoiceDate);

			rs = ps.executeQuery();

			rs.close();
			ps.close();
			conn.close();
		}
		catch (SQLException e) 
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 12. Adds a particular movieticket (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given number of units
	 */

	public static void addMovieTicketToInvoice(String invoiceCode, String productCode, int quantity) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;

		String query = "INSERT INTO InvoiceProducts VALUES (?, ?,null, ?)";
		String safeUpdates = "set SQL_SAFE_UPDATES = ?";

		try {
			PreparedStatement safePS = conn.prepareStatement(safeUpdates);
			safePS.setInt(1, 0);
			safePS.executeUpdate();

			ps = conn.prepareStatement(query);
			
			ps.setString(1, invoiceCode);
			ps.setString(2, productCode);
			ps.setInt(3, quantity);
			

			rs = ps.executeQuery();

			rs.close();
			ps.close();
			conn.close();
		}
		catch (SQLException e) 
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 13. Adds a particular seasonpass (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given begin/end dates
	 */
	public static void addSeasonPassToInvoice(String invoiceCode, String productCode, int quantity) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;

		String query = "INSERT INTO InvoiceProducts VALUES (?, ?,null, ?)";
		String safeUpdates = "set SQL_SAFE_UPDATES = ?";

		try {
			PreparedStatement safePS = conn.prepareStatement(safeUpdates);
			safePS.setInt(1, 0);
			safePS.executeUpdate();

			ps = conn.prepareStatement(query);
			
			ps.setString(1, invoiceCode);
			ps.setString(2, productCode);
			ps.setInt(3, quantity);
			

			rs = ps.executeQuery();

			rs.close();
			ps.close();
			conn.close();
		}
		catch (SQLException e) 
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 14. Adds a particular ParkingPass (corresponding to <code>productCode</code> to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of quantity.
	 * NOTE: ticketCode may be null
	 */
	public static void addParkingPassToInvoice(String invoiceCode, String productCode, int quantity, String ticketCode) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;

		String query = "INSERT INTO InvoiceProducts VALUES (?, ?,?, ?)";
		String safeUpdates = "set SQL_SAFE_UPDATES = ?";

		try {
			PreparedStatement safePS = conn.prepareStatement(safeUpdates);
			safePS.setInt(1, 0);
			safePS.executeUpdate();

			ps = conn.prepareStatement(query);
			
			ps.setString(1, invoiceCode);
			ps.setString(2, productCode);
			ps.setString(3, ticketCode);
			ps.setInt(4, quantity);
			

			rs = ps.executeQuery();

			rs.close();
			ps.close();
			conn.close();
		}
		catch (SQLException e) 
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 15. Adds a particular refreshment (corresponding to <code>productCode</code> to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of quantity. 
	 */
	public static void addRefreshmentToInvoice(String invoiceCode, String productCode, int quantity) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;

		String query = "INSERT INTO InvoiceProducts VALUES (?, ?,null, ?)";
		String safeUpdates = "set SQL_SAFE_UPDATES = ?";

		try {
			PreparedStatement safePS = conn.prepareStatement(safeUpdates);
			safePS.setInt(1, 0);
			safePS.executeUpdate();

			ps = conn.prepareStatement(query);
			
			ps.setString(1, invoiceCode);
			ps.setString(2, productCode);
			ps.setInt(3, quantity);
			

			rs = ps.executeQuery();

			rs.close();
			ps.close();
			conn.close();
		}
		catch (SQLException e) 
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
