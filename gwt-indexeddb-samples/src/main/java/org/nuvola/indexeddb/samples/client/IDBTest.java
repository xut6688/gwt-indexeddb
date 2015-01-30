package org.nuvola.indexeddb.samples.client;

import org.nuvola.indexeddb.client.IDBDatabase;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class IDBTest implements EntryPoint {
    private IDBDatabase database;

    @Override
    public void onModuleLoad() {
        openDatabaseConnection("test_db", "2");
    }

    private void openDatabaseConnection(String databaseName, final String version) {
        IDBDatabase.open(databaseName, new AsyncCallback<IDBDatabase>() {
            @Override
            public void onFailure(Throwable throwable) {
                Window.alert("Can't open connection to database");
            }

            @Override
            public void onSuccess(IDBDatabase idbDatabase) {
                database = idbDatabase;
            }
        });
    }
}
