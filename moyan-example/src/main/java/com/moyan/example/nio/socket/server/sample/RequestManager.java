package com.moyan.example.nio.socket.server.sample;

import com.moyan.example.nio.socket.server.data.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenjie
 * 2013-6-13
 */
public class RequestManager {

	private static Logger logger = LoggerFactory.getLogger(RequestManager.class);

	private static RequestManager instance;
	
	private Map<String, Class<IRequestHandler>> handlerClasses;
	private Map<String, IRequestHandler> handlers;
	
	private RequestManager(){
		this.handlerClasses = new HashMap<String, Class<IRequestHandler>>();
		this.handlers = new HashMap<String, IRequestHandler>();
	}
	
	public static RequestManager getInstance(){
		if(instance == null){
			instance = new RequestManager();
		}
		return instance;
	}
	
	//×¢²áhandler
	public void registerHandler(String cmd, Class<IRequestHandler> handler){
		if(this.handlerClasses.containsKey(cmd)){
			return;
		}
		this.handlerClasses.put(cmd, handler);
	}
	
	
	private IRequestHandler getRequestHandler(String id) throws InstantiationException, IllegalAccessException{
		if(this.handlers.containsKey(id)){
			return this.handlers.get(id);
		}
		
		if(this.handlerClasses.containsKey(id)){
			Class<IRequestHandler> handleClass = this.handlerClasses.get(id);
			
			IRequestHandler handler = handleClass.newInstance();
			this.handlers.put(id, handler);
			return handler;
		}
		
		return null;
	}
	
	public void handleRequest(Request request){
		
		//get handler;
		try {
			IRequestHandler handler = getRequestHandler(request.getId());
			if(handler == null){
				logger.info("The handler for cmd " + request.getId() + " is not registered in the RequestManager");
				return;
				
			}
			
			handler.handleRequest(request);
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		
		
	}
	
	
}
