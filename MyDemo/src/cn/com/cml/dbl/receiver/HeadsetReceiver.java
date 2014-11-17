package cn.com.cml.dbl.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 对耳机插入和拔出进行监听，此类必须要在代码中注入册才可使用，在配置文件中配置无效
 * 
 * @author 陈孟琳
 *
 *         2014年11月17日
 */
public class HeadsetReceiver extends BroadcastReceiver {

	/**
	 * The intent will have the following extra values:
	 * 
	 * state - 0 for unplugged, 1 for plugged. name - Headset type, human
	 * readable string microphone - 1 if headset has a microphone, 0 otherwise
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "插入耳机:" + intent.getAction(), Toast.LENGTH_LONG)
				.show();
	}

}
