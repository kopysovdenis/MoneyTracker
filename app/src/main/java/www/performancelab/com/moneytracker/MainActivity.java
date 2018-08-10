package www.performancelab.com.moneytracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import www.performancelab.com.moneytracker.Adapter.TestAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity: ";
    private ArrayList<Item> list = new ArrayList<>();
    private int totalPricesValue = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final EditText name = findViewById(R.id.nameProduct);
        final EditText price = findViewById(R.id.price);
        final EditText priceBuy = findViewById(R.id.priceBuy);
        final Button add = (Button)findViewById(R.id.addProduct);
        final Button clearListView = (Button)findViewById(R.id.clearList);
        final ListView items = findViewById(R.id.items);
        final TextView totalPrice = (TextView)findViewById(R.id.totalPryce);

        if(savedInstanceState == null || !savedInstanceState.containsKey("key")) {
            list.clear();
            totalPrice.setText(String.valueOf(totalPricesValue));
        } else {
            list = savedInstanceState.getParcelableArrayList("key");
            totalPricesValue = savedInstanceState.getInt("total");
        }
        final TestAdapter adapter =  new TestAdapter(MainActivity.this, list);

//        for (int i = 0; i < 20; i++){
//            list.add(new Item("Diana", i ));
//        }

        items.setAdapter(adapter);
        totalPrice.setText(String.valueOf(totalPricesValue));
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((!name.getText().toString().equals("")) && (!price.getText().toString().equals("") && (!priceBuy.getText().toString().equals(""))))
                {
                    list.add(new Item(name.getText().toString(), Integer.valueOf(price.getText().toString()), Integer.valueOf(priceBuy.getText().toString())));
                    totalPricesValue = totalPricesValue + (Integer.parseInt(price.getText().toString()) * Integer.parseInt(priceBuy.getText().toString()));

                    totalPrice.setText(String.valueOf(totalPricesValue));
                    name.setText("");
                    price.setText("");
                    priceBuy.setText("");
                    items.setAdapter(adapter);
                }
            }
        });

        clearListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                totalPricesValue = 0;
                totalPrice.setText(String.valueOf(totalPricesValue));
                items.setAdapter(adapter);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("key", list);
        outState.putInt("total", totalPricesValue);
        super.onSaveInstanceState(outState);
    }
}
