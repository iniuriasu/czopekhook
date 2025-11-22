package voltik.qpa.czopekhookreborn.skia.state;

import java.util.BitSet;

public class Properties {

    public final int[] lastActiveTexture = new int[1],
            lastProgram = new int[1],
            lastTexture = new int[1],
            lastSampler = new int[1],
            lastArrayBuffer = new int[1],
            lastVertexArrayObject = new int[1],
            lastPolygonMode = new int[2],
            lastViewport = new int[4],
            lastScissorBox = new int[4],
            lastBlendSrcRgb = new int[1],
            lastBlendDstRgb = new int[1],
            lastBlendSrcAlpha = new int[1],
            lastBlendDstAlpha = new int[1],
            lastBlendEquationRgb = new int[1],
            lastBlendEquationAlpha = new int[1];

    public final int[] lastPixelUnpackBufferBinding = new int[1],
            lastUnpackAlignment = new int[1],
            lastUnpackRowLength = new int[1],
            lastUnpackSkipPixels = new int[1],
            lastUnpackSkipRows = new int[1],
            lastPackSwapBytes = new int[1],
            lastPackLsbFirst = new int[1],
            lastPackRowLength = new int[1],
            lastPackImageHeight = new int[1],
            lastPackSkipPixels = new int[1],
            lastPackSkipRows = new int[1],
            lastPackSkipImages = new int[1],
            lastPackAlignment = new int[1],
            lastUnpackSwapBytes = new int[1],
            lastUnpackLsbFirst = new int[1],
            lastUnpackImageHeight = new int[1],
            lastUnpackSkipImages = new int[1];

    private final BitSet flags = new BitSet(7);

    public boolean isLastEnableBlend() {
        return flags.get(0);
    }

    public void setLastEnableBlend(boolean value) {
        flags.set(0, value);
    }

    public boolean isLastEnableCullFace() {
        return flags.get(1);
    }

    public void setLastEnableCullFace(boolean value) {
        flags.set(1, value);
    }

    public boolean isLastEnableDepthTest() {
        return flags.get(2);
    }

    public void setLastEnableDepthTest(boolean value) {
        flags.set(2, value);
    }

    public boolean isLastEnableStencilTest() {
        return flags.get(3);
    }

    public void setLastEnableStencilTest(boolean value) {
        flags.set(3, value);
    }

    public boolean isLastEnableScissorTest() {
        return flags.get(4);
    }

    public void setLastEnableScissorTest(boolean value) {
        flags.set(4, value);
    }

    public boolean isLastEnablePrimitiveRestart() {
        return flags.get(5);
    }

    public void setLastEnablePrimitiveRestart(boolean value) {
        flags.set(5, value);
    }

    public boolean isLastDepthMask() {
        return flags.get(6);
    }

    public void setLastDepthMask(boolean value) {
        flags.set(6, value);
    }
}
