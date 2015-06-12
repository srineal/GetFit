package parsing;

import java.io.IOException;
import java.io.InputStream;
import java.io.UTFDataFormatException;
import java.util.ArrayList;

import meals.FoodItemListActivity;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.os.IInterface;
import android.util.Log;
import android.util.Xml;
import android.widget.Toast;

public class FoodItemUtil {
	
	static public class FoodItemPullParser extends DefaultHandler{
		ArrayList<FoodItem> foodItemList;
		StringBuilder xmlInnerText;
		
		static public ArrayList<FoodItem> parseFoodItem(InputStream in) throws XmlPullParserException, IOException {
			XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
			parser.setInput(in , "UTF-8");
			FoodItem foodItem = null;
			ArrayList<FoodItem> foodItemList = new ArrayList<FoodItem>();
			int event = parser.getEventType();
			
			while(event != XmlPullParser.END_DOCUMENT){
				
				switch (event) {
				case XmlPullParser.START_TAG:
					if (parser.getName().equals("item"))
						foodItem = new FoodItem();
					
					if (parser.getName().equals("name") && foodItem != null){
						foodItem.setName(parser.nextText());	
					}
					if (parser.getName().equals("ndbno") && foodItem != null){
						foodItem.setNdbno(parser.nextText());
					}
					
					if (parser.getName().equals("foods"))
						foodItem = new FoodItem();
					
					if (parser.getName().equals("food") && foodItem != null){
						//Log.d("Demo", parser.getAttributeValue(0));
						foodItem.setNdbno(parser.getAttributeValue(0));
						foodItem.setName(parser.getAttributeValue(1));
						foodItem.setWeight(parser.getAttributeValue(2));
					}

					if (parser.getName().equals("nutrient") && foodItem != null){
						//Log.d("Demo", parser.getAttributeValue(0));
						if(parser.getAttributeValue(0).equals("203"))
						foodItem.setProtein(parser.getAttributeValue(3));
						
						if(parser.getAttributeValue(0).equals("269"))
							foodItem.setSugar(parser.getAttributeValue(3));
						
						if(parser.getAttributeValue(0).equals("204"))
							foodItem.setFat(parser.getAttributeValue(3));
						
						if(parser.getAttributeValue(0).equals("205"))
							foodItem.setCarbs(parser.getAttributeValue(3));
						
						if(parser.getAttributeValue(0).equals("208"))
							foodItem.setEnergy(parser.getAttributeValue(3));
					}
					
					break;
				case XmlPullParser.END_TAG:
					if (parser.getName().equals("item") || parser.getName().equals("food")) {
						foodItemList.add(foodItem);
						Log.d("foodItem", foodItem.toString());
						foodItem = null;
					}
				default:
					break;
				}
				event = parser.next();
			}
			return foodItemList;
		}
	}

}
