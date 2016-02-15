package edu.nefu.DeforeMgr.bean;

import java.sql.ResultSet;
import java.util.ArrayList;

import edu.nefu.DeforeMgr.util.MyDate;

public class Question 
{
	private int qid;
	private String question;
	private String qAuthor;
	private MyDate qDate;
	
	public int getQid() {
		return qid;
	}

	public void setQid(int qid) {
		this.qid = qid;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getqAuthor() {
		return qAuthor;
	}

	public void setqAuthor(String qAuthor) {
		this.qAuthor = qAuthor;
	}
	
	public MyDate getqDate() {
		return qDate;
	}

	public void setqDate(MyDate qDate) {
		this.qDate = qDate;
	}

	public Question(int qid, String question, String qAuthor, MyDate qDate) {
		super();
		this.qid = qid;
		this.question = question;
		this.qAuthor = qAuthor;
		this.qDate = qDate;
	}

	public Question(String question, String qAuthor, MyDate qDate) {
		super();
		this.question = question;
		this.qAuthor = qAuthor;
		this.qDate = qDate;
	}

	public Question() {
		super();
	}
	
	public void insertQuestion()
	{
		String insert="insert into question(question,qAuthor,qDate) values('"+question+"','"+qAuthor+"','"+qDate.getStringDate()+"')";
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
	
	public static ArrayList<Question> getQuestion(String where)
	{
		ArrayList<Question> questionList=new ArrayList<Question>();
		if(where==null)
			where="";
		String select="select * from question "+where;
		try
		{
			ConnectDatabase connectDatabase=new ConnectDatabase();
			ResultSet rs=connectDatabase.exeQuery(select);
			while(rs.next())
				questionList.add(new Question(Integer.parseInt(rs.getString("qid")),
						rs.getString("question"),rs.getString("qAuthor"),new MyDate(rs.getString("qDate"))));
			connectDatabase.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return questionList;
	}
	
	public static ArrayList<Question> getShowQuestion(int showNumber)
	{
		ArrayList<Question> questionList=new ArrayList<Question>();
		String select="select * from question ";
		try
		{
			ConnectDatabase connectDatabase=new ConnectDatabase();
			ResultSet rs=connectDatabase.exeQuery(select);
			int n=0;
			while(rs.next()&&n<showNumber)
			{
				questionList.add(new Question(Integer.parseInt(rs.getString("qid")),
						rs.getString("question"),rs.getString("qAuthor"),new MyDate(rs.getString("qDate"))));
				n++;
			}	
			connectDatabase.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return questionList;
	}
	
	public static Question getQuestionById(int qid)
	{
		String where=" where qid="+qid;
		ArrayList<Question> questionList=getQuestion(where);
		Question question=new Question();
		if(!questionList.isEmpty())
			question=questionList.get(0);
		return question;
	}
	
	public static int deleteQuestion(int qid)
	{
		String delete="delete from question where qid="+qid;
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
