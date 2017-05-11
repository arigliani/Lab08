package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public List<Country> loadAllCountries() {

		String sql = "SELECT ccode,StateAbb,StateNme " + "FROM country " + "ORDER BY StateAbb ";
		List<Country> lista= new ArrayList<Country>();
		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				//System.out.format("%d %s %s\n", rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme"));
				Country temp= new Country(rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme"));
				lista.add(temp);
			}

			conn.close();
			return lista;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database Error -- loadAllCountries");
			throw new RuntimeException("Database Error");
		}
	}
	
	

	public List<Border> getCountryPairs(int anno) {
		String sql = "SELECT  `state1no`,  `state1ab`,  `state2no`,  `state2ab`,  `year`,  `conttype` " + "FROM `countries`.`contiguity` "+
	                "WHERE year=? AND conttype=1 ";
		List<Border> lista= new ArrayList<Border>();
	
		try {
			
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st;
			st = conn.prepareStatement(sql);
			
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery();
			
			
			while (rs.next()) {
				Border temp= new Border(rs.getInt("state1no"), rs.getString("state1ab"),rs.getInt("state2no"), rs.getString("state2ab"), anno);
				lista.add(temp);
			}

			conn.close();
			return lista;
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista;
	}



	public List<Country> listAdiacenti(Country c, int anno) {
		
		String sql = "SELECT  `state1no`,  `state1ab`,  `state2no`,  `state2ab`,  `year`,  `conttype` " + "FROM `countries`.`contiguity` "+
                "WHERE state1no=? AND state1ab=? AND year=? ";
		List<Country> lista= new ArrayList<Country>();

	try {
		
		Connection conn = DBConnect.getInstance().getConnection();
		PreparedStatement st;
		st = conn.prepareStatement(sql);
		
		st.setInt(1, c.getcCod());
		st.setString(2, c.getStateAbb());
		st.setInt(3, anno);
		
		ResultSet rs = st.executeQuery();
		
		
		while (rs.next()) {
			Country temp= new Country(rs.getInt("state2no"), rs.getString("state2ab"), "");
			lista.add(temp);
		}

		conn.close();
		return lista;
		
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return lista;
    }
}
