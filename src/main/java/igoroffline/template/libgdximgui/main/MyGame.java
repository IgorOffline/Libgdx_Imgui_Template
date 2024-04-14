package igoroffline.template.libgdximgui.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Graphics;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import imgui.type.ImString;
import lombok.extern.slf4j.Slf4j;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

@Slf4j
public class MyGame extends ApplicationAdapter {

    private SpriteBatch batch;
    private Texture image;

    private MyImgui myImgui;

    ImString guiInput = new ImString("", 8);
    private int counter1 = 0;
    private int counter2 = 0;

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("assets/libgdx.png");

        path();

        myImgui = new MyImgui();
    }

    @Override
    public void render() {

        if (!ImGui.isAnyItemActive()) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
                counter1++;
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            counter2++;
        }

        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(image, 140, 210);
        batch.end();
        myImgui.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
        myImgui.dispose();
    }

    private void path() {
        var tiles = new Short[3][];
        tiles[0] = new Short[] { (short) 1, (short) 0, (short) 1 };
        tiles[1] = new Short[] { (short) 1, (short) 0, (short) 1 };
        tiles[2] = new Short[] { (short) 1, (short) 1, (short) 1 };

        final var astar = new AStar(tiles);
        final var path = astar.findPath(0, 0, 0, 2);

        log.info("path= {}", path);
        log.info("path= {}", path.getFirst());
        log.info("path= {}", path.getLast());
    }

    class MyImgui {

        ImGuiImplGlfw imGuiGlfw = new ImGuiImplGlfw();
        ImGuiImplGl3 imGuiGl3 = new ImGuiImplGl3();

        public MyImgui() {
            create();
        }

        public void create() {
            long windowHandle = ((Lwjgl3Graphics) Gdx.graphics).getWindow().getWindowHandle();
            GLFW.glfwMakeContextCurrent(windowHandle);
            GL.createCapabilities();
            ImGui.createContext();
            ImGuiIO io = ImGui.getIO();
            io.setIniFilename(null);
            io.getFonts().addFontDefault();
            io.getFonts().build();

            imGuiGlfw.init(windowHandle, true);
            imGuiGl3.init("#version 110");
        }

        public void render() {
            imGuiGlfw.newFrame();
            ImGui.newFrame();

            // --- ImGUI draw commands go here ---
            if (ImGui.button("I'm a Button!"))
            {
                log.info("<PRESS>");
            }
            ImGui.inputText("Input", guiInput);
            ImGui.text("Press W, observe the counters");
            ImGui.text("counter1: " + counter1);
            ImGui.text("counter2: " + counter2);
            // ---

            ImGui.render();
            imGuiGl3.renderDrawData(ImGui.getDrawData());
        }

        public void dispose() {
            imGuiGl3.dispose();
            imGuiGlfw.dispose();
            ImGui.destroyContext();
        }
    }
}
