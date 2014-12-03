package com.andy.jizhang;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText et;
	Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		et = (EditText) findViewById(R.id.et);
		btn = (Button) findViewById(R.id.btn);

		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (et.getText().length() > 0) {
					// 彩蛋 9527
					if (Integer.parseInt(et.getText().toString()) == 9527) {
						new AlertDialog.Builder(MainActivity.this)
								.setTitle("致敬星爷")
								.setMessage("作者：andy\nQQ:356969902")
								.setPositiveButton("确定", null).show();
						return;
					}
					if (Integer.parseInt(et.getText().toString()) < 10
							&& Integer.parseInt(et.getText().toString()) > 0) {
						Intent intent = new Intent();
						intent.putExtra("number", et.getText().toString());
						intent.setClass(MainActivity.this,
								NameInputActivity.class);
						startActivity(intent);
						return;
					}
				}
				Toast.makeText(MainActivity.this, "输入错误", Toast.LENGTH_SHORT)
						.show();
			}
		});

	}

}
