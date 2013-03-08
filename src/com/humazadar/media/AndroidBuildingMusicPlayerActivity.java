package com.humazadar.media;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.humazadar.media.mediaService.LocalBinder;

public class AndroidBuildingMusicPlayerActivity extends Activity implements SeekBar.OnSeekBarChangeListener {

	private ImageButton btnPlay;
	private ImageButton btnForward;
	private ImageButton btnBackward;
	private ImageButton btnNext;
	private ImageButton btnPrevious;
	private ImageButton btnPlaylist;
	private ImageButton btnRepeat;
	private ImageButton btnShuffle;
	private SeekBar songProgressBar;
	private TextView songArtistLabel;
	private TextView songTitleLabel;
	private TextView songCurrentDurationLabel;
	private TextView songTotalDurationLabel;
	private ImageView bgImg;

	// Media Player
	//private  MediaPlayer mp;
	// Handler to update UI timer, progress bar etc,.
	private Handler mHandler = new Handler();;
	private SongsManager songManager;
	private Utilities utils;
	private int seekForwardTime = 5000; // 5000 milliseconds
	private int seekBackwardTime = 5000; // 5000 milliseconds
	private int currentSongIndex; 
	private boolean isShuffle = false;
	private boolean isRepeat = false;
	private ArrayList<TrackObject> songsList = new ArrayList<TrackObject>();
	String URL;
	int songIndex;
	Intent pbsi;
	private mediaService mp3Service;
	private String songTitle;
	private String artist;
	private String defaultURL;

	int childP;
	int groupP;

	enum State {
		Retrieving, // the MediaRetriever is retrieving music
		Stopped, // media player is stopped and not prepared to play
		Preparing, // media player is preparing...
		Playing, // playback active (media player ready!). (but the media player may actually be
		// paused in this state if we don't have audio focus. But we stay in this state
		// so that we know we have to resume playback once we get focus back)
		Paused // playback paused (media player ready!)
	};
	State mState = State.Retrieving;

	private ServiceConnection mediaServiceConnection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName arg0, IBinder binder) 
		{
			mp3Service = ((LocalBinder) binder).getService();
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) 
		{
		}

	};

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.player);

		// All player buttons
		btnPlay = (ImageButton) findViewById(R.id.btnPlay);
		//btnForward = (ImageButton) findViewById(R.id.btnForward);
		//btnBackward = (ImageButton) findViewById(R.id.btnBackward);
		btnNext = (ImageButton) findViewById(R.id.btnNext);
		btnPrevious = (ImageButton) findViewById(R.id.btnPrevious);
		btnPlaylist = (ImageButton) findViewById(R.id.btnPlaylist);
		btnRepeat = (ImageButton) findViewById(R.id.btnRepeat);
		btnShuffle = (ImageButton) findViewById(R.id.btnShuffle);
		songProgressBar = (SeekBar) findViewById(R.id.songProgressBar);
		songArtistLabel = (TextView) findViewById(R.id.songArtist);
		songTitleLabel = (TextView) findViewById(R.id.songTitle);
		songCurrentDurationLabel = (TextView) findViewById(R.id.songCurrentDurationLabel);
		songTotalDurationLabel = (TextView) findViewById(R.id.songTotalDurationLabel);
		bgImg = (ImageView) findViewById(R.id.background_main_big);


		songManager = new SongsManager();
		utils = new Utilities();

		defaultURL = "http://arifzaidi.com/media/Shahhid_Baltistani/Nohay/Kash_Mera_Koi_Beta_Hota_3.mp3";


		// Listeners
		songProgressBar.setOnSeekBarChangeListener(this); // Important

		// Getting all songs list
		songsList = songManager.getIH();

		// By default play first song
		playSong(2);

		/**
		 * Play button click event
		 * plays a song and changes button to pause image
		 * pauses a song and changes button to play image
		 * */
		btnPlay.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// check for already playing
				if(mp3Service.getMediaPlayer().isPlaying())
				{
					if(mp3Service.getMediaPlayer()!=null)
					{
						mp3Service.pauseSong();
						// Changing button image to play button
						btnPlay.setImageResource(R.drawable.btn_play);
						mState = State.Playing;
					}
				}
				else
				{
					// Resume song
					if(mp3Service.getMediaPlayer()!=null)
					{
						mp3Service.startPlaying();
						// Changing button image to pause button
						btnPlay.setImageResource(R.drawable.btn_pause);
						mState = State.Paused;
					}
					else
					{

					}

				}


			}
		});

		/**
		 * Forward button click event
		 * Forwards song specified seconds
		 * */
		//		btnForward.setOnClickListener(new View.OnClickListener() {
		//
		//
		//			public void onClick(View arg0) {
		//				// get current song position				
		//				int currentPosition = mp3Service.getMediaPlayer().getCurrentPosition();
		//				// check if seekForward time is lesser than song duration
		//				if(currentPosition + seekForwardTime <= mp3Service.getMediaPlayer().getDuration()){
		//					// forward song
		//					mp3Service.getMediaPlayer().seekTo(currentPosition + seekForwardTime);
		//				}else{
		//					// forward to end position
		//					mp3Service.getMediaPlayer().seekTo(mp3Service.getMediaPlayer().getDuration());
		//				}
		//			}
		//		});

		/**
		 * Backward button click event
		 * Backward song to specified seconds
		 * */
		//				btnBackward.setOnClickListener(new View.OnClickListener() {
		//		
		//		
		//					public void onClick(View arg0) {
		//						// get current song position				
		//						int currentPosition = mp3Service.getMediaPlayer().getCurrentPosition();
		//						// check if seekBackward time is greater than 0 sec
		//						if(currentPosition - seekBackwardTime >= 0){
		//							// forward song
		//							mp3Service.getMediaPlayer().seekTo(currentPosition - seekBackwardTime);
		//						}else{
		//							// backward to starting position
		//							mp3Service.getMediaPlayer().seekTo(0);
		//						}
		//		
		//					}
		//				});

		/**
		 * Next button click event
		 * Plays next song by taking currentSongIndex + 1
		 * */
		btnNext.setOnClickListener(new View.OnClickListener() {


			public void onClick(View arg0) {
				// check if next song is there or not
				if(currentSongIndex < (songsList.size() - 1)){
					playSong(currentSongIndex + 1);
					currentSongIndex = currentSongIndex + 1;
					updateSongAndArtist();
				}else{
					// play first song
					playSong(0);
					currentSongIndex = 0;
					updateSongAndArtist();
				}

			}
		});

		/**
		 * Back button click event
		 * Plays previous song by currentSongIndex - 1
		 * */
		btnPrevious.setOnClickListener(new View.OnClickListener() {


			public void onClick(View arg0) {
				if(currentSongIndex > 0){
					playSong(currentSongIndex - 1);
					currentSongIndex = currentSongIndex - 1;
					updateSongAndArtist();
				}else{
					// play last song
					playSong(songsList.size() - 1);
					currentSongIndex = songsList.size() - 1;
					updateSongAndArtist();
				}

			}
		});

		/**
		 * Button Click event for Repeat button
		 * Enables repeat flag to true
		 * */
		btnRepeat.setOnClickListener(new View.OnClickListener() {


			public void onClick(View arg0) {
				if(isRepeat){
					isRepeat = false;
					Toast.makeText(getApplicationContext(), "Repeat is OFF", Toast.LENGTH_SHORT).show();
					btnRepeat.setImageResource(R.drawable.btn_repeat);
				}else{
					// make repeat to true
					isRepeat = true;
					Toast.makeText(getApplicationContext(), "Repeat is ON", Toast.LENGTH_SHORT).show();
					// make shuffle to false
					isShuffle = false;
					btnRepeat.setImageResource(R.drawable.btn_repeat_focused);
					btnShuffle.setImageResource(R.drawable.btn_shuffle);
				}	
			}
		});

		/**
		 * Button Click event for Shuffle button
		 * Enables shuffle flag to true
		 * */
		btnShuffle.setOnClickListener(new View.OnClickListener() {


			public void onClick(View arg0) {
				if(isShuffle){
					isShuffle = false;
					Toast.makeText(getApplicationContext(), "Shuffle is OFF", Toast.LENGTH_SHORT).show();
					btnShuffle.setImageResource(R.drawable.btn_shuffle);
				}else{
					// make repeat to true
					isShuffle= true;
					Toast.makeText(getApplicationContext(), "Shuffle is ON", Toast.LENGTH_SHORT).show();
					// make shuffle to false
					isRepeat = false;
					btnShuffle.setImageResource(R.drawable.btn_shuffle_focused);
					btnRepeat.setImageResource(R.drawable.btn_repeat);
				}	
			}
		});

		/**
		 * Button Click event for Play list click event
		 * Launches list activity which displays list of songs
		 * */
		btnPlaylist.setOnClickListener(new View.OnClickListener() {


			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(), ExpandListActivity.class);
				startActivityForResult(i, 100);

			}
		});

	}


	//RECIEVING GROUP POSITION AND CHILD POSITOIN FROM THE PLAYLIST
	protected void onActivityResult(int requestCode,
			int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 100)
		{

			if(resultCode == RESULT_CANCELED)
			{
				Log.i(this.getCallingPackage(), "resultCode == canceled");
			}
			else
			{
				mState = State.Playing;

				groupP = data.getExtras().getInt("groupPosition");
				childP = data.getExtras().getInt("childPosition");

				//Assign proper song list depending on the group child and change background picture
				if(groupP == 0)
				{
					songsList = songManager.getMHM();
					bgImg.setImageResource(R.drawable.mhm);
				}
				else if(groupP == 1)
				{
					songsList = songManager.getSB();
					bgImg.setImageResource(R.drawable.sb);
				}
				else if(groupP == 2)
				{
					songsList = songManager.getIH();
					bgImg.setImageResource(R.drawable.ih);
				}
				else if(groupP == 3)
				{
					songsList = songManager.getQ();
					bgImg.setImageResource(R.drawable.qz);
				}


				currentSongIndex = childP;

				songIndex = currentSongIndex;

				// play selected song
				playSong(currentSongIndex);

				//Updates song name and artist name
				updateSongAndArtist();

			}
		}
	}

	//	PLAY THE SONG
	public void  playSong(int songIndex)
	{
		mState = State.Preparing;

		// Intent (data) to send to the service class
		pbsi = new Intent(this, mediaService.class);

		// Setting URL
		URL = songsList.get(songIndex).getURL();

		// Changing Button Image to pause image
		btnPlay.setImageResource(R.drawable.btn_pause);

		//Send URL and repeat
		pbsi.putExtra("URL", URL);
		pbsi.putExtra("isRepeat", isRepeat);
		//pbsi.putExtra("isShuffle", isShuffle);
		pbsi.putExtra("songTitle", songTitle);
		pbsi.putExtra("artist", artist);

		//Start service
		startService(pbsi);
		Intent connectionIntent = new Intent(this, mediaService.class);
		bindService(connectionIntent, mediaServiceConnection,
				Context.BIND_AUTO_CREATE);

		updateSeekbar();
		Log.e(getClass().getName(), "inside playSong");

		mState = State.Playing;

	}


	public void updateSeekbar() 
	{
		mState = State.Playing;

		// set Progress bar values
		songProgressBar.setProgress(0);
		songProgressBar.setMax(100);

		// Updating progress bar
		updateProgressBar();	

	}

	/**
	 * Update timer on seekbar
	 * */
	public void updateProgressBar() {
		mHandler.postDelayed(mUpdateTimeTask, 100);

	}	


	/**
	 * Background Runnable thread
	 * */
	private Runnable mUpdateTimeTask = new Runnable() {
		public void run() {


			long totalDuration = 0;
			long currentDuration = 0;
			int percent = 0;

			if(mp3Service.getMediaPlayer().isPlaying())	
			{
				try {
					totalDuration = mp3Service.getMediaPlayer().getDuration();
					currentDuration = mp3Service.getMediaPlayer().getCurrentPosition();
					percent = mp3Service.getPercent();
				} catch (NullPointerException e) {
					Log.e(getClass().getName(), "NULL POINTER HANDLED!!!!");
					updateSeekbar();
				} 
			}


			// Displaying Total Duration time
			songTotalDurationLabel.setText(""+utils.milliSecondsToTimer(totalDuration));
			// Displaying time completed playing
			songCurrentDurationLabel.setText(""+utils.milliSecondsToTimer(currentDuration));

			// Updating progress bar
			int progress = (int)(utils.getProgressPercentage(currentDuration, totalDuration));
			//Log.d("Progress", ""+progress);
			songProgressBar.setProgress(progress);
			songProgressBar.setSecondaryProgress(percent);


			// Running this thread after 100 milliseconds
			mHandler.postDelayed(this, 100);
		}
	};

	/**
	 * 
	 * */


	public void updateSongAndArtist()
	{
		// Displaying Song title and artist
		songTitle = songsList.get(currentSongIndex).getTitle();
		songTitleLabel.setText(songTitle);
		artist = songsList.get(currentSongIndex).getArtist();
		songArtistLabel.setText(artist);
	}

	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {

	}

	/**
	 * When user starts moving the progress handler
	 * */

	public void onStartTrackingTouch(SeekBar seekBar) {
		// remove message Handler from updating progress bar
		mHandler.removeCallbacks(mUpdateTimeTask);
	}

	/**
	 * When user stops moving the progress hanlder
	 * */

	public void onStopTrackingTouch(SeekBar seekBar) {
		mHandler.removeCallbacks(mUpdateTimeTask);
		int totalDuration = mp3Service.getMediaPlayer().getDuration();
		int currentPosition = utils.progressToTimer(seekBar.getProgress(), totalDuration);

		// forward or backward to certain seconds
		mp3Service.getMediaPlayer().seekTo(currentPosition);

		// update timer progress again
		updateProgressBar();
	}

	@Override
	public void onBackPressed() {
		Log.i(this.getCallingPackage(), "back button pressed");
	}

	public void onDestroy(){
		super.onDestroy();
		//		mp3Service.getMediaPlayer().release();
		//      stopService(pbsi);
		Log.e(getClass().getName(), "main activity destroyed");



		/**
		 * On Song Playing completed
		 * if repeat is ON play same song again
		 * if shuffle is ON play random song
		 * */

		//	public void onCompletion(MediaPlayer arg0) {
		//
		//		// check for repeat is ON or OFF
		//		if(isRepeat){
		//			// repeat is on play same song again
		//			playSong(currentSongIndex);
		//		} else if(isShuffle){
		//			// shuffle is on - play a random song
		//			Random rand = new Random();
		//			currentSongIndex = rand.nextInt((songsList.size() - 1) - 0 + 1) + 0;
		//			playSong(currentSongIndex);
		//		} else{
		//			// no repeat or shuffle ON - play next song
		//			if(currentSongIndex < (songsList.size() - 1)){
		//				playSong(currentSongIndex + 1);
		//				currentSongIndex = currentSongIndex + 1;
		//			}else{
		//				// play first song
		//				playSong(0);
		//				currentSongIndex = 0;
		//			}
		//		}
		//	}



	}
}