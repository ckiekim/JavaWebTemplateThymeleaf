package com.human.thymeleaf.entity;

public class UserProfile {
	private int pid;
	private int suid;
	private String about;
	private String company;
	private String job;
	private String country;
	private String addr;
	private String phone;
	private String twitter;
	private String facebook;
	private String insta;
	private String linked;
	private String nickname;
	private String email;
	private String imgPath;
	
	public UserProfile() { }
	public UserProfile(int suid) {
		this.suid = suid;
	}
	public UserProfile(int pid, int suid, String about, String company, String job, String country, String addr,
			String phone, String twitter, String facebook, String insta, String linked) {
		this.pid = pid;
		this.suid = suid;
		this.about = about;
		this.company = company;
		this.job = job;
		this.country = country;
		this.addr = addr;
		this.phone = phone;
		this.twitter = twitter;
		this.facebook = facebook;
		this.insta = insta;
		this.linked = linked;
	}
	public UserProfile(int pid, int suid, String about, String company, String job, String country, String addr,
			String phone, String twitter, String facebook, String insta, String linked, String nickname, String email,
			String imgPath) {
		this.pid = pid;
		this.suid = suid;
		this.about = about;
		this.company = company;
		this.job = job;
		this.country = country;
		this.addr = addr;
		this.phone = phone;
		this.twitter = twitter;
		this.facebook = facebook;
		this.insta = insta;
		this.linked = linked;
		this.nickname = nickname;
		this.email = email;
		this.imgPath = imgPath;
	}
	
	@Override
	public String toString() {
		return "UserProfile [pid=" + pid + ", suid=" + suid + ", about=" + about + ", company=" + company + ", job="
				+ job + ", country=" + country + ", addr=" + addr + ", phone=" + phone + ", twitter=" + twitter
				+ ", facebook=" + facebook + ", insta=" + insta + ", linked=" + linked + ", nickname=" + nickname
				+ ", email=" + email + ", imgPath=" + imgPath + "]";
	}
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getSuid() {
		return suid;
	}
	public void setSuid(int suid) {
		this.suid = suid;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	public String getInsta() {
		return insta;
	}
	public void setInsta(String insta) {
		this.insta = insta;
	}
	public String getLinked() {
		return linked;
	}
	public void setLinked(String linked) {
		this.linked = linked;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
}
