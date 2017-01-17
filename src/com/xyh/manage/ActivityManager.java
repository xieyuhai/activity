package com.xyh.manage;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

/**
 * activity管理类
 * @author xieyuhai
 *
 */
@SuppressWarnings("unchecked")
public class ActivityManager implements ActivityLifecycleCallbacks {

	private Activity activity;

	private ArrayList<Activity> activityList = new ArrayList<Activity>();

	public ArrayList<Activity> getActivityList() {
		return activityList;
	}

	public void exitLogin() {
		Iterator<Activity> iterator = activityList.iterator();
		while (iterator.hasNext() && activityList.size() != 1) {
			iterator.next().finish();
			iterator.remove();
		}
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Activity getCurrentActivity() {
		return activity;
	}

	/**
	 * 在activity创建时记录当前activity，并添加到集合中
	 */
	@Override
	public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
		Log.e("TAG", "onActivityCreated");
		setActivity(activity);
		activityList.add(activity);
	}

	@Override
	public void onActivityStarted(Activity activity) {
		Log.e("TAG", "onActivityStarted");
	}

	/**
	 * 在resume中进行更新当前的activity
	 */
	@Override
	public void onActivityResumed(Activity activity) {
		setActivity(activity);
		Log.e("TAG", "onActivityResumed");
	}

	@Override
	public void onActivityPaused(Activity activity) {
		Log.e("TAG", "onActivityPaused");
	}

	@Override
	public void onActivityStopped(Activity activity) {
		Log.e("TAG", "onActivityStopped");
	}

	@Override
	public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
		Log.e("TAG", "onActivitySaveInstanceState");
	}

	/**
	 * 在activity销毁时移除
	 */
	@Override
	public void onActivityDestroyed(Activity activity) {
		Log.e("TAG", "onActivityDestroyed");
		activityList.remove(activity);
	}

	private Object[] _pullData;

	/**
	 * 获取回退前activity传递过来的参数
	 * 
	 * @param position
	 * @return
	 */
	public <T extends Object> T getPullData(int position) {
		return isPullEmpty(position) ? (T) _pullData[position] : null;
	}

	private boolean isPullEmpty(int position) {
		return _pullData != null && _pullData.length > position;
	}

	/**
	 * 关闭activity并回传
	 * 
	 * @param data
	 */
	public void pullCurrentAcivity(Object... data) {
		_pullData = data;
		_pushData = null;
		activity.finish();
	}

	/**
	 * finish
	 */
	public void pullActivity() {
		pullCurrentAcivity();
	}

	/**
	 * push
	 */
	private Object[] _pushData;

	/**
	 * 利用索引获取跳转前界面传递的数据
	 * 
	 * @param position
	 * @return
	 */
	public <T extends Object> T getPushData(int position) {
		return isPushEmpty(position) ? (T) _pushData[position] : null;
	}

	private boolean isPushEmpty(int position) {
		return _pushData != null && _pushData.length > position;
	}

	/**
	 * 跳转activity并传递参数
	 * 
	 * @param activityClass
	 * @param pushData
	 */
	public void pushActivity(Class<?> activityClass, Object... pushData) {
		_pushData = pushData;
		_pullData = null;
		Intent intent = new Intent(activity, activityClass);
		activity.startActivity(intent);
	}

	public void pushNewTaskActivity(Class<?> activityClass, Object... pushData) {
		_pushData = pushData;
		_pullData = null;
		Intent intent = new Intent(activity, activityClass);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		activity.startActivity(intent);
	}

	public void pushClearTopActivity(Class<?> activityClass, Object... pushData) {
		_pushData = pushData;
		_pullData = null;
		Intent intent = new Intent(activity, activityClass);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		activity.startActivity(intent);
	}

	public void install(String filePath, int requestCode) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(new File(filePath)), "application/vnd.android.package-archive");
		activity.startActivityForResult(intent, requestCode);
	}

	/**
	 * android.permission.DELETE_PACKAGES
	 *
	 * @param packageName
	 */
	public void unInstall(String packageName, int requestCode) {
		Uri uri = Uri.parse("package:" + packageName);
		Intent intent = new Intent(Intent.ACTION_DELETE);
		intent.setData(uri);// 设置获取到的URI
		activity.startActivityForResult(intent, requestCode);
	}

	/**
	 * 获取启动的包名S
	 *
	 * @param packageName
	 */
	public void run(String packageName) {
		PackageManager pm = activity.getPackageManager();
		Intent i = pm.getLaunchIntentForPackage(packageName);
		activity.startActivity(i);
	}

	/**
	 * 退出应用
	 */
	public void exit() {
		Iterator<Activity> iterator = activityList.iterator();
		while (iterator.hasNext()) {
			iterator.next().finish();
			iterator.remove();
		}
	}
}
