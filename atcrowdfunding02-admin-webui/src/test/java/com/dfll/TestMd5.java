package com.dfll;

import com.atguigu.crowd.util.CrowdUtil;
import org.junit.Test;

public class TestMd5 {

    @Test
    public void testmd5(){
        String str = "1234567";

        String md5 = CrowdUtil.md5(str);

        System.out.println(md5);
    }
}
