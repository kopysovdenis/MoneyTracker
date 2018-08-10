package www.performancelab.com.moneytracker;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable{
    public String name;
    public int price;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    protected Item(Parcel in) {
        name = in.readString();
        price = in.readInt();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeString(name);
        out.writeInt(price);
    }
}

