package com.jcg.examples.viewBean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="curriculum_areas")
public class Note_note implements Serializable{
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	private String name;
	private int parent_id;
	private String reference;
	public int getId(){
		return id;
	}
	public String getName(){
		return name;
	}
	public int getParent_id(){
		return parent_id;
	}
	public String getReference(){
		return reference;
	}
	public void setId(int id){
		this.id=id;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setParent_id(int parent_id){
		this.parent_id=parent_id;
	}
	public void setReference(String reference){
		this.reference=reference;
	}

}
