package com.ddev.nestedrecyclerview.util

import com.ddev.nestedrecyclerview.model.Data

interface OnClickListener {
   fun onClick(item: Data, position: Int)
}