package com.gu.cardstackviewpager.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.gu.cardstackviewpager.R;
import com.gu.cardstackviewpager.adapter.ContentFragmentAdapter;
import com.gu.cardstackviewpager.fragment.CardFragment;
import com.gu.library.OrientedViewPager;
import com.gu.library.transformer.VerticalStackTransformer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HomeActivity extends AppCompatActivity implements Runnable {
    private Handler handler;
    private OrientedViewPager mOrientedViewPager;
    private List<MovieData> mMoviedata = new ArrayList<>();
    String movname;
    String movhref;
    private static String TAG = "movie";
    String data = "赎罪";
    String movie[] = {"肖申克的救赎", "霸王别姬", "这个杀手不太冷", "阿甘正传", "美丽人生", "泰坦尼克号", "千与千寻", "辛德勒的名单", "盗梦空间", "忠犬八公的故事", "机器人总动员", "三傻大闹宝莱坞", "海上钢琴师", "放牛班的春天", "楚门的世界", "大话西游之大圣娶亲", "星际穿越", "龙猫", "教父", "熔炉", "无间道", "疯狂动物城", "当幸福来敲门", "怦然心动", "触不可及"};
    private ArrayList<String>list1 = new ArrayList<>();
    private MyParcelable myParcelable;

    private ContentFragmentAdapter mContentFragmentAdapter;
    private List<CardFragment> mFragments = new ArrayList<CardFragment>();
    //private ViewPager mOrientedViewPager;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mOrientedViewPager = findViewById(R.id.view_pager);

        //获取sp里保存的数据
        SharedPreferences SharedPreferences=getSharedPreferences("myrate",Activity.MODE_PRIVATE);
        //SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);//这个方法只能获取一个配置文件，用于保存关键信息
        //movie = SharedPreferences.getStringSet("movie","111");
        movname = SharedPreferences.getString("mov_name","赎罪");
        movhref = SharedPreferences.getString("mov_href","111");


        //制造数据
        for (int i = 0; i <25 ; i++) {
            mFragments.add(CardFragment.newInstance(movie[i],i));
            Log.i(TAG,"neme = "+movie[i]+"number="+i);

        }

        mContentFragmentAdapter = new ContentFragmentAdapter(getSupportFragmentManager(),mFragments);
        //设置viewpager的方向为竖直
        mOrientedViewPager.setOrientation(OrientedViewPager.Orientation.VERTICAL);
        //设置limit
        mOrientedViewPager.setOffscreenPageLimit(4);
        //设置transformer
        mOrientedViewPager.setPageTransformer(true, new VerticalStackTransformer(getApplicationContext()));
        mOrientedViewPager.setAdapter(mContentFragmentAdapter);

        //跳转我的界面
        findViewById(R.id.about_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MypageActivity.class);
                startActivity(intent);
            }
        });

       //开启子线程，启动run方法
        Thread t = new Thread(this);
        t.start();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 5) {
                    //MyParcelable myParcelable = new MyParcelable();
                    //ArrayList arrayList =msg.getData().getParcelableArrayList("moviedata");
                    list1 = (ArrayList<String>) msg.obj;
                    Bundle bd1 = new Bundle();
                    bd1.putStringArrayList("data",list1);

                    //保存更新的日期
                    SharedPreferences SharedPreferences=getSharedPreferences("myrate",Activity.MODE_PRIVATE);
                    android.content.SharedPreferences.Editor editor = SharedPreferences.edit();
                    //editor.putStringSet("movie", (Set<String>) list1);
                    //editor.putStringSet("mov_name", list1.get(0));
                    editor.putString("mov_name",list1.get(1));
                    editor.putString("mov_href",list1.get(28));
                    editor.apply();

                    Toast.makeText(HomeActivity.this, "数据已更新", Toast.LENGTH_SHORT).show();


                    Log.i(TAG,"movie:list1="+ list1);
                    Log.i(TAG,"movie:list1="+ list1.get(0));
                }
                super.handleMessage(msg);
            }
        };






    }

    public void run() {
        Log.i(TAG, "run: run()......");
        for (int i = 1; i < 3; i++) {
            Log.i(TAG, "run: i=" + i);
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //获取网络数据并解析成字符串
        Bundle bundle = new Bundle();
        Document doc = null;
        ArrayList<String>retList = new ArrayList<String>();
        ArrayList<String>hrefList = new ArrayList<String>();
        try {
            List<RateItem> rateList = new ArrayList<RateItem>();
            for (int count = 0; count<40; count=count+25){
                String url = String.format("https://movie.douban.com/top250?start=%s&filter=",count);
                doc = Jsoup.connect(url).get();

                Elements imgs = doc.getElementsByTag("img");
                Elements as = doc.getElementsByTag("a");
                int y = 28;
               for (int i=0;i<25;i++){
                   Element img = imgs.get(i);
                   //movList [i] = img.attr("alt");
                   movname = img.attr("alt");
                   retList.add(movname);//数据带回页面
                   list1.add(movname);
                   rateList.add(new RateItem(movname,""));
                   bundle.putStringArrayList("mov_name",retList);
                   //Log.i(TAG,"movie:list1="+i+ "-->"+list1);
                   Log.i(TAG,"movie:movlist="+i+ "-->"+movname);
                   //Log.i(TAG,"DBSQL="+rateList);
                }
                for (int x=28;x<77;x=x+2){
                    Element a = as.get(x);
                    movhref = a.attr("href");
                    hrefList.add(movhref);
                    list1.add(movhref);
                    rateList.add(new RateItem("",movhref));
                    bundle.putStringArrayList("mov_href",hrefList);
                    //Log.i(TAG,"movie:list1="+x+ "-->"+list1);
                    Log.i(TAG,"movie:href="+x+ "-->"+movhref);

                }
               // rateList.add(new RateItem(retList,hrefList));//保存到数据库
            }
            /*//把数据写入数据库中
            MovieManager manager = new MovieManager(this);
            manager.addAll(rateList);
            Log.i(TAG,"DBSQL="+rateList);*/

        } catch (IOException e) {
            e.printStackTrace();
        }

        //把获取到的数据存入bundel
        //获取Msg对象，用于返回主线程
        Message msg = handler.obtainMessage(5);
        //msg.what = 5;
        msg.obj = list1;
        msg.setData(bundle);
        handler.sendMessage(msg);
    }

    //将输入流InputStream转换为String的方法
    private String inputStream2String(InputStream inputStream) throws IOException {
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(inputStream, "utf-8");
        while (true) {
            int rsz = in.read(buffer, 0, buffer.length);
            if (rsz < 0)
                break;
            out.append(buffer, 0, rsz);
        }
        return out.toString();
    }
}
