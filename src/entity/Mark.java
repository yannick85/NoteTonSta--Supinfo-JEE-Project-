package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
@Entity
public class Mark implements Serializable {

	@Id @GeneratedValue
	private Long id;
	
	@Column(nullable=false) //BDD constraints
	private int idBooster;
	
	@Column(nullable=false) //BDD constraints
	private int speakerKnowledge;
	
	@Column(nullable=false) //BDD constraints
	private int speakerTeaching;
	
	@Column(nullable=false) //BDD constraints
	private int speakerAnswers;
	
	@Column(nullable=false) //BDD constraints
	private int slideContent;
	
	@Column(nullable=false) //BDD constraints
	private int slideFormat;
	
	@Column(nullable=false) //BDD constraints
	private int slideExample;
	
	@Column(nullable=false) //BDD constraints
	private long interventionId;
	
	@Lob
	private String comment;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getSpeakerKnowledge() {
		return speakerKnowledge;
	}

	public void setSpeakerKnowledge(int speakerKnowledge) {
		this.speakerKnowledge = speakerKnowledge;
	}

	public int getSpeakerTeaching() {
		return speakerTeaching;
	}

	public void setSpeakerTeaching(int speakerTeaching) {
		this.speakerTeaching = speakerTeaching;
	}

	public int getSpeakerAnswers() {
		return speakerAnswers;
	}

	public void setSpeakerAnswers(int speakerAnswers) {
		this.speakerAnswers = speakerAnswers;
	}

	public int getSlideContent() {
		return slideContent;
	}

	public void setSlideContent(int slideContent) {
		this.slideContent = slideContent;
	}

	public int getSlideFormat() {
		return slideFormat;
	}

	public void setSlideFormat(int slideFormat) {
		this.slideFormat = slideFormat;
	}

	public int getSlideExample() {
		return slideExample;
	}

	public void setSlideExample(int slideExample) {
		this.slideExample = slideExample;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getIdBooster() {
		return idBooster;
	}

	public void setIdBooster(int idBooster) {
		this.idBooster = idBooster;
	}

	public long getInterventionId() {
		return interventionId;
	}

	public void setInterventionId(long interventionId) {
		this.interventionId = interventionId;
	}
}
