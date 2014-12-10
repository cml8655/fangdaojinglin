package cn.com.cml.dbl;

import org.androidannotations.annotations.EApplication;

import android.app.Application;
import android.content.IntentFilter;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.update.BmobUpdateAgent;
import cn.com.cml.dbl.listener.GlobalBaseListener;
import cn.com.cml.dbl.model.SmsModel;
import cn.com.cml.dbl.service.SMSHandler;
import cn.com.cml.dbl.service.SmsContentObserver;
import cn.com.cml.dbl.util.DeviceUtil;

import com.baidu.mapapi.SDKInitializer;

@EApplication
public class PetApplication extends Application {

	public static String deviceId;

	@Override
	public void onCreate() {

		super.onCreate();

		Bmob.initialize(this, "26068ea20379de265c19f7ff8e8f9f99");
		BmobInstallation.getCurrentInstallation(this).save();
		// 初始化版本信息
		BmobUpdateAgent.initAppVersion(getApplicationContext());

		// ActiveAndroid.initialize(this, false);

		// 百度地图
		SDKInitializer.initialize(this);

		deviceId = DeviceUtil.deviceImei(this);

		getContentResolver().registerContentObserver(SmsModel.SMS_CONTENT_URI,
				true, new SmsContentObserver(this, new SMSHandler(this)));

		IntentFilter filter = new IntentFilter(
				"android.provider.Telephony.SMS_RECEIVED");
		filter.setPriority(2147483647);

		this.registerReceiver(new GlobalBaseListener(), filter);
	}

}
