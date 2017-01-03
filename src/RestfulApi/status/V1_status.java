package RestfulApi.status;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import RestfulApi.dao.RestDao;

import java.sql.*;

/**
 * This is the root path for our restful api service
 * In the web.xml file, we specified that /api/* need to be in the URL to
 * get to this class.
 * 
 * We are versioning the class in the URL path.  This is the first version v1.
 * Example how to get to the root of this api resource:
 * http://localhost:8080/restfulapi/api/v1/status
 * 
 * @author Shantanu
 *
 */


@Path("/v1/status")
public class V1_status {
	
	private static final String api_version ="00.01.00";
	
	/**
	 * This method sits at the root of the api.  It will return the name
	 * of this api.
	 * 
	 * @return String - Title of the api
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p>Java Web Service</p>";
	}
	
	/**
	 * This method will return the version number of the api
	 * Note: this is nested one down from the root.  You will need to add version
	 * into the URL path.
	 * 
	 * Example:
	 * http://localhost:8080/restfulapi/api/v1/status/version
	 * 
	 * @return String - version number of the api
	 */
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return "<p>Version : </p>"+api_version;
	}
	
	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDatabaseStatus() throws Exception{
		Connection conn = null;
        PreparedStatement query = null;
        String myString = null;
        String returnString = null;
        
        try{
        	conn = RestDao.MysqlConn();
        	query = conn.prepareStatement("select current_date from dual");
        	ResultSet rs = query.executeQuery();
        	
        	while(rs.next()){
        		myString = rs.getString("current_date");
        	}
        	
        	query.close();
        	
        	returnString = "<p>Database Status</p>"+
        			"<p>Database current date : "+myString+"</p>";
        }catch(Exception e) {
        	e.printStackTrace();
        }
        finally{
        	
        }
        
        return returnString;
        		
	}
}
