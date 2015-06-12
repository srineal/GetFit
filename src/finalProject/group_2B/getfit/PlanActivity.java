package finalProject.group_2B.getfit;

import loginSignUp.LoginSignUpActivity;
import meals.MealActivity;
import model.Plan;

import com.google.gson.Gson;
import com.parse.ParseUser;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class PlanActivity extends Activity {

	View planContainer, planComponentsContainer;
	RadioGroup lossPerWeek;
	int checkedRadioButton = 0;
	Gson gson;
	Plan plan = null;
	TextView tv1, tv2, tv3;
	String violet = "#c25975";
	String lightBlue = "#39add1";
	
private int[] radioButtons = new int[]{R.id.radio0, R.id.radio1, R.id.radio2,R.id.radio3,R.id.radio4};
	
	public final static String[] plandetails = new String[]{
		"Maintain current weight", "Loose  1/2 lb per week", "Loose  1 lb per week", "Loose  1 1/2 lb per week", 
		"Loose  2 lb per week"
	};
	
	public final static int[] calorieBudget = new int[]{
		1500, 1400, 1300, 1150, 1000
	};
	
	private final String[] planDescription = new String[]{
		"to maintain current weight", "to achieve your goal weight by reducing 1/2 lb per week",
		"to achieve your goal weight by reducing  1 lb per week", "to achieve your goal weight by reducing  1 1/2 lb per week", 
		"to achieve your goal weight by reducing  2 lb per week"
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plan);
		
		planContainer = findViewById(R.id.planContainer);
		planComponentsContainer = findViewById(R.id.planComponentsContainer);
		View planCalorieBudgetTextView = findViewById(R.id.planCalorieBudgetTextView);
		View planGoalTextView = findViewById(R.id.planGoalTextView);
		View Note = findViewById(R.id.Note);
		
		planCalorieBudgetTextView.setBackgroundColor(Color.parseColor(violet));
		planGoalTextView.setBackgroundColor(Color.parseColor(violet));
		Note.setBackgroundColor(Color.LTGRAY);
		
		planContainer.setBackgroundColor(Color.parseColor(violet));
		planComponentsContainer.setBackgroundColor(Color.WHITE);
		
		lossPerWeek = (RadioGroup) findViewById(R.id.lossPerWeek);
		paint();
		lossPerWeek.getChildAt(0).setBackgroundColor(Color.parseColor(violet));

		
		lossPerWeek.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				paint();
				checkedRadioButton = group.indexOfChild(findViewById(checkedId));
				View checkedRadioButtonView = group.getChildAt(checkedRadioButton);
				checkedRadioButtonView.setBackgroundColor(Color.parseColor(violet));
				
				Log.d("Radio", checkedRadioButton+"");
			}
		});
	}
	
	public void paint(){
		lossPerWeek.getChildAt(0).setBackgroundColor(Color.LTGRAY);
		lossPerWeek.getChildAt(1).setBackgroundColor(Color.TRANSPARENT);
		lossPerWeek.getChildAt(2).setBackgroundColor(Color.LTGRAY);
		lossPerWeek.getChildAt(3).setBackgroundColor(Color.TRANSPARENT);
		lossPerWeek.getChildAt(4).setBackgroundColor(Color.LTGRAY);
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
			goToNewActivity(PlanActivity.this, BudgetActivity.class);	
		}else if(item.getTitle().equals("Meals")){
			goToNewActivity(PlanActivity.this, MealActivity.class);	
		}else if(item.getTitle().equals("Goals")){
			goToNewActivity(PlanActivity.this, GoalsActivity.class);
		}else if(item.getTitle().equals("Program")){
			goToNewActivity(PlanActivity.this, ProgramActivity.class);
		}else if(item.getTitle().equals("My Plan")){
			Toast.makeText(this, "You are alredy in the Plan Activity", Toast.LENGTH_LONG)
			.show();
		}else if(item.getTitle().equals("Log Out")){
			planContainer.setBackgroundColor(Color.RED);
			AlertDialog.Builder alertBuilder = new AlertDialog.Builder(PlanActivity.this);
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
							Toast.makeText(PlanActivity.this, "YOU ARE BEING LOGED OUT..!!", Toast.LENGTH_LONG)
							.show();	
							ParseUser.logOut();
							goToNewActivity(PlanActivity.this, LoginSignUpActivity.class);
						}
					});

			logOutAlertDialog.setButton(
					DialogInterface.BUTTON_NEGATIVE, "Cancel",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							logOutAlertDialog.cancel();
							planContainer.setBackgroundColor(Color.parseColor(violet));

						}
					});
			logOutAlertDialog.show();
			}
		return super.onOptionsItemSelected(item);
	}
}
