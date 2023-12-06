package com.human.thymeleaf.entity;

public class Profile {
	private int pid;
	private String mid;
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
	private String mname;
	private String email;
	private String imgPath;
	
	public Profile() { }
	public Profile(String mid) {
		this.mid = mid;
	}
	public Profile(int pid, String mid, String about, String company, String job, String country, String addr,
			String phone, String twitter, String facebook, String insta, String linked) {
		this.pid = pid;
		this.mid = mid;
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
	public Profile(int pid, String mid, String about, String company, String job, String country, String addr,
			String phone, String twitter, String facebook, String insta, String linked, String mname, String email,
			String imgPath) {
		this.pid = pid;
		this.mid = mid;
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
		this.mname = mname;
		this.email = email;
		this.imgPath = imgPath;
	}
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
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
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
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
	
	@Override
	public String toString() {
		return "Profile [pid=" + pid + ", mid=" + mid + ", about=" + about + ", company=" + company + ", job=" + job
				+ ", country=" + country + ", addr=" + addr + ", phone=" + phone + ", twitter=" + twitter
				+ ", facebook=" + facebook + ", insta=" + insta + ", linked=" + linked + ", mname=" + mname + ", email="
				+ email + ", imgPath=" + imgPath + "]";
	}
}
