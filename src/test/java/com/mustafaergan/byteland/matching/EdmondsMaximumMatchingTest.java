package com.mustafaergan.byteland.matching;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.mustafaergan.byteland.TestBase;
import com.mustafaergan.byteland.graph.Graph;

public class EdmondsMaximumMatchingTest extends TestBase {
	
    @Test
    public void test_executorA() {
    	Graph graph = createGraphA();
    	EdmondsMaximumMatching edmondsMaximumMatching = new EdmondsMaximumMatching();
    	assertTrue(edmondsMaximumMatching.executor(graph) == 5);
    }
    
    @Test
    public void test_executorB() {
    	Graph graph = createGraphB();
    	EdmondsMaximumMatching edmondsMaximumMatching = new EdmondsMaximumMatching();
    	assertTrue(edmondsMaximumMatching.executor(graph) == 4);
    }
    
    @Test
    public void test_executorC() {
    	Graph graph = createGraphD();
    	EdmondsMaximumMatching edmondsMaximumMatching = new EdmondsMaximumMatching();
    	assertTrue(edmondsMaximumMatching.executor(graph) == 5);
    }

}
