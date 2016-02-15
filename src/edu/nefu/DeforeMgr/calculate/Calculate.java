package edu.nefu.DeforeMgr.calculate;

import edu.nefu.DeforeMgr.util.NameTransform;

public class Calculate 
{
	public static double CalV(String region,String tree,double dgBest,double dgMiddle,double dgWorst,double density,double area)
	{
		double vBest=0;
		double vMiddle=0;
		double vWorst=0;
		switch(region)
		{
		case "Common"://ȫʡͨ��
			switch (tree) 
			{
			case "hs"://1 ����
				vBest=Common.hs(dgBest);
				vMiddle=Common.hs(dgMiddle);
				vWorst=Common.hs(dgWorst);
				break;
			case "zzs"://2 ������
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "ls"://3 ��ɼ
				vBest=Common.ls(dgBest);
				vMiddle=Common.ls(dgMiddle);
				vWorst=Common.ls(dgWorst);
				break;
			case "cs"://4 ����
				vBest=Common.cs(dgBest);
				vMiddle=Common.cs(dgMiddle);
				vWorst=Common.cs(dgWorst);
				break;
			case "ys"://5 ��ɼ
				vBest=Common.ys(dgBest);
				vMiddle=Common.ys(dgMiddle);
				vWorst=Common.ys(dgWorst);
				break;
			case "qy"://6 ����
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "rgxhy"://7 �˹�С����
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			default:
				break;
			}
			break;
		case "Njly"://�۽�����
			switch (tree) 
			{
			case "lys"://1 ��Ҷ��
				vBest=Njly.lys(dgBest);
				vMiddle=Njly.lys(dgMiddle);
				vWorst=Njly.lys(dgWorst);
				break;
			case "hh"://2 ����
				vBest=Njly.hh(dgBest);
				vMiddle=Njly.hh(dgMiddle);
				vWorst=Njly.hh(dgWorst);
				break;
			case "zs"://3 ����
				vBest=Njly.zs(dgBest);
				vMiddle=Njly.zs(dgMiddle);
				vWorst=Njly.zs(dgWorst);
				break;
			case "bh"://4 ����
				vBest=Njly.bh(dgBest);
				vMiddle=Njly.bh(dgMiddle);
				vWorst=Njly.bh(dgWorst);
				break;
			case "sy"://5 ɽ��
				vBest=Njly.sy(dgBest);
				vMiddle=Njly.sy(dgMiddle);
				vWorst=Njly.sy(dgWorst);
				break;
			default:
				break;
			}
			break;
		case "Rglys"://�˹���Ҷ��
			switch (tree) {
			case "hljdb"://1 ������ʡ��������
				vBest=Rglys.hljdb(dgBest);
				vMiddle=Rglys.hljdb(dgMiddle);
				vWorst=Rglys.hljdb(dgWorst);
				break;
			case "hljnb"://2  ������ʡ�ϲ�����
				vBest=Rglys.hljnb(dgBest);
				vMiddle=Rglys.hljnb(dgMiddle);
				vWorst=Rglys.hljnb(dgWorst);
				break;
			case "hljbb"://3 ������ʡ��������
				vBest=Rglys.hljbb(dgBest);
				vMiddle=Rglys.hljbb(dgMiddle);
				vWorst=Rglys.hljbb(dgWorst);
				break;
			default:
				break;
			}
			break;
		case "Rgyl"://�˹�����
			switch (tree) 
			{
			case "ljfyxzdy"://1 ��������ԣ���ж���
				vBest=Rgyl.ljfyxzdy(dgBest);
				vMiddle=Rgyl.ljfyxzdy(dgMiddle);
				vWorst=Rgyl.ljfyxzdy(dgWorst);
				break;
			case "dmzzxzdy"://2  �����������ж���
				vBest=Rgyl.dmzzxzdy(dgBest);
				vMiddle=Rgyl.dmzzxzdy(dgMiddle);
				vWorst=Rgyl.dmzzxzdy(dgWorst);
				break;
			case "tlgnldxzdy"://3  ̩�������ϡ��ֵ����ж���
				vBest=Rgyl.tlgnldxzdy(dgBest);
				vMiddle=Rgyl.tlgnldxzdy(dgMiddle);
				vWorst=Rgyl.tlgnldxzdy(dgWorst);
				break;
			case "dmzzxxyy"://4 ����������СҶ��
				vBest=Rgyl.dmzzxxyy(dgBest);
				vMiddle=Rgyl.dmzzxxyy(dgMiddle);
				vWorst=Rgyl.dmzzxxyy(dgWorst);
				break;
			case "tlxxyy"://5 ̩����СҶ��
				vBest=Rgyl.tlxxyy(dgBest);
				vMiddle=Rgyl.tlxxyy(dgMiddle);
				vWorst=Rgyl.tlxxyy(dgWorst);
				break;
			case "ldgnljfyxxyy"://6 �ֵ顢���ϡ���������ԣ��СҶ��
				vBest=Rgyl.ldgnljfyxxyy(dgBest);
				vMiddle=Rgyl.ldgnljfyxxyy(dgMiddle);
				vWorst=Rgyl.ldgnljfyxxyy(dgWorst);
				break;
			default:
				break;
			}
			break;
		case "Wdssd"://���ɽɽ��
			switch (tree) 
			{
			case "sql"://1 ˮ����
				vBest=Wdssd.sql(dgBest);
				vMiddle=Wdssd.sql(dgMiddle);
				vWorst=Wdssd.sql(dgWorst);
				break;
			case "htq"://2  ������
				vBest=Wdssd.htq(dgBest);
				vMiddle=Wdssd.htq(dgMiddle);
				vWorst=Wdssd.htq(dgWorst);
				break;	
			case "hbl"://3  �Ʋ���
				vBest=Wdssd.hbl(dgBest);
				vMiddle=Wdssd.hbl(dgMiddle);
				vWorst=Wdssd.hbl(dgWorst);
				break;
			case "ys"://4 ����
				vBest=Wdssd.ys(dgBest);
				vMiddle=Wdssd.ys(dgMiddle);
				vWorst=Wdssd.ys(dgWorst);
				break;
			case "ss"://5 ɫ��
				vBest=Wdssd.ss(dgBest);
				vMiddle=Wdssd.ss(dgMiddle);
				vWorst=Wdssd.ss(dgWorst);
				break;
			case "fh"://6 ����
				vBest=Wdssd.fh(dgBest);
				vMiddle=Wdssd.fh(dgMiddle);
				vWorst=Wdssd.fh(dgWorst);
				break;
			case "zs"://7  ����
				vBest=Wdssd.zs(dgBest);
				vMiddle=Wdssd.zs(dgMiddle);
				vWorst=Wdssd.zs(dgWorst);
				break;
			case "hh"://8 ����
				vBest=Wdssd.hh(dgBest);
				vMiddle=Wdssd.hh(dgMiddle);
				vWorst=Wdssd.hh(dgWorst);
				break;
			case "bh"://9 ����
				vBest=Wdssd.bh(dgBest);
				vMiddle=Wdssd.bh(dgMiddle);
				vWorst=Wdssd.bh(dgWorst);
				break;
			case "ds"://10 ���
				vBest=Wdssd.ds(dgBest);
				vMiddle=Wdssd.ds(dgMiddle);
				vWorst=Wdssd.ds(dgWorst);
				break;
			case "sy"://11 ɽ��
				vBest=Wdssd.sy(dgBest);
				vMiddle=Wdssd.sy(dgMiddle);
				vWorst=Wdssd.sy(dgWorst);
				break;
			default:
				break;
			}
			break;
		case "Xxalbp"://С�˰��뱱��
			switch (tree) 
			{
			case "lys"://1 ��Ҷ��
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "htq"://2 ������
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "sql"://3 ˮ����
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "ys": //4 ����
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "ss"://5 ɫ��
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "fh"://6 ����
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
			   break;
		   case "zs"://7  ����
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
			    break;
		    case "hh"://8 ����
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "bh":	//9 ����
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "ds":	//10 ���
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "sy"://11 ɽ��
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			default:
				break;
			}
			break;
		case "Xxalnp"://С�˰�������
			switch (tree) 
			{
			case "lys"://1 ��Ҷ��
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "htq"://2 ������
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "sql"://3 ˮ����
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "ys": //4 ����
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "ss"://5 ɫ��
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "fh"://6 ����
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
			   break;
		   case "zs"://7  ����
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
		    case "hh"://8 ����
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "bh":	//9 ����
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "ds":	//10 ���
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "sy"://11 ɽ��
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			default:
				break;
			}
			break;
		case "Zgcldp"://�Ź���붫��
			switch (tree) 
			{
			case "htq"://1  ������
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "sql"://2 ˮ����
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "hbl"://3  �Ʋ���
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "ys": //4 ����
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "ss"://5 ɫ��
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "fh"://6 ����
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
			    break;
		   case "zs"://7  ����
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
		    case "hh"://8 ����
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "bh":	//9 ����
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "ds":	//10 ���
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "sy"://11 ɽ��
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			default:
				break;
			}
			break;
		case "Zgclxp"://�Ź��������
			switch (tree) 
			{
			case "sql"://1 ˮ����
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "htq"://2  ������
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "hbl"://3  �Ʋ���
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "ys": //4 ����
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "ss"://5 ɫ��
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "fh"://6 ����
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
			    break;
		    case "zs"://7  ����
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
		    case "hh"://8 ����
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "bh":	//9 ����
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "ds":	//10 ���
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "sy"://11 ɽ��
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
		   case "ls":	//12 ����
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
		   default:
				break;
			}
			break;  
		default:
			break;
		}
		double avg=(vBest+vMiddle+vWorst)/3;
		double V=avg*density*area;
		return V;
	}
	
	public static void main(String[] args) 
	{
		double result=CalV(NameTransform.getRegionValue("ȫʡͨ��"),NameTransform.getTreeValue("ȫʡͨ��", "��ɼ"), 32, 31, 25, 123, 3.2);
		System.out.println(result);
	}
}
