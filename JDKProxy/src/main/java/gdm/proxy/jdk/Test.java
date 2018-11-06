package gdm.proxy.jdk;

import gdm.myproxy.jdk.GdProxy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Test {

    public static void main(String[] args) {
       Myinterface obj = (Myinterface) new Myproxy().getInstance(new Person());
        obj.findHouse();
        obj.findJob();
        obj.findLove();

    }
}
