package loginSignUp;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

import finalProject.group_2B.getfit.BudgetActivity;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginSignUpActivity extends Activity implements LoginFragment.OnFragmentInteractionListener, SignUpFragment.OnFragmentInteractionListener{

	EditText userName, password;
	Button login, createNewAccount;
	Fragment loginFragment, signUpFragment ;
	static String currentUser;
	View loginSignUpContainer;
	String violet = "#c25975";
	String lightBlue = "#39add1";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loginsignup);

		Parse.initialize(this, "nBLE3FN2G6a1FwSjdU9TdRz7luf3AgDL4mEetGLo",
		 "nSgAOsVYADOtLsDT63fLXSjB9GZQjO3Q3tfsZHRH");
		
		loginFragment = new LoginFragment();
		signUpFragment = new SignUpFragment();
		loginSignUpContainer = findViewById(R.id.loginSignUpContainer);
		View loginSignUpComponentsContainer = findViewById(R.id.loginSignUpComponentsContainer);
		loginSignUpContainer.setBackgroundColor(Color.parseColor(violet));
		loginSignUpComponentsContainer.setBackgroundColor(Color.WHITE);
		
		getFragmentManager().beginTransaction()
		.add(R.id.loginSignUpComponentsContainer, loginFragment, "loginFragment")
		.commit();
	}

	@Override
	public void goToSignUpFragment() {
		getFragmentManager().beginTransaction()
    	.replace(R.id.loginSignUpComponentsContainer, signUpFragment, "signUpFragment")
    	.addToBackStack(null)
    	.commit();
		Toast.makeText(this, "Fill in your Information to Create an Account..",
				Toast.LENGTH_LONG).show();
	}

	@Override
	public void goToLoginFragment() {
		getFragmentManager().beginTransaction()
		.replace(R.id.loginSignUpComponentsContainer, loginFragment)
		.addToBackStack(null)
		.commit();
		Toast.makeText(this, "The Signing Up Process has been Canceled..",
				Toast.LENGTH_LONG).show();
	}

	@Override
	public void goToBudgetActivity() {
		Intent goToBudget = new Intent(this,
    			BudgetActivity.class);
		startActivity(goToBudget);
		finish();
		
	}

	@Override
	public void paintContainer(int colorID) {
		loginSignUpContainer.setBackgroundColor(colorID);
		
	}
}
