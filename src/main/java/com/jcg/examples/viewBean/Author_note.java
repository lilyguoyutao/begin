package com.jcg.examples.viewBean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="publish_author_note")
public class Author_note implements Serializable{
	
	@Id
	@Column(name="authorid")
	private int author_id;
	
	@Column(name="noteid")
	private int note_id;
	public int getAuthor_id(){
		return author_id;
	}
	public int getNote_id(){
		return note_id;
	}
	public void setAuthor_id(int author_id){
		this.author_id=author_id;
	}
	public void setNote_id(int note_id){
		this.note_id=note_id;
	}

}
