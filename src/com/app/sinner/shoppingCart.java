package com.app.sinner;

import com.app.sqlite.MyDbSqlite;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class shoppingCart extends Activity implements OnClickListener{
	
	 SQLiteDatabase db;
	
	 public String db_name = "MyDbSqlite";
	  //表名
	 public String table_name = "neworder";
	
	 MyDbSqlite helper;
	 
	 Button btn,btn2;
	 
	 String[] orderinfo;
	 
	 TextView txv;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shopping_cart);
		
		setAllParameters();
		MyDbSqlite_select();
		
	}

	

	private void MyDbSqlite_select() {
		// TODO Auto-generated method stub
		Cursor cursor = getCursor();

        StringBuilder resultData = new StringBuilder("RESULT: \n");

        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String shop = cursor.getString(1);
            String tea = cursor.getString(2);
            String ice = cursor.getString(3);
            String brix = cursor.getString(4);
            String cups = cursor.getString(5);
            String cupsNumber = cursor.getString(6);
           
            
            resultData.append(id).append(": ");
            resultData.append(shop).append(": ");
            resultData.append(tea).append(": ");
            resultData.append(ice).append(": ");
            resultData.append(brix).append(": ");
            resultData.append(cups).append(": ");
            resultData.append(cupsNumber).append(": ");
            
            resultData.append("\n");
        }

        txv.setText(resultData);
		
	}
	 private Cursor getCursor(){
	        SQLiteDatabase db = helper.getReadableDatabase();
	        String[] columns = 
	        	{"_ID","_shop","_tea","_ice","_brix","_cups","_cupsNumber"};
	      
	        Cursor cursor = db.query
	        		(table_name, columns, null, null, null, null, null);
	        startManagingCursor(cursor);

	        return cursor;
	    }
	private void setAllParameters() {
		// TODO Auto-generated method stub
	    helper = new MyDbSqlite(shoppingCart.this, db_name);
		db = helper.getReadableDatabase();
		
		btn = (Button)findViewById(R.id.button1);
		btn2 = (Button)findViewById(R.id.button2);
		
		btn.setOnClickListener(this);
		btn2.setOnClickListener(this);
		
		txv = (TextView)findViewById(R.id.textView1);
	}
	  

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.button1:
			
			goToMainActivity();
			
			break;
		case R.id.button2:
			
			break;
		default:
			break;
		}
		
	}

	private void goToMainActivity() {
		// TODO Auto-generated method stub
		
		 //關閉
		shoppingCart.this.finish(); 
		   
		//跳到MainActivity畫面，用下面的方式才會刷新
		Intent intent_request = new Intent(shoppingCart.this, MainActivity.class);
		shoppingCart.this.startActivity(intent_request);
		
	}

}
