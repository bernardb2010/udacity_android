package com.example.bernardb2010.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * This method is called when the order button is clicked.
     * @param view - The view that was selected, in the case, the Button
     */
    public void submitOrder(View view) {
        display(quantity);
        displayPrice(quantity * 5);
    }

    /**
     * This method displays the given quantity value on the screen.
     * @param number - integer value for the number to use when updating the quantity. This value is
     *               then displayed on screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    public void addCoffee(View view) {
        display(quantity + 1);
    }

    public void removeCoffee(View view) {
        display(quantity - 1);
    }
}
