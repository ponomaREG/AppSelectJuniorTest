package com.appselect.junior.ui.util

import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.appselect.junior.R
import com.appselect.junior.ui.adapter.MovieAdapter

class SwipeTouchHelper: ItemTouchHelper.Callback() {
    enum class MODE{
        LINEAR,
        GRID
    }
    var currentMode: MODE = MODE.LINEAR

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = 0
        val swipeFlags = if(currentMode == MODE.LINEAR) ItemTouchHelper.END
        else{
            if(viewHolder.adapterPosition % 2 == 0) ItemTouchHelper.START else ItemTouchHelper.END
        }
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
        val coefOfOpen = kotlin.math.abs(dX / width)
        if(coefOfOpen <= 0.7){
            background.alpha = coefOfOpen/0.7f
        }
        if(coefOfOpen == 0.0f) background.alpha = 1.0f
        val dxDivider = if(currentMode == MODE.LINEAR) 1.15 else 1.3
        getDefaultUIUtil().onDraw(c, recyclerView, foreground,
                (dX/dxDivider).toFloat(), dY, actionState, isCurrentlyActive)
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
        return 1f
    }
}