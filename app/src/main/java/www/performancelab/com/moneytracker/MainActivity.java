package www.performancelab.com.moneytracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import www.performancelab.com.moneytracker.Adapter.TestAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity: ";
    ArrayList<Item> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final EditText name = findViewById(R.id.nameProduct);
        final EditText price = findViewById(R.id.price);
        final Button add = (Button)findViewById(R.id.addProduct);
        final Button clearListView = (Button)findViewById(R.id.clearList);
        final ListView items = findViewById(R.id.items);

        if(savedInstanceState == null || !savedInstanceState.containsKey("key")) {
            list.clear();
        } else {
            list = savedInstanceState.getParcelableArrayList("key");
        }
        final TestAdapter adapter =  new TestAdapter(MainActivity.this, list);

//        for (int i = 0; i < 20; i++){
//            list.add(new Item("Diana", i ));
//        }

        items.setAdapter(adapter);

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if((!name.getText().toString().equals("")) && (!price.getText().toString().equals("")))
                    {
                        list.add(new Item(name.getText().toString(), Integer.valueOf(price.getText().toString())));

                        name.setText("");
                        price.setText("");
                        items.setAdapter(adapter);
                    }
                }
            });

            clearListView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.clear();
                    items.setAdapter(adapter);
                }
            });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("key", list);
        super.onSaveInstanceState(outState);
    }
}
