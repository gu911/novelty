package com.example.novelty.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.novelty.R;
import com.example.novelty.adapter.CustomList_Book;
import com.example.novelty.adapter.NestedListView;
import com.example.novelty.bean.BookBean;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //public static final int VIEW_EDIT_BOOK = 111;
    //public static final int ADD_BOOK = 112;

    FirebaseAuth fAuth;
    private String userID;

    FloatingActionButton fab_scan, fab_lend, fab_return, fab_view, fab_confirm;
    Animation fabOpen, fabClose, fabRClockwise, fabRCounterClockwise;

    NestedListView bookList;
    ArrayList<BookBean> bookDataList;
    ArrayAdapter<BookBean> bookAdapter;

    ArrayList<BookBean> dataList1;
    ArrayList<BookBean> dataList2;
    ArrayList<BookBean> dataList3;
    ArrayList<BookBean> dataList4;
    ArrayList<BookBean> dataList5;
    ArrayList<BookBean> dataList6;

    int select_pos = -1;

    private DrawerLayout drawerlayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    private Toolbar mToolbar;

    boolean isOpen = false;
    private Intent mIntent;

    private BottomAppBar bottomAppBar;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fAuth = FirebaseAuth.getInstance();
        userID = fAuth.getCurrentUser().getUid();

        bookList = findViewById(R.id.mainscreen_list);
        bookDataList = new ArrayList<>();
        bookAdapter = new CustomList_Book(this, bookDataList);

        dataList1 = new ArrayList<>();
        dataList2 = new ArrayList<>();
        dataList3 = new ArrayList<>();
        dataList4 = new ArrayList<>();
        dataList5 = new ArrayList<>();
        dataList6 = new ArrayList<>();

        bookList.setAdapter(bookAdapter);
        bookList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        Database.userAvailRef(userID).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException error) {
                bookDataList.clear();
                dataList1.clear();
                for(QueryDocumentSnapshot doc: queryDocumentSnapshots) {
                    String title = (String) doc.getData().get("Title");
                    String ISBN = (String) doc.getData().get("ISBN");
                    String author = (String) doc.getData().get("Author");
                    String holder = (String) doc.getData().get("Holder");
                    String description = (String) doc.getData().get("Description");
                    String status = (String) doc.getData().get("Status");

                    BookBean newBook = new BookBean(title);
                    newBook.setISBN(ISBN);
                    newBook.setAuthor(author);
                    newBook.setHolder(holder);
                    newBook.setDescription(description);
                    newBook.setStatus(status);

                    dataList1.add(newBook);
                }

                bookDataList.addAll(dataList1);
                bookDataList.addAll(dataList2);
                bookDataList.addAll(dataList3);
                bookDataList.addAll(dataList4);
                bookDataList.addAll(dataList5);
                bookDataList.addAll(dataList6);

                bookAdapter.notifyDataSetChanged();
            }
        });


        Database.userRequestRef(userID).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException error) {
                bookDataList.clear();
                dataList2.clear();
                for(QueryDocumentSnapshot doc: queryDocumentSnapshots) {
                    String title = (String) doc.getData().get("Title");
                    String ISBN = (String) doc.getData().get("ISBN");
                    String author = (String) doc.getData().get("Author");
                    String holder = (String) doc.getData().get("Holder");
                    String description = (String) doc.getData().get("Description");
                    String status = (String) doc.getData().get("Status");

                    BookBean newBook = new BookBean(title);
                    newBook.setISBN(ISBN);
                    newBook.setAuthor(author);
                    newBook.setHolder(holder);
                    newBook.setDescription(description);
                    newBook.setStatus(status);

                    dataList2.add(newBook);
                }

                bookDataList.addAll(dataList1);
                bookDataList.addAll(dataList2);
                bookDataList.addAll(dataList3);
                bookDataList.addAll(dataList4);
                bookDataList.addAll(dataList5);
                bookDataList.addAll(dataList6);

                bookAdapter.notifyDataSetChanged();
            }
        });


        Database.userAcceptedRef(userID).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException error) {
                bookDataList.clear();
                dataList3.clear();
                for(QueryDocumentSnapshot doc: queryDocumentSnapshots) {
                    String title = (String) doc.getData().get("Title");
                    String ISBN = (String) doc.getData().get("ISBN");
                    String author = (String) doc.getData().get("Author");
                    String holder = (String) doc.getData().get("Holder");
                    String description = (String) doc.getData().get("Description");
                    String status = (String) doc.getData().get("Status");

                    BookBean newBook = new BookBean(title);
                    newBook.setISBN(ISBN);
                    newBook.setAuthor(author);
                    newBook.setHolder(holder);
                    newBook.setDescription(description);
                    newBook.setStatus(status);

                    dataList3.add(newBook);
                }

                bookDataList.addAll(dataList1);
                bookDataList.addAll(dataList2);
                bookDataList.addAll(dataList3);
                bookDataList.addAll(dataList4);
                bookDataList.addAll(dataList5);
                bookDataList.addAll(dataList6);

                bookAdapter.notifyDataSetChanged();
            }
        });


        Database.userBorrowedRef(userID).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException error) {
                bookDataList.clear();
                dataList4.clear();
                for(QueryDocumentSnapshot doc: queryDocumentSnapshots) {
                    String title = (String) doc.getData().get("Title");
                    String ISBN = (String) doc.getData().get("ISBN");
                    String author = (String) doc.getData().get("Author");
                    String holder = (String) doc.getData().get("Holder");
                    String description = (String) doc.getData().get("Description");
                    String status = (String) doc.getData().get("Status");

                    BookBean newBook = new BookBean(title);
                    newBook.setISBN(ISBN);
                    newBook.setAuthor(author);
                    newBook.setHolder(holder);
                    newBook.setDescription(description);
                    newBook.setStatus(status);

                    dataList4.add(newBook);
                }

                bookDataList.addAll(dataList1);
                bookDataList.addAll(dataList2);
                bookDataList.addAll(dataList3);
                bookDataList.addAll(dataList4);
                bookDataList.addAll(dataList5);
                bookDataList.addAll(dataList6);
                bookAdapter.notifyDataSetChanged();
            }
        });

        Database.userReceivedRequestRef(userID).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException error) {
                bookDataList.clear();
                dataList5.clear();
                for(QueryDocumentSnapshot doc: queryDocumentSnapshots) {
                    String title = (String) doc.getData().get("Title");
                    String ISBN = (String) doc.getData().get("ISBN");
                    String author = (String) doc.getData().get("Author");
                    String holder = (String) doc.getData().get("Holder");
                    String description = (String) doc.getData().get("Description");
                    String status = (String) doc.getData().get("Status");

                    BookBean newBook = new BookBean(title);
                    newBook.setISBN(ISBN);
                    newBook.setAuthor(author);
                    newBook.setHolder(holder);
                    newBook.setDescription(description);
                    newBook.setStatus(status);

                    dataList5.add(newBook);
                }

                bookDataList.addAll(dataList1);
                bookDataList.addAll(dataList2);
                bookDataList.addAll(dataList3);
                bookDataList.addAll(dataList4);
                bookDataList.addAll(dataList5);
                bookDataList.addAll(dataList6);
                bookAdapter.notifyDataSetChanged();
            }
        });

        Database.userRequestAcceptedRef(userID).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException error) {
                bookDataList.clear();
                dataList6.clear();
                for(QueryDocumentSnapshot doc: queryDocumentSnapshots) {
                    String title = (String) doc.getData().get("Title");
                    String ISBN = (String) doc.getData().get("ISBN");
                    String author = (String) doc.getData().get("Author");
                    String holder = (String) doc.getData().get("Holder");
                    String description = (String) doc.getData().get("Description");
                    String status = (String) doc.getData().get("Status");

                    BookBean newBook = new BookBean(title);
                    newBook.setISBN(ISBN);
                    newBook.setAuthor(author);
                    newBook.setHolder(holder);
                    newBook.setDescription(description);
                    newBook.setStatus(status);

                    dataList6.add(newBook);
                }

                bookDataList.addAll(dataList1);
                bookDataList.addAll(dataList2);
                bookDataList.addAll(dataList3);
                bookDataList.addAll(dataList4);
                bookDataList.addAll(dataList5);
                bookDataList.addAll(dataList6);

                bookAdapter.notifyDataSetChanged();
            }
        });




        bookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                select_pos = position;

                BookBean book = bookDataList.get(position);

                Intent viewEditIntent = new Intent(MainActivity.this, ViewEditBook.class);
                viewEditIntent.putExtra("book", book);
                startActivity(viewEditIntent);
                select_pos = -1;
            }
        });


        fab_scan = findViewById(R.id.flt_scan);
        fab_lend = findViewById(R.id.flt_lend);
        fab_return = findViewById(R.id.flt_return);
        fab_view = findViewById(R.id.flt_view);
        fab_confirm = findViewById(R.id.flt_confirm);

        fabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fabRClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_clockwise);
        fabRCounterClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_counterclockwise);


        mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);


        drawerlayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation_view);

        toggle = new ActionBarDrawerToggle(this, drawerlayout, R.string.open, R.string.close);

        drawerlayout.addDrawerListener(toggle);
        toggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);

        bottomAppBar = findViewById(R.id.bottomAppBar);

        bottomAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call the FilterDialog class to show the dialog fragment
                new FilterDialog().show(getSupportFragmentManager(), "filterdialog");

            }
        });

        bottomAppBar.setOnMenuItemClickListener(new BottomAppBar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuAdd:
                        Intent addIntent = new Intent(MainActivity.this, AddBook.class);
                        startActivity(addIntent);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });


        fab_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isOpen) {

                    fab_view.startAnimation(fabClose);
                    fab_lend.startAnimation(fabClose);
                    fab_return.startAnimation(fabClose);
                    fab_confirm.startAnimation(fabClose);
                    fab_scan.startAnimation(fabRClockwise);

                    fab_view.setClickable(false);
                    fab_lend.setClickable(false);
                    fab_return.setClickable(false);
                    fab_confirm.setClickable(false);

                    isOpen = false;
                } else {

                    fab_view.startAnimation(fabOpen);
                    fab_lend.startAnimation(fabOpen);
                    fab_return.startAnimation(fabOpen);
                    fab_confirm.startAnimation(fabOpen);
                    fab_scan.startAnimation(fabRCounterClockwise);

                    fab_view.setClickable(true);
                    fab_lend.setClickable(true);
                    fab_return.setClickable(true);
                    fab_confirm.setClickable(true);

                    isOpen = true;

                }
            }
        });


        fab_lend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Lend book", Toast.LENGTH_SHORT).show();
                Intent lend_intent = new Intent(getApplicationContext(), LendBook.class);
                startActivityForResult(lend_intent, 1);
            }
        });

        fab_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Return book", Toast.LENGTH_SHORT).show();

            }
        });

        fab_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "View book details", Toast.LENGTH_SHORT).show();
            }
        });

        fab_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Confirm", Toast.LENGTH_SHORT).show();
                Intent lend_intent = new Intent(getApplicationContext(), LendBook.class);
                startActivityForResult(lend_intent, 1);
            }
        });
    }


    //send user to Login activity once logged out.
    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish(); //
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mIntent = new Intent();
        switch (item.getItemId()) {
            case R.id.nav_log_out:
                //send user to Login activity once logged out.
                Toast.makeText(MainActivity.this, "Log out", Toast.LENGTH_SHORT).show();
                logout(findViewById(R.id.nav_log_out));
                break;

            case R.id.nav_contact_info:
                Toast.makeText(MainActivity.this, "Contact information", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_search_book:
                mIntent.setClass(MainActivity.this, SearchBookActivity.class);
                startActivity(mIntent);
                break;
            case R.id.nav_search_user:
                mIntent.setClass(MainActivity.this, SearchUserActivity.class);
                startActivity(mIntent);
                break;
            case R.id.nav_my_request:
                mIntent.setClass(MainActivity.this, MyRequestActivity.class);
                startActivity(mIntent);
                break;

        }


        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            default:
                break;
        }
    }

}