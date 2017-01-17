package com.xyh.androidactivitytest;

import com.xyh.manage.AContext;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * activity管理及传值
 * 
 * @author xieyuhai
 *
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void btnSecond(View view) {
		AContext.push(SecondActivity.class, "SecondActivity");
	}
}
