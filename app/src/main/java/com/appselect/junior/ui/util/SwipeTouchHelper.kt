package com.appselect.junior.ui.util

import android.graphics.Canvas
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.appselect.junior.R
import com.appselect.junior.ui.adapter.MovieAdapter

class SwipeTouchHelper: ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = 0
        val swipeFlags = ItemTouchHelper.END
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(
        viewHolder: RecyclerView.ViewHolder,
        direction: Int
    ) {

    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val foreground: View = (viewHolder as MovieAdapter.Holder).binding.root.findViewById(R.id.foreground)
        val background: View = (viewHolder).binding.root.findViewById(R.id.background)
        val width = background.width
        val coefOfOpen = dX / width
        if(coefOfOpen <= 0.7){
            background.alpha = coefOfOpen/0.7f
        }
        if(coefOfOpen == 0.0f) background.alpha = 1.0f
        getDefaultUIUtil().onDraw(c, recyclerView, foreground,
                (dX/1.15).toFloat(), dY, actionState, isCurrentlyActive)
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

    override fun isLongPressDragEnabled(): Boolean {
        return false
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        val foreground: View = (viewHolder as MovieAdapter.Holder).binding.root.findViewById(R.id.foreground)
        getDefaultUIUtil().clearView(foreground)
    }

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        return 0.3f
    }
}