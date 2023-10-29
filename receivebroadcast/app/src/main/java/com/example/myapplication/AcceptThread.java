package com.example.myapplication;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class AcceptThread extends Thread  {
    private Handler myhandler;
    public AcceptThread( Handler handler){
      this.myhandler=  handler;
    }
    public void run(){

        int i=0;

        while(true){

            Message msg = myhandler.obtainMessage();
            // msg.obj = "Hello, world!";
            Bundle bundle= new Bundle();
            bundle.putString("data",i+"");
            msg.setData(bundle);
            msg.what = 1;
            myhandler.sendMessage(msg);

            try{
                Thread.sleep(500);
            }catch (Exception ex){}

            Message m2 = Message.obtain(myhandler, 2);
            m2.setData(bundle);
            m2.sendToTarget();

            i++;
          try{
              Thread.sleep(1000);
          }catch (Exception ex){}
      }
  }
}
