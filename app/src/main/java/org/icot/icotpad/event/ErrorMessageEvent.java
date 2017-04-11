package org.icot.icotpad.event;

/**
 * Created by lenovo on 2017/2/7.
 */

public class ErrorMessageEvent {
    private String msg;

    public ErrorMessageEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
