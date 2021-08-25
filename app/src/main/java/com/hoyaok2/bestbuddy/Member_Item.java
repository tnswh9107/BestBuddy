package com.hoyaok2.bestbuddy;

public class Member_Item {


    int num;
    String userID;
    String userPassword;
    String userName;
    String file;
    String pwc;

    public Member_Item() {
    }

    public Member_Item(int num, String userID, String userPassword, String userName, String file, String pwc) {
        this.num = num;
        this.userID = userID;
        this.userPassword = userPassword;
        this.userName = userName;
        this.file = file;
        this.pwc = pwc;
    }
}
