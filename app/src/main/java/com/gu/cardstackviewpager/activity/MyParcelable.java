package com.gu.cardstackviewpager.activity;

import android.os.Parcel;
import android.os.Parcelable;

public class MyParcelable implements Parcelable {
    private  String movname;
    private String movhref;

    protected MyParcelable(Parcel in) {
    }
    public MyParcelable(){
        super();
    }

    public MyParcelable(String movname,String movhref){
        super();
        this.movname = movname;
        this.movhref = movhref;
    }
    public String getMovname(){
        return movname;
    }
    public void setMovname(String movname) {
        this.movname = movname;
    }
    public String getMovhref(){
        return movhref;
    }
    public void setMovhref(String movhref) {
        this.movhref = movhref;
    }

    public String toString(){
        return "Moviename = "+movname+", Moviehref = "+movhref;
    }

    public static final Creator<MyParcelable> CREATOR = new Creator<MyParcelable>() {
        @Override
        public MyParcelable createFromParcel(Parcel in) {
            return new MyParcelable(in);
           /* MyParcelable mov = new MyParcelable();
            mov.movname = in.readString();
            mov.movhref = in.readString();
            return mov;*/
        }

        @Override
        public MyParcelable[] newArray(int size) {
            return new MyParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(movname);
        parcel.writeString(movhref);
    }
}
