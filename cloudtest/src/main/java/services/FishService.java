package services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import conn.Connections;
import data.Fish;

@Path("/fishservice")
public class FishService {
	
	@Context
	HttpServletRequest request;
	
	@Context
	HttpServletResponse response;
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Path("/readfish")
	public void readFish() {
		ArrayList<Fish> list=new ArrayList<>();
		Connection conn=Connections.getConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet RS=stmt.executeQuery("select * from fish");
			while (RS.next()) {
				Fish fish=new Fish();
				fish.setId(RS.getInt("id"));
				fish.setBreed(RS.getString("breed"));
				fish.setWeight(RS.getString("weight"));
				fish.setLength(RS.getString("length"));
				fish.setPrice(RS.getString("price"));
				list.add(fish);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("fish is:" + list);
		request.setAttribute("fishlist", list);
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/showfish.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded") //Method can receive POSTed data from a html form
	@Path("/addfish")
	public void addFish(@FormParam("breed") String breed, @FormParam("weight") String weight, @FormParam("length") String length,@FormParam("price") String price) {

		weight=weight.replace(",", ".");

		ArrayList<Fish> list=new ArrayList<>();
		Connection conn=Connections.getConnection();
		try {
			System.out.println("trying to insert data!");
			PreparedStatement pstmt=conn.prepareStatement("insert into fish(breed, weight, length, price) values(?,?,?,?)");
			pstmt.setString(1, breed);
			pstmt.setString(2, weight);
			pstmt.setString(3, length);
			pstmt.setString(4, price);
			pstmt.execute();
			
			Statement stmt=conn.createStatement();
			ResultSet RS=stmt.executeQuery("select * from fish");
			while (RS.next()) {
				Fish fish=new Fish();
				fish.setId(RS.getInt("id"));
				fish.setBreed(RS.getString("breed"));
				fish.setWeight(RS.getString("weight"));
				fish.setLength(RS.getString("length"));
				fish.setPrice(RS.getString("price"));
				list.add(fish);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("fish is:" + list);
		request.setAttribute("fishlist", list);
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/addfish.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/deletefish/{id}")
	public void deleteFish(@PathParam("id") int id) {
		System.out.println("ID to del: " + id);
		Connection conn=Connections.getConnection();
		String sql = "DELETE FROM fish where id=?";
		try {
		  PreparedStatement pstmt = conn.prepareStatement(sql);
		  pstmt.setInt(1, id);
		  pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
