package com.blankj.common.item;

import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.blankj.base.rv.ItemViewHolder;
import com.blankj.common.R;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.Utils;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

/**
 * <pre>
 *     author: blankj
 *     blog  : http://blankj.com
 *     time  : 2019/10/31
 *     desc  :
 * </pre>
 */
public class CommonItemTitle extends CommonItem {

    private CharSequence                 mTitle;
    private Utils.Supplier<CharSequence> mGetTitleSupplier;
    private boolean                      mIsTitleCenter;
    private CharSequence                 mContent;

    public CommonItemTitle(@NonNull Utils.Supplier<CharSequence> getTitleSupplier, boolean isTitleCenter) {
        super(R.layout.common_item_title_content);
        mTitle = mGetTitleSupplier.get();
        mGetTitleSupplier = getTitleSupplier;
        mIsTitleCenter = isTitleCenter;
    }

    public CommonItemTitle(@StringRes int title, boolean isTitleCenter) {
        this(StringUtils.getString(title), isTitleCenter);
    }

    public CommonItemTitle(@NonNull CharSequence title, boolean isTitleCenter) {
        super(R.layout.common_item_title_content);
        mTitle = title;
        mIsTitleCenter = isTitleCenter;
    }

    public CommonItemTitle(@NonNull CharSequence title, CharSequence content) {
        super(R.layout.common_item_title_content);
        mTitle = title;
        mContent = content;
    }

    @Override
    public void bind(@NonNull ItemViewHolder holder, int position) {
        super.bind(holder, position);
        if (mGetTitleSupplier != null) {
            mTitle = mGetTitleSupplier.get();
        }
        final TextView titleTv = holder.findViewById(R.id.commonItemTitleTv);
        final TextView contentTv = holder.findViewById(R.id.commonItemContentTv);

        titleTv.setText(mTitle);
        contentTv.setText(mContent);

        if (isViewType(R.layout.common_item_title_content)) {
            if (mIsTitleCenter) {
                holder.itemView.setBackgroundColor(Color.TRANSPARENT);
                titleTv.setGravity(Gravity.CENTER_HORIZONTAL);
                titleTv.getPaint().setFakeBoldText(true);
            } else {
                titleTv.setGravity(Gravity.START);
                titleTv.getPaint().setFakeBoldText(false);
            }
        }
    }

    public void setTitle(CharSequence title) {
        mTitle = title;
        update();
    }

    public CharSequence getTitle() {
        return mTitle;
    }
}
