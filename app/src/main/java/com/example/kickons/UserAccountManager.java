package com.example.kickons;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

/*
This class will:
    get class will get a google user account from the device when  user touches
    the textview in the login or rego form
 */
public class UserAccountManager {

    Context context;
    Activity activity;
    AccountManager userAccountManager;

    public UserAccountManager(Activity activity) {
        this.activity = activity;
        this.context = (Context) activity;
        userAccountManager = AccountManager.get(context);
    }

    //return intent that creates user select list
    public Intent getUserAccounts(String[] accountTypesAllowed){

        //alwaysPromptUser depreciated after Marshmellow. I included it so it
        // always prompts in all versions

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            return AccountManager.newChooseAccountIntent(null, null,
                    accountTypesAllowed , null, null,
                    null, null);
        }
        else {
        return AccountManager.newChooseAccountIntent(null, null,
                    accountTypesAllowed , true, null, null,
                    null, null);
        }
    }

    public void addUserAccount(){

        AccountManagerFuture<Bundle> af =  userAccountManager.addAccount("com.kickons", null, null, null, activity, null, null);
        System.out.println("Status: " + af.isDone());
    }




}
