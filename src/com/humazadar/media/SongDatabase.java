package com.humazadar.media;

import java.util.ArrayList;

public class SongDatabase {

	ArrayList<String> groupItem = new ArrayList<String>();
	ArrayList<Object> childItem = new ArrayList<Object>();
	
	public SongDatabase()
	{
		groupItem.add("Mir Hasan Mir");
		groupItem.add("Artist 2");
		groupItem.add("Artist 3");
		groupItem.add("Artist 4");
		
		ArrayList<String> child = new ArrayList<String>();
		child.add("Mir Hasan Mir - Ae Wafadare Hussain - 2012");
		child.add("Ae Mere Be Kafan - 2012");
		child.add("Mekon Akbar Atta Kar");
		child.add("Ae Maa Tum Muje Pass Bula Lo");
		child.add("Veer Khbran Tuoon Na Liyan");
		child.add("Zawar Karbal Ham Ko Bana Diya");
		child.add("Sham E Gham Hai Karbal Men");
		child.add("Logo Bazar e Sham Ke");
		child.add("Arman Riya Arman Riya");
		childItem.add(child);
		
		
	}

	public ArrayList<String> getGroup()
	{
		return groupItem;
		
	}
	
	public ArrayList<Object> getChild(int index)
	{
		return childItem;
		//return getGroup().get(index);
		
	}
	
}
