package com.mustafaergan.byteland;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.mustafaergan.byteland.graph.GraphTest;
import com.mustafaergan.byteland.matching.EdmondsMaximumMatchingTest;

/**
 * Unit suit test for Byteland App.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
    GraphTest.class,
    EdmondsMaximumMatchingTest.class
})
public class ByteLandSuitTest{
}
