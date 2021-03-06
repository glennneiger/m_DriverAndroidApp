package com.mibarim.driver.ui.fragments.userInfoFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.mibarim.driver.BootstrapApplication;
import com.mibarim.driver.R;
import com.mibarim.driver.models.UserInfoModel;
import com.mibarim.driver.ui.activities.UserInfoDetailActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Hamed on 3/5/2016.
 */
public class BankFragment extends Fragment {

    private LinearLayout layout;
    /*private static final int BANK_CARD_REQUEST_CAMERA = 11;
    private static final int BANK_CARD_SELECT_FILE = 12;*/


    @Bind(R.id.bank_shaba)
    protected AutoCompleteTextView bank_shaba;
    @Bind(R.id.bank_Name)
    protected AutoCompleteTextView bank_Name;
    @Bind(R.id.bank_account)
    protected AutoCompleteTextView bank_account;
    /*@Bind(R.id.bank_card_image)
    protected BootstrapThumbnail bank_card_image;*/
    private Tracker mTracker;

    public BankFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BootstrapApplication.component().inject(this);
        // Obtain the shared Tracker instance.
        BootstrapApplication application = (BootstrapApplication) getActivity().getApplication();
        mTracker = application.getDefaultTracker();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = (LinearLayout) inflater.inflate(R.layout.fragment_bank_info, container, false);
        return layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.bind(this, getView());

        UserInfoModel userInfoModel = ((UserInfoDetailActivity) getActivity()).getUserInfo();
        if(userInfoModel!=null){
            bank_shaba.setText(userInfoModel.BankShaba);
            bank_Name.setText(userInfoModel.BankName);
            bank_account.setText(userInfoModel.BankAccountNo);
            //bank_card_image.setImageBitmap(((UserInfoActivity) getActivity()).getImageById(userInfoModel.BankImageId));
        }
        /*bank_card_image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    selectImage();
                }
                return true;
            }
        });*/
/*
        if (licenseInfo != null) {
            licenseNo.setText(licenseInfo.LicenseNo);
            if (licenseInfo.Base64LicensePic != null && !licenseInfo.Base64LicensePic.equals("")) {
                byte[] decodedString = Base64.decode(licenseInfo.Base64LicensePic, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                licenseImage.setImageBitmap(decodedByte);
            }
            licenseImage.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        selectImage();
                    }
                    return true;
                }
            });

        }
*/
        mTracker.setScreenName("BankFragment");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        mTracker.send(new HitBuilders.EventBuilder().setCategory("Fragment").setAction("BankFragment").build());

    }

    /*private void selectImage() {
        final CharSequence[] items = {getString(R.string.camera), getString(R.string.fromGallery), getString(R.string.later)};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals(getString(R.string.camera))) {
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        // Check Permissions Now
                        // Callback onRequestPermissionsResult interceptado na Activity MainActivity0
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.CAMERA},
                                BANK_CARD_REQUEST_CAMERA);
                    } else {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        getActivity().startActivityForResult(intent, BANK_CARD_REQUEST_CAMERA);
                    }
                } else if (items[item].equals(getString(R.string.fromGallery))) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image*//*");
                    getActivity().startActivityForResult(
                            Intent.createChooser(intent, getString(R.string.choose_pic)),
                            BANK_CARD_SELECT_FILE);
                } else if (items[item].equals(getString(R.string.later))) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }*/

    public UserInfoModel getBankInfo() {
        UserInfoModel model = new UserInfoModel();
        model.BankShaba = bank_shaba.getText().toString();
        model.BankName = bank_Name.getText().toString();
        model.BankAccountNo = bank_account.getText().toString();
        return model;
    }

    /*public void setBankCardImage(ImageResponse imageResponse) {
        byte[] decodedString = Base64.decode(imageResponse.Base64ImageFile, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        //bank_card_image.setImageBitmap(decodedByte);
    }*/
}
