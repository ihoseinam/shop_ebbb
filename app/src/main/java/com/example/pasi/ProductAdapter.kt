package com.example.pasi

import android.app.Activity
import android.widget.ArrayAdapter

class ProductAdapter(
    private val context: Activity,
    private val data: Array<DataProduct>,

    ) : ArrayAdapter<DataProduct>(
    context,
    R.layout.list_item,
    data
) {
}