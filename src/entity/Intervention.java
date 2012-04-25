package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
@Entity
public class Intervention implements Serializable {

	@Id @GeneratedValue
	private Long id;
	
	@Column(unique=true, nullable=false) //BDD constraints
	private String subject;
	
	@Column(nullable=false)
	@Lob
	private String description;
	
	@Column(nullable=false) //BDD constraints
	private Date beginDate;
	
	@Column(nullable=false) //BDD constraints
	private Date endDate;
	
	@Column(nullable=false) //BDD constraints
	private Long campus;
	
	@Column(nullable=false) //BDD constraints
	private Long speaker;
	
	@Transient 
	private int status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCampus() {
		return campus;
	}

	public void setCampus(Long campus) {
		this.campus = campus;
	}

	public Long getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Long speakerId) {
		this.speaker = speakerId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
