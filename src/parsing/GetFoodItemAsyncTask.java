package parsing;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParserException;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class GetFoodItemAsyncTask extends AsyncTask<String, Void, ArrayList<FoodItem>> {
	Context context;
	DataBridge activity;
	ProgressDialog pd;

	public GetFoodItemAsyncTask(DataBridge activity) {
		super();
		this.activity = activity;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pd = new ProgressDialog(activity.getContext());
		pd.setTitle("Downloading...");
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setCancelable(false);
		pd.show();
	}

	@Override
	protected ArrayList<FoodItem> doInBackground(String... params) {

		try {Log.d("demo", "1");
			URL url = new URL(params[0]);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.connect();
			Log.d("demo", "2");
			//Toast.makeText(activity.getContext(),
					//"Internet Connection Established", Toast.LENGTH_LONG)
					//.show();
			int statusCode = con.getResponseCode();
			Log.d("demo", "3");
			if (statusCode == HttpURLConnection.HTTP_OK) {
				InputStream in = con.getInputStream();
				Log.d("demo", "conected");
				return FoodItemUtil.FoodItemPullParser.parseFoodItem(in);
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	protected void onPostExecute(ArrayList<FoodItem> foodItemList) {
		// TODO Auto-generated method stub
		super.onPostExecute(foodItemList);
		activity.setup(foodItemList);
		if (foodItemList != null) {
			Log.d("demo", foodItemList.toString());
			pd.dismiss();
		}
	}

	public interface DataBridge {
		public void setup(ArrayList<FoodItem> foodItemList);
		public Context getContext();
	}

}
