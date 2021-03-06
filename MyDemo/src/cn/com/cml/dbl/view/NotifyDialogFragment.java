package cn.com.cml.dbl.view;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import cn.com.cml.dbl.R;
import cn.com.cml.dbl.ui.FontIconTextView;

@EFragment
public class NotifyDialogFragment extends DialogFragment {

	@FragmentArg
	Integer iconRes;

	@FragmentArg
	Integer msgRes;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),DialogFragment.STYLE_NO_TITLE);

		Context context = builder.getContext();

		View v = LayoutInflater.from(context).inflate(R.layout.fragment_dialog,
				null);

		FontIconTextView iconView = (FontIconTextView) v
				.findViewById(R.id.dialog_icon);

		TextView textView = (TextView) v.findViewById(R.id.dialog_msg);

		if (null != iconRes) {
			iconView.setText(context.getString(iconRes));
		}
		if (null != msgRes) {
			textView.setText(context.getString(msgRes));
		}

		builder.setView(v);

		builder.setInverseBackgroundForced(true);

		Dialog dialog = builder.create();

		// dialog.getWindow().requestFeature(DialogFragment.STYLE_NO_FRAME);

		return dialog;
	}
}
