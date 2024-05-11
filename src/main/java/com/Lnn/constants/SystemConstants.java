package com.Lnn.constants;

import java.util.HashMap;
import java.util.stream.Stream;

public class SystemConstants {

    private SystemConstants(){};


    public static final int  LAB_STATE_LENGTH =  850;

    //星期几 转化为 数字
    public static final HashMap<String,Integer> DAY_TO_NUM = new HashMap<String,Integer>(){
        {
            put("一",0);
            put("二",1);
            put("三",2);
            put("四",3);
            put("五",4);
            put("六",5);
            put("日",6);
        }
    };

    public static final HashMap<String,Integer> SECTION_NUM = new HashMap<String,Integer>(){

        {
            put("1-2",0);
            put("3-5",1);
            put("6-7",2);
            put("8-9",3);
            put("10-12",4);
            put("13-15",5);
        }
    };

    //已处理
    public static final int PROCESSED = 1;

    //未处理
    public static final int NOT_PROCESSED = 0;




}
