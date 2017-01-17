package com.xyh.androidactivitytest;

import com.xyh.manage.AContext;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class ThreeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_three);

		// 获取SecondActivity传递的参数
		int one = AContext.getPushData(0);
		String two = AContext.getPushData(1);
		int three = AContext.getPushData(2);

		AContext.show("第二页传过来的值：" + one + ";" + two + ";" + three);
	}

	public void btnBackSecond(View view) {

		// 关闭当前activity并传递参数
		AContext.pull("2", 3);
	}

	public void btnExit(View view) {
		// 退出应用
		AContext.exit();
	}
}
