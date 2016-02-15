package edu.nefu.DeforeMgr.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nefu.DeforeMgr.bean.Answer;
import edu.nefu.DeforeMgr.bean.Question;
import edu.nefu.DeforeMgr.bean.User;
import edu.nefu.DeforeMgr.util.MyDate;

@WebServlet("/MessageBoardServlet")
public class MessageBoardServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private String task;
       
    public MessageBoardServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		task=request.getParameter("task");
		switch (task) 
		{
		case "deleteQuestion":
			deleteQuestion(request,response);
			break;
		case "deleteAnswer":
			deleteAnswer(request,response);
		default:
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		task=request.getParameter("task");
		switch (task)
		{
		case "question":
			addQuestion(request,response);
			break;
		case "answer":
			addAnswer(request,response);
			break;
		default:
			break;
		}
	}

	private void addQuestion(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		request.setCharacterEncoding("gb2312");
		HttpSession session=request.getSession(); 
		String question=request.getParameter("question");
		question=new String(question.getBytes( "ISO8859-1"), "GB2312"); 
		User user=(User)session.getAttribute("user");
		String qAuthor=user.getUname();
		MyDate qDate=new MyDate();
		new Question(question, qAuthor, qDate).insertQuestion();
		response.sendRedirect("MessageBoard/questionList.jsp");
	}
	

	private void addAnswer(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		request.setCharacterEncoding("gb2312");
		HttpSession session=request.getSession(); 
		String sqid=request.getParameter("qid");
		int qid=Integer.parseInt(sqid);
		String answer=request.getParameter("answer");
		answer=new String(answer.getBytes( "ISO8859-1"), "GB2312"); 
		User user=(User)session.getAttribute("user");
		String aAuthor=user.getUname();
		MyDate aDate=new MyDate();
		new Answer(answer, qid, aAuthor, aDate).insertAnswer();
		response.sendRedirect("MessageBoard/answerList.jsp?qid="+qid);
	}

	private void deleteQuestion(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		String sqid=request.getParameter("qid");
		int qid=Integer.parseInt(sqid);
		Question.deleteQuestion(qid);
		response.sendRedirect("MessageBoard/manageMsgBrd.jsp");
	}
	
	private void deleteAnswer(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		String said=request.getParameter("aid");
		String qid=request.getParameter("qid");
		int aid=Integer.parseInt(said);
		Answer.deleteAnswer(aid);
		response.sendRedirect("MessageBoard/manageAnswer.jsp?qid="+qid);
	}

}
