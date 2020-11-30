package com.example.novelty.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.novelty.R;
import com.example.novelty.bean.BookBean;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ViewEditBook extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    public static final int UPLOAD_PHOTO = 100;

    private Button uploadPhotoButton;
    private Button deletePhotoButton;
    private ImageView photoView;
    private Button cancelButton;
    private Button saveButton;
    private EditText editBookTitle;
    private EditText editAuthor;
    private EditText editHolder;
    private EditText editISBN;
    private EditText editDescription;
    private Button deleteButton;

    private FirebaseAuth fAuth;
    private String userID;

    private String ISBN;
    private String status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_edit_book);

        fAuth = FirebaseAuth.getInstance();

        uploadPhotoButton = findViewById(R.id.btn_upload);
        deletePhotoButton = findViewById(R.id.btn_deletePhoto);
        photoView = findViewById(R.id.photoView);
        cancelButton = findViewById(R.id.btn_cancel);
        saveButton = findViewById(R.id.btn_save);
        deleteButton = findViewById(R.id.btn_delete);

        editBookTitle = findViewById(R.id.editBookTitle);
        editAuthor = findViewById(R.id.editTextAuthor);
        editHolder = findViewById(R.id.editTextHolder);
        editISBN = findViewById(R.id.editTextISBN);
        editDescription = findViewById(R.id.editTextTextMultiLine);

        final Spinner spinner = findViewById(R.id.status_spinner2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        BookBean book = (BookBean) getIntent().getSerializableExtra("book");

        status = book.getStatus();
        ISBN = book.getISBN();

        if (book.getTitle() != null) {
            editBookTitle.setText(book.getTitle());
        }

        if (book.getAuthor() != null) {
            editAuthor.setText(book.getAuthor());
        }

        if (book.getDescription() != null) {
            editDescription.setText(book.getDescription());
        }

        if (book.getHolder() != null) {
            editHolder.setText(book.getHolder());
        }

        if (book.getISBN() != null) {
            editISBN.setText(book.getISBN());
        }


        if (book.getStatus() != null) {
            for (int i = 0; i < adapter.getCount(); i++) {
                if (book.getStatus().equals(adapter.getItem(i).toString())) {
                    spinner.setSelection(i);
                    break;
                } else {
                    spinner.setSelection(0);
                }
            }
        } else {
            spinner.setSelection(0);
        }


        photoView.setBackgroundColor(Color.LTGRAY);

        uploadPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent uploadIntent = new Intent(ViewEditBook.this, UploadPhoto.class);
                startActivityForResult(uploadIntent, UPLOAD_PHOTO);
            }
        });

        deletePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoView.setImageResource(0);
            }
        });


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (status) {
                    case "available":
                        Database.userAvailRef(userID).document(ISBN).delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d("Sample", "Data has been deleted successfully!");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("Sample", "Data could not be deleted!" + e.toString());
                                    }
                                });
                        break;

                    case "my requests":
                        Database.userRequestRef(userID).document(ISBN).delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d("Sample", "Data has been deleted successfully!");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("Sample", "Data could not be deleted!" + e.toString());
                                    }
                                });
                        break;

                    case "accepted requests":
                        Database.userAcceptedRef(userID).document(ISBN).delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d("Sample", "Data has been deleted successfully!");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("Sample", "Data could not be deleted!" + e.toString());
                                    }
                                });
                        break;

                    case "borrowed":
                        Database.userBorrowedRef(userID).document(ISBN).delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d("Sample", "Data has been deleted successfully!");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("Sample", "Data could not be deleted!" + e.toString());
                                    }
                                });
                        break;

                    case "received requests":
                        Database.userReceivedRequestRef(userID).document(ISBN).delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d("Sample", "Data has been deleted successfully!");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("Sample", "Data could not be deleted!" + e.toString());
                                    }
                                });
                        break;

                    case "my accepted requests":
                        Database.userRequestAcceptedRef(userID).document(ISBN).delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d("Sample", "Data has been deleted successfully!");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("Sample", "Data could not be deleted!" + e.toString());
                                    }
                                });
                        break;

                    default:
                        break;
                }

                finish();
            }
        });


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        userID = fAuth.getCurrentUser().getUid();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ISBN = editISBN.getText().toString();
                String author = editAuthor.getText().toString();
                String title = editBookTitle.getText().toString();
                String holder = editHolder.getText().toString();
                String description = editDescription.getText().toString();
                String status = spinner.getSelectedItem().toString();

                if (ISBN.length() > 0 && author.length() > 0 && title.length() > 0) {
                    Map<String, Object> bookMap = new HashMap<>();
                    bookMap.put("ISBN", ISBN);
                    bookMap.put("Title", title);
                    bookMap.put("Author", author);
                    bookMap.put("Holder", holder);
                    bookMap.put("Description", description);
                    bookMap.put("Status", status);

                    if (status.length() > 0) {
                        switch (status) {
                            case "available":
                                Database.userAvailRef(userID).document(ISBN).set(bookMap)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.d("Sample", "Data has been added successfully!");
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.d("Sample", "Data could not be added!" + e.toString());
                                            }
                                        });
                                break;

                            case "my requests":
                                Database.userRequestRef(userID).document(ISBN).set(bookMap)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.d("Sample", "Data has been added successfully!");
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.d("Sample", "Data could not be added!" + e.toString());
                                            }
                                        });
                                break;

                            case "accepted requests":
                                Database.userAcceptedRef(userID).document(ISBN).set(bookMap)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.d("Sample", "Data has been added successfully!");
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.d("Sample", "Data could not be added!" + e.toString());
                                            }
                                        });
                                break;

                            case "borrowed":
                                Database.userBorrowedRef(userID).document(ISBN).set(bookMap)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.d("Sample", "Data has been added successfully!");
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.d("Sample", "Data could not be added!" + e.toString());
                                            }
                                        });
                                break;

                            case "received requests":
                                Database.userReceivedRequestRef(userID).document(ISBN).set(bookMap)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.d("Sample", "Data has been added successfully!");
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.d("Sample", "Data could not be added!" + e.toString());
                                            }
                                        });
                                break;
                            case "my accepted requests":
                                Database.userRequestAcceptedRef(userID).document(ISBN).set(bookMap)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.d("Sample", "Data has been added successfully!");
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.d("Sample", "Data could not be added!" + e.toString());
                                            }
                                        });
                                break;

                            default:
                                break;
                        }
                    }

                    finish();
                }

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case UPLOAD_PHOTO:
                if (resultCode == 2) {
                    Uri imageUri = Uri.parse(data.getStringExtra("returnPhoto"));
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        photoView.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String statusNow = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), statusNow, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

