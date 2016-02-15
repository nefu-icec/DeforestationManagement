package edu.nefu.DeforeMgr.bean;

import java.sql.ResultSet;
import java.util.ArrayList;

import edu.nefu.DeforeMgr.util.MyDate;

public class Answer 
{
	private int aid;
	private String answer;
	private int qid;
	private String aAuthor;
	private MyDate aDate;
	
	public int getAid() {
		return aid;
	}
	
	public void setAid(int aid) {
		this.aid = aid;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public int getQid() {
		return qid;
	}

	public void setQid(int qid) {
		this.qid = qid;
	}
	
	public String getaAuthor() {
		return aAuthor;
	}
	
	public void setaAuthor(String aAuthor) {
		this.aAuthor = aAuthor;
	}
	
	public MyDate getaDate() {
		return aDate;
	}

	public Answer(String answer, int qid, String aAuthor, MyDate aDate) {
		super();
		this.answer = answer;
		this.qid = qid;
		this.aAuthor = aAuthor;
		this.aDate = aDate;
	}

	public Answer(int aid, String answer, int qid, String aAuthor, MyDate aDate) {
		super();
		this.aid = aid;
		this.answer = answer;
		this.qid = qid;
		this.aAuthor = aAuthor;
		this.aDate = aDate;
	}

	public void setaDate(MyDate aDate) {
		this.aDate = aDate;
	}

	public Answer() {
		super();
	}
	
	public void insertAnswer()
	{
		String insert="insert into answer(answer,qid,aAuthor,aDate) values('"+answer+"',"+qid+",'"+aAuthor+"','"+aDate.getStringDate()+"')";
		try 
		{
			ConnectDatabase connectDatabase=new ConnectDatabase();
			connectDatabase.exeUpdate(insert);
			connectDatabase.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Answer> getAnswerByQid(int qid)
	{
		ArrayList<Answer> answers= new ArrayList<Answer>();
		String select="select * from answer where qid="+qid+" order by aDate asc";
		try 
		{
			ConnectDatabase connectDatabase=new ConnectDatabase();
			ResultSet rs=connectDatabase.exeQuery(select);
			while(rs.next())
				answers.add(new Answer(Integer.parseInt(rs.getString("aid")),
														rs.getString("answer"),
														Integer.parseInt(rs.getString("qid")),
														rs.getString("aAuthor"), 
														new MyDate(rs.getString("aDate"))));
			connectDatabase.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return answers;
	}

	public static int deleteAnswer(int aid) 
	{
		String delete="delete from answer where aid="+aid;
		int count=0;
		try 
		{
			ConnectDatabase connectDatabase=new ConnectDatabase();
			count=connectDatabase.exeUpdate(delete);
			connectDatabase.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return count;
	}
	
}
