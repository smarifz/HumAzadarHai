package com.humazadar.media;

import java.util.ArrayList;
import java.util.HashMap;
 
public class SongsManager {

	private ArrayList<TrackObject> mhm = new ArrayList<TrackObject>();
	private ArrayList<TrackObject> sb = new ArrayList<TrackObject>();
	private ArrayList<TrackObject> ih = new ArrayList<TrackObject>();
	private ArrayList<TrackObject> q = new ArrayList<TrackObject>();
	
    // Constructor
    public SongsManager()
    {
    	String mhm_t0 = "http://arifzaidi.com/media/Mir_Hasan_Mir/Nohay/Ae_Wafadare_Hussain_1.mp3";
    	String mhm_t1 = "http://arifzaidi.com/media/Mir_Hasan_Mir/Nohay/Ae_Mere_Be_Kafar_2.mp3";
    	String mhm_t2 = "http://arifzaidi.com/media/Mir_Hasan_Mir/Nohay/Mekon_Akbar_Atta_Kar_3.mp3";
    	String mhm_t3 = "http://arifzaidi.com/media/Mir_Hasan_Mir/Nohay/Ae_Maa_Tum_Muje_Pass_Bula_Lo_4.mp3";
    	String mhm_t4 = "http://arifzaidi.com/media/Mir_Hasan_Mir/Nohay/Veer_Khbran_Tuoon_Na_Liyan_5.mp3";
    	String mhm_t5 = "http://arifzaidi.com/media/Mir_Hasan_Mir/Nohay/Zawar_Karbal_Ham_Ko_Bana_Diya_6.mp3";
    	String mhm_t6 = "http://arifzaidi.com/media/Mir_Hasan_Mir/Nohay/Sham_E_Gham_Hai_Karbal_Men_7.mp3";
    	String mhm_t7 = "http://arifzaidi.com/media/Mir_Hasan_Mir/Nohay/Logo_Bazar_e_Sham_Ke_8.mp3";
    	String mhm_t8 = "http://arifzaidi.com/media/Mir_Hasan_Mir/Nohay/Arman_Riya_Arman_Riya_9.mp3";
    	
    	//String artist, String track,	int year,String type, String URL
    	TrackObject mhm_obj0 = new TrackObject("Mir Hasan Mir", "Ae Wafadare Hussain - 2012", 2012, "noha", mhm_t0);
    	TrackObject mhm_obj1 = new TrackObject("Mir Hasan Mir", "Ae Mere Be Kafan - 2012", 2012, "noha", mhm_t1);
    	TrackObject mhm_obj2 = new TrackObject("Mir Hasan Mir", "Mekon Akbar Atta Kar - 2012", 2012, "noha", mhm_t2);
    	TrackObject mhm_obj3 = new TrackObject("Mir Hasan Mir", "Ae Maa Tum Muje Pass Bula Lo - 2012", 2012, "noha", mhm_t3);
    	TrackObject mhm_obj4 = new TrackObject("Mir Hasan Mir", "Veer Khbran Tuoon Na Liyan- 2012", 2012, "noha", mhm_t4);
    	TrackObject mhm_obj5 = new TrackObject("Mir Hasan Mir", "Zawar Karbal Ham Ko Bana Diya - 2012", 2012, "noha", mhm_t5);
    	TrackObject mhm_obj6 = new TrackObject("Mir Hasan Mir", "Sham E Gham Hai Karbal Men - 2012", 2012, "noha", mhm_t6);
    	TrackObject mhm_obj7 = new TrackObject("Mir Hasan Mir", "Logo Bazar e Sham Ke - 2012", 2012, "noha", mhm_t7);
    	TrackObject mhm_obj8 = new TrackObject("Mir Hasan Mir", "Arman Riya Arman Riya - 2012", 2012, "noha", mhm_t8);

    	
    	mhm.add(mhm_obj0);
    	mhm.add(mhm_obj1);
    	mhm.add(mhm_obj2);
    	mhm.add(mhm_obj3);
    	mhm.add(mhm_obj4);
    	mhm.add(mhm_obj5);
    	mhm.add(mhm_obj6);
    	mhm.add(mhm_obj7);
    	mhm.add(mhm_obj8);
    	
    	String sb_t0 = "http://arifzaidi.com/media/Shahhid_Baltistani/Nohay/Fikar_E_Darvaish_1.mp3";
    	String sb_t1 = "http://arifzaidi.com/media/Shahhid_Baltistani/Nohay/Allah_Jane_Hay_Hussain_Jane_2.mp3";
    	String sb_t2 = "http://arifzaidi.com/media/Shahhid_Baltistani/Nohay/Kash_Mera_Koi_Beta_Hota_3.mp3";
    	String sb_t3 = "http://arifzaidi.com/media/Shahhid_Baltistani/Nohay/Sham_Aa_Rha_Hai_4.mp3";
    	String sb_t4 = "http://arifzaidi.com/media/Shahhid_Baltistani/Nohay/Sakhi_Abbas_Alamdar_5.mp3";
    	String sb_t5 = "http://arifzaidi.com/media/Shahhid_Baltistani/Nohay/Hay_Akbar_De_Vichore_Ne_6.mp3";
    	String sb_t6 = "http://arifzaidi.com/media/Shahhid_Baltistani/Nohay/Sakina_Ki_Turbat_7.mp3";
    	String sb_t7 = "http://arifzaidi.com/media/Shahhid_Baltistani/Nohay/Ae_Mere_Bhai_Raza_8.mp3";
    	String sb_t8 = "http://arifzaidi.com/media/Shahhid_Baltistani/Nohay/Qayamat_Or_Kia_Ho_Gi_9.mp3";
    	
    	//String artist, String track,	int year,String type, String URL
    	TrackObject sb_obj0 = new TrackObject("Shahid Baltistani", "Fikar E Darvaish - 2012", 2012, "noha", sb_t0);
    	TrackObject sb_obj1 = new TrackObject("Shahid Baltistani", "Allah Jane Hay Hussain Jane - 2012", 2012, "noha", sb_t1);
    	TrackObject sb_obj2 = new TrackObject("Shahid Baltistani", "Kash Mera Koi Beta Hota - 2012", 2012, "noha", sb_t2);
    	TrackObject sb_obj3 = new TrackObject("Shahid Baltistani", "Sham Aa Raha Hai - 2012", 2012, "noha", sb_t3);
    	TrackObject sb_obj4 = new TrackObject("Shahid Baltistani", "Sakhi Abbas Alamdar - 2012", 2012, "noha", sb_t4);
    	TrackObject sb_obj5 = new TrackObject("Shahid Baltistani", "Hay Akbar De Vichore Ne - 2012", 2012, "noha", sb_t5);
    	TrackObject sb_obj6 = new TrackObject("Shahid Baltistani", "Sakina Ki Turbat - 2012", 2012, "noha", sb_t6);
    	TrackObject sb_obj7 = new TrackObject("Shahid Baltistani", "Ae Mere Bhai Raza - 2012", 2012, "noha", sb_t7);
    	TrackObject sb_obj8 = new TrackObject("Shahid Baltistani", "Qayamat Or Kia Ho Gi - 2012", 2012, "noha", sb_t8);

    	
    	sb.add(sb_obj0);
    	sb.add(sb_obj1);
    	sb.add(sb_obj2);
    	sb.add(sb_obj3);
    	sb.add(sb_obj4);
    	sb.add(sb_obj5);
    	sb.add(sb_obj6);
    	sb.add(sb_obj7);
    	sb.add(sb_obj8);
    	
    	
    	String ih_t0 = "http://arifzaidi.com/media/Irfan_Haider/Nohay/Rahe_Salamat_Ishqe_Hussain_1.mp3";
    	String ih_t1 = "http://arifzaidi.com/media/Irfan_Haider/Nohay/Allah_Jane_Kesa_Sabar_Banda_Hai_2.mp3";
    	String ih_t2 = "http://arifzaidi.com/media/Irfan_Haider/Nohay/Aai_Maqtal_Men_Shame_Ghariban_3.mp3";
    	String ih_t3 = "http://arifzaidi.com/media/Irfan_Haider/Nohay/Hay_O_Mera_Veer_Hussain_4.mp3";
    	String ih_t4 = "http://arifzaidi.com/media/Irfan_Haider/Nohay/Ae_Mere_Asghar_e_Be_Zuban_5.mp3";
    	String ih_t5 = "http://arifzaidi.com/media/Irfan_Haider/Nohay/Sain_Sughra_6.mp3.mp3";
    	String ih_t6 = "http://arifzaidi.com/media/Irfan_Haider/Nohay/Maa_Ho_To_Madere_Hussain_Si_7.mp3";
    	String ih_t7 = "http://arifzaidi.com/media/Irfan_Haider/Nohay/Pathar_Na_Maro_8.mp3";
    	
    	//String artist, String track,	int year,String type, String URL
    	TrackObject ih_obj0 = new TrackObject("Irfan Haider", "Rahe Salamat Ishqe Hussain - 2012", 2012, "noha", ih_t0);
    	TrackObject ih_obj1 = new TrackObject("Irfan Haider", "Allah Jane Kesa Sabar Banda Hai - 2012", 2012, "noha", ih_t1);
    	TrackObject ih_obj2 = new TrackObject("Irfan Haider", "Aai Maqtal Men Shame Ghariban - 2012", 2012, "noha", ih_t2);
    	TrackObject ih_obj3 = new TrackObject("Irfan Haider", "Hay O Mera Veer Hussain - 2012", 2012, "noha", ih_t3);
    	TrackObject ih_obj4 = new TrackObject("Irfan Haider", "Ae Mere Asghar E Be Zuban - 2012", 2012, "noha", ih_t4);
    	TrackObject ih_obj5 = new TrackObject("Irfan Haider", "Sain Sughra - 2012", 2012, "noha", ih_t5);
    	TrackObject ih_obj6 = new TrackObject("Irfan Haider", "Maa Ho To Madere Hussain Si - 2012", 2012, "noha", ih_t6);
    	TrackObject ih_obj7 = new TrackObject("Irfan Haider", "Pathar Na Maro - 2012", 2012, "noha", ih_t7);

    	
    	ih.add(ih_obj0);
    	ih.add(ih_obj1);
    	ih.add(ih_obj2);
    	ih.add(ih_obj3);
    	ih.add(ih_obj4);
    	ih.add(ih_obj5);
    	ih.add(ih_obj6);
    	ih.add(ih_obj7);

    	
    	String qz_t0 = "http://arifzaidi.com/media/Qamber_Zaidi/Manqabat/Maqam_Zainab_Ka.mp3";
    	String qz_t1 = "http://arifzaidi.com/media/Qamber_Zaidi/Manqabat/Aagaeen_Fatima.mp3";
    	
    	
    	//String artist, String track,	int year,String type, String URL
    	TrackObject qz_obj0 = new TrackObject("Qamber Zaidi", "Maqam Zainab Ka", 2012, "manqabat", qz_t0);
     	TrackObject qz_obj1 = new TrackObject("Qamber Zaidi", "Aagaeen Fatima", 2012, "manqabat", qz_t1);

    	
    	q.add(qz_obj0);
    	q.add(qz_obj1);
    	

        
    	
    }
 
    //Get Mir Hasan Mir 
    public ArrayList<TrackObject> getMHM(){
    	
        return mhm;
    }
    
    //Get Shahid Baltistani
    public ArrayList<TrackObject> getSB(){
    	return sb;
    }
    
    //Get Irfan Haider
    public ArrayList<TrackObject> getIH(){
    	return ih;
    }
    
    public ArrayList<TrackObject> getQ(){
    	return q;
    }
    
 }