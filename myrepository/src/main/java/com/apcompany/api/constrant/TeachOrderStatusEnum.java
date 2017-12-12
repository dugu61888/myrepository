package com.apcompany.api.constrant;

public enum TeachOrderStatusEnum {
	
	
	PAY_FINISH(1,"已支付"),
	COMMENT_FINISH(0,"未支付");
	
	private final int key;
    private final String value;

    TeachOrderStatusEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

	public int getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	
	
    

}
