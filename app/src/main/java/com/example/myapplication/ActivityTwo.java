package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;


public class ActivityTwo extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_CODE_PERMISSION_READEXTERNAL_STORAGE = 123;
    final String TAG = "States";

    EditText editName;
    EditText editSurname;
    RadioButton checkMale;
    RadioButton checkFemale;
    Button newPhoto;
    Button save;
    //DBHelper dbHelper;
    AppDatabase db;
    HumanDao humanDao;
    private final int Pick_image = 1;
    ImageView imageView;
    Uri fileUri;
    Bitmap bitmap;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);


        editName = findViewById(R.id.editName);
        editSurname = findViewById(R.id.editSurname);
        checkMale = findViewById(R.id.checkMale);
        checkFemale = findViewById(R.id.checkFemale);

        newPhoto = findViewById(R.id.newPhoto);
        newPhoto.setOnClickListener(this);

        save = findViewById(R.id.save);
        save.setOnClickListener(this);

        //dbHelper = new DBHelper(this);
        Log.d(TAG, "SecondActivity: onCreate()");
    }

    @Override
    public void onClick(View view) {
        //ContentValues cv = new ContentValues();

//        String edtName = editName.getText().toString();
//        String edtSurname = editSurname.getText().toString();
//        String edtSex = "who?";
        //SQLiteDatabase db = dbHelper.getWritableDatabase();
        Log.d(TAG, "onClick");

        switch (view.getId()) {
            case R.id.save:
                Log.d(TAG, "Button: save");
                //cv.put("name", edtName);
                //cv.put("surname", edtSurname);
//                if (checkMale.isChecked() == true){
//                    edtSex = "Male";
//                } else if(checkFemale.isChecked()==true){
//                    edtSex = "Female";
//                }
                //cv.put("sex", edtSex);
                //cv.put("uri", String.valueOf(fileUri));
                //db.insert("mytable", null, cv);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        db = App.getInstance().getDatabase();
                        humanDao = db.humanDao();
                        id = humanDao.getAll().size();
                        Human human = new Human();
                        human.id = ++id;
                        Log.d(TAG, "profile id " + human.id);
                        human.name = editName.getText().toString();
                        human.secondName = editSurname.getText().toString();
                        if (checkMale.isChecked() == true) {
                            human.sex = "Male";
                        } else if (checkFemale.isChecked() == true) {
                            human.sex = "Female";
                        }
                        Log.d(TAG, "sex is "+human.sex);
                        human.photo = String.valueOf(fileUri);

                        humanDao.insert(human);
                        if (humanDao.getById(human.id).getId() == -1) {
                            Log.d(TAG, "profile has not been added");
                        } else {
                            Log.d(TAG, "profile " + human.getName() + " has been added");
                        }
                    }
                });
                thread.start();
                finish();
                break;
            case R.id.newPhoto:
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");

                int permissionStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

                if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
                    startActivityForResult(photoPickerIntent, Pick_image);
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE_PERMISSION_READEXTERNAL_STORAGE);
                }
                break;
        }
        //db.close();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION_READEXTERNAL_STORAGE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission granted
                } else {
                    // permission denied
                }
                return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        imageView = findViewById(R.id.newImage);

        switch (requestCode) {
            case Pick_image:
                if (resultCode == RESULT_OK) {
                    fileUri = imageReturnedIntent.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), fileUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imageView.setImageBitmap(bitmap);
                }
        }
    }
}
