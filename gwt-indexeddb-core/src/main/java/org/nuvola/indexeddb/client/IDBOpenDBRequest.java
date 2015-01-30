package org.nuvola.indexeddb.client;

public class IDBOpenDBRequest extends IDBRequest {
    protected IDBOpenDBRequest() {
        super();
    }

    public final native void onBlocked(IDBCallback pCallback) /*-{
        this.onblocked = function (evt) {
            @org.nuvola.indexeddb.client.IDBRequest::fireCallback(Lorg/nuvola/indexeddb/client/IDBCallback;Lorg/nuvola/indexeddb/client/IDBEvent;)(pCallback, evt);
        };
    }-*/;

    public final native void onUpgradeNeeded(IDBCallback pCallback) /*-{
        this.onupgradeneeded = function (evt) {
            @org.nuvola.indexeddb.client.IDBRequest::fireCallback(Lorg/nuvola/indexeddb/client/IDBCallback;Lorg/nuvola/indexeddb/client/IDBEvent;)(pCallback, evt);
        };
    }-*/;
}
