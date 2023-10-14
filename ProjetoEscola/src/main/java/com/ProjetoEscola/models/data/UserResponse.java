package com.ProjetoEscola.models.data;

public class UserResponse {

	private Long id;
	private String Nome;
	private String userName;
	private String password;
	private String rePassword;
	private boolean active;
	private String email;
	private String photo;
	private String contentType;
	
	
	
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoto() {
		return photo;
	}
	
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getContentType() {
		return contentType;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	@Override
	public String toString() {
		return "UserResponse [id=" + id + ", Nome:" + Nome + ", userName=" + userName + ", password=" + password + ", rePassword="
				+ rePassword + ", active=" + active + ", email=" + email + ", photo=" + photo + ", contentType="
				+ contentType + ", id=" + getId() + "]";
	}
	
}
