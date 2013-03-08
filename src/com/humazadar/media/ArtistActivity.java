package com.humazadar.media;

import java.util.ArrayList;
import android.widget.Toast;

import java.util.HashMap;

import com.humazadar.media.AndroidBuildingMusicPlayerActivity.State;

import android.app.Activity;
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

public class ArtistActivity extends ListActivity {

	// Songs list

	int songIndexArtist;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.playlist);

		String[] values = new String[] { "Mir Hasan Mir", "Artist 2", "Artist 3",
		"Artist 4" };

		// Adding menuItems to ListView
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);

		// selecting single ListView item
		ListView lv = getListView();
		// listening to single listitem click
		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				int artistIndex = position;

				// Starting new intent
				Intent in2 = new Intent(getApplicationContext(),
						SongActivity.class);

				startActivityForResult(in2, 50);

				setResult(RESULT_OK, in2);

				in2.putExtra("artistIndex", artistIndex);

				finish();

			}
		});
	}
	protected void onActivityResult(int requestCode,
			int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if(requestCode == 50 && resultCode == RESULT_OK)
		{
			songIndexArtist = data.getExtras().getInt("songIndex");
			
			Log.e(getClass().getName(), "songIndexArtist: "+ songIndexArtist);
			
			Log.i(getPackageName(), "result code in ArtistActivity: "+resultCode);
			Log.i(getPackageName(), "request code in ArtistActivity: "+requestCode);
			Log.i(getPackageName(), "songIndexArtist in ArtistActivity: "+songIndexArtist);
		}



	}
}