package com.example.ex2_task5;

import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ex2_task5.accessor.impl.TodoListAccessorImpl;
import com.example.ex2_task5.accessor.spi.TodoListAccessor;
import com.example.ex2_task5.model.api.Todo;
import java.util.Collections;
import java.util.Comparator;

public class TodoListActivity extends AppCompatActivity {
    private TodoListAccessor todoListAccessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*
        the onCreate method, which is called when the activity is starting,
        will set the content view to R.layout.todolist, initialize the TodoListAccessor,
        and set up the ListView and EditText for displaying the to-do list and adding new to-dos,
        respectively.
        */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todolist);

        todoListAccessor = new TodoListAccessorImpl(this);

        final ListView listView = findViewById(R.id.listViewToDos);

        // The Comparator is implemented by Copilot
        // Create a custom Comparator for Todo objects
        Comparator<Todo> todoComparator = (todo1, todo2) -> {
            if (todo1.isFinished() == todo2.isFinished()) {
                // If both todos have the same finished status, compare their due dates
                return Long.compare(todo1.getDueDate(), todo2.getDueDate());
            } else {
                // Unfinished todos should come before finished todos
                return todo1.isFinished() ? 1 : -1;
            }
        };

        // Sort the Todo list
        Collections.sort(todoListAccessor.getTodoList(), todoComparator);

        // Set the sorted list to the adapter
        final TodoArrayAdapter adapter = new TodoArrayAdapter(this, todoListAccessor.getTodoList());
        listView.setAdapter(adapter);

        final EditText editTextNewTodo = findViewById(R.id.editTextNewTodo);

        editTextNewTodo.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String newTodoTitle = editTextNewTodo.getText().toString().trim();
                if (!newTodoTitle.isEmpty()) {
                    Todo newTodo = new Todo();
                    newTodo.setTitle(newTodoTitle);
                    todoListAccessor.addTodo(newTodo);

                    // Sort the Todo list again after adding a new Todo
                    Collections.sort(todoListAccessor.getTodoList(), todoComparator);

                    adapter.notifyDataSetChanged();
                    editTextNewTodo.setText("");
                    return true;
                }
            }
            return false;
        });
    }
}