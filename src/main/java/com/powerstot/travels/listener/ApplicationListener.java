package com.powerstot.travels.listener;

import org.springframework.stereotype.Component;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

@Component
public class ApplicationListener implements ServletContextAttributeListener {
    //在线人数
    //除去dispatcherServlet创建时的1个ServletContext对象添加和SpringBoot创建时的2个添加
    private int scc = -3;
    @Override
    public void attributeAdded(ServletContextAttributeEvent scae) {
        System.out.println("在线人数scc + 1");
        scc++;
        scae.getServletContext().setAttribute("scc", scc);
    }
    @Override
    public void attributeRemoved(ServletContextAttributeEvent scae) {
        System.out.println("在线人数scc - 1");
        scc--;
        scae.getServletContext().setAttribute("scc", scc);
    }
    @Override
    public void attributeReplaced(ServletContextAttributeEvent scae) {

    }
}
