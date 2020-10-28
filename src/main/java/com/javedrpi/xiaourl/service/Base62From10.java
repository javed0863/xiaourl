package com.javedrpi.xiaourl.service;

import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class Base62From10 implements UniqueRandomService{

    private static final char[] corpus   = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    @Override
    public String getUniqueRandom(final long seed) {
        System.out.println("-- Base10From62 --");

        String number = seed + "";
        char[] buf = new char[number.length()];
        int charPos = number.length() - 1;
        BigInteger bigIntegerNumber = new BigInteger(number);
        BigInteger radix = BigInteger.valueOf(62);

        while (bigIntegerNumber.compareTo(radix) >= 0){
            buf[charPos--] = corpus[bigIntegerNumber.mod(radix).intValue()];
            bigIntegerNumber = bigIntegerNumber.divide(radix);
        }
        buf[charPos] = corpus[bigIntegerNumber.intValue()];
        return new String(buf, charPos, (number.length() - charPos));
    }
}
