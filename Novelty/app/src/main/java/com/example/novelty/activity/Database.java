package com.example.novelty.activity;

import android.nfc.Tag;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;

public class Database {
    public static FirebaseFirestore db = FirebaseFirestore.getInstance();
//    public static FirebaseStorage storage = FirebaseStorage.getInstance();
//    public static StorageReference storageRef = storage.getReference();

    /**
     * gets reference
     * @param userID unique userID for user from firebase auth.
     * @return document reference for the user
     */
    public static DocumentReference getUserRef(String userID) {
        return Database.db.collection("users")
                .document(userID);
    }

    public static DocumentReference getBookInfo(String ISBN) {
        return Database.db.collection("books")
                .document(ISBN);
    }
    public static void addBookInfo(HashMap book, String ISBN) {
        Database.db.collection("books").document(ISBN).set(book);
    }

    /**
     * gets a book's reference
     * @param collec collection reference for the books
     * @param ISBN unique ISBN of the book to access document
     * @return document reference for the specified book
     */
    public static DocumentReference getBookRef(CollectionReference collec, String ISBN) {
        return collec.document(ISBN);
    }

    /**
     * The books that I have available
     * @param userID string unique userID from firebase auth.
     * @return collection reference to the "available"
     */
    public static CollectionReference userAvailRef(String userID) {
        return Database.db.collection("users")
                .document(userID).collection("available");
    }

    /**
     * The books that I'm borrowed
     * @param userID string unique userID from firebase auth.
     * @return collection reference to the "borrowed"
     */
    public static CollectionReference userBorrowedRef(String userID) {
        return Database.db.collection("users")
                .document(userID).collection("borrowed");
    }

    /**
     * as an owner, the requests that I received
     * @param userID string unique userID from firebase auth.
     * @return collection reference to the "received requests"
     */
    public static CollectionReference userReceivedRequestRef(String userID) {
        return Database.db.collection("users")
                .document(userID).collection("requested");
    }

    /**
     * as a borrower, the requests that I made
     * @param userID string unique userID from firebase auth.
     * @return collection reference to the "my requets"
     */
    public static CollectionReference userRequestRef(String userID) {
        return Database.db.collection("users")
                .document(userID).collection("my requests");
    }

    /**
     * as an owner, the requests that I accepted
     * @param userID string unique userID from firebase auth.
     * @return collection reference to the "accepted requests"
     */
    public static CollectionReference userAcceptedRef(String userID) {
        return Database.db.collection("users")
                .document(userID).collection("accepted");
    }

    /**
     * as a borrower, my requests that got accepted
     * @param userID string unique userID from firebase auth.
     * @return collection reference to "my accepted requests"
     */
    public static CollectionReference userRequestAcceptedRef(String userID) {
        return Database.db.collection("users")
                .document(userID).collection("my accepted requests");
    }

}
