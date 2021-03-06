package com.mibarim.driver.adapters;

import android.text.TextUtils;
import android.view.LayoutInflater;

import com.mibarim.driver.R;
import com.mibarim.driver.models.UserInfo.DiscountModel;
import com.mibarim.driver.util.SingleTypeAdapter;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Adapter to display a list of traffic items
 */
public class DiscountAdapter extends SingleTypeAdapter<DiscountModel> {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MMMM dd");

    /**
     * @param inflater
     * @param items
     */
    public DiscountAdapter(final LayoutInflater inflater, final List<DiscountModel> items) {
        super(inflater, R.layout.discount_list_item);

        setItems(items);
    }

    /**
     * @param inflater
     */
    public DiscountAdapter(final LayoutInflater inflater) {
        this(inflater, null);

    }

    @Override
    public long getItemId(final int position) {
        final String id = String.valueOf(getItem(position));
        return !TextUtils.isEmpty(id) ? id.hashCode() : super
                .getItemId(position);
    }

    @Override
    protected int[] getChildViewIds() {
        return new int[]{R.id.discount_state,R.id.discount_title,R.id.discount_code};
    }

    @Override
    protected void update(final int position, final DiscountModel discountModel) {
        setText(0, discountModel.DiscountStateString);
        setText(1, discountModel.DiscountTitle);
        setText(2, discountModel.DiscountCode);
/*
        setText(0, eventResponse.Name);
        setText(1, eventResponse.Address);
        setText(2, eventResponse.TimeString);
        setText(3, eventResponse.Conductor);
        setText(4, eventResponse.ImageDescription);
*/
/*
        setText(3, String.valueOf(eventResponse.AccompanyCount));
        setText(4, eventResponse.PricingString);
        setText(5, eventResponse.CarString);
*/
    }

}
