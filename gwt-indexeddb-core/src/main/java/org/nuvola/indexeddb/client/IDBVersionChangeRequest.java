package org.nuvola.indexeddb.client;

public class IDBVersionChangeRequest extends IDBRequest {
    protected IDBVersionChangeRequest() {
        super();
    }

    public final native void onBlocked(IDBCallback pCallback) /*-{
        this.onblocked = function (evt) {
            @org.nuvola.indexeddb.client.IDBRequest::fireCallback(Lorg/nuvola/indexeddb/client/IDBCallback;Lorg/nuvola/indexeddb/client/IDBEvent;)(pCallback, evt);
        };
    }-*/;
}
