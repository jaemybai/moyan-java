package com.moyan.example.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class HttpsClient extends DefaultHttpClient {

	private static Logger logger = LoggerFactory.getLogger(HttpsClient.class);

	public static void main(String[] args) throws Exception {
		String url = "http://repo.maven.apache.org/maven2/org/apache/httpcomponents/httpclient/4.0/httpclient-4.0.jar";
		url = "https://github.com/";
		String fileName = "httpclient-4.0.jar";
		fileName = "urlHttp.html";
		
//		url = "https://www.baidu.com/";
		HttpsClient httpsClient = new HttpsClient();
//		   HttpPost httpPost= new HttpPost(url);
		   HttpGet httpGet = new HttpGet(url);
	        HttpResponse response = httpsClient.execute(httpGet);
	        InputStream is = response.getEntity().getContent();
	        BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
	        BufferedWriter bufferedWriter = new BufferedWriter(
	        		new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"));
	        StringBuffer sb = new StringBuffer();
	        String line = null;
	        while ((line = br.readLine()) != null) {
	        	bufferedWriter.write(line);
	        	bufferedWriter.write("\n");
	        }
	        bufferedWriter.flush();
	        bufferedWriter.close();
	        br.close();
	}
	
	public HttpsClient() throws Exception {
		super();
		SSLContext ctx = SSLContext.getInstance("TLS");
		X509TrustManager tm = new X509TrustManager() {
			@Override
			public void checkClientTrusted(X509Certificate[] chain,
					String authType) {
				logger.info("checkClientTrusted1");
			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain,
					String authType) {
				logger.info("checkServerTrusted2");

			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				logger.info("X509Certificate3");
				return null;
			}
		};
		ctx.init(null, new TrustManager[] { tm }, null);
		SSLSocketFactory ssf = new SSLSocketFactory(ctx,
				SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		ClientConnectionManager ccm = this.getConnectionManager();
		SchemeRegistry sr = ccm.getSchemeRegistry();
		sr.register(new Scheme("https", 443, ssf));
	}
}
