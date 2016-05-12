package com.jcg.examples.service;

import java.sql.SQLException;
import java.util.List;

import com.jcg.examples.viewBean.Comment;
import com.jcg.examples.viewBean.Community;
import com.jcg.examples.viewBean.Idea;
import com.jcg.examples.viewBean.Mygroup;
import com.jcg.examples.viewBean.Project;
import com.jcg.examples.viewBean.Projecthread;
import com.jcg.examples.viewBean.Threadcomment;

public interface CommunityService {
     public void saveservice(Community community) throws SQLException;
     public List<Community> listservice(String username) throws SQLException;
     public boolean checkCommunity(String communityname) throws SQLException;
     public void deleteservice(int id) throws SQLException;
     public Community detailservice(String id) throws SQLException;
     public List<String> findcommunitybyusername(String username) throws SQLException;
     public void updatecreator(String comname,String username) throws SQLException;
     public List<Idea> listidea(String projectid,String thread) throws SQLException;
     public void savecomment(Threadcomment comment) throws SQLException;
     public List<Idea> searchidea(String idea,String thread) throws SQLException;
     public List<Project> searchproject(String community) throws SQLException;
     public List<Projecthread> searchthread(String project) throws SQLException;
     public List<String> listcommunityname() throws SQLException;
	public List<Comment> listcomment(String threadname) throws SQLException;
	public List<Integer> getNoteBythreadname(String thread);
	public void savegroup(Mygroup mygroup);
	public List<Mygroup> listmygroup(String userid);
	public List<Integer> getgroupbyuserid(String userid);
	public Mygroup getgroupbygroupid(Integer integer);
	public void savenotecomment(Comment comment);
	public List<Threadcomment> listthreadcomment(String projectid, String threadname);
	 
}
