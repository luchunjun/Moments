package com.lcj.moments.model.user;


import com.lcj.commonlib.model.BaseModel;
import com.lcj.moments.model.api.API;

/**
 * @author Lu Chunjun
 * @date 2019/11/9 11:59
 * @decs read user's information in moments project,for example
 */

public class UserInfoModel extends BaseModel {
    public UserInfoModel() {
        super(API.USER_URL);
    }

}
