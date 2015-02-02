package org.nuvola.indexeddb.samples.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.nuvola.indexeddb.client.ConnectionCallback;
import org.nuvola.indexeddb.client.IDBCallback;
import org.nuvola.indexeddb.client.IDBDatabase;
import org.nuvola.indexeddb.client.IDBDatabaseOptionalParameters;
import org.nuvola.indexeddb.client.IDBEvent;
import org.nuvola.indexeddb.client.IDBException;
import org.nuvola.indexeddb.client.IDBObjectStore;
import org.nuvola.indexeddb.client.IDBRequest;
import org.nuvola.indexeddb.client.IDBTransaction;
import org.nuvola.indexeddb.samples.shared.User;

import com.github.nmorel.gwtjackson.client.ObjectReader;
import com.github.nmorel.gwtjackson.client.ObjectWriter;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class IDBTest implements EntryPoint {
    public static interface UserWriter extends ObjectWriter<User> {
    }

    public static interface UserReader extends ObjectReader<User> {
    }

    private static final UserWriter USER_WRITER = GWT.create(UserWriter.class);
    private static final UserReader USER_READER = GWT.create(UserReader.class);

    private IDBDatabase database;

    @Override
    public void onModuleLoad() {
        openDatabaseConnection("test_db", 5L);
    }

    private void openDatabaseConnection(final String databaseName, Long version) {
        IDBDatabase.open(databaseName, version, new ConnectionCallback() {
            @Override
            public void onFailure(Throwable throwable) {
                Window.alert("Can't open connection to database");
            }

            @Override
            public void onUpgradeNeeded(IDBDatabase idbDatabase) {
                database = idbDatabase;
                initialzeStructure(idbDatabase);
            }

            @Override
            public void onSuccess(IDBDatabase idbDatabase) {
                database = idbDatabase;
                insertData();
            }
        });
    }

    private void initialzeStructure(IDBDatabase idbDatabase) {
        try {
            // Create user object store
            IDBDatabaseOptionalParameters parameters = IDBDatabaseOptionalParameters.create("id", true);
            idbDatabase.createObjectStore("users", parameters);
        } catch (IDBException e) {
            Window.alert(e.getMessage());
        }
    }

    private void insertData() {
        try {
            IDBTransaction tx = database.transaction(new String[] {"users"});
            IDBObjectStore objectStore = tx.objectStore("users");

            List<User> users = new ArrayList<>();
            users.add(new User("FG383", "Idriss", "Mrabti", "imrabti@gmail.com", new Date()));
            users.add(new User("FT534", "Johanne", "Jane", "jjane@gmail.com", new Date()));
            users.add(new User("FX938", "Jack", "Black", "jblack@gmail.com", new Date()));
            users.add(new User("FZ736", "Medinna", "Misouri", "mmisouri@gmail.com", new Date()));

            for (User user : users) {
                loadData(user.getId(), new AsyncCallback<User>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        Window.alert(throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(User user) {
                        if (user != null) {
                            Window.alert("User with name " + user.getFirstName() + " " + user.getLastName());
                        }
                    }
                });

                JSONObject userJson = JSONParser.parseStrict(USER_WRITER.write(user)).isObject();
                objectStore.add(userJson.getJavaScriptObject());
            }
        } catch (IDBException e) {
            Window.alert(e.getMessage());
        }
    }

    private void loadData(String id, final AsyncCallback<User> callback) {
        try {
            IDBTransaction tx = database.transaction(new String[] {"users"});
            IDBObjectStore objectStore = tx.objectStore("users");

            IDBRequest request = objectStore.get(id);
            request.onSuccess(new IDBCallback() {
                @Override
                public void onEvent(IDBEvent pEvent) {
                    JSONObject object = new JSONObject(pEvent.targetResult());
                    User user = USER_READER.read(object.toString());
                    callback.onSuccess(user);
                }
            });
        } catch (IDBException e) {
            callback.onFailure(e);
        }
    }
}
