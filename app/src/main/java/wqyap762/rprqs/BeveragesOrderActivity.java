package wqyap762.rprqs;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class BeveragesOrderActivity extends ActionBarActivity {

    String menu_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beverages_order);

        // ice lemon tea
        Button iceLemonTeaButton = (Button) findViewById(R.id.iceLemonTeaButton);
        iceLemonTeaButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "B001";
                        goToItemActivity();
                    }
                }
        );

        // green tea
        Button greenTeaButton = (Button) findViewById(R.id.greenTeaButton);
        greenTeaButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "B002";
                        goToItemActivity();
                    }
                }
        );

        // 100 plus
        Button hundredPlusButton = (Button) findViewById(R.id.hundredPlusButton);
        hundredPlusButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "B003";
                        goToItemActivity();
                    }
                }
        );

        // coca-cola
        Button cocaColaButton = (Button) findViewById(R.id.cocaColaButton);
        cocaColaButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "B004";
                        goToItemActivity();
                    }
                }
        );

        // chinese tea
        Button chineseTeaButton = (Button) findViewById(R.id.chineseTeaButton);
        chineseTeaButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "B005";
                        goToItemActivity();
                    }
                }
        );

        // sky juice
        Button skyJuiceButton = (Button) findViewById(R.id.skyJuiceButton);
        skyJuiceButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "B006";
                        goToItemActivity();
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_beverages_order, menu);
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
