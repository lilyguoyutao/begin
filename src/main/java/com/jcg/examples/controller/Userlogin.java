package com.jcg.examples.controller;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jcg.examples.service.UserService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class Userlogin {
	
	@Autowired
	private UserService userservice;
	
	
	
	@RequestMapping(value="/userlogin", method=RequestMethod.GET)
	public ModelAndView userlogin()
	{
		ModelAndView model=new ModelAndView();
		model.setViewName("userlogin");
		return model;
	}
	
	
	@RequestMapping(value="/userlogin",method=RequestMethod.POST)
	public ModelAndView userlogin2(HttpServletRequest request,HttpServletResponse response) throws SQLException{
		ModelAndView model=new ModelAndView();
		HttpSession session = request.getSession();
		String username=(String)request.getParameter("username");
		if(username==null)
			username="";
		String password=(String)request.getParameter("password");
		if(password==null)
			password="";
		String type=(String)request.getParameter("type");
		if(type==null)
			type="";
		String message="";
		if(username=="")
			message="username should not be empty";
		else if(password=="")
			message="password should not be empty";
		else if(type=="")
			message="user type should not be empty";
		else
		{
		if(userservice.checkUserpass_service(username, password,type)>0){
			if(type.trim().equals("teacher"))
			   model.setViewName("redirect:/listcomname1");
			else
		       model.setViewName("redirect:/listcomname2");		
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			session.setAttribute("iflogin", "true");
			session.setAttribute("id", String.valueOf(userservice.checkUserpass_service(username, password,type)));
			session.setAttribute("type", type);
			message="";
			
		}
		else
		{message="account not exsit";}
		}
		model.addObject("message", message);
		model.addObject("username", username);
		return model;
	}
	
	@RequestMapping(value="/userlogintest",method=RequestMethod.POST)
	public ModelAndView userlogin3(HttpServletRequest request,HttpServletResponse response) throws SQLException{
		ModelAndView model=new ModelAndView();
		HttpSession session = request.getSession();
		String username=(String)request.getParameter("username");
		if(username==null)
			username="";
		String password=(String)request.getParameter("password");
		if(password==null)
			password="";
		ClientConfig config = new ClientConfig();

	    Client client = (Client) ClientBuilder.newClient(config);
        URI uri=UriBuilder.fromUri("http://tccl.rit.albany.edu:8080/ITM2Dev3/").build();
	    WebTarget target = client.target(uri);


	  /*  System.out.println(target.path("rest").
	            path("UserService/login_get").queryParam("username", "tester")
	    		.queryParam("password", "test")
	    		.queryParam("community", "test").
	            request().
	            accept(MediaType.APPLICATION_JSON).get(String.class));*/
	    
	    
	   Form form =new Form();
	    form.param("username", username);
	    form.param("password",password);
	    Response response2 = target.path("rest/UserService/getUserInfo").request().post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED),Response.class);
	   // String object=target.path("rest/UserService/getUserInfo").request().post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED),String.class);
	    System.out.println(response2);
	    String obje=target.path("rest/UserService/getUserInfo").request().post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED),String.class);
	    System.out.println(obje);
	    JSONObject ob=JSONObject.fromObject(obje);
	    //JSONArray arr=new JSONArray();
	    String st=ob.getString("status");
	    if(st.equals("Failed"))
	    {
	    	model.setViewName("userlogin");
	    	model.addObject("message", "account not exist");
	    	return model;
	    }
	    String arr=ob.getString("msg");
	    System.out.println(arr);
	    JSONArray jss=JSONArray.fromObject(arr);
	    
	    int size=jss.size();
	    if(size==0){
	    	model.setViewName("userLogin");
	    	model.addObject("message", "account not exist");
	    }else{
	    	List<String> listcom=new ArrayList<String>();
	    	String idd="";
	    	String firstname="";
	    	String lastname="";
	    	for(int i=0;i<size;i++){
	    		JSONObject obs=(JSONObject)jss.get(i);
	    		
	    		System.out.println(obs.getString("localDatabases").equals("\"null\""));
	    		
	    		if(!obs.getString("localDatabases").equals("\"null\""))
	    		    {listcom.add(obs.getString("localDatabases"));
	    		    
	    		    }
	    		idd=obs.getString("id");
	    		firstname=obs.getString("firstName");
	    		lastname=obs.getString("lastName");
	    	}
	    	session.setAttribute("username", username);
			session.setAttribute("password", password);
			session.setAttribute("firstname", firstname);
			session.setAttribute("lastname", lastname);
			session.setAttribute("iflogin", "true");
			session.setAttribute("id", idd);
			session.setAttribute("type", "teacher");
			session.setAttribute("mycommunity", listcom);
			model.setViewName("teacher");
	    }
	    return model;
	    

} 
	}
