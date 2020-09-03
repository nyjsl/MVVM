package org.nyjsl.common.entity.login;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    @NonNull
    public  String id;
    public String pictureUrl;
    public String nickName;
    public Integer sex;
    public String mobile;
    public String schoolId;
    public int active;
    public String campusId;
    public String campusName;
    public String schoolName;
    public String buildingId;
    public String buildingName;
    public String floorId;
    public String floorName;
    public String residenceId;
    public boolean isManager;
    public boolean balanceRechargeOpenStatus;
    public boolean refundOpenStatus;
    public boolean quickPayOfSchool;
    public boolean quickPayOfUser;
    public String quickPayTitle;
    public String residenceName;
    public String cusRobotUrl;
    public String problemBaseUrl;
    public String exclusiveCusUrl;
    @Embedded(prefix = "ali")
    public AliInfo alipayBinding;
    @Embedded(prefix = "wechat")
    public WechatInfo wechatUserInfo;
}
    