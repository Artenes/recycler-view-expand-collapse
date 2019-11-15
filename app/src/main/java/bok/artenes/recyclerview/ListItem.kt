package bok.artenes.recyclerview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class ListItem(context: Context, attrs: AttributeSet): FrameLayout(context, attrs), View.OnClickListener {

    private val textViewName: TextView
    private val textViewTitle: TextView
    private val textViewDescription: TextView
    private val imageViewArrow: ImageView
    private var isExpanded: Boolean = false

    init {

        View.inflate(context, R.layout.list_item, this)

        val header = findViewById<ConstraintLayout>(R.id.constraintLayoutHeader)
        textViewName = findViewById(R.id.textViewName)
        textViewTitle = findViewById(R.id.textViewTitle)
        textViewDescription = findViewById(R.id.textViewDescription)
        imageViewArrow = findViewById(R.id.imageViewArrow)

        textViewDescription.layoutParams.height = 0
        header.setOnClickListener(this)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.ListItem)
        textViewName.text = attributes.getString(R.styleable.ListItem_personName)
        textViewTitle.text = attributes.getString(R.styleable.ListItem_personTitle)
        textViewDescription.text = attributes.getString(R.styleable.ListItem_personDescription)
        attributes.recycle()

    }

    fun setName(name: String) {
        textViewName.text = name
    }

    fun setTitle(title: String) {
        textViewTitle.text = title
    }

    fun setDescription(description: String) {
        textViewDescription.text = description
    }

    fun setExpanded(expanded: Boolean) {
        isExpanded = expanded
        if (isExpanded) {
            imageViewArrow.animate().setDuration(200).rotation(180F)
            textViewDescription.startAnimation(ExpandAnimation(textViewDescription))
        } else {
            imageViewArrow.animate().setDuration(200).rotation(0F)
            textViewDescription.startAnimation(CollapseAnimation(textViewDescription))
        }
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.constraintLayoutHeader) {
            setExpanded(!isExpanded)
        }
    }
}