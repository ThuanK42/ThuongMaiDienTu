package com.thuannluit.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.thuannluit.model.Employee;
import com.thuannluit.myapplication.R;

import java.util.List;

public class EmployeeAdapter extends ArrayAdapter<Employee> { // Lop nay chua cac thanh phan dc ve tren 1 dong

    @NonNull
    Activity context;
    int resource;
    @NonNull
    List<Employee> objects;

    public EmployeeAdapter(@NonNull Activity context, int resource, @NonNull List<Employee> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Gọi layoutInflater ra để bắt đầu ánh xạ view và data.
        LayoutInflater inflater = this.context.getLayoutInflater();
        // Đổ dữ liệu vào biến View, view này chính là những gì nằm trong item.xml
        View row = inflater.inflate(this.resource, null);

        //anh xa view
        TextView textView = (TextView) row.findViewById(R.id.txtdulieu);

        // gan gia tri
        final Employee employee = this.objects.get(position); // lay ra vi tri doi tuong de ve
        textView.setText(employee.toString());
        return row;
    }
}
