package com.sirialkillers.shoponthego.User_based_Activities;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sirialkillers.shoponthego.Controllers.AccountController;
import com.sirialkillers.shoponthego.Maps_Related_Activities.MapsActivity;
import com.sirialkillers.shoponthego.Models.AccountModel;
import com.sirialkillers.shoponthego.R;

import java.util.UUID;

public class SignUpActivity extends AppCompatActivity {
    /**
     * Keep track of the sign up task to ensure we can cancel it if requested.
     */
    SignUpValidation user;
    private UserRegisterTask mAuthTask = null;
    private ConstraintLayout mRegisterForm;
    private EditText username;
    private EditText email;
    private EditText password;
    private EditText verifyPassword;
    ProgressBar mProgressView;
    TextView loadingTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        username = (EditText) findViewById(R.id.usernameEditText);
        username.requestFocus();
        email = (EditText) findViewById(R.id.emailEditText);
        password = (EditText) findViewById(R.id.passwordEditText);
        verifyPassword = (EditText) findViewById(R.id.verifyPasswordEditText);
        Button registerAndLogin = (Button) findViewById(R.id.registerAndLoginButton);
        TextView haveAnAccount = (TextView) findViewById(R.id.goBackToLoginTextView);
        loadingTextView = (TextView) findViewById(R.id.loadingTextView);
        mProgressView = (ProgressBar) findViewById(R.id.progressBar);
        mRegisterForm = (ConstraintLayout) findViewById(R.id.registerFormView);
        verifyPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptRegister();
                    return true;
                }
                return false;
            }
        });
        registerAndLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptRegister();
            }


        });
        haveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLoginActivity();
            }
        });
    }

    private void hideKeyboard(){
        View view = this.getCurrentFocus();
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Setting the sign up to work if every choice is valid
     */
    private void attemptRegister() {
        hideKeyboard();
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        username.setError(null);
        email.setError(null);
        password.setError(null);
        verifyPassword.setError(null);

        // Store values at the time of the login attempt. s is for string
        String semail = email.getText().toString();
        String susername = username.getText().toString();
        String spassword = password.getText().toString();
        String sverify = verifyPassword.getText().toString();
        user = new SignUpValidation(susername, semail, spassword, sverify);

        boolean cancel = false;
        View focusView = null;

        // Check for a valid username, if the user entered one.

        if (!user.isUsernameNotEmpty()) {
            username.setError("Please enter a username");
            focusView = username;
            cancel = true;
        } else if (!user.UsernameContainsOnlyNumbersAndLetters()) {
            username.setError("Your username should only contain letters and numbers");
            focusView = username;
            cancel = true;
        } else if (!user.isUsernameLengthEnough()) {
            username.setError("Your username should be more than 5 characters.");
            focusView = username;
            cancel = true;
        } else if (!user.isUsernameLengthLessThanAmount()) {
            username.setError("Your username should be less than 25 characters.");
            focusView = username;
            cancel = true;
        }

        // Check for a valid email address.

        if (!user.isEmailNotEmpty()) {
            email.setError("This field is required");
            focusView = email;
            cancel = true;
        } else if (!user.isEmailValid()) {
            email.setError("Please enter a valid email.");
            focusView = email;
            cancel = true;
        }

        // Check for a valid password, if the user entered one.
        if(!user.isPasswordNotEmpty()){
            password.setError("This field is required");
            focusView = password;
            cancel = true;}
        else if (!user.isPasswordLengthEnough()) {
            password.setError("Your password should be at least 6 characters");
            focusView = password;
            cancel = true;
        } else {

            // Check if passwords are not empty and if they match
            if (!user.isVerifyNotEmpty()) {
                verifyPassword.setError("This field is required");
                focusView = verifyPassword;
                cancel = true;
            } else if (!user.isVerifyPasswordValid()) {
                verifyPassword.setError("Your passwords do not match");
                focusView = verifyPassword;
                cancel = true;
            }
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {

            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.

            mAuthTask = new UserRegisterTask(susername, semail, spassword);
            mAuthTask.execute((Void) null);
        }
    }







    /**
     * Represents an asynchronous registration task used to authenticate
     * the user.
     */
    public class UserRegisterTask extends AsyncTask<Void, Void, Boolean> {

        private final String mUsername;
        private final String mEmail;
        private final String mPassword;

        UserRegisterTask(String username, String email, String password) {
            mUsername = username;
            mEmail = email;
            mPassword = password;
        }


        /**
         * Shows the progress UI and hides the registration form.
         */
        @Override
        protected void onPreExecute() {
            mProgressView.setVisibility(View.VISIBLE);
            loadingTextView.setVisibility(View.VISIBLE);
            mRegisterForm.setVisibility(View.INVISIBLE);
        }

        @Override
        protected Boolean doInBackground(Void... params) {


            try {
                AccountModel accountModel = new AccountModel(UUID.randomUUID().toString(),mUsername,mEmail,mPassword);
                AccountController accountController = new AccountController();
                accountController.createAccount(accountModel);
            } catch (Exception e) {

                return false;
            }



            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {

            if (success) {
                goToMapsActivity();
                finish();
            } else{
                Toast.makeText(SignUpActivity.this, "Something went wrong please try again", Toast.LENGTH_SHORT).show();
                mProgressView.setVisibility(View.INVISIBLE);
                loadingTextView.setVisibility(View.INVISIBLE);
                mRegisterForm.setVisibility(View.VISIBLE);
                mAuthTask = null;
            }
        }



        @Override
        protected void onCancelled() {
            mAuthTask = null;
        }
    }
    private void goToMapsActivity() {
        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        startActivity(intent);
        finish();
    }
    private void goToLoginActivity() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
