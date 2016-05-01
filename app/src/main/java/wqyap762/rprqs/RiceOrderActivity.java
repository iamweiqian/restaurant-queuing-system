package wqyap762.rprqs;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class RiceOrderActivity extends AppCompatActivity {

    private Toolbar toolbar;
    String menu_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rice_order);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Black Pepper Chicken Rice
        Button blackPepperChickenRiceButton = (Button) findViewById(R.id.blackPepperChickenRiceButton);
        blackPepperChickenRiceButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "R001";
                        goToItemActivity();
                    }
                }
        );

        // Ginger Onion Sliced Fish Rice
        Button gingerOnionSlicedFishRiceButton = (Button) findViewById(R.id.gingerOnionSlicedFishRiceButton);
        gingerOnionSlicedFishRiceButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "R002";
                        goToItemActivity();
                    }
                }
        );

        // Pork Ribs King Rice
        Button porkRibsKingRiceButton = (Button) findViewById(R.id.porkRibsKingRiceButton);
        porkRibsKingRiceButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "R003";
                        goToItemActivity();
                    }
                }
        );

        // Sambal Squid Rice
        Button sambalSquidRiceButton = (Button) findViewById(R.id.sambalSquidRiceButton);
        sambalSquidRiceButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "R004";
                        goToItemActivity();
                    }
                }
        );

        // Sweet and Sour Pork Rice
        Button sweetSourPorkRiceButton = (Button) findViewById(R.id.sweetSourPorkRiceButton);
        sweetSourPorkRiceButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "R005";
                        goToItemActivity();
                    }
                }
        );

        // Special Sauce Diced Chicken Rice
        Button specialSauceDicedChickenRiceButton = (Button) findViewById(R.id.specialSauceDicedChickenRiceButton);
        specialSauceDicedChickenRiceButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "R006";
                        goToItemActivity();
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rice_order, menu);
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
