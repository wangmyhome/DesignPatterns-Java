package com.parttern.classadapter;

/**
 * 适配器
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-10
 */
public class VoltageAdapter  extends Voltage220V implements IVoltage5V {

    @Override
    public int output5V() {
        // TODO Auto-generated method stub
        //获取到220V电压
        int srcV = output220V();
        int dstV = srcV / 44 ; //转成 5v
        return dstV;
    }

}