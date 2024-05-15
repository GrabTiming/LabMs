package com.Lnn;

import com.Lnn.domain.entity.TermLab;
import com.Lnn.mapper.TermLabMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 管理员
 */

@SpringBootTest
public class ArrangeTest {

    //测试处理申请后实验室的占用情况
    @Autowired
    private TermLabMapper termLabMapper;
    @Test
    public void test1()
    {
        String term = "2023-2024-2";
        TermLab termLab = termLabMapper.get(term, 3);
        String state = termLab.getState();
        char[] arr = state.toCharArray();
        //查看第六周每一天的占课情况
        int week = 5;
        int idx=0;
        for(int dayNum = 0;dayNum<7;dayNum++)
        {
            for(int sectionNum=0;sectionNum<6;sectionNum++)
            {
                idx =week*7*6 + dayNum+sectionNum;
                System.out.print(arr[idx]);
            }
            System.out.println();
        }


        // 42 + 6 +1
    }

}
