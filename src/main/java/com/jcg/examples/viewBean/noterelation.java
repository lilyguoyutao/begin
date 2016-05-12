package com.jcg.examples.viewBean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="note_note")
public class noterelation implements Serializable{
	@Id
	@Column(name="fromnoteid")
	private int fromnoteid;
	private int tonoteid;
	private String linktype;
	private int note_table_id;
	public noterelation(){
		
	}
	public int getFromnoteid(){
		return fromnoteid;
	}
	public int getTonoteid(){
		return tonoteid;
	}
	public String getLinktype(){
		return linktype;
	}
	public int getNote_table_id(){
		return note_table_id;
	}
	public void setFromnoteid(int fromnoteid){
		this.fromnoteid=fromnoteid;
	}
	public void setTonoteid(int tonoteid){
		this.tonoteid=tonoteid;
	}
	public void setLinktype(String linktype){
		this.linktype=linktype;
	}
	public void setNote_table_id(int note_table_id){
		this.note_table_id=note_table_id;
	}
	

}
