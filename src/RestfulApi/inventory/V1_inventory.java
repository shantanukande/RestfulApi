package RestfulApi.inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.QueryParam;

import org.codehaus.jettison.json.JSONArray;

import RestfulApi.dao.RestDao;
import RestfulApi.utility.ToJSON;;

@Path("/v1/inventory")
public class V1_inventory {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String returnAllPcParts() throws Exception{
	
		Connection conn = null;
		PreparedStatement query = null;
        String returnString = null;
        
        
        try{
        	conn = RestDao.MysqlConn();
        	query = conn.prepareStatement("select * from OneOps.pc_parts");
        	ResultSet rs = query.executeQuery();
        	
        	ToJSON convertor = new ToJSON();
        	JSONArray json = new JSONArray();
        	
        	json = convertor.toJSONArray(rs);
        	query.close();
        	
        	returnString = json.toString();
        	 
        }
        catch(Exception e){
        	e.printStackTrace();
        }
        finally{
        	if(conn != null) conn.close();
        }
		
        return returnString;
	}

}
