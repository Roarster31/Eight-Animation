package com.smithyproductions.eightanimation.animations;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by rory on 08/08/2014.
 */
public class EnterAnimation extends FloatAnimation {
	private final FloatChangeListener mLineLengthCallback;

	public EnterAnimation(final FloatChangeListener timeCallback, final FloatChangeListener lineLengthCallback) {
		super(timeCallback);
		mLineLengthCallback = lineLengthCallback;
	}

	@Override
	public void play() {


		final long DURATION = 4000;

		ValueAnimator timeAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
		timeAnimator.setDuration(DURATION);
		timeAnimator.setInterpolator(new DecelerateInterpolator());
		timeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(final ValueAnimator animation) {
				mChangeListener.onChange((Float) animation.getAnimatedValue());
			}
		});

		long delay = 2000;
		ValueAnimator lengthAnimator1 = ValueAnimator.ofFloat(mLineLengthCallback.getInitialValue(), 0.2f);
		lengthAnimator1.setDuration(2000);
		lengthAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(final ValueAnimator animation) {
				mLineLengthCallback.onChange((Float) animation.getAnimatedValue());
			}
		});

		ValueAnimator lengthAnimator2 = ValueAnimator.ofFloat(0.2f, 1.0f);
		lengthAnimator2.setDuration(1500);
		lengthAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(final ValueAnimator animation) {
				mLineLengthCallback.onChange((Float) animation.getAnimatedValue());
			}
		});

		AnimatorSet set = new AnimatorSet();
		set.play(lengthAnimator2).after(lengthAnimator1);

		timeAnimator.start();
		set.start();

	}
}
