package com.ProjetoEscola.models.model;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.ProjetoEscola.models.pk.PrimaryKey;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

@MappedSuperclass
public abstract class User extends PrimaryKey {
	
	static final long serialVersionUID = -4292510511982171161L;
 
 
	private String userName;
	private String email;
	private String password;
	private String rePassword;
	private String photo;
	private String contentType;
	private Integer loginFailed;
	private Date passwordExpireDate;
	
	private boolean active;
	
	public User() {
	}

	public User(String userName, String email, String password, String rePassword, String photo, String contentType,
			Integer loginFailed, Date passwordExpireDate, boolean active) {
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.rePassword = rePassword;
		this.photo = photo;
		this.contentType = contentType;
		this.loginFailed = loginFailed;
		this.passwordExpireDate = passwordExpireDate;
		this.active = active;
	}



	@Column(name="USERNAME", length=100, nullable=false)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name="EMAIL", length=100, nullable=false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="PASSWORD", length=100, nullable=false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Transient //Só vai funcionar na memória e não vai salvar no banco
	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	@Column(name="PHOTO", length=100, nullable=true)
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Column(name="CONTENT_TYPE", length=50, nullable=true)
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Column(name="FAILED_LOGIN_COUNT", nullable=true)
	public Integer getLoginFailed() {
		return loginFailed;
	}

	public void setLoginFailed(Integer loginFailed) {
		this.loginFailed = loginFailed;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy") // Automaticamente já salva em formato de servidor no SQL, é apenas para mostrar pro usuário
	@Temporal(TemporalType.DATE) // Gera automaticamente com a data do servidor
	@Column(name="PASSWORD_EXPIRE_DATE", columnDefinition = "DATE", nullable=true)
	public Date getPasswordExpireDate() {
		return passwordExpireDate;
	}

	public void setPasswordExpireDate(Date passwordExpireDate) {
		this.passwordExpireDate = passwordExpireDate;
	}

	@Column(name="ACTIVE", length=1, nullable=false)
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "User [id=" + this.getId() + ", userName=" + userName + ", email=" + email + ", active=" + active + "]";
	}
}
