package com.javedrpi.xiaourl.service;

import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class Base10From62 implements UniqueRandomService{
    @Override
    public String getUniqueRandom(long longNumber) {
        System.out.println("-- Base10From62 --");
        String number = longNumber + "";
        BigInteger value = BigInteger.ZERO;
        for (char c : number.toCharArray()){
            value = value.multiply(BigInteger.valueOf(62));
            if ('0' <= c && c <= '9'){
                value = value.add(BigInteger.valueOf(c - '0'));
            }
            if ('a' <= c && c <= 'z'){
                value = value.add(BigInteger.valueOf(c - 'a' + 10));
            }
            if ('A' <= c && c <= 'Z'){
                value = value.add(BigInteger.valueOf(c - 'A' + 36));
            }
        }
        return value.toString();
    }
}
