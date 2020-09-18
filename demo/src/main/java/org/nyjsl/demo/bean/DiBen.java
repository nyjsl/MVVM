package org.nyjsl.demo.bean;

import org.nyjsl.demo.BR;

import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

/**
 * @author : weixing
 * @date : 2020/9/18 5:49 PM
 */
public class DiBen extends BaseObservable {

    private String data;

    @Bindable
    @Nullable
    public String getData() {
        return data;
    }

    public void setData(@Nullable String data) {
        this.data = data;
        notifyPropertyChanged(BR.dd);
        notifyPropertyChanged(BR.data);
    }
}
