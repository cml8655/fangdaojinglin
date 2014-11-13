package cn.com.cml.pets;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import cn.com.cml.pets.view.UserInfoFragment;
import cn.com.cml.pets.view.UserInfoFragment_;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class BaseMainActivity extends FragmentActivity implements
		OnClickListener {

	protected ResideMenu resideMenu;

	private int[] itemIds = new int[] { 0, 1, 2, 3 ,4};

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		injectMenu();
		initActionBar();

	}

	protected void initActionBar() {

		final ActionBar bar = getActionBar();

		bar.setDisplayHomeAsUpEnabled(true);
		bar.setDisplayShowTitleEnabled(true);
		bar.setDisplayUseLogoEnabled(true);
	}

	protected void injectMenu() {

		// 初始化侧边菜单栏
		resideMenu = new ResideMenu(this);
		resideMenu.setBackground(R.drawable.menu_background);
		resideMenu.attachToActivity(this);
		// resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_LEFT);

		// create menu items;
		String titles[] = { "相机", "短信操作", "百度地图", "主页","音量控制" };
		int icon[] = { R.drawable.ic_launcher, R.drawable.ic_launcher,
				R.drawable.ic_launcher, R.drawable.ic_launcher,R.drawable.icon_gcoding };

		for (int i = 0; i < titles.length; i++) {
			ResideMenuItem item = new ResideMenuItem(this, icon[i], titles[i],
					itemIds[i]);
			item.setOnClickListener(this);
			resideMenu.addMenuItem(item, ResideMenu.DIRECTION_LEFT);
		}
		// 设置初始化界面
		UserInfoFragment userInfoFragment = UserInfoFragment_.builder().build();

		replaceContainer(userInfoFragment);
	}

	protected void replaceContainer(Fragment target) {

		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();

		transaction.replace(R.id.container, target);
		transaction.disallowAddToBackStack();

		transaction.commit();
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return resideMenu.dispatchTouchEvent(ev);
	}

	@Override
	public void onClick(View v) {
		if (resideMenu.isOpened())
			resideMenu.closeMenu();
	}
}
