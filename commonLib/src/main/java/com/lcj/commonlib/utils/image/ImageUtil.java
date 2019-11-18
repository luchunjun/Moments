package com.lcj.commonlib.utils.image;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ImageUtil {
    public static void loadImage(final Activity activity, final ImageView imageView, final String imageUrl, final int defaultImageId) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RequestOptions requestOptions = new RequestOptions().error(defaultImageId).placeholder(defaultImageId);
                Glide.with(activity)
                        .load(imageUrl).apply(requestOptions)
                        .into(imageView);
            }
        });
    }
}
