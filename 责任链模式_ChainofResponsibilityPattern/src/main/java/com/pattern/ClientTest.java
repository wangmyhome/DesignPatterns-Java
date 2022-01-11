package com.pattern;

/**
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-11
 */
public class ClientTest {
    public static void main(String[] args) {
        //请假条来一张
        LeaveRequest leave = new LeaveRequest("小花",5,"身体不适");

        //各位领导
        LeaderThree leaderThree = new LeaderThree();
        LeaderTwo leaderTwo = new LeaderTwo();
        LeaderOne leaderOne = new LeaderOne();
        leaderThree.setNextHandler(leaderTwo);// 小组长的领导是部门经理
        leaderTwo.setNextHandler(leaderOne);// 部门经理的领导是总经理
        //之所以在这里设置上级领导，是因为可以根据实际需求来更改设置，如果实战中上级领导人都是固定的，则可以移到领导实现类中。

        //提交申请
        leaderTwo.submit(leave);
    }
}
