package io.github.humbleui.jwm;

import org.jetbrains.annotations.ApiStatus;
import io.github.humbleui.jwm.impl.RefCounted;

public class LayerRaster extends RefCounted implements Layer {
    @ApiStatus.Internal public Window _window;
    @ApiStatus.Internal public int _width;
    @ApiStatus.Internal public int _height;

    public LayerRaster() {
        super(_nMake());
    }

    @Override
    public void attach(Window window) {
        assert _onUIThread();
        _nAttach(window);
        _window = window;
    }

    @Override
    public void reconfigure() {
        assert _onUIThread();
        _nReconfigure();
    }

    @Override
    public void resize(int width, int height) {
        assert _onUIThread();
        _width = width;
        _height = height;
        _nResize(width, height);
    }

    @Override
    public int getWidth() {
        assert _onUIThread();
        return _width;
    }

    @Override
    public int getHeight() {
        assert _onUIThread();
        return _height;
    }

    @Override
    public void swapBuffers() {
        assert _onUIThread();
        _nSwapBuffers();
    }

    @Override
    public void close() {
        assert _onUIThread();
        _nClose();
        super.close();
    }

    public long getPixelsPtr() {
       assert _onUIThread();
       return _nGetPixelsPtr();
    }

    public int getRowBytes() {
       assert _onUIThread();
       return _nGetRowBytes();
    }

    @ApiStatus.Internal public static boolean _onUIThread() {
        return App._onUIThread();
    }

    @ApiStatus.Internal public static native long _nMake();
    @ApiStatus.Internal public native void _nAttach(Window window);
    @ApiStatus.Internal public native void _nReconfigure();
    @ApiStatus.Internal public native void _nResize(int width, int height);
    @ApiStatus.Internal public native void _nSwapBuffers();
    @ApiStatus.Internal public native long _nGetPixelsPtr();
    @ApiStatus.Internal public native int _nGetRowBytes();
    @ApiStatus.Internal public native void _nClose();
}