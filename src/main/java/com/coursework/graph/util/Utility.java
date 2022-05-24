package com.coursework.graph.util;
public class Utility {
    public static String transformNodeId(int id){
        return "Node_" + id;
    }
    public static int getIdFromEdgeFullId(String full){
        String[] split = full.split("_");
        return split.length - 1;
    }
    public static String transformEdgeId(int beginNodeId, int endNodeId){
        return "Edge_" + beginNodeId + "_" + endNodeId;
    }
    public static String transformEdgeId(int beginNodeId, String endNodeId){
        return "Edge_" + beginNodeId + "_" + endNodeId;
    }
    public static String transformTextId(int id){
        return "Text_" + id;
    }
}
