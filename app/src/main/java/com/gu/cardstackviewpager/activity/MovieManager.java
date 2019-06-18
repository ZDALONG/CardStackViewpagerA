package com.gu.cardstackviewpager.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MovieManager {
    private DBHelper dbHelper;
    private String TBNAME;

    public MovieManager(Context context) {
        dbHelper = new DBHelper(context);
        TBNAME = DBHelper.TB_NAME;
    }

    public void add(RateItem item){
        SQLiteDatabase db = dbHelper.getWritableDatabase();//可写数据库
        ContentValues values = new ContentValues();
        values.put("curname", item.getMovName());
        values.put("currate", item.getMovHref());
        db.insert(TBNAME, null, values);
        db.close();
    }

    public void addAll(List<RateItem> list){//可以添加所有数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for (RateItem item : list) {
            ContentValues values = new ContentValues();
            values.put("curname", item.getMovName());
            values.put("currate", item.getMovHref());
            db.insert(TBNAME, null, values);
        }
        db.close();
    }

    public void deleteAll(){//删除所有数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME,null,null);
        db.close();
    }

    public void delete(int id){//删除一个数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME, "ID=?", new String[]{String.valueOf(id)});
        db.close();
    }
    public void update(RateItem item){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("curname", item.getMovName());
        values.put("currate", item.getMovHref());
        db.update(TBNAME, values, "ID=?", new String[]{String.valueOf(item.getId())});
        db.close();
    }

    //把数据库对象转成列表对象
    public List<RateItem> listAll(){//用列表返回一个二维数据
        List<RateItem> rateList = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();//只读数据库
        Cursor cursor = db.query(TBNAME, null, null, null, null, null, null);//query查询，null表示查询表里所有数据 cursor光标
        if(cursor!=null){
            rateList = new ArrayList<RateItem>();
            while(cursor.moveToNext()){
                RateItem item = new RateItem();
                item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                item.setMovName(cursor.getString(cursor.getColumnIndex("CURNAME")));
                item.setMovHref(cursor.getString(cursor.getColumnIndex("CURRATE")));
                rateList.add(item);
            }
            cursor.close();
        }
        db.close();
        return rateList;
    }

    public RateItem findById(int id){//查询
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null, "ID=?", new String[]{String.valueOf(id)}, null, null, null);
        RateItem rateItem = null;
        if(cursor!=null && cursor.moveToFirst()){
            rateItem = new RateItem();
            rateItem.setId(cursor.getInt(cursor.getColumnIndex("ID")));
            rateItem.setMovName(cursor.getString(cursor.getColumnIndex("CURNAME")));
            rateItem.setMovHref(cursor.getString(cursor.getColumnIndex("CURRATE")));
            cursor.close();
        }
        db.close();
        return rateItem;
    }


}
