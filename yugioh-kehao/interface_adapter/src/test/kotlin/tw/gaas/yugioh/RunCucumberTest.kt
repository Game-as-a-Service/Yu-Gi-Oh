package tw.gaas.yugioh

import io.cucumber.junit.platform.engine.Constants
import org.junit.platform.suite.api.ConfigurationParameter
import org.junit.platform.suite.api.IncludeEngines
import org.junit.platform.suite.api.SelectClasspathResource
import org.junit.platform.suite.api.Suite

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("tw/gaas/yugioh")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "tw.gaas.yugioh")
class RunCucumberTest
