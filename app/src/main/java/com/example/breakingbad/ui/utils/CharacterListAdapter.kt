package com.example.breakingbad.ui.utils

import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.breakingbad.R
import com.example.breakingbad.databinding.ItemCharacterBinding
import com.example.breakingbad.model.BBCharacter

class CharacterListAdapter(private val clickCallback: ((BBCharacter) -> Unit)?) : ListAdapter<BBCharacter, CharacterListAdapter.CharacterViewHolder>(CharacterCompare()) {

    class CharacterViewHolder(var binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val characterName: TextView = itemView.findViewById(R.id.character_name)
        private val characterNickname: TextView = itemView.findViewById(R.id.character_nickname)
        private val characterBirthday: TextView = itemView.findViewById(R.id.character_birthdate)
        private val characterOccupation: TextView = itemView.findViewById(R.id.character_occupation)
        private val characterStatus: TextView = itemView.findViewById(R.id.character_status)
        private val characterImage: ImageView = itemView.findViewById(R.id.character_image)


        fun bind(character: BBCharacter) {
            characterName.text = character.name
            characterNickname.text = character.nickname
            characterBirthday.text = character.birthday
            characterOccupation.text = character.occupation.joinToString { "," }
            characterStatus.text = character.status

            if (character.img != null) {
                Glide.with(itemView).load(character.img).centerCrop().into(characterImage)

            }
        }

        companion object {
            fun create(parent: ViewGroup): CharacterViewHolder {
                return CharacterViewHolder(
                    ItemCharacterBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
        holder.itemView.setOnClickListener {
            clickCallback?.invoke(character)
        }
    }
}

class CharacterCompare() : DiffUtil.ItemCallback<BBCharacter>() {
    override fun areItemsTheSame(oldItem: BBCharacter, newItem: BBCharacter): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BBCharacter, newItem: BBCharacter): Boolean {
        return oldItem.name == newItem.name
                && oldItem.birthday == newItem.birthday
                && oldItem.status == newItem.status
                && oldItem.occupation == newItem.occupation
                && oldItem.nickname == newItem.nickname
                && oldItem.img == newItem.img
    }

}