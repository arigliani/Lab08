package it.polito.tdp.borders.db;

import java.util.List;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Border;

public class TestDAO {

	public static void main(String[] args) {

		BordersDAO dao = new BordersDAO();

		System.out.println("Lista di tutte le nazioni:");
		System.out.println(dao.loadAllCountries());
		
		System.out.println("Lista confini anno 1990: ");
		System.out.println(dao.getCountryPairs(1990));
		
		
		System.out.println("confini un paese nel 2000 ");
		Country c= dao.loadAllCountries().get(1);
		System.out.println(dao.listAdiacenti(c,2000));

	}
}
