package com.tinc.web.entity;

public class MemberRole {
	private String memberId;
	private String roleId;
	
	public MemberRole() {
		// TODO Auto-generated constructor stub
	}

	public MemberRole(String memberId, String roleId) {
		this.memberId = memberId;
		this.roleId = roleId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "MemberRole [memberId=" + memberId + ", roleId=" + roleId + "]";
	}
	
	

}
