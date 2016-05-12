package com.jcg.examples.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jcg.examples.service.CommunityService;
import com.jcg.examples.service.UserService;
import com.jcg.examples.viewBean.Adopt;
import com.jcg.examples.viewBean.Collect;
import com.jcg.examples.viewBean.Combin;
import com.jcg.examples.viewBean.Comment;
import com.jcg.examples.viewBean.Community;
import com.jcg.examples.viewBean.Follow;
import com.jcg.examples.viewBean.Likeq;
import com.jcg.examples.viewBean.Myadopt;
import com.jcg.examples.viewBean.Mycollection;
import com.jcg.examples.viewBean.Note_note;
import com.jcg.examples.viewBean.Project;
import com.jcg.examples.viewBean.Projecthread;
import com.jcg.examples.viewBean.Student;
import com.jcg.examples.viewBean.Threadsimilar;
import com.jcg.examples.viewBean.col_thread;
import com.jcg.examples.viewBean.Followupda;
import com.jcg.examples.viewBean.Idea;

@Controller
public class Projectcontroller {
	@Autowired
	private UserService userService;
	//private HibernateTemplate hibernateTemplate;
    @Autowired
    private CommunityService conservice;
    
    @RequestMapping(value="/browse")
    public ModelAndView browse()
    {
    	ModelAndView model=new ModelAndView("Browse");
    	return model;
    }
    
    @RequestMapping(value="/searchproject",method=RequestMethod.POST)
    public ModelAndView searchproject(HttpServletRequest request) throws SQLException{
    	
    	ModelAndView model=new ModelAndView();
    	String name=request.getParameter("name");
    	String school=request.getParameter("school");
    	String teacher=request.getParameter("teacher");
    	String fromyear=request.getParameter("fromyear");
    	String toyear=request.getParameter("toyear");
    	String community=request.getParameter("community");
    	if(school==null)
    		school="";
    	if(teacher==null)
    		teacher="";
    	if(fromyear==null)
    		fromyear="";
    	if(toyear==null)
    		toyear="";
    	if(community==null)
    		community="";
    	String html="";
    		
    		html="from Project where projectname like '%"+name+"%'";
    	
    
    	

       if(!school.equals("")){	   
    	   html+=" and school='"+school+"'"; 

    }
       if(!teacher.equals("")){	   
    	   html+=" and teacher='"+teacher+"'"; 

    }
       if(!fromyear.equals(""))
          html+=" and fromyear='"+fromyear+"'";
       if(!toyear.equals(""))
    	   html+=" and toyear='"+toyear+"'";
       if(!community.trim().equals("all"))
    	   html+=" and communityname='"+community+"'";
       List<Project> projects=userService.searchproject(html);
       for(Project project:projects){
    	  String areaname= userService.findareanamebyid(project.getArea());
    	  project.setBigidea(areaname);
       }
       model.addObject("projects",projects);
       
       model.setViewName("Browse");
       return model;
       
}
    @RequestMapping(value="/searchprojectall")
    public ModelAndView searchprojectall(HttpServletRequest request) throws SQLException{
    	
    	ModelAndView model=new ModelAndView();
    	
    	String html="";
    		
    		html="from Project";
    	
    
    	

       
       List<Project> projects=userService.searchproject(html);
       for(Project project:projects){
     	  String areaname= userService.findareanamebyid(project.getArea());
     	  project.setBigidea(areaname);
        }
       
       model.addObject("projects",projects);
       
       model.setViewName("Browse");
       return model;
       
}
    @RequestMapping(value="/searchthread")
    public ModelAndView searchthread()
    {   
    	ModelAndView model=new ModelAndView("searchthread");
    	return model;
    }
    
    @RequestMapping(value="/searchthread",method=RequestMethod.POST)
    public ModelAndView searchthread(HttpServletRequest request) throws SQLException{
    	
    	ModelAndView model=new ModelAndView();
    	String name=request.getParameter("name");
    	String author=request.getParameter("author");
    	String project=request.getParameter("project");
    	
    	if(name==null)
    		name="";
    	if(author==null)
    		author="";
    	if(project==null)
    		project="";
    	
    	String html="";
    		
    		html="from Projecthread where threadfocus like '%"+name+"%'";
    	
    
    	

       if(!author.equals("")){	   
    	   html+=" and author='"+author+"'"; 

    }
       if(!project.trim().equals("all")){	   
    	   html+=" and projectname='"+project+"'"; 

    }
       html +=" and projectid in (select id from Project)";
 
       List<Projecthread> thread=userService.searchthread(html);
       for(Projecthread threads:thread){
       int like=userService.caculateLike(threads.getProjectid(),threads.getThreadfocus());
       int follow=userService.caculateFollow(threads.getProjectid(),threads.getThreadfocus());
       threads.setAuthor(String.valueOf(like));
       threads.setId(follow);
       }
       model.addObject("threads",thread);
       model.setViewName("searchthread");
       return model;
       
}
    @RequestMapping(value="/searchthreadall")
    public ModelAndView searchthreadall(HttpServletRequest request) throws SQLException{
    	
    	ModelAndView model=new ModelAndView();
    	
    	
    	
    	
    	String html="";
    		
    		html="from Projecthread where projectid in (select id from Project)";
    
 
       List<Projecthread> thread=userService.searchthread(html);
       for(Projecthread threads:thread){
           int like=userService.caculateLike(threads.getProjectid(),threads.getThreadfocus());
           int follow=userService.caculateFollow(threads.getProjectid(),threads.getThreadfocus());
           threads.setAuthor(String.valueOf(like));
           threads.setId(follow);
           }
       
       model.addObject("threads",thread);
       model.setViewName("searchthread");
       return model;
       
}
    @RequestMapping(value="/getprojectname")
    @ResponseBody
    public List<String> getprojectname() throws SQLException
    {   String html="from Project";
    	List<Project> list=userService.searchproject(html);
    	List<String> projectname=new ArrayList<String>();
    	for(int i=0;i<list.size();i++)
    	{ projectname.add(list.get(i).getProjectname());}
    	return projectname;
    	
    }
    
    
    @RequestMapping(value="/whetherlike")
    @ResponseBody
    public List<String> getwhetherlike(HttpServletRequest request, HttpSession session) throws SQLException
       
    {   String userid=(String)session.getAttribute("id");
    	String threadname=(String)request.getParameter("threadname");
    	String projectid=(String)request.getParameter("projectid");
    	//System.out.println("this aaais "+ threadname);
    	return userService.checklike(Integer.parseInt(userid),projectid,threadname);
    	
    }
    
    @RequestMapping(value="/likeme")
    @ResponseBody
    public List<String> insertlike(HttpServletRequest request,HttpSession session) throws SQLException{
    	String userid=(String)session.getAttribute("id");
     	String threadname=(String)request.getParameter("threadname");
     	String projectid=(String)request.getParameter("projectid");
     	//ModelAndView model=new ModelAndView("threadpage");
     	Likeq lik=new Likeq();
     	lik.setUserid(Integer.parseInt(userid));
     	lik.setThread(threadname);
     	lik.setProjectid(Integer.valueOf(projectid));
     	userService.insertlik(lik);
     	//String userid=(String)session.getAttribute("id");
    	//String threadname=(String)request.getParameter("threadname");
    	//String projectid=(String)request.getParameter("projectid");
    	//System.out.println("this aaais "+ threadname);
    	return userService.checklike(Integer.parseInt(userid),projectid,threadname);
     	
    }
    @RequestMapping(value="/notecomment")
    public ModelAndView notecomment(HttpServletRequest request,HttpSession session) throws SQLException{
    	String noteid=(String)request.getParameter("noteid");
     	String threadname=(String)request.getParameter("threadname");
     	String projectid=(String)request.getParameter("projectid");
     	ModelAndView model=new ModelAndView("notecomment");
     	//List<Comment> comments=userService.findcomment(Integer.valueOf(noteid),threadname,Integer.valueOf(projectid));
     	
     	model.addObject("noteid", noteid);
     	model.addObject("threadname",threadname);
     	model.addObject("projectid",projectid);
     	//model.addObject("comments", comments);
     	List<Comment> commentss=userService.findcomment(Integer.valueOf(noteid),threadname,Integer.valueOf(projectid));
     	model.addObject("listcomment", commentss);
     	return model;
    }
    
    @RequestMapping(value="/notecomment",method=RequestMethod.POST)
    public ModelAndView notecomments(HttpServletRequest request,HttpSession session) throws SQLException{
    	String noteid=(String)request.getParameter("noteid");
     	String threadname=(String)request.getParameter("threadname");
     	String projectid=(String)request.getParameter("projectid");
     	String message=(String)request.getParameter("mess");
     	String firstname=(String)session.getAttribute("firstname");
     	String lastname=(String)session.getAttribute("lastname");
     	ModelAndView model=new ModelAndView("notecomment");
     	System.out.println("noteid"+noteid);
     	Comment comments=new Comment();
     	comments.setNoteid(Integer.valueOf(noteid));
     	comments.setProjectid(Integer.valueOf(projectid));
     	comments.setThreadname(threadname);
     	comments.setMe_text(message);
     	comments.setAuthor(firstname+" "+lastname);
     	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		comments.setTime(df.format(new Date()));
		conservice.savenotecomment(comments);
     	//userService.insertComment(Integer.valueOf(noteid),threadname,Integer.valueOf(projectid));
     	List<Comment> commentss=userService.findcomment(Integer.valueOf(noteid),threadname,Integer.valueOf(projectid));
     	model.addObject("listcomment", commentss);
 
     	return model;
    }
    
    @RequestMapping(value="/dislikeme")
    @ResponseBody
    public List<String> dellike(HttpServletRequest request,HttpSession session) throws SQLException{
    	 
 	    String userid=(String)session.getAttribute("id");
     	String threadname=(String)request.getParameter("threadname");
     	String projectid=(String)request.getParameter("projectid");
     	//ModelAndView model=new ModelAndView("threadpage");
     	Likeq lik=new Likeq();
     	lik.setUserid(Integer.parseInt(userid));
     	lik.setProjectid(Integer.valueOf(projectid));
     	lik.setThread(threadname);
     	userService.deletelik(lik);
     	//model.addObject("threadname", threadname);
     	//model.addObject("projectid",projectid);
     	//return model;
     	return userService.checklike(Integer.parseInt(userid),projectid,threadname);
    }
    
    /*@RequestMapping(value="/likelist")
    public ModelAndView listlike(HttpServletRequest request,HttpSession session) throws SQLException{
    	String threadname=(String)request.getParameter("threadname");
    	String projectid=(String)request.getParameter("projectid");
    	ModelAndView model=new ModelAndView("listlike");
    	List<Likeq> list=userService.listlike(projectid,threadname);
    	List<Student> student=new ArrayList<Student>();
    	for(int i=0;i<list.size();i++){
    		
    		student.add(userService.listuserService(list.get(i).getUserid()));
    	}
    	model.addObject("listlike", student);
    	return model;
    }*/
    
    @RequestMapping(value="/follow")
    @ResponseBody
    public String follow(HttpServletRequest request,HttpSession session) throws SQLException{
    	 
  	  String userid=(String)session.getAttribute("id");
  	  String projectid=(String)request.getParameter("projectid");
  	  String threadname=(String)request.getParameter("threadname");
  	   Follow follow=new Follow();
  	   follow.setUserid(Integer.parseInt(userid));
  	   follow.setProjectid(Integer.valueOf(projectid));
  	   follow.setFollowname(threadname);
  	   follow.setIsthread("true");
  	   userService.insertfollow(follow);
  	   
  	   return userService.checkfollow(Integer.parseInt(userid), projectid,threadname);
  	   
  		
  	   
    }
    
    
    @RequestMapping(value="/checkfollow")
    @ResponseBody
    public String getcheckfollow(HttpServletRequest request, HttpSession session) throws SQLException
       
    {   //String password=(String)session.getAttribute("password");
	    String userid=(String)session.getAttribute("id");
    	String threadname=(String)request.getParameter("threadname");
    	String projectid=(String)request.getParameter("projectid");
    	return userService.checkfollow(Integer.parseInt(userid), projectid,threadname);
    	
    }
    
    @RequestMapping(value="/checkadopt")
    @ResponseBody
    public String checkadopt(HttpServletRequest request, HttpSession session) throws SQLException
    
    {   
	    String userid=(String)session.getAttribute("id");
    	String threadname=(String)request.getParameter("thread");
    	String projectid=(String)request.getParameter("projectid");
    	String community=(String) request.getParameter("community");
    	String reason=(String)request.getParameter("reason");
    	return userService.checkadopt(Integer.parseInt(userid), projectid,threadname,community);
    	
       
    }
  
    
    @RequestMapping(value="/insertadopt",method=RequestMethod.POST)
    public ModelAndView insertadopt(HttpServletRequest request,HttpSession session) throws SQLException{
    	String userid=(String)session.getAttribute("id");
    	String threadname=(String)request.getParameter("threadname");
    	String projectid=(String)request.getParameter("projectid");
    	String reason=(String)request.getParameter("reason");
    	String community=(String) request.getParameter("community");
    	Adopt adopt=new Adopt();
    	adopt.setUserid(Integer.parseInt(userid));
    	adopt.setProjectid(Integer.valueOf(projectid));
    	adopt.setReason(reason);
    	adopt.setThreadname(threadname);
    	adopt.setCommunity(community);
    	userService.insertadopt(adopt);
    	
    	ModelAndView model=new ModelAndView("Adopt");
    	model.addObject("from",threadname+" has been recommended to '" +community+" 'community");
    	model.addObject("threadname", threadname);
    	model.addObject("projectid",projectid);
    	return model;
    }
    
    @RequestMapping(value="/adopt")
    public ModelAndView adopt(HttpServletRequest request,HttpSession session) throws SQLException{
    	ModelAndView model=new ModelAndView("Adopt");
    	model.addObject("threadname",(String)request.getParameter("threadname"));
        model.addObject("projectid",(String)request.getParameter("projectid"));
        return model;
    }
    /*@RequestMapping(value="/collect")
    public ModelAndView collect(HttpServletRequest request,HttpSession session) throws SQLException{
    	ModelAndView model=new ModelAndView("Collect");
        model.addObject("threadname",(String)request.getParameter("threadname"));
        model.addObject("projectid",(String)request.getParameter("projectid"));
        return model;
    }
    */
    /*
    @RequestMapping(value="/insertcollect",method=RequestMethod.POST)
    public ModelAndView insertcollect(HttpServletRequest request,HttpSession session) throws SQLException{
    	String userid=(String)session.getAttribute("id");
    	String threadname=(String)request.getParameter("threadname");
    	String projectid=(String)request.getParameter("projectid");
    	String reason=(String)request.getParameter("reason");
    	String community=(String) request.getParameter("community");
    	Collect collect=new Collect();
    	collect.setUserid(Integer.parseInt(userid));
    	collect.setProjectid(Integer.valueOf(projectid));
    	collect.setThreadname(threadname);
    	collect.setCommunity(community);
    	collect.setReason(reason);
    	userService.insertcollect(collect);
    	ModelAndView model=new ModelAndView("Collect");
    	model.addObject("from",threadname+" has been recommended to '" +community+" 'community");
    	model.addObject("threadname", threadname);
    	model.addObject("projectid",projectid);
    	return model;
    }
    */
    @RequestMapping(value="/personal")
    public ModelAndView personal(HttpServletRequest reqeust,HttpSession session) throws SQLException{
    	String userid=(String)session.getAttribute("id");
    	String password=(String)session.getAttribute("password");
	    String username=(String)session.getAttribute("username");
	    String type=(String)session.getAttribute("type");
	    List<Likeq> list=userService.listmylikes(Integer.parseInt(userid));
	    List<Combin> com=new ArrayList<Combin>();
	    for(Likeq li:list){
	    	int projectid=li.getProjectid();
	    	List<Project> po=userService.findprojectbythread(projectid);
	    	Combin newcom=new Combin();
	    	newcom.setThreadname(li.getThread());
	    	newcom.setProjectid(projectid);
	    	if(po.size()!=0){
	    		newcom.setProjectname(po.get(0).getProjectname());
	    		newcom.setCommunityname(po.get(0).getCommunityname());
	    	}
	    	com.add(newcom);
	    }
	    List<Follow> folist=userService.listfollow(Integer.parseInt(userid));
	    List<Followupda> update=new ArrayList<Followupda>();
	    
	    for(int i=0;i<folist.size();i++)
	    {  if(userService.searchthreadupdate(folist.get(i).getFollowname())!=null)
	    	update.add(userService.searchthreadupdate(folist.get(i).getFollowname()));
	    }
	    List<String> name=new ArrayList<String>();
	    if(type.equals("teacher"))
	    {
	    	List<Community> listco=conservice.listservice(username);
	    	for(int i=0;i<listco.size();i++)
	    	{
	    		name.add(listco.get(i).getCommunity_name());
	    	}
	    }
	    else
	    {
	    	name=userService.getcommunitybyuserid(Integer.parseInt(userid));
	    }
	    //List<Community> listco=conservice.listservice(username);
	    List<Adopt> adopt=new ArrayList<Adopt>();
	    for(int i=0;i<name.size();i++){
	    	 List<Adopt> listdaod=userService.findaoptbycommunity(name.get(i));
	    	 for(int j=0;j<listdaod.size();j++)
	    	 {
	    		 adopt.add(listdaod.get(j));
	    	 }
	    }
	    List<Mycollection> collect=userService.findcollectbyid(Integer.parseInt(userid));
	    
	    ModelAndView model=new ModelAndView("personal");
	    model.addObject("likelist", com);
	    model.addObject("update", update);
	    model.addObject("adopt", adopt);
	    model.addObject("collect",collect);
	    return model;
	    
    }
    
    @RequestMapping(value="/checkaoptaccept")
    @ResponseBody
    public String checkaoptaccept(HttpServletRequest request, HttpSession session) throws NumberFormatException, SQLException{
    	String userid=(String)session.getAttribute("id");
    	String threadname=(String)request.getParameter("thread");
    	return userService.checkmyadopt(Integer.parseInt(userid),threadname);
    }
    
    @RequestMapping(value="/insertmyadopt")
    public ModelAndView insertmyaopt(HttpServletRequest request, HttpSession session) throws SQLException{
    	String userid=(String)session.getAttribute("id");
    	String threadname=(String)request.getParameter("thread");
    	Myadopt my=new Myadopt();
    	my.setUserid(Integer.parseInt(userid));
    	my.setThread(threadname);
    	userService.insertmyadopt(my);
    	ModelAndView model=new ModelAndView("redirect:/userprofile");
    	return model;
    }
    
    @RequestMapping(value="/searchmyadopt")
    @ResponseBody
    public List<String> searchtmyaopt(HttpServletRequest request, HttpSession session) throws NumberFormatException, SQLException{
    	String userid=(String)session.getAttribute("id");
    	
    	return userService.searchmyadopt(Integer.parseInt(userid));
    	
    }
    
    @RequestMapping(value="/showcollection")
    public ModelAndView showcollection(HttpServletRequest request, HttpSession session)
    {
    	ModelAndView model=new ModelAndView("C_collect");
        String threadname=(String)request.getParameter("threadname");
        String projectid=(String)request.getParameter("projectid");
        System.out.println("projectid"+projectid);
        model.addObject("threadname", threadname);
        model.addObject("projectid",projectid);
        return model;
    }
    
    @RequestMapping(value="/searchcollection")
    @ResponseBody
    public List<String> searchcollection(HttpServletRequest request, HttpSession session) throws SQLException{
    	String userid=(String)session.getAttribute("id");
    	String threadname=(String)request.getParameter("threadname");
    	
    	List<Mycollection> list=userService.searchcollection(Integer.parseInt(userid));
    	List<String> st=new ArrayList<String>();
    	for(int i=0;i<list.size();i++){
    		if(userService.checkcoexsit(list.get(i).getId(),threadname))
    		{
    		   st.add(list.get(i).getCollectname());
    		}
    			
    	}
    	return st;
    }
    @RequestMapping(value="/listcollect")
    @ResponseBody
    public List<String> seearchcollection(HttpServletRequest request, HttpSession session) throws SQLException{
    	String userid=(String)session.getAttribute("id");
    	//String threadname=(String)request.getParameter("threadname");
    	
    	List<Mycollection> list=userService.searchcollection(Integer.parseInt(userid));
    	List<String> st=new ArrayList<String>();
    	for(int i=0;i<list.size();i++){
    		st.add(list.get(i).getCollectname());
    			
    	}
    	return st;
    }
    
    @RequestMapping(value="/collectinsert",method=RequestMethod.POST)
    public ModelAndView collectinsert(HttpServletRequest request, HttpSession session) throws  SQLException{
    	String userid=(String)session.getAttribute("id");
    	String threadname=(String)request.getParameter("threadname");
    	String projectid=(String)request.getParameter("projectid");
    	String newname=(String)request.getParameter("newname");
    	String collection=(String)request.getParameter("collection");
    	if(newname==null)
    		newname="";
    	System.out.println(newname);
    	if(collection==null)
    		collection="";
    	if(newname.equals(""))
    	{   
    	    int collectid=userService.getidbycollection(Integer.parseInt(userid),collection);
    	    col_thread col=new col_thread();
    	    col.setCollectid(collectid);
    	    col.setThread(threadname);
    	    col.setProjectid(Integer.valueOf(projectid));
    		userService.insertcol_thread(col);
    		
    	}
    	else
    	{ 
    		Mycollection mycollection=new Mycollection();
    		mycollection.setUserid(Integer.parseInt(userid));
    		mycollection.setCollectname(newname);
    		userService.insertmycollection(mycollection);
    		int collectid=userService.getidbycollection(Integer.parseInt(userid),newname);
    	    col_thread col=new col_thread();
    	    col.setCollectid(collectid);
    	    col.setThread(threadname);
    	    col.setProjectid(Integer.valueOf(projectid));
    		userService.insertcol_thread(col);
    		
    		
    	}
    	ModelAndView model=new ModelAndView("C_collect");
    	model.addObject("threadname", threadname);
    	model.addObject("threadname",threadname);
    	model.addObject("message", threadname+"has been added into collection");
    	return model;
    	
    	
    }
    
    @RequestMapping(value="/mycollectthread")
    @ResponseBody
    public List<Combin> mycollectthread(HttpServletRequest request,HttpSession session) throws SQLException{
    	String collectid=(String)request.getParameter("collectid");
    	List<Combin> result=new ArrayList<Combin>();
    	List<col_thread> threadlist= userService.findthreadbycollect(collectid);
    	for(int i=0;i<threadlist.size();i++){
    		
    		Combin combin=new Combin();
    		combin.setThreadname(threadlist.get(i).getThread());
    		combin.setProjectid(threadlist.get(i).getProjectid());
    		//System.out.println(threadlist.get(i));
    		List<Project> ccc=userService.findprojectbythread(threadlist.get(i).getProjectid());
    		//System.out.println("ccc"+ccc.get(0));
    		combin.setProjectname(ccc.get(0).getProjectname());
    		combin.setCommunityname("");
    		result.add(combin);
    		
    	}
    	return result;
    	
    }
    
    /*@RequestMapping(value="/getsimilarthread")
    @ResponseBody
    public List<String> getsimilarthread(HttpServletRequest request,HttpSession session) throws SQLException{
    	List<String> result=new ArrayList<String>();
    	String threadname=(String)request.getParameter("threadname");
    	String list=(String)request.getParameter("more");
    	System.out.println(list);
    	int more=Integer.parseInt(list);
    	List<String> strinname=userService.findallthread();
        String[] name=new String[strinname.size()];
        double[] namevalue=new double[strinname.size()];
        for(int i=0;i<strinname.size();i++){
        	name[i]=strinname.get(i); 
        	if(strinname.get(i).equals(threadname))
        		   namevalue[i]=-1;
        	else   
        		namevalue[i]=userService.getsimilarthread(threadname, strinname.get(i));
        	
        }
        //System.out.println(userService.getsimilarthread(threadname, threadname));
     for(int i=0;i<namevalue.length;i++) {
    	 System.out.println(name[i]+" "+namevalue[i]);
     }  
     int listindex=0;
     
     while(listindex<namevalue.length-1){
    	 double maxinde=0;
    	 int finalindex=-1;
     for(int i=0;i<namevalue.length;i++){
    	 if(namevalue[i]>=maxinde){
    		 finalindex=i;
    		 maxinde=namevalue[i];
    	 }
     }
     System.out.println(finalindex);
     result.add(name[finalindex]);
     namevalue[finalindex]=-1;
     listindex++;
     }
     List<String> finalresult=new ArrayList<String>();
     for(int i=more;i<result.size()&&i<more+2;i++){
    	 finalresult.add(result.get(i));
    	 
     }
     
     return finalresult;
    }
   */
    @RequestMapping(value="/similarfindbar")
    public ModelAndView similarfindbar(HttpServletRequest request,HttpSession session) throws SQLException{
    	//List<Combin> result=new ArrayList<Combin>();
    	String threadname=(String)request.getParameter("threadname");
    	String projectid=(String)request.getParameter("projectid");
    	//com.similarity.GetSimilar gets=new GetSimilar();
    	//gets.getsimilar(projectid,threadname);
     List<Threadsimilar> projectthread=userService.findsimilar(Integer.valueOf(projectid),threadname);
     ModelAndView mode=new ModelAndView("similar");
     mode.addObject("threadlist",projectthread);
     return mode;
     
     
    
    }
    
    /*@RequestMapping(value="/similarfindbar2")
    public ModelAndView similarfindbar2(HttpServletRequest request,HttpSession session) throws SQLException{
    	//List<Combin> result=new ArrayList<Combin>();
    	String threadname=(String)request.getParameter("threadname");
    	String projectid=(String)request.getParameter("projectid");
    	
    	List<Projecthread> strinname=userService.findallthread();
        
        
        for(int i=0;i<strinname.size();i++){
        	if((strinname.get(i).getThreadfocus().equals(threadname))&&(strinname.get(i).getProjectid()==Integer.valueOf(projectid))) continue;
        	 boolean isexist=userService.isexsitsimilar(threadname,projectid,strinname.get(i).getThreadfocus(),strinname.get(i).getProjectid());
        	 if(!isexist)
        	 {Threadsimilar threadsimlar=new Threadsimilar();
        	 threadsimlar.setProjectidone(Integer.valueOf(projectid));
        	 threadsimlar.setProjectidtwo(strinname.get(i).getProjectid());
        	 threadsimlar.setThreadone(threadname);
        	 threadsimlar.setThreadtwo(strinname.get(i).getThreadfocus());
        	double score=userService.getsimilarthread(Integer.valueOf(projectid),threadname, strinname.get(i).getProjectid(),strinname.get(i).getThreadfocus());
        	threadsimlar.setScore(score);
        	userService.inserttosimilarthread(threadsimlar);}
        	 else{
        		 double score=userService.getsimilarthread(Integer.valueOf(projectid),threadname, strinname.get(i).getProjectid(),strinname.get(i).getThreadfocus());
        		 userService.updatesimilar(threadname,projectid,strinname.get(i).getThreadfocus(),strinname.get(i).getProjectid(),score);
        	 }
        
    	
        }
        for(int i=0;i<strinname.size();i++){
        	if((strinname.get(i).getThreadfocus().equals(threadname))&&(strinname.get(i).getProjectid()==Integer.valueOf(projectid))) continue;
        	 boolean isexist=userService.isexsitsimilar(strinname.get(i).getThreadfocus(),strinname.get(i).getProjectid(),threadname,projectid);
        	 if(!isexist)
        	 {Threadsimilar threadsimlar=new Threadsimilar();
        	 threadsimlar.setProjectidone(Integer.valueOf(projectid));
        	 threadsimlar.setProjectidtwo(strinname.get(i).getProjectid());
        	 threadsimlar.setThreadone(threadname);
        	 threadsimlar.setThreadtwo(strinname.get(i).getThreadfocus());
        	double score=userService.getsimilarthread(strinname.get(i).getThreadfocus(),Integer.valueOf(projectid),threadname, strinname.get(i).getProjectid(),);
        	threadsimlar.setScore(score);
        	userService.inserttosimilarthread(threadsimlar);}
        	 else{
        		 double score=userService.getsimilarthread(Integer.valueOf(projectid),threadname, strinname.get(i).getProjectid(),strinname.get(i).getThreadfocus());
        		 userService.updatesimilar(threadname,projectid,strinname.get(i).getThreadfocus(),strinname.get(i).getProjectid(),score);
        	 }
        
    	
        }
     List<Threadsimilar> projectthread=userService.findsimilar(Integer.valueOf(projectid),threadname);
     ModelAndView mode=new ModelAndView("similar");
     mode.addObject("threadlist",projectthread);
     return mode;
     
     
    
    }
    
    
    */
    
    
    @RequestMapping(value="/morefunction")
    @ResponseBody
    public List<String> morefunction(HttpServletRequest request,HttpSession session) throws SQLException{
    	//String threadname=(String)request.getParameter("threadname");
    	List<Projecthread> result=userService.findallthread();
    	String list=(String)request.getParameter("more");
    	int more=Integer.parseInt(list);
    	System.out.println(more);
    	List<String> lre=new ArrayList<String>();
    	System.out.println(result.get(0));
    	lre.add(result.get(more).getThreadfocus());
    	lre.add(result.get(more+1).getThreadfocus());
    	return lre;
    }
    
    @RequestMapping(value="/listcollectcommunity")
    @ResponseBody
    public List<String> listcommunity(HttpServletRequest request,HttpSession session) throws SQLException{
    	//String threadname=(String)request.getParameter("threadname");
    	 String username=(String)session.getAttribute("username");
    	 List<String> result=conservice.findcommunitybyusername(username);
    	 System.out.println("how mand"+result.size());
     	return conservice.findcommunitybyusername(username);
    }
    
    @RequestMapping(value="/addition")
    public ModelAndView addtion() throws SQLException{
    	ModelAndView model=new ModelAndView();
    	model.setViewName("addition");
       return model;
    }
    @RequestMapping(value="/addition",method=RequestMethod.POST)
    public ModelAndView addtion1(HttpSession session,HttpServletRequest request) throws SQLException{
    	//String[] threads=(String[])request.getParameterValues("threads");
    	String userid=(String)session.getAttribute("id");
    	String newname=(String)request.getParameter("collectname");
    	String dis=(String)request.getParameter("disc");
    	if(dis==null)
    		dis="";
    	Mycollection mycollection=new Mycollection();
		mycollection.setUserid(Integer.parseInt(userid));
		mycollection.setCollectname(newname);
		mycollection.setCollectdetail(dis);
		userService.insertmycollection(mycollection);
		/*int collectid=userService.getidbycollection(Integer.parseInt(userid),newname);
		for(int i=0;i<threads.length;i++)
	    {col_thread col=new col_thread();
	    col.setCollectid(collectid);
	    col.setThread(threads[i]);
		userService.insertcol_thread(col);}*/
    	ModelAndView model=new ModelAndView();
    	model.setViewName("redirect:/personal");
       return model;
    }
    
    @RequestMapping(value="/recomendcollect")
    public ModelAndView recocollectq(HttpServletRequest request,HttpSession session){
    	String collectid=(String)request.getParameter("collectid");
    	String collectname=(String)request.getParameter("collectname");
    	ModelAndView model=new ModelAndView();
    	model.setViewName("collectrecommend");
    	model.addObject("collectid", collectid);
    	model.addObject("collectname",collectname);
    	return model;
    	
    }
    
    
    
    
    @RequestMapping(value="/recommendcollect",method=RequestMethod.POST)
    public ModelAndView recocollect(HttpServletRequest request,HttpSession session) throws SQLException{
    	String userid=(String)session.getAttribute("id");
    	String threadname=(String)request.getParameter("collectname");
    	String collectid=(String)request.getParameter("collectid");
    	String reason=(String)request.getParameter("reason");
    	String community=(String) request.getParameter("community");
    	Adopt adopt=new Adopt();
    	adopt.setUserid(Integer.parseInt(userid));
    	adopt.setProjectid(Integer.valueOf(collectid));
    	adopt.setReason(reason);
    	adopt.setThreadname(threadname);
    	adopt.setCommunity(community);
    	userService.insertadopt(adopt);
    	
    	ModelAndView model=new ModelAndView("collectrecommend");
    	model.addObject("from",threadname+" has been recommended to '" +community+" 'community");
    	model.addObject("collectname", threadname);
    	model.addObject("collectid",collectid);
    	return model;
    }
    
    @RequestMapping(value="/findallthreadforcollection")
    @ResponseBody
    public List<Projecthread> findallthread(HttpSession session,HttpServletRequest request) throws SQLException{
    	List<Projecthread> strinname=userService.findallthread();
    	return strinname;
    }
    
    @RequestMapping(value="/contentbrowse")
    public ModelAndView findnote() throws SQLException{
    	ModelAndView model=new ModelAndView("Note_note");
    	List<Note_note> list=userService.browsecontent(0);
    	model.addObject("contentlist", list);
    	return model;
    	
    	
    }
    
    @RequestMapping(value="/getchildren")
    @ResponseBody
    public List<Note_note> findcurrilen(HttpServletRequest request) throws SQLException{
    	  String id=request.getParameter("id");
          return userService.browsecontent(Integer.valueOf(id));
    }

    @RequestMapping(value="/getchildrenproject")
    @ResponseBody
    public List<Project> findcurrilend(HttpServletRequest request) throws SQLException{
    	  String id=request.getParameter("id");
          return userService.getprojectbyarea(Integer.valueOf(id));
    }
    
    @RequestMapping(value="/getfirstlevel")
    @ResponseBody
    public List<Idea> getfirstlevel(HttpServletRequest request) throws SQLException
    {   String projectid=request.getParameter("projectid");
        String threadname=request.getParameter("threadname");
    	List<Integer> list=new ArrayList<Integer>();
    	List<Integer> temlist=userService.getfirstlevel(Integer.valueOf(projectid),threadname);
    	for(int id:temlist ){
    		if(!userService.is_exsit_tonoteid(id))
    			list.add(id);
    	}
    	List<Idea> result=userService.getNoteById(temlist,projectid,threadname);
    	return result;
    		
    }
    
  

	@RequestMapping(value="/getsecondlevel")
    @ResponseBody
    public List<Idea> getsecondlevel(HttpServletRequest request) throws SQLException{
		String projectid=request.getParameter("projectid");
        String threadname=request.getParameter("threadname");
    	String id=request.getParameter("id");
    	List<Integer> list=userService.getotherlevel(Integer.valueOf(id));
    	List<Idea> result=userService.getNoteById(list,projectid,threadname);
    	return result;
    }
	
	
	@RequestMapping(value="/test")
	public ModelAndView getNoteandTheirrelation(HttpServletRequest request){
		String projectid=(String)request.getParameter("projectid");
		String threadname=(String)request.getParameter("threadname");
		ModelAndView model=new ModelAndView("noterelation");
		model.addObject("projectid", projectid);
		model.addObject("threadname",threadname);
		return model;
	}
	
	
	@RequestMapping(value="/getName")
	@ResponseBody
	public List<String> getNamebyid(HttpServletRequest request){
		String projectid=(String)request.getParameter("projectid");
		String threadname=(String)request.getParameter("threadname");
		String noteid=(String)request.getParameter("id");
		List<Integer> authorids=userService.getauthorid(Integer.valueOf(noteid));
		System.out.println("enter " + authorids.size());
		List<String> result=new ArrayList<String>();
		for(int ids:authorids){
			//System.out.println("this is"+ids);
			//System.out.println("that is "+ userService.getnamebyid(ids));
			result.add(userService.getnamebyid(ids));
			
		}
		//System.out.println("enter " + result.size());
		return result;
	}
	
	@RequestMapping(value="/getthreadpage")
	public ModelAndView getthreadpage(HttpServletRequest request){
		String getvalue=(String)request.getParameter("getvalue");
		if(getvalue=="") getvalue="";
		String nidts=(String)request.getParameter("nidts");
		if(nidts==null) nidts="";
		ModelAndView model=new ModelAndView("thread");
		model.addObject("getvalue", getvalue);
		model.addObject("nidts",nidts);
		return model;
		
	}
	
	@RequestMapping(value="/getproject-thread")
	public ModelAndView getthreadbyprojectid(HttpServletRequest request) throws SQLException{
		String projectid=request.getParameter("projectid");
		Project project=userService.getprojectbyid(Integer.valueOf(projectid));
		String areaname= userService.findareanamebyid(project.getArea());
  	    project.setBigidea(areaname);
		List<Projecthread> listthread=conservice.searchthread(projectid);
		ModelAndView model=new ModelAndView("project-threadlist");
		model.addObject("project",project);
		model.addObject("threads",listthread);
		return model;
		
	}
    
    
}