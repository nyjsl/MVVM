package org.nyjsl.demo.bean;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author : weixing
 * @date : 2020/9/18 4:04 PM
 */
public class DemoBean {

    private int index;
    private int age;
    private boolean large;

    public boolean isLarge() {
        return large;
    }

    public void setLarge(boolean large) {
        this.large = large;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    private String name;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
