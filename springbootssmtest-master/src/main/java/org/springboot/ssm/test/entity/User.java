package org.springboot.ssm.test.entity;



import lombok.Data;

/**
 * @author michengxin
 * @description
 * @date 2019/12/27/027
 */
@Data
public class User {
    public String id;
    public String name; //名字
    public String nickName; //昵称
    public String headSculpture; //头像
    public String workNum;//工号
    public int gameFlag; //是否游戏过 1.是 0.否
    public int lotteryDrawFlag;//是否抽奖过 1.是 0.否
    public int joinFlag; //是否参加过该活动 1.是  0.否
    public int firstDrawFlag;
    public int twoDrawFlag;
    public int threeDrawFlag;
    public int fourDrawFlag;
}
