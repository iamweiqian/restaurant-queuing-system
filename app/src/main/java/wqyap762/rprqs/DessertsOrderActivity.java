package wqyap762.rprqs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class DessertsOrderActivity extends AppCompatActivity {

    String menu_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desserts_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Barley with Gingko
        Button barleyGingkoButton = (Button) findViewById(R.id.barleyGingkoButton);
        assert barleyGingkoButton != null;
        barleyGingkoButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "D001";
                        goToItemActivity();
                    }
                }
        );

        // Red Bean Soup
        Button redBeanSoupButton = (Button) findViewById(R.id.redBeanSoupButton);
        assert redBeanSoupButton != null;
        redBeanSoupButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "D002";
                        goToItemActivity();
                    }
                }
        );

        // Cheng Tang
        Button chengTangButton = (Button) findViewById(R.id.chengTangButton);
        assert chengTangButton != null;
        chengTangButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "D003";
                        goToItemActivity();
                    }
                }
        );

        // Sesame Paste
        Button sesamePasteButton = (Button) findViewById(R.id.sesamePasteButton);
        assert sesamePasteButton != null;
        sesamePasteButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "D004";
                        goToItemActivity();
                    }
                }
        );

        // Tau Suan
        Button tauSuanButton = (Button) findViewById(R.id.tauSuanButton);
        assert tauSuanButton != null;
        tauSuanButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "D005";
                        goToItemActivity();
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_desserts_order, menu);
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

    public void goToItemActivity() {
        Intent intent = new Intent(this, ItemActivity.class);
        intent.putExtra("menu_id", menu_id);
        startActivity(intent);
    }
}
