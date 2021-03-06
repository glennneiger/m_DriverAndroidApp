package com.mibarim.driver.ui.fragments.mainFragments;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import com.beardedhen.androidbootstrap.AwesomeTextView;
import com.mibarim.driver.BootstrapApplication;
import com.mibarim.driver.R;
import com.mibarim.driver.models.RouteRequest;
import com.mibarim.driver.models.enums.TimingOptions;
import com.mibarim.driver.ui.fragments.InfoMessageFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Hamed on 3/5/2016.
 */
public class AddMainFragment extends Fragment implements View.OnTouchListener, TimePickerDialog.OnTimeSetListener {

    @Bind(R.id.do_done_btn)
    protected TextView do_done_btn;

    @Bind(R.id.home_editText)
    protected EditText home_editText;
    @Bind(R.id.return_cb_label)
    protected TextView return_cb_label;
    @Bind(R.id.return_checkBox)
    protected CheckBox return_checkBox;

    /*@Bind(R.id.home_editText)
    protected EditText home_editText;*/
    @Bind(R.id.work_editText)
    protected EditText work_editText;
    @Bind(R.id.fa_src)
    protected AwesomeTextView fa_src;
    @Bind(R.id.fa_dst)
    protected AwesomeTextView fa_dst;
    @Bind(R.id.fa_driver_pass)
    protected AwesomeTextView fa_driver_pass;

    @Bind(R.id.home_work_time)
    protected EditText home_work_time;
    @Bind(R.id.work_home_time)
    protected EditText work_home_time;
    @Bind(R.id.driver_pass)
    protected EditText driver_pass;
    /*

    @Bind(R.id.home_work_tlabel)
    protected TextView home_work_tlabel;
*/

    @Bind(R.id.desc_title)
    protected TextView desc_title;

    @Bind(R.id.home_work_layout)
    protected LinearLayout home_work_layout;
    @Bind(R.id.work_home_layout)
    protected LinearLayout work_home_layout;
    @Bind(R.id.return_layout)
    protected LinearLayout return_layout;

    @Bind(R.id.weekly_txt)
    protected TextView weekly_txt;
    @Bind(R.id.weekly_chk_bx)
    protected CheckBox weekly_chk_bx;


    @Bind(R.id.message_fragment)
    protected FrameLayout message_fragment;

    @Bind(R.id.sat)
    protected ToggleButton sat;
    @Bind(R.id.sun)
    protected ToggleButton sun;
    @Bind(R.id.mon)
    protected ToggleButton mon;
    @Bind(R.id.tue)
    protected ToggleButton tue;
    @Bind(R.id.wed)
    protected ToggleButton wed;
    @Bind(R.id.thu)
    protected ToggleButton thu;
    @Bind(R.id.fri)
    protected ToggleButton fri;

    private RelativeLayout layout;
    private TimePickerDialog timeDialog;
    private int currentDialog;
    int hour;
    int min;
    RouteRequest routeRequest;

    FragmentInterface mCallback;
    Calendar TheTime = null;
    Calendar TheReturnTime = null;

    public AddMainFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BootstrapApplication.component().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = (RelativeLayout) inflater.inflate(R.layout.fragment_main_add, container, false);
        initScreen();
        return layout;
    }

    private void initScreen() {
        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.message_fragment, new InfoMessageFragment())
                .commit();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (FragmentInterface) activity;
        } catch (ClassCastException e) {
        }

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.bind(this, getView());

        fa_src.setFontAwesomeIcon(mCallback.getOriginIcon());
        fa_dst.setFontAwesomeIcon(mCallback.getDestinationIcon());
        fa_driver_pass.setFontAwesomeIcon(mCallback.getDriverPassIcon());

        message_fragment.setVisibility(View.GONE);
        routeRequest = mCallback.getRouteRequest();
        //home_work_time.setText(routeRequest.TheTimeString());
        if (routeRequest.SatDatetime != null) {
            setColors(sat);
            sat.setChecked(true);
        }
        if (routeRequest.SunDatetime != null) {
            setColors(sun);
            sun.setChecked(true);
        }
        if (routeRequest.MonDatetime != null) {
            setColors(mon);
            mon.setChecked(true);
        }
        if (routeRequest.TueDatetime != null) {
            setColors(tue);
            tue.setChecked(true);
        }
        if (routeRequest.WedDatetime != null) {
            setColors(wed);
            wed.setChecked(true);
        }
        if (routeRequest.ThuDatetime != null) {
            setColors(thu);
            thu.setChecked(true);
        }
        if (routeRequest.FriDatetime != null) {
            setColors(fri);
            fri.setChecked(true);
        }
        setAddresses();
        if (mCallback.isShowWeeklyChkBx())
            weekly_chk_bx.setVisibility(View.VISIBLE);
        else
            weekly_chk_bx.setVisibility(View.GONE);
        if(mCallback.isShowReturnChkBx()){
            work_home_layout.setVisibility(View.VISIBLE);
            return_layout.setVisibility(View.VISIBLE);
        }else{
            work_home_layout.setVisibility(View.GONE);
            return_layout.setVisibility(View.GONE);
        }
        desc_title.setText(mCallback.getTitleDescription());
        driver_pass.setText(mCallback.getDriverPassenger());


        home_editText.setHint(mCallback.getOriginLabel());
        work_editText.setHint(mCallback.getDestinationLabel());
        home_work_time.setHint(mCallback.getTimeLabel());
        home_editText.setOnTouchListener(this);
        driver_pass.setOnTouchListener(this);
        work_editText.setOnTouchListener(this);
        return_cb_label.setOnTouchListener(this);
        //work_editText.setOnTouchListener(this);
        //home_work_layout.setOnTouchListener(this);
        home_work_time.setOnTouchListener(this);
        //work_home_layout.setOnTouchListener(this);
        work_home_time.setOnTouchListener(this);
        weekly_txt.setOnTouchListener(this);
        //weekly_chk_bx.setOnTouchListener(this);
        do_done_btn.setOnTouchListener(this);
        return_checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    work_home_time.setEnabled(true);
                }else {
                    work_home_time.setEnabled(false);
                }
            }
        });

        sat.setOnTouchListener(this);
        sun.setOnTouchListener(this);
        mon.setOnTouchListener(this);
        tue.setOnTouchListener(this);
        wed.setOnTouchListener(this);
        thu.setOnTouchListener(this);
        fri.setOnTouchListener(this);


        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        min = calendar.get(Calendar.MINUTE);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            switch (v.getId()) {
                case R.id.weekly_txt:
                    weekly_chk_bx.toggle();
                    break;
                case R.id.do_done_btn:
                    routeRequest = getDateParams();
                    mCallback.Done(routeRequest);
                    break;
                case R.id.home_editText:
                    //case R.id.home_editText:
                    mCallback.gotoOriginAddMapActivity();
                    break;
                case R.id.return_cb_label:
                    if(return_checkBox.isChecked()){
                        return_checkBox.setChecked(false);
                    }else {
                        return_checkBox.setChecked(true);
                    }
                    break;
                case R.id.work_textView:
                    //case R.id.work_editText:
                    mCallback.gotoDestinationAddMapActivity();
                    break;
                case R.id.home_work_time:
                    //case R.id.home_work_time:
                    timeDialog = new TimePickerDialog(getActivity(), this, hour, min, true);
                    timeDialog.show();
                    currentDialog = R.id.home_work_time;
                    break;
                case R.id.work_home_time:
                    //case R.id.home_work_time:
                    timeDialog = new TimePickerDialog(getActivity(), this, hour, min, true);
                    timeDialog.show();
                    currentDialog = R.id.work_home_time;
                    break;
                case R.id.driver_pass:
                    mCallback.gotoDriverActivity();
                    break;
                case R.id.sat:
                    setColors(sat);
                    break;
                case R.id.sun:
                    setColors(sun);
                    break;
                case R.id.mon:
                    setColors(mon);
                    break;
                case R.id.tue:
                    setColors(tue);
                    break;
                case R.id.wed:
                    setColors(wed);
                    break;
                case R.id.thu:
                    setColors(thu);
                    break;
                case R.id.fri:
                    setColors(fri);
                    break;

            }
            //for toggleButtons
            return false;
        }
        return false;
    }


    // Container Activity must implement this interface
    public interface FragmentInterface {
        public String getTitleDescription();

        public String getOriginLabel();

        public String getDestinationLabel();

        public String getTimeLabel();

        public RouteRequest getRouteRequest();

        public void gotoOriginAddMapActivity();

        public void gotoDestinationAddMapActivity();

        public void gotoDriverActivity();

        public void Done(RouteRequest routeRequest);

        public String getOriginAddress();

        public String getDriverPassenger();

        public String getDestinationAddress();

        public Boolean isShowWeeklyChkBx();

        public Boolean isShowReturnChkBx();

        public String getDriverPassIcon();

        public String getOriginIcon();

        public String getDestinationIcon();
    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar calendar = Calendar.getInstance();
        switch (currentDialog) {
            case R.id.home_work_time:
                home_work_time.setText(hourOfDay + ":" + minute);
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                TheTime = calendar;
                break;
            case R.id.work_home_time:
                work_home_time.setText(hourOfDay + ":" + minute);
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                TheReturnTime = calendar;
                break;
        }
    }

    private void setColors(ToggleButton tb) {
        if (tb.isChecked()) {
            tb.setTextColor(getResources().getColor(R.color.week_time_text_off));
            tb.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));
        } else {
            tb.setTextColor(getResources().getColor(R.color.week_time_text_on));
            tb.setBackgroundColor(getResources().getColor(R.color.week_time_on));
        }

    }

    public void setAddresses() {
        home_editText.setText(mCallback.getOriginAddress());
        work_editText.setText(mCallback.getDestinationAddress());
    }

    public void setDriver() {
        driver_pass.setText(mCallback.getDriverPassenger());
        fa_driver_pass.setFontAwesomeIcon(mCallback.getDriverPassIcon());

        home_editText.setText("");
        work_editText.setText("");
    }

    public RouteRequest getDateParams() {
        RouteRequest routeRequest = new RouteRequest();
        if (weekly_chk_bx.isChecked()) {
            routeRequest.TimingOption = TimingOptions.InWeek;
        } else {
            routeRequest.TimingOption = TimingOptions.Weekly;
        }
        if(return_checkBox.isChecked()){
            routeRequest.IsReturn=true;
        }else {
            routeRequest.IsReturn=false;
        }
        routeRequest.TheTime = TheTime; //castStringToCal(home_work_time.getText().toString());
        routeRequest.TheReturnTime= TheReturnTime;
        if (sat.isChecked()) {
            routeRequest.SatDatetime = TheTime;//castStringToCal(home_work_time.getText().toString());
        }
        if (sun.isChecked()) {
            routeRequest.SunDatetime = TheTime;// castStringToCal(home_work_time.getText().toString());
        }
        if (mon.isChecked()) {
            routeRequest.MonDatetime = TheTime;//castStringToCal(home_work_time.getText().toString());
        }
        if (tue.isChecked()) {
            routeRequest.TueDatetime = TheTime;//castStringToCal(home_work_time.getText().toString());
        }
        if (wed.isChecked()) {
            routeRequest.WedDatetime = TheTime;// castStringToCal(home_work_time.getText().toString());
        }
        if (thu.isChecked()) {
            routeRequest.ThuDatetime = TheTime;//castStringToCal(home_work_time.getText().toString());
        }
        if (fri.isChecked()) {
            routeRequest.FriDatetime = TheTime;//castStringToCal(home_work_time.getText().toString());
        }
        return routeRequest;
    }

    private Calendar castStringToCal(String dateString) {
        Calendar cal = Calendar.getInstance();
        if (dateString == null || dateString.equals("")) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            Date date = format.parse(dateString);
            cal.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal;
    }

    public void showMsg(String msg) {
        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.message_fragment);
        ((InfoMessageFragment) fragment).setActionBtn(getString(R.string.ok));
        ((InfoMessageFragment) fragment).setMsg(msg);
        message_fragment.setVisibility(View.VISIBLE);
    }
}
