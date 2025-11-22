package voltik.qpa.czopekhookreborn.skia.state;

import org.lwjgl.opengl.GL30;

import java.util.Stack;
public class UIState {
    private static final Stack<State> stateStack = new Stack<>();
    private static final int glVersion;

    /**
     * Backs up the current OpenGL state and pushes it onto the stack.
     */
    public static void backup() {
        State currentState = new State(glVersion);
        currentState.push();
        stateStack.push(currentState);
    }

    /**
     * Restores the most recent OpenGL state from the stack.
     */
    public static void restore() {
        if (!stateStack.isEmpty()) {
            stateStack.pop().pop();
        }
    }

    static {
        int[] major = new int[1];
        int[] minor = new int[1];
        GL30.glGetIntegerv(GL30.GL_MAJOR_VERSION, major);
        GL30.glGetIntegerv(GL30.GL_MINOR_VERSION, minor);
        glVersion = major[0] * 100 + minor[0] * 10;
    }
}