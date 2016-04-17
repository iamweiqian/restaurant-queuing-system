package wqyap762.rprqs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class RegisterActivity extends Activity {

    EditText hpnoText, nameText, usernameText, passwordText, confirmPasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        nameText = (EditText) findViewById(R.id.nameText);
        hpnoText = (EditText) findViewById(R.id.hpnoText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        confirmPasswordText = (EditText) findViewById(R.id.confirmPasswordText);

        // register account button
        final Button registerAccountButton = (Button) findViewById(R.id.registerAccountButton);
        registerAccountButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(final View v) {
                        // check empty fields
                        if (TextUtils.isEmpty(nameText.getText().toString())) {
                            nameText.setError("Please enter your full name.");
                            return;
                        } else if (TextUtils.isEmpty(hpnoText.getText().toString())) {
                            hpnoText.setError("Please enter your phone number.");
                            return;
                        } else if (TextUtils.isEmpty(passwordText.getText().toString())) {
                            passwordText.setError("Please enter password.");
                            return;
                        } else if (TextUtils.isEmpty(confirmPasswordText.getText().toString())) {
                            confirmPasswordText.setError("Please confirm your password.");
                            return;
                        }

                        // phone number should be in format
                        if (hpnoText.getText().toString().length() < 10) {
                            hpnoText.setError("Phone number must be at least 10 number!");
                            return;
                        }

                        // password > 8 char and mix
                        if (!isValidPassword(passwordText.getText().toString().trim())) {
                            passwordText.setError("Minimum 8 characters, at least 1 Uppercase Alphabet, 1 Lowercase Alphabet and 1 Number");
                            passwordText.setText("");
                            confirmPasswordText.setText("");
                            return;
                        }

                        // if all fields filled
                        if (!TextUtils.isEmpty(nameText.getText().toString()) && !TextUtils.isEmpty(hpnoText.getText().toString()) && !TextUtils.isEmpty(passwordText.getText().toString()) && !TextUtils.isEmpty(confirmPasswordText.getText().toString())) {
                            if (passwordText.getText().toString().equals(confirmPasswordText.getText().toString())) {
                                AlertDialog.Builder orderConfirm = new AlertDialog.Builder(RegisterActivity.this);

                                // setting dialog title
                                orderConfirm.setTitle("Confirm Register");

                                // setting dialog message
                                orderConfirm.setMessage("Do you want to continue?");

                                // setting icon to dialog
                                //orderConfirm.setIcon(R.drawable.save);

                                // setting positive "Proceed" button
                                orderConfirm.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // user press Proceed button. Write logic here
                                        registerButtonClicked(v);
                                    }
                                });

                                // setting neutral "Cancel" button
                                orderConfirm.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // user press Cancel button. Write login here
                                        dialog.dismiss();
                                    }
                                });

                                orderConfirm.show();
                            } else {
                                passwordText.setText("");
                                confirmPasswordText.setText("");
                                confirmPasswordText.setError("Your password does not match!");
                            }
                        }
                    }
                }
        );
    }

    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }

    public void registerButtonClicked(View v) {
        final String hpno = hpnoText.getText().toString();
        final String password = passwordText.getText().toString();
        final String name = nameText.getText().toString();
        final String user_state = "2";

        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        registrationDone();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                        builder.setMessage("Register Failed")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        RegisterRequest registerRequest = new RegisterRequest(hpno, password, name, Integer.parseInt(user_state), responseListener);
        RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
        queue.add(registerRequest);
    }

    public void registrationDone() {
        AlertDialog.Builder registrationDone = new AlertDialog.Builder(RegisterActivity.this);

        // setting dialog title
        registrationDone.setTitle("Register Successfully!");

        // setting dialog message
        registrationDone.setMessage("Your account has been registered successfully.");

        // setting icon to dialog
        //orderConfirm.setIcon(R.drawable.save);

        // setting positive "Okay" button
        registrationDone.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // user press Proceed button. Write logic here
                goToLoginActivity();
            }
        });
        registrationDone.show();
    }

    public void goToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
