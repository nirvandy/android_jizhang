package com.andy.jizhang;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ScoreActivity extends Activity{
	
	LinearLayout ll;
	ArrayList<String> namelist;			//名字列表
	ArrayList<TextView> TotalScoreText;	//分数显示用
	ArrayList<EditText> inputText;		//分数输入用
	ArrayList<Integer> scorelist;		//存储分数用
	LayoutInflater inflater;
	DataHelper dh;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("name");
		namelist = bundle.getStringArrayList("name");
		
		
		dh = new DataHelper(this, namelist.size());
		
		scorelist = new ArrayList<Integer>();
		TotalScoreText = new ArrayList<TextView>();
		inputText = new ArrayList<EditText>();
		inflater = (LayoutInflater) getSystemService(this.LAYOUT_INFLATER_SERVICE);
		
		ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		
		for (int i = 0; i < namelist.size(); i++) {
			View player = inflater.inflate(R.layout.score, null);
			
			TextView name = (TextView) player.findViewById(R.id.s_name);
			name.setText(namelist.get(i));

			TextView tv = (TextView) player.findViewById(R.id.s_tv);
			tv.setText("0");
			TotalScoreText.add(tv);

			EditText et = (EditText) player.findViewById(R.id.s_et);
			inputText.add(et);
			ll.addView(player);
		}
		
		Button add = new Button(this);
		add.setText("增加");
		add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//先判断是否只留了一个未填写
				boolean ok = false;
				int count = 0;
				for(EditText et:inputText){
					if (et.getText().length()>0) {
						count++;
					}
				}
				if (count == inputText.size()-1) {
					ok =true;
				}
				if (ok) {//完成分数list的计算
					int destion = 0;
					int total = 0;
					for (int i = 0; i < inputText.size(); i++) {
						if (inputText.get(i).getText().length()>0) {
							scorelist.add(i, -Integer.parseInt(inputText.get(i).getText().toString()));
						}
						else {
							destion = i;
							scorelist.add(i,0);
						}
					}
					for(int t : scorelist){
						total += t;
					}
					scorelist.set(destion, -total);
//					Toast.makeText(ScoreActivity.this,scorelist.toString(), Toast.LENGTH_SHORT).show();
					Log.i("andy", "score=="+scorelist.toString());
					//把分数对应人名添加到数据库
					for (int i = 0; i < namelist.size(); i++) {
						dh.save(i, scorelist.get(i));
					}
					
					//读取数据库，得到总分
					for (int i = 0; i < namelist.size(); i++) {
						ArrayList<Integer> result = dh.getScore(i);
						Log.i("andy_for_total_score", result.toString());
						total = 0;
						for (int t : result) {
							total += t;
						}
						TotalScoreText.get(i).setText(""+total);
					}
					//把输入清零,scorelist清零
					for (int i = 0; i < inputText.size(); i++) {
						inputText.get(i).setText("");
					}
					scorelist.clear();
					
				}else {
					Toast.makeText(ScoreActivity.this, "输入错误:"+count, Toast.LENGTH_SHORT).show();
				}
				
				
				
				
				
				
			}
		});
		
		ll.addView(add);
		
		Button more = new Button(this);
		more.setText("详细");
		more.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ScoreActivity.this,MoreActivity.class);
				Bundle bundle = new Bundle();
				bundle.putStringArrayList("name", namelist);
								
				for (int i = 0; i < namelist.size(); i++) {
					bundle.putIntegerArrayList(String.valueOf(i), dh.getScore(i));		
				}				
				intent.putExtra("data", bundle);
				startActivity(intent);
				
			}
		});
		
		ll.addView(more);
		
		Button end = new Button(this);
		end.setText("结束");
		end.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new Builder(ScoreActivity.this);
				builder.setMessage("退出并清空数据？");
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {				
					dh.delete();
					dialog.dismiss();
					ScoreActivity.this.finish();
				}
			});
				builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				builder.create().show();
			}
		});
		ll.addView(end);		
		setContentView(ll);
	}
	
	
	
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			    return true;
			   }
			   return super.onKeyDown(keyCode, event);
	};

}
