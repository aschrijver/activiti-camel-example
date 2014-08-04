package org.activiti.workflow.test.camel;

import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.activiti.workflow.test.AbstractWorkflowTest;
import org.activiti.workflow.test.data.Page;

import static org.junit.Assert.fail;

public class ActivitiCamelTaskTest extends AbstractWorkflowTest{

    @Autowired
	@Rule
	public ActivitiRule activitiRule;
	
    @Produce(uri = "direct:start", context = "camelContext")
    protected ProducerTemplate start;
	
    @EndpointInject(uri = "mock:result")
    protected MockEndpoint resultEndpoint;
    
	@Test
	@Deployment(resources = {"org/activiti/workflow/test/camel/cameltask-test-process.bpmn20.xml"})
	public void testStartProcessFromCamelRoute() throws Exception {
	    resultEndpoint.expectedBodyReceived().body().isInstanceOf(Page.class);
		
	    try {
	        start.sendBody(null);
	    } catch (CamelExecutionException e) {
	        fail(e.getMessage());
	    }

		resultEndpoint.assertIsSatisfied();
	}
}
