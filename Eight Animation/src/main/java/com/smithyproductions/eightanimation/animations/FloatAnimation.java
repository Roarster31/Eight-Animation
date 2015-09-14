package com.smithyproductions.eightanimation.animations;

/**
 * Created by rory on 08/08/2014.
 */
public abstract class FloatAnimation extends EightAnimation{
	protected final FloatChangeListener mChangeListener;

	public FloatAnimation(FloatChangeListener changeListener){
		mChangeListener = changeListener;
	}


}
