package com.chathra.fernanpharmacy.ui.patientEditProfile;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.chathra.fernanpharmacy.PatientActivity;
import com.chathra.fernanpharmacy.R;
import com.chathra.fernanpharmacy.animDialog.AnimDialog;
import com.chathra.fernanpharmacy.common.ComLib;
import com.chathra.fernanpharmacy.databinding.FragmentDoctorEditProfileBinding;
import com.chathra.fernanpharmacy.databinding.FragmentPatientEditProfileBinding;
import com.chathra.fernanpharmacy.model.Doctor;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Date;

import static android.app.Activity.RESULT_OK;
import static com.chathra.fernanpharmacy.common.ComActions.UPDATEDOCTORACCOUNT;
import static com.chathra.fernanpharmacy.common.ComActions.UPDATEPATIENTACCOUNT;
import static com.chathra.fernanpharmacy.common.ComLoaders.LOADDOCTORDETAILS;
import static com.chathra.fernanpharmacy.common.ComLoaders.LOADPATIENTDETAILS;
import static com.chathra.fernanpharmacy.common.Config.URL;

public class PatientEditProfileFragment extends Fragment {

    FragmentPatientEditProfileBinding binding;

    AnimDialog ImagePickerDialog;
    AnimDialog responseAnimDialog;
    KProgressHUD hud;
    private Uri profileImageUri;

    private final int PICK_IMAGE_REQUEST = 10;
    private final int CAPTURE_IMAGE_REQUEST = 11;

    private final int WRITE_STORAGE_REQUEST_CODE = 101;

    public Doctor doctor;
    public String doctorDocumentId;

    String encodedBase64Image = "";

    PatientActivity patientActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentPatientEditProfileBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        patientActivity = (PatientActivity) requireActivity();

        hud = KProgressHUD.create(requireActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

        init(patientActivity.getCurrentUser().getId());

        binding.profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImagePickerDialog();
            }
        });

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAccount();
            }
        });

    }


    void init(int id) {

        System.out.println("init --- " + id);

//        hud.show();

        RequestQueue queue = Volley.newRequestQueue(requireActivity());
        String url = URL + LOADPATIENTDETAILS + "/" + id;
        System.out.println(url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("Response", "Response is: " + response);
                hud.dismiss();
                try {
                    int status = response.getInt("status");
                    System.out.println(status);

                    binding.editName.setText(response.getString("fullName"));
                    binding.editMobile.setText(response.getString("mobile"));
                    binding.editEmail.setText(response.getString("email"));
                    binding.editAddress.setText(response.getString("address"));



                    if (response.getString("bloodGroup") != null) {
                        binding.spinnerBloodGroup.setSelection(((ArrayAdapter<String>) binding.spinnerBloodGroup.getAdapter()).getPosition(response.getString("bloodGroup")));
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Response", error.toString());
            }
        });
        queue.add(jsonObjectRequest);

    }


    void showImagePickerDialog() {
        if (ImagePickerDialog == null) {
            ImagePickerDialog = new AnimDialog(requireActivity())
                    .createAnimatedDualDialog()
                    .setAnimation(R.raw.camera)
                    .setTitle("Select Image")
                    .setContent("You can either select an image from the Gallery or Capture an image from the camera.\nCapturing images from camera requires Storage permissions make sure to provide them.")
                    .setButton1("Camera", 0, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            checkStoragePermissionsAndOpenCamera(CAPTURE_IMAGE_REQUEST);
                            ImagePickerDialog.dismiss();
                        }
                    })
                    .setButton2("Gallery", 0, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            openFileChooser(PICK_IMAGE_REQUEST);
                            ImagePickerDialog.dismiss();
                        }
                    });
        }

        ImagePickerDialog.show();
    }

    private void checkStoragePermissionsAndOpenCamera(int requestCode) {

        boolean permissionStatus = ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED;

        if (ContextCompat.checkSelfPermission(requireActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_STORAGE_REQUEST_CODE);

        } else {
            dispatchTakePictureIntent(requestCode);
        }
    }


    private void dispatchTakePictureIntent(int requestCode) {

        // Check Camera
        if (requireActivity().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {

            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(takePictureIntent, requestCode);
        }
    }


    private void openFileChooser(int requestCode) {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, requestCode);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case WRITE_STORAGE_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(requireActivity(),
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(requireActivity(), "Permission Granted", Toast.LENGTH_SHORT).show();

                        checkStoragePermissionsAndOpenCamera(CAPTURE_IMAGE_REQUEST);

                    }
                } else {
                    Toast.makeText(requireActivity(), "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {

            if (requestCode == PICK_IMAGE_REQUEST) { //  gallery
                Glide.with(this).load(data.getData()).into(binding.profileImage);

                profileImageUri = data.getData();

                try {

                    ByteArrayOutputStream applicationImageOutputStream = new ByteArrayOutputStream();
                    uriToBitmap(data.getData()).compress(Bitmap.CompressFormat.PNG, 100, applicationImageOutputStream);
                    byte[] applicationImageByteArray = applicationImageOutputStream.toByteArray();
                    encodedBase64Image = Base64.encodeToString(applicationImageByteArray, Base64.DEFAULT);

                    System.out.println("encodedBase64Image - " + encodedBase64Image);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (requestCode == CAPTURE_IMAGE_REQUEST) {  //  camera
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                Glide.with(this).load(photo).into(binding.profileImage);

                profileImageUri = getImageUri(requireActivity(), photo);

                System.out.println(profileImageUri);

                try {

                    ByteArrayOutputStream applicationImageOutputStream = new ByteArrayOutputStream();
                    photo.compress(Bitmap.CompressFormat.PNG, 100, applicationImageOutputStream);
                    byte[] applicationImageByteArray = applicationImageOutputStream.toByteArray();
                    encodedBase64Image = Base64.encodeToString(applicationImageByteArray, Base64.DEFAULT);

                    System.out.println("encodedBase64Image - " + encodedBase64Image);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }
    }


    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }


    private Bitmap uriToBitmap(Uri selectedFileUri) {
        Bitmap image = null;

        try {
            ParcelFileDescriptor parcelFileDescriptor = requireActivity().getContentResolver().openFileDescriptor(selectedFileUri, "r");
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            image = BitmapFactory.decodeFileDescriptor(fileDescriptor);

            parcelFileDescriptor.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }


    synchronized void updateAccount() {
        String fullName = binding.editName.getText().toString();
        String mobile = binding.editMobile.getText().toString();
//        String email = binding.editEmail.getText().toString();
        String bloodGroup = binding.spinnerBloodGroup.getSelectedItem().toString();
//        Date dob = ComLib.getDateObject(binding.editDob.getText().toString());
        String address = binding.editAddress.getText().toString();


        System.out.println("update detauilsssssssssssss");

        if (fullName.equals("") || fullName == null) {
            Toast.makeText(requireActivity(), "Please Enter Your Name!", Toast.LENGTH_SHORT).show();
        } else if (mobile.equals("") || mobile == null) {
            Toast.makeText(requireActivity(), "Please Enter Your Mobile No!", Toast.LENGTH_SHORT).show();
        } else {
            hud.show();

            JSONObject jsonRequest = new JSONObject();
            try {
                jsonRequest.put("id", patientActivity.getCurrentUser().getId());
                jsonRequest.put("fullName", fullName);
                jsonRequest.put("mobile", mobile);
                jsonRequest.put("bloodGroup", bloodGroup);
                jsonRequest.put("address", address);
//                jsonRequest.put("dob", dob);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            System.out.println(jsonRequest.toString());


            RequestQueue queue = Volley.newRequestQueue(requireActivity());
            String url = URL + UPDATEPATIENTACCOUNT;

            System.out.println(url);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonRequest, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    hud.dismiss();
                    Log.i("Response", "Response is: " + response);

                    System.out.println(response);
                    Toast.makeText(requireActivity(), "Successfully Updated!", Toast.LENGTH_SHORT).show();
//                    try {
//                        int status = response.getInt("status");
//                        System.out.println(status);
//
//                        Toast.makeText(requireActivity(), response.getString("message"), Toast.LENGTH_SHORT).show();
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("Response", error.toString());

                }
            });
            queue.add(jsonObjectRequest);
        }
    }
}