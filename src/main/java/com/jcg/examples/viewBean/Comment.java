package com.jcg.examples.viewBean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="comment")
public class Comment implements Serializable{
	
	
	 @Id
	 @GeneratedValue
     @Column(name = "me_id")
	private int me_id;
	private int noteid; 
	private int projectid;
	private String threadname;
	 
	private String me_text;
	
	private String time;
	
	
	
	
	
	private String author;
	
	
	
	public Comment(){}
	 public int getId(){
	  		return me_id;
	  		
	  	}
	     public void setId(int me_id){
	    	  this.me_id=me_id;
	      }
	      public String getMe_text(){
	    		return me_text;
	    		
	    	}
	      public int getProjectid(){
	    	  return projectid;
	      }
	      public void setProjectid(int projectid){
	    	  this.projectid=projectid;
	      }
	      public String getThreadname(){
	    	  return threadname;
	      }
	      public void setThreadname(String threadname){
	    	  this.threadname=threadname;
	      }
	     public void setMe_text(String me_text){
	      	  this.me_text=me_text;
	        }
	     public String getTime(){
	      		return time;
	      		
	      	}
	     public void setTime(String createtime){
	        	  this.time=createtime;
	          }
	     public int getNoteid(){
	        		return noteid;
	        		
	        	}
	     public void setNoteid(int noteid){
	          	  this.noteid=noteid;
	            }
	     public String getAuthor(){
	      	  return author;
	        }
	        
	                 
	         public void setAuthor(String author){
	       	  this.author=author;
	         }
	         

}
