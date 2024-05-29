package com.example.ex2_task5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.ex2_task5.model.api.Todo;

import java.util.List;

public class TodoArrayAdapter extends ArrayAdapter<Todo> {
    /*
    Instructions for CustomArrayAdapter by @james-wasson:

    1. Override the getView() method. This method is called for each item in the list when it needs to be displayed.
    2. In the getView() method, inflate a layout from todo_item.xml if the convertView parameter is null. This layout will be used to display a single Todo item.
    3. Get the Todo object for the current list item.
    4. Find each view in the layout (e.g., TextViews, ImageViews) and set its content based on the properties of the Todo object.
    5. Set an OnClickListener for the ImageViews that represent the finished and importance status. When clicked, these should toggle the corresponding property of the Todo object and update the image source accordingly.
    6. Return the inflated and populated view.
    */

    public TodoArrayAdapter(Context context, List<Todo> todos) {
        super(context, 0, todos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.todo_item, parent, false);
        }

        Todo todo = getItem(position);

        TextView titleTextView = convertView.findViewById(R.id.todoTitle);
        titleTextView.setText(todo.getTitle());

        TextView descriptionTextView = convertView.findViewById(R.id.todoDescription);
        descriptionTextView.setText(todo.getDescription());

        TextView dueDateTextView = convertView.findViewById(R.id.todoDueDate);
        dueDateTextView.setText("Due Date: " + String.valueOf(todo.getDueDate()));

        ImageView isFinishedImageView = convertView.findViewById(R.id.todoIsFinished);
        isFinishedImageView.setImageResource(todo.isFinished() ? R.drawable.star_yellow : R.drawable.star_grey);
        isFinishedImageView.setOnClickListener(v -> {
            todo.setFinished(!todo.isFinished());
            isFinishedImageView.setImageResource(todo.isFinished() ? R.drawable.star_yellow : R.drawable.star_grey);
        });

        ImageView isImportantImageView = convertView.findViewById(R.id.todoIsImportant);
        isImportantImageView.setImageResource(todo.isImportant() ? R.drawable.star_yellow : R.drawable.star_grey);
        isImportantImageView.setOnClickListener(v -> {
            todo.setImportant(!todo.isImportant());
            isImportantImageView.setImageResource(todo.isImportant() ? R.drawable.star_yellow : R.drawable.star_grey);
        });

        return convertView;
    }
}
