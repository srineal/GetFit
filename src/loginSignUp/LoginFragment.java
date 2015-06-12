package loginSignUp;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginFragment extends Fragment {
	
	EditText userName, password;
	Button login, createNewAccount;
	private OnFragmentInteractionListener mListener;
	String violet = "#c25975";
	String lightBlue = "#39add1";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_login, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		
		super.onActivityCreated(savedInstanceState);
		
		userName = (EditText) getView().findViewById(R.id.userName);
		password = (EditText) getView().findViewById(R.id.password);
		login = (Button) getView().findViewById(R.id.login);
		createNewAccount = (Button) getView().findViewById(R.id.createNewAccount);

		checkForCurrentUser();
		login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final String userNameText = userName.getText().toString();
				final String passwordText =password.getText().toString();
				
				if (userNameText.isEmpty() || passwordText.isEmpty()){
					userName.setText("");
			    	password.setText("");
					paint(Color.RED);
					mListener.paintContainer(Color.RED);
					Toast.makeText(getActivity(), "Please enter BOTH YOUR USERNAME AND PASSWORD..",
							Toast.LENGTH_LONG).show();
				}else{
					ParseUser.logInInBackground(userNameText, passwordText, new LogInCallback() {
						  public void done(ParseUser user, ParseException e) {
						    if (user != null) {
						    	paint(Color.GREEN);
						    	mListener.paintContainer(Color.GREEN);
						     	mListener.goToBudgetActivity();
						    } else {
						    	userName.setText("");
						    	password.setText("");
						    	paint(Color.RED);
						    	Toast.makeText(getActivity(), "WRONG UserName/Password!!! Try Again..",
										Toast.LENGTH_LONG).show();
						    	}
						  }

						});
				}
			}
		});
		
		createNewAccount.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				paint(Color.WHITE);
				mListener.paintContainer(Color.parseColor(violet));
				mListener.goToSignUpFragment();
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

	private void checkForCurrentUser() {
		ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser != null) {
			String displayMessage = "Welcome Back " + currentUser.getUsername();
			Toast.makeText(getActivity(), displayMessage, Toast.LENGTH_LONG)
			.show();
			mListener.goToBudgetActivity();
		} else {
			Toast.makeText(getActivity(), "Enter your Credentials to Sign in",
					Toast.LENGTH_LONG).show();
		}

	}
	
	public void paint(int color){
    	userName.setBackgroundColor(color);
    	password.setBackgroundColor(color);
	}
	
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		public void goToSignUpFragment();
		public void goToBudgetActivity();
		public void paintContainer(int colorID);
	}
		
}
