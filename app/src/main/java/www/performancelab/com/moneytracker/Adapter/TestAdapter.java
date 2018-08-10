package www.performancelab.com.moneytracker.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import www.performancelab.com.moneytracker.Item;
import www.performancelab.com.moneytracker.R;

public class TestAdapter extends BaseAdapter {
    private ArrayList<Item> mListItems;
    private LayoutInflater mLayoutInflater;

    public TestAdapter(Context context, ArrayList<Item> arrayList){

        mListItems = arrayList;

        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return mListItems.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final String unknown = "unknown";
        ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();

            view = mLayoutInflater.inflate(R.layout.item, viewGroup, false);

            holder.itemName = (TextView) view.findViewById(R.id.nameProduct);
            holder.itemPrice = (TextView) view.findViewById(R.id.price);
            holder.itemPriceBuy = (TextView) view.findViewById(R.id.priceBuy);


            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }

        Item stringItem = mListItems.get(position);
        if (stringItem != null) {
            holder.itemName.setText(stringItem.name);
            holder.itemPrice.setText(String.valueOf(stringItem.price));
            holder.itemPriceBuy.setText(String.valueOf(stringItem.priceBuy));

        } else {
            holder.itemName.setText(unknown);
            holder.itemPrice.setText(unknown);
        }

        return view;
    }

    private class ViewHolder {

        protected TextView itemName;
        protected TextView itemPrice;
        protected TextView itemPriceBuy;

    }
}
