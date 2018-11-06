package gdm.myproxy.jdk.myjdk.test;

public class Test {

    public static void main(String[] args) {
       Myinterface obj = (Myinterface) new Myproxy().getInstance(new Person(),new String[]{"findLove"});
        obj.findHouse();
        obj.findJob();
        obj.findLove();

    }
}
