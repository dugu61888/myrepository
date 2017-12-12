package com.apcompany.api.model.message;

import com.apcompany.api.constrant.MessagePushEnum;

public class InviteVideoMessgae {
    private int key;
    private Object value;

    public InviteVideoMessgae(){}

    public InviteVideoMessgae(MessagePushEnum messagePushEnum){
        this.key=messagePushEnum.getKey();
        this.value=messagePushEnum.getValue();
    }

    public InviteVideoMessgae(Object data){
        this.key=0;
        this.value=data;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
