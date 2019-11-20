package bok.artenes.recyclerview.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import bok.artenes.recyclerview.R

class ListItem(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    constructor(context: Context) : this(context, null)

    private val textViewName: TextView
    private val textViewTitle: TextView
    private val textViewDescription: TextView
    private val imageViewArrow: ImageView
    private val constraintLayoutHeader: ConstraintLayout

    val descriptionView: View
        get() = textViewDescription

    val arrowView: View
        get() = imageViewArrow

    var name: String
        get() = textViewName.text.toString()
        set(value) {
            textViewName.text = value
        }

    var title: String
        get() = textViewTitle.text.toString()
        set(value) {
            textViewTitle.text = value
        }

    var description: String
        get() = textViewDescription.text.toString()
        set(value) {
            textViewDescription.text = value
        }

    var expanded: Boolean
        get() = textViewDescription.visibility == View.VISIBLE
        set(value) {
            if (value) {
                imageViewArrow.rotation = 180F
                textViewDescription.visibility = VISIBLE
            } else {
                imageViewArrow.rotation = 0F
                textViewDescription.visibility = GONE
            }
        }

    init {

        View.inflate(context, R.layout.list_item, this)

        constraintLayoutHeader = findViewById(R.id.constraintLayoutHeader)
        textViewName = findViewById(R.id.textViewName)
        textViewTitle = findViewById(R.id.textViewTitle)
        textViewDescription = findViewById(R.id.textViewDescription)
        imageViewArrow = findViewById(R.id.imageViewArrow)

        textViewDescription.visibility = GONE

        val attributes = context.obtainStyledAttributes(
            attrs,
            R.styleable.ListItem
        )
        textViewName.text = attributes.getString(R.styleable.ListItem_personName)
        textViewTitle.text = attributes.getString(R.styleable.ListItem_personTitle)
        textViewDescription.text = attributes.getString(R.styleable.ListItem_personDescription)
        attributes.recycle()

    }

    override fun setOnClickListener(listener: OnClickListener?) {
        constraintLayoutHeader.setOnClickListener(listener)
    }
}