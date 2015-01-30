package org.nuvola.indexeddb.client;

public interface IDBCursorCallback {
    public boolean onKeyValue(IDBKeyValueRecord pRecord);

    public void onDone();

    public void onError(IDBEvent pEvent);
}

