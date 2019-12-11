package Util;

import Model.JdbcConfig;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

public class XmlConfigReader {
    //单例懒汉式（延迟载入lazy）
    //设置为空，使用在new。不用不new
    private static XmlConfigReader instance = null;

    //定义JdbcConfig成员变量。保存jdbc相关配置信息
    private JdbcConfig jdbcConfig = new JdbcConfig();;

    private XmlConfigReader(){
        //创建saxReader对象
        SAXReader reader = new SAXReader();
        //通过当前线程的类载入器，获得文件的相对路径，将xml文件读入到输入流
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("Config.xml");
        try {
            // 通过read方法读取xml文件。 转换成Document对象
            Document doc = reader.read(in);

            //获得节点对象，取得jdbc相关的配置信息
            Element driverNameElt = (Element)doc.selectObject("/sqlConnection/sql/driver");
            Element urlElt = (Element)doc.selectObject("/sqlConnection/sql/url");
            Element userNameElt = (Element)doc.selectObject("/sqlConnection/sql/use");
            Element passwordElt = (Element)doc.selectObject("/sqlConnection/sql/pwd");

            //设置jdbc相关的配置
            jdbcConfig.setDriver(driverNameElt.getStringValue());
            jdbcConfig.setUrl(urlElt.getStringValue());
            jdbcConfig.setUse(userNameElt.getStringValue());
            jdbcConfig.setPassword(passwordElt.getStringValue());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 加锁，实现多线程的同步机制，防止多线程的訪问冲突
     * @return
     */
    public static synchronized XmlConfigReader getInstance(){
        if(instance == null){
            instance = new XmlConfigReader();
        }
        return instance;
    }
    /**
     * 返回jdbc的相关配置
     * @return
     */
    public JdbcConfig getJdbcConfig(){
        return jdbcConfig;
    }

}
