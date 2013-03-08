package com.humazadar.media;

public class TrackObject {

	String artist;
	String title;
	int year;
	String type;
	String URL;
	
	public TrackObject(String artist, String title,	int year,String type, String URL)
	{
		this.artist = artist;
		this.title = title;
		this.year = year;
		this.type = type;
		this.URL = URL;
	}

	
	public String getArtist()
	{
		return artist;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public int getYear()
	{
		return year;
	}
	
	public String getType()
	{
		return type;
	}
	
	public String getURL()
	{
		return URL;
	}
}
