package wqyap762.rprqs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;


public class LoginActivity extends ActionBarActivity {

    EditText usernameText, passwordText;
    MyDBHandler dbHandler = new MyDBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // sign in button
        Button signiInButton = (Button) findViewById(R.id.signInButton);
        signiInButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        usernameText = (EditText) findViewById(R.id.usernameText);
                        passwordText = (EditText) findViewById(R.id.passwordText);

                        if (TextUtils.isEmpty(usernameText.getText().toString())) {
                            usernameText.setError("Please enter username");
                            return;
                        } else if (TextUtils.isEmpty(passwordText.getText().toString())) {
                            passwordText.setError("Please enter password");
                            return;
                        } else if (TextUtils.isEmpty(usernameText.getText().toString()) && TextUtils.isEmpty(passwordText.getText().toString())) {
                            usernameText.setError("Please enter username");
                            passwordText.setError("Please enter password");
                            return;
                        } else {
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
                        goToRegisterAccountActivity();
                    }
                }
        );
    }

    public void loginButtonClicked(View v) {
        if (v.getId() == R.id.signInButton) {
            //usernameText = (EditText) findViewById(R.id.usernameText);
            //passwordText = (EditText) findViewById(R.id.passwordText);

            String pass = dbHandler.searchPassword(usernameText.getText().toString());
            if (usernameText.getText().toString().equals("admin") && passwordText.getText().toString().equals("admin")) {
                goToAdminMainActivity();
            } else if (passwordText.getText().toString().equals(pass)) {
                goToCustomerMainActivity();
            } else {
                AlertDialog.Builder loginError = new AlertDialog.Builder(LoginActivity.this);

                // setting dialog title
                loginError.setTitle("Invalid");

                // setting dialog message
                loginError.setMessage("Username or Password is not correct.");

                // setting icon to dialog
                //orderConfirm.setIcon(R.drawable.save);

                // setting positive "Okay" button
                loginError.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // user press Proceed button. Write logic here
                        passwordText.setText("");
                        dialog.dismiss();
                    }
                });
                loginError.show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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

    public void goToAdminMainActivity() {
        Intent intent = new Intent(this, AdminMainActivity.class);
        startActivity(intent);
    }

    public void goToCustomerMainActivity() {
        Intent intent = new Intent(this, CustomerMainActivity.class);
        startActivity(intent);
    }

    public void goToRegisterAccountActivity() {
        Intent intent = new Intent(this, RegisterAccountActivity.class);
        startActivity(intent);
    }

}