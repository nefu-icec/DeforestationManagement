package edu.nefu.DeforeMgr.util;

import java.util.Random;

public class MyRandom 
{
	public static int [] getRandom(int need,int range)
	{
		int [] rands=new int[need];
		Random random=new Random ();  
        boolean[] bool=new boolean[range];
        int randInt=0;  
        for(int i=0;i<need;i++)
        {
        	do
        		randInt=random.nextInt(range);  
        	while(bool[randInt]);
        	bool[randInt]=true;  
        	rands[i]=randInt;
        }
        return rands;
	}
	
	public static void main(String[] args) 
	{
		int [] rands=getRandom(7, 9);
		for(int i:rands)
			System.out.println(i);
	}
}
