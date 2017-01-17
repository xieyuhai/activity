package com.xyh.androidactivitytest;

import com.xyh.manage.AContext;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		String msg = AContext.getPushData();
		AContext.show(msg);
	}

	@Override
	protected void onResume() {
		super.onResume();

		if (AContext.getPullData() != null) {
			// 获取ThreeActivity返回的数据
			String one = AContext.getPullData(0);
			int two = AContext.getPullData(1);
			AContext.show("第一页传过来的值：" + one + ";" + two);
		}
	}

	public void btnThree(View view) {
		// 跳转到ThreeActivity并传递三个参数
		AContext.push(ThreeActivity.class, 1, "2", 3);
	}
}
