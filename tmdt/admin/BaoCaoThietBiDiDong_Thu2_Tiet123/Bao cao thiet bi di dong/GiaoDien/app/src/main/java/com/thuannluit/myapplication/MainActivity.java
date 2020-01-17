
package com.thuannluit.myapplication;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.thuannluit.adapter.EmployeeAdapter;
import com.thuannluit.model.Employee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Khai bao bo phan hien thi trong screen
    private EditText txtid, txtsohieunhanvien, txtname, txtphone, txtaddress, txtemail;
    private ImageButton btntimkiem, btnthem, btnsua, btnxoa, btncapnhat;
    private RadioButton btnnam, btnnu;
    private RadioGroup btngioitinh;
    private TextView txtngaysinh;
    private DatePickerDialog.OnDateSetListener mdateSetListener;

    // Khai bao phia Adapter + ListView
    private ListView lvNhanVien;
    private List<Employee> listEmployee = new ArrayList<>();
    private EmployeeAdapter employeeAdapter;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static String gender, name = "";
    private static Integer id;

    // Khai bao TAG
    private static final String TAG = "MainActivity";

    //danh sach dia chi ip khi dung mang wifi hien tai
    // Chu y: Thay the 192.168.1.9 = dia chi ipv4
    // ip lay danh sach toan bo
    private static final String URLGETDATA = "http://192.168.1.9:8080/employees/";
    // ip tim nhan vien theo ten
    private static final String URLFindOneEmployeeByName = "http://192.168.1.9:8080/employees/find-by-name/";
    // ip tim nhan vien theo id
    private static final String URLFindOneEmployeeById = "http://192.168.1.9:8080/employees/find-by-id/";
    //ip tao nhan vien
    private static final String URLCreateEmployee = "http://192.168.1.9:8080/employees/";
    // ip cap nhat thong tin theo id
    private static final String URLUpdateEmployee = "http://192.168.1.9:8080/employees/";
    // xoa nhan vien theo id : 1 nhan vien
    private static final String URLDeleteEmployee = "http://192.168.1.9:8080/employees/";

    OkHttpClient Client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addControls() {
        // Anh xa tung thuoc tinh voi id tung thanh phan trong giao dien
        txtid = findViewById(R.id.txtid);
        txtsohieunhanvien = findViewById(R.id.txtsohieunhanvien);
        txtname = findViewById(R.id.txtten);
        txtngaysinh = findViewById(R.id.txtngaysinh);
        txtphone = findViewById(R.id.txtdienthoai);
        txtemail = findViewById(R.id.txtemail);
        txtaddress = findViewById(R.id.txtdiachi);

        btntimkiem = findViewById(R.id.btntimkiem);
        btnthem = findViewById(R.id.btnthem);
        btnsua = findViewById(R.id.btnchinhsua);
        btnxoa = findViewById(R.id.btnxoa);
        btncapnhat = findViewById(R.id.btncapnhat);
        btngioitinh = findViewById(R.id.btngioitinh);
        btnnam = findViewById(R.id.btnnam);
        btnnu = findViewById(R.id.btnnu);

    }

    private void addEvents() {
        // lay danh sach hien tai
        btncapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

        // Tim kiem theo ten
        btntimkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listEmployee.clear();
                try {
                    name = txtname.getText().toString(); // lay gia tri tu txtname gan cho name
                    AsyncTask asyncTask = new AsyncTask() { //AsyncTask() xu ly da tien trinh
                        @Override
                        protected Object doInBackground(Object[] objects) {
                            // Khởi tạo OkHttpClient để lấy dữ liệu.

                            // Tạo request lên server.
                            Request request = new Request.Builder().url(URLFindOneEmployeeByName + name).build();
                            //        Request request = new Request.Builder().url(URLFindOneEmployeeById + id).build();
                            Response response = null;
                            try {
                                // Thực thi request.
                                response = Client.newCall(request).execute();
                                return response.body().string(); // Lấy thông tin JSON trả về.
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Object o) {
                            ObjectMapper mapper = new ObjectMapper(); // Chuoi doi tuong sang chuoi JSON
                            String json = o.toString();
                            try {
                                //    readValue() của đối tượng này để chyển đổi chuỗi JSON sang đối tượng Java
                                // o day se la 1 list empployees
                                Employee[] pp1 = mapper.readValue(json, Employee[].class);
                                for (Employee person : pp1) {
                                    System.out.println(person);
                                    listEmployee.add(person); // add tung phan phan tu trong list danh sach tim duoc vao listEmployee
                                }
                                lvNhanVien = findViewById(R.id.lvdanhsachnhanvien);
                                employeeAdapter = new EmployeeAdapter(MainActivity.this, R.layout.item, listEmployee);
                                lvNhanVien.setAdapter(employeeAdapter);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                resetDataInput();
            }
        });

        // Them 1 doi tuong
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listEmployee.clear(); // xoa danh sach hien tai
                try {

                    // get gia tri tung o input
                    final String sohieunhanvien = txtsohieunhanvien.getText().toString();
                    final String name = txtname.getText().toString();
                    final String birthday = txtngaysinh.getText().toString();
                    if (btnnam.isChecked()) { // neu radio button chon male -> ko hoac nu
                        gender = "nam";
                    } else if (btnnu.isChecked()) {
                        gender = "nu";
                    } else {
                        gender = "khong xac dinh"; // con ko chon thi mac dinh no la gioi tinh khong xac dinh
                    }
                    final String phone = txtphone.getText().toString();
                    final String email = txtemail.getText().toString();
                    final String address = txtaddress.getText().toString();

                    if (!(sohieunhanvien.isEmpty())
                            && !(name.isEmpty())
                            && (!birthday.isEmpty())
                            && (!(phone.isEmpty()))
                            && (!(email.isEmpty()))
                            && (!(address.isEmpty()))
                    ) {
                        AsyncTask asyncTask = new AsyncTask() {
                            @Override
                            protected Object doInBackground(Object[] objects) {
                                // Chuoi du lieu json can chuyen di de tao doi tuong
                                String jsonData = " {\n" +
                                        "        \"no\": \"" + sohieunhanvien + "\",\n" +
                                        "        \"name\": \"" + name + "\",\n" +
                                        "        \"birthday\": \"" + birthday + "\",\n" +
                                        "        \"gender\": \"" + gender + "\",\n" +
                                        "        \"phone\": \"" + phone + "\",\n" +
                                        "        \"email\": \"" + email + "\",\n" +
                                        "        \"address\": \"" + address + "\"\n" + "\n" +
                                        "    }";
                                // Doc them document https://square.github.io/okhttp/3.x/okhttp/okhttp3/RequestBody.html
                                // RequestBody.create(JSON, jsonData)
                                // Tao du lieu la 1 doi tuong json de gui di
                                RequestBody requestBody = RequestBody.create(JSON, jsonData);
                                // Tao request (1 doi tuong) len server thong qua phuong thuc post
                                Request request = new Request.Builder().url(URLCreateEmployee).post(requestBody).build();
                                Response response = null;
                                try {
                                    response = Client.newCall(request).execute();
                                    return response.body().string();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                return null;
                            }
                        }.execute();

                        getData(); // cap nhat lai list view de no hien thi data hien tai

                        Toast.makeText(MainActivity.this, "Them doi tuong THANH CONG", Toast.LENGTH_LONG).show(); // thong bao them doi tuong thanh cong
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Them doi tuong THAT BAI", Toast.LENGTH_LONG).show(); // thong bao them doi tuong that bai
                    e.printStackTrace();
                }
                resetDataInput(); // xoa sach du lieu tai cac o input
            }
        });

        // Chinh sua 1 doi tuong
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listEmployee.clear(); // xoa du lieu hien tai
                try {

                    // get lay data tung o input
                    id = Integer.parseInt(txtid.getText().toString());
                    final String sohieunhanvien = txtsohieunhanvien.getText().toString();
                    final String name = txtname.getText().toString();
                    final String birthday = txtngaysinh.getText().toString();
                    if (btnnam.isChecked()) {
                        gender = "nam";
                    } else if (btnnu.isChecked()) {
                        gender = "nu";
                    } else {
                        gender = "khong xac dinh";
                    }
                    final String phone = txtphone.getText().toString();
                    final String email = txtemail.getText().toString();
                    final String address = txtaddress.getText().toString();

                    AsyncTask asyncTask = new AsyncTask() {
                        @Override
                        protected Object doInBackground(Object[] objects) {

                            String jsonData = " {\n" +
                                    "        \"no\": \"" + sohieunhanvien + "\",\n" +
                                    "        \"name\": \"" + name + "\",\n" +
                                    "        \"birthday\": \"" + birthday + "\",\n" +
                                    "        \"gender\": \"" + gender + "\",\n" +
                                    "        \"phone\": \"" + phone + "\",\n" +
                                    "        \"email\": \"" + email + "\",\n" +
                                    "        \"address\": \"" + address + "\"\n" + "\n" +
                                    "    }";

                            RequestBody requestBody = RequestBody.create(JSON, jsonData);

                            Request request = new Request.Builder().url(URLUpdateEmployee + id).put(requestBody).build();
                            Response response = null;

                            try {
                                response = Client.newCall(request).execute();
                                return response.body().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }
                    }.execute();

                    getData();

                    Toast.makeText(MainActivity.this, "Chinh sua doi tuong THANH CONG", Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Chinh sua doi tuong THAT BAI", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                resetDataInput(); // xoa sach du lieu cac o input
            }
        });

        // Xoa 1 doi tuong
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listEmployee.clear();
                try {

                    id = Integer.parseInt(txtid.getText().toString());

                    AsyncTask asyncTask = new AsyncTask() {
                        @Override
                        protected Object doInBackground(Object[] objects) {

                            Request request = new Request.Builder().url(URLDeleteEmployee + id).delete().build();
                            Response response = null;
                            try {
                                response = Client.newCall(request).execute();
                                return response.body().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }
                    }.execute();

                    getData();

                    Toast.makeText(MainActivity.this, "Xoa doi tuong THANH CONG", Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Xoa doi tuong THAT BAI", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                resetDataInput(); // xoa sach du lieu cac o input
            }
        });

        // lay thoi gian tu datepicker
        txtngaysinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // du lieu ngay thang nam tu he thong
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mdateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mdateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDateSet: date: " + dayOfMonth + "/" + month + "/" + year);
                String date = dayOfMonth + "/" + month + "/" + year; // lay du lieu ngay thang nam qua lua chon cua nguoi dung
                txtngaysinh.setText(date);// set du lieu cho o text birthday
            }
        };

    }

    private void getData() {
        listEmployee.clear(); // xoa du lieu hien tai
        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {

                Request request = new Request.Builder().url(URLGETDATA).build();
                Response response = null;
                try {
                    response = Client.newCall(request).execute();
                    return response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                ObjectMapper mapper = new ObjectMapper();
                String json = o.toString();
                try {
                    Employee[] pp1 = mapper.readValue(json, Employee[].class);
                    for (Employee person : pp1) {
                        listEmployee.add(person);
                    }
                    lvNhanVien = findViewById(R.id.lvdanhsachnhanvien);
                    employeeAdapter = new EmployeeAdapter(MainActivity.this, R.layout.item, listEmployee);
                    lvNhanVien.setAdapter(employeeAdapter);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }

    // xoa het gia tri cac o
    private void resetDataInput() {
        // Dua du lieu cac o text ve ""
        txtid.setText("");
        txtsohieunhanvien.setText("");
        txtname.setText("");
        txtngaysinh.setText("");
        txtphone.setText("");
        txtemail.setText("");
        txtaddress.setText("");
        txtid.requestFocus();// dua con tro ve text id
    }

}
