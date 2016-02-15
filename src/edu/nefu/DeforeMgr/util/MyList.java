package edu.nefu.DeforeMgr.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyList 
{
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList removeDuplicateWithOrder(ArrayList list) 
	{
        Set set = new HashSet();
        ArrayList newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext();) 
        {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        return newList;
     }
}
