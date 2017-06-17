package xcbean;
import java.sql.*;
import java.util.*;

public class MsgRecord extends SingleTable
{
	public static void main(String[] args)
	{
		
	}
	/* variable length argument */
	public static boolean systemMsg(int type, String... arg)
	{
		boolean bAns = false;
		XCDatabase xcDatabase = new XCDatabase();
		xcDatabase.connect();
		PreparedStatement pst;
		ResultSet rs;
		switch(type){
			case SYS_ADD_FRIEND:
				/* do not check */
				int user_id_send = Integer.parseInt(arg[0]);
				int user_id_recv = Integer.parseInt(arg[1]);
				XCUser user = XCUser.find(XCUser.FIND_BY_ID, user_id_send + "");
				String str = String.format("%s(%d)想要添加您为好友。", user.getUser_nick(), user_id_send);
				try{
					pst = xcDatabase.prepareStatement(String.format("INSERT INTO %s (msg_user_id_send, msg_user_id_recv, msg_type, msg_context) VALUES (?, ?, ?, ?)", tableName));
					pst.setInt(1, user_id_send);
					pst.setInt(2, user_id_recv);
					pst.setInt(3, SYS_ADD_FRIEND);
					pst.setString(4, str);
					int ans = pst.executeUpdate();
					if(ans > 0)
						bAns = true;
				}catch(SQLException e){
					System.out.println(e);
				}
				break;
		}
		xcDatabase.close();
		return bAns;
	}
	public static String getMsg(XCUser user)
	{
		StringBuilder ans = new StringBuilder("");
		XCDatabase xcDatabase = new XCDatabase();
		xcDatabase.connect();
		PreparedStatement pst;
		ResultSet rs;
		int msg_count = 0;
		List<String> list = new LinkedList<>();
		try{
			pst = xcDatabase.prepareStatement(String.format("SELECT msg_id, msg_user_id_send, msg_type, msg_context, msg_time FROM %s WHERE msg_user_id_recv = ? AND msg_read = 0", tableName));
			pst.setInt(1, user.getUser_id());
			rs = pst.executeQuery();
			while(rs.next()){
				int send_id = rs.getInt("msg_user_id_send");
				XCUser tmpUser = XCUser.find(XCUser.FIND_BY_ID, "" + send_id);
				ans.append(send_id + split + tmpUser.getUser_nick() + split + rs.getInt("msg_type") + split + rs.getString("msg_context") + split + rs.getTimestamp("msg_time") + line_split);
				list.add(rs.getInt("msg_id") + "");
			}
			if(list.size() > 0){
				StringBuilder str = new StringBuilder("(");
				int i = 0;
				str.append(list.get(i++));
				while(i < list.size())
					str.append(", " + list.get(i++));
				str.append(")");
				// System.out.println(str);
				pst = xcDatabase.prepareStatement(String.format("UPDATE %s SET msg_read = 1 WHERE msg_id IN " + str.toString(), tableName));
				pst.executeUpdate();
			}
		}catch(SQLException e){
			System.out.println(e);
		}
		xcDatabase.close();
		return ans.toString();
	}
	public static boolean sendMsg(int flag, XCUser user1, XCUser user2, String text)
	{
		boolean bAns = false;
		switch(flag){
			case FRIENT_MSG:
				MsgRecord record = new MsgRecord(user1.getUser_id(), user2.getUser_id(), FRIENT_MSG, text);
				if(record.update())
					bAns = true;
				break;
		}
		return bAns;
	}
	public MsgRecord()
	{

	}
	public MsgRecord(int aMsg_user_id_send, int aMsg_user_id_recv, int aMsg_type, String aMsg_context)
	{
		setMsg_user_id_send(aMsg_user_id_send);
		setMsg_user_id_recv(aMsg_user_id_recv);
		setMsg_type(aMsg_type);
		setMsg_context(aMsg_context);
	}



	public boolean update()
	{
		boolean bAns = false;
		xcDatabase.connect();
		PreparedStatement pst;
		/* can not modify msg */
		if(msg_id != -1)
			return bAns;
		try{
			pst = xcDatabase.prepareStatement(String.format("INSERT INTO %s (msg_user_id_send, msg_user_id_recv, msg_type, msg_context) VALUES (?, ?, ?, ?)", tableName));
			pst.setInt(1, msg_user_id_send);
			pst.setInt(2, msg_user_id_recv);
			pst.setInt(3, msg_type);
			pst.setString(4, msg_context);
			int ans = pst.executeUpdate();
			if(ans > 0)
				bAns = true;
		}catch(SQLException e){
			System.out.println(e);
		}
		return bAns;
	}
	public boolean delete()
	{
		return false;
	}

	public int getMsg_id()
	{
		return msg_id;
	}
	public void setMsg_user_id_send(int aMsg_user_id_send)
	{
		msg_user_id_send = aMsg_user_id_send;
	}
	public int getMsg_user_id_send()
	{
		return msg_user_id_send;
	}
	public void setMsg_user_id_recv(int aMsg_user_id_recv)
	{
		msg_user_id_recv = aMsg_user_id_recv;
	}
	public int getMsg_user_id_recv()
	{
		return msg_user_id_recv;
	}
	public void setMsg_type(int aMsg_type)
	{
		msg_type = aMsg_type;
	}
	public int getMsg_type()
	{
		return msg_type;
	}
	public void setMsg_context(String aMsg_context)
	{
		if(aMsg_context != null){
			msg_context = aMsg_context.substring(0, Math.min(aMsg_context.length(), 100));
		}
		
	}
	public String getMsg_context()
	{
		return msg_context;
	}
	public void setMsg_time(java.sql.Timestamp aMsg_time)
	{
		msg_time = aMsg_time;
	}
	public java.sql.Timestamp getMsg_time()
	{
		return msg_time;
	}
	public void setMsg_read(int aMsg_read)
	{
		msg_read = aMsg_read;
	}
	public int getMsg_read()
	{
		return msg_read;
	}
	public static final int GROUP_MSG = 0;
	public static final int FRIENT_MSG = 1;
	public static final int SYS_ADD_FRIEND = 2;


	private static final String tableName = "msg_records";
	private static final String primaryKey = "msg_id";

	private int msg_id = -1;
	private int msg_user_id_send = -1;
	private int msg_user_id_recv = -1;
	private int msg_type = -1;
	private String msg_context;
	private Timestamp msg_time;
	private int msg_read = -1;
}