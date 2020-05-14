package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;


public class ActivityTwo extends AppCompatActivity implements View.OnClickListener {

    EditText editName;
    EditText editSurname;
    RadioButton checkMale;
    RadioButton checkFemale;
    Button newPhoto;
    Button save;
    DBHelper dbHelper;
    private final int Pick_image = 1;
    CircleImageView imageView;
    Uri fileUri;

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

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View view) {
        ContentValues cv = new ContentValues();

        String edtName = editName.getText().toString();
        String edtSurname = editSurname.getText().toString();
        String edtSex = "who?";
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (view.getId()){
            case R.id.save:
                cv.put("name", edtName);
                cv.put("surname", edtSurname);
                if (checkMale.isChecked() == true){
                    edtSex = "Male";
                } else if(checkFemale.isChecked()==true){
                    edtSex = "Female";
                }
                cv.put("sex", edtSex);
                db.insert("mytable", null, cv);
                finish();
                break;
            case R.id.newPhoto:
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, Pick_image);
                break;
        }
        db.close();
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
//        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
//
//        imageView = findViewById(R.id.image);
//
//        switch (requestCode) {
//            case Pick_image:
//                if (resultCode == RESULT_OK) {
//                    fileUri = imageReturnedIntent.getData();
//                    InputStream imageStream;
//                    try {
//                        imageStream = getContentResolver().openInputStream(fileUri);
//                        Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
//                        imageView.setImageBitmap(selectedImage);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
//        }
//    }
}
