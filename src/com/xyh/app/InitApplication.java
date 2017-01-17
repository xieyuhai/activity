package com.xyh.app;

import com.xyh.manage.AContext;

import android.app.Application;

/**
 * 
 * @author xieyuhai
 *
 */
public class InitApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		/**
		 * 初始化注册activity生命周期监听
		 */
		AContext.init(this);
	}
}
