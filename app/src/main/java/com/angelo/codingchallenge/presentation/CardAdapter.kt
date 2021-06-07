package com.angelo.codingchallenge.presentation

import android.graphics.Color
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.angelo.codingchallenge.R
import com.angelo.codingchallenge.data.model.Card
import com.angelo.codingchallenge.data.model.CardTypes
import com.angelo.codingchallenge.databinding.ListItemBinding
import com.bumptech.glide.Glide

class CardAdapter : RecyclerView.Adapter<CardViewHolder>() {

    private val cardList = ArrayList<Card>()

    fun setList(cards: List<Card>) {
        cardList.clear()
        cardList.addAll(cards)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(cardList[position])
    }

    override fun getItemCount(): Int = cardList.size
}

class CardViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(card: Card) {

        when (card?.card_type) {
            CardTypes.TEXT_TYPE -> {
                binding.imageView.visibility = View.GONE
                binding.descriptionTextView.visibility = View.GONE
                binding.titleTextView.visibility = View.VISIBLE

                binding.titleTextView.text = card?.card?.value
                binding.titleTextView.setTextColor(Color.parseColor(card?.card?.attributes?.text_color))
                binding.titleTextView.setTextSize(
                    TypedValue.COMPLEX_UNIT_SP,
                    card?.card?.attributes?.font?.size.toFloat()
                )
            }
            CardTypes.IMAGE_TITLE_DESCRIPTION_TYPE -> {
                binding.imageView.visibility = View.VISIBLE
                binding.descriptionTextView.visibility = View.VISIBLE
                binding.titleTextView.visibility = View.VISIBLE

                binding.titleTextView.text = card?.card?.title?.value
                binding.titleTextView.setTextColor(Color.parseColor(card?.card?.title?.attributes?.text_color))
                binding.titleTextView.setTextSize(
                    TypedValue.COMPLEX_UNIT_SP,
                    card?.card?.title?.attributes?.font?.size.toFloat()
                )

                binding.descriptionTextView.text = card?.card?.description?.value
                binding.descriptionTextView.setTextColor(Color.parseColor(card?.card?.description?.attributes?.text_color))
                binding.descriptionTextView.setTextSize(
                    TypedValue.COMPLEX_UNIT_SP,
                    card?.card?.description?.attributes?.font?.size.toFloat()
                )
                Glide.with(binding.imageView.context).load(card?.card?.image?.url)
                    .into(binding.imageView)
            }
            CardTypes.TITLE_DESCRIPTION_TYPE -> {

                binding.imageView.visibility = View.GONE
                binding.descriptionTextView.visibility = View.VISIBLE
                binding.titleTextView.visibility = View.VISIBLE

                binding.titleTextView.text = card?.card?.title?.value
                binding.titleTextView.setTextColor(Color.parseColor(card?.card?.title?.attributes?.text_color))
                binding.titleTextView.setTextSize(
                    TypedValue.COMPLEX_UNIT_SP,
                    card?.card?.title?.attributes?.font?.size.toFloat()
                )
                binding.descriptionTextView.text = card?.card?.description?.value
                binding.descriptionTextView.setTextColor(Color.parseColor(card?.card?.description?.attributes?.text_color))
                binding.descriptionTextView.setTextSize(
                    TypedValue.COMPLEX_UNIT_SP,
                    card?.card?.description?.attributes?.font?.size.toFloat()
                )
            }
        }
    }
}