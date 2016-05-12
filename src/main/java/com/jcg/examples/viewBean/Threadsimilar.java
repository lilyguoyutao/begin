package com.jcg.examples.viewBean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Thread_similar")
public class Threadsimilar implements Serializable{
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	private int projectidone;
	private int projectidtwo;
	private String threadone;
	private String threadtwo;
	private double score;
	public int getId(){
		return id;
	}
	public int getProjectidone(){
		return projectidone;
	}
	public int getProjectidtwo(){
		return projectidtwo;
	}
	public String getThreadone(){
		return threadone;
	}
	public String getThreadtwo(){
		return threadtwo;
	}
	public double getScore(){
		return score;
	}
	public void setId(int id){
		this.id=id;
	}
	public void setProjectidone(int projectidone){
		this.projectidone=projectidone;
	}
	public void setProjectidtwo(int projectidtwo){
		this.projectidtwo=projectidtwo;
	}
	public void setThreadone(String threadone){
		this.threadone=threadone;
	}
	public void setThreadtwo(String threadtwo){
		this.threadtwo=threadtwo;
	}
	public void setScore(double score){
		this.score=score;
	}

}
