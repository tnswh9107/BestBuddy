package com.hoyaok2.bestbuddy;

public class Api_House_recyclerItem {
    public String name; //상호명
    public String lineintro;    //소개
    public String houseingimg;    //이미지
    public String induty;    //업종
    public String addr1;    //주소
    public String addr2;    //상세주소
    public String mapX;    //맵x
    public String mapY;    //맵y
    public String opne;     //영업상태
    public String close;    //폐업일자
    public String roomcnt;     //예약방

    public Api_House_recyclerItem() {
    }

    public Api_House_recyclerItem(String name, String lineintro, String houseingimg, String induty, String addr1, String addr2, String mapX, String mapY) {
        this.name = name;
        this.lineintro = lineintro;
        this.houseingimg = houseingimg;
        this.induty = induty;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.mapX = mapX;
        this.mapY = mapY;
    }
}
