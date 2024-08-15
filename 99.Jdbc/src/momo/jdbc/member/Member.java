package momo.jdbc.member;

import java.util.Date;

public class Member {
	
	private String id;
	private String password;
	private Date regdate; //가입일자 
	
	
	public Member() {}
			
	public Member(String id, String password) {
		this.id = id;
		this.password = password;
	}

	public Member(String id, String password, Date regdate) {
		this.id = id;
		this.password = password;
		this.regdate = regdate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", password=" + password + ", regdate=" + regdate + "]";
	}
				
}

/*
CREATE TABLE member (
    id VARCHAR2(50) PRIMARY KEY,
    password VARCHAR2(100) NOT NULL,
    regdate DATE DEFAULT SYSDATE
);
*/