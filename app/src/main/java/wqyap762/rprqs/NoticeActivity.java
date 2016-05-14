package wqyap762.rprqs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NoticeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        displayNotice();

        Button okayButton = (Button) findViewById(R.id.okayButton);
        assert okayButton != null;
        okayButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(final View v) {
                        goToCustomerMainActivity();
                    }
                }
        );
    }

    public void displayNotice() {
        TextView noticeText = (TextView) findViewById(R.id.noticeText);
        if (noticeText != null) {
            noticeText.setText("Restaurant's operating hours is from 10.00 A.M. to 10.00 P.M.");
        }
    }

    public void goToCustomerMainActivity() {
        Intent intent = new Intent(this, CustomerMainActivity.class);
        this.startActivity(intent);
        this.finish();
    }
}
