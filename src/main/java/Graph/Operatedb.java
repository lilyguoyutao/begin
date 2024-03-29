package Graph;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.io.*;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.serial.SerialBlob;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Operatedb {

	private Statement DBConn = null;
	private String curdb = null;
	private sqls conobj = null;

	public Statement getC() {
		return this.DBConn;
	}

	public Operatedb(sqls sobj, String strname) {
		this.curdb = strname;
		DBConn = sobj.Connect(strname);
		this.conobj = sobj;
	}

	public void CloseCon() {
		try {
			if (this.DBConn != null) {
				this.DBConn.close();
			}
			if (this.conobj != null) {
				this.conobj.Close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get Records from single table in database
	 * 
	 * @param strtable
	 *            table name
	 * @param strcolumn
	 *            column name
	 * @param strconstraint
	 *            constraint
	 * @return record set
	 */
	public ResultSet GetRecordsFromDB(String strtable, String strcolumn, String strconstraint) {
		if (this.DBConn == null) {
			return null;
		}
		ResultSet rs = null;
		String strsql;

		if (strconstraint.equals("")) {
			strsql = "SELECT " + strcolumn + " FROM " + "`" + strtable + "`";
		} else {
			strsql = "SELECT " + strcolumn + " FROM " + "`" + strtable + "`" + "WHERE " + strconstraint;
		}

		try {
			rs = this.DBConn.executeQuery(strsql);
		} catch (SQLException e) {
			System.out.println("GetRecordsFromDB error");
			e.printStackTrace();
		}

		return rs;
	}

	/**
	 * Get multiple Records from single table in database
	 * 
	 * @param strtable
	 *            table name
	 * @param String[]
	 *            column names
	 * @param strconstraint
	 *            constraint
	 * @return record set
	 */
	public ResultSet GetMultipleRecordsFromDB(String strtable, String strcolumns[], String strconstraint) {
		if (this.DBConn == null) {
			return null;
		}

		ResultSet rs = null;
		String strsql;
		String str = "";

		for (int i = 0; i < strcolumns.length; i++) {
			if (i == (strcolumns.length - 1)) {
				str += strcolumns[i];
			} else {
				str += strcolumns[i] + ",";
			}
		}

		if (strconstraint.equals("")) {
			strsql = "SELECT " + str + " FROM " + "`" + strtable + "`";
			// System.out.println(strsql);
		} else {
			strsql = "SELECT " + str + " FROM " + "`" + strtable + "`" + "WHERE " + strconstraint;
			// System.out.println(strsql);
		}

		try {
			rs = this.DBConn.executeQuery(strsql);
		} catch (SQLException e) {
			System.out.println("GetRecordsFromDB error");
			e.printStackTrace();
		}

		return rs;
	}

	/**
	 * Get Records from multiple tables in database
	 * 
	 * @param strtable
	 *            table names
	 * @param strcolumn
	 *            column name
	 * @param strconstraint
	 *            constraint
	 * @param num
	 *            table number
	 * @return record set
	 */
	public ResultSet MulGetRecordsFromDB(String[] strtable, String strcolumn, String strconstraint, int num) {
		ResultSet rs = null;
		String strsql;
		String strname = null;
		strname = "`" + strtable[0] + "`";
		// get the sql
		for (int i = 1; i < num; i++) {
			if (i < num - 1) {
				strname = strname + ", " + "`" + strtable[i] + "` ";
			} else if (i == num - 1) {
				strname = strname + ", " + "`" + strtable[i] + "` ";
			}
		}
		if (strconstraint == null) {
			strsql = "SELECT " + strcolumn + " FROM " + strname;
		} else {
			strsql = "SELECT " + strcolumn + " FROM " + strname + " WHERE " + strconstraint;
		}

		try {
			System.out.println("**********************************************");
			System.out.println(strsql);
			System.out.println("**********************************************");
			rs = this.DBConn.executeQuery(strsql);
		} catch (SQLException e) {
			System.out.println("GetRecordsFromDB error");
			e.printStackTrace();
		}

		return rs;
	}


	/**
	 * Get all notes contained in view chosen by user
	 * 
	 * @param strfrtime
	 *            from time
	 * @param strtotime
	 *            to time
	 * @param strview
	 *            view title
	 * @param strword1
	 *            key word in content
	 * @param strword2
	 *            key word in title
	 * @param strrange
	 *            key word range
	 * @return record set
	 */
	public ResultSet GetNotes(String strfrtime, String strtotime, String[] strtitle, String strword1, String strword2,
			String strrange, String match) {
		// Get the notes which are contained by the view user chosen
		ResultSet rs = null;
		try {
			String strsql = null;
			String viewsql = null, keysql = "", timesql = null;
			boolean ifall = false;
			String[] viewtitle = new String[strtitle.length];

			viewsql = "(view_table.title='";
			for (int i = 0; i < strtitle.length; i++) {
				viewtitle[i] = strtitle[i].replace("'", "''");
				if (viewtitle[i].equals("all views")) {
					ifall = true;
					break;
				}
				if (i < strtitle.length - 1) {
					viewsql = viewsql + viewtitle[i] + "' or view_table.title= '";
				} else {
					viewsql = viewsql + viewtitle[i] + "'";
				}
			}

			viewsql += ")";

			if (match.contentEquals("exact")) {
				if (strrange.equals("anywhere")) {
					keysql = " AND (note_table.notecontent LIKE '%" + strword1 + "%'"
							+ "OR note_table.notetitle LIKE '%" + strword2 + "%')";
				} else if (strrange.equals("note title")) {
					keysql = " AND note_table.notetitle LIKE '%" + strword2 + "%'";
				} else if (strrange.equals("note content")) {
					keysql = " AND note_table.notecontent LIKE '%" + strword1 + "%'";
				}
			}

			if (!strfrtime.equals("earliest") && !strtotime.equals("latest")) {
				timesql = " AND note_table.createtime >'" + strfrtime + "'" + " AND note_table.createtime < '"
						+ strtotime + "'";
			} else if (strfrtime.equals("earliest") && strtotime.equals("latest")) {
				timesql = " ";
			} else if (strfrtime.equals("earliest") && !strtotime.equals("latest")) {
				timesql = " AND note_table.createtime <'" + strtotime + "'";
			} else if (!strfrtime.equals("earliest") && strtotime.equals("latest")) {
				timesql = " AND note_table.createtime > '" + strfrtime + "'";
			}
			

			if (!ifall) {
				strsql = "SELECT note_table.notetitle,view_table.title,DATE(note_table.createtime), note_table.noteid,SUBSTRING_INDEX(note_table.notecontent, ' ', 20),note_table.notecontent FROM `note_table` ,view_note, view_table WHERE "
						+ viewsql + " AND view_table.idview=view_note.viewid AND note_table.noteid=view_note.noteid"
						+ timesql + keysql + ";";

			} else if (ifall) {

				strsql = "SELECT note_table.notetitle,view_table.title,DATE(note_table.createtime), note_table.noteid,SUBSTRING_INDEX(note_table.notecontent, ' ', 20),note_table.notecontent "
						+ "FROM note_table ,view_table,view_note"
						+ " WHERE view_table.idview = view_note.viewid AND note_table.noteid = view_note.noteid"
						+ timesql + keysql + ";";

			}
			rs = this.DBConn.executeQuery(strsql);

		} catch (SQLException e) {
			System.out.println("error:when get the notes which are contained by the view user chosen");
			e.printStackTrace();
		}

		return rs;

	}

	public CachedRowSet GetStemmedNotes(String strfrtime, String strtotime, String[] strtitle, String strword1,
			String strword2, String strrange) throws SQLException {
		// Get the notes which are contained by the view user chosen
		ResultSet rs = null;
		try {
			String strsql = null;
			String viewsql = null, keysql = "", timesql = null;
			boolean ifall = false;
			String[] viewtitle = new String[strtitle.length];

			viewsql = "(view_table.title='";
			for (int i = 0; i < strtitle.length; i++) {
				viewtitle[i] = strtitle[i].replace("'", "''");
				if (viewtitle[i].equals("all views")) {
					ifall = true;
					break;
				}
				if (i < strtitle.length - 1) {
					viewsql = viewsql + viewtitle[i] + "' or view_table.title= '";
				} else {
					viewsql = viewsql + viewtitle[i] + "'";
				}

			}

			viewsql += ")";

			if (!strfrtime.equals("earliest") && !strtotime.equals("latest")) {
				timesql = " AND note_table.createtime >'" + strfrtime + "'" + " AND note_table.createtime < '"
						+ strtotime + "'";
			} else if (strfrtime.equals("earliest") && strtotime.equals("latest")) {
				timesql = " ";
			} else if (strfrtime.equals("earliest") && !strtotime.equals("latest")) {
				timesql = " AND note_table.createtime <'" + strtotime + "'";
			} else if (!strfrtime.equals("earliest") && strtotime.equals("latest")) {
				timesql = " AND note_table.createtime > '" + strfrtime + "'";
			}

			if (!ifall) {
				strsql = "SELECT note_table.notetitle,view_table.title,DATE(note_table.createtime), note_table.noteid,SUBSTRING_INDEX(note_table.notecontent, ' ', 20),note_table.notecontent FROM `note_table` ,view_note, view_table WHERE "
						+ viewsql + " AND view_table.idview=view_note.viewid AND note_table.noteid=view_note.noteid"
						+ timesql + keysql + ";";

			} else if (ifall) {

				strsql = "SELECT note_table.notetitle,view_table.title,DATE(note_table.createtime), note_table.noteid,SUBSTRING_INDEX(note_table.notecontent, ' ', 20),note_table.notecontent "
						+ "FROM note_table ,view_table,view_note"
						+ " WHERE view_table.idview = view_note.viewid AND note_table.noteid = view_note.noteid"
						+ timesql + keysql + ";";
			}
			rs = this.DBConn.executeQuery(strsql);

		} catch (SQLException e) {
			System.out.println("error:when get the notes which are contained by the view user chosen");
			e.printStackTrace();
		}

		// Create CacheRowset of Resultset to filter and stem data
		CachedRowSet crs = new CachedRowSetImpl();
		crs.populate(rs);
	
		if (crs != null) {
			while (crs.next()) {
				System.out.println("Inside loop" + crs.getString(1));
			}
		}
		return crs;

	}

	/**
	 * Get authors for the note
	 *
	 * @param noteid
	 * @return
	 */
	public List<String> getMultipleAuthors(String noteid) {

		List<String> authorList = new ArrayList<String>();
		ResultSet rs = null;
		String strsql = "select firstname,lastname from publish_author_note,publish_author_table where noteid=" + noteid
				+ " and publish_author_note.authorid=publish_author_table.authorid";

		try {
			rs = this.DBConn.executeQuery(strsql);

			while (rs.next()) {
				String name = rs.getString(1) + " " + rs.getString(2);
				authorList.add(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (authorList.size() == 0) {
			authorList.add(this.addUnknownAuthor(noteid));
		}
		return authorList;
	}

	/**
	 * Get unknown author id from the database
	 * 
	 * @return
	 */
	public List<String> getUnknownAuthorInfo() {
		List<String> unknownAuthorList = new ArrayList<String>();
		ResultSet rs = null;
		String strsql = "select firstname,lastname from author_table where authorid= 99999999";
		try {
			rs = this.DBConn.executeQuery(strsql);

			while (rs.next()) {
				unknownAuthorList.add(rs.getString(1));
				unknownAuthorList.add(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return unknownAuthorList;
	}

	/**
	 * Add unknown author for notes with no author
	 *
	 * @param noteid
	 */
	public String addUnknownAuthor(String noteid) {
		List<String> unknownAuthorInfo = this.getUnknownAuthorInfo();
		String authorFirstName = unknownAuthorInfo.get(0);
		String authorLastName = unknownAuthorInfo.get(1);
		String name = authorFirstName + " " + authorLastName;
		int authorid = 99999999;

		PreparedStatement stmt;
		try {
			stmt = this.DBConn.getConnection()
					.prepareStatement("insert into author_note (authorid, noteid) " + "values (?, ?)");
			stmt.setInt(1, authorid);
			stmt.setString(2, noteid);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("[error] Failed to ad anonymous author for " + noteid);
			e.printStackTrace();
			name = "";
		}
		return name;
	}
}
