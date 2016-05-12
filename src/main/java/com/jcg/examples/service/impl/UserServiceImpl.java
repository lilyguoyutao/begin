package com.jcg.examples.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcg.examples.dao.UserDao;
import com.jcg.examples.viewBean.Note_note;
import com.jcg.examples.service.UserService;
import com.jcg.examples.viewBean.Adopt;
import com.jcg.examples.viewBean.Collect;
import com.jcg.examples.viewBean.Comment;
import com.jcg.examples.viewBean.Follow;
import com.jcg.examples.viewBean.Likeq;
import com.jcg.examples.viewBean.Myadopt;
import com.jcg.examples.viewBean.Mycollect;
import com.jcg.examples.viewBean.Mycollection;
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

@Service
public class UserServiceImpl implements UserService
{
        @Autowired
		private UserDao userDao;

		//public UserDao getUserDao()
		//{
		//		return this.userDao;
		//}

		//public void setUserDao(UserDao userDao)
		//{
		//		this.userDao = userDao;
		//}


		
		@Override
		public int checkUserpass_service(String username,String password,String type) throws SQLException
		{
			return userDao.checkUserpass(username, password,type);
		}
		
		
		@Override
		public void saveStuService(Student student) throws SQLException
		{
				 userDao.saveStuDao(student);
		}
		
		
		@Override
		public Student listuserService(int id) throws SQLException{
			return userDao.listuserDao(id);
		}
		
		
		@Override
		public Student userfile(String name,String password,String type) throws SQLException{
			return userDao.userfileDao(name,password,type);
		}
		
		@Override
		public List<String> findcombyuser(String name) throws SQLException{
			return userDao.findcombyuser(name);
		}
		
		@Override
		public void changepassservice(String username, String old,String password) throws SQLException{
			userDao.changeDao(username,old,password);
		}
		
		@Override
		 public boolean check(int id,String community) throws SQLException{
			return userDao.check(id,community);
		}
		
		@Override
		public void edit(Student student) throws SQLException{
			userDao.editDao(student);
		}
		
		@Override
		public List<Project> searchproject(String html) throws SQLException{
			return userDao.searchprojectDao(html);
		}
		
		@Override
		public List<Projecthread> searchthread(String html) throws SQLException{
			return userDao.searchthread(html);
		}
		
		@Override
		public List<String> checklike(int userid,String projectid,String thread) throws SQLException{
			return userDao.checklike(userid,projectid,thread);
		}
		
		@Override
		  public void insertlik(Likeq lik) throws SQLException{
			userDao.insertlik(lik);
		}
		
		@Override
	        public void deletelik(Likeq lik) throws SQLException
	        {userDao.deletelik(lik);}
		
		@Override
		public List<Likeq> listlike(String projectid,String threadname) throws SQLException{
			return userDao.listlike(projectid,threadname);
		}
		
		@Override
		 public void  insertfollow(Follow follow) throws SQLException{
			userDao.insertfollow(follow);
		}
		
		@Override
		public String checkfollow(int userid,String projectid,String thread) throws SQLException{
			return userDao.checkfollow(userid,projectid,thread);
		}
		
		@Override
		public void  insertadopt(Adopt adopt) throws SQLException{
			userDao.insertadopt(adopt);
		}
		
		@Override
        public String  checkadopt(int userid,String projectid,String thread,String community) throws SQLException{
			  return userDao.checkadopt(userid,projectid,thread,community);
		}
		
		@Override
		public List<Likeq> listmylikes(int userid) throws SQLException{
			return userDao.listmylikes(userid);
		}
		@Override
		public List<Follow> listfollow(int userid)throws SQLException{
			return userDao.listfollow(userid);
		}
        
		@Override
		public Followupda searchthreadupdate(String followname) throws SQLException{
			return userDao.searchthreadupdate(followname);
		}
		
		@Override
		public List<Adopt> findaoptbycommunity(String community_name) throws SQLException{
			return userDao.findaoptbycommunity(community_name);
		}


		@Override
		public String showusermore(int userid) throws SQLException {
			// TODO Auto-generated method stub
			return userDao.showusermore(userid);
		}


		@Override
		public void insertinterest(usermore user) throws SQLException {
			// TODO Auto-generated method stub
			   userDao.insertinterest(user);
		}


		@Override
		public int getIdbyname(String username, String password, String type) throws SQLException{
			// TODO Auto-generated method stub
			return userDao.getIdbyname( username,password,type);
		}


		@Override
		public void saveintost_com(st_com st) throws SQLException {
			// TODO Auto-generated method stub
			userDao.saveintost_com(st);
		}


		@Override
		public List<Integer> getUseridbycommunity(String name) throws SQLException {
			return userDao.getUseridbycommunity(name);
		}


		@Override
		public List<String> getcommunitybyuserid(int id) throws SQLException {
			return userDao.getcommunitybyuserid(id);
		}


		@Override
		public void insertcollect(Collect collect) throws SQLException {
			userDao.insertcollect(collect);
			
		}


		@Override
		public List<Collect> findcollectbycommunity(String community_name) throws SQLException {
			return userDao.findcollectbycommunity(community_name);
		}


		@Override
		public String checkmyadopt(int userid, String threadname) throws SQLException {
			// TODO Auto-generated method stub
			return userDao.checkmyadopt(userid,threadname);
		}


		@Override
		public void insertmyadopt(Myadopt my) throws SQLException {
			// TODO Auto-generated method stub
			userDao.insertmyadopt(my);
		}


		@Override
		public List<String> searchmyadopt(int userid) throws SQLException {
			// TODO Auto-generated method stub
			return userDao.searchmyadopt(userid);
		}


		@Override
		public String checkmycollect(int userid, String threadname) throws SQLException {
			// TODO Auto-generated method stub
			return userDao.checkmycollect(userid,threadname);
		}


		@Override
		public void insertmycollect(Mycollect my) throws SQLException {
			// TODO Auto-generated method stub
			userDao.insertmycollect(my);
		}


		@Override
		public List<String> searchmycollect(int userid) throws SQLException {
			// TODO Auto-generated method stub
			return userDao.searchmycollect(userid);
		}


		@Override
		public List<Mycollection> searchcollection(int userid) throws SQLException {
			// TODO Auto-generated method stub
			return userDao.searchcollection(userid);
		}


		@Override
		public int getidbycollection(int userid, String collection) throws SQLException {
			// TODO Auto-generated method stub
			return userDao.getidbycollection(userid,collection);
		}


		@Override
		public void insertcol_thread(col_thread col) throws SQLException {
			// TODO Auto-generated method stub
			 userDao.insertcol_thread(col);
		}


		@Override
		public void insertmycollection(Mycollection mycollection) throws SQLException {
			// TODO Auto-generated method stub
			userDao.insertmycollection(mycollection);
		}


		@Override
		public boolean checkcoexsit(int id, String threadname) throws SQLException {
			// TODO Auto-generated method stub
			return userDao.checkcoexsit(id,threadname);
		}


		@Override
		public List<Mycollection> findcollectbyid(int userid) throws SQLException {
			return userDao.findcollectbyid(userid);
			
		}


		@Override
		public List<col_thread> findthreadbycollect(String collectid) throws SQLException {
			return userDao.findthreadbycollect(collectid);
		}


		@Override
		public double getsimilarthread(int projectidone,String thread1,int projectidtwo,String thread2) throws SQLException {
			// TODO Auto-generated method stub
			return userDao.getsimilarthread(projectidone,thread1,projectidtwo,thread2);
		}


		@Override
		public List<Projecthread> findallthread() throws SQLException {
			// TODO Auto-generated method stub
			return userDao.findallthread();
		}


		@Override
		public List<Project> findprojectbythread(int projectid) throws SQLException {
			// TODO Auto-generated method stub
			return userDao.findprojectbythread(projectid);
		}


		@Override
		public boolean checkst_com(int userid, String com) {
			// TODO Auto-generated method stub
			return userDao.checkst_com(userid,com);
		}


		@Override
		public List<Integer> getUseridbygroupid(String id) {
			// TODO Auto-generated method stub
			return userDao.getUseridbygroupid(id);
		}


		@Override
		public void saveintostgroup(Stgroup sgroup) {
			// TODO Auto-generated method stub
			userDao.saveintostgroup(sgroup);
		}


		@Override
		public List<Threadsimilar> find_inthreadsimilar(String projectid, String threadname) throws SQLException {
			// TODO Auto-generated method stub
			return userDao.find_inthreadsimilar(projectid,threadname);
		}


		@Override
		public void inserttosimilarthread(Threadsimilar threadsimlar) {
			// TODO Auto-generated method stub
			userDao.inserttosimilarthread(threadsimlar);
		}


		@Override
		public List<Threadsimilar> findsimilar(int projectid, String threadname) {
			// TODO Auto-generated method stub
			return userDao.findsimilar(projectid,threadname);
		}


		@Override
		public List<Note_note> browsecontent(int id) throws SQLException {
			// TODO Auto-generated method stub
			return userDao.browsecontent(id);
		}


		@Override
		public List<Project> getprojectbyarea(int id) throws SQLException {
			// TODO Auto-generated method stub
			return userDao.getprojectbyarea(id);
		}


		@Override
		public List<Integer> getfirstlevel(int id,String name) throws SQLException {
			// TODO Auto-generated method stub
			return userDao.getfirstlevel(id,name);
		}


		@Override
		public List<Integer> getotherlevel(int id) throws SQLException {
			// TODO Auto-generated method stub
			return userDao.getotherlevel(id);
		}


		@Override
		public boolean is_exsit_tonoteid(int id) {
			// TODO Auto-generated method stub
			return userDao.is_exsit_tonoteid(id);
		}


		@Override
		public List<Idea> getNoteById(List<Integer> list,String projectid,String threadname) throws SQLException {
			// TODO Auto-generated method stub
			return userDao.getNoteById(list,projectid,threadname);
		}


		@Override
		public List<Integer> getauthorid(int noteid) {
			// TODO Auto-generated method stub
			return userDao.getauthorid(noteid);
		}


		@Override
		public String getnamebyid(int ids) {
			// TODO Auto-generated method stub
			return userDao.getnamebyid(ids);
		}


		@Override
		public List<Project> findprojectbyprojectid(String projectid) {
			// TODO Auto-generated method stub
			return userDao.findprojectbyprojectid(projectid);
		}


		@Override
		public String findareanamebyid(int area) {
			// TODO Auto-generated method stub
			return userDao.getareanamebyid(area);
		}


		@Override
		public Project getprojectbyid(int projectid) {
			// TODO Auto-generated method stub
			return userDao.getprojectbyid(projectid);
		}


		@Override
		public void insertComment(int noteid, String threadname, int projectid) {
			// TODO Auto-generated method stub
			userDao.insertComment(noteid,threadname,projectid);
		}


		@Override
		public List<Comment> findcomment(int noteid, String threadname, int projectid) {
			// TODO Auto-generated method stub
			return userDao.findcomment(noteid,threadname,projectid);
		}


		@Override
		public int caculateLike(int projectid, String threadfocus) {
			// TODO Auto-generated method stub
			return userDao.caculateLike(projectid,threadfocus);
		}


		@Override
		public int caculateFollow(int projectid, String threadfocus) {
			// TODO Auto-generated method stub
			return userDao.caculateFollow(projectid,threadfocus);
		}


	
}
