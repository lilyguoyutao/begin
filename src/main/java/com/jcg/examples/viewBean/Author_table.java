package com.jcg.examples.viewBean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="publish_author_table")
public class Author_table implements Serializable{
	
	@Id
	@GeneratedValue
	private int authorid;
	private String firstname;
	private String lastname;
	private String username;
	private String type;
	private String password;
	private String community;
	public  int getAuthorid(){
		return authorid;
	}
	public String getFirstname(){
		return firstname;
	}
	public String getLastname(){
		return lastname;
	}
	public String getUsername(){
		return username;
	}
	public String getType(){
		return type;
	}
	public String getPassword(){
		return password;
	}
	
	public void setAuthorid(int authorid){
		this.authorid=authorid;
	}
	public void setFirstname(String firstname){
		this.firstname=firstname;
	}
	public void setLastname(String lastname){
		this.lastname=lastname;
	}
	public void setUsername(String username){
		this.username=username;
	}
	public void setType(String type){
		this.type=type;
	}
	public void setCommunity(String community){
		this.community=community;
	}

}
