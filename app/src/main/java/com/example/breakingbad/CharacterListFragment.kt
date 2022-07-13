package com.example.breakingbad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.breakingbad.ui.BreakingBadApplication
import com.example.breakingbad.ui.CharacterListViewModel
import com.example.breakingbad.ui.utils.CharacterListAdapter

class CharacterListFragment : Fragment() {

    private val characterListViewModel: CharacterListViewModel by viewModels {
        CharacterListViewModel.CharacterListViewModelFactory((requireActivity().application as BreakingBadApplication).characterRepository)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_character_list, container, false)
        super.onActivityCreated(savedInstanceState)

        val rvCharacterList = requireActivity().findViewById<RecyclerView>(R.id.characterList)

        var adapter = CharacterListAdapter {
            val adapter = CharacterListAdapter { bbCharacter ->
                if (bbCharacter.img != null) {

                }
            }
        }
        rvCharacterList.adapter = adapter
        characterListViewModel.character.observe(viewLifecycleOwner, { bbCharacter ->
            adapter.submitList(bbCharacter)
        })

        var refreshLayout = requireActivity().findViewById<SwipeRefreshLayout>(R.id.refresh_layout)
        refreshLayout.setOnRefreshListener {
            characterListViewModel.refreshDataFromRepository()
            Toast.makeText(requireContext(), "Data is Refreshed", Toast.LENGTH_LONG).show()
            refreshLayout.isRefreshing = false

        }
    }
}