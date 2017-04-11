package org.icot.icotpad.event;

/**
 * Created by lenovo on 2017/2/7.
 */

public class UnLoginEvent {
    private int type;

    public UnLoginEvent(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
