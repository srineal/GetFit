package finalProject.group_2B.getfit;

import java.text.DateFormatSymbols;
import java.util.Date;

import com.parse.ParseUser;

import loginSignUp.LoginSignUpActivity;
import meals.MealActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProgramActivity extends Activity {
	
	View programContainer,programComponentsContainer;
	TextView startingWeight, currentWeight, goalsWeight, gender, height,
			age, myPlan, dailyCalorieBudget;

	Double startingWeightValue, currentWeightValue, goalsWeightValue,
			heightValue, myPlanValue, dailyCalorieBudgetValue;
	String genderValue;
	Integer ageValue;
	String violet = "#c25975";
	String lightBlue = "#39add1";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_program);
		
		programContainer = findViewById(R.id.programContainer);
		programComponentsContainer = findViewById(R.id.programComponentsContainer);
		
		programContainer.setBackgroundColor(Color.parseColor(violet));
		programComponentsContainer.setBackgroundColor(Color.WHITE);
		
		startingWeight = (TextView)findViewById(
				R.id.startingWeightView);
		currentWeight = (TextView)findViewById(
				R.id.currentWeightView);
		
		goalsWeight = (TextView)findViewById(R.id.goalsWeightView);
		gender = (TextView) findViewById(R.id.gender);
		height = (TextView)findViewById(R.id.height);
		age = (TextView)findViewById(R.id.age);
		myPlan = (TextView)findViewById(R.id.myPlan);
		dailyCalorieBudget = (TextView)findViewById(
				R.id.dailyCalorieBudget);
		
		//programActivityContainer.setBackgroundColor(Color.LTGRAY);
		startingWeight.setBackgroundColor(Color.LTGRAY);
		currentWeight.setBackgroundColor(Color.TRANSPARENT);
		goalsWeight.setBackgroundColor(Color.LTGRAY);
		gender.setBackgroundColor(Color.TRANSPARENT);
		height.setBackgroundColor(Color.LTGRAY);
		age.setBackgroundColor(Color.TRANSPARENT);
		myPlan.setBackgroundColor(Color.LTGRAY);
		dailyCalorieBudget.setBackgroundColor(Color.TRANSPARENT);

		startingWeight.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startingWeight.setBackgroundColor(Color.parseColor(violet));
				AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ProgramActivity.this);
				final AlertDialog startingWeightAlertDialog = alertBuilder.create();
				startingWeightAlertDialog.setTitle("Strting Weight..");
				startingWeightAlertDialog.setMessage("Enter the weight in.. (lbs)");
				final EditText editText = new EditText(ProgramActivity.this);
				startingWeightAlertDialog.setView(editText);
				startingWeightAlertDialog.setCancelable(false);

				startingWeightAlertDialog.setButton(
						DialogInterface.BUTTON_POSITIVE, "Ok",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								startingWeightValue = Double
										.parseDouble(editText
												.getText().toString());
								startingWeight.setText("Strting Weight		"+startingWeightValue
										.toString());
								startingWeight.setBackgroundColor(Color.LTGRAY);

							}
						});

				startingWeightAlertDialog.setButton(
						DialogInterface.BUTTON_NEGATIVE, "Cancel",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								startingWeightAlertDialog.cancel();
								startingWeight.setBackgroundColor(Color.LTGRAY);

							}
						});
				startingWeightAlertDialog.show();

			}
		});

		currentWeight.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				currentWeight.setBackgroundColor(Color.parseColor(violet));
				AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ProgramActivity.this);
				final AlertDialog currentWeightAlertDialog = alertBuilder.create();
				currentWeightAlertDialog.setTitle("Current Weight..");
				currentWeightAlertDialog.setMessage("Enter the weight in.. (lbs)");
				final EditText editText = new EditText(ProgramActivity.this);
				currentWeightAlertDialog.setView(editText);
				currentWeightAlertDialog.setCancelable(false);

				currentWeightAlertDialog.setButton(
						DialogInterface.BUTTON_POSITIVE, "Ok",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								currentWeightValue = Double
										.parseDouble(editText
												.getText().toString());
								currentWeight.setText("Current Weight		"+currentWeightValue
										.toString());
								currentWeight.setBackgroundColor(Color.TRANSPARENT);

							}
						});

				currentWeightAlertDialog.setButton(
						DialogInterface.BUTTON_NEGATIVE, "Cancel",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								currentWeightAlertDialog.cancel();
								currentWeight.setBackgroundColor(Color.TRANSPARENT);

							}
						});
				currentWeightAlertDialog.show();

			}
		});

		goalsWeight.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				goalsWeight.setBackgroundColor(Color.parseColor(violet));
				AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ProgramActivity.this);
				final AlertDialog goalsWeightAlertDialog = alertBuilder.create();
				goalsWeightAlertDialog.setTitle("Goal Weight..");
				goalsWeightAlertDialog.setMessage("Enter the weight in.. (lbs)");
				final EditText editText = new EditText(ProgramActivity.this);
				goalsWeightAlertDialog.setView(editText);
				goalsWeightAlertDialog.setCancelable(false);

				goalsWeightAlertDialog.setButton(
						DialogInterface.BUTTON_POSITIVE, "Ok",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								goalsWeightValue = Double
										.parseDouble(editText
												.getText().toString());
								goalsWeight.setText("Goal Weight		"+goalsWeightValue
										.toString());
								goalsWeight.setBackgroundColor(Color.LTGRAY);

							}
						});

				goalsWeightAlertDialog.setButton(
						DialogInterface.BUTTON_NEGATIVE, "Cancel",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								goalsWeightAlertDialog.cancel();
								goalsWeight.setBackgroundColor(Color.LTGRAY);

							}
						});
				goalsWeightAlertDialog.show();

			}
		});
		
		gender.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				gender.setBackgroundColor(Color.parseColor(violet));
				AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ProgramActivity.this);
				final AlertDialog genderAlertDialog = alertBuilder.create();
				genderAlertDialog.setTitle("Genger..");
				genderAlertDialog.setMessage("Pick Your Gender..");
				genderAlertDialog.setCancelable(false);
				
				genderAlertDialog.setButton(
						DialogInterface.BUTTON_POSITIVE, "Male",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								genderValue = "Male";
								gender.setText("Gender			Male");
								gender.setBackgroundColor(Color.TRANSPARENT);
							}
						});
				
				genderAlertDialog.setButton(
						DialogInterface.BUTTON_NEGATIVE, "Female",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								genderValue = "Female";
								gender.setText("Gender			Female");
								gender.setBackgroundColor(Color.TRANSPARENT);

							}
						});
				
				genderAlertDialog.show();
				
			}
		});
		
		height.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				height.setBackgroundColor(Color.parseColor(violet));
				AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ProgramActivity.this);
				final AlertDialog hightAlertDialog = alertBuilder.create();
				hightAlertDialog.setTitle("Height..");
				hightAlertDialog.setMessage("Enter Your Height in.. (f.in)");
				final EditText editText = new EditText(ProgramActivity.this);
				hightAlertDialog.setView(editText);
				hightAlertDialog.setCancelable(false);

				hightAlertDialog.setButton(
						DialogInterface.BUTTON_POSITIVE, "Ok",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								heightValue = Double
										.parseDouble(editText
												.getText().toString());
								height.setText("Height		"+heightValue
										.toString());
								height.setBackgroundColor(Color.LTGRAY);

							}
						});

				hightAlertDialog.setButton(
						DialogInterface.BUTTON_NEGATIVE, "Cancel",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								hightAlertDialog.cancel();
								height.setBackgroundColor(Color.LTGRAY);

							}
						});
				hightAlertDialog.show();

				
			}
		});
		
		age.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				age.setBackgroundColor(Color.parseColor(violet));
				AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ProgramActivity.this);
				final AlertDialog birthdayAlertDialog = alertBuilder.create();
				birthdayAlertDialog.setTitle("Age..");
				birthdayAlertDialog.setMessage("Enter Your Age..");
				final EditText editText = new EditText(ProgramActivity.this);
				birthdayAlertDialog.setView(editText);
				birthdayAlertDialog.setCancelable(false);

				birthdayAlertDialog.setButton(
						DialogInterface.BUTTON_POSITIVE, "Ok",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								ageValue= Integer.parseInt(editText.getText().toString());
								age.setText("Age		"+ageValue
										.toString());
								age.setBackgroundColor(Color.TRANSPARENT);

							}
						});

				birthdayAlertDialog.setButton(
						DialogInterface.BUTTON_NEGATIVE, "Cancel",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								birthdayAlertDialog.cancel();
								age.setBackgroundColor(Color.TRANSPARENT);

							}
						});
				birthdayAlertDialog.show();

				
			}
		});
		
		myPlan.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			myPlan.setBackgroundColor(Color.parseColor(lightBlue));
			Intent goToPlan = new Intent(ProgramActivity.this, PlanActivity.class);
			startActivity(goToPlan);
			finish();
				
			}
		});
		
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
			goToNewActivity(ProgramActivity.this, BudgetActivity.class);				
		}else if(item.getTitle().equals("Meals")){
			goToNewActivity(ProgramActivity.this, MealActivity.class);
		}else if(item.getTitle().equals("Goals")){
			goToNewActivity(ProgramActivity.this, GoalsActivity.class);
		}else if(item.getTitle().equals("Program")){
			Toast.makeText(this, "You are alredy in the Program Activity", Toast.LENGTH_LONG)
			.show();
		}else if(item.getTitle().equals("My Plan")){
			goToNewActivity(ProgramActivity.this, PlanActivity.class);
		}else if(item.getTitle().equals("Log Out")){
			programContainer.setBackgroundColor(Color.RED);
			AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ProgramActivity.this);
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
							Toast.makeText(ProgramActivity.this, "YOU ARE BEING LOGED OUT..!!", Toast.LENGTH_LONG)
							.show();	
							ParseUser.logOut();
							goToNewActivity(ProgramActivity.this, LoginSignUpActivity.class);
						}
					});

			logOutAlertDialog.setButton(
					DialogInterface.BUTTON_NEGATIVE, "Cancel",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							logOutAlertDialog.cancel();
							programContainer.setBackgroundColor(Color.parseColor(violet));

						}
					});
			logOutAlertDialog.show();
		}
		return super.onOptionsItemSelected(item);
	}

}
