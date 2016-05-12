package com.jcg.examples.viewBean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="threadcomment")
public class Threadcomment implements Serializable{
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	private int projectid;
	private String threadname;
	private String dtime;
	private String content;
	private int userid;
	private String author;
	public int getId(){
		return id;
	}
	public int getProject(){
		return projectid;
	}
	public String getThreadname(){
		return threadname;
	}
	
	public String getContent(){
		return content;
	}
	public int getUserid(){
		return userid;
	}
	public String getDtime(){
		return dtime;
	}
	public String getAuthor(){
		return author;
	}
	public void setId(int id){
		this.id=id;
	}
	public void setProjectid(int projectid){
		this.projectid=projectid;
	}
	public void setThreadname(String threadname){
		this.threadname=threadname;
	}
	public void setDtime(String dtime){
		this.dtime=dtime;
	}
	public void setContent(String content){
		this.content=content;
	}
	public void setUserid(int userid){
		this.userid=userid;
	}
	public void setAuthor(String author){
		this.author=author;
	}

}
