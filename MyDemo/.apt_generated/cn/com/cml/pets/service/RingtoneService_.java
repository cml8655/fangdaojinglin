//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.0.1.
//


package cn.com.cml.pets.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import cn.com.cml.pets.util.PrefUtil_;
import cn.com.cml.pets.util.VolumeUtil_;

public final class RingtoneService_
    extends RingtoneService
{


    public static RingtoneService_.IntentBuilder_ intent(Context context) {
        return new RingtoneService_.IntentBuilder_(context);
    }

    public static RingtoneService_.IntentBuilder_ intent(android.app.Fragment fragment) {
        return new RingtoneService_.IntentBuilder_(fragment);
    }

    public static RingtoneService_.IntentBuilder_ intent(android.support.v4.app.Fragment supportFragment) {
        return new RingtoneService_.IntentBuilder_(supportFragment);
    }

    private void init_() {
        prefUtil = new PrefUtil_(this);
        vibrator = ((Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE));
        volumeUtil = VolumeUtil_.getInstance_(this);
    }

    @Override
    public void onCreate() {
        init_();
        super.onCreate();
    }

    public static class IntentBuilder_ {

        private Context context_;
        private final Intent intent_;
        private android.app.Fragment fragment_;
        private android.support.v4.app.Fragment fragmentSupport_;

        public IntentBuilder_(Context context) {
            context_ = context;
            intent_ = new Intent(context, RingtoneService_.class);
        }

        public IntentBuilder_(android.app.Fragment fragment) {
            fragment_ = fragment;
            context_ = fragment.getActivity();
            intent_ = new Intent(context_, RingtoneService_.class);
        }

        public IntentBuilder_(android.support.v4.app.Fragment fragment) {
            fragmentSupport_ = fragment;
            context_ = fragment.getActivity();
            intent_ = new Intent(context_, RingtoneService_.class);
        }

        public Intent get() {
            return intent_;
        }

        public RingtoneService_.IntentBuilder_ flags(int flags) {
            intent_.setFlags(flags);
            return this;
        }

        public ComponentName start() {
            return context_.startService(intent_);
        }

        public boolean stop() {
            return context_.stopService(intent_);
        }

    }

}
