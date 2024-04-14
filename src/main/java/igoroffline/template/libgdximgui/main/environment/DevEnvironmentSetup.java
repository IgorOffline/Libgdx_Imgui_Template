package igoroffline.template.libgdximgui.main.environment;

import igoroffline.template.libgdximgui.main.AStar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Profile("dev")
public class DevEnvironmentSetup implements EnvironmentSetup {

    @Override
    public void init() {
        log.info("<START:DEV>");

        var tiles = new Short[3][];
        tiles[0] = new Short[] { (short) 1, (short) 0, (short) 1 };
        tiles[1] = new Short[] { (short) 1, (short) 0, (short) 1 };
        tiles[2] = new Short[] { (short) 1, (short) 1, (short) 1 };

        final var astar = new AStar(tiles);
        final var path = astar.findPath(0, 0, 0, 2);

        log.info("path= {}", path);
        log.info("path= {}", path.getFirst());
        log.info("path= {}", path.getLast());

        log.info("<END:DEV>");
    }
}
