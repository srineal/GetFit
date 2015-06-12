package finalProject.group_2B.getfit;

import java.util.ArrayList;

import com.parse.ParseUser;

import loginSignUp.LoginSignUpActivity;
import meals.MealActivity;
import meals.SingleMealFragment;
import meals.SingleMealFragment.OnFragmentInteractionListener;
import parsing.FoodItem;
import parsing.GetFoodItemAsyncTask;
import parsing.GetFoodItemAsyncTask.DataBridge;
import finalProject.group_2B.getfit.R;
import finalProject.group_2B.getfit.R.id;
import finalProject.group_2B.getfit.R.layout;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BudgetActivity extends Activity{
	
	View budgetContainer, budgetComponentContainer, budgetHeaderContainer, BudgetButtonsContainer;
	RelativeLayout budgetMealsHeader;
	Fragment budgetFragment, mealsFragment, foodItemListFragment;
	int fragmentContainerIntAdress;
	ImageButton  back, forward;
	Button meals, goals, program, plan, logout;
	TextView date;
	TextView title;
	String violet = "#c25975";
	String lightBlue = "#39add1";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_budget);
		
		budgetContainer = findViewById(R.id.budgetContainer);
		budgetComponentContainer = findViewById(R.id.budgetComponentContainer);
		BudgetButtonsContainer = findViewById(R.id.budgetButtonsContainer);
		budgetHeaderContainer = findViewById(R.id.budgetHeaderContainer);
		
		budgetContainer.setBackgroundColor(Color.parseColor(violet));
		//budgetComponentContainer.setBackgroundColor(Color.WHITE);
		BudgetButtonsContainer.setBackgroundColor(Color.parseColor(violet));
		//budgetHeaderContainer.setBackgroundColor(Color.WHITE);
		
		
		
		SharedPreferences sharedPreferences =getSharedPreferences("bestStories3", Context.MODE_PRIVATE);

		//budgetMealsHeader = (RelativeLayout) findViewById(R.id.budgetMealsHeader);
		//budgetMealsHeader.setBackgroundColor(Color.BLUE);
		back = (ImageButton) findViewById(R.id.back);
		forward = (ImageButton) findViewById(R.id.forward);
		date = (TextView) findViewById(R.id.date);
		title = (TextView) findViewById(R.id.budgetTitle);
		meals = (Button) findViewById(R.id.meals);
		goals = (Button) findViewById(R.id.goals);
		program = (Button)findViewById(R.id.program);
		plan = (Button) findViewById(R.id.plan);
		logout= (Button) findViewById(R.id.logout);
		
		meals.setBackgroundColor(Color.LTGRAY);
		goals.setBackgroundColor(Color.LTGRAY);
		program.setBackgroundColor(Color.LTGRAY);
		plan.setBackgroundColor(Color.LTGRAY);
		logout.setBackgroundColor(Color.DKGRAY);
		
		//date.setTextColor(Color.BLACK);
		
		title.setTextColor(Color.parseColor(lightBlue));
		meals.setTextColor(Color.BLACK);
		goals.setTextColor(Color.BLACK);
		program.setTextColor(Color.BLACK);
		plan.setTextColor(Color.BLACK);
		
		meals.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				meals.setBackgroundColor(Color.parseColor(lightBlue));
				goToNewActivity(BudgetActivity.this, MealActivity.class);
			}
		});
		
		goals.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				goals.setBackgroundColor(Color.parseColor(lightBlue));
				goToNewActivity(BudgetActivity.this, GoalsActivity.class);
			}
		});
		
		program.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				program.setBackgroundColor(Color.parseColor(lightBlue));
				goToNewActivity(BudgetActivity.this, ProgramActivity.class);
			}
		});
		
		plan.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				plan.setBackgroundColor(Color.parseColor(lightBlue));
				goToNewActivity(BudgetActivity.this, PlanActivity.class);
				
			}
		});
		
		logout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				logout.setBackgroundColor(Color.RED);
				budgetContainer.setBackgroundColor(Color.RED);
				AlertDialog.Builder alertBuilder = new AlertDialog.Builder(BudgetActivity.this);
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
								logout.setBackgroundColor(Color.DKGRAY);
								Toast.makeText(BudgetActivity.this, "YOU ARE BEING LOGED OUT..!!", Toast.LENGTH_LONG)
								.show();	
								ParseUser.logOut();
								goToNewActivity(BudgetActivity.this, LoginSignUpActivity.class);
							}
						});

				logOutAlertDialog.setButton(
						DialogInterface.BUTTON_NEGATIVE, "Cancel",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								logOutAlertDialog.cancel();
								logout.setBackgroundColor(Color.DKGRAY);
								budgetContainer.setBackgroundColor(Color.parseColor(violet));

							}
						});
				logOutAlertDialog.show();
			}
		});
	}
	
	public void goToNewActivity(Activity Starting, Class destination){
		Intent goToNewActiviy = new Intent(Starting, destination);
		startActivity(goToNewActiviy);
		finish();;
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
			Toast.makeText(this, "You are alredy in the Budget Activity", Toast.LENGTH_LONG)
			.show();
						
		}else if(item.getTitle().equals("Meals")){
			meals.performClick();
		}else if(item.getTitle().equals("Goals")){
			goals.performClick();
		}else if(item.getTitle().equals("Program")){
			program.performClick();
		}else if(item.getTitle().equals("Log Out")){
			logout.performClick();
		}
		return super.onOptionsItemSelected(item);
	}
}
