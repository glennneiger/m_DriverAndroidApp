package com.mibarim.driver.ui.fragments.TripProfileFragments;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mibarim.driver.BootstrapApplication;
import com.mibarim.driver.R;
import com.mibarim.driver.models.UserInfo.UserRouteModel;
import com.mibarim.driver.ui.activities.TripProfileActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Hamed on 3/4/2016.
 */
public class ProfileScoreInfoFragment extends Fragment {

    private Activity context;
    private LinearLayout layout;

    @Bind(R.id.src_address)
    protected TextView src_address;

    @Bind(R.id.dst_address)
    protected TextView dst_address;

    @Bind(R.id.timing)
    protected TextView timing;
    @Bind(R.id.sat)
    protected TextView sat;
    @Bind(R.id.sun)
    protected TextView sun;
    @Bind(R.id.mon)
    protected TextView mon;
    @Bind(R.id.tue)
    protected TextView tue;
    @Bind(R.id.wed)
    protected TextView wed;
    @Bind(R.id.thu)
    protected TextView thu;
    @Bind(R.id.fri)
    protected TextView fri;

    public ProfileScoreInfoFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BootstrapApplication.component().inject(this);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_trip_profile_score_info, container, false);
        return layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.bind(this, getView());
        UserRouteModel v=((TripProfileActivity)getActivity()).getProfileTripInfo();
        src_address.setText(v.SrcAddress);
        dst_address.setText(v.DstAddress);
        timing.setText(v.TimingString);
        if (v.Sat) {
            sat.setBackgroundDrawable(getResource(R.drawable.roundedgraybutton));
        } else {
            sat.setBackgroundDrawable(getResource(R.drawable.roundedwhitebutton));
        }
        if (v.Sun) {
            sun.setBackgroundDrawable(getResource(R.drawable.roundedgraybutton));
        } else {
            sun.setBackgroundDrawable(getResource(R.drawable.roundedwhitebutton));
        }
        if (v.Mon) {
            mon.setBackgroundDrawable(getResource(R.drawable.roundedgraybutton));
        } else {
            mon.setBackgroundDrawable(getResource(R.drawable.roundedwhitebutton));
        }
        if (v.Tue) {
            tue.setBackgroundDrawable(getResource(R.drawable.roundedgraybutton));
        } else {
            tue.setBackgroundDrawable(getResource(R.drawable.roundedwhitebutton));
        }
        if (v.Wed) {
            wed.setBackgroundDrawable(getResource(R.drawable.roundedgraybutton));
        } else {
            wed.setBackgroundDrawable(getResource(R.drawable.roundedwhitebutton));
        }
        if (v.Thu) {
            thu.setBackgroundDrawable(getResource(R.drawable.roundedgraybutton));
        } else {
            thu.setBackgroundDrawable(getResource(R.drawable.roundedwhitebutton));
        }
        if (v.Fri) {
            fri.setBackgroundDrawable(getResource(R.drawable.roundedgraybutton));
        } else {
            fri.setBackgroundDrawable(getResource(R.drawable.roundedwhitebutton));
        }
    }

    private Drawable getResource(int id) {
        return context.getResources().getDrawable(id);
    }

}
