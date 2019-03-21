package com.test.workbean;

public class SysInfo {
    private static byte sort;
    private static int sid;
    private static int seq=0;
    private static int ack=0;
    private static int link;
    private static String username;
    private static String passworld;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        SysInfo.username = username;
    }

    public static String getPassworld() {
        return passworld;
    }

    public static void setPassworld(String passworld) {
        SysInfo.passworld = passworld;
    }

    private SysInfo() {
    }

    public static byte getSort() {
        return sort;
    }

    public static void setSort(byte sort) {
        SysInfo.sort = sort;
    }

    public static int getSid() {
        return sid;
    }

    public static void setSid(int sid) {
        SysInfo.sid = sid;
    }

    public static int getSeq() {
        return seq;
    }

    public static void setSeq(int seq) {
        SysInfo.seq = seq;
    }

    public static int getAck() {
        return ack;
    }

    public static void setAck(int ack) {
        SysInfo.ack = ack;
    }

    public static int getLink() {
        return link;
    }

    public static void setLink(int link) {
        SysInfo.link = link;
    }

}
