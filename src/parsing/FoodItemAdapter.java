package parsing;

/*Mohammed Jalal Hemidach
 * */
import java.util.ArrayList;

import com.squareup.picasso.Picasso;

import finalProject.group_2B.getfit.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodItemAdapter extends ArrayAdapter<FoodItem> {

	Context context;
	int resource;
	ArrayList<FoodItem> foodItemList;

	public FoodItemAdapter(Context context, int resource, ArrayList<FoodItem> foodItemList) {
		super(context, resource, foodItemList);
		this.context = context;
		this.resource = resource;
		this.foodItemList = foodItemList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(resource, parent, false);
		}

		FoodItem foodItem = foodItemList.get(position);

		TextView foodItemName = (TextView) convertView.findViewById(R.id.itemsName);
		foodItemName.setText(foodItem.getName());

		return convertView;
	}

}
