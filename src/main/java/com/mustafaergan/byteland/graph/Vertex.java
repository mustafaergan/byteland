package com.mustafaergan.byteland.graph;

import java.util.ArrayList;


/**
 * 
 * Contains node point and neighbor edge information
 * 
 * @author mustafa.ergan
 * @date 2017/12/13
 *
 */
public class Vertex {

    private ArrayList<Edge> neighborhood;
    private String name;
    
    public Vertex(String name){
        this.name = name;
        this.neighborhood = new ArrayList<Edge>();
    }
    
    public void addNeighbor(Edge edge){
        if(this.neighborhood.contains(edge)){
            return;
        }
        
        this.neighborhood.add(edge);
    }
    
    public boolean containsNeighbor(Edge other){
        return this.neighborhood.contains(other);
    }
    
    public Edge getNeighbor(int index){
        return this.neighborhood.get(index);
    }
    
    
    public Edge removeNeighbor(int index){
        return this.neighborhood.remove(index);
    }
    
    public void removeNeighbor(Edge e){
        this.neighborhood.remove(e);
    }
    
    
    public int getNeighborCount(){
        return this.neighborhood.size();
    }
    
    public void setName(String name) {
		this.name = name;
	}
    
    public String getName(){
        return this.name;
    }
    
    public String toString(){
        return "Vertex " + name;
    }
    
    public int hashCode(){
        return this.name.hashCode();
    }
    
    public boolean equals(Object other){
        if(!(other instanceof Vertex)){
            return false;
        }
        Vertex v = (Vertex)other;
        return this.name.equals(v.name);
    }
    
    public ArrayList<Edge> getNeighbors(){
        return new ArrayList<Edge>(this.neighborhood);
    }
    
}

