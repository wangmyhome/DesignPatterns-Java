package com.pattern.methodfactory;

import com.pattern.smplefactory.IPhone;
import com.pattern.smplefactory.Phone;

/**
 * 苹果手机工厂
 * 只能生产手机
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-07
 */
public class ApplePhoneFactory implements MethodFactory {
    @Override
    public Phone makePhone() {
        return new IPhone();
    }
}