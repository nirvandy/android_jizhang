package com.andy.jizhang;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NameInputActivity extends Activity{
	
	
	LinearLayout ll;
	//人数
	int number;
	//人名输入框
	List<EditText> list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		number = Integer.parseInt(intent.getStringExtra("number"));
		
		list = new ArrayList<EditText>();
		ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		TextView tv =new TextView(this);
		tv.setText("请依次输入姓名：");
		tv.setTextSize(40);
		ll.addView(tv);
		
		for (int i = 0; i < number; i++) {
			EditText et = new EditText(this);
			et.setHint(i+1+"号玩家");
			list.add(et);
			ll.addView(et);
			
		}
		
		Button btn = new Button(this);
		btn.setText("开始");
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				ArrayList<String> nameList = new ArrayList<String>();//人名列表
				for (int i = 0; i < number; i++) {
					if (list.get(i).getText().length()>0) {
						nameList.add(list.get(i).getText().toString());
					}			
				}
//			Toast.makeText(NameInputActivity.this, nameList.toString(), Toast.LENGTH_SHORT).show();	
				Intent intent = new Intent();
				intent.setClass(NameInputActivity.this, ScoreActivity.class);
				Bundle bundle = new Bundle();
				bundle.putStringArrayList("name", nameList);
				intent.putExtra("name", bundle);
				startActivity(intent);
				
			}
		});
			
		ll.addView(btn);
		setContentView(ll);
		
	}
	

}
