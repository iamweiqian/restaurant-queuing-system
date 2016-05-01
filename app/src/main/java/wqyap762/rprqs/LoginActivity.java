package wqyap762.rprqs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.content.Intent;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {

    private Toolbar toolbar;
    EditText hpnoText, passwordText;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        hpnoText = (EditText) findViewById(R.id.hpnoText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        // sign in button
        Button signiInButton = (Button) findViewById(R.id.signInButton);
        signiInButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(hpnoText.getText().toString())) {
                            hpnoText.setError("Please enter username");
                            return;
                        } else if (TextUtils.isEmpty(passwordText.getText().toString())) {
                            passwordText.setError("Please enter password");
                            return;
                        } else if (TextUtils.isEmpty(hpnoText.getText().toString()) && TextUtils.isEmpty(passwordText.getText().toString())) {
                            hpnoText.setError("Please enter username");
                            passwordText.setError("Please enter password");
                            return;
                        } else {
                            spinner.setVisibility(View.VISIBLE);
                            loginButtonClicked(v);
                        }
                    }
                }
        );

        // register account link
        String text="Sign up for an account";
        TextView registerAccountLink = (TextView) findViewById(R.id.registerAccountLink);
        SpannableString spanString = new SpannableString(text);
        spanString.setSpan(new UnderlineSpan(), 0, spanString.length(), 0);
        registerAccountLink.setText(spanString);

        registerAccountLink.setOnClickListener(
                new TextView.OnClickListener() {
                    public void onClick(View v) {
                        goToRegisterActivity();
                    }
                }
        );
    }

    public void loginButtonClicked(final View v) {
        final String hpno = hpnoText.getText().toString();
        final String password = passwordText.getText().toString();

        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        String name = jsonResponse.getString("name");
                        SaveSharedPreferences.setPrefHpno(LoginActivity.this, hpno);
                        SaveSharedPreferences.setPrefName(LoginActivity.this, name);

                        Intent intent = new Intent(LoginActivity.this, CustomerMainActivity.class);
                        LoginActivity.this.startActivity(intent);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setMessage("Login Failed")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };


        LoginRequest loginRequest = new LoginRequest(hpno, password, responseListener);
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        queue.add(loginRequest);
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder closeButton = new AlertDialog.Builder(LoginActivity.this);

            // setting dialog title
            closeButton.setTitle("Quit");

            // setting dialog message
            closeButton.setMessage("Are you sure to quit?");

            // setting icon to dialog
            //orderConfirm.setIcon(R.drawable.save);

            // setting positive "Yes" button
            closeButton.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // user press Proceed button. Write logic here
                    moveTaskToBack(true);
                }
            });

            // setting positive "No" button
            closeButton.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // user press Proceed button. Write logic here
                    dialog.dismiss();
                }
            });

            closeButton.show();
        }
        return super.onKeyDown(keycode, event);
    }

    public void goToRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
