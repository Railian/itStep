package com.interactivechart.views;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.View.OnTouchListener;

import com.interactivechart.R;
import com.interactivechart.data.ActivityValue;
import com.interactivechart.listeners.GustureListener;
import com.interactivechart.listeners.ScaleListener;
import com.interactivechart.tools.Axis;
import com.interactivechart.tools.Axis.AxisType;
import com.interactivechart.tools.Axis.AxisType.LabelAlign;
import com.interactivechart.tools.Scroller;
import com.interactivechart.tools.Timeline;
import com.interactivechart.tools.Zoomer;

@SuppressLint("ClickableViewAccessibility")
public class InteractiveChartView extends View implements Timeline,
		OnTouchListener {

	// private static final float GRAPH_SMOOTHNES = 0.15f;
	private static final float MIN_VALUE = 0;
	private static final float MAX_VALUE = 100;

	private long mLeftTimestamp;
	private long mRightTimestamp;
	private AxisType mAxisType;

	private Zoomer mZoomer;
	private Scroller mScroller;

	private ScaleListener mScaleListener;
	private GustureListener mGestureListener;

	private ScaleGestureDetector mScaleGestureDetector;
	private GestureDetector mGestureDetector;

	private Path mGridPath;
	private Path mLinePath;

	private Paint mGridPaint;
	private Paint mTextPaint;
	private Paint mTimelineTextPaint;
	private Paint mTimelineBackgroundPaint;
	private Paint mLinePaint;

	private ArrayList<ActivityValue> mActivityValues;

	public InteractiveChartView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initGui();
		initDemo(20);
		setOnTouchListener(this);

		mZoomer = new Zoomer(this, this);
		mScroller = new Scroller(this, this);

		mScaleListener = new ScaleListener(mZoomer);
		mGestureListener = new GustureListener(mZoomer, mScroller,
				mScaleListener);

		mScaleGestureDetector = new ScaleGestureDetector(getContext(),
				mScaleListener);
		mGestureDetector = new GestureDetector(getContext(), mGestureListener);
	}

	private void initGui() {
		mRightTimestamp = System.currentTimeMillis();
		mLeftTimestamp = mRightTimestamp - Timeline.DAY_IN_MILLISECONDS;

		mGridPath = new Path();
		mLinePath = new Path();

		mGridPaint = new Paint();
		mGridPaint.setStrokeWidth(dp2px(1.5f));
		mGridPaint.setColor(getResources().getColor(android.R.color.white));
		mGridPaint.setStyle(Paint.Style.STROKE);
		mGridPaint.setAlpha(0x88);
		mGridPaint.setPathEffect(new DashPathEffect(new float[] { dp2px(1.5f),
				dp2px(1.5f) }, 1));

		mTimelineTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mTimelineTextPaint.setColor(getResources().getColor(R.color.grey));
		mTimelineTextPaint.setTextSize(dp2px(12));
		mTimelineTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
		mTimelineTextPaint.setTextAlign(Align.CENTER);

		mTimelineBackgroundPaint = new Paint();
		mTimelineBackgroundPaint.setColor(getResources().getColor(
				R.color.grey_darker));

		mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mTextPaint.setColor(getResources().getColor(R.color.white));
		mTextPaint.setTextSize(dp2px(12));
		mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
		mTextPaint.setShadowLayer(dp2px(2), 0, 0, 0x55222222);

		mLinePaint = new Paint();
		mLinePaint.setColor(getResources().getColor(android.R.color.black));
		mLinePaint.setStyle(Style.STROKE);
		mLinePaint.setStrokeWidth(dp2px(2));
		mLinePaint.setStrokeCap(Cap.ROUND);
		mLinePaint.setAntiAlias(true);
	}

	private void initDemo(final int DAYS) {
		long to = System.currentTimeMillis();
		long from = to - DAYS * Timeline.DAY_IN_MILLISECONDS;

		mActivityValues = new ArrayList<ActivityValue>();
		for (long timestamp = from; timestamp < to; timestamp += Timeline.MINUTE_IN_MILLISECONDS)
			mActivityValues.add(getRendomActivityValue(timestamp));

	 
//		mEventValues = new ArrayList<EventValue>();
//		for (long timestamp = from; timestamp < to; timestamp += Timeline.MINUTE_IN_MILLISECONDS)
//			mEventValues.add(getRendomEventVal												  ue(from, to));
	}

	private ActivityValue getRendomActivityValue(long timestamp) {
		return new ActivityValue(new Random().nextFloat() * 80 + 10, timestamp);
	}

	@Override
	public long getLeftTimestamp() {
		return mLeftTimestamp;
	}

	@Override
	public long getRightTimestamp() {
		return mRightTimestamp;
	}

	@Override
	public long getTimeRange() {
		return getRightTimestamp() - getLeftTimestamp();
	}

	@Override
	public AxisType getAxisType() {
		return mAxisType;
	}

	@Override
	public void setLeftTimestamp(long timestamp) {
		mLeftTimestamp = timestamp;
	}

	@Override
	public void setRightTimestamp(long timestamp) {
		mRightTimestamp = timestamp;
	}

	@Override
	public void setTimeRange(long leftTimestamp, long rightTimestamp) {
		mLeftTimestamp = leftTimestamp;
		mRightTimestamp = rightTimestamp;
	}

	// @Override
	// public void setLeftTimestamp(long timestamp) {
	// if (timestamp < mRightTimestamp)
	// mLeftTimestamp = timestamp;
	// else {
	// mLeftTimestamp = mRightTimestamp;
	// mRightTimestamp = timestamp;
	// }
	// }
	//
	// @Override
	// public void setRightTimestamp(long timestamp) {
	// if (timestamp > mLeftTimestamp)
	// mRightTimestamp = timestamp;
	// else {
	// mRightTimestamp = mLeftTimestamp;
	// mLeftTimestamp = timestamp;
	// }
	// }
	//
	// @Override
	// public void setTimeRange(long leftTimestamp, long rightTimestamp) {
	// if (leftTimestamp < rightTimestamp) {
	// mLeftTimestamp = leftTimestamp;
	// mRightTimestamp = rightTimestamp;
	// } else {
	// mLeftTimestamp = rightTimestamp;
	// mRightTimestamp = leftTimestamp;
	// }
	// }

	@Override
	public void setAxisType(AxisType axisType) {
		mAxisType = axisType;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int measuredWidth = MeasureSpec.getSize(widthMeasureSpec);
		int measuredHeight = MeasureSpec.getSize(heightMeasureSpec);
		setMeasuredDimension(measuredWidth, measuredHeight);
		mGestureListener.setViewWidth(measuredWidth);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		boolean touch = false;
		touch |= mScaleGestureDetector.onTouchEvent(event);
		touch |= mGestureDetector.onTouchEvent(event);
		return touch;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// Path path = createSmoothPath(-1, 1);
		// canvas.drawPath(path, mLinePaint);

		canvas.drawColor(getResources().getColor(R.color.green));

		mTextPaint.setTextAlign(Align.LEFT);
		canvas.drawText(String.valueOf(mLeftTimestamp), 0, getHeight() / 2,
				mTextPaint);
		mTextPaint.setTextAlign(Align.RIGHT);
		canvas.drawText(String.valueOf(mRightTimestamp), getWidth(),
				getHeight() / 2, mTextPaint);
		mTextPaint.setTextAlign(Align.CENTER);
		canvas.drawText(timeRangeToString(mLeftTimestamp, mRightTimestamp),
				getWidth() / 2, getHeight() / 2, mTextPaint);

		if (mAxisType == null)
			mAxisType = AxisType.determinate(mLeftTimestamp, mRightTimestamp);
		canvas.drawText(mAxisType.name(), canvas.getWidth() / 2,
				canvas.getHeight() / 2 + 100, mTextPaint);

		canvas.drawRect(0, getHeight() - 50, getWidth(), getHeight(),
				mTimelineBackgroundPaint);

		mGridPath.reset();

		mGridPath.moveTo(0, (getHeight() - 50) / 4);
		mGridPath.lineTo(getWidth(), (getHeight() - 50) / 4);
		mGridPath.moveTo(0, (getHeight() - 50) * 2 / 4);
		mGridPath.lineTo(getWidth(), (getHeight() - 50) * 2 / 4);
		mGridPath.moveTo(0, (getHeight() - 50) * 3 / 4);
		mGridPath.lineTo(getWidth(), (getHeight() - 50) * 3 / 4);

		Axis[] axis = mAxisType.getAxis(mLeftTimestamp, mRightTimestamp);

		for (int i = 0; i < axis.length; i++) {
			mGridPath.moveTo(getXPos(axis[i].getTimestamp()), 0);
			mGridPath.lineTo(getXPos(axis[i].getTimestamp()), getHeight() - 50);
			float xPos = getXPos(axis[i].getTimestamp()
					+ (mAxisType.getLabelAlign() == LabelAlign.BETWEEN_LINES ? mAxisType
							.getStepInMillis() / 2 : 0));
			canvas.drawText(axis[i].getLabel(), xPos, getHeight() - 10,
					mTimelineTextPaint);
		}
		canvas.drawPath(mGridPath, mGridPaint);

		mLinePath.reset();

		Iterator<ActivityValue> iterator = mActivityValues.iterator();
		ActivityValue activityValue = null;

		while (iterator.hasNext()) {
			activityValue = iterator.next();
			if (activityValue.getTimestamp() >= mLeftTimestamp)
				break;
		}
		if (activityValue != null)
			mLinePath.moveTo(getXPos(activityValue.getTimestamp()),
					getYPos(activityValue.getActivity()));

		long millisToApproximate;
		switch (mAxisType) {
		default:
		case EVERY_5_MINUTES:
		case EVERY_10_MINUTES:
		case EVERY_15_MINUTES:
			millisToApproximate = 1 * Timeline.MINUTE_IN_MILLISECONDS;
			break;
		case EVERY_30_MINUTES:
			millisToApproximate = 2 * Timeline.MINUTE_IN_MILLISECONDS;
			break;
		case EVERY_HOUR:
			millisToApproximate = 4 * Timeline.MINUTE_IN_MILLISECONDS;
			break;
		case EVERY_2_HOURS:
			millisToApproximate = 8 * Timeline.MINUTE_IN_MILLISECONDS;
			break;
		case EVERY_6_HOURS:
			millisToApproximate = 24 * Timeline.MINUTE_IN_MILLISECONDS;
			break;
		case EVERY_DAY:
			millisToApproximate = 96 * Timeline.MINUTE_IN_MILLISECONDS;
			break;
		case EVERY_DAY_2:
			millisToApproximate = 192 * Timeline.MINUTE_IN_MILLISECONDS;
			break;
		}

		int sumActivity = 0;
		int skipCounter = 0;
		long thrushold = activityValue.getTimestamp() / millisToApproximate;

		while (iterator.hasNext()) {
			sumActivity += (activityValue = iterator.next()).getActivity();
			skipCounter++;
			if (activityValue.getTimestamp() <= mRightTimestamp) {
				if (activityValue.getTimestamp() / millisToApproximate != thrushold) {
					mLinePath.lineTo(getXPos(activityValue.getTimestamp()),
							getYPos(sumActivity / skipCounter));
					sumActivity = 0;
					skipCounter = 0;
					thrushold = activityValue.getTimestamp()
							/ millisToApproximate;
				}
			} else
				break;
		}

		canvas.drawPath(mLinePath, mLinePaint);
	}

	private String timeRangeToString(long leftTimestamp, long rightTimestamp) {
		long range = Math.abs(leftTimestamp - rightTimestamp);
		int ms = (int) (range % 1000);
		int s = (int) (range / 1000 % 60);
		int m = (int) (range / 1000 / 60 % 60);
		int h = (int) (range / 1000 / 60 / 60 % 24);
		int d = (int) (range / 1000 / 60 / 60 / 24);
		return String.format(Locale.getDefault(),
				"%dd:%02dh:%02dm:%02ds:%03dms", d, h, m, s, ms);
	}

	boolean isSmooth = false;

	// private Path createSmoothPath(float minValue, float maxValue) {
	//
	// Path path = new Path();

	// if (isSmooth)
	// for (int i = mLeftSide; i < mRightSide - 1; i++) {
	// if (i == mLeftSide) {
	// path.moveTo(getXPos(mLeftSide),
	// getYPos(mData.get(mLeftSide).getActivity()));
	// continue;
	// }
	// float thisPointX = getXPos(i);
	// float thisPointY = getYPos(mData.get(i).getActivity());
	// float nextPointX = getXPos(si(i + 1));
	// float nextPointY = getYPos(mData.get(si(i + 1)).getActivity());
	//
	// float startdiffX = nextPointX - getXPos(si(i - 1));
	// float startdiffY = nextPointY - getYPos(mData.get(si(i -
	// 1)).getActivity());
	// float endDiffX = getXPos(si(i + 2)) - thisPointX;
	// float endDiffY = getYPos(mData.get(si(i + 2)).getActivity()) -
	// thisPointY;
	//
	// float firstControlX = thisPointX
	// + (GRAPH_SMOOTHNES * startdiffX);
	// float firstControlY = thisPointY
	// + (GRAPH_SMOOTHNES * startdiffY);
	// float secondControlX = nextPointX
	// - (GRAPH_SMOOTHNES * endDiffX);
	// float secondControlY = nextPointY
	// - (GRAPH_SMOOTHNES * endDiffY);
	//
	// path.cubicTo(firstControlX, firstControlY, secondControlX,
	// secondControlY, nextPointX, nextPointY);
	// if (si(i) == mRightSide - mLeftSide - 1)
	// break;
	// }
	// else
	// for (int i = mLeftSide; i < mRightSide; i++) {
	// if (i == mLeftSide) {
	// path.moveTo(getXPos(mLeftSide), getYPos(mData.get(mLeftSide)
	// .getActivity()));
	// continue;
	// }
	// path.lineTo(getXPos(i), getYPos(mData.get(i).getActivity()));
	// }
	// return path;
	// }

	// private int si(int i) {
	// if (i > mRightSide - mLeftSide - 1)
	// return mRightSide - mLeftSide - 1;
	// else if (i < 0)
	// return 0;
	//
	// return i;
	// }

	private float getYPos(float value) {
		float height = getHeight() - getPaddingTop() - getPaddingBottom();
		value = (value - MIN_VALUE) / (MAX_VALUE - MIN_VALUE) * height;
		value = height - value;
		value += getPaddingTop();
		return value;
	}

	private float getXPos(long value) {
		float width = getWidth() - getPaddingLeft() - getPaddingRight();
		long maxValue = mRightTimestamp - mLeftTimestamp;
		return getPaddingLeft() + (float) (value - mLeftTimestamp) / maxValue
				* width;
	}

	private int dp2px(float dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				getResources().getDisplayMetrics());
	}
}