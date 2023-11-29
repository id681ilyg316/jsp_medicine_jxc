package com.medicine.util;

public class PageUtil {


	
	public static String genPagation(String targetUrl,int totalNum,int currentPage,int pageSize){
		int totalPage=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
		StringBuffer pageCode=new StringBuffer();
		pageCode.append("<li><a href='"+targetUrl+"?page=1'>��ҳ</a></li>");
		if(currentPage==1){
			pageCode.append("<li class='disabled'><a href='#'>��һҳ</a></li>");			
		}else{
			pageCode.append("<li><a href='"+targetUrl+"?page="+(currentPage-1)+"'>��һҳ</a></li>");			
		}
		for(int i=currentPage-2;i<=currentPage+2;i++){
			if(i<1||i>totalPage){
				continue;
			}
			if(i==currentPage){
				pageCode.append("<li class='active'><a href='#'>"+i+"</a></li>");		
			}else{
				pageCode.append("<li><a href='"+targetUrl+"?page="+i+"'>"+i+"</a></li>");	
			}
		}
		if(currentPage==totalPage){
			pageCode.append("<li class='disabled'><a href='#'>��һҳ</a></li>");			
		}else{
			pageCode.append("<li><a href='"+targetUrl+"?page="+(currentPage+1)+"'>��һҳ</a></li>");		
		}
		pageCode.append("<li><a href='"+targetUrl+"?page="+totalPage+"'>βҳ</a></li>");
		return pageCode.toString();
	}
}
