package com.app.sinner;

import com.app.sqlite.MyDbSqlite;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	
	 SQLiteDatabase db;
	  //資料庫名
	 public String db_name = "MyDbSqlite";
	  //表名
	 public String table_name = "neworder";
	
	
	
	 Spinner spin1,spin2,spin3,spin4,spin5,spin6;
	 
	 TextView txv;
	 
	 Button btn,btn2;
	 
	 String[] shop,tea,ice,brix,cups,cupsNumber;
	 
	 int index1,index2,index3,index4,index5,index6;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		setAllParameters();
	}
	
	private void setAllParameters() {
		// TODO Auto-generated method stub
		MyDbSqlite helper = new MyDbSqlite(MainActivity.this, db_name);
		db = helper.getReadableDatabase();
		
		btn = (Button)findViewById(R.id.button1);
		btn2 = (Button)findViewById(R.id.button2);
		
		btn.setOnClickListener(this);
		btn2.setOnClickListener(this);
		
		spin1 = (Spinner)findViewById(R.id.spinner1);
		spin2 = (Spinner)findViewById(R.id.spinner2);
		spin3 = (Spinner)findViewById(R.id.spinner3);
		spin4 = (Spinner)findViewById(R.id.spinner4);
		spin5 = (Spinner)findViewById(R.id.spinner5);
		spin6 = (Spinner)findViewById(R.id.spinner6);
		
		txv = (TextView)findViewById(R.id.textView1);
		
		shop = getResources().getStringArray(R.array.shop);
		tea  = getResources().getStringArray(R.array.tea);
		ice  = getResources().getStringArray(R.array.ice);
		brix = getResources().getStringArray(R.array.brix);
		cups = getResources().getStringArray(R.array.cups);
		cupsNumber = getResources().getStringArray(R.array.cupsNumber);
		

		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.button1:
			infoAddSqliste();
			break;
		case R.id.button2:
			goToShoppingCart();
			break;
		default:
			break;
		}
		
		
	}
	private void goToShoppingCart() {
		// TODO Auto-generated method stub
		 //關閉
		MainActivity.this.finish(); 
		   
		//跳到shoppingCart畫面，用下面的方式才會刷新
		Intent intent_request = new Intent(MainActivity.this, shoppingCart.class);
		MainActivity.this.startActivity(intent_request);
		
		
	}

	private void infoAddSqliste() {
		// TODO Auto-generated method stub

		
		index1 = spin1.getSelectedItemPosition();
		index2 = spin2.getSelectedItemPosition();
		index3 = spin3.getSelectedItemPosition();
		index4 = spin4.getSelectedItemPosition();
		index5 = spin5.getSelectedItemPosition();
		index6 = spin6.getSelectedItemPosition();
		
		txv.setText(txv.getText()+
				"\n店名:"+	shop[index1]+
				"\n品名:"+	tea[index2]+
				"\n冰度:"+	ice[index3]+
				"\n糖度:"+	brix[index4]+
				"\n杯量:"+	cups[index5]+
				"\n杯數:"+	cupsNumber[index6]
				);
		//寫進SQLite
		 ContentValues cv = new ContentValues();
		   cv.put("_shop",		shop[index1] );
		   cv.put("_tea",		tea[index2]);
		   cv.put("_ice",		ice[index3] );
		   cv.put("_brix",		brix[index4]);
		   cv.put("_cups",		cups[index5] );
		   cv.put("_cupsNumber", cupsNumber[index6]);
		   
		 //添加方法
		 long long1 = db.insert(table_name, "", cv);
		 
		 if (long1 == -1) {
			  Toast.makeText(MainActivity.this,
			    "新增失敗！", Toast.LENGTH_SHORT)
			    .show();
			 } else {
			 int mID = (int) long1;
			  Toast.makeText(MainActivity.this,
					  "新增成功！:第"+mID+"筆資料", Toast.LENGTH_SHORT)
					    .show();
			 }
	}
}
