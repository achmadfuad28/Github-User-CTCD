package comtest.ct.cd.achmadfuad.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import comtest.ct.cd.achmadfuad.R
import comtest.ct.cd.achmadfuad.domain.entities.UserItemResult
import comtest.ct.cd.achmadfuad.presentation.widget.WebImageView
import kotlinx.android.synthetic.main.item_list.view.*

class ItemAdapter(private val vehicles: MutableList<UserItemResult>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return vehicles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(vehicles[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindTo(user: UserItemResult) {

            itemView.apply {
                tv_owner_name.text = user.name
                user.avatar?.let {
                    img_owner_avatar.setImageUrl(it, WebImageView.TransformType.ROUNDED_CORNER)
                }

            }

        }


    }
}