package com.xyh.manage;

import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.widget.Toast;

public class AContext {

	private static ActivityManager sActivityManager;

	public static List<Activity> getActivityList() {
		return sActivityManager.getActivityList();
	}

	public static void exitLogin() {
		sActivityManager.exitLogin();
	}

	public static ActivityManager getActivityContext() {
		return sActivityManager;
	}

	public static Activity getCurrentActivity() {
		return sActivityManager.getCurrentActivity();
	}

	private static Application sApplication;

	public static Application getApplication() {
		return sApplication;
	}

	public static void pull(Object... data) {
		sActivityManager.pullCurrentAcivity(data);
	}

	public static <T extends Object> T getPullData() {
		return getPullData(0);
	}

	public static <T extends Object> T getPullData(int position) {
		return sActivityManager.getPullData(position);
	}

	public static void push(Class<?> activityClass, Object... data) {
		sActivityManager.pushActivity(activityClass, data);
	}

	public static void pushNewTaskActivity(Class<?> activityClass, Object... pushData) {
		sActivityManager.pushNewTaskActivity(activityClass, pushData);
	}

	public static void pushClearTopActivity(Class<?> activityClass, Object... pushData) {
		sActivityManager.pushClearTopActivity(activityClass, pushData);
	}

	public static <T extends Object> T getPushData() {
		return sActivityManager.getPushData(0);
	}

	public static <T extends Object> T getPushData(int position) {
		return sActivityManager.getPushData(position);
	}

	public static void init(Application currentApplication) {
		sApplication = currentApplication;
		sActivityManager = new ActivityManager();
		sApplication.registerActivityLifecycleCallbacks(sActivityManager);
	}

	public static void exit() {
		sActivityManager.exit();
	}

	public static void unInit() {
		if (sApplication != null && sActivityManager != null) {
			sApplication.unregisterActivityLifecycleCallbacks(sActivityManager);
		}
	}

	private static Toast toast = null;

	public static void show(Object msg) {
		if (msg == null || msg.toString().isEmpty()) {
			return;
		}
		if (toast == null) {
			if (msg instanceof Integer) {
				toast = Toast.makeText(sApplication, (Integer) msg, Toast.LENGTH_SHORT);
			} else {
				toast = Toast.makeText(sApplication, "" + msg, Toast.LENGTH_SHORT);
			}
		} else {
			if (msg instanceof Integer) {
				toast.setText((Integer) msg);
			} else {
				toast.setText("" + msg);
			}
		}
		toast.show();
	}
}
