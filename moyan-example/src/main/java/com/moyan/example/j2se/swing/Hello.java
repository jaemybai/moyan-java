package com.moyan.example.j2se.swing;


import com.moyan.example.j2se.base.ViolateTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.*;

/**
 * swing����ʵ��
 * @author HZ20232
 *
 */
public class Hello{

    private static Logger logger = LoggerFactory.getLogger(ViolateTest.class);

    public static void main(String args[])throws Exception{
        NewFrame frame1 = new NewFrame();
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//һ��Ҫ���ùر�

        frame1.setVisible(true);
    }
}
class NewFrame extends JFrame{
    private JLabel label1;
    private JButton button1;
    private JTextField text1;
    private JComboBox box;
    private JMenuBar menuBar;
    private JSlider slider;
    private JSpinner spinner;
    private JToolBar toolBar;
    
    public NewFrame(){
        super();
        this.setSize(300,500);
        this.getContentPane().setLayout(null);//���ò��ֿ�����

//        this.getContentPane().setLayout(new FlowLayout());//���ò��ֿ�����

//        this.getContentPane().setLayout(new GridLayout(1,2));//���ò��ֿ�����,��Ҫ�����趨��������Ŀ

//        this.getContentPane().setLayout(new BorderLayout());//���ò��ֿ���������North,South,West,East�������ƿؼ�����

//        this.getContentPane().setLayout(new GridBagLayout());//���ò��ֿ�����

        this.add(this.getTextField(),null);//����ı���

        this.add(this.getButton(),null);//��Ӱ�ť

        this.add(this.getLabel(),null);//��ӱ�ǩ

        this.add(this.getBox(),null);//��������б��

        this.setJMenuBar(this.getMenu());//��Ӳ˵�

        this.add(this.getSlider(),null);
        this.add(this.getSpinner(),null);
        this.add(this.getToolBar(),null);
        this.setTitle("Hello World!");//���ô��ڱ���

    }
    private JToolBar getToolBar(){
        if(toolBar==null){
            toolBar = new JToolBar();
            toolBar.setBounds(103,260,71,20);
            toolBar.setFloatable(true);
        }
        return toolBar;
    }
    private JSpinner getSpinner(){
        if(spinner==null){
            spinner = new JSpinner();
            spinner.setBounds(103,220, 80,20);
            spinner.setValue(100);
        }
        return spinner;
    }
    private JSlider getSlider(){
        if(slider==null){
            slider = new JSlider();
            slider.setBounds(103,200,100, 20);
            slider.setMaximum(100);
            slider.setMinimum(0);
            slider.setOrientation(0);
            slider.setValue(0);
        }
        return slider;
    }
    /**
     * �˵��ļ���JMenuBar->JMenu->JMenuItem
     * ��������1��n�Ĺ�ϵ
     * �����Ӳ˵��õ�SetJMenuBar����
     * @return �����õĲ˵�
     */
    private JMenuBar getMenu(){
        if(menuBar==null){
            menuBar = new JMenuBar();
            JMenu m1 = new JMenu();
            m1.setText("�ļ�");
            JMenu m2 = new JMenu();
            m2.setText("�༭");
            JMenu m3 = new JMenu();
            m3.setText("����");
            
            JMenuItem item11 = new JMenuItem();
            item11.setText("��");
            JMenuItem item12 = new JMenuItem();
            item12.setText("����");
            JMenuItem item13 = new JMenuItem();
            item13.setText("�˳�");
            
            JMenuItem item21 = new JMenuItem();
            item21.setText("����");
            JMenuItem item22 = new JMenuItem();
            item22.setText("����");
            JMenuItem item23 = new JMenuItem();
            item23.setText("����");
            
            JMenuItem item31 = new JMenuItem();
            item31.setText("��ӭ");
            JMenuItem item32 = new JMenuItem();
            item32.setText("����");
            JMenuItem item33 = new JMenuItem();
            item33.setText("�汾��Ϣ");
            
            m1.add(item11);
            m1.add(item12);
            m1.add(item13);
            
            m2.add(item21);
            m2.add(item22);
            m2.add(item23);
            
            m3.add(item31);
            m3.add(item32);
            m3.add(item33);
            
            menuBar.add(m1);
            menuBar.add(m2);
            menuBar.add(m3);
        }
        return menuBar;
    }
    /**
     * ���������б��
     * @return
     */
    private JComboBox getBox(){
        if(box==null){
            box = new JComboBox();
            box.setBounds(103,140,71,27);
            box.addItem("1");
            box.addItem("2");
            box.addActionListener(new comboxListener());//Ϊ�����б����Ӽ�������

        }
        return box;
    }
    private class comboxListener implements ActionListener{

        private Logger logger = LoggerFactory.getLogger(ViolateTest.class);

        public void actionPerformed(ActionEvent e){
            Object o = e.getSource();
            logger.info("" + o.toString());
        }
    }
    /**
     * ���ñ�ǩ
     * @return ���úõı�ǩ
     */
    private JLabel getLabel(){
        if(label1==null){
            label1 = new JLabel();
            label1.setBounds(34,49,53,18);
            label1.setText("Name");
            label1.setToolTipText("JLabel");
        }
        return label1;
    }
    /**
     * ���ð�ť
     * @return ���úõİ�ť
     */
    private JButton getButton(){
        if(button1==null){
            button1 = new JButton();
            button1.setBounds(103,110,71,27);
            button1.setText("OK");
            button1.setToolTipText("OK");
            button1.addActionListener(new HelloButton());//��Ӽ������࣬����Ҫ����Ӧ���ɼ�������ķ���ʵ��

        }
        return button1;
    }
    /**
     * ��������ʵ��ActionListener�ӿڣ���Ҫʵ��actionPerformed����
     * @author HZ20232
     *
     */
    private class HelloButton implements ActionListener{
        private Logger logger = LoggerFactory.getLogger(ViolateTest.class);

        public void actionPerformed(ActionEvent e){
            logger.info("Hello world!");
        }
    }
    /**
     * �趨�ı���
     * @return
     */
    private JTextField getTextField(){
        if(text1==null){
            text1 = new JTextField();
            text1.setBounds(96,49,160,20);
        }
        return text1;
    }
}