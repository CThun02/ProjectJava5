package java5.poly.assignment.ulities;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class ImageUlities {
    public String getImgByteBase64(String path){
        if(path!=null) {
            File file = new File(path);
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (fis != null) {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                try {
                    for (int readNum; (readNum = fis.read(buf)) != -1; ) {
                        //Writes to this byte array output stream
                        bos.write(buf, 0, readNum);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                byte[] bytes = bos.toByteArray();
                byte[] encodeBase64 = Base64.encodeBase64(bytes, true);
                String base64Encoded = null;
                try {
                    base64Encoded = new String(encodeBase64, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return base64Encoded;
            }
        }
        return "";
    }
}
