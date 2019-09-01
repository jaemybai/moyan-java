package com.moyan.example.j2se.serializable;
import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * @descripiton Externalizable接口的使用
 * 
 * @author Alexia
 * @date 2013-10-15
 *
 */
public class ExternalizableTest2 implements Externalizable {

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
        
        ExternalizableTest2 et = new ExternalizableTest2();
        et.content = "1";
        et.user = "2";
        et.password = 3;
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream(
                new File("test")));
        Object oo = 1;
        out.writeObject(oo);
//        out.flush();
        ObjectInput in = new ObjectInputStream(new FileInputStream(new File(
                "test")));
        Object et2 =  in.readObject();
        System.out.println(et2);

        out.close();
        in.close();
    }
}