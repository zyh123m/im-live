package com.example.common.utils;

public class PhoneNumberUtils {

    public static boolean isMobile(String phoneNum) {
        String regex = "^1[3-9]\\d{9}$";
        if (phoneNum.matches(regex)) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        String phoneNum1 = "13512345678";
        String phoneNum2 = "123";

        System.out.println(isMobile(phoneNum1)); // true
        System.out.println(isMobile(phoneNum2)); // false
    }

}