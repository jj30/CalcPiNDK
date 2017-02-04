package com.buildingfive.hwndk;
 
import java.text.NumberFormat;
import java.util.Locale;

import com.hw.ndk.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;  
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView; 
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast; 

public class HWNDKActivity extends Activity {
    final static String pi = "3.1415926535897932384";
	
    static {  
	    System.loadLibrary("ndk1");  
	} 
	
	private static String formatNS(long ns) {
		NumberFormat nf = NumberFormat.getInstance(Locale.US); 
		return nf.format(ns);
	}
	
	private static String right(String strTest) {
		if (strTest.equals(""))
			return "";
		
		String strReturn = ""; 
		for (int i = 0; i < strTest.length(); i++) {
			if (!strTest.substring(i, i + 1).equals(pi.substring(i, i + 1))) {
				break;
			} else {
				strReturn += strTest.substring(i, i + 1);
			}
		} 
		
		return strReturn;
	}
	
	private static String wrong(String strTest) {
		return strTest.replace(right(strTest), "");
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TextView tv =(TextView)findViewById(R.id.textView1);
        TextView tv2 =(TextView)findViewById(R.id.textView2);
        TextView tv3 =(TextView)findViewById(R.id.textView3);
        TextView tv4 =(TextView)findViewById(R.id.textView4);
        final TextView tv5 =(TextView)findViewById(R.id.textView5);
        final TextView tv6 =(TextView)findViewById(R.id.textView6);
        final TextView tv7 =(TextView)findViewById(R.id.textView7);
        
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/PressStart2P-Regular.ttf");
        tv.setTextSize(12);
        tv2.setTextSize(12);
        tv3.setTextSize(12);
        tv4.setTextSize(12);
        tv5.setTextSize(12);
        tv6.setTextSize(12);
        tv7.setTextSize(12);
        tv.setTypeface(face);
        tv2.setTypeface(face);
        tv3.setTypeface(face);
        tv4.setTypeface(face);
        tv5.setTypeface(face);
        tv6.setTypeface(face);
        tv7.setTypeface(face);
        
        Button btnBellardJava = (Button) findViewById(R.id.btnBellardJava);
        Button btnBellardC = (Button) findViewById(R.id.btnBellardC);
        Button btnChudnovskyJava = (Button) findViewById(R.id.btnChudnovskyJava);
        Button btnChudnovskyC = (Button) findViewById(R.id.btnChudnovskyC);
        Button btnGLJava = (Button) findViewById(R.id.btnGLJava);
        Button btnGLC = (Button) findViewById(R.id.btnGLC);
        Button btnLeibnizJava = (Button) findViewById(R.id.btnLeibnizJava);
        Button btnLeibnizC = (Button) findViewById(R.id.btnLeibnizC);
        Button btnShowScores = (Button) findViewById(R.id.btnShowScores);

        //btnBellardJava.setTextSize(12);
        //btnBellardJava.setTypeface(face);
        //btnBellardC.setTextSize(12);
        //btnBellardC.setTypeface(face);
        final DBHelper db = new DBHelper(HWNDKActivity.this);
		        
        btnBellardJava.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View arg0) {
        		long timeBegin = System.nanoTime();
        		String piBellards = Double.toString(Pi.bellards(5));
        		long timeEnd = System.nanoTime();
        		long lElapsed = timeEnd - timeBegin;
        		String elapsed = String.valueOf(formatNS(timeEnd - timeBegin)) + " ns";
        		
        		db.saveScore("Bellard's Java", String.valueOf(lElapsed), elapsed);

        		Log.v("BellardJavaPI", piBellards);
        		tv5.setText(right(piBellards));
        		tv6.setText(wrong(piBellards));
        		tv7.setText(elapsed);
 			}
 		});        
        
        btnBellardC.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View arg0) {
        		long timeBegin = System.nanoTime();
        		String piBellards = getBellardsPi(5); 
        		long timeEnd = System.nanoTime();
        		long lElapsed = timeEnd - timeBegin;
        		String elapsed = String.valueOf(formatNS(timeEnd - timeBegin)) + " ns";
        		
        		db.saveScore("Bellard's C/C++", String.valueOf(lElapsed), elapsed);

        		Log.v("BellardCPI", piBellards);
        		tv5.setText(right(piBellards));
        		tv6.setText(wrong(piBellards));
        		tv7.setText(elapsed);
 			}
 		});        
        btnChudnovskyJava.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View arg0) {
        		long timeBegin = System.nanoTime();
        		String piChudnovsky = Double.toString(Pi.chudnovsky(4));
        		long timeEnd = System.nanoTime();
        		long lElapsed = timeEnd - timeBegin;
        		String elapsed = String.valueOf(formatNS(timeEnd - timeBegin)) + " ns";
        		
        		db.saveScore("Chudnovsky's Java", String.valueOf(lElapsed), elapsed);

        		Log.v("ChudnovskyJavaPI", piChudnovsky);
        		tv5.setText(right(piChudnovsky));
        		tv6.setText(wrong(piChudnovsky));
        		tv7.setText(elapsed);
 			}
 		});        
        btnChudnovskyC.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View arg0) {
        		long timeBegin = System.nanoTime();
        		String piChudnovsky = getChudnovskyPi(4);
        		long timeEnd = System.nanoTime();
        		long lElapsed = timeEnd - timeBegin;
        		String elapsed = String.valueOf(formatNS(timeEnd - timeBegin)) + " ns";
        		 
        		db.saveScore("Chudnovsky's C/C++", String.valueOf(lElapsed), elapsed);
        		
        		Log.v("ChudnovskyCPI", piChudnovsky);
        		tv5.setText(right(piChudnovsky));
        		tv6.setText(wrong(piChudnovsky));
        		tv7.setText(elapsed);
 			}
 		});        
        btnGLJava.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View arg0) {
        		long timeBegin = System.nanoTime();
        		String piGL = Double.toString(Pi.gaussLegendre(4));
        		long timeEnd = System.nanoTime();
        		long lElapsed = timeEnd - timeBegin;
        		String elapsed = String.valueOf(formatNS(timeEnd - timeBegin)) + " ns";
        		
        		db.saveScore("Gauss-Legendre's Java", String.valueOf(lElapsed), elapsed);

        		Log.v("GLJavaPI", piGL);
        		tv5.setText(right(piGL));
        		tv6.setText(wrong(piGL));
        		tv7.setText(elapsed);
 			}
 		});        
        btnGLC.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View arg0) {
        		long timeBegin = System.nanoTime();
        		String piGL = getGLPi(4);
        		long timeEnd = System.nanoTime();
        		long lElapsed = timeEnd - timeBegin;
        		String elapsed = String.valueOf(formatNS(timeEnd - timeBegin)) + " ns";

        		db.saveScore("Gauss-Legendre's C/C++", String.valueOf(lElapsed), elapsed);

        		Log.v("GLCPI", piGL);
        		tv5.setText(right(piGL));
        		tv6.setText(wrong(piGL));
        		tv7.setText(elapsed);
 			}
 		});        
        btnLeibnizJava.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View arg0) {
        		long timeBegin = System.nanoTime();
        		String piLeibniz = Double.toString(Pi.leibniz(100));
        		long timeEnd = System.nanoTime();
        		long lElapsed = timeEnd - timeBegin;
        		String elapsed = String.valueOf(formatNS(lElapsed)) + " ns";

        		db.saveScore("Leibniz's Java", String.valueOf(lElapsed), elapsed);

        		Log.v("LeibnizJavaPI", piLeibniz);
        		tv5.setText(right(piLeibniz));
        		tv6.setText(wrong(piLeibniz));
        		tv7.setText(elapsed);
 			}
 		});        
        btnLeibnizC.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View arg0) { 
        		long timeBegin = System.nanoTime();
        		String piLeibniz = getLeibnizPi(100);
        		long timeEnd = System.nanoTime();
        		long lElapsed = timeEnd - timeBegin;
        		String elapsed = String.valueOf(formatNS(timeEnd - timeBegin)) + " ns";
        		
        		db.saveScore("Leibniz's C/C++", String.valueOf(lElapsed), elapsed);

        		Log.v("LeibnizCPI", piLeibniz);
        		tv5.setText(right(piLeibniz));
        		tv6.setText(wrong(piLeibniz));
        		tv7.setText(elapsed);
 			}
 		});    
        
        btnShowScores.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View arg0) {
        		Dialog dialog = new Dialog(HWNDKActivity.this);

        		dialog.setContentView(R.layout.custom_dialog);
        		dialog.setTitle("High Scores");

        		//get the scores from the database
        		String strStartText = "All runs calc'ed 14 digits of pi.\n";
        		DBHelper dbH = new DBHelper(HWNDKActivity.this);
        		Cursor scores = dbH.getScores();
        		scores.moveToFirst();
        		
        		if (scores.getCount() == 0) {
        			strStartText += "(There have been no runs yet.)";
        		} else {
        			for (int i = 0; i < scores.getCount(); i++ ) {
        				strStartText += String.valueOf(i + 1) + ". " + scores.getString(0) + " ran in " + scores.getString(2) + ".\n";
        				scores.moveToNext();
            		}
            	}        		
        		//we're done here
        		scores.close();
        		
        		TextView text = (TextView) dialog.findViewById(R.id.text);
        		text.setText(strStartText);
        		ImageView image = (ImageView) dialog.findViewById(R.id.image);
        		image.setImageResource(R.drawable.pi);
        		
        		dialog.show(); 
        	}
        });
        
    }
    
    private native String getLeibnizPi(long value1);
    private native String getBellardsPi(int value1);  
    private native String getGLPi(int value1);
    private native String getChudnovskyPi(int value1);
 }