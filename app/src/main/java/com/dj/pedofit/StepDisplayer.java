package com.dj.pedofit;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.database.Cursor;
import android.os.CountDownTimer;
public class StepDisplayer implements StepListener, SpeakingTimer.Listener {

	private int mCount = 0;
	PedometerSettings mSettings;
	Utils mUtils;
	Context context;
	MyDBHelper db;

	public StepDisplayer(Context context, PedometerSettings settings,
			Utils utils) {
		mUtils = utils;
		mSettings = settings;
		this.context = context;
		notifyListener();
		db = new MyDBHelper(context);
	}

	public void setUtils(Utils utils) {
		mUtils = utils;
	}

	public void setSteps(int steps) {
		mCount = steps;
		notifyListener();
	}

	public void onStep() {
		mCount++;
		savedata();
		notifyListener();
	}

	public void reloadSettings() {
		notifyListener();
	}

	public void passValue() {
	}

	ArrayList<String> date = new ArrayList<String>();

	public void savedata() {
		toGetDatabaseValues();
		Calendar cal = Calendar.getInstance();
		String tDate = cal.get(Calendar.YEAR) + "" + cal.get(Calendar.MONTH)
				+ cal.get(Calendar.DAY_OF_MONTH);
		if (date.size() > 0) {
			if (!date.get(date.size() - 1).equals(date)) {
				db.open();
				db.insert(tDate, "" + mCount);
				db.close();
			}
		} else {
			db.open();
			db.insert(tDate, "" + mCount);
			db.close();
		}
	}

	public void toGetDatabaseValues() {
		date.clear();
		try {
			db.open();
			Cursor cur = db.getEmpValues();
			cur.moveToFirst();
			while (!cur.isAfterLast()) {
				date.add(cur.getString(cur.getColumnIndex(MyDBHelper.date)));
				cur.moveToNext();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		db.close();
	}

	public interface Listener {
		public void stepsChanged(int value);

		public void passValue();
	}

	private ArrayList<Listener> mListeners = new ArrayList<Listener>();

	public void addListener(Listener l) {
		mListeners.add(l);
	}

	public void notifyListener() {
		for (Listener listener : mListeners) {
			listener.stepsChanged((int) mCount);
		}
	}

	public void speak() {
		if (mSettings.shouldTellSteps()) {
			if (mCount > 0) {
				mUtils.say("" + mCount + " steps");
			}
		}
	}

}
