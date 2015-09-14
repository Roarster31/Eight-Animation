package com.smithyproductions.eightanimation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.smithyproductions.eightanimation.animations.EnterAnimation;
import com.smithyproductions.eightanimation.animations.FloatChangeListener;
import com.smithyproductions.eightanimation.animations.MockDurationAnimation;

/**
 * Created by rory on 28/07/2014.
 */
public class AnimatableEight extends View {
	private Paint backgroundPaint;
	private int count;
	private float lineLength;
	private float lineLimit;
	private int factor;
	private int parentWidth;
	private int parentHeight;
	private Paint primaryCircPaint;
	private Paint secondaryCircPaint;
	private int radius;
	private float primaryTime = 0;
	private float secondaryTime = 0;
	public AnimatableEight(final Context context) {
		super(context);
		init(context);
	}
	public AnimatableEight(final Context context, final AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	public AnimatableEight(final Context context, final AttributeSet attrs, final int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}
	private void init(final Context context) {
		backgroundPaint = new Paint();
		primaryCircPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		secondaryCircPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

		backgroundPaint.setColor(Color.WHITE);
		primaryCircPaint.setColor(Color.parseColor("#6aa9ff"));
		secondaryCircPaint.setColor(Color.RED);

		radius = 5;
		float arcLength = 38.56f;
		lineLength = 0.6f;
		lineLimit = 0.2f;
		factor = 5;

		count = (int) (radius*arcLength/2);

	}

	public void animateSecondary(){

		new MockDurationAnimation(new FloatChangeListener(secondaryTime, new FloatChangeListener.OnFloatChangeListener() {
			@Override
			public void onFloatChange(final float newValue) {
				secondaryTime = newValue;
				invalidate();
			}
		})).play();


	}

	public void playAnimation(){

		new EnterAnimation(new FloatChangeListener(primaryTime, new FloatChangeListener.OnFloatChangeListener() {
			@Override
			public void onFloatChange(final float newValue) {
				primaryTime = newValue;
				invalidate();
			}
		}),new FloatChangeListener(lineLength, new FloatChangeListener.OnFloatChangeListener() {
			@Override
			public void onFloatChange(final float newValue) {
				lineLength = newValue;
				invalidate();
			}
		})).play();

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		parentWidth = MeasureSpec.getSize(widthMeasureSpec);
		parentHeight = MeasureSpec.getSize(heightMeasureSpec);
		updateDimens();
		this.setMeasuredDimension(parentWidth, parentHeight);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	private void updateDimens() {
		radius = (int) (parentHeight/50f);
	}
	@Override
	protected void onDraw(final Canvas canvas) {
		canvas.drawRect(0,0,parentWidth,parentHeight,backgroundPaint);

		if(isInEditMode())
			return;

		count = 100;

		for(int i=0; i<count; i++){

			//we need to add spacing between each point
			//there's a target length we need to achieve
			//so we'll aim for that
			float relativeTime = smoothStep(primaryTime, 10) - (i/(float)count)*(lineLength-lineLimit);

			double x;
			double y;
			double rY;
			double rX;
			double x2 = 0;
			double y2 = 0;

			if(relativeTime<=lineLimit){
				//if you're here we're drawing a line :)
				relativeTime = relativeTime/lineLimit;
				x = getLineX(relativeTime, false);
				y = getLineY(relativeTime, false);
				rX = getLineX(relativeTime, true);
				rY = getLineY(relativeTime, true);

			}else{
				//if you're here we're drawing a nice parametric
				float startOffset = (float) - Math.PI;
				float endOffset = (float) ((5*Math.PI)/2f);

				relativeTime = (relativeTime-lineLimit)/(1-lineLimit);
				relativeTime = (float) (endOffset-relativeTime*endOffset + startOffset);
				x = getParamtericX(relativeTime, false);
				y = getParamtericY(relativeTime, false);
				rX = getParamtericX(relativeTime, true);
				rY = getParamtericY(relativeTime, true);


			}


			float startOffset = (float) Math.PI;
			float endOffset = (float) (5*Math.PI*secondaryTime);
			float relativeTime2 = (float) (endOffset-secondaryTime*endOffset + startOffset);

			relativeTime2 = Math.max(0,secondaryTime - (i/(float)count));
			relativeTime2 = startOffset + relativeTime2*(endOffset-startOffset);

			x2 = getParamtericX(relativeTime2, false);
			y2 = getParamtericY(relativeTime2, false);

			//var customTime =  t-(i/count)*(1-lineLimit-length);

			canvas.drawCircle((float)x, (float) y, radius, primaryCircPaint);
			canvas.drawCircle((float)rX, (float) rY, radius, primaryCircPaint);


			canvas.drawCircle((float)x2,(float)y2,radius,secondaryCircPaint);
		}
	}

	private double getParamtericX(float t, boolean reversed){

		if(reversed){
			return parentWidth/2 - 2*Math.cos(t/2) * (parentHeight/factor);
		}else{
			return parentWidth/2 + 2*Math.cos(t/2) * (parentHeight/factor);
		}
	}

	private double getLineX(float t, boolean reversed){
		if(reversed){
			return parentWidth - (parentWidth/2f - 1.5*(parentHeight/factor)) * t;
		}else{
			return (parentWidth/2f - 1.5*(parentHeight/factor)) * t;
		}

	}

	private double getParamtericY(float t, boolean reversed){


		if(reversed){
			return parentHeight/2f - Math.sin(t) * (parentHeight/factor);
		}else{
			return parentHeight/2f - Math.sin(t) * (parentHeight/factor);
		}
	}

	private double getLineY(float t, boolean reversed){
		return parentHeight/2f + (parentHeight/factor);
	}

	private float smoothStep(float x){
		return smoothStep(x, 0);
	}

	private float smoothStep(float x, int pow){
		if(pow==0){
			return (x) * (x) * (3 - 2 * (x));
		}else{
			return smoothStep(x, pow - 1);
		}
	}
	public void setPlayed() {
		primaryTime = 1.0f;
		lineLength = 1.0f;
		invalidate();
	}
}
