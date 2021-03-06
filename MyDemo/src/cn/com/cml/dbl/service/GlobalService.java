package cn.com.cml.dbl.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import cn.com.cml.dbl.model.SmsModel;
import cn.com.cml.dbl.receiver.ShoutdownReceiver;
import cn.com.cml.dbl.receiver.ShoutdownReceiver_;
import cn.com.cml.dbl.util.AppUtil;

/**
 * app全局后台服务，用于启动短信的监听功能
 * 
 * @author 陈孟琳
 * 
 *         2014年11月13日
 */
public class GlobalService extends Service {

	private ShoutdownReceiver shoutDownReceiver;

	@Override
	public void onCreate() {
		super.onCreate();

		shoutDownReceiver = new ShoutdownReceiver_();

		IntentFilter shoutdownFilter = new IntentFilter(Intent.ACTION_SHUTDOWN);
		shoutdownFilter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
		shoutdownFilter.addAction(ShoutdownReceiver.ACTION_SHOUT_CANCEL);
		shoutdownFilter.setPriority(Integer.MAX_VALUE);

		registerReceiver(shoutDownReceiver, shoutdownFilter);

		// 注册短信数据库监听
		getContentResolver().registerContentObserver(SmsModel.SMS_CONTENT_URI,
				true, new SmsContentObserver(this, new Handler()));
		Log.d("SmsContentObserver", "注册短信监听。。。。");

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		if (!AppUtil.serviceRunning(GlobalServiceHolder.class,
				getApplicationContext())) {
			Log.d("Service", "GlobalService.onStartCommand,启动holder");
			startHolderService();
		}

		return super.onStartCommand(intent, START_STICKY, startId);
	}

	private void startHolderService() {
		Intent intent = new Intent(getApplicationContext(),
				GlobalServiceHolder.class);
		startService(intent);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {

		unregisterReceiver(shoutDownReceiver);

		super.onDestroy();
		startHolderService();
	}

}
