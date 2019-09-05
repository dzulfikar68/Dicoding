package com.digitcreativestudio.consumerapp;

import android.database.Cursor;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.digitcreativestudio.consumerapp.DatabaseContract.NoteColumns.DATE;
import static com.digitcreativestudio.consumerapp.DatabaseContract.NoteColumns.DESCRIPTION;
import static com.digitcreativestudio.consumerapp.DatabaseContract.NoteColumns.TITLE;

public class MappingHelper {
    public static ArrayList<NoteItem> mapCursorToArrayList(Cursor notesCursor) {
        ArrayList<NoteItem> notesList = new ArrayList<>();

        while (notesCursor.moveToNext()) {
            int id = notesCursor.getInt(notesCursor.getColumnIndexOrThrow(_ID));
            String title = notesCursor.getString(notesCursor.getColumnIndexOrThrow(TITLE));
            String description = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DESCRIPTION));
            String date = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DATE));
            notesList.add(new NoteItem(id, title, description, date));
        }

        return notesList;
    }
}
