package com.pattern;

/**
 * 三级领导
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-11
 */
public class LeaderThree extends Handler{

    public LeaderThree() {
        super(Handler.NUM_ONE, Handler.NUM_THREE);
    }

    @Override
    protected void handleLeave(LeaveRequest leave) {
        System.out.println(leave.getName() + "请假" + leave.getNum() + "天," + leave.getContent() + "。"); System.out.println("三级领导审批：同意。");
    }
}
