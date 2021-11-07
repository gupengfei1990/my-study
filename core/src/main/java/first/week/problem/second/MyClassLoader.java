package first.week.problem.second;

import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String fileName) throws ClassNotFoundException {
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
            byte[] sourceBytes = new byte[inputStream.available()];
            inputStream.read(sourceBytes);
            inputStream.close();
            byte[] targetBytes = new byte[sourceBytes.length];
            for(int i=0; i<sourceBytes.length; i++) {
                targetBytes[i] = (byte)(255 - sourceBytes[i]);
            }
            return defineClass(fileName.split("\\.")[0], targetBytes, 0, targetBytes.length);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
