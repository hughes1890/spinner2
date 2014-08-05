package com.app.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbSqlite extends SQLiteOpenHelper {
	
	//資料庫版本關係到App更新時，資料庫是否要調用onUpgrade()
	private static final int VERSION = 1;//資料庫版本  

	public MyDbSqlite(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	public MyDbSqlite(Context context, String name) 
	{
		this(context, name, null, VERSION);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String DATABASE_CREATE_TABLE =
			     "create table neworder("
			       + "_ID INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL,"
			       + "_shop VARCHAR,"
			       + "_tea VARCHAR,"
			       + "_ice VARCHAR,"
			       + "_brix VARCHAR,"
			       + "_cups VARCHAR,"
			       + "_cupsNumber VARCHAR"
			         + ")";
		 db.execSQL(DATABASE_CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS neworder"); //刪除舊有的資料表
		onCreate(db);
	}
	 @Override   
	   public void onOpen(SQLiteDatabase db) {     
	           super.onOpen(db);       
	           // TODO 每次成功打開數據庫後首先被執行     
	       } 
	 @Override
     public synchronized void close() {
         super.close();
     }

	
	

}
