package com.dj.pedofit;

public class DistanceNotifier implements StepListener, SpeakingTimer.Listener {

	public interface Listener {
		public void valueChanged(float value);

		public void passValue();
	}

	private Listener mListener;

	float mDistance = 0;

	PedometerSettings mSettings;
	Utils mUtils;

	boolean mIsMetric;
	float mStepLength;

	public DistanceNotifier(Listener listener, PedometerSettings settings,
			Utils utils) {
		mListener = listener;
		mUtils = utils;
		mSettings = settings;
		reloadSettings();
	}

	public void setDistance(float distance) {
		mDistance = distance;
		notifyListener();
	}

	public void reloadSettings() {
		mIsMetric = mSettings.isMetric();
		mStepLength = mSettings.getStepLength();
		notifyListener();
	}

	public void onStep() {

		if (mIsMetric) {
			mDistance += (float) (mStepLength / 100000.0);
		} else {
			mDistance += (float) (mStepLength / 63360.0);
		}

		notifyListener();
	}

	private void notifyListener() {
		mListener.valueChanged(mDistance);
	}

	public void passValue() {
	}

	public void speak() {
		if (mSettings.shouldTellDistance()) {
			if (mDistance >= .001f) {
				mUtils.say(("" + (mDistance + 0.000001f)).substring(0, 4)
						+ (mIsMetric ? " kilometers" : " miles"));
				// TODO: format numbers (no "." at the end)
			}
		}
	}

}
