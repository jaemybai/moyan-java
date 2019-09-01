package com.moyan.example.http;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

public class SSLSocketFactoryImpl extends SSLSocketFactory {
	/**
	 * @Fields sslContext
	 * @Description: Field Description
	 */
	SSLContext sslContext = SSLContext.getInstance("TLS");

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param truststore
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws KeyStoreException
	 * @throws UnrecoverableKeyException
	 */
	public SSLSocketFactoryImpl(KeyStore truststore)
			throws NoSuchAlgorithmException, KeyManagementException,
			KeyStoreException, UnrecoverableKeyException {
		super(truststore);
		TrustManager tm = new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};
		sslContext.init(null, new TrustManager[] { tm }, null);
	}

	/*
	 * (non-Javadoc) <p>Title: createSocket</p> <p>Description: </p>
	 * 
	 * @param socket
	 * 
	 * @param host
	 * 
	 * @param port
	 * 
	 * @param autoClose
	 * 
	 * @return
	 * 
	 * @throws IOException
	 * 
	 * @throws UnknownHostException
	 * 
	 * @see
	 * org.apache.http.conn.ssl.SSLSocketFactory#createSocket(java.net.Socket,
	 * java.lang.String, int, boolean)
	 */
	@Override
	public Socket createSocket(Socket socket, String host, int port,
			boolean autoClose) throws IOException, UnknownHostException {
		return sslContext.getSocketFactory().createSocket(socket, host, port,
				autoClose);
	}

	/*
	 * (non-Javadoc) <p>Title: createSocket</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @throws IOException
	 * 
	 * @see org.apache.http.conn.ssl.SSLSocketFactory#createSocket()
	 */
	@Override
	public Socket createSocket() throws IOException {
		return sslContext.getSocketFactory().createSocket();
	}

	/**
	 * @Title: getNewHttpClient
	 * @Description: Methods Description
	 * @param @return
	 * @return HttpClient
	 * @throws
	 */

	@SuppressWarnings("deprecation")
	private static HttpClient getNewHttpClient() {
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore
					.getDefaultType());
			trustStore.load(null, null);
			SSLSocketFactory sf = new SSLSocketFactory(trustStore);
			sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("com/moyan/example/http", PlainSocketFactory
					.getSocketFactory(), 80));
			registry.register(new Scheme("https", sf, 443));
			ClientConnectionManager ccm = new ThreadSafeClientConnManager(
					params, registry);
			return new DefaultHttpClient(ccm, params);
		} catch (Exception e) {
			return new DefaultHttpClient();
		}
	}

	public static void main(String[] args) throws Exception {
		DefaultHttpClient httpclient = (DefaultHttpClient) getNewHttpClient();

		try {
			// Secure Protocol implementation.
			SSLContext ctx = SSLContext.getInstance("SSL");
			// Implementation of a trust manager for X509 certificates
			X509TrustManager tm = new X509TrustManager() {

				public void checkClientTrusted(X509Certificate[] xcs,
						String string) throws CertificateException {

				}

				public void checkServerTrusted(X509Certificate[] xcs,
						String string) throws CertificateException {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			ctx.init(null, new TrustManager[] { tm }, null);
			SSLSocketFactory ssf = new SSLSocketFactory(ctx);
			ClientConnectionManager ccm = httpclient.getConnectionManager();
			// register https protocol in httpclient's scheme registry
			SchemeRegistry sr = ccm.getSchemeRegistry();
			sr.register(new Scheme("https", 443, ssf));
		} catch (Exception e) {
			e.printStackTrace();
		}

		String httpGetUrl = "";
		HttpGet httpGet = new HttpGet(httpGetUrl);
//		HttpResponse response = httpclient.execute(httpGet, localContext);
		HttpResponse response = httpclient.execute(httpGet);

	}
}