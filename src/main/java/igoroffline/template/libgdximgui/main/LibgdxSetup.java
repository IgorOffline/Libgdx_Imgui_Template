package igoroffline.template.libgdximgui.main;

import igoroffline.template.libgdximgui.main.environment.EnvironmentSetup;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LibgdxSetup {

    private final EnvironmentSetup environmentSetup;

    public LibgdxSetup(EnvironmentSetup environmentSetup) {
        this.environmentSetup = environmentSetup;
    }

    @PostConstruct
    void init() {
        environmentSetup.init();
    }
}
