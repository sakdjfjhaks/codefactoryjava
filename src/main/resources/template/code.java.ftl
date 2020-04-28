package ${package};

import com.miniyoupin.test.niniyoupin.app.baseentity.AppBaseEntity;
import org.junit.Test;
import org.openqa.selenium.By;

public class ${className} extends AppBaseEntity {

    @Test
    public void ${methodName}() throws InterruptedException {

        getDriver().findElement(By.xpath("//*[@text = '消息']")).click(); //7
        Thread.sleep(1500);

    }

}