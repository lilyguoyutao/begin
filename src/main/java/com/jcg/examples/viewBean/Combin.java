package com.jcg.examples.viewBean;

public class Combin {
     private String threadname;
     private int projectid;
     private String projectname;
     private String communityname;
     public String getThreadname(){
    	 return threadname;
     }
     public String getProjectname(){
    	 return projectname;
     }
     public int getProjectid(){
    	 return projectid;
     }
     public String getCommunityname(){
    	 return communityname;
     }
     public void setThreadname(String threadname){
    	 this.threadname=threadname;
     }
     public void setProjectname(String projectname){
    	 this.projectname=projectname;
     }
     public void setProjectid(int projectid){
    	 this.projectid=projectid;
     }
     public void setCommunityname(String communityname){
    	 this.communityname=communityname;
     }
     
}
