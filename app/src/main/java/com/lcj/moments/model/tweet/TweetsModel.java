package com.lcj.moments.model.tweet;

import com.lcj.commonlib.model.BaseModel;
import com.lcj.moments.model.api.API;

/**
 * @author Lu Chunjun
 * @date 2019/11/9
 * @decs user's tweet in moments project
 */
public class TweetsModel extends BaseModel {
    public TweetsModel() {
        super(API.USER_URL + API.TWEET_URL_SUFFIX);
    }
}
