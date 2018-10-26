package org.pyf.admin.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServerApplicationTests {

    @Test
    public void contextLoads() throws Exception {
        URL url = new URL("https://www.icourse163.org/learn/BNU-1003257002?tid=1003476005#/learn/content?type=detail&id=1005172024");
        URLConnection uc = url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
        String str= null;
        String xz = "";
        while((str=br.readLine())!=null){
            System.out.println(str);
            if(str.indexOf(".mp4")!=-1){
                try{
                    xz = str.substring(str.lastIndexOf("https"),str.indexOf(".mp4") + 3);
                }catch(Exception e){

                }
            }
        }
        System.out.println("下载地址为：" + xz);
        //getDondow(xz,"F:\\xx.swf");

    }

    //下载视频方法
    private void getDondow(String url,String pathName)throws Exception{
        URL ul = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) ul.openConnection();
        BufferedInputStream bi = new BufferedInputStream(conn.getInputStream());
        FileOutputStream bs = new FileOutputStream(pathName);
        System.out.println("文件大约："+(conn.getContentLength()/1024)+"K");
        byte[] by = new byte[1024];
        int len = 0;
        while((len=bi.read(by))!=-1){
            bs.write(by,0,len);
        }
        bs.close();
        bi.close();
    }

}
