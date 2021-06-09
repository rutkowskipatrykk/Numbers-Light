package com.example.numberslight.view.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.numberslight.databinding.ListItemBinding;
import com.example.numberslight.model.ListElement;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListItemViewHolder> {

    private ArrayList<ListElement> itemList;
    private OnClickListElement listener;
    private ListElement selectedItem = null;

    public ListAdapter(ArrayList<ListElement> itemList, OnClickListElement listener, ListElement selectedItem) {
        this.itemList = itemList;
        this.listener = listener;
        if (selectedItem != null) {
            this.selectedItem = selectedItem;
        }
    }

    public void setItemList(List<ListElement> itemList) {
        DiffUtil.Callback diffCallback = new ListDiffUtils(this.itemList, itemList);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(diffCallback);
        this.itemList.clear();
        this.itemList.addAll(itemList);
        result.dispatchUpdatesTo(this);
    }

    public void setSelectedItem(ListElement selectedItem) {
        this.selectedItem = selectedItem;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ListItemBinding binding = ListItemBinding.inflate(inflater, parent, false);
        return new ListItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {
        holder.bind(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ListItemViewHolder extends RecyclerView.ViewHolder {

        private ListItemBinding binding;

        public ListItemViewHolder(@NonNull ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(ListElement item) {
            if (selectedItem != null) {
                binding.elementContainer.setSelected(item.getName().contentEquals(selectedItem.getName()) );
            } else {
                binding.elementContainer.setSelected(false);
            }
            binding.setModel(item);
            binding.setListener(item1 -> {
                selectedItem = item1;
                listener.onClick(item1);
                notifyDataSetChanged();
            });
            binding.executePendingBindings();
        }

    }

}
