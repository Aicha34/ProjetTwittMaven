package fmin362.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.JoinColumn;
import javax.xml.bind.ValidationException;

@Entity

@Table(name="t_user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long id;
	
	@Column(unique=true,nullable=false,length=10)
	private String login;
	
	@Column(nullable=false,length=10)
	private String password;
	
	@Column(nullable=false, length=30)
	private String firstname;
	
	@Column(nullable=false, length=30)
	private String lastname;
	
	@Column(nullable=false,length=30)
	private String email;
	
	@Column(name="date_of_birth")
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	@Transient
	private Integer age;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="t_user_message",joinColumns={@JoinColumn(name="user_fk")},
		inverseJoinColumns={@JoinColumn(name="message_fk")})
	
	private List<Message> messages;
	
	@PrePersist
	@PreUpdate
	private void validateData() throws ValidationException{
		if (firstname==null ||"".equals(firstname))
			throw new ValidationException("invalidate first name");
		if (lastname==null ||"".equals(lastname))
			throw new ValidationException("invalidate last name");
		if (login==null ||"".equals(login))
		throw new ValidationException("invalidate login");
		if (password==null ||"".equals(password))
			throw new ValidationException("invalidate password");
		
	}
	@PostLoad
	@PostPersist
	@PostUpdate
	public void calculateAge(){
		if (dateOfBirth==null){
			age=null;
			return;
		}
		Calendar birth=new GregorianCalendar();
		birth.setTime(dateOfBirth);
		Calendar now=new GregorianCalendar();
		now.setTime(new Date());
		int adjust=0;
		if(now.get(Calendar.DAY_OF_YEAR)-birth.get(Calendar.DAY_OF_YEAR)<0){
			adjust=-1;
		}
		age=now.get(Calendar.YEAR)-birth.get(Calendar.YEAR)+adjust;
	}
	public void matchPassword(String pwd) throws ValidationException{
		if(pwd==null||"".equals(pwd))
			throw new ValidationException("Invalid password ");
		if(!pwd.equals(password))
			throw new ValidationException("Passwords don't match");
	}
	public long getId() {
		return id;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public List<Message> getMessages() {
		return messages;
	}
	
	public User(String login, String password, String email, Date dateOfBirth) {
		super();
		this.login = login;
		this.password = password;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
	}
	
	
	
	
	
	
	

}
