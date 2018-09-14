package leo.wesine.wesinecpyrtkey;

import java.io.Serializable;

/**
 * Created by wesine on 2018/9/12.
 */

public class SerialInfo implements Serializable {
    private String preCode;
    private String factory;
    private int year;
    private int week;
    private String type;
    private int num;

    public SerialInfo() {
    }

    public String getPreCode() {
        return preCode;
    }

    public void setPreCode(String preCode) {
        this.preCode = preCode;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "SerialInfo{" +
                "preCode='" + preCode + '\'' +
                ", factory='" + factory + '\'' +
                ", year='" + year + '\'' +
                ", week='" + week + '\'' +
                ", type='" + type + '\'' +
                ", num='" + num + '\'' +
                '}';
    }
}
