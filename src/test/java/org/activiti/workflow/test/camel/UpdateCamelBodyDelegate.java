package org.activiti.workflow.test.camel;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.workflow.test.data.Page;
import org.activiti.workflow.util.WorkflowTestConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateCamelBodyDelegate implements JavaDelegate, WorkflowTestConstants {

    private static final Logger log = LoggerFactory.getLogger(UpdateCamelBodyDelegate.class);

    public void execute(DelegateExecution execution) throws Exception {
        log.debug("UpdateCamelBodyDelegate invoked. Updating 'page' process variable...");
        
        Page page = (Page) execution.getVariable(CAMEL_BODY_VARIABLE);
        page.setPageName("Activiti Tested");
        execution.setVariable(CAMEL_BODY_VARIABLE, page);
    }
}
