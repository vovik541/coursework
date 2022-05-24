package com.coursework.graph.util;

import org.junit.jupiter.api.Test;

import static com.coursework.graph.util.Utility.*;
import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {

    private static final int TEST_NODE_ID = 1;
    private static final int TEST_BEGIN_NODE_ID = 1;
    private static final int TEST_END_NODE_ID = 2;

    @Test
    void transformNodeIdTest() {
        String result = transformNodeId(TEST_NODE_ID);
        assertEquals(result, "Node_1");
    }

    @Test
    void getIdFromEdgeFullIdTest() {
        int result = getIdFromEdgeFullId("Edge_1");
        assertEquals(result, TEST_NODE_ID);
    }

    @Test
    void transformEdgeIdWithAdvancedParamsTest() {
        String result = transformEdgeId(TEST_BEGIN_NODE_ID, String.valueOf(TEST_END_NODE_ID));
        assertEquals(result, "Edge_1_2");
    }

    @Test
    void transformEdgeIdWithPrimitivesParamsTest() {
        String result = transformEdgeId(TEST_BEGIN_NODE_ID, TEST_END_NODE_ID);
        assertEquals(result, "Edge_1_2");
    }

    @Test
    void transformTextIdTest() {
        String result = transformTextId(TEST_NODE_ID);
        assertEquals(result, "Text_1");
    }
}