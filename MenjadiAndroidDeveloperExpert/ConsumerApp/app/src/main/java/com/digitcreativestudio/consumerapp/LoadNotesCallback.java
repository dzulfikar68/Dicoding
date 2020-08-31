package com.digitcreativestudio.consumerapp;

import android.database.Cursor;

interface LoadNotesCallback {
    void postExecute(Cursor notes);
}
