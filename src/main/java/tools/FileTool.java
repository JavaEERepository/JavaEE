package tools;
import java.io.File;

public class FileTool {	
	public static String root;
	
	//��ȡ����·�����ļ�����  ��·�����ų����ļ�����,����ǡ�\��
	public static String getDictionary(String fullFileName){
		int index=fullFileName.lastIndexOf("\\");
		return fullFileName.substring(0,index);
	}		
	//��ȡ����·���е��ļ���
	public static String getFileName(String fullFileName){
		int index=fullFileName.lastIndexOf("\\");
		return fullFileName.substring(index+1);
	}
	//�����ļ�������ļ�������·��������չ��
	public static String getExtendedFileName(String fileName){
		int index=fileName.lastIndexOf(".");
		return fileName.substring(index+1);
	}
	//��������ļ������ļ���Ϊ��ǰʱ�������ֵ����չ������
	public static String getRandomFileNameByCurrentTime(String fileName){
		String randomFileName=System.currentTimeMillis() +"."+ getExtendedFileName(fileName);
		return randomFileName;
	}
	//��������·����������ļ������ļ���Ϊ��ǰʱ�������ֵ����չ�����䣬·������
	public static String getRandomFileNameFromFullFileNameByCurrentTime(String fullFileName){
		String randomFileName=System.currentTimeMillis() +"."+ getExtendedFileName(getFileName(fullFileName));
		return getDictionary(fullFileName)+randomFileName;
	}	
	
	//ɾ���ļ�
	public static boolean deleteFile(File file)   
	{   
	    boolean result = false;   
	    int tryCount = 0;   
	    while(!result && tryCount++ <10)   
	    {   		      
		    result = file.delete(); 
		    System.gc(); //JVM ������������
	    }   
	    return result;   
	} 	

	//ɾ���ļ���
	private static boolean deleteDictionary(File file) {
		boolean success = true;
		if (file.exists()) { // �ж��ļ��Ƿ����
			if (file.isFile()) { // �ж��Ƿ����ļ�
				if (file.delete() == false) // delete()���� ��Ӧ��֪�� ��ɾ������˼;
					return false;
			} else if (file.isDirectory()) { // �����������һ��Ŀ¼
				File files[] = file.listFiles(); // ����Ŀ¼�����е��ļ� files[];
				for (int i = 0; i < files.length; i++) { // ����Ŀ¼�����е��ļ�
					if (deleteDictionary(files[i]) == false) // ��ÿ���ļ���  ������������еݹ����																	
						return false;
				}
				if (file.delete() == false)// ɾ���ļ���
					return false;
			}
		}
		return success;
	}
	
	
}