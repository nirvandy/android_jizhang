package com.andy.jizhang;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySqliteHelper extends SQLiteOpenHelper{

	// 用来保存UserID、Access Token等用户信息的表名

	private int size;//名字列表长度
	
	private String[] tableName = {"one","two","three","four","five","six","seven","eight","nine"};
	

	public MySqliteHelper(Context context, String name, CursorFactory factory,
			int version,int size) {
		
		super(context, name, factory, version);
		this.size = size;
		
		
	}

	// 创建表

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		for (int i = 0; i < size; i++) {
			
		db.execSQL("CREATE TABLE IF NOT EXISTS " +

		tableName[i] + "(" +
				
		"score "+"interger"+

		")"

		);
			
		}

		Log.i("MySqliteHelper.size==>",size+"");
		
			
	}

	// 更新表

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		for (int i = 0; i < size; i++) {
		
		db.execSQL("DROP TABLE IF EXISTS " + tableName[i]);

		Log.e("Database", "onUpgrade");
		
		}
		
		onCreate(db);

	}

	
	
}