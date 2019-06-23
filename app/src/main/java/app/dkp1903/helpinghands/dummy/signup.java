package app.dkp1903.helpinghands.dummy;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import app.dkp1903.helpinghands.LoginActivity;
import app.dkp1903.helpinghands.R;
import app.dkp1903.helpinghands.TaskListActivity;

public class signup extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private AutoCompleteTextView mEmailView;
    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;
    private EditText mConfirmPasswordView;
    private Button signUpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Log.d("Debug1","onCreate Launched");

        mAuth= FirebaseAuth.getInstance();
        mEmailView = (AutoCompleteTextView) findViewById(R.id.register_email);
        mPasswordView = (EditText) findViewById(R.id.register_password);
        mConfirmPasswordView = (EditText) findViewById(R.id.register_confirm_password);
        mUsernameView = (AutoCompleteTextView) findViewById(R.id.register_username);
        signUpButton = (Button) findViewById(R.id.register_sign_up_button);

        // Keyboard sign in action
        /*mConfirmPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.register_confirm_password || id == EditorInfo.IME_NULL) {
                    attemptRegistration();
                    return true;
                }
                return false;
            }
        });*/
        attemptRegistration();

    }

    private void attemptRegistration() {

        // Reset errors displayed in the form.
        mEmailView.setError(null);
        mPasswordView.setError(null);
        Log.d("Debug1","attemptReg.() called");

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = (View) mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            createNewUser();

        }

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Debug1","Sign up button pressed");
                if(v== signUpButton){
                    createNewUser();
                }

            }
        });

    }

    private boolean isEmailValid(String email)
    {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password)
    {
        String confirmPassword= mConfirmPasswordView.getText().toString();
        return confirmPassword.equals(password) && password.length() > 4;
    }

    private void createNewUser()
    {
        String email= mEmailView.getText().toString();
        String password= mPasswordView.getText().toString();
        Log.d("Debug1","createNewUser() called");

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("Debug Auth", "Create User" + task.isSuccessful());
                if(!task.isSuccessful())
                {
                    Log.d("Debug Auth", "Failed");
                    showErrorDialog("Registration Failed");
                }else
                {
                    showSucessDialog("Registration Successful");
                }
            }
        });
    }

    private void showErrorDialog(String message)
    {
        new AlertDialog.Builder(this)
                .setTitle("Oops, something went wrong")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void showSucessDialog(String message)
    {
        new AlertDialog.Builder(this)
                .setTitle("Congratulations!")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getBaseContext(), TaskListActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }


}
