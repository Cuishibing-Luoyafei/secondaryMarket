package com.secondaryMarket.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test001 {
	
	public static void main(String[] args) {
		String[] strs = {"0","1"};
		List<String[]> listStr = new ArrayList<String[]>();
		for(int i = 0; i < 2; i++) {
			listStr.add(strs);
		}
		Iterator<String[]> items = listStr.iterator();
		List<String> res = new ArrayList<String>();
		while(items.hasNext()) {
			for(String s:items.next()) {
				//System.out.println(s);
				res.add(s);
			}
		}
		String[] ssss = new String[res.size()];
		for(int i = 0; i < res.size(); i++) {
			ssss[i] = res.get(i);
		}
		int len = ssss.length;
		getReturn(ssss, len);
	}
	
	
	public static void getReturn(String[] ssss, int len) {
		if(len == 0) {
			return;
		}
			
		for(int i = 0; i < ssss.length; i++) {
			String s = ssss[i];
			System.out.print(s);
			getReturn(ssss, len-1);
			System.out.println();
		}
	}
	
	 public static int Puzzle1(int[] a) {
	        int sum = 0;
	        for(int i : a) {
	            sum += i;
	        }
	        return sum;
	    }
	
	
	 public static int[] Puzzle(float[] a) {
		 
		int k = 0;
        int[] m = new int[a.length];
        for(float i : a) {
        	int temp = 0;
        	if(i > 0)
        		temp = (int) ((i*10 + 5)/10);
        	else if(i < 0)
        		temp = (int) ((i*10 - 5)/10); 
        	else
        		temp = 0;
            m[k++] = temp;
	     }
        return m;
	 }
	 
	 
}
