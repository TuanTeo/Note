package dev.tuanteo.note.view

import android.app.AlertDialog
import android.graphics.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import dev.tuanteo.note.R
import dev.tuanteo.note.adapter.NoteListAdapter
import dev.tuanteo.note.databinding.NoteListFragmentBinding
import dev.tuanteo.note.viewmodel.NoteListViewModel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NoteListFragment : Fragment() {

    private val p = Paint().apply {
        color = Color.BLACK
        // Smooths out edges of what is drawn without affecting shape.
        isAntiAlias = true
        // Dithering affects how colors with higher-precision than the device are down-sampled.
        isDither = true
        style = Paint.Style.STROKE // default: FILL
        strokeJoin = Paint.Join.ROUND // default: MITER
        strokeCap = Paint.Cap.ROUND // default: BUTT
        strokeWidth = 12f // default: Hairline-width (really thin)
    }

    private val adapter = NoteListAdapter()
    private val viewModel : NoteListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding : NoteListFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.note_list_fragment, container, false)

        binding.addNoteButton.setOnClickListener {
            findNavController().navigate(NoteListFragmentDirections.actionNoteListFragmentToNoteNewItemFragment(
                ""
            ))
        }

        binding.listNotesRecyclerview.adapter = adapter

        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.absoluteAdapterPosition
                if (direction == ItemTouchHelper.LEFT) {
                    adapter.notifyItemChanged(position)
                    val selectedNote = adapter.currentList[position]

                    viewModel.saveSwipedNoteID(selectedNote)

                    showRequestDeleteDialog(selectedNote.title)
//                } else if (direction == ItemTouchHelper.RIGHT) {
//                    adapter.notifyItemChanged(position)
//                    Toast.makeText(requireContext(), "Swipe right", Toast.LENGTH_SHORT).show()
                }
            }

            private fun showRequestDeleteDialog(noteTitle: String) {
                val dialog = AlertDialog.Builder(context)
                dialog.setTitle(getString(R.string.delete_dialog_title))
                dialog.setMessage("\'" + noteTitle + "\' " + getString(R.string.delete_dialog_message))
                dialog.setNegativeButton(getString(android.R.string.ok))
                { _, _ ->
                    viewModel.deleteSwipedNote()
                }

                dialog.setPositiveButton(getString(android.R.string.cancel)) { _, _ ->
                }

                dialog.create().show()
            }

            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    val itemView = viewHolder.itemView
                    val height = itemView.bottom.toFloat() - itemView.top.toFloat()
                    val width = height / 3

                    if (dX < 0) {
                        p.color = Color.RED
                        val icon =
                            AppCompatResources.getDrawable(requireContext(), R.drawable.ic_delete)
                                ?.toBitmap()
                        val margin = (dX / 5 - width) / 2
                        val iconDest = RectF(itemView.right.toFloat() + margin, itemView.top.toFloat() + width, itemView.right.toFloat() + (margin + width), itemView.bottom.toFloat() - width)
                        c.drawBitmap(icon!!, null, iconDest, p)
                    }
//                    if (dX > 0) {
//                        p.color = Color.BLUE
//                        val background = RectF(itemView.left.toFloat(), itemView.top.toFloat(), itemView.left.toFloat() + dX, itemView.bottom.toFloat())
//                        c.drawRect(background, p)
//                        val icon =
//                            AppCompatResources.getDrawable(requireContext(), R.drawable.ic_done)
//                                ?.toBitmap()
//
//                        val margin = (dX / 5 - width) / 2
//                        val iconDest = RectF(margin, itemView.top.toFloat() + width, margin + width, itemView.bottom.toFloat() - width)
//                        c.drawBitmap(icon!!, null, iconDest, p)
//                    }
                } else {
                    c.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX / 5, dY, actionState, isCurrentlyActive)
            }

        }

        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.listNotesRecyclerview)


        /*TuanTeo: Set data cho recycler view */
        setDataOnView()

        return binding.root
    }

    private fun setDataOnView() {
        viewModel.listNotes.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}