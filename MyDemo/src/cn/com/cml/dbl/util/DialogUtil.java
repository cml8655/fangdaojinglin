package cn.com.cml.dbl.util;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import cn.com.cml.dbl.R;
import cn.com.cml.dbl.view.DefaultDialogFragment.OnItemClickListener;
import cn.com.cml.dbl.view.DefaultDialogFragment_;
import cn.com.cml.dbl.view.NotifyDialogFragment;
import cn.com.cml.dbl.view.NotifyDialogFragment_;

import com.gitonway.lee.niftynotification.lib.Configuration;
import com.gitonway.lee.niftynotification.lib.Effects;
import com.gitonway.lee.niftynotification.lib.Manager;
import com.gitonway.lee.niftynotification.lib.NiftyNotificationView;

public class DialogUtil {

	private static Configuration cfg;

	static {
		cfg = new Configuration.Builder().setAnimDuration(700)
				.setDispalyDuration(3000).setBackgroundColor("#FFBDC3C7")
				.setTextColor("#FF444444").setIconBackgroundColor("#FFFFFFFF")
				.setTextPadding(5) // dp
				.setViewHeight(48) // dp
				.setTextLines(2) // You had better use setViewHeight and
				.setTextGravity(Gravity.LEFT) // only text def
				.build();
	}

	public static void showNiftyTip(Activity ac, String msg, int layoutId) {

		final NiftyNotificationView niftyView = NiftyNotificationView.build(ac,
				msg, Effects.thumbSlider, layoutId, cfg).setIcon(
				R.drawable.launcher);

		niftyView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				niftyView.removeSticky();
			}
		});

		niftyView.showSticky();
	}

	public static void showTip(Context context, String str) {

		if (str.endsWith("\n")) {
			str = str.substring(0, str.length() - 1);
		}

		Toast toast = Toast.makeText(context, str, Toast.LENGTH_LONG);

		toast.setGravity(Gravity.TOP, 0, 0);

		toast.show();

	}

	public static void toast(Context context, int str) {
		Toast.makeText(context, context.getString(str), Toast.LENGTH_LONG)
				.show();
	}

	public static DialogFragment defaultDialog(Integer text, int requestId,
			Activity activity) {

		if (activity instanceof OnItemClickListener) {
			return DefaultDialogFragment_.builder()
					.negativeBtnText(R.string.system_btn_cancel)
					.positiveBtnText(R.string.system_btn_ensure)
					.requestId(requestId).title(R.string.system_tip).text(text)
					.build();
		}

		throw new IllegalArgumentException(
				"activity must extends OnItemClickListener");
	}

	public static DialogFragment defaultDialog(Integer text, int requestId,
			Fragment targetFragment) {

		if (targetFragment instanceof OnItemClickListener) {

			DialogFragment dialog = DefaultDialogFragment_.builder()
					.negativeBtnText(R.string.system_btn_cancel)
					.positiveBtnText(R.string.system_btn_ensure)
					.requestId(requestId).title(R.string.system_tip).text(text)
					.build();

			dialog.setTargetFragment(targetFragment, 1);

			return dialog;
		}

		throw new IllegalArgumentException(
				"fragment must extends OnItemClickListener");
	}

	public static DialogFragment defaultSingleSelectDialog(Integer items,
			Integer title, int requestId, Fragment targetFragment) {

		DialogFragment dialog = DefaultDialogFragment_.builder()
				.requestId(requestId).title(title).singleChoiceItems(items)
				.build();

		dialog.setTargetFragment(targetFragment, 1);

		return dialog;
	}

	public static DialogFragment defaultSingleSelectDialog(Integer items,
			Integer title, int requestId) {

		DialogFragment dialog = DefaultDialogFragment_.builder()
				.requestId(requestId).title(title).singleChoiceItems(items)
				.build();

		return dialog;
	}

	public static DialogFragment notifyDialogBuild(int icon, int msg) {

		NotifyDialogFragment dialog = NotifyDialogFragment_.builder()
				.iconRes(icon).msgRes(msg).build();

		return dialog;

	}
}
