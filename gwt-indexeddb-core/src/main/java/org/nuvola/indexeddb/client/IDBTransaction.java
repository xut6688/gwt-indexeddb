package org.nuvola.indexeddb.client;

import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.core.client.JavaScriptObject;

public class IDBTransaction extends JavaScriptObject {
    public static final String READ_ONLY = "readonly";
    public static final String READ_WRITE = "readwrite";
    public static final String VERSION_CHANGE = "versionchange";

    private static native void init() /*-{
        $wnd.IDBTransaction = $wnd.IDBTransaction || $wnd.webkitIDBTransaction;
    }-*/;

    static {
        init();
    }

    protected IDBTransaction() {
    }

    public final native void onError(IDBCallback pCallback) /*-{
        this.onerror = function (evt) {
            @org.nuvola.indexeddb.client.IDBUtils::fireCallback(Lorg/nuvola/indexeddb/client/IDBCallback;Lorg/nuvola/indexeddb/client/IDBEvent;)(pCallback, evt);
        };
    }-*/;

    public final native void onAbort(IDBCallback pCallback) /*-{
        this.onabort = function (evt) {
            @org.nuvola.indexeddb.client.IDBUtils::fireCallback(Lorg/nuvola/indexeddb/client/IDBCallback;Lorg/nuvola/indexeddb/client/IDBEvent;)(pCallback, evt);
        };
    }-*/;

    public final native void onComplete(IDBCallback pCallback) /*-{
        this.oncomplete = function (evt) {
            @org.nuvola.indexeddb.client.IDBUtils::fireCallback(Lorg/nuvola/indexeddb/client/IDBCallback;Lorg/nuvola/indexeddb/client/IDBEvent;)(pCallback, evt);
        };
    }-*/;

    private native void abort0() throws JavaScriptException /*-{
        this.abort();
    }-*/;

    public final void abort() throws IDBException {
        try {
            abort0();
        } catch (JavaScriptException jex) {
            throw new IDBException(jex);
        }
    }

    public final native int mode() /*-{
        return this.mode;
    }-*/;

    public final native IDBDatabase db() /*-{
        return this.db;
    }-*/;

    private native IDBObjectStore objectStore0(String pName) throws JavaScriptException /*-{
        return this.objectStore(pName);
    }-*/;

    public final IDBObjectStore objectStore(String pName) throws IDBException {
        try {
            return objectStore0(pName);
        } catch (JavaScriptException jex) {
            throw new IDBException(jex);
        }
    }
}