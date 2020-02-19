package org.timespace.blog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @RequestMapping(value = "test1")
    public String jsp(Model model){
        model.addAttribute("id", 1);
        model.addAttribute("name", "jsp");
        model.addAttribute("nickname","인텔리J");
        model.addAttribute("photo", "images/IntelliJ.jpg");
        return "test1";
    }

    @RequestMapping(value = "thymleaf1")
    public String tmpl(Model model){
        model.addAttribute("id", 1);
        model.addAttribute("name", "thymleaf1");
        model.addAttribute("nickname","인텔리J");
        model.addAttribute("photo", "images/IntelliJ.jpg");
        return "test1";
    }
    /*
    spring.datasource.url=jdbc:h2:mem:testdb;CACHE_SIZE=10240;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;LOCK_TIMEOUT=15000;MODE=MySQL;
    #jdbc:h2:~/testdb
    spring.datasource.platform=h2
    spring.datasource.separator=;
    spring.datasource.username=sa
    spring.datasource.driver-class-name=org.h2.Driver
    spring.datasource.sql-script-encoding=UTF-8
    spring.jpa.database-platform=H2
    spring.jpa.hibernate.ddl-auto=update
    #spring.datasource.data=classpath:sql/data-H2.sql
    spring.jpa.show-sql=true

    spring.thymeleaf.cache=false
    spring.thymeleaf.mode=LEGACYHTML5

    spring.devtools.livereload.enabled=true
    spring.messages.basename=validation
    spring.messages.encoding=UTF-8

    logging.level.org.springframework.security = debug
     */
}

