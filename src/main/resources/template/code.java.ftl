package ${package};

import com.miniyoupin.test.niniyoupin.app.baseentity.AppBaseEntity;
import org.junit.Test;
import org.openqa.selenium.By;

public class ${className} extends AppBaseEntity {
    @Test
    public void ${methodName}() throws InterruptedException {
        getDriver().findElement(By.xpath("//android.widget.TextView[contains(@text,'我的')]")).click(); //7
        Thread.sleep(1500);
    }
}
