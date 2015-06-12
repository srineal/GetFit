package meals;

import java.util.ArrayList;

import meals.MealActivity;
import parsing.FoodItemAdapter;
import parsing.FoodItem;
import parsing.GetFoodItemAsyncTask;
import finalProject.group_2B.getfit.R;
import finalProject.group_2B.getfit.R.layout;
import android.R.color;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FoodItemListActivity extends Activity implements
		GetFoodItemAsyncTask.DataBridge {

	public final static String FOOD_ITEM_LIST_KEY = "pass a foodItem to preview";
	final String APILink = "http://api.nal.usda.gov/usda/ndb/search/?format=xml&sort=n&max=50&api_key=ZJlnupS1AxCii3Io32hPgj2bhtPZfALl8UlhLRRP&q=";

	TextView searchItemText;
	Button searchItemButton, done;
	ListView foodItemListView;
	ArrayList<FoodItem> foodItemArrayList;
	String mealsName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_item_list);

		done = (Button) findViewById(R.id.foodItemListDone);
		searchItemText = (TextView) findViewById(R.id.searchItemText);
		searchItemButton = (Button) findViewById(R.id.searchItemButton);
		foodItemListView = (ListView) findViewById(R.id.foodItemListView);
		
		Intent getMealsName = getIntent();
		mealsName = getMealsName.getExtras().getString((MealActivity.MEAL_ACTIVITY_KEY));
		Log.d("lmochkil", mealsName);
		
		done.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent goToMeal = new Intent(FoodItemListActivity.this, MealActivity.class);
				startActivity(goToMeal);
				finish();	
			}
		});
		
		searchItemButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (searchItemText.getText().length() == 0) {
					Toast.makeText(FoodItemListActivity.this,
							"Please Enter a Food Item and Press Search..!!!",
							Toast.LENGTH_LONG).show();
				} else {
					String itemToSearch = searchItemText.getText().toString();
					new GetFoodItemAsyncTask(FoodItemListActivity.this)
							.execute(APILink + itemToSearch);
				}

			}
		});
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		
		Intent goToMeal = new Intent(FoodItemListActivity.this, MealActivity.class);
		startActivity(goToMeal);
		finish();
		
	}
	
	
	@Override
	public void setup(ArrayList<FoodItem> foodItemList) {
		this.foodItemArrayList = foodItemList;
		FoodItemAdapter adapter = new FoodItemAdapter(this,
				R.layout.row_item_layout, foodItemArrayList);
		adapter.setNotifyOnChange(true);
		
		if(foodItemArrayList.size() > 0){
		foodItemListView.setAdapter(adapter);
		}

		foodItemListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						FoodItem foodItem = foodItemArrayList.get(position);
						foodItem.setMealsName(mealsName);
						Intent goToPreview = new Intent(
								FoodItemListActivity.this,
								PreviewFoodItemActivity.class);
						goToPreview.putExtra(FOOD_ITEM_LIST_KEY,foodItem);
						startActivity(goToPreview);
					}
				});
	}

	@Override
	public Context getContext() {
		return this;
	}
}
