package com.humazadar.media;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.widget.Toast;

public class mediaService extends Service implements OnBufferingUpdateListener, OnPreparedListener , OnCompletionListener{

	MediaPlayer mediaPlayer;
	String URL;
	private NotificationManager mNM;
	public final IBinder localBinder = new LocalBinder();

	// Unique Identification Number for the Notification.
	// We use it on Notification start, and to cancel it.
	private int NOTIFICATION = R.string.app_name;
	public boolean created = false;
	private boolean isRepeat;
	private boolean isShuffle;
	private SongsManager songManager;
	private ArrayList<TrackObject> songsList = new ArrayList<TrackObject>();
	private int currentSongIndex = 0;
	int percent = 0;
	private String songTitle;
	private String artist;
	NotificationCompat.Builder mBuilder;



	@Override
	public IBinder onBind(Intent intent) {

		return localBinder;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.e(getClass().getName(), "In onCreate");

		mediaPlayer = new MediaPlayer();

		//Listeners
		mediaPlayer.setOnCompletionListener(this);
		mediaPlayer.setOnPreparedListener(this);
		mediaPlayer.setOnBufferingUpdateListener(this);

		//Song Manger 
		songManager = new SongsManager();

		//Notifications
		mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

	}

	public class LocalBinder extends Binder 
	{
		mediaService getService()
		{
			return mediaService.this;
		}
	}

	public int onStartCommand(Intent intent, int flags, int startId) 
	{
		Log.e(getClass().getName(), "inside onStartCommand");

		//Get data from intent
		URL = intent.getExtras().getString("URL");
		isRepeat = intent.getExtras().getBoolean("isRepeat");
		//isShuffle = intent.getExtras().getBoolean("isShuffle");
		songTitle = intent.getExtras().getString("songTitle");
		artist = intent.getExtras().getString("artist");

		//sets data source and prepares
		initMP();

		return START_NOT_STICKY;

	}

	public void initMP()
	{
		Log.e(getClass().getName(), "inside initMP");

		if(mediaPlayer == null)
		{
			mediaPlayer = new MediaPlayer();

			//Listeners
			mediaPlayer.setOnCompletionListener(this);
			mediaPlayer.setOnPreparedListener(this);
			mediaPlayer.setOnBufferingUpdateListener(this);
		}
		else
			mediaPlayer.reset();

		
		try {
			mediaPlayer.setDataSource(URL);
		} catch (Exception e) {
			Log.e(getClass().getName(), "CANNOT SET DATASOURCE");
		}

		showNotification();

		mediaPlayer.prepareAsync();
	}

	public void onPrepared(MediaPlayer arg0) {
		Log.e(getClass().getName(), "inside onPrepared");
		startPlaying();
		created = true;

	}


	public void onCompletion(MediaPlayer arg0) {

		// check for repeat is ON or OFF
		if(isRepeat){
			// repeat is on play same song again
			startPlaying();
			Log.e(getClass().getName(), "Inside completion repeat");
		} 
		else
		{
			mediaPlayer.release();
		}


		//		else if(isShuffle){
		//			// shuffle is on - play a random song
		//			Random rand = new Random();
		//			currentSongIndex  = rand.nextInt((songsList.size() - 1) - 0 + 1) + 0;
		//			URL = songManager.getMainSongList().get(currentSongIndex).getURL();
		//			initMP();
		//			Log.e(getClass().getName(), "Inside completion shuffle");
		//		} 
		//		else
		//		{
		//			// no repeat or shuffle ON - play next song
		//			if(currentSongIndex < (songsList.size() - 1)){
		//				URL = songManager.getMainSongList().get(currentSongIndex+1).getURL();
		//				initMP();
		//			}else{
		//				// play first song
		//				currentSongIndex = 0;
		//				URL = songManager.getMainSongList().get(currentSongIndex).getURL();
		//				initMP();
		//			}
		//		}
	}

	public void startPlaying()
	{
		mediaPlayer.start();
		Log.e(getClass().getName(), "MP started playing");

	}

	public MediaPlayer getMediaPlayer()
	{
		return mediaPlayer;
	}

	public void pauseSong() {
		mediaPlayer.pause();
	}

	public void showNotification()
	{
		String desc = "Playing: "+ songTitle + " - " + artist;

		mBuilder =
				new NotificationCompat.Builder(this)
		.setSmallIcon(R.drawable.ic_launcher_web)
		.setContentTitle("Hum Azadar Hai")
		.setContentText("Click to go back to media player.");

		// Creates an explicit intent for an Activity in your app
		Intent resultIntent = new Intent(this, AndroidBuildingMusicPlayerActivity.class);
		resultIntent.setAction(Intent.ACTION_MAIN);
		resultIntent.addCategory(Intent.CATEGORY_LAUNCHER);

		// The stack builder object will contain an artificial back stack for the
		// started Activity.
		// This ensures that navigating backward from the Activity leads out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		// Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(AndroidBuildingMusicPlayerActivity.class);
		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent =
				stackBuilder.getPendingIntent(
						0,
						PendingIntent.FLAG_UPDATE_CURRENT
						);
		mBuilder.setContentIntent(resultPendingIntent);
		NotificationManager mNotificationManager =
				(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		// mId allows you to update the notification later on.
		mNotificationManager.notify(NOTIFICATION, mBuilder.build());

	}

	public void onDestroy() {
		super.onDestroy();
		//mediaPlayer.stop();
		mediaPlayer.release();
		mNM.cancel(NOTIFICATION);

	}

	@Override
	public void onBufferingUpdate(MediaPlayer mp, int percent) {
		// TODO Auto-generated method stub

		this.percent = percent;
		this.mediaPlayer = mp;
	}

	public int getPercent()
	{
		return percent;
	}

	//	private void showNotification() {
	//		// In this sample, we'll use the same text for the ticker and the expanded notification
	//		CharSequence appName = getText(R.string.app_name);
	//
	//		// Set the icon, scrolling text and timestamp
	//		Notification notification = new Notification(R.drawable.btn_play, appName,
	//				System.currentTimeMillis());
	//
	//		// The PendingIntent to launch our activity if the user selects this notification
	//		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
	//				new Intent(this, AndroidBuildingMusicPlayerActivity.class), 0);
	//
	//		// Set the info for the views that show in the notification panel.
	//		notification.setLatestEventInfo(this, appName ,
	//				"Playing: "+songManager.getMainSongList().get(currentSongIndex).getTitle(), contentIntent);
	//
	//		// Send the notification.
	//		mNM.notify(NOTIFICATION, notification);
	//	}



}