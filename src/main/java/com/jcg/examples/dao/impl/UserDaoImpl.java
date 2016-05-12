package com.jcg.examples.dao.impl;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.jcg.examples.dao.UserDao;
import com.jcg.examples.function.Compare;
import com.jcg.examples.viewBean.Project;
import com.jcg.examples.viewBean.Projecthread;
import com.jcg.examples.viewBean.Stgroup;
import com.jcg.examples.viewBean.Student;
import com.jcg.examples.viewBean.Threadsimilar;
import com.jcg.examples.viewBean.col_thread;
import com.jcg.examples.viewBean.st_com;
import com.jcg.examples.viewBean.usermore;
import com.jcg.examples.viewBean.Followupda;
import com.jcg.examples.viewBean.Idea;
import com.jcg.examples.viewBean.Adopt;
import com.jcg.examples.viewBean.Collect;
import com.jcg.examples.viewBean.Comment;
import com.jcg.examples.viewBean.Follow;
import com.jcg.examples.viewBean.Likeq;
import com.jcg.examples.viewBean.Myadopt;
import com.jcg.examples.viewBean.Mycollect;
import com.jcg.examples.viewBean.Mycollection;
import com.jcg.examples.viewBean.Note_note;


/**
 * @author CENTAUR
 */
@Repository
public class UserDaoImpl implements UserDao
{
        @Autowired
		private HibernateTemplate hibernateTemplate;

		//public DataSource getDataSource()
		//{
		//		return this.dataSource;
		//}

		

		
		
		@Override
		public  int checkUserpass(String username,String password,String type)
		{
			String hql="from Student u where u.username=? and password=? and type=?";
			//Object value=new Object[]{username};
			List<Student> loginlist=(List<Student>)hibernateTemplate.find(hql,username,password,type);
			
			if(loginlist.size()!=0)
				return loginlist.get(0).getSt_id();
			else
				return -1;
			
			
		}
		
		@Override
		public void saveStuDao(Student student) throws SQLException{
			hibernateTemplate.save(student);
		}
		
		@Override
		public Student listuserDao(int id) throws SQLException{
		   
		   
		   List<Student> student= (List<Student>)hibernateTemplate.find("from Student where st_id=?",id);
		   return student.get(0);
		    
		}
		
		
		@Override
		public Student userfileDao(String name,String password,String type) throws SQLException{
		  List<Student> student=(List<Student>)hibernateTemplate.find("from Student where username=? and password=? and type=?", name,password,type);
		  return student.get(0);
		}
		
		@Override
		public List<String> findcombyuser(String username) throws SQLException
		{
			return (List<String>)hibernateTemplate.find("select distinct(community) from Student where username=?",username);
		}
		
		@Override
		public void changeDao(String username,String old,String password) throws SQLException
		{   
			hibernateTemplate.bulkUpdate("update Student set password=? where username=? and password=?",password,username,old);
		}
		
		@Override
		public boolean check(int id,String community) throws SQLException{
			List<st_com> student=(List<st_com>)hibernateTemplate.find("from st_com where userid=? and community=?",id,community);
			if(student.size()==0)
				return true;
			else
				return false;
		}
		
		@Override
		public void editDao(Student student) throws SQLException{
			hibernateTemplate.update(student);
		}
		
		@Override
		public List<Project> searchprojectDao(String html) throws SQLException{
			return (List<Project>)hibernateTemplate.find(html);
		}
		
		@Override
		public List<Projecthread> searchthread(String html) throws SQLException{
			return (List<Projecthread>)hibernateTemplate.find(html);
		}
		
		@Override
		public List<String> checklike(int userid,String projectid,String thread) throws SQLException{
			System.out.println("result is" +projectid);
			System.out.println(userid);
			System.out.println(thread);
			List<Likeq> likeq1=(List<Likeq>)hibernateTemplate.find("from Likeq where userid=? and projectid=? and thread=?", userid, Integer.valueOf(projectid.trim()),thread);
			List<Likeq> likeq2=(List<Likeq>)hibernateTemplate.find("from Likeq where projectid=? and thread=?", Integer.valueOf(projectid.trim()),thread);
			List<String> list=new ArrayList<String>();
			System.out.println("result is "+likeq1.size());
			list.add(String.valueOf(likeq1.size()));
			list.add(String.valueOf(likeq2.size()));
			return list;
			
		}
		@Override
		  public void insertlik(Likeq lik) throws SQLException{
			hibernateTemplate.save(lik);
		}
		
		@Override
	        public void deletelik(Likeq lik) throws SQLException{
			List<Likeq> likes=(List<Likeq>)hibernateTemplate.find("from Likeq where userid=? and projectid=? and thread=?",lik.getUserid(),lik.getProjectid(),lik.getThread());
			int id=likes.get(0).getId();
			lik.setId(id);
					hibernateTemplate.delete(lik);
		}
		
		@Override
		 public List<Likeq> listlike(String projectid,String threadname) throws SQLException{
			return (List<Likeq>)hibernateTemplate.find("from Likeq where projectid=? and thread=?",Integer.valueOf(projectid),threadname);
		}
        
		@Override
		public void insertfollow(Follow follow) throws SQLException{
			hibernateTemplate.save(follow);
		}
		
		@Override
		public String checkfollow(int userid,String projectid,String thread) throws SQLException{
			List<Follow> list=(List<Follow>)hibernateTemplate.find("from Follow where userid=? and projectid=? and followname=?",userid,Integer.valueOf(projectid),thread);
			if(list.size()==0)
				return "true";
			else 
				return "false";
		}
		
		@Override
		public void  insertadopt(Adopt adopt) throws SQLException{
			  hibernateTemplate.save(adopt);
		}
		
		@Override
        public String  checkadopt(int userid,String projectid,String thread,String community) throws SQLException{
			/*List<Adopt> adopt=(List<Adopt>)hibernateTemplate.find("from Adopt);
			if(adopt.size()==0)
				return "true";
			else
				return "false";*/
			return "true";
		}
		
		@Override
		public List<Likeq> listmylikes(int userid) throws SQLException{
			return (List<Likeq>)hibernateTemplate.find("from Likeq where userid=?",userid);
		}
		
		@Override
		public List<Follow> listfollow(int userid)throws SQLException{
			return (List<Follow>)hibernateTemplate.find("from Follow where userid=?",userid);
		}
		
		@Override
		public Followupda searchthreadupdate(String followname) throws SQLException{
			List<Followupda> list=(List<Followupda>)hibernateTemplate.find("from Followupda where thread=? order by time1 DESC",followname);
			if(list.size()>0)
			   return list.get(0);
			else
				return null;
		}

		@Override
		public List<Adopt> findaoptbycommunity(String community_name) throws SQLException {
			List<Adopt> list=(List<Adopt>)hibernateTemplate.find("from Adopt where community=? order by id DESC",community_name);
			return list;
		}

		@Override
		public String showusermore(int userid) throws SQLException {
			List<usermore> user=(List<usermore>)hibernateTemplate.find("from usermore where userid=?",userid);
			if(user.size()>0)
			    return user.get(0).getInterest();
			else
				return "";
		}

		@Override
		public void insertinterest(usermore user) throws SQLException {
			// TODO Auto-generated method stub
			//hibernateTemplate.saveorUpdate("update usermore set interest=? where username=? and password=?",user.getInterest(),user.getUsername(),user.getPassword());
			List<usermore> user1=(List<usermore>)hibernateTemplate.find("from usermore where userid=?",user.getUserid());;
			if(user1.size()>0)
			    hibernateTemplate.update(user);
			else
				hibernateTemplate.save(user);
			
			
		}

		@Override
		public int getIdbyname(String username, String password, String type) throws SQLException{
			// TODO Auto-generated method stub
			List<Integer> student=(List<Integer>)hibernateTemplate.find("select st_id from Student where username=? and password=? and type=?",username,password,type);
		    return student.get(0);
		}

		@Override
		public void saveintost_com(st_com st) throws SQLException {
			hibernateTemplate.save(st);// TODO Auto-generated method stub
			
		}

		@Override
		public List<Integer> getUseridbycommunity(String name) throws SQLException {
			return (List<Integer>)hibernateTemplate.find("select userid from st_com where community=?",name);
		}

		@Override
		public List<String> getcommunitybyuserid(int id) throws SQLException {
			return (List<String>)hibernateTemplate.find("select community from st_com where userid=?",id);
		}

		@Override
		public void insertcollect(Collect collect) throws SQLException {
			hibernateTemplate.save(collect);
			// TODO Auto-generated method stub
			
		}

		@Override
		public List<Collect> findcollectbycommunity(String community_name) throws SQLException {
			// TODO Auto-generated method stub
		     return (List<Collect>)hibernateTemplate.find("from Collect where community=?",community_name);
		}

		@Override
		public String checkmyadopt(int userid, String threadname) throws SQLException {
			// TODO Auto-generated method stub
			List<Myadopt> mydopt=(List<Myadopt>)hibernateTemplate.find("from Myadopt where userid=? and thread=?",userid,threadname);
			if(mydopt.size()==0)
					return "true";
			else
				return "false";
		}

		@Override
		public void insertmyadopt(Myadopt my) throws SQLException {
			// TODO Auto-generated method stub
			hibernateTemplate.save(my);
		}

		@Override
		public List<String> searchmyadopt(int userid) throws SQLException {
			List<String> mydopt=(List<String>)hibernateTemplate.find("select thread from Myadopt where userid=?",userid);
			return mydopt;
		}

		@Override
		public String checkmycollect(int userid, String threadname) throws SQLException {
			List<Mycollect> mycollect=(List<Mycollect>)hibernateTemplate.find("from Mycollect where userid=? and threadname=?",userid,threadname);
			if(mycollect.size()==0)
					return "true";
			else
				return "false";
		}

		@Override
		public void insertmycollect(Mycollect my) throws SQLException {
			// TODO Auto-generated method stub
			hibernateTemplate.save(my);
		}

		@Override
		public List<String> searchmycollect(int userid) throws SQLException {
			List<String> mycollect=(List<String>)hibernateTemplate.find("select thread from Mycollect where userid=?",userid);// TODO Auto-generated method stub
			return mycollect;
		}

		@Override
		public List<Mycollection> searchcollection(int userid) throws SQLException {
			// TODO Auto-generated method stub
			return (List<Mycollection>)hibernateTemplate.find("from Mycollection where userid=?",userid);
		}

		@Override
		public int getidbycollection(int userid, String collection) throws SQLException {
			List<Integer> list=(List<Integer>)hibernateTemplate.find("select id from Mycollection where userid=? and collectname=?",userid,collection);
			return list.get(0);
		}

		@Override
		public void insertcol_thread(col_thread col) throws SQLException {
			// TODO Auto-generated method stub
			hibernateTemplate.save(col);
		}

		@Override
		public void insertmycollection(Mycollection mycollection) throws SQLException {
			// TODO Auto-generated method stub
			hibernateTemplate.save(mycollection);
		}

		@Override
		public boolean checkcoexsit(int id, String threadname) throws SQLException {
			// TODO Auto-generated method stub
			 List<col_thread> list=(List<col_thread>)hibernateTemplate.find("from col_thread where collectid=? and thread=? ",id,threadname);
			 if(list.size()==0)
				 return true;
			 else 
				 return false;
		}

		@Override
		public List<Mycollection> findcollectbyid(int userid) throws SQLException {
			
			return (List<Mycollection>)hibernateTemplate.find("from Mycollection where userid=?",userid);
		}

		@Override
		public List<col_thread> findthreadbycollect(String collectname) throws SQLException {
			// TODO Auto-generated method stub
			int collectid=Integer.parseInt(collectname);
			return (List<col_thread>)hibernateTemplate.find("from col_thread where collectid=?",collectid);
		}

		@Override
		public double getsimilarthread(int projectidone,String threada,int projectidtwo,String threadb) throws SQLException {
			List<String> note1=(List<String>)(hibernateTemplate.find("select notecontent from Idea where projectid=? and threadname=?",projectidone,threada));
			List<String> note2=(List<String>)(hibernateTemplate.find("select notecontent from Idea where projectid=? and threadname=?",projectidtwo,threadb));
			String sb1="";
			String sb2="";
			for(int i=0;i<note1.size();i++){
				String st=note1.get(i).replaceAll("<.*?>", "").replaceAll("&nbsp; ", "").replaceAll("&nbsp;", "").replaceAll("\r","").replaceAll("\n","").replaceAll("\t","").trim();
			    sb1=sb1+" "+st;
			}
			System.out.println("this is first  "+sb1.toString());
			
			for(int i=0;i<note2.size();i++){
				String st=note2.get(i).replaceAll("<.*?>", "").replaceAll("&nbsp; ", "").replaceAll("&nbsp;", "").replaceAll("\r","").replaceAll("\n","").replaceAll("\t","").trim();
			    sb2=sb2+" "+st;
			}
			System.out.println("this is second  "+sb2);
			System.out.println(Compare.getSimilarDegree(sb1, sb2));
			return Compare.getSimilarDegree(sb1, sb2);
		}

		@Override
		public List<Projecthread> findallthread() throws SQLException {
			// TODO Auto-generated method stub
			return (List<Projecthread>)hibernateTemplate.find("from Projecthread");
		}

		@Override
		public List<Project> findprojectbythread(int projectid) throws SQLException {
			List<Project> project=(List<Project>)hibernateTemplate.find("from Project where id=?",projectid);
			
			return project;
		}

		@Override
		public boolean checkst_com(int userid, String com) {
			List<st_com> list=(List<st_com>) hibernateTemplate.find("from st_com where userid=? and community=?",userid,com);
			if(list.size()!=0) return true;
			else return false;
		}

		@Override
		public List<Integer> getUseridbygroupid(String id) {
			// TODO Auto-generated method stub
			return (List<Integer>)hibernateTemplate.find("select student_id from Stgroup where group_id=?",Integer.valueOf(id));
		}

		@Override
		public void saveintostgroup(Stgroup sgroup) {
			// TODO Auto-generated method stub
			hibernateTemplate.save(sgroup);
		}

		@Override
		public List<Threadsimilar> find_inthreadsimilar(String projectid, String threadname) throws SQLException{
			// TODO Auto-generated method stub
			return (List<Threadsimilar>)hibernateTemplate.find("from Threadsimilar where projectidone=? and threadone=?",Integer.valueOf(projectid),threadname);
			
		}

		@Override
		public void inserttosimilarthread(Threadsimilar threadsimlar) {
			// TODO Auto-generated method stub
			hibernateTemplate.save(threadsimlar);
			
		}

		@Override
		public List<Threadsimilar> findsimilar(int projectid, String threadname) {
			// TODO Auto-generated method stub
			List<Threadsimilar> thread=(List<Threadsimilar>)hibernateTemplate.find("from Threadsimilar where projectidone=? and threadone=? order by score DESC",projectid,threadname);
			return thread;
		}

		@Override
		public List<Note_note> browsecontent(int id) throws SQLException {
			List<Note_note> notes=(List<Note_note>)hibernateTemplate.find("from Note_note where parent_id=?",id);
			return notes;
		}

		@Override
		public List<Project> getprojectbyarea(int id) throws SQLException {
			// TODO Auto-generated method stub
			List<Project> projects=(List<Project>)hibernateTemplate.find("from Project where area=?",id);
			return projects;
		}

		@Override
		public List<Integer> getfirstlevel(int id,String name) {
			// TODO Auto-generated method stub
			//String sql="SELECT note_id,addtime FROM  thread_note inner join  project2 on  project2.idProject =thread_note.projectid where project2.projectid=? and thread_note.threadfocus = ?"
			return (List<Integer>)hibernateTemplate.find("select noteid from thread_note where projectid=? and threadfocus=? order by addtime DESC",id,name);
		}

		@Override
		public List<Integer> getotherlevel(int id) {
			// TODO Auto-generated method stub
			return (List<Integer>)hibernateTemplate.find("select tonoteid from noterelation where fromnoteid=?",id);
		}

		@Override
		public boolean is_exsit_tonoteid(int id) {
			List<Integer> list=(List<Integer>)hibernateTemplate.find("select fromnoteid from noterelation where tonoteid=?",id);
			if(list.size()==0) return false;
			else return true;
		}

		@Override
		public List<Idea> getNoteById(List<Integer> list,String projectid,String threadname) {
			List<Idea> result=new ArrayList<Idea>();
			for(int id:list){
				List<Idea> tem=(List<Idea>)hibernateTemplate.find("from Idea where noteid=? and projectid=? and threadname=?",id,Integer.valueOf(projectid),threadname);
				if(tem.size()==0) continue;
				result.add(tem.get(0));
			}
			return result;
		}

		@Override
		public List<Integer> getauthorid(int noteid) {
			
			return (List<Integer>)hibernateTemplate.find("select author_id from Author_note where note_id=?",noteid);
		}

		@Override
		public String getnamebyid(int ids) {
			// TODO Auto-generated method stub
			List<String> result=(List<String>)hibernateTemplate.find("select username from Author_table where authorid=?",ids);
			if(result.size()==0) return "";
			return result.get(0);
		}

		@Override
		public List<Project> findprojectbyprojectid(String projectid) {
			// TODO Auto-generated method stub
			return (List<Project>)hibernateTemplate.find("from Project where id=?",Integer.valueOf(projectid));
		}

		@Override
		public String getareanamebyid(int area) {
			// TODO Auto-generated method stub
			List<String> list=(List<String>)hibernateTemplate.find("select name from Note_note where id=?",area);
			if(list.size()==0) return "";
			return list.get(0);
		}

		@Override
		public Project getprojectbyid(int projectid) {
			// TODO Auto-generated method stub
			List<Project> project=(List<Project>)hibernateTemplate.find("from Project where id=?",projectid);
			if(project.size()==0) return null;
			return project.get(0);
		}

		@Override
		public void insertComment(int noteid, String threadname, int projectid) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public List<Comment> findcomment(int noteid, String threadname, int projectid) {
			// TODO Auto-generated method stub
			return (List<Comment>)hibernateTemplate.find("from Comment where noteid=? and threadname=? and projectid=? order by time DESC",noteid,threadname,projectid);
		}

		@Override
		public int caculateLike(int projectid, String threadfocus) {
			// TODO Auto-generated method stub
			List<Integer> list=(List<Integer>)hibernateTemplate.find("select id from Likeq where projectid=? and thread=?",projectid,threadfocus);
			return list.size();
		}

		@Override
		public int caculateFollow(int projectid, String threadfocus) {
			// TODO Auto-generated method stub
			List<Integer> list=(List<Integer>)hibernateTemplate.find("select id from Follow where projectid=? and followname=?",projectid,threadfocus);
			return list.size();
		}
}