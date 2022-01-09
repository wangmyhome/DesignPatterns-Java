package com.pattern.methodfactory;

import com.pattern.smplefactory.MiPhone;
import com.pattern.smplefactory.Phone;

/**
 * 小米手机工厂
 * 只能生产手机
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-07
 */
public class XiaoMiPhoneFactory implements MethodFactory {
    @Override
    public Phone makePhone() {
        return new MiPhone();
    }
}