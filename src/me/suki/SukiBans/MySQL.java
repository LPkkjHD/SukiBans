package me.suki.SukiBans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {
	
	public static Connection con;
	
	public static Connection connect(String host, String user, String passwort, String database, int port) throws Exception{
		java.sql.Connection con = null;
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?user=" + user + "&password=" + passwort + "&autoReconnect=true");
		System.out.println("MySQL Connected!");
		MySQL.con = con;
		
		return con;
	}
	public static void update(String query){
		Statement st;
		try {
			st = (Statement) con.createStatement();
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static ResultSet query(String query){
		ResultSet rs = null;
		
		Statement st;
		try {
			st = (Statement) con.createStatement();
			rs = st.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	public static void close() throws Exception{
		con.close();
	}

}
