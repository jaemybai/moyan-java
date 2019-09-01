package com.moyan.example.j2se.serializable;

import java.io.*;

/**
 * @descripiton Externalizable接口的使用
 * 
 * @author Alexia
 * @date 2013-10-15
 *
 */
public class ExternalizableTest implements Externalizable {

    private  String content = "是的，我将会被序列化，不管我是否被transient关键字修饰";
    private String user = "user";
    private int password = 2016;

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(content);
        out.writeObject(user);
        out.writeInt(password);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException,
            ClassNotFoundException {
    	content = null;
    	user = null;
    	password = 0;
        content = (String) in.readObject();
        user = (String) in.readObject();
        password = in.readInt();

    }

    public static void main(String[] args) throws Exception {
        
        ExternalizableTest et = new ExternalizableTest();
        et.content = "1";
        et.user = "2";
        et.password = 3;
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream(
                new File("test")));
        out.writeObject(et);
//        out.flush();
        ObjectInput in = new ObjectInputStream(new FileInputStream(new File(
                "test")));
        Object oo = in.readObject();
        ExternalizableTest et2 = (ExternalizableTest) oo;
        System.out.println(et2.content);
        System.out.println(et2.user);
        System.out.println(et2.password);

        out.close();
        in.close();
    }
}