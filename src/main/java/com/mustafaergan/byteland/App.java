package com.mustafaergan.byteland;

import java.util.Scanner;

import com.mustafaergan.byteland.graph.Graph;
import com.mustafaergan.byteland.graph.Vertex;
import com.mustafaergan.byteland.matching.EdmondsMaximumMatching;

/**
 * 
 * Initially, each city is an independent State. The process of integration is divided into steps.
 * At each step, due to the limited number of diplomatic envoys available, a State can only be involved in a unification process with at most one other state. At each step two States can unite to form a new State, but only if there exists a road directly connecting some two cities of the uniting States.
 * The unification process is considered to be complete when all the cities belong to the same, global State.
 * The Mayors have asked you to arrange a schedule for the diplomatic talks, so that unification can be completed in as few steps as possible. Can you handle this delicate task?

 * Input:
 * The first line contains t, the number of test cases (less than 1000). The descriptions of t test cases follow.
 * Each test case contains the description of the cities of Byteland, given in two lines. The first line contains a single integer k, representing the number of cities in Byteland (2 < = k < = 600); we assume that the cities are numbered 0,...,k-1. The second line contains exactly k-1 integers, and the ith integer having a value of p represents a road connecting cities having numbers i+1 and p in Byteland.
 * Output:
 * For each test case, output a separate line containing one number, equal to the minimum number of steps required to perform the unification.
 * 
 * 
 * @author mustafa.ergan
 * @date 2017/12/13
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Input:");
		Scanner scanner = new Scanner(System.in);
		int testCount = Integer.parseInt(scanner.nextLine());
		Graph[] graphs = new Graph[testCount];
		Graph graph;
		String edgeValuesString;
		String[] edgeValues;
		Vertex[] vertices;
		for (int i = 0; i < testCount; i++) {
			graph = new Graph();
			int vertexCount = Integer.parseInt(scanner.nextLine());
			edgeValuesString = scanner.nextLine();
			edgeValues = edgeValuesString.split("\\s+");
			vertices = new Vertex[vertexCount];
			for (int j = 0; j < vertices.length; j++) {
				vertices[j] = new Vertex("" + j);
				graph.addVertex(vertices[j], true);
			}
			for (int x = 0; x < vertexCount - 1; x++) {
				int a = Integer.parseInt(edgeValues[x]);
				int b = x + 1;
				graph.addEdge(vertices[a], vertices[b]);
			}
			graphs[i] = graph;
		}
		scanner.close();	
		EdmondsMaximumMatching edMatching = new EdmondsMaximumMatching();
		int count;
		System.out.println("Output:");
		for (Graph graphObj : graphs) {
			count = edMatching.executor(graphObj);
			System.out.println(count);
		}
	}
}
