package com.hoyaok2.bestbuddy;

public class Pic_Item {

    int no;
    String title;
    String subtitle;
    String file;
    String usernicname;
    String userprofile;
    int favor;

    public Pic_Item() {
    }

    public Pic_Item(int no, String title, String subtitle, String file, String usernicname, String userprofile, int favor) {
        this.no = no;
        this.title = title;
        this.subtitle = subtitle;
        this.file = file;
        this.usernicname = usernicname;
        this.userprofile = userprofile;
        this.favor = favor;
    }
}
