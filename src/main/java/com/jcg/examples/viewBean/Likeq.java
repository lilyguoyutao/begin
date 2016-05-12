package com.jcg.examples.viewBean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Wlike")
public class Likeq implements Serializable{
	
	@Id
	@GeneratedValue
    @Column(name = "id")
    private int id;
	
	private int userid;
	private int projectid;
	 
	private String thread;
	
	public int getId(){
		return id;
	}
	
	
	public int getUserid(){
		return userid;
	}
	
	public int getProjectid(){
		return projectid;
	}
	
	public String getThread(){
		return thread;
	}
	
	public void setId(int id){
		this.id=id;
	}
	/*public void setUsername(String username){
		this.username=username;
	}
	public void setPassword(String password){
		this.password=password;
	}*/
	public void setUserid(int userid)
	{this.userid=userid;}
	public void setThread(String thread){
		this.thread=thread;
	}
	
	public void setProjectid(int projectid){
		this.projectid=projectid;
	}

}
