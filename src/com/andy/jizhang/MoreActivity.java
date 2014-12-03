package com.andy.jizhang;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MoreActivity extends Activity{
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("data");
		ArrayList<String> nameList = bundle.getStringArrayList("name");
		
		//get players scrore
		ArrayList<ArrayList<Integer>> scoreList = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < nameList.size(); i++) {
			scoreList.add(bundle.getIntegerArrayList(String.valueOf(i)));
			Log.i("scorelist", bundle.getIntegerArrayList(String.valueOf(i)).toString());
		}
		
		
		ScrollView scrollView = new ScrollView(this);
		LinearLayout linearLayout=new LinearLayout(this);           
		TableLayout tableLayout = new TableLayout(this);
		tableLayout.setStretchAllColumns(true);
		
		scrollView.addView(linearLayout);
		linearLayout.setOrientation(LinearLayout.VERTICAL); 
		linearLayout.addView(tableLayout);
		
		TableRow nameTableRow = new TableRow(this);

		for (int i = 0; i < nameList.size(); i++) {
			TextView textView = new TextView(this);
			textView.setText(nameList.get(i));		
			nameTableRow.addView(textView);
			
		}
		tableLayout.addView(nameTableRow);
		
		int max = 0;
		for (ArrayList<Integer> i : scoreList) {
			if (i.size()>max) {
				max = i.size();
			}	
		}
		
		for (int i = 0; i < max; i++) {
			TableRow tableRow = new TableRow(this);
			for (int j = 0; j < scoreList.size(); j++) {
				TextView textView = new TextView(this);
				if (i<scoreList.get(j).size()) {
					textView.setText(scoreList.get(j).get(i)+"");
				}else{
					textView.setText(" ");
				}
				tableRow.addView(textView);
			}
			tableLayout.addView(tableRow);
		}
		
		
		setContentView(scrollView);
		
		
//		linearLayout.invalidate();
	}

}
