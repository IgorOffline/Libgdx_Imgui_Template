package igoroffline.template.libgdximgui.main;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Profile("dev")
public class LibgdxSetup {

    @PostConstruct
    public void init() {
        log.info("<START:DEV>");
        createApplication();
        log.info("<END:DEV>");
    }

    private Lwjgl3Application createApplication() {
        return new Lwjgl3Application(new MyGame(), getDefaultConfiguration());
    }

    private Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("libgdx_imgui_template");
        configuration.useVsync(true);
        configuration.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate);
        configuration.setWindowedMode(640, 480);
        return configuration;
    }
}
