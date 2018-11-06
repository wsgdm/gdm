package gdm.myproxy.jdk.myjdk;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class GdClassLoader extends ClassLoader{
    private File baseDir;

    public GdClassLoader(){
        String basePath = GdClassLoader.class.getResource("").getPath();
        this.baseDir = new File(basePath);
    }

    @Override
    protected  Class<?> findClass(String name) throws ClassNotFoundException {
        String className = GdClassLoader.class.getPackage().getName() + "." + name;
        if(baseDir != null){
             File classFile = new File(baseDir,name.replaceAll("\\.", "/") + ".class");
            if(classFile.exists()){
                FileInputStream in = null;
                ByteArrayOutputStream out = null;
                try{
                    in = new FileInputStream(classFile);
                    out = new ByteArrayOutputStream();
                    byte [] buff = new byte[1024];
                    int len;
                    while ((len = in.read(buff)) != -1) {
                        out.write(buff, 0, len);
                    }
                     Class<?> cc = defineClass(className, out.toByteArray(), 0,out.size());
                    return cc;
                }catch (Exception e) {
                    e.printStackTrace();
                }finally{
                    if(null != in){
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(null != out){
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                     classFile.delete();
                }

            }
        }

        return null;
    }

}
