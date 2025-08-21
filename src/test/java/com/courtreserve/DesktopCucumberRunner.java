package com.courtreserve;

import com.courtreserve.configs.BaseConfig;
import io.cucumber.junit.platform.engine.Constants;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;


@Suite
@ComponentScan
@EnableAutoConfiguration
@IncludeEngines("cucumber")
@CucumberContextConfiguration
@SelectClasspathResource("features")
@SpringBootTest(classes = BaseConfig.class)
@TestPropertySource("classpath:application.yml")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "com.courtreserve")
// Only uncomment the following line if you want to generate HTML reports locally
//@ConfigurationParameter(key = io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-reports/cucumber.html")
//@ConfigurationParameter(key = io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME, value = "io.qase.cucumber7.QaseEventListener")
public class DesktopCucumberRunner {
}
