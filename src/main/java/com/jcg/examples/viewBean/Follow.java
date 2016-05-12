package com.jcg.examples.viewBean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Follow")
public class Follow implements Serializable{
	
	@Id
	@GeneratedValue
    @Column(name = "id")
	private int id;
	private int userid;
	private int projectid;
	private String  followname;
	private String isthread;
	
	public int getId(){
		return id;
	}
    public int getUserid(){
    	return userid;
    }
    public int getProjectid(){
    	return projectid;
    }
    
    public String getFollowname(){
    	return followname;
    }
    public String getIsthread(){
    	return isthread;
    }
    public void setId(int id){
    	this.id=id;
    }
    public void setUserid(int userid){
    	this.userid=userid;
    }
    
    public void setProjectid(int projectid){
    	this.projectid=projectid;
    }
    
    public void setFollowname(String followname){
    	this.followname=followname;
    }
    public void setIsthread(String isthread){
    	this.isthread=isthread;
    }
}
