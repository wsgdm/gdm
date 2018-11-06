package gdm.myproxy.jdk.myjdk;


import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class GdProxy {
     static  String ln = "\r\n";
    //返回一个代理对象
    public static Object newProxyInstance(GdClassLoader classLoader, Class<?>[] interfaces, GdInvocationHandler h,String[] MethodsName,Object obj){

        try {
            //1.生成一个java代理类.java输出到硬盘
            String src = generateSrc(interfaces[0],MethodsName);
            String filePath = GdProxy.class.getResource("").getPath();
            File file = new File(filePath + "$Proxy0.java");
            FileWriter fw = new FileWriter(file);
            fw.write(src);
            fw.flush();
            fw.close();

            //2.把java类编译成.class
            JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = jc.getStandardFileManager(null,null,null);
            Iterable ita = manager.getJavaFileObjects(file);
            JavaCompiler.CompilationTask task =  jc.getTask(null,manager,null,null,null,ita);
            task.call();
            manager.close();

            //3.把.class文件加载到jvm虚拟机,并返回对象
            Class proxyClass = classLoader.findClass("$Proxy0");
            Constructor c = proxyClass.getConstructor(new Class[]{GdInvocationHandler.class, String.class});
            file.delete();
            return c.newInstance(new Object[]{h,obj});


        } catch (Exception e) {
            e.printStackTrace();
        }

            return null;
    }
    public static String generateSrc(Class<?> interfaces,String[] MethodsName){
        StringBuffer sr = new StringBuffer();
        sr.append("package gdm.myproxy.jdk;" + ln);
        sr.append("import java.lang.reflect.Method;" + ln);
        sr.append("public class $Proxy0 implements " + interfaces.getName() + "{" + ln);
        sr.append("    private GdInvocationHandler h;" + ln);
        sr.append("    private Object obj;" + ln);
        sr.append("    public $Proxy0(GdInvocationHandler h,Object obj){" + ln);
        sr.append("        this.h = h;" + ln);
        sr.append("        this.obj = obj;" + ln);
        sr.append("    }" + ln);
        for(Method method : interfaces.getMethods()){
            sr.append("    public " +  method.getReturnType().getName() + " " + method.getName() + "(){" + ln);
            for(String s : MethodsName){
                if(s == null || s == "")continue;
                if(s.equals(method.getName())){
                    sr.append("        try{"+ ln);
                    sr.append("                Method m = " + interfaces.getName() + ".class.getMethod(\"" +method.getName()+"\",new Class[]{});" + ln);
                    sr.append("                m.invoke(this.obj,(Object[])null);" + ln);
                    sr.append("            }catch(Throwable e){e.printStackTrace();}" + ln);
                }else{
                    sr.append("        try{"+ ln);
                    sr.append("                Method m = " + interfaces.getName() + ".class.getMethod(\"" +method.getName()+"\",new Class[]{});" + ln);
                    sr.append("                this.h.invoke(this,m,null);" + ln);
                    sr.append("            }catch(Throwable e){e.printStackTrace();}" + ln);
                }
            }
            sr.append("    }" + ln);
        }
        sr.append("}" + ln);
        return sr.toString();

    }
}
