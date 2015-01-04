package cn.com.cml.dbl.helper;

import org.androidannotations.annotations.EBean;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;

@EBean
public class ReverseCoderHelper {

	private BDLocation userLocation;
	private BDLocation mobileLocation;

	public boolean reverseUserLocationCoder(OnGetGeoCoderResultListener listener) {

		if (null == userLocation) {
			return false;
		}

		ReverseGeoCodeOption option = new ReverseGeoCodeOption();
		option.location(new LatLng(userLocation.getLatitude(), userLocation
				.getLongitude()));

		GeoCoder coder = GeoCoder.newInstance();
		coder.setOnGetGeoCodeResultListener(listener);

		return coder.reverseGeoCode(option);
	}

	public boolean reverseMobileLocationCoder(
			OnGetGeoCoderResultListener listener) {

		if (null == mobileLocation) {
			return false;
		}

		ReverseGeoCodeOption option = new ReverseGeoCodeOption();
		option.location(new LatLng(mobileLocation.getLatitude(), mobileLocation
				.getLongitude()));

		GeoCoder coder = GeoCoder.newInstance();
		coder.setOnGetGeoCodeResultListener(listener);

		return coder.reverseGeoCode(option);
	}

	public BDLocation getUserLocation() {
		return userLocation;
	}

	public BDLocation getMobileLocation() {
		return mobileLocation;
	}

	public void setUserLocation(BDLocation userLocation) {
		this.userLocation = userLocation;
	}

	public void setMobileLocation(BDLocation mobileLocation) {
		this.mobileLocation = mobileLocation;
	}

}