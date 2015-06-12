package meals;

import java.util.ArrayList;

import org.w3c.dom.Text;

import parsing.FoodItem;
import parsing.GetFoodItemAsyncTask;
import parsing.GetFoodItemAsyncTask.DataBridge;
import finalProject.group_2B.getfit.R;
import finalProject.group_2B.getfit.R.layout;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout.Alignment;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class PreviewFoodItemActivity extends Activity implements
		GetFoodItemAsyncTask.DataBridge {

	final String APILink = "http://api.nal.usda.gov/usda/ndb/nutrients/?format=xml&api_key=ZJlnupS1AxCii3Io32hPgj2bhtPZfALl8UlhLRRP&nutrients=205&nutrients=203&nutrients=204&nutrients=208&nutrients=269&ndbno=";
	Button cancel, add;
	ImageButton increase, decrease;
	TextView name, weight, measure, protein, sugar, fat, carbs, energy;
	double WEIGHT, MEASURE, PROTEIN, SUGAR, FAT, CARBS, ENERGY;
	FoodItem foodItem;
	SharedPreferences sharedPreferences;
	View proteinLayout, sugarLayout, fatLayout, carbsLayout, energyLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preview_food_item);

		name = (TextView) findViewById(R.id.name);
		proteinLayout = findViewById(R.id.proteinLayout);
		sugarLayout = findViewById(R.id.sugarLayout);
		fatLayout = findViewById(R.id.fatLayout);
		carbsLayout = findViewById(R.id.carbsLayout);
		energyLayout = findViewById(R.id.energyLayout);

		proteinLayout.setBackgroundColor(Color.BLUE);
		sugarLayout.setBackgroundColor(Color.MAGENTA);
		fatLayout.setBackgroundColor(Color.YELLOW);
		carbsLayout.setBackgroundColor(Color.GREEN);
		energyLayout.setBackgroundColor(Color.RED);

		Intent getData = getIntent();
		foodItem = (FoodItem) getData
				.getSerializableExtra(FoodItemListActivity.FOOD_ITEM_LIST_KEY);

		String foodItemNDBNO = foodItem.getNdbno();
		Log.d("Jalal", foodItemNDBNO);
		Log.d("Amjad", foodItem.getName());
		Log.d("Rabia", foodItem.getMealsName());
		new GetFoodItemAsyncTask(this).execute(APILink + foodItemNDBNO);

		cancel = (Button) findViewById(R.id.foodItemCancel);
		add = (Button) findViewById(R.id.foodItemAdd);

		increase = (ImageButton) findViewById(R.id.increase);
		decrease = (ImageButton) findViewById(R.id.decrease);

		name = (TextView) findViewById(R.id.name);
		weight = (TextView) findViewById(R.id.weight);
		measure = (TextView) findViewById(R.id.measure);
		protein = (TextView) findViewById(R.id.protein);
		protein.setBackgroundColor(Color.BLUE);
		sugar = (TextView) findViewById(R.id.sugar);
		fat = (TextView) findViewById(R.id.fat);
		carbs = (TextView) findViewById(R.id.carbs);
		energy = (TextView) findViewById(R.id.energy);

		cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		add.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String stringToStore = foodItem.getMealsName() + ";;;"
						+ foodItem.getNdbno() + ";;;" + foodItem.getName()
						+ ";;;" + foodItem.getWeight() + ";;;"
						+ foodItem.getMeasure() + ";;;" + foodItem.getProtein()
						+ ";;;" + foodItem.getSugar() + ";;;"
						+ foodItem.getFat() + ";;;" + foodItem.getCarbs()
						+ ";;;" + foodItem.getEnergy();
				sharedPreferences = getSharedPreferences("storedMeals",
						Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = sharedPreferences.edit();
				editor.putString(foodItem.getNdbno(), stringToStore);
				editor.commit();
				Toast.makeText(
						PreviewFoodItemActivity.this,
						"Food Item was added to your "
								+ foodItem.getMealsName(), Toast.LENGTH_LONG)
						.show();
				finish();
			}
		});

		increase.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				increase();
				
			}
		});

		decrease.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				decrease();
			}
		});
		
		weight.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder alertBuilder = new AlertDialog.Builder(PreviewFoodItemActivity.this);
				final AlertDialog editWeight = alertBuilder.create();
				editWeight.setTitle("Weight..");
				editWeight.setMessage("Enter the Quantity..(in grams)");
				final EditText editWeightText = new EditText(PreviewFoodItemActivity.this);
				editWeight.setView(editWeightText);
				editWeight.setCancelable(false);
				editWeight.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						weight.setText(editWeightText.getText().toString());
						compute();
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

	@Override
	public void setup(ArrayList<FoodItem> foodItemList) {
		FoodItem foodItem = foodItemList.get(0);
		Double doubleValue;
		name.setText(foodItem.getName());

		if (!foodItem.getWeight().equals("--")) {
			doubleValue = Double.parseDouble(foodItem.getWeight());
			WEIGHT = doubleValue;
			weight.setText(doubleValue.toString());
		} else
			weight.setText("N/A");

		measure.setText(foodItem.getMeasure());

		if (!foodItem.getProtein().equals("--")) {
			doubleValue = Double.parseDouble(foodItem.getProtein());
			PROTEIN = doubleValue;
			protein.setText(doubleValue.toString());
		} else
			protein.setText("N/A");

		if (!foodItem.getSugar().equals("--")) {
			doubleValue = Double.parseDouble(foodItem.getSugar());
			SUGAR = doubleValue;
			sugar.setText(doubleValue.toString());
		} else
			sugar.setText("N/A");

		if (!foodItem.getFat().equals("--")) {
			doubleValue = Double.parseDouble(foodItem.getFat());
			FAT = doubleValue;
			fat.setText(doubleValue.toString());
		} else
			fat.setText("N/A");

		if (!foodItem.getCarbs().equals("--")) {
			doubleValue = Double.parseDouble(foodItem.getCarbs());
			CARBS = doubleValue;
			carbs.setText(doubleValue.toString());
		} else
			carbs.setText("N/A");

		if (!foodItem.getEnergy().equals("--")) {
			doubleValue = Double.parseDouble(foodItem.getEnergy());
			ENERGY = doubleValue;
			energy.setText(doubleValue.toString());
		} else
			energy.setText("N/A");
	}
	
	public void increase(){
		Double doubleValue = Double.parseDouble(weight.getText()
				.toString());
		if (doubleValue != 0) {
			doubleValue += WEIGHT;
			weight.setText(doubleValue.toString());
		}

		doubleValue = Double.parseDouble(protein.getText().toString());
		if (doubleValue != 0) {
			doubleValue += PROTEIN;
			protein.setText(doubleValue.toString());
		}
		doubleValue = Double.parseDouble(sugar.getText().toString());
		if (doubleValue != 0) {
			doubleValue += SUGAR;
			sugar.setText(doubleValue.toString());
		}
		doubleValue = Double.parseDouble(fat.getText().toString());
		if (doubleValue != 0) {
			doubleValue += FAT;
			fat.setText(doubleValue.toString());
		}
		doubleValue = Double.parseDouble(carbs.getText().toString());
		if (doubleValue != 0) {
			doubleValue += CARBS;
			carbs.setText(doubleValue.toString());
		}
		doubleValue = Double.parseDouble(energy.getText().toString());
		if (doubleValue != 0) {
			doubleValue += ENERGY;
			energy.setText(doubleValue.toString());
		}
	}
	
	public void decrease(){
		Double doubleValue = Double.parseDouble(weight.getText()
				.toString());
		if (doubleValue != 0) {
			doubleValue -= WEIGHT;
			weight.setText(doubleValue.toString());
		}

		doubleValue = Double.parseDouble(protein.getText().toString());
		if (doubleValue != 0) {
			doubleValue -= PROTEIN;
			protein.setText(doubleValue.toString());
		}
		doubleValue = Double.parseDouble(sugar.getText().toString());
		if (doubleValue != 0) {
			doubleValue -= SUGAR;
			sugar.setText(doubleValue.toString());
		}
		doubleValue = Double.parseDouble(fat.getText().toString());
		if (doubleValue != 0) {
			doubleValue -= FAT;
			fat.setText(doubleValue.toString());
		}
		doubleValue = Double.parseDouble(carbs.getText().toString());
		if (doubleValue != 0) {
			doubleValue -= CARBS;
			carbs.setText(doubleValue.toString());
		}
		doubleValue = Double.parseDouble(energy.getText().toString());
		if (doubleValue != 0) {
			doubleValue -= ENERGY;
			energy.setText(doubleValue.toString());
		}
	}
	
	public void compute(){
		Double doubleValue = Double.parseDouble(weight.getText()
				.toString());
		if (doubleValue != 0) {
			Double rate = (double) doubleValue/WEIGHT;
			
			doubleValue = Double.parseDouble(protein.getText().toString());
			if (doubleValue != 0) {
				doubleValue += doubleValue * rate;
				protein.setText(doubleValue.toString());
			}
			doubleValue = Double.parseDouble(sugar.getText().toString());
			if (doubleValue != 0) {
				doubleValue += doubleValue * rate;
				sugar.setText(doubleValue.toString());
			}
			doubleValue = Double.parseDouble(fat.getText().toString());
			if (doubleValue != 0) {
				doubleValue += doubleValue * rate;
				fat.setText(doubleValue.toString());
			}
			doubleValue = Double.parseDouble(carbs.getText().toString());
			if (doubleValue != 0) {
				doubleValue += doubleValue * rate;
				carbs.setText(doubleValue.toString());
			}
			doubleValue = Double.parseDouble(energy.getText().toString());
			if (doubleValue != 0) {
				doubleValue += doubleValue * rate;
				energy.setText(doubleValue.toString());
			}
		}else{
			protein.setText(doubleValue.toString());
			sugar.setText(doubleValue.toString());
			fat.setText(doubleValue.toString());
			carbs.setText(doubleValue.toString());
			energy.setText(doubleValue.toString());
		}
	}

	@Override
	public Context getContext() {
		// TODO Auto-generated method stub
		return this;
	}

}
