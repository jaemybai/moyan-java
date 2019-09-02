package com.moyan.example.http;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
/**
 * Java 通过 Socket 的形式抓取网页内容
 * <br><b>创建日期</b>：2011-1-20
 * @author <a href="mailto:hemingwang0902@126.com" mce_href="mailto:hemingwang0902@126.com">何明旺</a>
 */
public class FetchHtmlBySocket {

	private Logger logger = LoggerFactory.getLogger(FetchHtmlBySocket.class);

	public static void main(String[] args) {
		String url = "/hemingwang0902";
		String host = "blog.csdn.net";
//		url = "/public/common/toolbar/css/index.css";
//		host = "c.csdnimg.cn";
		url = "o/2017-03-27/doc-ifycsukm3847674.shtml";
		host = "news.sina.com.cn";
		String html = new FetchHtmlBySocket().htmlContent(new HtmlPage(host, url));
//		logger.info(html);
	}
	
	/**
	 * 抓取网页原代码
	 * <br><b>创建日期</b>：2011-1-20
	 * @param hp
	 * @return	指定网页的源代码，如果抓取失败，则返回一个长度为 0 的空字符串
	 * @author <a href="mailto:hemingwang0902@126.com" mce_href="mailto:hemingwang0902@126.com">何明旺</a>
	 */
	public String htmlContent(HtmlPage hp){
		StringBuffer html = new StringBuffer();
		Socket socket = null;
		BufferedWriter writer = null;
		BufferedReader reader = null;
		try {
			// 建立一个Socket
			socket = new Socket(InetAddress.getByName(hp.getServer()), hp.getPort());
			
			
//			StringBuffer command = new StringBuffer()
//				.append("GET " + hp.getPath() + " HTTP/1.0/r/n")
//				.append("HOST:" + hp.getServer() + "/r/n")
//				.append("/r/n");
			
			StringBuffer command = new StringBuffer()
			.append("GET / " + hp.getPath() + " HTTP/1.0\r\n")
			.append("HOST:" + hp.getServer() + "\r\n")
			.append("\r\n");
			// 发送命令
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF8"));
			writer.write(command.toString());
			writer.flush();
//			writer.write("GET / " + hp.getPath() + " HTTP/1.0\r\n");
//			writer.write("HOST:" + hp.getServer() + "\r\n");
//			writer.write("\r\n");
//			writer.flush(); // 接收返回的结果
			// 接收返回的结果
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
//				html.append(line).append("/r/n");
				logger.info(line);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		} finally {
			try {
				if (reader != null)
					reader.close();
				if (writer != null)
					writer.close();
				if (socket != null)
					socket.close();
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			}
		}
	
		return html.toString();
	}
	
	static class HtmlPage {
		private String server;
		private int port = 80;
		private String path;
		private String charset = "utf-8";
		public HtmlPage() {
			super();
		}
		public HtmlPage(String server, String path) {
			super();
			this.server = server;
			this.path = path;
		}
		public HtmlPage(String server, String path, String charset) {
			super();
			this.server = server;
			this.path = path;
			this.charset = charset;
		}
		public HtmlPage(String server, int port, String path, String charset) {
			super();
			this.server = server;
			this.port = port;
			this.path = path;
			this.charset = charset;
		}
		public String getServer() {
			return server;
		}
		public void setServer(String server) {
			this.server = server;
		}
		public int getPort() {
			return port;
		}
		public void setPort(int port) {
			this.port = port;
		}
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public String getCharset() {
			return charset;
		}
		public void setCharset(String charset) {
			this.charset = charset;
		}
	}
}
