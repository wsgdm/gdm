package cn.tx.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class SourceGenerator {

	public static void main(String[] args) throws Exception {
		
		generator("Menu");
		//removeFile("storeDetail");
	}
	
	public static void generator(String fileName) throws Exception{
		GetActionGenerator(fileName);
		GetDaoGenerator(fileName);
		GetDaoImplGenerator(fileName);
		GetQueryGenerator(fileName);
		GetServiceGenerator(fileName);
		GetServiceImplGenerator(fileName);
		generDaoConfig(fileName);
		generServiceConfig(fileName);
	}
	
	public static void removeFile(String fileName){
		File file = null;
		file = new File("src/cn/tx/dao/"+fileName+"Dao.java");
		file.delete();
		file = new File("src/cn/tx/dao/impl/"+fileName+"DaoImpl.java");
		file.delete();
		file = new File("src/cn/tx/query/"+fileName+"Query.java");
		file.delete();
		file = new File("src/cn/tx/service/"+fileName+"Service.java");
		file.delete();
		file = new File("src/cn/tx/service/impl/"+fileName+"ServiceImpl.java");
		file.delete();
	}
	public static void GetActionGenerator(String fileName){
		BufferedReader br = null;
		BufferedWriter bw = null;
		String line = null;
		String newline = null;
		String fileName1 = fileName.substring(0,1).toLowerCase() + fileName.substring(1);
		try {
			br = new BufferedReader(new FileReader("config/cn/tx/template/DemoAction.tlf"));
			bw = new BufferedWriter(new FileWriter("src/cn/tx/controller/"+fileName+"Action.java"));
			while((line = br.readLine()) != null){
				newline = line.replace("Demo", fileName).replace("demo",fileName1);
				bw.write(newline);
				bw.newLine();
				bw.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	public static void GetDaoGenerator(String fileName){
		BufferedReader br = null;
		BufferedWriter bw = null;
		String line = null;
		String newline = null;
		try {
			br = new BufferedReader(new FileReader("config/cn/tx/template/DemoDao.tlf"));
			bw = new BufferedWriter(new FileWriter("src/cn/tx/dao/"+fileName+"Dao.java"));
			while((line = br.readLine()) != null){
				newline = line.replace("Demo", fileName);
				bw.write(newline);
				bw.newLine();
				bw.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	public static void GetDaoImplGenerator(String fileName){
		BufferedReader br = null;
		BufferedWriter bw = null;
		String line = null;
		String newline = null;
		try {
			br = new BufferedReader(new FileReader("config/cn/tx/template/DemoDaoImpl.tlf"));
			bw = new BufferedWriter(new FileWriter("src/cn/tx/dao/impl/"+fileName+"DaoImpl.java"));
			while((line = br.readLine()) != null){
				newline = line.replace("Demo", fileName);
				bw.write(newline);
				bw.newLine();
				bw.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	public static void GetQueryGenerator(String fileName){
		BufferedReader br = null;
		BufferedWriter bw = null;
		String line = null;
		String newline = null;
		try {
			br = new BufferedReader(new FileReader("config/cn/tx/template/DemoQuery.tlf"));
			bw = new BufferedWriter(new FileWriter("src/cn/tx/query/"+fileName+"Query.java"));
			while((line = br.readLine()) != null){
				newline = line.replace("Demo", fileName);
				bw.write(newline);
				bw.newLine();
				bw.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	public static void GetServiceGenerator(String fileName){
		BufferedReader br = null;
		BufferedWriter bw = null;
		String line = null;
		String newline = null;
		try {
			br = new BufferedReader(new FileReader("config/cn/tx/template/DemoService.tlf"));
			bw = new BufferedWriter(new FileWriter("src/cn/tx/service/"+fileName+"Service.java"));
			while((line = br.readLine()) != null){
				newline = line.replace("Demo", fileName);
				bw.write(newline);
				bw.newLine();
				bw.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	public static void GetServiceImplGenerator(String fileName){
		BufferedReader br = null;
		BufferedWriter bw = null;
		String line = null;
		String newline = null;
		String fileName1 = fileName.substring(0,1).toLowerCase() + fileName.substring(1);
		try {
			br = new BufferedReader(new FileReader("config/cn/tx/template/DemoServiceImpl.tlf"));
			bw = new BufferedWriter(new FileWriter("src/cn/tx/service/impl/"+fileName+"ServiceImpl.java"));
			while((line = br.readLine()) != null){
				newline = line.replace("Demo", fileName).replace("demo",fileName1);
				bw.write(newline);
				bw.newLine();
				bw.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static void generDaoConfig(String fileName) throws DocumentException, IOException{
		String fileName1 = fileName.substring(0,1).toLowerCase() + fileName.substring(1);
		SAXReader reader = new SAXReader();
		Document doc = reader.read("config/ApplicationContext-dao.xml");
		Element el =  doc.getRootElement();
		
		el.addElement("bean").addAttribute("id", fileName1+"Dao")
			.addAttribute("class", "cn.tx.dao.impl."+fileName+"DaoImpl")
			.addElement("property").addAttribute("name", "sessionFactory").addAttribute("ref", "sessionFactory");
		XMLWriter writer = new XMLWriter(new FileWriter("config/ApplicationContext-dao.xml"), OutputFormat.createPrettyPrint());
		writer.write(doc);
		writer.close();
	}
	
	public static void generServiceConfig(String fileName) throws Exception{
		String lowerCaseClassName = fileName.substring(0, 1).toLowerCase() + fileName.substring(1);
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new FileReader("config/ApplicationContext-service.xml"));
		Element rootElement = doc.getRootElement();
		
		
		Element newEle = rootElement.addElement("bean")
					.addAttribute("id", lowerCaseClassName+"Service")
					.addAttribute("class", "cn.tx.service.impl."+fileName+"ServiceImpl");
		newEle.addElement("property")
			  .addAttribute("name", lowerCaseClassName+"Dao")
			  .addAttribute("ref", lowerCaseClassName+"Dao");
		XMLWriter writer = new XMLWriter(new FileWriter("config/ApplicationContext-service.xml"), OutputFormat.createPrettyPrint());
		writer.write(doc);
		writer.close();
	}
}
