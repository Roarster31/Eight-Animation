package com.smithyproductions.eightanimation.animations;

/**
 * Created by rory on 08/08/2014.
 */
public class FloatChangeListener {
	private final float mInitialValue;
	private final OnFloatChangeListener mChangeListener;

	public interface OnFloatChangeListener {
		public void onFloatChange(float newValue);
	}

	public FloatChangeListener(float initialValue, final OnFloatChangeListener changeListener){
		mInitialValue = initialValue;
		mChangeListener = changeListener;
	}
	public void onChange(float newValue){
		mChangeListener.onFloatChange(newValue);
	}

	public float getInitialValue(){
		return mInitialValue;
	}
}