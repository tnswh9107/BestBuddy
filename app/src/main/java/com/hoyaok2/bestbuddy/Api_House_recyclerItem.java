package com.hoyaok2.bestbuddy;

public class Api_House_recyclerItem {
    public String beachAddr; //지역주소
    public String beachNm;    //해수욕장 명
    public String beachPhone1;    //해수욕장 번호
    public String detailInfo;    //해수욕장 설명
    public String homepage;    //해수욕장 홈페이지
    public String imagePath;    //이미지 사진
    public String mapX;    //맵x
    public String mapY;    //맵y
    public String openDate;      //해수욕장 오픈일
    public String traffic;     //오는방법

    public Api_House_recyclerItem() {

    }

    public Api_House_recyclerItem(String beachAddr, String beachNm, String beachPhone1, String detailInfo, String homepage, String imagePath, String mapX, String mapY, String openDate, String traffic) {
        this.beachAddr = beachAddr;
        this.beachNm = beachNm;
        this.beachPhone1 = beachPhone1;
        this.detailInfo = detailInfo;
        this.homepage = homepage;
        this.imagePath = imagePath;
        this.mapX = mapX;
        this.mapY = mapY;
        this.openDate = openDate;
        this.traffic = traffic;
    }
// TEST
//    public String name; //상호명
//    public String lineintro;    //소개
//    public int houseingimg;    //이미지 int -> string으로 바꾸고 런 해야한다. int text중
//    public Api_House_recyclerItem() {
//    }
//
//    public Api_House_recyclerItem(String name, String lineintro, int houseingimg) {
//        this.name = name;
//        this.lineintro = lineintro;
//        this.houseingimg = houseingimg;
//    }
}
