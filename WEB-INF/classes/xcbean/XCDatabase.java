package xcbean;

import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class XCDatabase
{
	/* use for test */
	public static void main(String[] args)
	{
		XCDatabase xcdb = new XCDatabase();	
		xcdb.connect();
		xcdb.close();
	}
	public boolean connect()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(Exception e){
			System.out.println(e);
		}
		String uri = "jdbc:mysql://127.0.0.1/onlinechat";
		String user = "root";
		String password = "m03s05I32dVix5HDIze9";

		try{
			con = DriverManager.getConnection(uri, user, password);
			return true;
		}catch(Exception e){
			System.out.println(e);
			return false;
		}
		
	}
	public PreparedStatement prepareStatement(String s)
	{
		try{
			return con.prepareStatement(s, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		}catch(SQLException e){
			return null;
		}
	}
	public String[][] parseRS(ResultSet rs)
	{
		String[][] ans = null;
		try{
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			// String[] columnName = new String[columnCount];
			rs.last();
			int rowCount = rs.getRow();
			ans = new String[rowCount][columnCount];
			rs.beforeFirst();
			int i = 0;
			while(rs.next()){
				for(int j = 0; j < columnCount; ++j)
					ans[i][j] = rs.getString(j + 1);
				++i;
			}
		}catch(SQLException e){
			return null;
		}
		return ans;
	}

	public boolean close()
	{
		if(con != null){
			try{
				con.close();	
			}catch(SQLException e){
				return false;
			}
			con = null;
			return true;
		}
		return false;
	}
	public String MD5(String sourceStr)
	{
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            String salt = "ZwIKEMdRQkMolEnHKxfIMNTG4j1mkYR5";
            md.update(sourceStr.concat(salt).getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
            // System.out.println("MD5(" + sourceStr + ",32) = " + result);
            // System.out.println("MD5(" + sourceStr + ",16) = " + buf.toString().substring(8, 24));
            return result;
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
}