package com.itaoniu.testserver.view.webserver.reset.v1.users.params;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class UpdateUserinfoParams {
	
	@NotBlank(message="头像不能为空")
	private String image;
	
	@NotBlank(message="昵称不能为空")
	private String nickname;
	
	@NotEmpty(message="签名不能为空")
	private String signature;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	
}
