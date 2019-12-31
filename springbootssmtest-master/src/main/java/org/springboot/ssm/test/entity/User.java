package org.springboot.ssm.test.entity;



import lombok.Data;

/**
 * @author michengxin
 * @description
 * @date 2019/12/27/027
 */
@Data
public class User {
    String id;
    String name; //名字
    String workNum;//工号
    String telphone;//手机号码
    int eightType;//8人游戏，0未抽中 1.已抽中
    int gameFlag; //是否游戏过 1.是 0.否
    int lotteryDrawFlag;//是否抽奖过 1.是 0.否
    int loginFlag;//是否登陆过 1.是 0.否
    int firstDrawFlag;//是否抽中特等奖 1.是 0.否 1
    int twoDrawFlag;//是否抽中一等奖 1.是 0.否  5
    int threeDrawFlag;//是否抽中二等奖 1.是 0.否 10
    int fourDrawFlag;//是否抽中三等奖 1.是 0.否  15
}
