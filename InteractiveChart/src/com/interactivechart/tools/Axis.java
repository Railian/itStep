package com.interactivechart.tools;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class Axis {

	private long mTimestamp;
	private String mLabel;

	public Axis(long timestamp, String label) {
		mTimestamp = timestamp;
		mLabel = label;
	}

	public long getTimestamp() {
		return mTimestamp;
	}

	public String getLabel() {
		return mLabel;
	}

	public static enum AxisType {

		//@formatter:off
		EVERY_5_MINUTES (5 * Timeline.MINUTE_IN_MILLISECONDS, LabelType.HH_MM, LabelAlign.ON_LINE),
		EVERY_10_MINUTES (10 * Timeline.MINUTE_IN_MILLISECONDS, LabelType.HH_MM, LabelAlign.ON_LINE), 
		EVERY_15_MINUTES (15 * Timeline.MINUTE_IN_MILLISECONDS, LabelType.HH_MM, LabelAlign.ON_LINE), 
		EVERY_30_MINUTES (30 * Timeline.MINUTE_IN_MILLISECONDS, LabelType.HH_MM, LabelAlign.ON_LINE), 
		EVERY_HOUR (Timeline.HOUR_IN_MILLISECONDS, LabelType.HH, LabelAlign.ON_LINE), 
		EVERY_2_HOURS (2 * Timeline.HOUR_IN_MILLISECONDS, LabelType.HH, LabelAlign.ON_LINE), 
		EVERY_6_HOURS (6 * Timeline.HOUR_IN_MILLISECONDS, LabelType.HH, LabelAlign.ON_LINE), 
		EVERY_DAY (Timeline.DAY_IN_MILLISECONDS, LabelType.WD_DM, LabelAlign.BETWEEN_LINES), 
		EVERY_DAY_2 (Timeline.DAY_IN_MILLISECONDS, LabelType.DM, LabelAlign.BETWEEN_LINES);
		//@formatter:on

		private static enum LabelType {
			HH_MM, HH, WD_DM, DM;

			public String toString(long timestamp) {
				Calendar axisCalendar = Calendar.getInstance();
				axisCalendar.setTimeZone(TimeZone.getDefault());
				axisCalendar.setTimeInMillis(timestamp);

				int dw = axisCalendar.get(Calendar.DAY_OF_WEEK);
				int dm = axisCalendar.get(Calendar.DAY_OF_MONTH);
				int hh = axisCalendar.get(Calendar.HOUR_OF_DAY);
				int mm = axisCalendar.get(Calendar.MINUTE);

				Locale locale = Locale.getDefault();
				switch (this) {
				case HH_MM:
					return String.format(locale, "%2d:%02d", hh, mm);
				case HH:
					return String.format(locale, "%2d h", hh);
				case WD_DM:
					return String.format(locale, "%s %2d", DayOfWeek
							.getFromCalendar(dw).getShortName(), dm);
				case DM:
					return String.format(locale, "%2d", dm);
				default:
					return null;
				}
			};
		};

		public static enum LabelAlign {
			ON_LINE, BETWEEN_LINES
		};

		private enum DayOfWeek {
			MONDAY("Mon"), TUESDAY("Tue"), WEDNESDAY("Wed"), THURSDAY("Thu"), FRIDAY(
					"Fri"), SATURDAY("Sat"), SUNDAY("Sun");

			private String mShortName;

			DayOfWeek(String shortName) {
				mShortName = shortName;
			}

			public String getShortName() {
				return mShortName;
			}

			public static DayOfWeek getFromCalendar(int dayOfWeek) {
				switch (dayOfWeek) {
				case Calendar.MONDAY:
					return MONDAY;
				case Calendar.TUESDAY:
					return TUESDAY;
				case Calendar.WEDNESDAY:
					return WEDNESDAY;
				case Calendar.THURSDAY:
					return THURSDAY;
				case Calendar.FRIDAY:
					return FRIDAY;
				case Calendar.SATURDAY:
					return SATURDAY;
				case Calendar.SUNDAY:
					return SUNDAY;
				default:
					return null;
				}
			}
		};

		private int mStepInMillis;
		private LabelType mLabelType;
		private LabelAlign mLabelAlign;

		AxisType(int stepInMillis, LabelType labelType, LabelAlign labelAlign) {
			mStepInMillis = stepInMillis;
			mLabelType = labelType;
			mLabelAlign = labelAlign;
		}

		public int getStepInMillis() {
			return mStepInMillis;
		}

		public LabelAlign getLabelAlign() {
			return mLabelAlign;
		}

		public static AxisType determinate(long rightTimestamp,
				long leftTimestamp) {
			long timeRange = Math.abs(rightTimestamp - leftTimestamp);
			if (timeRange <= 30*Timeline.MINUTE_IN_MILLISECONDS)
				return EVERY_5_MINUTES;
			if (timeRange <= Timeline.HOUR_IN_MILLISECONDS)
				return EVERY_10_MINUTES;
			else if (timeRange <= 2 * Timeline.HOUR_IN_MILLISECONDS)
				return EVERY_15_MINUTES;
			else if (timeRange <= 4 * Timeline.HOUR_IN_MILLISECONDS)
				return EVERY_30_MINUTES;
			else if (timeRange <= 8 * Timeline.HOUR_IN_MILLISECONDS)
				return EVERY_HOUR;
			else if (timeRange <= Timeline.DAY_IN_MILLISECONDS)
				return EVERY_2_HOURS;
			else if (timeRange <= 2 * Timeline.DAY_IN_MILLISECONDS)
				return EVERY_6_HOURS;
			else if (timeRange <= 7 * Timeline.DAY_IN_MILLISECONDS)
				return EVERY_DAY;
			else
				return EVERY_DAY_2;
		}

		public Axis[] getAxis(long leftTimestamp, long rightTimestamp) {

			Calendar leftCalendar = Calendar.getInstance();
			leftCalendar.setTimeZone(TimeZone.getDefault());
			leftCalendar.setTimeInMillis(leftTimestamp);

			int leftHour = leftCalendar.get(Calendar.HOUR_OF_DAY);
			int leftMinute = leftCalendar.get(Calendar.MINUTE);
			int leftSecond = leftCalendar.get(Calendar.SECOND);
			int leftMillisecond = leftCalendar.get(Calendar.MILLISECOND);

			long timeRange = rightTimestamp - leftTimestamp;
			int leftOffset = (getStepInMillis() - leftHour * 60 * 60 * 1000
					- leftMinute * 60 * 1000 - leftSecond * 1000 - leftMillisecond)
					% (getStepInMillis());
			int axisCount = (int) ((timeRange - leftOffset) / getStepInMillis()) + 1;
			Axis[] axis = new Axis[axisCount];

			for (int i = 0; i < axis.length; i++) {
				long timestamp = leftTimestamp + leftOffset + i
						* getStepInMillis();
				axis[i] = new Axis(timestamp, mLabelType.toString(timestamp));
			}
			return axis;
		}

	};
}
