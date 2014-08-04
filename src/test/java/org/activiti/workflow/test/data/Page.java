package org.activiti.workflow.test.data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "page")
public class Page implements Serializable {

    String pageName = "";
    
    public Page() {
    }

    @XmlElement(name = "pageName")
    public String getPageName() {
        return pageName;
    }
    
    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
}
