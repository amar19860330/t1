package com.t1.framework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * Created by User on 2016/4/26.
 */

@Service
@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {

    @Autowired
    Environment environment;

    public String getByKey(String key) {
        return environment.getProperty(key);
    }

    public String getRootPath() {
        String systemInfo = System.getProperty("os.name");
        String rootPath = "";
        if (systemInfo.toLowerCase().contains("win")) {
            rootPath = getByKey("data.root.path");
        } else {
            rootPath = getByKey("data.linux.root.path");
        }
        return rootPath;
    }

    public String getDocRootPath() {
        return getRootPath() + getByKey("doc.path");
    }

}
