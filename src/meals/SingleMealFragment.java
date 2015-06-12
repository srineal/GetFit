package meals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import parsing.FoodItem;
import parsing.FoodItemAdapter;
import finalProject.group_2B.getfit.R;
import finalProject.group_2B.getfit.R.id;
import finalProject.group_2B.getfit.R.layout;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SingleMealFragment extends Fragment {
	
	String mealsName;
	ImageButton addToMeal;
	ListView fragmentSingleMealListView;
	ArrayList<FoodItem> foodItemArrayList;
	private OnFragmentInteractionListener mListener;

	public SingleMealFragment(String mealsName) {
		this.mealsName = mealsName;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_single_meal, container,
				false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		foodItemArrayList = new ArrayList();
		
		TextView mealsNameLable = (TextView) getView().findViewById(R.id.mealsName);
		mealsNameLable.setText(getMealsName());
		
		fragmentSingleMealListView = (ListView) getView().findViewById(R.id.fragmentSingleMealListView);
		
		loadFoodItems();
		Log.d(mealsName,foodItemArrayList.size()+"");
		//mListener.loadFoodItems(getMealsName(), fragmentSingleMealListView, foodItemArrayList);
		
		addToMeal = (ImageButton) getView().findViewById(R.id.addToMeal);
		
		addToMeal.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mListener.searchFoodItem(getMealsName());
				
			}
		});
		
		fragmentSingleMealListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					final int position, long id) {
				AlertDialog.Builder alertBuilder = new AlertDialog.Builder(mListener.getcontext());
				AlertDialog alertDelete = alertBuilder.create();
				
				alertDelete.setTitle("Delete");
				alertDelete.setMessage("Confirm your Choice Please..");
				alertDelete.setButton(DialogInterface.BUTTON_POSITIVE, "Delete", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						SharedPreferences sharedPreferences =getActivity().getSharedPreferences("storedMeals", Context.MODE_PRIVATE);
						SharedPreferences.Editor editor = sharedPreferences.edit();
						HashMap fooditemsFromCash = (HashMap) sharedPreferences
								.getAll();

						editor.clear();
						editor.commit();

						Log.d("Before",
								((Integer) fooditemsFromCash.size()).toString());
						FoodItem foodItem = foodItemArrayList.get(position);
						fooditemsFromCash.remove(foodItem.getNdbno());
						Log.d("After",
								((Integer) fooditemsFromCash.size()).toString());

						Iterator iterator = fooditemsFromCash.entrySet().iterator();
						while (iterator.hasNext()) {
							HashMap.Entry pair = (HashMap.Entry) iterator.next();

							String foodItemyKey = pair.getKey().toString();
							String foodItemValue = pair.getValue().toString();

							editor.putString(foodItemyKey, foodItemValue);
							editor.commit();

							iterator.remove();
						}
						foodItemArrayList.remove(position);
						fragmentSingleMealListView.removeViews(position, 0);
						
						
						Toast.makeText(mListener.getcontext(),
								"Story Remouved From Favorites", Toast.LENGTH_LONG)
								.show();
						/*AlertDialog alertDeleat = new AlertDialog();
						FoodItem foodItem = foodItemArrayList.get(position);
						Log.d("hna", foodItem.toString());
						Intent goToPreview = new Intent(
								getActivity(),
								PreviewFoodItemActivity.class);
						goToPreview.putExtra(FoodItemListActivity.FOOD_ITEM_LIST_KEY,foodItem);
						startActivity(goToPreview);*/
						
					}
				} );
				
				alertDelete.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
						
					}
				});
				
				alertDelete.setCancelable(false);
				
				alertDelete.show();
				
			
				return false;
			}
		});
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnFragmentInteractionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}

	public String getMealsName() {
		return mealsName;
	}
	
	public void loadFoodItems(){
		SharedPreferences sharedPreferences = getActivity().getSharedPreferences("storedMeals", Context.MODE_PRIVATE);
		HashMap storedfoodItems =  (HashMap) sharedPreferences.getAll();
		Iterator iterator = storedfoodItems.entrySet().iterator();

		while (iterator.hasNext()){
			HashMap.Entry pair= (HashMap.Entry) iterator.next();
			String foodItemPhrase= pair.getValue().toString();
			Log.d("Mohaammed", foodItemPhrase);//null misses up the splitting with commas
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
			
			iterator.remove();
		}
		setup();
	}
	
public void setup(){
		
		FoodItemAdapter adapter = new FoodItemAdapter(getActivity(),
				R.layout.row_item_layout, foodItemArrayList);
		adapter.setNotifyOnChange(true);
		
		//if(foodItemsList.size()>0){
		fragmentSingleMealListView.setAdapter(adapter);
		//}
		Log.d(mealsName,foodItemArrayList.size()+"");
	}	
	
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		public void searchFoodItem(String mealsName);
		public Context getcontext();
			
	}

}
