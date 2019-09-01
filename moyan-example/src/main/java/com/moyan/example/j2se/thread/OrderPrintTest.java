package com.moyan.example.j2se.thread;//package com.xlbai.base.j2se.thread;
//
//import com.xlbai.base.ListTest;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class OrderPrintTest {
//
//    private Object objLock = new Object();
//    private volatile String next  = "A";
//
//
//    public static void main(String[] args) {
//        final OrderPrintTest orderPrintTest = new OrderPrintTest();
//        List<Thread> threadList = new ArrayList<>();
//        Thread a1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Thread.currentThread().setName("A");
//                orderPrintTest.printWrapper();
//            }
//        });
//        Thread a2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Thread.currentThread().setName("B");
//                orderPrintTest.printWrapper();
//
//            }
//        });
//        Thread a3 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Thread.currentThread().setName("C");
//                orderPrintTest.printWrapper();
//
//            }
//        });
//        threadList.add(a1);
//        threadList.add(a2);
//        threadList.add(a3);
////        threadList.stream().limit(1).forEach(null);
//        threadList.forEach(item -> item.start());
//        threadList.forEach(item -> {
//            try {
//                while(true) {
//                    if(item.isAlive()) {
//                        Thread.sleep(1);
//                    } else {
//                        break;
//                    }
//                }
//
//            }catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//        System.out.println();
//    }
//
//    private void printWrapper() {
//        try {
//            print();
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    public synchronized void print() throws Exception{
//        String currentName = null;
//        for (int i = 0;i<10;i++) {
//            while(true) {
//                currentName = Thread.currentThread().getName();
//                if(currentName.equals(next)) {
//                    break;
//                } else {
//                    this.wait();
//                }
//            }
//            System.out.print(currentName);
//            next();
//            this.notifyAll();
//        }
//    }
//
//    private void next() {
//       switch (next) {
//           case "A":
//               next = "B";
//               break;
//           case "B":
//               next = "C";
//               break;
//           case "C":
//               next = "A";
//               break;
//           default:
//               throw new RuntimeException();
//       }
//    }
//}
