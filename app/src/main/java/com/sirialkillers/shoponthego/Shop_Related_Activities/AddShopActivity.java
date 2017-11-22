package com.sirialkillers.shoponthego.Shop_Related_Activities;


import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.net.Uri;

import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.sirialkillers.shoponthego.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class AddShopActivity extends AppCompatActivity {
    private EditText titleEditText;
    private ImageView shopImage;
    private TextView shopCategoriesTextView;
    private TextView shopAddressTextView;
    private String shopCategories;
    private Address shopAddress;
    private LatLng shopsLatLng;

    String sCategories;
    String[] categories;
    boolean[] checkedCategories;
    ArrayList<Integer> mShopCategories = new ArrayList<>();

    private final static int REQUEST_CAMERA = 1;
    private final static int SELECT_FILE = 0;

    private final static int MY_PERMISSION_FINE_LOCATION = 101;
    private final static int PLACE_PICKER_REQUEST = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop);

        requestPermission();

        shopAddressTextView = (TextView) findViewById(R.id.addressTextView);
        shopCategoriesTextView = (TextView) findViewById(R.id.categoriesTextView);

        titleEditText = (EditText) findViewById(R.id.titleEditText);

        shopImage = (ImageView) findViewById(R.id.shopPhotoImageView);

        categories = getResources().getStringArray(R.array.productCategories);
        checkedCategories = new boolean[categories.length];

        TextView selectCategories = (TextView) findViewById(R.id.selectCategoriesTextView);
        selectCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCategoriesClick();
            }
        });

        final TextView selectAddress = (TextView) findViewById(R.id.selectAddressTextView);

        selectAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAddressClick();
            }
        });

        Button addPhoto = (Button) findViewById(R.id.addPhotoButton);
        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPhotoClick();
            }
        });

        Button submitShop = (Button) findViewById(R.id.submitShopButton);
        submitShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptSubmitShop();
            }
        });

    }

    private void selectAddressClick() {
        PlacePicker.IntentBuilder placePickerBuilder = new PlacePicker.IntentBuilder();
        try {
            Intent intent = placePickerBuilder.build(AddShopActivity.this);
            startActivityForResult(intent, PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }


    private void selectCategoriesClick() {
        AlertDialog.Builder categoryMBuilder = new AlertDialog.Builder(AddShopActivity.this);
        categoryMBuilder.setTitle(R.string.title);

        categoryMBuilder.setMultiChoiceItems(categories, checkedCategories, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                if (isChecked) {
                    if (!mShopCategories.contains(position)) {
                        mShopCategories.add(position);
                    }
                } else if (mShopCategories.contains(position)) {
                    mShopCategories.remove((Integer) position);
                }
            }
        });

        categoryMBuilder.setCancelable(false);

        categoryMBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String categoriesString = "";
                sCategories = "";
                for (int i = 0; i < mShopCategories.size(); i++) {
                    categoriesString = categoriesString + categories[mShopCategories.get(i)];
                    sCategories = categoriesString + categories[mShopCategories.get(i)];
                    if (i != mShopCategories.size() - 1) {
                        categoriesString = categoriesString + ", ";
                        sCategories = sCategories + " ";
                    }
                }
                shopCategoriesTextView.setText(categoriesString);
            }
        });

        categoryMBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        categoryMBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int i = 0; i < checkedCategories.length; i++) {
                    checkedCategories[i] = false;
                    mShopCategories.clear();
                    shopCategoriesTextView.setText("");
                }
            }
        });

        AlertDialog mDialog = categoryMBuilder.create();
        mDialog.show();
    }

    private void addPhotoClick() {
        final CharSequence[] items = {"Camera", "Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(AddShopActivity.this);
        builder.setTitle("Add Image");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (items[i].equals("Camera")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[i].equals("Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent.createChooser(intent, "Select File"), SELECT_FILE);

                } else if (items[i].equals("Cancel")) {
                    dialogInterface.dismiss();

                }

            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {

                Uri selectedImageUri = data.getData();
                Picasso.with(this).load(selectedImageUri).fit().centerInside().into(shopImage);

            } else if (requestCode == SELECT_FILE) {

                Uri selectedImageUri = data.getData();
                Picasso.with(this).load(selectedImageUri).fit().centerInside().into(shopImage);

            } else if (requestCode == PLACE_PICKER_REQUEST){

                if (resultCode == RESULT_OK){

                    Place place = PlacePicker.getPlace(AddShopActivity.this, data);
                    shopAddressTextView.setText(place.getAddress());
                    shopsLatLng=place.getLatLng();

                }
            }
        }
    }

    private void requestPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_FINE_LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MY_PERMISSION_FINE_LOCATION:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "This app requires location permissions to be granted", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }

    private void attemptSubmitShop() {
    }


}
