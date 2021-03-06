package com.humazadar.media;

import java.util.ArrayList;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class SongActivity extends ListActivity {

	// Songs list
	public ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.playlist);

		//ArrayList that goes into the Adapter
		final ArrayList<HashMap<String, String>> songsListData = new ArrayList<HashMap<String, String>>();

		SongsManager plm = new SongsManager();
		// get all songs from sdcard
		//this.songsList = plm.getPlayList();

		// looping through playlist
		for (int i = 0; i < songsList.size(); i++) {
			// creating new HashMap
			HashMap<String, String> song = songsList.get(i);

			// adding HashList to ArrayList
			songsListData.add(song);
		}

		// Adding menuItems to ListView
		ListAdapter adapter = new SimpleAdapter(this, songsListData,
				R.layout.playlist_item, new String[] { "songTitle" }, new int[] {
				R.id.songTitle });

		setListAdapter(adapter);

		// selecting single ListView item
		ListView lv = getListView();
		// listening to single listitem click
		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {


				Toast.makeText(getApplicationContext(), "DOWNLOADING, PLEASE WAIT", Toast.LENGTH_LONG).show(); 

				// getting listitem index
				int songsIndex = position;

				// Starting new intent
				Intent in3 = new Intent(getApplicationContext(),
						AndroidBuildingMusicPlayerActivity.class);

				// Sending songIndex to PlayerActivity
				in3.putExtra("songsIndex", songsIndex);

				setResult(RESULT_OK, in3);

				// Closing PlayListView
				finish();
			}
		});
	}
}