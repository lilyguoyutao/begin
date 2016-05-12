package com.jcg.examples.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.jcg.examples.dao.CommunityDao;
import com.jcg.examples.viewBean.Comment;
import com.jcg.examples.viewBean.Community;
import com.jcg.examples.viewBean.Idea;
import com.jcg.examples.viewBean.Mygroup;
import com.jcg.examples.viewBean.Project;
import com.jcg.examples.viewBean.Projecthread;
import com.jcg.examples.viewBean.Threadcomment;
@Repository
public class CommunityDaoImpl implements CommunityDao{
	@Autowired
	private HibernateTemplate hibernateTemplate;
    
	@Override
	public void saveDao(Community community) throws SQLException{
		hibernateTemplate.save(community);
	}
	
	@Override
	public List<Community> listDao(String name) throws SQLException{
		return (List<Community>)hibernateTemplate.find("from Community where creator=?",name);
	}
	
	@Override
	public boolean checkDao(String name) throws SQLException{
		List<Community> result=(List<Community>)hibernateTemplate.find("from Community where community_name=?",name);
		if(result.size()==0)
			return false;
		else
			return true;
	}
	
	@Override
	public void deleteDao(int id) throws SQLException{
		Community community=hibernateTemplate.load(Community.class, id);
		hibernateTemplate.delete(community);
		
	}
	
	@Override
	public Community detailDao(String id) throws SQLException{
		List<Community> community=(List<Community>)hibernateTemplate.find("from Community  where community_name=?",id);
		return community.get(0);
	}
	
	@Override
	public List<String> findcombyuser(String creator) throws SQLException{
		return (List<String>)hibernateTemplate.find("select community_name from Community where creator=?", creator);
	}
	
	@Override
	public void updatecreator(String commname,String username) throws SQLException{
		hibernateTemplate.bulkUpdate("update Community set creator=? where community_name=?", username,commname);
	}
	
	@Override
	public List<Idea> listideaDao(String projectid,String thread) throws SQLException{
		
		List<Idea> list= (List<Idea>)hibernateTemplate.find("from Idea where projectid=? and threadname=?",Integer.valueOf(projectid),thread);
	    List<Idea> result=new ArrayList<Idea>();
		for(int i=0;i<list.size();i++){
			Idea ide=new Idea();
			ide.setId(list.get(i).getId());
			ide.setThreadname(list.get(i).getThreadname());
			ide.setNoteid(list.get(i).getNoteid());
			ide.setNotetitle(list.get(i).getNotetitle());
			ide.setCreatetime(list.get(i).getCreatetime());
			ide.setOffset(list.get(i).getOffset());
			ide.setProjectid(list.get(i).getProjectid());
			String st=list.get(i).getNotecontent();
			st=st.replaceAll("<.*?>", "").replaceAll("&nbsp; ", "").replaceAll("&nbsp;", "").trim();
			ide.setNotecontent(st);
			result.add(ide);
		}
		return result;
		
	}
	
	@Override
	public  void savecomment(Threadcomment comment) throws SQLException{
		hibernateTemplate.save(comment);
	}
	
	@Override
	public List<Idea> searchidea(String idea,String threadname) throws SQLException{
		List<Integer> noteid=(List<Integer>)(hibernateTemplate.find("select noteid from thread_note where threadfocus=?",threadname));
		List<Idea> list=new ArrayList<Idea>();
		for(int i=0;i<noteid.size();i++){
			List<Idea> tem=(List<Idea>)hibernateTemplate.find("from Idea where noteid=? and (notetitle like ? or notecontent like ?) ", noteid.get(i),"%"+idea+"%","%"+idea+"%");
		    if(tem.size()>0){
		    	list.add(tem.get(0));
		    }
		}
		return list;
	}
	
	@Override
	public List<Project> searchproject(String community) throws SQLException{
		return (List<Project>)hibernateTemplate.find("from Project where communityname=?",community);
	}
	
	@Override
	public List<Projecthread> searchthread(String project) throws SQLException{
		int id=Integer.valueOf(project);
		return (List<Projecthread>)hibernateTemplate.find(" from Projecthread where projectid=?",id);
	}
	
	
	@Override
	public List<String> listcommunityname() throws SQLException{
		List<String> list=(List<String>)hibernateTemplate.find("select community_name from Community");
		return list;
	}

	@Override
	public List<Comment> listcomment(String threadname) throws SQLException {
		// TODO Auto-generated method stub
		return (List<Comment>)hibernateTemplate.find("from Comment where fromw=? order by time DESC",threadname);
	}

	

	@Override
	public List<Integer> getNoteBythreadname(String thread) {
		// TODO Auto-generated method stub
		return (List<Integer>)(hibernateTemplate.find("select noteid from thread_note where threadfocus=?",thread));
	}

	@Override
	public void savegroup(Mygroup mygroup) {
		// TODO Auto-generated method stub
		hibernateTemplate.save(mygroup);
	}

	@Override
	public List<Mygroup> listmygroup(String userid) {
		// TODO Auto-generated method stub
		return (List<Mygroup>) hibernateTemplate.find("from Mygroup where group_builder=?",userid);
	}

	@Override
	public List<Integer> getgroupbyuserid(String userid) {
		// TODO Auto-generated method stub
		return (List<Integer>)hibernateTemplate.find("select group_id from Stgroup where student_id=?",Integer.valueOf(userid));
	}

	@Override
	public Mygroup getgroupbygroupid(Integer integer) {
		// TODO Auto-generated method stub
		List<Mygroup> list=(List<Mygroup>)hibernateTemplate.find("from Mygroup where group_id=?",integer);
		return list.get(0);
	}

	@Override
	public void savenotcomment(Comment comment) {
		// TODO Auto-generated method stub
		hibernateTemplate.save(comment);
	}

	@Override
	public List<Threadcomment> listthreadcomment(String projectid, String threadname) {
		// TODO Auto-generated method stub
		return (List<Threadcomment>)hibernateTemplate.find("from Threadcomment where projectid=? and threadname=? order by dtime DESC",Integer.valueOf(projectid),threadname);
	}
	
}
