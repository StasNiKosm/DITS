package provider;

import config.WebConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class AppContextProvider {

    private static AbstractApplicationContext context;

    private static void init() {
        context = new AnnotationConfigApplicationContext(WebConfig.class);
    }

    public static AbstractApplicationContext getAppContext() {
        if (context == null)
            init();
        return context;
    }

}
