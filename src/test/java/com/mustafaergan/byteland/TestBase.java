package com.mustafaergan.byteland;

import com.mustafaergan.byteland.graph.Graph;
import com.mustafaergan.byteland.graph.Vertex;

public class TestBase {
	
	
	public Graph createGraph(int vertexCount, String edgeValuesString){
		Graph graph = new Graph();
		String[] edgeValues = edgeValuesString.split("\\s+");
		Vertex[] vertices = new Vertex[vertexCount];
		for (int j = 0; j < vertices.length; j++) {
			vertices[j] = new Vertex("" + j);
			graph.addVertex(vertices[j], true);
		}
		for (int x = 0; x < vertexCount - 1; x++) {
			int a = Integer.parseInt(edgeValues[x]);
			int b = x + 1;
			graph.addEdge(vertices[a], vertices[b]);
		}
		return graph;
	}
	
	public Graph createGraphA(){
		return createGraph(10,"0 1 2 0 0 3 3 2 8");
	}
	
	public Graph createGraphB(){
		return createGraph(8,"0 1 2 0 0 3 3");
	}
	
	public Graph createGraphC(){
		return createGraph(4,"0 1 2");
	}
	
	public Graph createGraphD(){
		return createGraph(9,"0 1 1 1 1 0 2 2");
	}

}
