package com.g1024l.androidripper_simplewizardtester

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText

import kotlinx.android.synthetic.main.activity_arsimple_wizard.*
import kotlinx.android.synthetic.main.content_arsimple_wizard.*

class ARSimpleWizard : AppCompatActivity() {

    fun upadteState(message : CharSequence?, checkboxState : Boolean) {
        checkBox.isEnabled = message.isNullOrBlank() == false;
        button.isEnabled = message.isNullOrBlank() == false && checkboxState == true;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arsimple_wizard)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        upadteState(editText.text, checkBox.isChecked);

        editText.addTextChangedListener( object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                upadteState(p0, checkBox.isChecked);
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        });

        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            upadteState(editText.text, isChecked);
        };
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_arsimple_wizard, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
