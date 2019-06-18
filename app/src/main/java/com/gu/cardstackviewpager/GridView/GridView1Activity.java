package com.gu.cardstackviewpager.GridView;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.gu.cardstackviewpager.R;

import java.util.ArrayList;
import java.util.List;

public class GridView1Activity extends AppCompatActivity {

    private GridView mGV1;
    private int[] images = new int[]{
            R.drawable.p0, R.drawable.p1, R.drawable.p2
           // R.drawable.pic4, R.drawable.pic5, R.drawable.pic6,
            //R.drawable.pic7, R.drawable.pic8,R.drawable.pic9
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view1);
        mGV1=findViewById(R.id.GV1);
        MyGridViewAdapter adapter = new MyGridViewAdapter(images, this);
        mGV1.setAdapter(adapter);

        mGV1.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                Toast.makeText(GridView1Activity.this, "pic" + (position+1), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class MyGridViewAdapter extends BaseAdapter {

        //声明引用
        private LayoutInflater inflater;
        private List<Picture> pictures;

        //public MyGridViewAdapter(String[] titles, int[] images, Context context)
        public MyGridViewAdapter(int[] images, Context context)
        {
            super();
            pictures = new ArrayList<Picture>();
            inflater = LayoutInflater.from(context);
            for (int i = 0; i < images.length; i++)
            {
                //Picture picture = new Picture(titles[i], images[i]);
                Picture picture = new Picture(images[i]);
                pictures.add(picture);
            }
        }

        @Override
        public int getCount() {
            if (null != pictures)
            {
                return pictures.size();
            } else
            {
                return 0;
            }
        }

        @Override
        public Object getItem(int position) {
            return pictures.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null)
            {
                convertView = inflater.inflate(R.layout.gridview1_item, null);
                viewHolder = new ViewHolder();
                //viewHolder.title = (TextView) convertView.findViewById(R.id.title);
                viewHolder.image = convertView.findViewById(R.id.image);
                convertView.setTag(viewHolder);
            } else
            {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            //viewHolder.title.setText(pictures.get(position).getTitle());
            viewHolder.image.setImageResource(pictures.get(position).getImageId());
            return convertView;
        }

        class ViewHolder
        {
            //public TextView title;
            public ImageView image;
        }
        class Picture
        {
            //private String title;
            private int imageId;

            public Picture(int image)
            {
                super();
            }

            //public Picture(String title, int imageId)
            public Picture(String title,int imageId) {
                super();
                // this.title = title;
                this.imageId = imageId;
            }

        /*public String getTitle()
        {
            return title;
        }*/

        /*public void setTitle(String title)
        {
            this.title = title;
        }*/

            public int getImageId()
            {
                return imageId;
            }

            public void setImageId(int imageId)
            {
                this.imageId = imageId;
            }

        }
    }
}




