package com.gu.cardstackviewpager.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gu.cardstackviewpager.R;
import com.gu.cardstackviewpager.activity.HomeActivity;
import com.gu.cardstackviewpager.activity.MovieData;
import com.gu.cardstackviewpager.activity.MoviePageActivity;
import com.gu.cardstackviewpager.activity.MypageActivity;

import java.util.ArrayList;

import static android.content.Context.ACTIVITY_SERVICE;


public class CardFragment extends Fragment {
    private static final String INDEX_KEY = "mov_name";
    private static final String INDEX_NUM = "mov_number";
    private static String TAG = "movie";
    int number;

    public static CardFragment newInstance(String s,int i){
        CardFragment cardFragment = new CardFragment();
        Bundle bundle = new Bundle();
        bundle.putString(INDEX_KEY,s);
        bundle.putInt(INDEX_NUM,i);
        cardFragment.setArguments(bundle);
        return cardFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_card, container, false);
        //TextView cardNumTv = (TextView) v.findViewById(R.id.card_num_tv);
        ImageView post = v.findViewById(R.id.post);
        TextView mov_name = v.findViewById(R.id.movname);
        TextView num = v.findViewById(R.id.num);


        final Bundle bundle = getArguments();
        final int Num = bundle.getInt(INDEX_NUM,0);
        Log.i(TAG,"num"+Num);

        if (bundle != null) {
              mov_name.setText(bundle.getString(INDEX_KEY));
              //number = bundle.getInt(INDEX_NUM,0);

              num.setText(bundle.getInt(INDEX_NUM, 0) + "");
            //mov_name.setText((CharSequence) mMoviedata.get(bundle1.getInt(INDEX_KEY,0)));
            // Log.i(TAG,"NUMBER="+num.getText());

        }

         post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(),MoviePageActivity.class);
                    intent.putExtra("number",bundle.getInt(INDEX_NUM,0)+"");
                   // Log.i(TAG,"bundle="+ bundle.getInt(INDEX_NUM)+"")+"");
                //inten.putExtras("num",bundle.putInt(INDEX_NUM));*//*
                    intent.putExtra("age", 25);
                    startActivity(intent);
                }
            });





     /*   post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "点击了" + bundle.getInt(INDEX_NUM, 0) + "", Toast.LENGTH_SHORT).show();
            }
        });*/



        return v;


    }



}