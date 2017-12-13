package com.mustafaergan.byteland.matching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mustafaergan.byteland.graph.Edge;
import com.mustafaergan.byteland.graph.Graph;
import com.mustafaergan.byteland.graph.Vertex;


/**
 * This is a Java implementation of Edmond's algorithm
 * to find the minimum spanning tree in a directed graph.
 * 
 * The algorithm takes as input a directed graph D = {V, E} 
 * where V is the set of nodes and E is the set of directed edges, 
 * a distinguished vertex r in V called the root, and a real-valued weight w(e) 
 * for each edge in E. It returns a spanning arborescence 
 * A rooted at r of minimum weight, where the weight of an arborescence is defined to be the sum of its edge weights, 
 * w(A) = sum{e in A}{w(e)}.
 * 
 * 
 * @author mustafa.ergan
 * @date 2017/12/13
 *
 */
public class EdmondsMaximumMatching {
	
	/**
	 * shape continuous join algorithm 
	 * and this process continues until the number of edge is exhausted
	 * 
	 * @param graph is containing cities which will be merge
	 * @return number of merger test
	 */
	public int executor(Graph graph) {
		int control = 0;
		List<Vertex> vertices;
		int[] match;
		List<Integer>[] g;
		while (graph.getVertex().size() > 1) {
			control++;
			g = convertGraph(graph);
			match = myMaxMatching(g);
			findMinStep(match, graph);
			vertices = graph.getVertex();
			graph.rename();
			vertices = graph.getVertex();
		}
		return control;
	}
	
	/**
	 * According to the Edmonds algorithm, it finds logical conjunctions
	 * combine the relevant vertices
	 * @param match Edmonds algorithm result
	 * @param graph created with entries
	 */
	private void findMinStep(int[] match, Graph graph) {
		for (int i = 0; i < match.length; i++) {
			if (match[i] != -1) {
				Vertex vertexOut = graph.getVertex("" + match[i]);
				for (int j = i + 1; j < match.length; j++) {
					Vertex vertexIn = graph.getVertex("" + match[j]);
					if (match[j] != -1) {
						if (graph.isNeighborhood(vertexOut, vertexIn)) {
							match[i] = -1;
							match[j] = -1;
							graph.mergeVertex(vertexOut, vertexIn);
						}
					}
				}
			}
		}
	}


	/**
	 * gives the best match status as a array
	 * @param graph to match
	 * @return matched array
	 */
	private int[] myMaxMatching(List<Integer>[] graph) {
		int n = graph.length;
		int[] match = new int[n];
		Arrays.fill(match, -1);
		int[] p = new int[n];
		for (int i = 0; i < n; ++i) {
			if (match[i] == -1) {
				int v = findPath(graph, match, p, i);
				while (v != -1) {
					int pv = p[v];
					int ppv = match[pv];
					match[v] = pv;
					match[pv] = v;
					v = ppv;
				}
			}
		}
		return match;
	}

	/**
	 * my type is translated into the existing Edmonds algorithm type
	 * @param graph 
	 * @return Edmonds array type
	 */
	private List<Integer>[] convertGraph(Graph graph) {
		List<Integer>[] g = new List[graph.getVertex().size()];
		for (int i = 0; i < graph.getVertex().size(); i++) {
			for (int j = 0; j < graph.getVertex().size(); j++) {
				g[j] = new ArrayList<Integer>();
				for (Edge edge : graph.getVertex(String.valueOf(j)).getNeighbors()) {
					if (!(graph.getVertex(String.valueOf(j)).getName().equals(edge.getStart().getName()))) {
						g[j].add(Integer.parseInt(edge.getStart().getName()));
					} else {
						g[j].add(Integer.parseInt(edge.getEnd().getName()));
					}
				}
			}
		}
		return g;
	}


	private int findPath(List<Integer>[] graph, int[] match, int[] p, int root) {
		int n = graph.length;
		boolean[] used = new boolean[n];
		Arrays.fill(p, -1);
		int[] base = new int[n];
		for (int i = 0; i < n; ++i)
			base[i] = i;
		used[root] = true;
		int qh = 0;
		int qt = 0;
		int[] q = new int[n];
		q[qt++] = root;
		while (qh < qt) {
			int v = q[qh++];

			for (int to : graph[v]) {
				if (base[v] == base[to] || match[v] == to)
					continue;
				if (to == root || match[to] != -1 && p[match[to]] != -1) {
					int curbase = lca(match, base, p, v, to);
					boolean[] blossom = new boolean[n];
					markPath(match, base, blossom, p, v, curbase, to);
					markPath(match, base, blossom, p, to, curbase, v);
					for (int i = 0; i < n; ++i)
						if (blossom[base[i]]) {
							base[i] = curbase;
							if (!used[i]) {
								used[i] = true;
								q[qt++] = i;
							}
						}
				} else if (p[to] == -1) {
					p[to] = v;
					if (match[to] == -1)
						return to;
					to = match[to];
					used[to] = true;
					q[qt++] = to;
				}
			}
		}
		return -1;
	}
	
	private void markPath(int[] match, int[] base, boolean[] blossom, int[] p, int v, int b, int children) {
		for (; base[v] != b; v = p[match[v]]) {
			blossom[base[v]] = blossom[base[match[v]]] = true;
			p[v] = children;
			children = match[v];
		}
	}
	
	private int lca(int[] match, int[] base, int[] p, int a, int b) {
		boolean[] used = new boolean[match.length];
		while (true) {
			a = base[a];
			used[a] = true;
			if (match[a] == -1)
				break;
			a = p[match[a]];
		}
		while (true) {
			b = base[b];
			if (used[b])
				return b;
			b = p[match[b]];
		}
	}
}
