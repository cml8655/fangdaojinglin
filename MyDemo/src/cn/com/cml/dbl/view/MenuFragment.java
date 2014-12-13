package cn.com.cml.dbl.view;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import cn.com.cml.dbl.MainActivity;
import cn.com.cml.dbl.R;

@EFragment(R.layout.fragment_menu)
public class MenuFragment extends Fragment {

	private static final String TAG = "MenuFragment";

	public static final String ACTION_MENU_CHANGE = "cn.com.cml.dbl.view.MenuFragment.ACTION_MENU_CHANGE";
	public static final String EXTRA_MENUITEM = "cn.com.cml.dbl.view.MenuFragment.EXTRA_MENUITEM";

	public static enum MenuItems {

		HOME(R.id.menu_home, HomeFragment_.class, R.string.menu_home), MAP(
				R.id.menu_monitor, MobileMonitorFragment_.class,
				R.string.menu_monitor), ALARM(R.id.menu_alarm,
				AlarmFragment_.class, R.string.menu_alarm);

		private int id;
		private Class<? extends Fragment> clazz;
		private int title;

		public static MenuItems getById(int id) {

			MenuItems[] values = MenuItems.values();

			for (MenuItems value : values) {
				if (value.getId() == id) {
					return value;
				}
			}

			return null;
		}

		private MenuItems(int id, Class<? extends Fragment> clazz, int title) {
			this.id = id;
			this.clazz = clazz;
			this.title = title;
		}

		public int getTitle() {
			return title;
		}

		public int getId() {
			return id;
		}

		public Class<? extends Fragment> getClazz() {
			return clazz;
		}

	}

	private SparseArray<Fragment> menus = new SparseArray<Fragment>(5);

	private int selectedId = -1;

	@FragmentArg
	MenuItems initMenuItem = MenuItems.HOME;// 初始化时默认的菜单

	private BroadcastReceiver itemChangeReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {

			if (intent.hasExtra(EXTRA_MENUITEM)) {
				Activity activity = getActivity();
				if (null != activity && !activity.isFinishing()) {

					int menuId = intent.getIntExtra(EXTRA_MENUITEM, -1);
					toggleMenu(menuId, false);
				}
			}
		}
	};

	public void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		IntentFilter filter = new IntentFilter(ACTION_MENU_CHANGE);
		getActivity().registerReceiver(itemChangeReceiver, filter);
	}

	@Override
	public void onDestroy() {
		getActivity().unregisterReceiver(itemChangeReceiver);
		super.onDestroy();
	}

	@AfterViews
	public void initConfig() {

		final int id = initMenuItem.getId();

		Fragment initFragment = null;

		try {
			initFragment = initMenuItem.getClazz().newInstance();
		} catch (Exception e) {
			Log.e(TAG, "initClass error", e);
			initFragment = HomeFragment_.builder().build();
		}

		selectedId = id;
		menus.put(id, initFragment);

		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();
		transaction.add(R.id.content_frame, initFragment);
		transaction.commit();

	}

	@Click(value = { R.id.menu_home, R.id.menu_photo, R.id.menu_sms,
			R.id.menu_alarm, R.id.menu_volume, R.id.menu_monitor })
	public void click(View clickView) {

		final int id = clickView.getId();

		toggleMenu(id, true);
	}

	private void toggleMenu(int id, boolean leftMenuShow) {

		// 点击当前菜单
		if (id == selectedId) {

			if (leftMenuShow) {
				closeMenu();
			}

			return;
		}

		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();

		transaction.setCustomAnimations(R.anim.right_in, R.anim.left_fadeout,
				R.anim.right_fadein, R.anim.left_fadeout);

		MenuItems menu = MenuItems.getById(id);

		if (null == menu) {
			return;
		}

		Fragment fragment = menus.get(R.id.menu_photo);

		if (null == fragment) {

			try {

				fragment = menu.getClazz().newInstance();
				transaction.add(R.id.content_frame, fragment);
				menus.append(id, fragment);

			} catch (Exception e) {

				Log.e(TAG, "实例化菜单失败");
				return;
			}

		}

		if (selectedId != -1) {
			transaction.hide(menus.get(selectedId));
		}

		transaction.show(fragment);

		transaction.commit();

		((MainActivity) getActivity()).setCustomTitle(menu.getTitle());

		selectedId = id;

		if (leftMenuShow) {
			closeMenu();
		}
	}

	private void closeMenu() {
		((MainActivity) getActivity()).closeMenu();
	}
}
