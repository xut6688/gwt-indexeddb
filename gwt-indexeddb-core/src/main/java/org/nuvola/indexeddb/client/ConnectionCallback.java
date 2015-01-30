package org.nuvola.indexeddb.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ConnectionCallback extends AsyncCallback<IDBDatabase> {
    void onUpgradeNeeded(IDBDatabase idbDatabase);
}
