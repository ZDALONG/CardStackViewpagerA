package com.gu.cardstackviewpager.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.gu.cardstackviewpager.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MoviePageActivity extends AppCompatActivity implements Runnable{
    Handler handler;
    ImageView post = null;
    TextView name;
    TextView date;
    TextView grade;
    TextView intro;
    private  String number;
    String imgUrl, movname, movdate, movintro, movscore;
    private final String TAG = "Movie";
    String MovHref[]={"https://movie.douban.com/subject/1292052/", "https://movie.douban.com/subject/1291546/",
            "https://movie.douban.com/subject/1295644/", "https://movie.douban.com/subject/1292720/", "https://movie.douban.com/subject/1292063/",
            "https://movie.douban.com/subject/1292722/", "https://movie.douban.com/subject/1291561/", "https://movie.douban.com/subject/1295124/",
            "https://movie.douban.com/subject/3541415/", "https://movie.douban.com/subject/3011091/", "https://movie.douban.com/subject/2131459/",
            "https://movie.douban.com/subject/3793023/", "https://movie.douban.com/subject/1292001/", "https://movie.douban.com/subject/1291549/",
            "https://movie.douban.com/subject/1292064/", "https://movie.douban.com/subject/1292213/", "https://movie.douban.com/subject/1889243/",
            "https://movie.douban.com/subject/1291560/", "https://movie.douban.com/subject/1291841/", "https://movie.douban.com/subject/5912992/",
            "https://movie.douban.com/subject/1307914/", "https://movie.douban.com/subject/25662329/", "https://movie.douban.com/subject/1849031/",
            "https://movie.douban.com/subject/3319755/", "https://movie.douban.com/subject/6786002/"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_page);

        this.post = findViewById(R.id.post);
        name = findViewById(R.id.name);
        date = findViewById(R.id.date);
        grade = findViewById(R.id.grade);
        intro = findViewById(R.id.intro);


        Intent intent = getIntent();
        number = intent.getStringExtra("number");
        Log.i(TAG,"Num = "+number);


        //开启子线程，启动run方法
        Thread d = new Thread(this);
        d.start();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 5) {
                    Bundle bdl = (Bundle) msg.obj;
                }
                super.handleMessage(msg);
                //*handleMessage(msg) {
            }
        };
        /*//显示图片
        try {
            byte data [] = this.getUrlData() ;                      // 接收数据
            Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length);    // 生成图形
            this.post.setImageBitmap(bm) ;                           // 显示图片
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }


    public void run() {
        Log.i(TAG, "run: run()......");
        for (int i = 1; i < 3; i++) {
            Log.i(TAG, "run: i=" + i);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //获取网络数据并解析成字符串
        Bundle bundle = new Bundle();
        Document doc = null;
        try {
            //String url = "https://movie.douban.com/subject/1950148/";
           // String url = MovHref[Integer.parseInt(number)];
            String url = MovHref[0];
            doc = Jsoup.connect(url).get();
            Log.i(TAG, "run: " + doc.title());
            //获取电影名字
            Elements h = doc.getElementsByTag("h1");
            Log.i(TAG, "run:h1=" + h.text());
            bundle.putString("mov_name",h.text());
            //获取电影名字
            Elements spans = doc.getElementsByTag("span");
            /*for (int i =0;i<spans.size();i++){
                Element span1 = spans.get(i);
                String sp = span1.text();
                Log.i(TAG,"run:span="+ i +"  "+sp);
            }*/
            Element span5 = spans.get(5);
            name.setText(span5.text());
            bundle.putString("mov_name", span5.text());
            Log.i(TAG, "run: name=" + span5.text());
            //获取上映时间
            Element span21 = spans.get(21);
            Element span22 = spans.get(22);
            Element span23 = spans.get(23);
            //bundle.putString("mov_date", span21.text() + span22.text() + "/" + span23.text());
            date.setText(span21.text()+span22.text()+"/"+span23.text());
            Log.i(TAG, "run:date=" + span21.text() + span22.text() + "/" + span23.text());

            //获取剧情简介
            Element span47 = spans.get(47);
            intro.setText(span47.text());
            Log.i(TAG, "run: intro=" + span47.text());
            /*for (int i =0;i<spans.size();i++){
                Element span1 = spans.get(i);
                String sp = span1.text();
                Log.i(TAG,"run:span="+ i +"  "+sp);
            }*/
            //获取评分
            Elements strongs = doc.getElementsByTag("strong");
            Element strong = strongs.get(0);
           // bundle.putString("mov_score", strong.text());
            grade.setText("评分：" + strong.text());
            Log.i(TAG, "run: grade=" + strong.text());
           /* for (int i=0;i<strongs.size();i++){
                Element strong = strongs.get(i);
                Log.i(TAG,"run:strong="+ i +"  "+strong.text());
            }*/

            //获取图片
           /* Elements imgs = doc.getElementsByTag("img");
            Element img = imgs.get(0);
            imgUrl = img.attr("abs:src").toString();//获取图片的绝对路径
            bundle.putString("img_Url", img.attr("abs:src"));*/
            //this.post.setImageBitmap(getURLimage(imgUrl));
            //显示图片
            /*try {
                 byte data [] = this.getUrlData() ;                      // 接收数据
                 Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length);    // 生成图形
                 this.post.setImageBitmap(bm) ;                           // 显示图片
             } catch (Exception e) {
                  e.printStackTrace();
            }*/
            //Log.i(TAG, "run: imgUrl=" + imgUrl);

            //post.setImageURI(img.attr("abs:scr"));
            /*for (int i=0;i<imgs.size();i++) {
                Element img = imgs.get(i);
                //String imgUrl = img.attr("abs:src");//获取图片的绝对路径
                Log.i(TAG, "run:img=" + i + "  " + img.attr("abs:src"));
            }*/

        } catch (IOException e) {
            e.printStackTrace();
        }

        //把获取到的数据存入bundel
        //获取Msg对象，用于返回主线程
        Message msg = handler.obtainMessage(5);
        //msg.what = 5;
        msg.obj = bundle;
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


   /* public byte[] getUrlData() throws Exception {                   // 取得网络图片数据
        ByteArrayOutputStream bos = null;                          // 内存输出流
        try {
            URL url1 = new URL(imgUrl);                               // 定义URL
            Log.i(TAG, "getUrlData: =" + url1);
            bos = new ByteArrayOutputStream();                     // 定义内存输出流
            byte data[] = new byte[1024];                         // 每次读取1024
            HttpURLConnection conn = (HttpURLConnection) url1.openConnection();  // 打开连接
            InputStream input = conn.getInputStream();             // 取得输入流
            int len = 0;                                           // 接收读取长度
            while ((len = input.read(data)) != -1) {                 // 没有读取到底部
                bos.write(data, 0, len);                           // 向内存中保存
            }
            return bos.toByteArray();                              // 变为字节数组返回
        } catch (Exception e) {
            throw e;
        } finally {
            if (bos != null) {
                bos.close();                                        // 关闭输出流
            }
        }
    }*/


}
