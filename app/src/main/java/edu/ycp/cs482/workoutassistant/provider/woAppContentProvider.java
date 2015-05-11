package edu.ycp.cs482.workoutassistant.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import org.apache.http.auth.AUTH;

import edu.ycp.cs482.workoutassistant.DBHandler;

public class woAppContentProvider extends ContentProvider {

    private DBHandler handler;

    private static final String AUTHORITY = "edu.ycp.cs482.workoutassistant.provider.woAppContentProvider";
    private static final String WORKOUTS_TABLE = "workouts";

    // create URI
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + WORKOUTS_TABLE);

    // identifiers for if the Uri is referencing the entire table or 1 row
    public static final int WORKOUTS = 1;
    public static final int WORKOUTS_ID = 2;

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sURIMatcher.addURI(AUTHORITY,WORKOUTS_TABLE,WORKOUTS);
        sURIMatcher.addURI(AUTHORITY,WORKOUTS_TABLE + "/#",WORKOUTS_ID);
    }



    public woAppContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase db = handler.getWritableDatabase();
        int rowsDeleted = 0;

        switch(uriType) {
            case WORKOUTS:
                rowsDeleted = db.delete(handler.TABLE_WORKOUTS,selection,selectionArgs);
                break;

            case WORKOUTS_ID:
                String id  = uri.getLastPathSegment();
                if(TextUtils.isEmpty(selection)){
                    rowsDeleted = db.delete(handler.TABLE_WORKOUTS,handler.COLUMN_ID + "=" + id, null);
                }
                else {
                    rowsDeleted = db.delete(handler.TABLE_WORKOUTS, handler.COLUMN_ID + "=" + id + " and " + selection,selectionArgs);
                }
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = sURIMatcher.match(uri);

        SQLiteDatabase db = handler.getWritableDatabase();

        long id =  0;
        switch(uriType) {
            case WORKOUTS:
                id = db.insert(handler.TABLE_WORKOUTS,null,values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(WORKOUTS_TABLE + "/" + id);
    }

    @Override
    public boolean onCreate() {
        handler = new DBHandler(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qBuilder = new SQLiteQueryBuilder();
        qBuilder.setTables(handler.TABLE_WORKOUTS);

        int uriType = sURIMatcher.match(uri);

        switch(uriType){
            case WORKOUTS_ID:
                qBuilder.appendWhere(handler.COLUMN_ID + "=" + uri.getLastPathSegment());
                break;
            case WORKOUTS:
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        Cursor cursor = qBuilder.query(handler.getReadableDatabase(),projection,selection,selectionArgs,null,null,sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,String[] selectionArgs) {
        int uriType= sURIMatcher.match(uri);
        SQLiteDatabase db = handler.getWritableDatabase();
        int rowsUpdated = 0;

        switch (uriType) {
            case WORKOUTS:
                rowsUpdated = db.update(handler.TABLE_WORKOUTS,values,selection,selectionArgs);
                break;
            case WORKOUTS_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = db.update(handler.TABLE_WORKOUTS,values,handler.COLUMN_ID + "=" + id, null);
                }
                else {
                    rowsUpdated = db.update(handler.TABLE_WORKOUTS,values,handler.COLUMN_ID + "=" + id + " and " + selection,selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return rowsUpdated;
    }
}
