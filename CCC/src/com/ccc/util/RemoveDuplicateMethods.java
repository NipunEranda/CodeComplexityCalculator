package com.ccc.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

import com.ccc.model.Line;
 
public class RemoveDuplicateMethods 
{
    public ArrayList<Line> initiate(ArrayList<Line> lineSet) {
        // ArrayList with duplicate elements
 
        LinkedHashSet<Line> hashSet = new LinkedHashSet<>(lineSet);
         
        ArrayList<Line> listWithoutDuplicates = new ArrayList<>(hashSet);
         
        return listWithoutDuplicates;
    }
}
