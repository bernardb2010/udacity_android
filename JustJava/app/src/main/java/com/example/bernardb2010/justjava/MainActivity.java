package com.example.bernardb2010.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final int numToppingChoices = 2;
    private final String sendToEmail[] = new String[]{"Immeraufdemhund@hotmail.com"};
    private final String emailSubject = "Just Java order summary - ";

    private int quantity = 1;
    private int pricePerCup = 5;
    private TextView orderQuantity;
    private boolean hasWhippedCream = false;
    private boolean hasChocolate = false;
    private CheckBox toppingsCheckBoxes[];
    private EditText nameInputField;
    private Intent emailIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getComponents();

    }

    private void getComponents() {
        orderQuantity = findViewById(R.id.quantity_text_view);
        toppingsCheckBoxes = new CheckBox[numToppingChoices];
        toppingsCheckBoxes[0] = findViewById(R.id.whippedCream);
        toppingsCheckBoxes[1] = findViewById(R.id.chocolate);
        nameInputField = findViewById(R.id.nameInput);
        emailIntent = new Intent();//Intent.ACTION_SENDTO, Uri.fromParts(
        // "mailto",sendToEmail, null));
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
     * This method is called when the order button is clicked. Updates the quantity view
     * and then creates and displays the order summary.
     *
     * @param view - The view that was selected, in the case, the Button
     */
    public void submitOrder(View view) {
        displayQuantity(quantity);
        getToppings();
        emailOrderSummary(createOrderSummary());
    }

    private void getToppings() {
        hasWhippedCream = toppingsCheckBoxes[0].isChecked();
        hasChocolate = toppingsCheckBoxes[1].isChecked();
    }

    /**
     * Generates string for order summary: name, qty, total
     *
     * @return Order Summary String
     */
    private String createOrderSummary() {
        String summary = "Name: " + nameInputField.getText().toString() + "\n";
        summary += "Has whipped cream? " + hasWhippedCream + "\n";
        summary += "Add Chocolate? " + hasChocolate + "\n";
        summary += "Quantity: " + quantity + "\n";
        summary += "Total: $" + calculatePrice() + "\nThank You!";
        return summary;
    }

    /**
     * This method displays the given quantity value on the screen.
     *
     * @param numberOfCoffees - integer value for the numberOfCoffees to use when updating the quantity. This value is
     *                        then displayed on screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        orderQuantity.setText(String.valueOf(numberOfCoffees));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void emailOrderSummary(String summary) {
        generateIntent(summary);
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);//Intent.createChooser(emailIntent, "Send Email:"));
        }
    }

    private void generateIntent(String emailText) {
        emailIntent.setAction(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, sendToEmail);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubject + nameInputField.getText().toString());
        emailIntent.putExtra(Intent.EXTRA_TEXT, emailText);
    }

    /**
     * Calculates the price of the order based on the current quantity and types of toppings
     *
     * @return total price of order
     */
    private int calculatePrice() {
        int totalPrice = pricePerCup;

        if (hasWhippedCream) {
            totalPrice += 1;
        }
        if (hasChocolate) {
            totalPrice += 2;
        }
        return quantity * totalPrice;
    }

    /**
     * Adds 1 coffee to order
     *
     * @param view - the Button that was clicked
     */
    public void addCoffee(View view) {
        if (quantity == 100) {
            Toast.makeText(this, getString(R.string.orderMax), Toast.LENGTH_SHORT).show();
            return;
        }
        displayQuantity(++quantity);
    }

    /**
     * Removes 1 coffee from the order
     *
     * @param view- the Button that was clicked
     */
    public void removeCoffee(View view) {
        if (quantity <= 1) {
            Toast.makeText(this, getString(R.string.orderMin), Toast.LENGTH_SHORT).show();
            return;
        }
        displayQuantity(--quantity);
    }

}
