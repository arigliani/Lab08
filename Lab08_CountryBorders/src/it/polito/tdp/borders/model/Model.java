package it.polito.tdp.borders.model;

import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private BordersDAO dao= new BordersDAO();
	//prima della class va il tipo di oggetto che ho messo come arco
	private UndirectedGraph<Country, DefaultEdge> graph;
	public Model() {

		this.graph= new SimpleGraph<>(DefaultEdge.class); 
	}
	
	public void creaGrafo(int i){
		Graphs.addAllVertices(graph, dao.loadAllCountries());
		List<Border> confiniAnno= dao.getCountryPairs(i);
		
		//il metodo lista adiacenti non funziona
		
		for(Country c1: graph.vertexSet()){
			for(Country c2: dao.listAdiacenti(c1, i)){
				graph.addEdge(c1, c2);
			}
			
		}
		
	}
	
	
	
	
	
	
	

}
