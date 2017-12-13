package com.mustafaergan.byteland.graph;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.mustafaergan.byteland.TestBase;

public class GraphTest extends TestBase {
	
	
    @Test
    public void test_mergeVertex() {
    	Graph graph = createGraphA();
    	graph.mergeVertex(graph.getVertex("5"), new Vertex("0"));
    	assertTrue(graph.getVertex().size() == 9);
    }
    
    @Test
    public void test_isNeighborhoodA() {
    	Graph graph = createGraphA();
    	assertTrue(graph.isNeighborhood(graph.getVertex("5"), new Vertex("3")) == false);
    }
    
    @Test
    public void test_isNeighborhoodB() {
    	Graph graph = createGraphA();
    	assertTrue(graph.isNeighborhood(graph.getVertex("2"), new Vertex("3")) == true);
    }
    
    public void test_renameA(){
    	Graph graph = createGraphA();
    	graph.mergeVertex(graph.getVertex("5"), new Vertex("0"));
    	graph.rename();
    	assertTrue(graph.getVertex("0") != null);
    }
    
    public void test_renameB(){
    	Graph graph = createGraphA();
    	graph.mergeVertex(graph.getVertex("8"), new Vertex("9"));
    	graph.rename();
    	assertTrue(graph.getVertex("9") == null);
    }

}
