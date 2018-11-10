package com.gdm.callback;

import java.util.Date;

public class CallBackImpl implements CallBack {

    Listener listener;

    public CallBackImpl(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(listener != null)
                    listener.MyList("hello---" + new Date());
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        t.start();
    }

    @Override
    public void sayMyHello(String name, Listener listener) {
        this.listener = listener;
    }
}
