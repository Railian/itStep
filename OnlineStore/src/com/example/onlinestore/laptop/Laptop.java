package com.example.onlinestore.laptop;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.onlinestore.R;

public class Laptop implements Parcelable {

	public static enum Manufacturer {
		// @formatter:off
		ACER(R.string.laptop_manufacturer_acer), 
		APPLE(R.string.laptop_manufacturer_apple), 
		ASUS(R.string.laptop_manufacturer_asus), 
		DELL(R.string.laptop_manufacturer_dell), 
		FUJITSU(R.string.laptop_manufacturer_fujitsu), 
		GIGABYTE(R.string.laptop_manufacturer_gigabyte), 
		HP(R.string.laptop_manufacturer_hp), 
		IMPRESSION(R.string.laptop_manufacturer_impression), 
		LENOVO(R.string.laptop_manufacturer_lenovo), 
		MSI(R.string.laptop_manufacturer_msi);
		// @formatter:on

		private int mStringId;

		private Manufacturer(int stringId) {
			mStringId = stringId;
		}

		public String toString(Context context) {
			return context.getResources().getString(mStringId);
		}
	}

	public static enum ScreenSize {
		// @formatter:off
		FROM_9_TO_10(R.string.laptop_screen_size_from_9_to_10), 
		FROM_11_TO_12_5(R.string.laptop_screen_size_from_11_to_12_5), 
		_13(R.string.laptop_screen_size_13), 
		FROM_14_TO_15_6(R.string.laptop_screen_size_from_14_to_15_6), 
		FROM_16_TO_17(R.string.laptop_screen_size_from_16_to_17), 
		FROM_18_TO_20(R.string.laptop_screen_size_from_18_to_20);
		// @formatter:on

		private int mStringId;

		private ScreenSize(int stringId) {
			mStringId = stringId;
		}

		public String toString(Context context) {
			return context.getResources().getString(mStringId);
		}
	}

	public static enum ScreenResolution {
		// @formatter:off
		_1024_600(R.string.laptop_screen_resolution_1024_600), 
		_1366_768(R.string.laptop_screen_resolution_1366_768), 
		_1440_900(R.string.laptop_screen_resolution_1440_900), 
		_1600_900(R.string.laptop_screen_resolution_1600_900), 
		FULL_HD(R.string.laptop_screen_resolution_full_hd), 
		BIGGER_THAN_FHD(R.string.laptop_screen_resolution_bigger_than_fhd);	
		// @formatter:on

		private int mStringId;

		private ScreenResolution(int stringId) {
			mStringId = stringId;
		}

		public String toString(Context context) {
			return context.getResources().getString(mStringId);
		}
	}

	public static enum ScreenType {
		IPS, RETINA, IGZO
	}

	public static enum ScreenCoating {
		MATTE, GLOSSY
	}

	public static enum TouchScreen {
		YES, NO
	}

	public static enum Cpu {
		INTEL_CORE_I7, INTEL_CORE_I5, INTEL_CORE_I3, INTEL_PENTIUM, INTEL_CELERON, INTEL_ATOM, AMD_E, AMD_A10, AMD_A8, AMD_A6, AMD_A4
	}

	public static enum Ram {
		UP_TO_4GB, _6GB, _8GB, _10GB, FROM_10GB_TO_16GB, MORE_THAN_16GB
	}

	public static enum VideoCard {
		INTEGRATED, AMD_RADEON, NVIDIA_GEFORCE, NVIDIA_QUADRO
	}

	public static enum VideoCardRam {
		_1GB, _2GB, MORE_THAN_2GB
	}

	public static enum TypeOfDrive {
		HDD, SSD, HDD_SSD, EMMC, HDD_EMMC
	}

	public static enum CapacityOfDrive {
		UP_TO_500GB, FROM_500GB_TO_750GB, FROM_750GB_TO_1TB, FROM_1TB_TO_2TB, MORE_THAN_2TB
	}

	public static enum OpticalDrive {
		Blu_Ray, DVD
	}

	public static enum OperationSystem {
		LINUX, WINDOWS_7, WINDOWS_8, MAC_OS, DOS
	}

	public static enum Weight {
		UP_TO_1, FROM_1_TO_1_5, FROM_1_5_TO_2, FROM_2_TO_2_5, FROM_2_5_TO_3, MORE_THAN_3
	}

	public static enum Color {
		BLACK, BLUE, BROWN, GOLD, GRAY, PINK, RED, SILVER, WHITE, YELLOW
	}

	private String mTitle;
	private int mPrice;
	private String mImagesAssetPath;
	private String mHtmlDescription;
	private Manufacturer mManufacturer;
	private ScreenSize mScreenSize;
	private ScreenResolution mScreenResolution;
	private ScreenType mScreenType;
	private ScreenCoating mScreenCoating;
	private TouchScreen mTouchScreen;
	private Cpu mCpu;
	private Ram mRam;
	private VideoCard mVideoCard;
	private VideoCardRam mVideoCardRam;
	private TypeOfDrive mTypeOfDrive;
	private CapacityOfDrive mCapacityOfDrive;
	private OpticalDrive mOpticalDrive;
	private OperationSystem mOperationSystem;
	private Weight mWeight;
	private Color mColor;

	public Laptop() {
	}

	public Laptop(Parcel source) {
		mTitle = source.readString();
		mPrice = source.readInt();
		mImagesAssetPath = source.readString();
		mHtmlDescription = source.readString();
		mManufacturer = Manufacturer.values()[source.readInt()];
		mScreenSize = ScreenSize.values()[source.readInt()];
		mScreenResolution = ScreenResolution.values()[source.readInt()];
		mScreenType = ScreenType.values()[source.readInt()];
		mScreenCoating = ScreenCoating.values()[source.readInt()];
		mTouchScreen = TouchScreen.values()[source.readInt()];
		mCpu = Cpu.values()[source.readInt()];
		mRam = Ram.values()[source.readInt()];
		mVideoCard = VideoCard.values()[source.readInt()];
		mVideoCardRam = VideoCardRam.values()[source.readInt()];
		mTypeOfDrive = TypeOfDrive.values()[source.readInt()];
		mCapacityOfDrive = CapacityOfDrive.values()[source.readInt()];
		mOpticalDrive = OpticalDrive.values()[source.readInt()];
		mOperationSystem = OperationSystem.values()[source.readInt()];
		mWeight = Weight.values()[source.readInt()];
		mColor = Color.values()[source.readInt()];
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mTitle);
		dest.writeInt(mPrice);
		dest.writeString(mImagesAssetPath);
		dest.writeString(mHtmlDescription);
		dest.writeInt(mManufacturer.ordinal());
		dest.writeInt(mScreenSize.ordinal());
		dest.writeInt(mScreenResolution.ordinal());
		dest.writeInt(mScreenType.ordinal());
		dest.writeInt(mScreenCoating.ordinal());
		dest.writeInt(mTouchScreen.ordinal());
		dest.writeInt(mCpu.ordinal());
		dest.writeInt(mRam.ordinal());
		dest.writeInt(mVideoCard.ordinal());
		dest.writeInt(mVideoCardRam.ordinal());
		dest.writeInt(mTypeOfDrive.ordinal());
		dest.writeInt(mCapacityOfDrive.ordinal());
		dest.writeInt(mOpticalDrive.ordinal());
		dest.writeInt(mOperationSystem.ordinal());
		dest.writeInt(mWeight.ordinal());
		dest.writeInt(mColor.ordinal());
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Laptop> CREATOR = new Creator<Laptop>() {

		@Override
		public Laptop createFromParcel(Parcel source) {
			return new Laptop(source);
		}

		@Override
		public Laptop[] newArray(int size) {
			return new Laptop[size];
		}
	};

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public int getPrice() {
		return mPrice;
	}

	public void setPrice(int price) {
		mPrice = price;
	}

	public String getImagesAssetPath() {
		return mImagesAssetPath;
	}

	public void setImagesAssetPath(String imagesAssetPath) {
		mImagesAssetPath = imagesAssetPath;
	}

	public String getHtmlDescription() {
		return mHtmlDescription;
	}

	public void setHtmlDescription(String htmlDescription) {
		mHtmlDescription = htmlDescription;
	}

	public Manufacturer getManufacturer() {
		return mManufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		mManufacturer = manufacturer;
	}

	public ScreenSize getScreenSize() {
		return mScreenSize;
	}

	public void setScreenSize(ScreenSize screenSize) {
		mScreenSize = screenSize;
	}

	public ScreenResolution getScreenResolution() {
		return mScreenResolution;
	}

	public void setScreenResolution(ScreenResolution screenResolution) {
		mScreenResolution = screenResolution;
	}

	public ScreenType getScreenType() {
		return mScreenType;
	}

	public void setScreenType(ScreenType screenType) {
		mScreenType = screenType;
	}

	public ScreenCoating getScreenCoating() {
		return mScreenCoating;
	}

	public void setScreenCoating(ScreenCoating screenCoating) {
		mScreenCoating = screenCoating;
	}

	public TouchScreen getTouchScreen() {
		return mTouchScreen;
	}

	public void setTouchScreen(TouchScreen touchScreen) {
		mTouchScreen = touchScreen;
	}

	public Cpu getCpu() {
		return mCpu;
	}

	public void setCpu(Cpu cpu) {
		mCpu = cpu;
	}

	public Ram getRam() {
		return mRam;
	}

	public void setRam(Ram ram) {
		mRam = ram;
	}

	public VideoCard getVideoCard() {
		return mVideoCard;
	}

	public void setVideoCard(VideoCard videoCard) {
		mVideoCard = videoCard;
	}

	public VideoCardRam getVideoCardRam() {
		return mVideoCardRam;
	}

	public void setVideoCardRam(VideoCardRam videoCardRam) {
		mVideoCardRam = videoCardRam;
	}

	public TypeOfDrive getTypeOfDrive() {
		return mTypeOfDrive;
	}

	public void setTypeOfDrive(TypeOfDrive typeOfDrive) {
		mTypeOfDrive = typeOfDrive;
	}

	public CapacityOfDrive getCapacityOfDrive() {
		return mCapacityOfDrive;
	}

	public void setCapacityOfDrive(CapacityOfDrive capacityOfDrive) {
		mCapacityOfDrive = capacityOfDrive;
	}

	public OpticalDrive getOpticalDrive() {
		return mOpticalDrive;
	}

	public void setOpticalDrive(OpticalDrive opticalDrive) {
		mOpticalDrive = opticalDrive;
	}

	public OperationSystem getOperationSystem() {
		return mOperationSystem;
	}

	public void setOperationSystem(OperationSystem operationSystem) {
		mOperationSystem = operationSystem;
	}

	public Weight getWeight() {
		return mWeight;
	}

	public void setWeight(Weight weight) {
		mWeight = weight;
	}

	public Color getColor() {
		return mColor;
	}

	public void setColor(Color color) {
		mColor = color;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (mTitle != null)
			builder.append("Title: ").append(mTitle).append('\n');
		if (mPrice > 0)
			builder.append("Price: ").append(mPrice).append('\n');
		if (mImagesAssetPath != null)
			builder.append("mImagesAssetPath: ").append(mImagesAssetPath).append('\n');
		if (mManufacturer != null)
			builder.append("Manufacturer: ").append(mManufacturer).append('\n');
		if (mScreenSize != null)
			builder.append("ScreenSize: ").append(mScreenSize).append('\n');
		if (mScreenResolution != null)
			builder.append("ScreenResolution: ").append(mScreenResolution)
					.append('\n');
		if (mScreenType != null)
			builder.append("ScreenType: ").append(mScreenType).append('\n');
		if (mScreenCoating != null)
			builder.append("ScreenCoating: ").append(mScreenCoating)
					.append('\n');
		if (mTouchScreen != null)
			builder.append("TouchScreen: ").append(mTouchScreen).append('\n');
		if (mCpu != null)
			builder.append("Cpu: ").append(mCpu).append('\n');
		if (mRam != null)
			builder.append("Ram: ").append(mRam).append('\n');
		if (mVideoCard != null)
			builder.append("VideoCard: ").append(mVideoCard).append('\n');
		if (mVideoCardRam != null)
			builder.append("VideoCardRam: ").append(mVideoCardRam).append('\n');
		if (mTypeOfDrive != null)
			builder.append("TypeOfDrive: ").append(mTypeOfDrive).append('\n');
		if (mCapacityOfDrive != null)
			builder.append("CapacityOfDrive: ").append(mCapacityOfDrive)
					.append('\n');
		if (mOpticalDrive != null)
			builder.append("OpticalDrive: ").append(mOpticalDrive).append('\n');
		if (mOperationSystem != null)
			builder.append("OperationSystem: ").append(mOperationSystem)
					.append('\n');
		if (mWeight != null)
			builder.append("Weight: ").append(mWeight).append('\n');
		if (mColor != null)
			builder.append("Color: ").append(mColor).append('\n');
		return builder.toString();
	}

}
