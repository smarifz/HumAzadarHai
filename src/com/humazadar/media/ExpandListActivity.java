package com.humazadar.media;

import java.util.ArrayList;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

public class ExpandListActivity extends ExpandableListActivity implements
OnChildClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ExpandableListView expandbleLis = getExpandableListView();
		expandbleLis.setDividerHeight(2);
		expandbleLis.setGroupIndicator(null);
		expandbleLis.setClickable(true);
		expandbleLis.setOnChildClickListener(this);

		setGroupData();
		setChildGroupData();

		NewAdapter mNewAdapter = new NewAdapter(groupItem, childItem);
		mNewAdapter
		.setInflater(
				(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE),
				this);
		expandbleLis.setAdapter(mNewAdapter);

	}

	public void setGroupData() {
		groupItem.add("Mir Hasan Mir");
		groupItem.add("Shahid Baltistani");
		groupItem.add("Irfan Haider");
		groupItem.add("Qamber Zaidi");
	}

	ArrayList<String> groupItem = new ArrayList<String>();
	ArrayList<Object> childItem = new ArrayList<Object>();

	public void setChildGroupData() {
		/**
		 * Add Data For TecthNology
		 */
		ArrayList<String> child = new ArrayList<String>();
		child.add("Ae Wafadare Hussain");
		child.add("Ae Mere Be Kafan");
		child.add("Mekon Akbar Atta Kar");
		child.add("Ae Maa Tum Muje Pass Bula Lo");
		child.add("Veer Khbran Tuoon Na Liyan");
		child.add("Zawar Karbal Ham Ko Bana Diya");
		child.add("Sham E Gham Hai Karbal Men");
		child.add("Logo Bazar e Sham Ke");
		child.add("Arman Riya Arman Riya");
		childItem.add(child);

		/**
		 * Add Data For Mobile
		 */
		child = new ArrayList<String>();
		child.add("Fikar E Darvaish");
		child.add("Allah Jane Hay Hussain Jane");
		child.add("Kash Mera Koi Beta Hota");
		child.add("Sham Aa Raha Hai");
		child.add("Sakhi Abbas Alamdar");
		child.add("Hay Akbar De Vichore Ne");
		child.add("Sakina Ki Turbat");
		child.add("Ae Mere Bhai Raza");
		child.add("Qayamat Or Kia Ho Gi");
		childItem.add(child);
		/**
		 * Add Data For Manufacture
		 */
		child = new ArrayList<String>();
		child.add("Rahe Salamat Ishqe Hussain");
		child.add("Allah Jane Kesa Sabar Banda Hai");
		child.add("Aai Maqtal Men Shame Ghariban");
		child.add("Hay O Mera Veer Hussain");
		child.add("Ae Mere Asghar E Be Zuban");
		child.add("Sain Sughra");
		child.add("Maa Ho To Madere Hussain Si");
		child.add("Pathar Na Maro");
		childItem.add(child);
		/**
		 * Add Data For Extras
		 */
		child = new ArrayList<String>();
		child.add("Maqam Zainab Ka");
		child.add("Aagaeen Fatima");
		childItem.add(child);
	}


	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		
		return true;
	}
}