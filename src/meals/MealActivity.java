package meals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import loginSignUp.LoginSignUpActivity;

import com.parse.ParseUser;

import parsing.FoodItem;
import parsing.FoodItemAdapter;
import finalProject.group_2B.getfit.BudgetActivity;
import finalProject.group_2B.getfit.GoalsActivity;
import finalProject.group_2B.getfit.PlanActivity;
import finalProject.group_2B.getfit.ProgramActivity;
import finalProject.group_2B.getfit.R;
import finalProject.group_2B.getfit.R.id;
import finalProject.group_2B.getfit.R.layout;
import android.R.color;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MealActivity extends Activity implements SingleMealFragment.OnFragmentInteractionListener{

	public final static String MEAL_ACTIVITY_KEY = "pass over the meals name";
	
	View mealsContainer,mealsComponentContainer;
	TextView budget, food, exercise, net, status; 
	String brakfastNameText = "Breakfast: ";
	String lunchNameText = "Lunch: ";
	String dinnerNameText = "Dinner: ";
	String snacksNameText = "Snacks: ";
	String exerciseNameText = "Exercise: ";
	ArrayList <FoodItem>foodItemsList;
	String violet = "#c25975";
	String lightBlue = "#39add1";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_meal);
		
		foodItemsList = new ArrayList<FoodItem>();
		
		mealsContainer = findViewById(R.id.mealsContainer);
		mealsComponentContainer =findViewById(R.id.mealsComponentContainer);
		View mealFragmentContainer = findViewById(
				R.id.mealsFragmentContainer);
		mealsContainer.setBackgroundColor(Color.parseColor(violet));
		mealsComponentContainer.setBackgroundColor(Color.WHITE);
		mealFragmentContainer.setBackgroundColor(Color.WHITE);
		budget = (TextView) findViewById(R.id.budget);
		food = (TextView) findViewById(R.id.food);
		exercise = (TextView) findViewById(R.id.exercise);
		net = (TextView) findViewById(R.id.net);
		status = (TextView) findViewById(R.id.status);
		
		budget.setText("Budget\n--");
		food.setText("Food\n--");
		exercise.setText("Exercise\n--");
		net.setText("Net\n--");
		status.setText("Status\n--");
		
		SingleMealFragment breakfast = new SingleMealFragment(brakfastNameText);
		SingleMealFragment lunch = new SingleMealFragment(lunchNameText);
		SingleMealFragment dinner = new SingleMealFragment(dinnerNameText);
		SingleMealFragment snacks = new SingleMealFragment(snacksNameText);
		SingleMealFragment exercise = new SingleMealFragment(exerciseNameText);

		getFragmentManager().beginTransaction().add(
				mealFragmentContainer.getId(), breakfast, breakfast.getMealsName())
				.commit();
		getFragmentManager().beginTransaction().add(
				mealFragmentContainer.getId(), lunch, lunch.getMealsName())
				.commit();
		getFragmentManager().beginTransaction().add(
				mealFragmentContainer.getId(), dinner, dinner.getMealsName())
				.commit();
		getFragmentManager().beginTransaction().add(
				mealFragmentContainer.getId(), snacks, snacks.getMealsName())
				.commit();
		getFragmentManager().beginTransaction().add(
				mealFragmentContainer.getId(), exercise, exercise.getMealsName())
				.commit();
	}

	@Override
	public void searchFoodItem(String mealsName) {
		
		Intent goToFoodItemList = new Intent(MealActivity.this, FoodItemListActivity.class);
		goToFoodItemList.putExtra(meals.MealActivity.MEAL_ACTIVITY_KEY, mealsName);//may cause an issue
		Log.d("RabiaMeals", mealsName);
		startActivity(goToFoodItemList);
		finish();
		
	}

	@Override
	public Context getcontext() {
		// TODO Auto-generated method stub
		return this;
	}
		
		/*SharedPreferences sharedPreferences =getSharedPreferences("storedMeals", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		HashMap fooditemsFromCash = (HashMap) sharedPreferences
				.getAll();

		editor.clear();
		editor.commit();

		Log.d("Before",
				((Integer) fooditemsFromCash.size()).toString());
		fooditemsFromCash.remove();
		Log.d("After",
				((Integer) storiesFromCash.size()).toString());

		Iterator iterator = fooditemsFromCash.entrySet().iterator();
		while (iterator.hasNext()) {
			HashMap.Entry pair = (HashMap.Entry) iterator.next();

			String storyKey = pair.getKey().toString();
			String storyValue = pair.getValue().toString();

			editor.putString(storyKey, storyValue);
			editor.commit();

			iterator.remove();
		}
		Toast.makeText(MealActivity.this,
				"Story Remouved From Favorites", Toast.LENGTH_LONG)
				.show();
		*/
	
	public void goToNewActivity(Activity Starting, Class destination){
		Intent goToNewActiviy = new Intent(Starting, destination);
		startActivity(goToNewActiviy);
		finish();;
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("Budget");
		menu.add("Meals");
		menu.add("Goals");
		menu.add("Program");
		menu.add("My Plan");
		menu.add("Log Out");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getTitle().equals("Budget")){
			goToNewActivity(MealActivity.this, BudgetActivity.class);
		}else if(item.getTitle().equals("Meals")){
			Toast.makeText(this, "You are alredy in the Meals Activity", Toast.LENGTH_LONG)
			.show();
		}else if(item.getTitle().equals("Goals")){
			goToNewActivity(MealActivity.this, GoalsActivity.class);
		}else if(item.getTitle().equals("Program")){
			goToNewActivity(MealActivity.this, ProgramActivity.class);
		}else if(item.getTitle().equals("My Plan")){
			goToNewActivity(MealActivity.this, PlanActivity.class);
		}else if(item.getTitle().equals("Log Out")){
			mealsContainer.setBackgroundColor(Color.RED);
			AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MealActivity.this);
			final AlertDialog logOutAlertDialog = alertBuilder.create();
			logOutAlertDialog.setTitle("Logout..!!");
			logOutAlertDialog.setMessage("ARE YOU SURE YOU WANT TO LOGOUT..!!");
			logOutAlertDialog.setCancelable(false);

			logOutAlertDialog.setButton(
					DialogInterface.BUTTON_POSITIVE, "Ok",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							Toast.makeText(MealActivity.this, "YOU ARE BEING LOGED OUT..!!", Toast.LENGTH_LONG)
							.show();	
							ParseUser.logOut();
							goToNewActivity(MealActivity.this, LoginSignUpActivity.class);
						}
					});

			logOutAlertDialog.setButton(
					DialogInterface.BUTTON_NEGATIVE, "Cancel",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							logOutAlertDialog.cancel();
							mealsContainer.setBackgroundColor(Color.parseColor(violet));

						}
					});
			logOutAlertDialog.show();
		}
		return super.onOptionsItemSelected(item);
	}
}
