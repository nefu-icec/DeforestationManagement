package edu.nefu.DeforeMgr.util;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.nefu.DeforeMgr.bean.AndroidCheckdataBuffer;
import edu.nefu.DeforeMgr.bean.AndroidUpdate;
import edu.nefu.DeforeMgr.bean.UnCheckList;
import edu.nefu.DeforeMgr.bean.UnCheckListItem;

/**
 * xml读写工具
 * @author lm2343635
 *
 */
public class XmlTool
{
	private String path;

	public XmlTool(String path) 
	{
		super();
		this.path = path;
	}
	
	public ArrayList<AndroidCheckdataBuffer> readCheckData() 
	{
		ArrayList<AndroidCheckdataBuffer> dataList=new ArrayList<AndroidCheckdataBuffer>();
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		try 
		{
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new File(path));
			NodeList nodeList = doc.getElementsByTagName("data");
			for (int i=0;i<nodeList.getLength();i++) 
			{
				Element node = (Element) nodeList.item(i);
				int checkListId =Integer.parseInt(node.getElementsByTagName("id").item(0)
						.getFirstChild().getNodeValue());
				int lb =Integer.parseInt(node.getElementsByTagName("lb").item(0)
						.getFirstChild().getNodeValue());
				int xb =Integer.parseInt( node.getElementsByTagName("xb").item(0)
						.getFirstChild().getNodeValue());
				String region = node.getElementsByTagName("region").item(0)
						.getFirstChild().getNodeValue();
				String tree = node.getElementsByTagName("tree").item(0)
						.getFirstChild().getNodeValue();
				double dgBest =Double.parseDouble(node.getElementsByTagName("dgBest").item(0)
						.getFirstChild().getNodeValue());
				double dgMiddle =Double.parseDouble( node.getElementsByTagName("dgMiddle").item(0)
						.getFirstChild().getNodeValue());
				double dgWorst = Double.parseDouble(node.getElementsByTagName("dgWorst").item(0)
						.getFirstChild().getNodeValue());
				double area = Double.parseDouble(node.getElementsByTagName("area").item(0)
						.getFirstChild().getNodeValue());
				double density = Double.parseDouble(node.getElementsByTagName("density").item(0)
						.getFirstChild().getNodeValue());
				dataList.add(new AndroidCheckdataBuffer(checkListId,lb, xb, region, tree, dgBest, dgMiddle, dgWorst, density, area));
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return dataList;
	}
	
	public void addNode(UnCheckListItem item)
	{
		Document doc=null;
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		try 
		{
			DocumentBuilder builder=factory.newDocumentBuilder();
			doc=builder.parse(new File(path));
			doc.normalize();
			Element root=doc.getDocumentElement();
			Node nodeList=doc.createElement("data");
			//创建验收id号结点
			Node id=doc.createElement("id");
			Node tmp0=doc.createTextNode("tmp0");
			tmp0.setNodeValue(String.valueOf(item.getCheckListId()));
			id.appendChild(tmp0);
			//创建林班结点
			Node lb=doc.createElement("lb");
			Node tmp1=doc.createTextNode("tmp1");
			tmp1.setNodeValue(item.getLb());
			lb.appendChild(tmp1);
			//创建小班结点
			Node xb=doc.createElement("xb");
			Node tmp2=doc.createTextNode("tmp2");
			tmp2.setNodeValue(item.getXb());
			xb.appendChild(tmp2);
			//创建时间结点
			Node date=doc.createElement("date");
			Node tmp3=doc.createTextNode("tmp3");
			tmp3.setNodeValue(item.getDate());
			date.appendChild(tmp3);
			//添加结点到nodeList
			nodeList.appendChild(id);
			nodeList.appendChild(lb);
			nodeList.appendChild(xb);
			nodeList.appendChild(date);
			//添加到根结点
			root.appendChild(nodeList);
			TransformerFactory tFactory=TransformerFactory.newInstance();
			Transformer transformer=tFactory.newTransformer();
			DOMSource source=new DOMSource(doc);
			StreamResult result=new StreamResult(new File(path));
			transformer.transform(source, result);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void createPushCheckList()
	{
		UnCheckList push=new UnCheckList();
		ArrayList<UnCheckListItem> items=push.getItems();
		for(UnCheckListItem item:items)
			addNode(item);
	}
	
	public void createUpdate(String serverIP) 
	{
		Document doc=null;
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		AndroidUpdate update=AndroidUpdate.getNewestUpdate();
		String aplUrl="http://"+serverIP+":8080/DeforestationManagement/AndroidUpdate/downloadNewest.jsp";
		try 
		{
			DocumentBuilder builder=factory.newDocumentBuilder();
			doc=builder.parse(new File(path));
			doc.normalize();
			Element root=doc.getDocumentElement();
			//创建验收version结点
			Node version=doc.createElement("version");
			Node tmp0=doc.createTextNode("tmp0");
			tmp0.setNodeValue(String.valueOf(update.getVersion()));
			version.appendChild(tmp0);
			//创建林班结点
			Node apkurl=doc.createElement("apkurl");
			Node tmp1=doc.createTextNode("tmp1");
			tmp1.setNodeValue(aplUrl);
			apkurl.appendChild(tmp1);
			//添加到根结点
			root.appendChild(version);
			root.appendChild(apkurl);
			TransformerFactory tFactory=TransformerFactory.newInstance();
			Transformer transformer=tFactory.newTransformer();
			DOMSource source=new DOMSource(doc);
			StreamResult result=new StreamResult(new File(path));
			transformer.transform(source, result);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		XmlTool tool=new XmlTool("D:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\DeforestationManagement\\xml\\2014_01_03 15_48_36.xml");
		ArrayList<AndroidCheckdataBuffer> lists=tool.readCheckData();
		for(AndroidCheckdataBuffer data:lists)
			System.out.println(data.getCheckListId()+" "+data.getLb()+" "+data.getXb());
	}
}