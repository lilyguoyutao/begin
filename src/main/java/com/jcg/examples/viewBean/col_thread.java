package com.jcg.examples.viewBean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="col_thread")
public class col_thread implements Serializable{
	
	@Id
	@Column(name="collectid")
	private int collectid;
	
	private int projectid;
	
	
	private String thread;
	public int getCollectid(){
		return collectid;
	}
	public String getThread(){
		return thread;
	}
	public int getProjectid(){
		return projectid;
	}
	public void setCollectid(int collectid){
		this.collectid=collectid;
	}
	public void setThread(String thread){
		this.thread=thread;
	}
	public void setProjectid(int projectid){
		this.projectid=projectid;
	}

}
