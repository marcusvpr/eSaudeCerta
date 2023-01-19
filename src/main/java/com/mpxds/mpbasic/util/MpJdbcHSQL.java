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
			
			connection = DriverManager.getConnection(
					"jdbc:hsqldb:file:~/db/mpSaudeMedidaCerta/mpSaudeMedidaCertaDB", "SA", "");
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("SELECT codigo, descricao FROM mp_tabela_interna" +
											   " ORDER BY codigo");
			//
			System.out.println("Tabela Interna LISTAGEM: "); 

					while (resultSet.next()) {
				//
				System.out.println("Tabela Interna : " 
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