package services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

@Path("/connservice")
public class ConnService {
	
	@Context
	HttpServletRequest request;
	
	@Context
	HttpServletResponse response;
	
//	Will print out connection properties from appengine-web.xml
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Path("/printconnection")
	public void printConn() {		
		Map<String, String> map = new HashMap<>();
		
		map.put("drivername", System.getProperty("drivername"));
		map.put("databasename", System.getProperty("databasename"));
		map.put("googleusername", System.getProperty("googleusername"));
		map.put("localusername", System.getProperty("localusername"));
		map.put("socketfactory", System.getProperty("socketfactory"));
		map.put("servertimezone", System.getProperty("servertimezone"));
		map.put("cloudsqlinstance", System.getProperty("cloudsqlinstance"));
		map.put("usessl", System.getProperty("usessl"));
		
		System.out.println("system connection map:" + map);
		request.setAttribute("map", map);
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/showconnection.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	Will print out all system properties including connection properties from appengine-web.xml
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Path("/printproperties")
	public void printProp() {		
		Map<String, String> map = new HashMap<>();
		
		Properties p = System.getProperties();	
		Enumeration keys = p.keys();
		while (keys.hasMoreElements()) {
		    String key = (String)keys.nextElement();
		    String value = (String)p.get(key);
		    
		    map.put(key, value);
		    System.out.println(key + ": " + value);
		}
	
		System.out.println("system property map:" + map);
		request.setAttribute("map", map);
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/showconnection.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
