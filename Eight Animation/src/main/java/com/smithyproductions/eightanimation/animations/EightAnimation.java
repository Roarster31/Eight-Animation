package com.smithyproductions.eightanimation.animations;

/**
 * Created by rory on 08/08/2014.
 */
public abstract class EightAnimation {

	public interface AnimationCallback {
		public void onValueUpdated();
	}

	public abstract void play();
}
