package com.enums;

public enum Avatar {
	
	AVATAR1(1),AVATAR2(2),AVATAR3(3),AVATAR4(4);
	
	Avatar(int num){
		this.num = num;
	}
	
	private int num;
	
	public int getNum(){
		return this.num;
	}
	
	public static Avatar getAvatar(int num){
		if(num == 1)
			return Avatar.AVATAR1;
		else if(num == 2)
			return Avatar.AVATAR2;
		else if(num == 3)
			return AVATAR3;
		else
			return Avatar.AVATAR4;
	}
}
