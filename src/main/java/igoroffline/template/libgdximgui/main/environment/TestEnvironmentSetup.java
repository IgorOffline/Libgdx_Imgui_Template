package igoroffline.template.libgdximgui.main.environment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Profile("test")
public class TestEnvironmentSetup implements EnvironmentSetup {

    @Override
    public void init() {
        log.info("<START:TEST>");
    }
}
