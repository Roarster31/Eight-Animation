package com.smithyproductions.eightanimation.animations;

import android.animation.ValueAnimator;

/**
 * Created by rory on 08/08/2014.
 */
public class MockDurationAnimation extends FloatAnimation {

	public MockDurationAnimation(final FloatChangeListener changeListener) {
		super(changeListener);
	}

	@Override
	public void play() {

		ValueAnimator va = ValueAnimator.ofFloat(0.0f, 1.0f);
		va.setDuration(4000);
		va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(final ValueAnimator animation) {
				mChangeListener.onChange((Float) animation.getAnimatedValue());
			}
		});
		va.start();

	}
}
