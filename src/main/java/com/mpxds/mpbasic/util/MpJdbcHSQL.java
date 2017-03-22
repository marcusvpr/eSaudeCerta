package com.mpxds.mpbasic.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MpJdbcHSQL {
	//
	public static void main(String[] args) {
		//
		Connection connection = null;
		ResultSet resultSet = null;
		Statement statement = null;
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			
			connection = DriverManager.getConnection("jdbc:hsqldb:file:~/db/sisjuri/sisjuriDB", "SA", "");
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("SELECT numero, codigo, descricao FROM tabela_interna" +
											   " ORDER BY numero, codigo");
			//
			while (resultSet.next()) {
				//
				System.out.println("Tabela Interna : " 
						+ resultSet.getString("numero") + " / "
						+ resultSet.getString("codigo") + " / "
						+ resultSet.getString("descricao"));
				//
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}