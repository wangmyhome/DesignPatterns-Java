package com.pattern.smplefactory;

/**
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-07
 */
public class PhoneFactory {
    /**
     *
     * @param phoneType
     * @return
     */
    public Phone makePhone(String phoneType){
        if(phoneType.equalsIgnoreCase("MiPhone")){
            return new MiPhone();
        }else if (phoneType.equalsIgnoreCase("IPhone")){
            return new IPhone();
        }
        return null;
    }
}

