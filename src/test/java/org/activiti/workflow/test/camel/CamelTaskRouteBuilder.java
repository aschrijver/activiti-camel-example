package org.activiti.workflow.test.camel;

import javax.xml.bind.JAXBContext;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.spi.DataFormat;
import org.activiti.workflow.test.data.Page;

@SuppressWarnings("restriction")
public class CamelTaskRouteBuilder extends RouteBuilder {

    @Override
	public void configure() throws Exception {
		JAXBContext jc = JAXBContext.newInstance(Page.class);
		DataFormat jaxb = new JaxbDataFormat(jc);

		from("direct:start")
			.setBody().simple("resource:classpath:data/page.xml")
			.unmarshal(jaxb)
			.log(LoggingLevel.DEBUG, "Testing workflow creation from a Camel route")
			.to("activiti:camelTaskTestProcess");
			
		from("activiti:camelTaskTestProcess:theCamelTask?copyCamelBodyToBody=true")
			.unmarshal(jaxb)
            .log("Testing Camel route invocation from a Camel task.")
			.to("mock:result");
	}
}
