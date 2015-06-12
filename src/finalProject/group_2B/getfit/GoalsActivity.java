package finalProject.group_2B.getfit;

import java.util.HashMap;
import java.util.Iterator;

import parsing.FoodItem;

import com.parse.ParseUser;

import loginSignUp.LoginSignUpActivity;
import meals.MealActivity;
import meals.PreviewFoodItemActivity;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GoalsActivity extends Activity {

	View goalsContainer, goalsComponentsContainer;
	TextView todaysWeightLable;
	Double currentsWeight;
	String todaysWeightName = "Weight ";
	String todaysWeightUnit = " lbs";
	String violet = "#c25975";
	String lightBlue = "#39add1";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goals);
		
		goalsContainer = findViewById(R.id.goalsContainer);
		goalsComponentsContainer = findViewById(R.id.goalsComponentsContainer);
		
		goalsContainer.setBackgroundColor(Color.parseColor(violet));
		goalsComponentsContainer.setBackgroundColor(Color.WHITE);
		todaysWeightLable = (TextView) findViewById(R.id.goalsTodysWeight);
	
		readFromSharedPreferences();
		
		todaysWeightLable.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder alertBuilder = new AlertDialog.Builder(GoalsActivity.this);
				final AlertDialog editWeight = alertBuilder.create();
				editWeight.setTitle("Weight..");
				editWeight.setMessage("Enter Today's Weight..(in lbs)");
				final EditText editWeightText = new EditText(GoalsActivity.this);
				editWeight.setView(editWeightText);
				editWeight.setCancelable(false);
				editWeight.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						currentsWeight = Double.parseDouble(editWeightText.getText().toString());
						todaysWeightLable.setText(todaysWeightName +currentsWeight+ todaysWeightUnit);
					}
				});
				editWeight.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						editWeight.cancel();
					}
				});
				
				editWeight.show();
			}
		});		
	}
	
	public void saveToSharedPreferences(double currentWeight ){
		ParseUser currentUser = ParseUser.getCurrentUser();
		String stringToStore = currentWeight +"";
		SharedPreferences sharedPreferences = getSharedPreferences("Weights",
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(currentUser.getUsername(), stringToStore);
		editor.commit();
		Toast.makeText(
				GoalsActivity.this,
				"Your Current Weight is "+ currentWeight, Toast.LENGTH_LONG)
				.show();
	}
	
	public void readFromSharedPreferences(){
		/*SharedPreferences sharedPreferences = getSharedPreferences("Weights", Context.MODE_PRIVATE);
		HashMap storedfoodItems =  (HashMap) sharedPreferences.getAll();
		Iterator iterator = storedfoodItems.entrySet().iterator();

		while (iterator.hasNext()){
			HashMap.Entry pair= (HashMap.Entry) iterator.next();
			String foodItemPhrase= pair.getValue().toString();
			Log.d("Mohaammed", foodItemPhrase);
			String [] foodItemAtributes = foodItemPhrase.split(";;;");
			FoodItem foodItem = new FoodItem();
			foodItem.setMealsName(foodItemAtributes[0]);
			foodItem.setNdbno(foodItemAtributes[1]);
			foodItem.setName(foodItemAtributes[2]);
			foodItem.setWeight(foodItemAtributes[3]);
			foodItem.setMeasure(foodItemAtributes[4]);
			foodItem.setProtein(foodItemAtributes[5]);
			foodItem.setSugar(foodItemAtributes[6]);
			foodItem.setFat(foodItemAtributes[7]);
			foodItem.setCarbs(foodItemAtributes[8]);
			foodItem.setEnergy(foodItemAtributes[9]);
			
			if(mealsName.equals(foodItem.getMealsName())){
				foodItemArrayList.add(foodItem);
			Log.d("compare", mealsName+" "+ foodItem.getMealsName());
			}
			
			iterator.remove();*/
	}

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
			goToNewActivity(GoalsActivity.this, BudgetActivity.class);
		}else if(item.getTitle().equals("Meals")){
			goToNewActivity(GoalsActivity.this, MealActivity.class);
		}else if(item.getTitle().equals("Goals")){
			Toast.makeText(this, "You are alredy in the Goals Activity", Toast.LENGTH_LONG)
			.show();
		}else if(item.getTitle().equals("Program")){
			goToNewActivity(GoalsActivity.this, ProgramActivity.class);
		}else if(item.getTitle().equals("My Plan")){
			goToNewActivity(GoalsActivity.this, PlanActivity.class);
		}else if(item.getTitle().equals("Log Out")){
			goalsContainer.setBackgroundColor(Color.RED);
			AlertDialog.Builder alertBuilder = new AlertDialog.Builder(GoalsActivity.this);
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
							Toast.makeText(GoalsActivity.this, "YOU ARE BEING LOGED OUT..!!", Toast.LENGTH_LONG)
							.show();	
							ParseUser.logOut();
							goToNewActivity(GoalsActivity.this, LoginSignUpActivity.class);
						}
					});

			logOutAlertDialog.setButton(
					DialogInterface.BUTTON_NEGATIVE, "Cancel",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							logOutAlertDialog.cancel();
							goalsContainer.setBackgroundColor(Color.parseColor(violet));

						}
					});
			logOutAlertDialog.show();
		}
		return super.onOptionsItemSelected(item);
	}
}
