package com.mustafaergan.byteland.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 
 * There are node points and edges.
 * Graph all relationships are found in this class
 * Edges and vertices are mapped because of accessibility.
 * 
 * 
 * @author mustafa.ergan
 * @date 2017/12/13
 *
 */
public class Graph {
    
    private Map<String, Vertex> vertices;
    private Map<Integer, Edge> edges;
    
    public Graph(){
        this.vertices = new HashMap<String, Vertex>();
        this.edges = new HashMap<Integer, Edge>();
    }
    
    public Graph(ArrayList<Vertex> vertices){
        this.vertices = new HashMap<String, Vertex>();
        this.edges = new HashMap<Integer, Edge>();
        for(Vertex v: vertices){
            this.vertices.put(v.getName(), v);
        }
    }
    
    /**
     * Combine two vertices.
     * The information of the second-party vertex assigns a vertex
     * @param one - the vertices that the algorithm will combine
     * @param two - the vertices that the algorithm will combine
     */
    public void mergeVertex(Vertex one, Vertex two){
    	for (Edge edgeTwo : two.getNeighbors()) {
    		removeEdge(edgeTwo);
    		if(edgeTwo.getStart().getName().equals(one.getName())){
    			edgeTwo.setStart(one);
    		}else if(edgeTwo.getEnd().getName().equals(one.getName())){
    			edgeTwo.setEnd(one);
    		}
    		if(edgeTwo.getStart().getName().equals(two.getName())){
    			edgeTwo.setStart(one);
    		}else if(edgeTwo.getEnd().getName().equals(two.getName())){
    			edgeTwo.setEnd(one);
    		}
    		if(edgeTwo.hashCode() != (one.getName()+one.getName()).hashCode()){
    			one.addNeighbor(edgeTwo);
        		if(!edgeTwo.getStart().getName().equals(one.getName())){
        			Edge ed = new Edge(one,edgeTwo.getStart());
        			edgeTwo.getStart().addNeighbor(ed);
        		}else if(!edgeTwo.getEnd().getName().equals(one.getName())){
        			Edge ed = new Edge(edgeTwo.getEnd(), one);
        			edgeTwo.getEnd().addNeighbor(ed);
        		}
    		}
		}
		removeVertex(two.getName());
    }
    
    
    /**
     * The graph is renamed because of there are no spaces in the index
     */
    public void rename(){
    	int i = 0;
    	for (Vertex vertexOut : getVertex()) {
			String name = vertexOut.getName();
    		for(Vertex vertexIn : getVertex()){
				if(vertexIn.getName().equals(name)){
					vertexIn.setName(i+"");
					removeVertex(name);
					vertices.put(i+"", vertexIn);
				}
			}
    		i++;
		}
    }
    
	/**
	 * it is checked whether or not there is a neighbor during the merger 
	 * and the merging is applied accordingly
	 * 
	 * @return in case of neighbor return true
	 */
	public boolean isNeighborhood(Vertex vertexOut, Vertex vertexIn) {
		for (Edge out : vertexOut.getNeighbors()) {
			for (Vertex vertexNegOut : out.getVertexList()) {
				if (vertexNegOut.getName().equals(vertexIn.getName())) {
					return true;
				}
			}
		}
		return false;
	}
    
	public boolean addEdge(Vertex start, Vertex end){
        return addEdge(start, end, 1);
    }
    
    public boolean addEdge(Vertex start, Vertex end, int weight){
        if(start.equals(end)){
            return false;   
        }
        Edge e = new Edge(start, end, weight);
        if(edges.containsKey(e.hashCode())){
            return false;
        }
        else if(start.containsNeighbor(e) || end.containsNeighbor(e)){
            return false;
        }
        edges.put(e.hashCode(), e);
        start.addNeighbor(e);
        end.addNeighbor(e);
        return true;
    }
    
    public boolean containsEdge(Edge e){
        if(e.getStart() == null || e.getEnd() == null){
            return false;
        }
        return this.edges.containsKey(e.hashCode());
    }
    
    public Edge removeEdge(Edge e){
       e.getStart().removeNeighbor(e);
       e.getEnd().removeNeighbor(e);
       return this.edges.remove(e.hashCode());
    }
    
    
    public boolean addVertex(Vertex vertex, boolean overwriteExisting){
        Vertex current = this.vertices.get(vertex.getName());
        if(current != null){
            if(!overwriteExisting){
                return false;
            }
            while(current.getNeighborCount() > 0){
                this.removeEdge(current.getNeighbor(0));
            }
        }
        vertices.put(vertex.getName(), vertex);
        return true;
    }
    
    public Vertex removeVertex(String label){
        return vertices.remove(label);
    }
    
    public Set<String> vertexKeys(){
        return this.vertices.keySet();
    }
    
    public Set<Edge> getEdges(){
        return new HashSet<Edge>(this.edges.values());
    }
    
    public List<Vertex> getVertex(){
    	List<Vertex> vertex = new ArrayList<Vertex>();
    	for(Map.Entry entry:this.vertices.entrySet()){
    		vertex.add((Vertex)entry.getValue());
    	}
    	return vertex;
    }
    
    public boolean containsVertex(Vertex vertex){
        return this.vertices.get(vertex.getName()) != null;
    }
    
    public Vertex getVertex(String label){
        return vertices.get(label);
    }
    
}
