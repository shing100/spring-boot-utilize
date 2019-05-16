package com.kingname.springbootautoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

//@ConfigurationProperties("holoman")
// 2.1 버전 이후로 빈 오버라이딩이 막힘
public class HolomanProperties {

    private String name;
    private int howLong;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHowLong() {
        return howLong;
    }

    public void setHowLong(int howLong) {
        this.howLong = howLong;
    }
}
