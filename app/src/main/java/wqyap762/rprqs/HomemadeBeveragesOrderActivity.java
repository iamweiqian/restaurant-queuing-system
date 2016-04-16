package wqyap762.rprqs;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class HomemadeBeveragesOrderActivity extends ActionBarActivity {

    String menu_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homemade_beverages_order);

        // barley
        Button barleyButton = (Button) findViewById(R.id.barleyButton);
        barleyButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "HB001";
                        goToItemActivity();
                    }
                }
        );

        // herbal tea
        Button herbalTeaButton = (Button) findViewById(R.id.herbalTeaButton);
        herbalTeaButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "HB002";
                        goToItemActivity();
                    }
                }
        );

        // chrysanthemum tea
        Button chrysanthemumTeaButton = (Button) findViewById(R.id.chrysanthemumTeaButton);
        chrysanthemumTeaButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "HB003";
                        goToItemActivity();
                    }
                }
        );

        // soy bean
        Button soyBeanButton = (Button) findViewById(R.id.soyBeanButton);
        soyBeanButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "HB004";
                        goToItemActivity();
                    }
                }
        );

        // sour plum lime juice
        Button sourPlumLimeJuiceButton = (Button) findViewById(R.id.sourPlumLimeJuiceButton);
        sourPlumLimeJuiceButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "HB005";
                        goToItemActivity();
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_homemade_beverages_order, menu);
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
