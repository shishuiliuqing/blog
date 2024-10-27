package com.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private static Integer[] LV = {0, 50, 200, 1500, 4500, 10800, 28800};

    private Integer id;
    private String username;
    private Integer lv;
    private Integer experience;
    private String profilePicture;
    private String signature;

    public void setLv() {
        int i;
        for (i = LV.length - 1; experience < LV[i]; i--) ;
        lv = i;
    }

    public void setSignature() {
        if (Strings.isEmpty(signature))
            signature = "这个用户很懒，什么也没写";
    }
}
