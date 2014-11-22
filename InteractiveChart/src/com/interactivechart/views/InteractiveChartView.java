package com.interactivechart.views;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
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
import android.widget.EdgeEffect;

import com.interactivechart.R;
import com.interactivechart.data.ActivityValue;
import com.interactivechart.data.EventType;
import com.interactivechart.data.EventValue;
import com.interactivechart.data.IsAwakeValue;
import com.interactivechart.listeners.GustureListener;
import com.interactivechart.listeners.ScaleListener;
import com.interactivechart.tools.Axis;
import com.interactivechart.tools.Axis.AxisType;
import com.interactivechart.tools.Axis.AxisType.LabelAlign;
import com.interactivechart.tools.Button;
import com.interactivechart.tools.Button.AlignHorizontal;
import com.interactivechart.tools.Scroller;
import com.interactivechart.tools.Timeline;
import com.interactivechart.tools.Zoomer;

@SuppressLint("ClickableViewAccessibility")
public class InteractiveChartView extends View implements Timeline,
		OnTouchListener, com.interactivechart.tools.Button.OnClickListener {

	public static enum ButtonId {
		GO_TO_NOW, SMOOTH, MAXIMIZE
	}

	public static final String EXTRA_LEFT_TIMESTAMP = "EXTRA_LEFT_TIMESTAMP";
	public static final String EXTRA_RIGHT_TIMESTAMP = "EXTRA_RIGHT_TIMESTAMP";
	public static final String EXTRA_SMOOTH = "EXTRA_SMOOTH";

	public static final long DEFAULT_TIME_RANGE = Timeline.DAY_IN_MILLISECONDS;
	public static final float DEFAULT_LINE_SMOOTHNES = 30f;

	// private static final float GRAPH_SMOOTHNES = 0.15f;
	private static final float MIN_VALUE = 0;
	private static final float MAX_VALUE = 100;

	private final int BUTTON_SIZE = dp2px(36);
	private final int BUTTON_OFFSET = dp2px(8);

	private long mLeftTimestamp;
	private long mRightTimestamp;
	private AxisType mAxisType;

	private Zoomer mZoomer;
	private Scroller mScroller;

	private ScaleListener mScaleListener;
	private GustureListener mGestureListener;

	private ScaleGestureDetector mScaleGestureDetector;
	private GestureDetector mGestureDetector;

	private Button btGoToNow;
	private Button btSmooth;
	private Button btMaximize;

	private Path mGridPath;
	private Path mLinePath;

	private Paint mGridPaint;
	private Paint mTextPaint;
	private Paint mTimelineTextPaint;
	private Paint mTimelineBackgroundPaint;
	private Paint mLinePaint;

	private boolean isSmooth;

	private ArrayList<ActivityValue> mActivityValues;
	private ArrayList<EventValue> mEventValues;
	private ArrayList<IsAwakeValue> mIsAwakeValues;

	private OnData—hangedListener mListener;

	private Random mRandom = new Random();

	public InteractiveChartView(Context context) {
		this(context, null);
	}

	public InteractiveChartView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initGui();
		initDemo(20);
		EventType.loadBitmaps(getResources());

		setOnTouchListener(this);

		mZoomer = new Zoomer(this);
		mScroller = new Scroller(this);

		mScaleListener = new ScaleListener(mZoomer);
		mGestureListener = new GustureListener(mZoomer, mScroller,
				mScaleListener);

		mScaleGestureDetector = new ScaleGestureDetector(getContext(),
				mScaleListener);
		mGestureDetector = new GestureDetector(getContext(), mGestureListener);

		btGoToNow.setOnClickListener(this);
		btSmooth.setOnClickListener(this);
		btMaximize.setOnClickListener(this);
	}

	private void initGui() {
		mRightTimestamp = System.currentTimeMillis();
		mLeftTimestamp = mRightTimestamp - Timeline.DAY_IN_MILLISECONDS;

		btGoToNow = new Button(getContext(), R.drawable.btn_rewind,
				R.drawable.btn_rewind_p, BUTTON_SIZE, BUTTON_SIZE);
		btGoToNow.setOffset(BUTTON_OFFSET, BUTTON_OFFSET);
		btGoToNow.setVibrateEnabled(true);

		btSmooth = new Button(getContext(), R.drawable.btn_complex,
				R.drawable.btn_complex_p, R.drawable.btn_simplify,
				R.drawable.btn_simplify_p, BUTTON_SIZE, BUTTON_SIZE);
		btSmooth.setOffset(2 * BUTTON_OFFSET + BUTTON_SIZE, BUTTON_OFFSET);
		btSmooth.setAlign(AlignHorizontal.RIGHT);
		btSmooth.setVibrateEnabled(true);

		btMaximize = new Button(getContext(), R.drawable.btn_expand,
				R.drawable.btn_expand_p, R.drawable.btn_collapse,
				R.drawable.btn_collapse_p, BUTTON_SIZE, BUTTON_SIZE);
		btMaximize.setOffset(BUTTON_OFFSET, BUTTON_OFFSET);
		btMaximize.setAlign(AlignHorizontal.RIGHT);
		btMaximize.setVibrateEnabled(true);

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

		mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mLinePaint.setColor(getResources().getColor(android.R.color.black));
		mLinePaint.setStyle(Style.STROKE);
		mLinePaint.setStrokeWidth(dp2px(2));
		mLinePaint.setStrokeCap(Cap.ROUND);
		setSmooth(true);
	}

	private void initDemo(final int DAYS) {
		long to = System.currentTimeMillis();
		long from = to - DAYS * Timeline.DAY_IN_MILLISECONDS;

		mActivityValues = new ArrayList<ActivityValue>();
		for (long timestamp = from; timestamp < to; timestamp += Timeline.MINUTE_IN_MILLISECONDS)
			mActivityValues.add(getRendomActivityValue(timestamp));

		mEventValues = getRendomEventValues(from, to, 30);
	}

	private ActivityValue getRendomActivityValue(long timestamp) {
		return new ActivityValue(mRandom.nextFloat() * 80 + 10, timestamp);
	}

	private ArrayList<EventValue> getRendomEventValues(long from, long to,
			int maxCount) {
		ArrayList<EventValue> eventValues = new ArrayList<EventValue>();
		int count = mRandom.nextInt(maxCount);
		float[] steps = new float[count];
		float width = 0;
		for (int i = 0; i < count; i++)
			width += steps[i] = mRandom.nextFloat();

		float position = 0;
		for (int i = 0; i < count; i++)
			eventValues
					.add(new EventValue(EventType.values()[new Random()
							.nextInt(EventType.values().length)],
							(long) (from + (to - from) * (position += steps[i])
									/ width)));
		return eventValues;
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
	public void setLeftTimestamp(long timestamp) {
		mLeftTimestamp = timestamp;
		invalidate();
	}

	@Override
	public void setRightTimestamp(long timestamp) {
		mRightTimestamp = timestamp;
		invalidate();
	}

	@Override
	public void setTimeRange(long leftTimestamp, long rightTimestamp) {
		mLeftTimestamp = leftTimestamp;
		mRightTimestamp = rightTimestamp;
		invalidate();
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

	private void setSmooth(boolean enable) {
		mLinePaint.setPathEffect(enable ? new CornerPathEffect(
				DEFAULT_LINE_SMOOTHNES) : null);
		btSmooth.setToggled(enable);
		isSmooth = enable;
		invalidate();
	}

	public void setOnDataChangedListener(OnData—hangedListener listener) {
		mListener = listener;
	}

	public Button getButton(ButtonId id) {
		switch (id) {
		case GO_TO_NOW:
			return btGoToNow;
		case SMOOTH:
			return btSmooth;
		case MAXIMIZE:
			return btMaximize;
		default:
			return null;
		}
	}

	public boolean isSmooth() {
		return isSmooth;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int measuredWidth = MeasureSpec.getSize(widthMeasureSpec);
		int measuredHeight = MeasureSpec.getSize(heightMeasureSpec);
		setMeasuredDimension(measuredWidth, measuredHeight);
		mGestureListener.setViewWidth(measuredWidth);

		btGoToNow.measureCanvas(measuredWidth, measuredHeight);
		btSmooth.measureCanvas(measuredWidth, measuredHeight);
		btMaximize.measureCanvas(measuredWidth, measuredHeight);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		boolean isTouched = false;
		isTouched |= btGoToNow.onTouchEvent(event);
		isTouched |= btSmooth.onTouchEvent(event);
		isTouched |= btMaximize.onTouchEvent(event);
		if (isTouched)
			invalidate();
		isTouched |= mScaleGestureDetector.onTouchEvent(event);
		isTouched |= mGestureDetector.onTouchEvent(event);
		return isTouched;
	}

	@Override
	public void onClick(Button b) {
		if (b == btGoToNow)
			mScroller.scrollToRightTimestamp(System.currentTimeMillis());
		else if (b == btSmooth)
			setSmooth(b.isToggled());
	}

	@Override
	protected void onDraw(Canvas canvas) {
		drawBackground(canvas);
		drawGrid(canvas);
		drawLine(canvas);
		drawEvents(canvas);
		drawButtons(canvas);
		drawDebugInfo(canvas);
	}

	private void drawBackground(Canvas canvas) {
		canvas.drawColor(getResources().getColor(R.color.green));
		canvas.drawRect(0, getHeight() - 50, getWidth(), getHeight(),
				mTimelineBackgroundPaint);
	}

	private void drawGrid(Canvas canvas) {
		mGridPath.reset();
		mGridPath.moveTo(0, (getHeight() - 50) / 4);
		mGridPath.lineTo(getWidth(), (getHeight() - 50) / 4);
		mGridPath.moveTo(0, (getHeight() - 50) * 2 / 4);
		mGridPath.lineTo(getWidth(), (getHeight() - 50) * 2 / 4);
		mGridPath.moveTo(0, (getHeight() - 50) * 3 / 4);
		mGridPath.lineTo(getWidth(), (getHeight() - 50) * 3 / 4);

		mAxisType = AxisType.determinate(mLeftTimestamp, mRightTimestamp);
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
	}

	private void drawLine(Canvas canvas) {
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
			if (activityValue.getTimestamp() > mRightTimestamp)
				break;
			if (activityValue.getTimestamp() / millisToApproximate != thrushold) {

				// float thisPointX = getXPos(i);
				// float thisPointY = getYPos(mData.get(i).getActivity());
				// float nextPointX = getXPos(si(i + 1));
				// float nextPointY = getYPos(mData.get(si(i +
				// 1)).getActivity());
				//
				// float startdiffX = nextPointX - getXPos(si(i - 1));
				// float startdiffY = nextPointY
				// - getYPos(mData.get(si(i - 1)).getActivity());
				// float endDiffX = getXPos(si(i + 2)) - thisPointX;
				// float endDiffY = getYPos(mData.get(si(i + 2)).getActivity())
				// - thisPointY;
				//
				// float firstControlX = thisPointX + (GRAPH_SMOOTHNES *
				// startdiffX);
				// float firstControlY = thisPointY + (GRAPH_SMOOTHNES *
				// startdiffY);
				// float secondControlX = nextPointX - (GRAPH_SMOOTHNES *
				// endDiffX);
				// float secondControlY = nextPointY - (GRAPH_SMOOTHNES *
				// endDiffY);
				//
				// mLinePath.cubicTo(firstControlX, firstControlY,
				// secondControlX,
				// secondControlY, nextPointX, nextPointY);

				mLinePath.lineTo(getXPos(activityValue.getTimestamp()),
						getYPos(sumActivity / skipCounter));

				sumActivity = 0;
				skipCounter = 0;
				thrushold = activityValue.getTimestamp() / millisToApproximate;
			}
		}
		canvas.drawPath(mLinePath, mLinePaint);
	}

	private void drawEvents(Canvas canvas) {
		Iterator<EventValue> iterator = mEventValues.iterator();
		EventValue eventValue = null;

		while (iterator.hasNext()) {
			eventValue = iterator.next();
			if (eventValue.getTimestamp() >= mLeftTimestamp)
				break;
		}

		while (iterator.hasNext()) {
			if (eventValue.getTimestamp() > mRightTimestamp)
				break;
			canvas.drawBitmap(eventValue.getEvent().getBitmap(),
					getXPos(eventValue.getTimestamp())
							- eventValue.getEvent().getBitmap().getWidth() / 2,
					getYPos(50), null);
			eventValue = iterator.next();
		}
	}

	private void drawButtons(Canvas canvas) {
		btGoToNow.draw(canvas);
		btSmooth.draw(canvas);
		btMaximize.draw(canvas);
	}

	private void drawDebugInfo(Canvas canvas) {
		mTextPaint.setTextAlign(Align.CENTER);
		canvas.drawText(mAxisType.name(), canvas.getWidth() / 2,
				canvas.getHeight() / 2 + 100, mTextPaint);
		canvas.drawText(timeRangeToString(mLeftTimestamp, mRightTimestamp),
				getWidth() / 2, getHeight() / 2, mTextPaint);
		mTextPaint.setTextAlign(Align.LEFT);
		canvas.drawText(String.valueOf(mLeftTimestamp), 0, getHeight() / 2,
				mTextPaint);
		mTextPaint.setTextAlign(Align.RIGHT);
		canvas.drawText(String.valueOf(mRightTimestamp), getWidth(),
				getHeight() / 2, mTextPaint);
	}

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

	@Override
	public void invalidate() {
		super.invalidate();
		if (mListener != null)
			mListener.onData—hanged(this);
	}

	public static interface OnData—hangedListener {
		public void onData—hanged(Timeline timeline);
	}

}