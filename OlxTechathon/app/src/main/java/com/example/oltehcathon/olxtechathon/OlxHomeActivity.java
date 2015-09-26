package com.example.oltehcathon.olxtechathon;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArraySet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class OlxHomeActivity extends AppCompatActivity {

    private Button mDoneButton;
    private EditText mEmailEdtTxt;
    private EditText mDescriptionEdtTxt;
    private Spinner mLocationSpinner;
    private Spinner mCategorySpinner;
    private Spinner mProductSpinner;
    private TextView mProductTitle;
    private View mProductView;
    private EditText mPriceEdtTxt;
    private String mDescription;
    private String mLocation;
    private String mCategory;
    private String mProduct;
    private String mPrice;
    private String mEmail;

    private static final String KEY = "OLX_TECHATHON";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olx_home);
        initializeUI();
    }

    private void initializeUI() {
        mDoneButton = (Button) findViewById(R.id.done);
        mEmailEdtTxt = (EditText) findViewById(R.id.email);
        mEmailEdtTxt.setText(getAccountEmail());
        mDescriptionEdtTxt = (EditText) findViewById(R.id.description);
        mLocationSpinner = (Spinner) findViewById(R.id.location_dropdown);
        mCategorySpinner = (Spinner) findViewById(R.id.category_dropdown);
        mProductSpinner = (Spinner) findViewById(R.id.subcategory_dropdown);
        mProductTitle = (TextView) findViewById(R.id.subcategory);
        mProductView = findViewById(R.id.subcategory_view);
        mPriceEdtTxt = (EditText) findViewById(R.id.price_detail);
        mProductSpinner.setVisibility(View.GONE);
        mProductView.setVisibility(View.GONE);
        mProductTitle.setVisibility(View.GONE);
        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateInput()) {
                    Set<String> adDetailsModel = new ArraySet<String>();
                    adDetailsModel.add(mDescription);
                    adDetailsModel.add(mLocation);
                    adDetailsModel.add(mCategory);
                    adDetailsModel.add(mProduct);
                    adDetailsModel.add(mPrice);

                    SharedPreferences sharedPreferences = getSharedPreferences(KEY, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putStringSet(mEmail, adDetailsModel);
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Data posted successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mProductSpinner.setVisibility(View.VISIBLE);
                mProductView.setVisibility(View.VISIBLE);
                mProductTitle.setVisibility(View.VISIBLE);
                switch (position) {
                    case 0:
                        String[] mobiles = getResources().getStringArray(R.array.categories_mobile);
                        List<String> mobileList = Arrays.asList(mobiles);
                        ArrayAdapter<String> mobileAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.component,  R.id.tv_promo_txt, mobileList);
                        mobileAdapter.setDropDownViewResource(R.layout.component);
                        mProductSpinner.setAdapter(mobileAdapter);
                        break;
                    case 1:
                        String[] cars = getResources().getStringArray(R.array.categories_cars);
                        List<String> carsList = Arrays.asList(cars);
                        ArrayAdapter<String> carsAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.component,  R.id.tv_promo_txt, carsList);
                        carsAdapter.setDropDownViewResource(R.layout.component);
                        mProductSpinner.setAdapter(carsAdapter);
                        break;
                    case 2:
                        String[] electronics = getResources().getStringArray(R.array.categories_electronics);
                        List<String> electronicsList = Arrays.asList(electronics);
                        ArrayAdapter<String> electronicsAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.component,  R.id.tv_promo_txt, electronicsList);
                        electronicsAdapter.setDropDownViewResource(R.layout.component);
                        mProductSpinner.setAdapter(electronicsAdapter);
                        break;
                    case 3:
                        String[] furniture = getResources().getStringArray(R.array.categories_furniture);
                        List<String> furnitureList = Arrays.asList(furniture);
                        ArrayAdapter<String> furnitureAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.component,  R.id.tv_promo_txt, furnitureList);
                        furnitureAdapter.setDropDownViewResource(R.layout.component);
                        mProductSpinner.setAdapter(furnitureAdapter);
                        break;
                    case 4:
                        String[] kitchen = getResources().getStringArray(R.array.categories_kitchen);
                        List<String> kitchenList = Arrays.asList(kitchen);
                        ArrayAdapter<String> kitchenAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.component,  R.id.tv_promo_txt, kitchenList);
                        kitchenAdapter.setDropDownViewResource(R.layout.component);
                        mProductSpinner.setAdapter(kitchenAdapter);
                        break;
                    case 5:
                        String[] entertainment = getResources().getStringArray(R.array.categories_entertainment);
                        List<String> entertainmentList = Arrays.asList(entertainment);
                        ArrayAdapter<String> entertainmentAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.component,  R.id.tv_promo_txt, entertainmentList);
                        entertainmentAdapter.setDropDownViewResource(R.layout.component);
                        mProductSpinner.setAdapter(entertainmentAdapter);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private boolean validateInput() {
        mEmail = mEmailEdtTxt.getText().toString();
        mDescription = mDescriptionEdtTxt.getText().toString();
        mLocation = mLocationSpinner.getSelectedItem().toString();
        mCategory = mCategorySpinner.getSelectedItem().toString();
        mProduct = mProductSpinner.getSelectedItem().toString();
        mPrice = mPriceEdtTxt.getText().toString();
        if (mDescription != null && !mDescription.isEmpty() &&
                mLocation != null && !mLocation.isEmpty() &&
                mCategory != null && !mCategory.isEmpty() &&
                mProduct != null && !mProduct.isEmpty() &&
                mPrice != null && !mPrice.isEmpty()) {
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "Please fill the required input fields", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_olx_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String getAccountEmail() {
        String emailId = null;
        /**First check if user is logged in mmt app or not **/
        AccountManager accountManager = AccountManager.get(OlxHomeActivity.this);
        Account[] accounts = accountManager.getAccountsByType("com.google");
        Account account = null;
        if (accounts.length > 0) {
            account = accounts[0];
        }
        if (account != null) {
            emailId = account.name;
        }

        return emailId;
    }
}
