package com.gu.cardstackviewpager.activity;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.stream.Stream;


//public class Moviedata implements Serializable
@SuppressLint("ParcelCreator")
public class MovieData extends ArrayList<Parcelable> implements Parcelable {

   /* public String movname = "";
    public String movhref = "";
    public List<String> options = new ArrayList<>();
    public String option = "";*/
   private String movname;
   private String movhref;


    public MovieData(){
        super();
    }

    public MovieData(String movname,String movhref){
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

    //*protected Moviedata(Parcel in) {
   // }
    public static final Creator<MovieData> CREATOR = new Creator<MovieData>() {
        @Override
        public MovieData createFromParcel(Parcel in) {
           // return new Moviedata(in);

            MovieData mov = new MovieData();
            mov.movname = in.readString();
            mov.movhref = in.readString();
            return mov;
        }

        @Override
        public MovieData[] newArray(int size) {
            return new MovieData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
    //把实体类数据写入Parcel
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(movname);
        parcel.writeString(movhref);
    }

    @Override
    public Stream<Parcelable> stream() {
        return null;
    }
}
