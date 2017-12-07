package com.sirialkillers.shoponthego.Shop_Related_Activities;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.net.Uri;

import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.sirialkillers.shoponthego.Controllers.ShopController;
import com.sirialkillers.shoponthego.Maps_Related_Activities.Position;
import com.sirialkillers.shoponthego.MenuActivity;
import com.sirialkillers.shoponthego.Models.CategoryModel;
import com.sirialkillers.shoponthego.Models.ShopModel;
import com.sirialkillers.shoponthego.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class AddShopActivity extends AppCompatActivity {
    private EditText titleEditText;
    private ImageView shopImage;
    private TextView shopCategoriesTextView;
    private TextView shopAddressTextView;
    private String shopCategories;
    private Address shopAddress;
    private LatLng shopLatLng;
    private ShopRegisterTask shopRegisterTask = null;
    private Place place;
    private Bitmap bitmap;
    List<String> shopChosenCategoriesList = new ArrayList<String>();
    private final static LatLngBounds bounds = new LatLngBounds(new LatLng(34.875228,20.379639), new LatLng(41.695988,26.597900));

    String[] categories;
    boolean[] checkedCategories;
    ArrayList<Integer> mShopCategories = new ArrayList<>();
    ConstraintLayout constraintLayout ;
    ProgressBar progressBar;
    TextView loadingTextView;
    private final static int REQUEST_CAMERA = 1;
    private final static int SELECT_FILE = 0;

    private final static int MY_PERMISSION_FINE_LOCATION = 101;
    private final static int PLACE_PICKER_REQUEST = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop);

        requestPermission();
        shopChosenCategoriesList.clear();
        constraintLayout = findViewById(R.id.constraintLayout);
        progressBar = findViewById(R.id.progressBar);
        loadingTextView=findViewById(R.id.loadingTextView);

        shopAddressTextView = (TextView) findViewById(R.id.addressTextView);
        shopCategoriesTextView = (TextView) findViewById(R.id.categoriesTextView);

        titleEditText = (EditText) findViewById(R.id.titleEditText);
        titleEditText.requestFocus();
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
        placePickerBuilder.setLatLngBounds(bounds);
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
        shopCategoriesTextView.setError(null);

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

                shopChosenCategoriesList.clear();
                for (int i = 0; i < mShopCategories.size(); i++) {
                    categoriesString = categoriesString + categories[mShopCategories.get(i)];

                    shopChosenCategoriesList.add(categories[mShopCategories.get(i)]);
                    if (i != mShopCategories.size() - 1) {
                        categoriesString = categoriesString + ", ";

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
                    shopChosenCategoriesList.clear();
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
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == PLACE_PICKER_REQUEST) {

                if (resultCode == RESULT_OK) {

                    place = PlacePicker.getPlace(AddShopActivity.this, data);
                    shopAddressTextView.setText(place.getAddress());
                    shopLatLng = place.getLatLng();
                    shopAddressTextView.setError(null);

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
    private void hideKeyboard(){
        View view = this.getCurrentFocus();
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void attemptSubmitShop() {
        hideKeyboard();
        if (shopRegisterTask != null) {
            return;
        }

        boolean cancel = false;
        View focusView = titleEditText;


        titleEditText.setError(null);
        shopCategoriesTextView.setError(null);
        shopAddressTextView.setError(null);

        if (titleEditText.getText().toString().isEmpty()) {
            titleEditText.setError("Please enter a title ");
            focusView = titleEditText;
            cancel = true;

        } else if (titleEditText.getText().toString().length() > 70) {

            titleEditText.setError("Please enter a title shorter than 70 characters");
            focusView = titleEditText;
            cancel = true;
        }


        if (shopCategoriesTextView.getText().toString().isEmpty()) {
            Toast.makeText(AddShopActivity.this,
                    "Please select the Shop's Categories", Toast.LENGTH_LONG).show();
            shopCategoriesTextView.setError("Please fill");
            cancel = true;

        }

        if (shopAddressTextView.getText().toString().isEmpty()) {
            Toast.makeText(AddShopActivity.this,
                    "Please select the Shop's Address", Toast.LENGTH_LONG).show();
            shopAddressTextView.setError("Please fill");
            cancel = true;

        }


        UUID shopID = UUID.randomUUID();

        if (cancel) {
            // There was an error; don't to register the shop and show
            // form field with an error.
            focusView.requestFocus();
        } else {
            shopRegisterTask = new ShopRegisterTask(titleEditText.getText().toString(),shopLatLng,shopChosenCategoriesList,bitmap, shopID);
            shopRegisterTask.execute((Void) null);

        }
    }

    /**
     * Setting the shop register task for sending the shop to the REST
     */

    public class ShopRegisterTask extends AsyncTask<Void, Boolean, Boolean> {

        private String shopTitle;
        private LatLng shopLatLng;
        private List<String> shopCategories;
        private Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.addphoto);
        private UUID shopID;

        public ShopRegisterTask(String shopTitle, LatLng shopLatLng, List<String> shopCategories,Bitmap bitmap, UUID shopID) {
            this.shopTitle = shopTitle;
            this.shopLatLng = shopLatLng;
            this.shopCategories = shopCategories;
            this.bitmap = bitmap;
            this.shopID = shopID;
        }

        @Override
        protected void onPreExecute() {
            loadingTextView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            constraintLayout.setVisibility(View.INVISIBLE);
           }

        @Override
        protected Boolean doInBackground(Void... params) {
            try
            {
                List<CategoryModel> shopCategoriesModels = new ArrayList<>();
                for(int i=0;i<shopCategories.size();i++){
                    shopCategoriesModels.add(new CategoryModel(shopCategories.get(i)));
                }
                Position position = new Position(shopLatLng.latitude,shopLatLng.longitude);
                ShopModel newShop = new ShopModel(shopID.toString(),shopTitle,position);
                ShopController shopController = new ShopController();
                shopController.create(newShop);
                newShop.setCategories(shopCategoriesModels);


            } catch (Exception e){

                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success) {
                goToMenuActivity();

            } else {
                Toast.makeText(AddShopActivity.this, "Something went wrong!!! Try again", Toast.LENGTH_SHORT).show();
                loadingTextView.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                constraintLayout.setVisibility(View.VISIBLE);
            }
        }

        @Override
        protected void onCancelled() {
            shopRegisterTask = null;
        }
    }
    private void goToMenuActivity(){
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
