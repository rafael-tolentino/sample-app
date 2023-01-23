package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.analytics.FirebaseAnalytics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import com.google.firebase.analytics.FirebaseAnalytics.Event;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.ArrayList;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    public String normalizeParamValues (String paramValue )
        {
            paramValue = paramValue.toLowerCase().trim();
            if(paramValue.length() > 100)
                {
                    Log.e("Tagueamento: ","O valor: '" + paramValue + "' excede o limite de 100 caracteres. Possui: " + paramValue.length() + " caracteres");
                }
            return paramValue;
        }

    private FirebaseAnalytics mFirebaseAnalytics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        setContentView(R.layout.activity_main);
        //Bundle bundle = new Bundle();
        //bundle.putString("screen_name", "home teste 1 setCurrentScreen");
        //mFirebaseAnalytics.logEvent("SCREENVIEW_CUSTOMIZADO", bundle);
        //mFirebaseAnalytics.setCurrentScreen(this, "teste 1 setCurrentScreen", null);


        final Bundle itemProduct1 = new Bundle();
        itemProduct1.putString("item_id", "123456789");
        itemProduct1.putString("item_name", "Smartphone Galaxy S20");
        itemProduct1.putString("item_category", "Eletronicos");
        itemProduct1.putString("item_category2", "Telefones");
        itemProduct1.putString("item_variant", "Preto");
        itemProduct1.putString("item_brand", "Samsung");
        itemProduct1.putDouble("price", 3999.98);
        itemProduct1.putString("affiliation", "Casas Bahia");

        final Bundle itemProduct2 = new Bundle();
        itemProduct2.putString(Param.ITEM_ID, "987654321");
        itemProduct2.putString(Param.ITEM_NAME, "Macbook Air M1");
        itemProduct2.putString(Param.ITEM_CATEGORY, "Eletronicos");
        itemProduct2.putString(Param.ITEM_CATEGORY2, "Computadores");
        itemProduct2.putString(Param.ITEM_CATEGORY3, "Notebooks");
        itemProduct2.putString(Param.ITEM_CATEGORY4, "Linha premium");
        itemProduct2.putString(Param.ITEM_CATEGORY5, "Apple");
        itemProduct2.putString(Param.ITEM_VARIANT, "8GB 256GB");
        itemProduct2.putString(Param.ITEM_BRAND, "Apple");
        itemProduct2.putDouble(Param.PRICE, 7890.10);
        itemProduct2.putString(Param.AFFILIATION, "Ponto Frio");

        Button buttonNext = findViewById(R.id.botaoproximo);
        FloatingActionButton fab = findViewById(R.id.fab);
        Button select_item = findViewById(R.id.botaoselectitem);
        Button view_item = findViewById(R.id.botaoviewitem);
        Button add_wish = findViewById(R.id.botaoaddwishlist);
        Button add_cart = findViewById(R.id.botaoaddcart);
        Button remove_cart = findViewById(R.id.botaoremovecart);
        Button view_cart = findViewById(R.id.botaoviewcart);
        Button begin_check = findViewById(R.id.botaobegincheckout);
        Button add_ship = findViewById(R.id.botaoaddship);
        Button add_pay = findViewById(R.id.botaoaddpayment);
        Button purchase = findViewById(R.id.botaopurchase);


        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondScreenActivity.class);
                startActivity(intent);
                Bundle itemProduct1withIndex = new Bundle(itemProduct1);
                itemProduct1withIndex.putLong(Param.INDEX, 1);

                Bundle itemProduct2withIndex = new Bundle(itemProduct2);
                itemProduct2withIndex.putLong(Param.INDEX, 2);

                Bundle viewItemListParams = new Bundle();
                viewItemListParams.putString(Param.ITEM_LIST_ID, "List001");
                viewItemListParams.putString(Param.ITEM_LIST_NAME, "Promoções");
                viewItemListParams.putParcelableArray(Param.ITEMS, new Parcelable[]{itemProduct1withIndex, itemProduct2withIndex});

                mFirebaseAnalytics.logEvent(Event.VIEW_ITEM_LIST, viewItemListParams);


            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                mFirebaseAnalytics.setUserProperty("favorite_food", "teste2");
            }
        });

        //EVENTO SELECT ITEM
        select_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle1 = new Bundle();
                bundle1.putString("text",normalizeParamValues("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat"));
                mFirebaseAnalytics.logEvent("screen_view", bundle1);
                //Log.e("Tagueamento: ","O parametro 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat' possui mais de 100 caracteres");

                Bundle bundle2 = new Bundle();
                bundle2.putString("screen","pagina / teste");
                mFirebaseAnalytics.logEvent("evento_teste", bundle2);

                Bundle selectItemParams = new Bundle();
                selectItemParams.putString(Param.ITEM_LIST_ID, "List001");
                selectItemParams.putString(Param.ITEM_LIST_NAME, "Promoções");
                selectItemParams.putParcelableArray(Param.ITEMS, new Parcelable[]{itemProduct1});
                mFirebaseAnalytics.logEvent(Event.SELECT_ITEM, selectItemParams);
            }
        });

        //EVENTO VIEW ITEM
        view_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle viewItemParams = new Bundle();
                viewItemParams.putString(Param.CURRENCY, "BRL");
                viewItemParams.putDouble(Param.VALUE, itemProduct1.getDouble(Param.PRICE));
                viewItemParams.putParcelableArray(Param.ITEMS, new Parcelable[]{itemProduct1});
                mFirebaseAnalytics.logEvent(Event.VIEW_ITEM, viewItemParams);
            }
        });

        //EVENTO ADD WISHLIST
        add_wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle itemProduct1AddToWishlist = new Bundle(itemProduct1);
                itemProduct1AddToWishlist.putLong(FirebaseAnalytics.Param.QUANTITY, 2);

                Bundle addToWishlistParams = new Bundle();
                addToWishlistParams.putString(FirebaseAnalytics.Param.CURRENCY, "BRL");
                addToWishlistParams.putDouble(FirebaseAnalytics.Param.VALUE, 2 * itemProduct1.getDouble(Param.PRICE));
                addToWishlistParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS, new Parcelable[]{itemProduct1AddToWishlist});

                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.ADD_TO_WISHLIST, itemProduct1AddToWishlist);
            }
        });

        //EVENTO ADD CART
        add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle itemProduct1AddToCart = new Bundle(itemProduct1);
                itemProduct1AddToCart.putLong("quantity", 3);

                Bundle addToCartParams = new Bundle();
                addToCartParams.putString("currency", "BRL");
                addToCartParams.putDouble("value", itemProduct1AddToCart.getLong("quantity") * itemProduct1.getDouble("price"));
                addToCartParams.putParcelableArray(Param.ITEMS, new Parcelable[]{itemProduct1AddToCart});

                mFirebaseAnalytics.logEvent("add_to_cart", addToCartParams);
            }
        });


        //EVENTO REMOVE CART
        remove_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle itemProduct1RemoveFromCart = new Bundle(itemProduct1);
                itemProduct1RemoveFromCart.putLong(FirebaseAnalytics.Param.QUANTITY, 2);

                Bundle removeFromCartParams = new Bundle();
                removeFromCartParams.putString(FirebaseAnalytics.Param.CURRENCY, "BRL");
                removeFromCartParams.putDouble(FirebaseAnalytics.Param.VALUE, itemProduct1RemoveFromCart.getLong(Param.QUANTITY) * itemProduct1.getDouble(Param.PRICE));
                removeFromCartParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS, new Parcelable[]{itemProduct1RemoveFromCart});

                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.REMOVE_FROM_CART, removeFromCartParams);
            }
        });

        //EVENTO VIEW CART
        view_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle itemProduct1Cart = new Bundle(itemProduct1);
                itemProduct1Cart.putLong(FirebaseAnalytics.Param.QUANTITY, 2);

                Bundle itemProduct2Cart = new Bundle(itemProduct2);
                itemProduct2Cart.putLong(FirebaseAnalytics.Param.QUANTITY, 1);

                Bundle viewCartParams = new Bundle();
                viewCartParams.putString(FirebaseAnalytics.Param.CURRENCY, "BRL");
                viewCartParams.putDouble(FirebaseAnalytics.Param.VALUE, (itemProduct1Cart.getLong(Param.QUANTITY) * itemProduct1.getDouble(Param.PRICE)) + (itemProduct2Cart.getLong(Param.QUANTITY) * itemProduct2.getDouble(Param.PRICE)));
                viewCartParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS, new Parcelable[]{itemProduct1Cart, itemProduct2Cart});

                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_CART, viewCartParams);
            }
        });

        //EVENTO BEGIN CHECKOUT
        begin_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle itemProduct1Cart = new Bundle(itemProduct1);
                itemProduct1Cart.putLong(FirebaseAnalytics.Param.QUANTITY, 2);

                Bundle itemProduct2Cart = new Bundle(itemProduct2);
                itemProduct2Cart.putLong(FirebaseAnalytics.Param.QUANTITY, 1);
                Bundle beginCheckoutParams = new Bundle();
                beginCheckoutParams.putString(FirebaseAnalytics.Param.CURRENCY, "BRL");
                beginCheckoutParams.putDouble(FirebaseAnalytics.Param.VALUE, (itemProduct1Cart.getLong(Param.QUANTITY) * itemProduct1.getDouble(Param.PRICE)) + (itemProduct2Cart.getLong(Param.QUANTITY) * itemProduct2.getDouble(Param.PRICE)));
                beginCheckoutParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS, new Parcelable[]{itemProduct1Cart, itemProduct2Cart});
                mFirebaseAnalytics.logEvent("begin_checkout", beginCheckoutParams);
            }
        });

        //EVENTO ADD SHIPPING
        add_ship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle itemProduct1Cart = new Bundle(itemProduct1);
                itemProduct1Cart.putLong(FirebaseAnalytics.Param.QUANTITY, 2);

                Bundle itemProduct2Cart = new Bundle(itemProduct2);
                itemProduct2Cart.putLong(FirebaseAnalytics.Param.QUANTITY, 1);

                Bundle addShippingParams = new Bundle();
                addShippingParams.putString(FirebaseAnalytics.Param.CURRENCY, "BRL");
                addShippingParams.putDouble(FirebaseAnalytics.Param.VALUE, (itemProduct1Cart.getLong(Param.QUANTITY) * itemProduct1.getDouble(Param.PRICE)) + (itemProduct2Cart.getLong(Param.QUANTITY) * itemProduct2.getDouble(Param.PRICE)));
                addShippingParams.putString(FirebaseAnalytics.Param.SHIPPING_TIER, "Entrega Padrão");
                addShippingParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS, new Parcelable[]{itemProduct1Cart, itemProduct2Cart});
                mFirebaseAnalytics.logEvent("add_shipping_info", addShippingParams);
            }
        });

        //EVENTO ADD PAYMENT
        add_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle itemProduct1Cart = new Bundle(itemProduct1);
                itemProduct1Cart.putLong(FirebaseAnalytics.Param.QUANTITY, 2);

                Bundle itemProduct2Cart = new Bundle(itemProduct2);
                itemProduct2Cart.putLong(FirebaseAnalytics.Param.QUANTITY, 1);

                Bundle addPaymentParams = new Bundle();
                addPaymentParams.putString(FirebaseAnalytics.Param.CURRENCY, "BRL");
                addPaymentParams.putDouble(FirebaseAnalytics.Param.VALUE, (itemProduct1Cart.getLong(Param.QUANTITY) * itemProduct1.getDouble(Param.PRICE)) + (itemProduct2Cart.getLong(Param.QUANTITY) * itemProduct2.getDouble(Param.PRICE)));
                addPaymentParams.putString(FirebaseAnalytics.Param.PAYMENT_TYPE, "Cartão de crédito - 12X");
                addPaymentParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS, new Parcelable[]{itemProduct1Cart, itemProduct2Cart});
                mFirebaseAnalytics.logEvent("add_payment_info", addPaymentParams);
            }
        });

        //EVENTO PURCHASE
        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle itemProduct1Cart = new Bundle(itemProduct1);
                itemProduct1Cart.putLong(FirebaseAnalytics.Param.QUANTITY, 2);

                Bundle itemProduct2Cart = new Bundle(itemProduct2);
                itemProduct2Cart.putLong(FirebaseAnalytics.Param.QUANTITY, 1);

                Bundle purchaseParams = new Bundle();
                purchaseParams.putString(FirebaseAnalytics.Param.TRANSACTION_ID, String.valueOf(Math.random()).replace("0.", ""));
                purchaseParams.putString(FirebaseAnalytics.Param.AFFILIATION, "Submarino");
                purchaseParams.putString(FirebaseAnalytics.Param.CURRENCY, "BRL");
                purchaseParams.putString(FirebaseAnalytics.Param.COUPON, "10OFF");
                purchaseParams.putDouble(FirebaseAnalytics.Param.VALUE, (itemProduct1Cart.getLong(Param.QUANTITY) * itemProduct1.getDouble(Param.PRICE)) + (itemProduct2Cart.getLong(Param.QUANTITY) * itemProduct2.getDouble(Param.PRICE)));
                purchaseParams.putDouble(FirebaseAnalytics.Param.SHIPPING, 9.99);
                purchaseParams.putDouble(FirebaseAnalytics.Param.TAX, 5.99);

                purchaseParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS, new Parcelable[]{itemProduct1Cart, itemProduct2Cart});

                mFirebaseAnalytics.logEvent("purchase", purchaseParams);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle params = new Bundle();
        params.putString(Param.SCREEN_NAME, "home teste 1");
        mFirebaseAnalytics.logEvent(Event.SCREEN_VIEW, params);
        Bundle params2 = new Bundle();
        params2.putString("screen_name", "home teste 1");
        mFirebaseAnalytics.logEvent("SCREENVIEW_CUSTOMIZADO", params2);


    }


}
