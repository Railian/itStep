package com.example.onlinestore.laptop;

import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;

import com.example.onlinestore.laptop.Laptop.CapacityOfDrive;
import com.example.onlinestore.laptop.Laptop.Color;
import com.example.onlinestore.laptop.Laptop.Cpu;
import com.example.onlinestore.laptop.Laptop.Manufacturer;
import com.example.onlinestore.laptop.Laptop.OperationSystem;
import com.example.onlinestore.laptop.Laptop.Ram;
import com.example.onlinestore.laptop.Laptop.ScreenCoating;
import com.example.onlinestore.laptop.Laptop.ScreenResolution;
import com.example.onlinestore.laptop.Laptop.ScreenSize;
import com.example.onlinestore.laptop.Laptop.TypeOfDrive;
import com.example.onlinestore.laptop.Laptop.VideoCard;
import com.example.onlinestore.laptop.Laptop.Weight;

public class Laptops extends ArrayList<Laptop> {

	private static final long serialVersionUID = 1L;

	public static Laptops parseFromXml(Context context, int xmlRes) {

		Laptops laptops = new Laptops();
		try {
			XmlPullParser xmlPullParser = context.getResources().getXml(xmlRes);
			while (xmlPullParser.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xmlPullParser.getEventType() == XmlPullParser.START_TAG)
					if ("laptop".equals(xmlPullParser.getName())) {
						Laptop laptop = new Laptop();
						for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
							switch (xmlPullParser.getAttributeName(i)) {
							case "Title":
								laptop.setTitle(xmlPullParser
										.getAttributeValue(i));
								break;
							case "Price":
								laptop.setPrice(Integer.parseInt(xmlPullParser
										.getAttributeValue(i)));
								break;
							case "ImagesAssetPath":
								laptop.setImagesAssetPath(xmlPullParser
										.getAttributeValue(i));
								break;
							case "HtmlDescription":
								laptop.setHtmlDescription(xmlPullParser
										.getAttributeValue(i));
								System.out.println(laptop.getHtmlDescription());
								break;
							case "Manufacturer":
								laptop.setManufacturer(Manufacturer
										.valueOf(xmlPullParser
												.getAttributeValue(i)));
								break;
							case "ScreenSize":
								laptop.setScreenSize(ScreenSize
										.valueOf(xmlPullParser
												.getAttributeValue(i)));
								break;
							case "CapacityOfDrive":
								laptop.setCapacityOfDrive(CapacityOfDrive
										.valueOf(xmlPullParser
												.getAttributeValue(i)));
								break;
							case "Color":
								laptop.setColor(Color.valueOf(xmlPullParser
										.getAttributeValue(i)));
								break;
							case "Cpu":
								laptop.setCpu(Cpu.valueOf(xmlPullParser
										.getAttributeValue(i)));
								break;
							case "OperationSystem":
								laptop.setOperationSystem(OperationSystem
										.valueOf(xmlPullParser
												.getAttributeValue(i)));
								break;
							case "Ram":
								laptop.setRam(Ram.valueOf(xmlPullParser
										.getAttributeValue(i)));
								break;
							case "ScreenCoating":
								laptop.setScreenCoating(ScreenCoating
										.valueOf(xmlPullParser
												.getAttributeValue(i)));
								break;
							case "ScreenResolution":
								laptop.setScreenResolution(ScreenResolution
										.valueOf(xmlPullParser
												.getAttributeValue(i)));
								break;
							case "TypeOfDrive":
								laptop.setTypeOfDrive(TypeOfDrive
										.valueOf(xmlPullParser
												.getAttributeValue(i)));
								break;
							case "VideoCard":
								laptop.setVideoCard(VideoCard
										.valueOf(xmlPullParser
												.getAttributeValue(i)));
								break;
							case "Weight":
								laptop.setWeight(Weight.valueOf(xmlPullParser
										.getAttributeValue(i)));
								break;
							}
						}
						laptops.add(laptop);
					}
				xmlPullParser.next();
			}
		} catch (XmlPullParserException | IOException e) {
			e.printStackTrace();
		}

		return laptops;
	}
}
