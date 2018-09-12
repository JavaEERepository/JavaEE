package servlet;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

import bean.News;
import bean.NewsType;
import tools.FileTool;
import tools.PageInformation;
import tools.Tool;
import tools.WebProperties;
import dao.DatabaseDao;
import service.NewsService;
import service.NewsTypeService;

public class InitServlet extends HttpServlet {
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		//初始化数据库参数
		DatabaseDao.drv = this.getServletContext().getInitParameter("drv");
		DatabaseDao.url = this.getServletContext().getInitParameter("url");
		DatabaseDao.usr = this.getServletContext().getInitParameter("usr");
		DatabaseDao.pwd = this.getServletContext().getInitParameter("pwd");
		
		ServletContext servletContext=conf.getServletContext();
		FileTool.root=servletContext.getRealPath("\\");		
		
		//读取属性文件
		String fileDir=servletContext.getRealPath("\\WEB-INF\\web.properties");		
		Parameters params = new Parameters();
		FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
		    new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
		    .configure(params.properties().setFileName(fileDir));
		try
		{
			
		    Configuration config = builder.getConfiguration();

		    WebProperties.propertiesMap.put("projectRoot", 
		    		servletContext.getRealPath(config.getString("projectName")));
		    WebProperties.propertiesMap.put("projectName",config.getString("projectName"));		    
		    WebProperties.propertiesMap.put("tempDir",config.getString("tempDir"));
		    
		    WebProperties.propertiesMap.put("headIconFileDefault",config.getString("headIconFileDefault"));
		    WebProperties.propertiesMap.put("headIconDir",config.getString("headIconDir"));		    
		    WebProperties.propertiesMap.put("headIconDirDefault",config.getString("headIconDirDefault"));
		    WebProperties.propertiesMap.put("redirectTime",config.getString("redirectTime"));
  
		    WebProperties.propertiesMap.put("ueditConfigJs",config.getString("ueditConfigJs"));
		    WebProperties.propertiesMap.put("ueditJs",config.getString("ueditJs"));
		    WebProperties.propertiesMap.put("ueditLang",config.getString("ueditLang"));
		    
		    //加载新闻类型
		    NewsTypeService newsTypeService=new NewsTypeService();
		    List<NewsType> newsTypes=new ArrayList<NewsType>(); 
		    newsTypes=newsTypeService.getAll();
		    this.getServletContext().setAttribute("newsTypes", newsTypes);
		    
		    //加载全部新闻
		    NewsService newsService=new NewsService();
		    PageInformation pageInformation= new PageInformation();
		    pageInformation.setTableName("news");
		    pageInformation.setOrderFiled("newsTime");
		    pageInformation.setOrderType("DESC");
			List<News> newss=newsService.getAllNews(pageInformation);
			List<News> national=new ArrayList<News>(),society=new ArrayList<News>(),sports=new ArrayList<News>(),lately=new ArrayList<News>();
			Integer count0=0,count1=0,count2=0,count3=0;
			
			for(int i=0;i<newss.size();i++) {
				if(count0<5) {
					lately.add(newss.get(i));
					count0++;
				}
				if("国际".equals(newss.get(i).getNewsType()) && count1<5) {
					national.add(newss.get(i));
					count1++;
				}
				if("社会".equals(newss.get(i).getNewsType()) && count2<5) {
					society.add(newss.get(i));
					count2++;
				}
				if("体育".equals(newss.get(i).getNewsType()) && count3<5) {
					sports.add(newss.get(i));
					count3++;
				}
				if(count1==5 && count2==5 && count3==5) break;
			}

			this.getServletContext().setAttribute("newses", newss);
			this.getServletContext().setAttribute("lately", lately);
			this.getServletContext().setAttribute("national", national);
			this.getServletContext().setAttribute("society", society);
			this.getServletContext().setAttribute("sports", sports);

		    /*//加载权限信息:利用哈希map存储权限，目的是方便查找权限
		    AuthorityService authorityService=new AuthorityService();
		    List<Authority> authorities=authorityService.getAll();
		    for(Authority authority:authorities){
		    	String key;
		    	if(authority.getParam()==null || authority.getParam().isEmpty())
		    		key=authority.getUrl()+authority.getUserType();
		    	else
		    		key=authority.getUrl()+authority.getParam()+authority.getUserType();
		    	//System.out.println(key);
		    	AuthorityTool.authorityMap.put(key, authority);
		    }	 */
		}
		catch(ConfigurationException cex)
		{
			cex.printStackTrace();
		}
	}

}
