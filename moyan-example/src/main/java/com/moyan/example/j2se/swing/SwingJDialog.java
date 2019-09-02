package com.moyan.example.j2se.swing;

import com.moyan.example.j2se.base.ViolateTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * This program demonstrates the creation of a JDialog from a super-window.
 * The created dialog is on the mode "Modal".
 * @author han
 *
 */
public class SwingJDialog {

	private static Logger logger = LoggerFactory.getLogger(ViolateTest.class);

	public SwingJDialog(){
		final JFrame jf=new JFrame("��������ʵ��");
		// Some methods defined by Toolkit query the native operating system directly.
		Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize(); 
		int Swing1x=500;
		int Swing1y=300;
		jf.setBounds(screensize.width/2-Swing1x/2,screensize.height/2-Swing1y/2,Swing1x,Swing1y);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c=jf.getContentPane();
		c.setBackground(Color.pink);
		c.setLayout(null);

		Dimension Swing1size=jf.getSize();
		JButton jb=new JButton("�����Ի���");
		int jbx=100;
		int jby=30;
		jb.setBounds(Swing1size.width/2-jbx/2,Swing1size.height/2-jby/2,jbx,jby);
		//jb.setBounds(Swing1x/2-jbx/2,Swing1y/2-jby/2,jbx,jby);
		c.add(jb);

		class Dialog1 {
			JDialog jd=new JDialog(jf,"JDialog����",true);
			Dialog1(){

				jd.setSize(300,200);
				Container c2=jd.getContentPane();
				c2.setLayout(null);
				JLabel jl=new JLabel("ֻ��һ���Ի���");
				jl.setBounds(0,-20,100,100);
				JButton jbb=new JButton("ȷ��");
				jbb.setBounds(100,100,60,30);
				c2.add(jl);
				c2.add(jbb);
				jbb.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						jd.dispose();
						//System.exit(0);
					}

				});
				logger.info("OK");

				jd.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

			}
		}

		jb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Dialog1().jd.setVisible(true);//�����Ի���
				logger.info("OK2");
			}
		});
		logger.info("OK3");
	}

	public static void main(String[] args){
		new SwingJDialog();
	}
}