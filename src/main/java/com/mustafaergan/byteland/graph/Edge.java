package com.mustafaergan.byteland.graph;

import java.util.Arrays;
import java.util.List;


/**
 * Contains edge and neighbor vertex information
 * 
 * Firstly, the emphasis is given to the fact that the edges are weighted and solved.
 * It has been observed that it will not succeed in this way and has not been changed with the thought that it may be need later.
 * 
 * @author mustafa.ergan
 * @date 2017/12/13
 *
 */
public class Edge {

    private Vertex start, end;
    private int weight;
    
    public Edge(Vertex start, Vertex end){
        this(start, end, 1);
    }
    
    public Edge(Vertex start, Vertex end, int weight){
        this.start = (start.getName().compareTo(end.getName()) <= 0) ? start : end;
        this.end = (this.start == start) ? end : start;
        this.weight = weight;
    }
    
    public Vertex getNeighbor(Vertex current){
        if(!(current.equals(start) || current.equals(end))){
            return null;
        }
        return (current.equals(start)) ? end : start;
    }
    
    public List<Vertex> getVertexList(){
    	return Arrays.asList(this.start,this.end);
    }
    
    public Vertex getStart(){
        return this.start;
    }
    
    public Vertex getEnd(){
        return this.end;
    }
    
    public int getWeight(){
        return this.weight;
    }
    
    public void setWeight(int weight){
        this.weight = weight;
    }
    
    public void setStart(Vertex start) {
		this.start = start;
	}
    
    public void setEnd(Vertex end) {
		this.end = end;
	}
    
    public int compareTo(Edge other){
        return this.weight - other.weight;
    }
    
    public String toString(){
        return "(Edge : {Start:" + start + ", End:" + end + "}, Weight:" + weight + ")";
    }
    
    public int hashCode(){
        return (start.getName() + end.getName()).hashCode(); 
    }
    
    public boolean equals(Object other){
        if(!(other instanceof Edge)){
            return false;
        }
        Edge e = (Edge)other;
        return e.start.equals(this.start) && e.end.equals(this.end);
    }   

}
