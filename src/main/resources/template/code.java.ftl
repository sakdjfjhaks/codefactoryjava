package ${package};

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;
public class ${className}  {

    protected AndroidDriver driver = null;

    @BeforeEach
    public void first() throws Exception {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("automationName", "Appium");//appium做自动化
        cap.setCapability("platformName", "Android"); //安卓自动化还是IOS自动化
        cap.setCapability("platformVersion", "10.0"); //安卓操作系统版本
        cap.setCapability("deviceName", "Android Emulator"); //安卓操作系统版本
        cap.setCapability("appPackage", "com.heyi.meike");//被测app的包名
        cap.setCapability("appActivity", ".MainActivity");//被测app的入口Activity名称

        cap.setCapability("app", "D:\\meike.apk");//安装app
        cap.setCapability("noReset", true);//不清空数据,不用每次安装

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);//把以上配置传到appium服务端并连接手机
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//隐式等待
    }

    @Test
    public void login() throws Exception {
        driver.findElementByXPath("//*[@text='使用账号密码登录']").click();
        Thread.sleep(5000);

        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.EditText").sendKeys("17862462918");
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.EditText").sendKeys("123456");

        Thread.sleep(2000);
        driver.findElementByXPath("(//android.view.ViewGroup[@content-desc=\"button-item\"])[1]/android.view.ViewGroup/android.widget.TextView").click();
    }

    @AfterEach
    public void after() {
    //        driver.quit();
    }
}