import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.apache.log4j.Appender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.RollingFileAppender;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;

public class BaseTest {
        protected static AppiumDriver<MobileElement> appiumDriver;
        protected boolean localAndroid = true;

        protected static final Logger logger = LogManager.getLogger(BaseTest.class);

        public void rollLogFile(Logger logger) {
            while (logger != null && !logger.getAllAppenders().hasMoreElements()) {
                logger = (Logger)logger.getParent();
            }

            if (logger == null) {
                return;
            }

            for (Enumeration e2 = logger.getAllAppenders(); e2.hasMoreElements();) {
                final Appender appender = (Appender)e2.nextElement();
                if (appender instanceof RollingFileAppender) {
                    final RollingFileAppender rfa = (RollingFileAppender)appender;
                    final File logFile = new File(rfa.getFile());
                    if (logFile.length() > 0) {
                        rfa.rollOver();
                    }
                }
            }
            //File backupFile = new File();
        }

        @BeforeScenario
        public void Education() throws MalformedURLException {
            if(localAndroid){
                System.out.println("Test for Android Device is Running.");
                DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                desiredCapabilities
                        .setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
                desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"emulator-5554");
                //desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.mobisoft.kitapyurdu");
                //desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"com.mobisoft.kitapyurdu.main.SplashActivity");
                desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.ozdilek.ozdilekteyim");
                desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"com.ozdilek.ozdilekteyim.MainActivity");
                //desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,appiumDriver.getCapabilities().getCapability("appActivity");
                desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 3000);
                URL url = new URL("http://127.0.0.1:4723/wd/hub");
                appiumDriver = new AndroidDriver(url, desiredCapabilities);
            }else{
                System.out.println("Test for IOS Device is Running.");
                DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                desiredCapabilities
                        .setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
                desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                desiredCapabilities
                        .setCapability(MobileCapabilityType.UDID, "00008030-00157936018B802E");
                desiredCapabilities
                        .setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.pozitron.hepsiburada");
                desiredCapabilities
                        .setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone");
                desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14.7.1");
                desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 3000);
                URL url = new URL("http://127.0.0.1:4723/wd/hub");
                appiumDriver = new IOSDriver(url, desiredCapabilities);
            }
            rollLogFile(logger);
            File backupFile = new File("log4j/Ozdilekteyim.log.1");
            backupFile.delete();
        }
        @AfterScenario
        public void afterScenario() {
            if(appiumDriver != null)
                appiumDriver.quit();
        }

    }

