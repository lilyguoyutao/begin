package com.jcg.examples.dao;

import java.sql.SQLException;
import java.util.List;

import com.jcg.examples.viewBean.Comment;
import com.jcg.examples.viewBean.Community;
import com.jcg.examples.viewBean.Idea;
import com.jcg.examples.viewBean.Mygroup;
import com.jcg.examples.viewBean.Project;
import com.jcg.examples.viewBean.Projecthread;
import com.jcg.examples.viewBean.Threadcomment;

public interface CommunityDao {
public void saveDao(Community community) throws SQLException;
public List<Community> listDao(String name) throws SQLException;
public boolean checkDao(String name) throws SQLException;
public void deleteDao(int id) throws SQLException;
public Community detailDao(String id) throws SQLException;
public List<String> findcombyuser(String creator) throws SQLException;
public void updatecreator(String commname,String username) throws SQLException;
public List<Idea> listideaDao(String projectid,String thread) throws SQLException;
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
public void savenotcomment(Comment comment);
public List<Threadcomment> listthreadcomment(String projectid, String threadname);
}
