package com.moyan.example.nio.socket.server.protocal;

/**
 * @author chenjie
 * 2013-6-8
 */
public enum ProtocalType {

	TEXT_PROTOCAL(1),		//�ı�Э��
	BINARY_PROTOCAL(2);	//������Э��
	
	private int type;
	
	private ProtocalType(int type) {
		this.type = type;
	}

	public final int getType() {
		return type;
	}

	public final void setType(int type) {
		this.type = type;
	}
	
	
	
}
