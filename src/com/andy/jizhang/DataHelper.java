package com.andy.jizhang;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;



public class DataHelper {

	// 数据库名称
	private static String DB_NAME = "jizhang.db";

	// 数据库版本
	private static int DB_VERSION = 3;

	private SQLiteDatabase db;

	private MySqliteHelper dbHelper;
	
	//名字列表长度
	private int size;
	
	private String[] tableName = {"one","two","three","four","five","six","seven","eight","nine"};

	public DataHelper(Context context,int size) {

		dbHelper = new MySqliteHelper(context, DB_NAME, null, DB_VERSION,size);
		db = dbHelper.getWritableDatabase();
		this.size = size;
		dbHelper.onCreate(db);
	}

	public void Close()

	{

		db.close();

		dbHelper.close();

	}


	// 添加users表的记录

	public void save(int i,int score)
	{
		ContentValues values = new ContentValues();
		values.put("score", score);
		db.insert(tableName[i], null, values);

	}
	
	public ArrayList<Integer> getScore(int i){
		
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		Cursor cursor = db.query(tableName[i],null, null, null, null, null,null);//最后一个orderby参数为null的返回的是第一列的数据
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			result.add(cursor.getInt(0));
//			Log.i("andy", "cursor="+cursor.getInt(0));
			cursor.moveToNext();
		}
				
		return result;
	} 
	

	// 删除表的记录

	public void delete() {
		
		for (int i = 0; i < size; i++) {
			db.delete(tableName[i],null,null);
			
		}

	}
	

	
	//http://byandby.iteye.com/blog/835580
	

}
