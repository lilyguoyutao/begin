/**
 *
 */
package com.jcg.examples.service;

import java.sql.SQLException;
import java.util.List;

import com.jcg.examples.viewBean.Note_note;
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

/**
 * @author CENTAUR
 *
 */

public interface UserService
{
		
		public int checkUserpass_service(String username,String password,String type) throws SQLException;
		public void saveStuService(Student student) throws SQLException;
		public Student listuserService(int id) throws SQLException;
		public Student userfile(String name,String password,String type) throws SQLException;
		public List<String> findcombyuser(String name) throws SQLException;
		public void changepassservice(String username,String old,String password) throws SQLException;
        public boolean check(int id,String community) throws SQLException;
        public void edit(Student student) throws SQLException;
        public List<Project> searchproject(String html) throws SQLException;
        public List<Projecthread> searchthread(String html) throws SQLException;
        public List<String> checklike(int userid,String projectid,String thread) throws SQLException;
        public void insertlik(Likeq lik) throws SQLException;
        public void deletelik(Likeq lik) throws SQLException;
        public List<Likeq> listlike(String projectid,String threadname) throws SQLException;
        public void  insertfollow(Follow follow) throws SQLException;
        public String  checkfollow(int userid,String projectid,String thread) throws SQLException;
        public void  insertadopt(Adopt adopt) throws SQLException;
        public String  checkadopt(int userid,String projectid,String thread,String community) throws SQLException;
        public List<Likeq> listmylikes(int userid) throws SQLException;
		public List<Follow> listfollow(int userid)throws SQLException;
		public Followupda searchthreadupdate(String followname) throws SQLException;
		public List<Adopt> findaoptbycommunity(String community_name) throws SQLException;
		
		public void insertinterest(usermore user) throws SQLException;
		public int getIdbyname(String username,String password,String type) throws SQLException;
		public void saveintost_com(st_com st) throws SQLException;
		public List<Integer> getUseridbycommunity(String name) throws SQLException;
		public List<String> getcommunitybyuserid(int id) throws SQLException;
		public String showusermore(int userid) throws SQLException;
		public void insertcollect(Collect collect) throws SQLException;
		public List<Collect> findcollectbycommunity(String community_name) throws SQLException;
		public String checkmyadopt(int userid, String threadname) throws SQLException;
		public void insertmyadopt(Myadopt my) throws SQLException;
		public List<String> searchmyadopt(int userid)throws SQLException;
		public String checkmycollect(int userid, String threadname) throws SQLException;
		public void insertmycollect(Mycollect my)throws SQLException;
		public List<String> searchmycollect(int userid)throws SQLException;
		public List<Mycollection> searchcollection(int userid) throws SQLException;
		public int getidbycollection(int userid, String collection) throws SQLException;
		public void insertcol_thread(col_thread col) throws SQLException;
		public void insertmycollection(Mycollection mycollection) throws SQLException;
		public boolean checkcoexsit(int id, String threadname) throws SQLException;
		public List<Mycollection> findcollectbyid(int userid) throws SQLException;
		public List<col_thread> findthreadbycollect(String collectid) throws SQLException;
		public double getsimilarthread(int projectidone,String thread1,int projectidtwo,String thread2) throws SQLException;
		public List<Projecthread> findallthread() throws SQLException;
		public List<Project> findprojectbythread(int projectid) throws SQLException;
		public boolean checkst_com(int userid, String com);
		public List<Integer> getUseridbygroupid(String id);
		public void saveintostgroup(Stgroup sgroup);
		public List<Threadsimilar> find_inthreadsimilar(String projectid, String threadname) throws SQLException;
		public void inserttosimilarthread(Threadsimilar threadsimlar);
		public List<Threadsimilar> findsimilar(int projectid, String threadname);
		public List<Note_note> browsecontent(int id) throws SQLException;
		public List<Project> getprojectbyarea(int id) throws SQLException;
		public List<Integer> getfirstlevel(int id,String name) throws SQLException;
		public List<Integer> getotherlevel(int id) throws SQLException;
		public boolean is_exsit_tonoteid(int id);
		public List<Idea> getNoteById(List<Integer> list,String projectid,String threadname) throws SQLException;
		public List<Integer> getauthorid(int noteid);
		public String getnamebyid(int ids);
		public List<Project> findprojectbyprojectid(String projectid);
		public String findareanamebyid(int area);
		public Project getprojectbyid(int projectid);
		public void insertComment(int noteid, String threadname, int projectid);
		public List<Comment> findcomment(int noteid, String threadname, int projectid);
		public int caculateLike(int projectid, String threadfocus);
		public int caculateFollow(int projectid, String threadfocus);
		
}
