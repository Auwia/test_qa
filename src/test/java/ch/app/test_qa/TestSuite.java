package ch.app.test_qa;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ch.app.test_qa.client_rest_api.TestClientRestAPI;
import ch.app.test_qa.mobile_test_automation.TestAndroidCalc;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestClientRestAPI.class, TestAndroidCalc.class })
public class TestSuite {
}