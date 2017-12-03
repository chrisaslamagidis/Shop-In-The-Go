package com.sirialkillers.shoponthego;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sirialkillers.shoponthego.Controllers.ShopController;
import com.sirialkillers.shoponthego.Models.DiscountModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


public class AddDiscountActivity extends AppCompatActivity {

    private ConstraintLayout dConstraintLayout;

    private EditText dTitleEditText;
    private EditText dDescriptionEditText;
    private DiscountRegisterTask discountRegisterTask = null;

    private TextView dCategoriesTextView;
    private TextView dExpDateTextView;
    private TextView loadingTextView;

    private ProgressBar progressBar;

    private NumberPicker numberPicker;

    private String date;
    private int DIALOG_ID = 0;
    private int dYear, dMonth, dDay;
    private Calendar calendar = Calendar.getInstance();


    String sCategories;
    String[] categories;
    boolean[] checkedCategories;
    ArrayList<Integer> mUserCategories = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_discount);

        dConstraintLayout = (ConstraintLayout) findViewById(R.id.addDiscountConstraintLayout);

        dTitleEditText = (EditText) findViewById(R.id.titleDiscountEditText);
        dDescriptionEditText = (EditText) findViewById(R.id.descriptionDiscountEditText);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        setNumberPicker();
        setCalendar();

        loadingTextView = (TextView) findViewById(R.id.loadingTextView);
        dExpDateTextView = (TextView) findViewById(R.id.expirationDateTextView);
        dCategoriesTextView = (TextView) findViewById(R.id.categoriesTextView);
        dCategoriesTextView.setMovementMethod(new ScrollingMovementMethod());

        categories = getResources().getStringArray(R.array.productCategories);
        checkedCategories = new boolean[categories.length];


        TextView selectExpDateTextView = (TextView) findViewById(R.id.selectExpirationDateTextView);
        selectExpDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectExpDate();
            }
        });

        TextView selectCategoriesTextView = (TextView) findViewById(R.id.selectCategoriesTextView);
        selectCategoriesTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCategories();
            }
        });

        Button submitOrderButton = (Button) findViewById(R.id.submitDiscountButton);
        submitOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptSubmitDiscount();
            }
        });
    }

    //Setting Discount to be from 1 to 99% and default value at 20%
    public void setNumberPicker(){
        numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        numberPicker.setMaxValue(99);
        numberPicker.setMinValue(1);
        numberPicker.setValue(20);
    }

    /**
     * Setting the DateSetListener so you can pick only a future day.
     */
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            dYear = year;
            dMonth = month;

            //Months are from 0 to 11 but we need to look from 1 to 12 like IRL
            int realMonth = month + 1;
            dDay = dayOfMonth;

            //if you chose a valid date display that date
            if (dYear > calendar.get(Calendar.YEAR) ||
                    dYear == calendar.get(Calendar.YEAR) && dMonth > calendar.get(Calendar.MONTH) ||
                    dYear == calendar.get(Calendar.YEAR) && dMonth == calendar.get(Calendar.MONTH) && dDay >= calendar.get(Calendar.DAY_OF_MONTH)) {
                date = Integer.toString(dDay) + "/" + Integer.toString(realMonth)
                        + "/" + Integer.toString(year);
                dExpDateTextView.setError(null);
                dExpDateTextView.setText(date);

            //else you chose a past date display an error and set the date to today's date
            } else {

                date = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)) +
                        "/" + Integer.toString(calendar.get(Calendar.MONTH) + 1) +
                        "/" + Integer.toString(calendar.get(Calendar.YEAR));
                dExpDateTextView.setText(date);
                dExpDateTextView.setError("Please select a future date.");
                Toast toast = Toast.makeText(AddDiscountActivity.this,
                        "The day you selected is a past one. Please select a future date.",
                        Toast.LENGTH_LONG);
                toast.show();
            }
        }
    };

    /**
     * Setting the Multiple Category choices, you can pick one or many categories
     */
    private void selectCategories() {
        AlertDialog.Builder categoryMBuilder = new AlertDialog.Builder(AddDiscountActivity.this);
        categoryMBuilder.setTitle(R.string.title);

        categoryMBuilder.setMultiChoiceItems(categories, checkedCategories, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                if (isChecked) {
                    if (!mUserCategories.contains(position)) {
                        mUserCategories.add(position);
                    }
                } else if (mUserCategories.contains(position)) {
                    mUserCategories.remove((Integer) position);
                }
            }
        });

        categoryMBuilder.setCancelable(false);

        categoryMBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String categoriesString = "";
                sCategories = "";
                for (int i = 0; i < mUserCategories.size(); i++) {
                    categoriesString = categoriesString + categories[mUserCategories.get(i)];
                    sCategories = categoriesString + categories[mUserCategories.get(i)];
                    if (i != mUserCategories.size() - 1) {
                        categoriesString = categoriesString + ", ";
                        sCategories = sCategories + " ";
                    }
                }
                dCategoriesTextView.setText(categoriesString);
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
                    mUserCategories.clear();
                    dCategoriesTextView.setText("");
                }
            }
        });

        AlertDialog mDialog = categoryMBuilder.create();
        mDialog.show();
    }

    //Loading
    private void Loading() {
        dConstraintLayout.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        loadingTextView.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(intent);
            }
        },2000);
    }

    private void selectExpDate() {
        showDialog(DIALOG_ID);
    }

    private void setCalendar() {

        dYear = calendar.get(Calendar.YEAR);
        dMonth = calendar.get(Calendar.MONTH);
        dDay = calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID) {
            return new DatePickerDialog(AddDiscountActivity.this, datePickerListener, dYear, dMonth, dDay);
        }
        return null;
    }

    /**
     * Setting the Submit Discount to work if every choice is valid
     */
    private void attemptSubmitDiscount() {

        if (discountRegisterTask != null) {
            return;
        }

        boolean cancel = false;
        View focusView = dTitleEditText;


        dTitleEditText.setError(null);
        dDescriptionEditText.setError(null);
        dCategoriesTextView.setError(null);


        if (dTitleEditText.getText().toString().isEmpty()) {
            dTitleEditText.setError("Please enter a title ");
            focusView = dTitleEditText;
            cancel = true;
        }

        if (dTitleEditText.getText().toString().length() > 70) {
            dTitleEditText.setError("Please enter a title shorter than 70 characters");
            focusView = dTitleEditText;
            cancel = true;
        }

        if (dDescriptionEditText.getText().toString().length() > 500) {
            dDescriptionEditText.setError("Please enter a Description shorter than 500 characters");
            focusView = dDescriptionEditText;
            cancel = true;
        }

        if (dCategoriesTextView.getText().toString().isEmpty()) {
            Toast categoryToast = Toast.makeText(AddDiscountActivity.this,
                    "Please select the Product Categories", Toast.LENGTH_LONG);
            dCategoriesTextView.setError("Please fill");
            cancel = true;
            categoryToast.show();
        }

        if (dExpDateTextView.getText().toString().isEmpty()) {
            Toast expDateToast = Toast.makeText(AddDiscountActivity.this,
                    "Please select a expiration date.", Toast.LENGTH_LONG);
            dExpDateTextView.setError("Please fill");
            cancel = true;
            expDateToast.show();
        }
        //We need to create the shop UI. for now we just create a random ID whenever you create a Discount.
        UUID shopId = UUID.randomUUID();

        if (cancel) {
            // There was an error; don't to register the discount and show
            // form field with an error.
            focusView.requestFocus();
        } else {
            String dTitle = dTitleEditText.getText().toString();
            Date dExpDate = new Date(dYear, dMonth, dDay);
            String dDescription = dDescriptionEditText.getText().toString();
            // Show a progress spinner, and kick off a background task to
            // perform the discount register attempt
            Loading();

            discountRegisterTask = new DiscountRegisterTask(dTitle, dExpDate, numberPicker.getValue(), sCategories, dDescription,shopId);

        }
    }

    /**
     * Setting the discount register task for sending the discount to the REST
     */

    public class DiscountRegisterTask extends AsyncTask<Void, Boolean, Boolean> {

        private String discountTitle;
        private Date discountExpDate;
        private int discountValue;
        private String discountCategories;
        private String discountDescription = "default_empty";
        private UUID shopID;

        DiscountRegisterTask(String discountTitle, Date discountExpDate, int discountValue, String discountCategories, String discountDescription,UUID shopID) {
            this.discountTitle = discountTitle;
            this.discountExpDate = discountExpDate;
            this.discountValue = discountValue;
            this.discountCategories = discountCategories;
            this.discountDescription = discountDescription;
            this.shopID = shopID;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            DiscountModel discountModel= new DiscountModel(shopID.toString(),null,(double)discountValue,discountTitle,discountDescription);

            try {
                ShopController shopController =new ShopController();
                shopController.addShopDiscount(shopID.toString(),discountModel);
                return true;

            } catch (Exception e) {
                return false;
            }


        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if(success){
                Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(intent);

            }
            else {
                Toast.makeText(AddDiscountActivity.this, "Something went wromg!!! Try again", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {
            discountRegisterTask = null;
        }
    }
}

