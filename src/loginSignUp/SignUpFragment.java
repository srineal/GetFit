package loginSignUp;

import loginSignUp.LoginFragment.OnFragmentInteractionListener;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import finalProject.group_2B.getfit.BudgetActivity;
import finalProject.group_2B.getfit.R;
import finalProject.group_2B.getfit.R.id;
import finalProject.group_2B.getfit.R.layout;
import android.R.color;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpFragment extends Fragment {

	EditText firstname, lastName, email, password, confirmPassword;
	Button signUp, cancel;
	OnFragmentInteractionListener mListener;
	String violet = "#c25975";
	String lightBlue = "#39add1";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_sign_up, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		firstname = (EditText) getView().findViewById(R.id.firstName);
		lastName = (EditText) getView().findViewById(R.id.lastName);
		email = (EditText) getView().findViewById(R.id.signUpEmail);
		password = (EditText) getView().findViewById(R.id.signUpPassword);
		confirmPassword = (EditText) getView().findViewById(R.id.signUpConfrimPassword);
		signUp = (Button) getView().findViewById(R.id.signUp);
		cancel = (Button) getView().findViewById(R.id.cancel);
		
		signUp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final String firstNameText = firstname.getText().toString();
				String lastNameText = lastName.getText().toString();
				final String emailText = email.getText().toString();
				final String passwordText = password.getText().toString();
				String confirmPasswordText = confirmPassword.getText().toString();
				
				if(firstNameText.isEmpty()|| lastNameText.isEmpty()||emailText.isEmpty()
						||passwordText.isEmpty()||confirmPasswordText.isEmpty()){
					
					if(passwordText.isEmpty()||confirmPasswordText.isEmpty()){
					password.setText("");
					confirmPassword.setText("");
					}
					paintAll(Color.WHITE);
					paintCondition(Color.RED);
					mListener.paintContainer(Color.RED);
					Toast.makeText(getActivity(), "MISSING INFORMATION!!! Provide all the above information..",
							Toast.LENGTH_LONG).show();
				}
				else{
					if(passwordText.compareTo(confirmPasswordText)!= 0){
						Toast.makeText(getActivity(), "PASSWORD MISMATCH!!! REENTER YOUR PASSWORDS PLEASE..",
								Toast.LENGTH_LONG).show();
						password.setText("");
						confirmPassword.setText("");
						
						paintAll(Color.WHITE);
						paintCondition(Color.RED);
						mListener.paintContainer(Color.RED);
					}else{
						ParseUser user = new ParseUser();
						user.setUsername(emailText);
						user.setPassword(passwordText);
						user.setEmail(emailText);
						user.put("FirstName",firstNameText );
						user.put("LastName",lastNameText );
						 
						user.signUpInBackground(new SignUpCallback() {
						  public void done(ParseException e) {
						    if (e == null) {
						    	paintAll(Color.GREEN);
						    	mListener.paintContainer(Color.GREEN);
						    	
						    	Toast.makeText(getActivity(), "Thank you "+firstNameText +" for Signning Up to GetFit! App..",
										Toast.LENGTH_LONG).show();
						    	
						    	ParseUser.logInInBackground(emailText, passwordText, new LogInCallback() {
									  public void done(ParseUser user, ParseException e) {
									    if (user != null) {
									    	Intent goToBudgetMeals = new Intent(getActivity(),
									    			BudgetActivity.class);
											startActivity(goToBudgetMeals);
											getActivity().finish();
											Toast.makeText(getActivity(),"First User, "+ firstNameText +", You Have been logged on Automatically..",
													Toast.LENGTH_LONG).show();
											}
									    }
									  });
						    } else {
						    	paintAll(Color.WHITE);
						    	email.setBackgroundColor(Color.RED);
						    	Toast.makeText(getActivity(), "Sory "+firstNameText+"!!! The User Name chosen has already been Taken!!! Try again with a New One..",
										Toast.LENGTH_LONG).show();
						    }
						  }
						});
					}
				}
				
			}
		});
		
		cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mListener.goToLoginFragment();
				mListener.paintContainer(Color.parseColor(violet));
			}
		});
	}

	public void paintCondition(int color) {
		if (firstname.getText().length() == 0)
			firstname.setBackgroundColor(color);
		if (lastName.getText().length() == 0)
			lastName.setBackgroundColor(color);
		if (email.getText().length() == 0)
			email.setBackgroundColor(color);
		if (password.getText().length() == 0
				|| confirmPassword.getText().length() == 0) {
			password.setBackgroundColor(color);
			confirmPassword.setBackgroundColor(color);
		}
	}
	
	public void paintAll(int color){
			firstname.setBackgroundColor(color);
			lastName.setBackgroundColor(color);
			email.setBackgroundColor(color);
			password.setBackgroundColor(color);
			confirmPassword.setBackgroundColor(color);
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

	public interface OnFragmentInteractionListener {

		public void goToLoginFragment();
		public void paintContainer(int colorID);
	}
}
