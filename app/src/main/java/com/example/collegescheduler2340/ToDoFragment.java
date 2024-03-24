package com.example.collegescheduler2340;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class ToDoFragment extends Fragment {

    private ToDoViewModel viewModel;
    private CustomAdapter customAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_do, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(ToDoViewModel.class);

        ArrayList<ToDoTask> taskArrayList = viewModel.getTaskArrayList();

        customAdapter = new CustomAdapter(taskArrayList);

        ListView listView = view.findViewById(R.id.listView);

        listView.setAdapter(customAdapter);

        view.findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });

        Spinner sortSpinner = view.findViewById(R.id.sortSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.sort_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(adapter);

        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedSortOption = parentView.getItemAtPosition(position).toString();
                sortTaskArrayList(selectedSortOption);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                //nothing done
            }
        });

        return view;
    }

    private void sortTaskArrayList(String selectedSortOption) {
        switch (selectedSortOption) {
            case "Date":
                Collections.sort(viewModel.getTaskArrayList(), new Comparator<ToDoTask>() {
                    @Override
                    public int compare(ToDoTask task1, ToDoTask task2) {
                        return task1.getDate().compareTo(task2.getDate());
                    }
                });
                break;
            case "Course":
                Collections.sort(viewModel.getTaskArrayList(), new Comparator<ToDoTask>() {
                    @Override
                    public int compare(ToDoTask task1, ToDoTask task2) {
                        return task1.getCourse().compareTo(task2.getCourse());
                    }
                });
                break;
            case "Completed":
                Collections.sort(viewModel.getTaskArrayList(), new Comparator<ToDoTask>() {
                    @Override
                    public int compare(ToDoTask task1, ToDoTask task2) {
                        return Boolean.compare(task2.getCompleted(), task1.getCompleted());
                    }
                });
                break;
        }

        customAdapter.notifyDataSetChanged();
    }

    private void addItem() {
        String userInput = ((EditText) requireView().findViewById(R.id.editText)).getText().toString().trim();
        String dateInput = ((EditText) requireView().findViewById(R.id.dateEditText)).getText().toString().trim();
        String courseInput = ((EditText) requireView().findViewById(R.id.courseEditText)).getText().toString().trim();

        if (!userInput.isEmpty()) {
            Date inputDate;
            if (!dateInput.isEmpty()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
                try {
                    inputDate = dateFormat.parse(dateInput);
                } catch (ParseException e) {
                    e.printStackTrace();
                    //parse error
                    return;
                }
            } else {
                inputDate = new Date();
            }

            String course = courseInput.isEmpty() ? "No course" : courseInput;

            ToDoTask newTask = new ToDoTask(userInput, inputDate, course, "No Location");
            viewModel.getTaskArrayList().add(newTask);
            customAdapter.notifyDataSetChanged();
            ((EditText) requireView().findViewById(R.id.editText)).setText("");
            ((EditText) requireView().findViewById(R.id.dateEditText)).setText("");
            ((EditText) requireView().findViewById(R.id.courseEditText)).setText("");
        }
    }

    private boolean isValidDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
            sdf.setLenient(false);
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private Date parseDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    private void sortListByDate() {
        Collections.sort(viewModel.getTaskArrayList());
        customAdapter.notifyDataSetChanged();
    }

    private void sortListByCourse() {
        Collections.sort(viewModel.getTaskArrayList(), customAdapter);
        customAdapter.notifyDataSetChanged();
    }

    private void handleTextEditing(final int position) {
        final EditText editText = new EditText(requireContext());
        editText.setText(viewModel.getTaskArrayList().get(position).getText());

        new AlertDialog.Builder(requireContext())
                .setTitle("Edit Task")
                .setView(editText)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String editedText = editText.getText().toString().trim();
                        if (!editedText.isEmpty()) {
                            viewModel.getTaskArrayList().get(position).setText(editedText);
                            customAdapter.notifyDataSetChanged();
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    public class CustomAdapter extends ArrayAdapter<ToDoTask> implements Comparator<ToDoTask> {

        private boolean userCheckInteraction = true;

        public CustomAdapter(ArrayList<ToDoTask> taskArrayList) {
            super(requireContext(), R.layout.fragment_todo_list, taskArrayList);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_todo_list, parent, false);
            }

            TextView itemText = convertView.findViewById(R.id.itemText);
            TextView dateText = convertView.findViewById(R.id.dateText);
            TextView courseText = convertView.findViewById(R.id.courseText);
            ImageView deleteIcon = convertView.findViewById(R.id.deleteIcon);
            ImageView editIcon = convertView.findViewById(R.id.editIcon);
            CheckBox checkBox = convertView.findViewById(R.id.checkBox);

            ToDoTask currentTask = getItem(position);
            itemText.setText(currentTask.getText());

            dateText.setText(formatDate(currentTask.getDate()));

            courseText.setText(currentTask.getCourse());

            checkBox.setChecked(currentTask.getCompleted());
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (userCheckInteraction) {
                        viewModel.setTaskCompleted(position);
                        notifyDataSetChanged();
                    }
                }
            });

            deleteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewModel.getTaskArrayList().remove(position);
                    notifyDataSetChanged();
                }
            });

            editIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleTextEditing(position);
                }
            });

            return convertView;
        }

        private String formatDate(Date date) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
            return dateFormat.format(date);
        }

        public void setUserCheckInteraction(boolean isUserInteraction) {
            this.userCheckInteraction = isUserInteraction;
        }

        @Override
        public int compare(ToDoTask task1, ToDoTask task2) {
            return task1.getCourse().compareTo(task2.getCourse());
        }
    }
}