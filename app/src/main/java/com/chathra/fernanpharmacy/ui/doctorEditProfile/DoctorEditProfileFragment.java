package com.chathra.fernanpharmacy.ui.doctorEditProfile;

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
import com.chathra.fernanpharmacy.DoctorActivity;
import com.chathra.fernanpharmacy.R;
import com.chathra.fernanpharmacy.animDialog.AnimDialog;
import com.chathra.fernanpharmacy.common.ComLib;
import com.chathra.fernanpharmacy.databinding.FragmentDoctorEditProfileBinding;
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
import static com.chathra.fernanpharmacy.common.ComLoaders.LOADDOCTORDETAILS;
import static com.chathra.fernanpharmacy.common.Config.URL;


public class DoctorEditProfileFragment extends Fragment {

    FragmentDoctorEditProfileBinding binding;

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

    DoctorActivity doctorActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("DoctorEditProfileFragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDoctorEditProfileBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        doctorActivity = (DoctorActivity) requireActivity();

        System.out.println(doctorActivity.getCurrentUser().getType());

        hud = KProgressHUD.create(requireActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

        init(doctorActivity.getCurrentUser().getId());

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
        String url = URL + LOADDOCTORDETAILS + "/" + id;
        System.out.println(url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("Response", "Response is: " + response);
                hud.dismiss();
                try {
                    int status = response.getInt("status");
                    System.out.println(status);

                    binding.editName.setText(response.getString("fname"));
                    binding.editMobile.setText(response.getString("mobile"));
                    binding.editEmail.setText(response.getString("mobile"));
                    binding.editDob.setText(response.getString("dob"));
                    binding.spinnerGender.setSelection(((ArrayAdapter<String>) binding.spinnerGender.getAdapter()).getPosition(response.getString("gender")));
                    binding.editAbout.setText(response.getString("about"));
                    binding.editPrice.setText(response.getString("price"));


                    if (response.getString("specialities") != null) {
                        binding.spinnerSpecialist.setSelection(((ArrayAdapter<String>) binding.spinnerSpecialist.getAdapter()).getPosition(response.getString("specialities")));
                    }

//                    if (doctor.getExperience() != null) {
//                        binding.spinnerExperience.setSelection(((ArrayAdapter<String>) binding.spinnerExperience.getAdapter()).getPosition(doctor.getExperience()));
//                    }

//                    if (doctor.isVideoOk()) {
//                        binding.spinnerVideo.setSelection(0);
//                    } else {
//                        binding.spinnerVideo.setSelection(1);
//                    }


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

//        binding.editName.setText(doctor.getName());
//        binding.editMobile.setText(doctor.getMobile());
//        binding.editEmail.setText(doctor.getEmail());
//        binding.editNic.setText(doctor.getNic());
//        binding.editDob.setText(ComLib.getDate(doctor.getDob()));
//        binding.spinnerGender.setSelection(((ArrayAdapter<String>) binding.spinnerGender.getAdapter()).getPosition(doctor.getGender()));
//        binding.spinnerCountry.setSelection(((ArrayAdapter<String>) binding.spinnerCountry.getAdapter()).getPosition(doctor.getCountry()));
//        binding.editDistrict.setText(doctor.getDistrict());
//        binding.editCity.setText(doctor.getCity());
//        binding.editAddress.setText(doctor.getAddress());
//        binding.editZipCode.setText(doctor.getZipCode());
//        binding.editAbout.setText(doctor.getAbout());
//        binding.editPrice.setText(String.valueOf(doctor.getPrice()));
//
//
//        if (doctor.getSpecialist() != null) {
//            binding.spinnerSpecialist.setSelection(((ArrayAdapter<String>) binding.spinnerSpecialist.getAdapter()).getPosition(doctor.getSpecialist()));
//        }
//
//        if (doctor.getExperience() != null) {
//            binding.spinnerExperience.setSelection(((ArrayAdapter<String>) binding.spinnerExperience.getAdapter()).getPosition(doctor.getExperience()));
//        }
//
//        if (doctor.isVideoOk()) {
//            binding.spinnerVideo.setSelection(0);
//        }else{
//            binding.spinnerVideo.setSelection(1);
//        }
//
//        if (doctor.getImage() != null) {
////            storageRef.child("doctorImages/" + doctor.getImage()).getDownloadUrl()
////                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
////                        @Override
////                        public void onSuccess(Uri uri) {
////                            Glide.with(requireActivity()).load(uri).into(binding.profileImage);
////                            hud.dismiss();
////                        }
////                    })
////                    .addOnFailureListener(new OnFailureListener() {
////                        @Override
////                        public void onFailure(@NonNull Exception e) {
////                            e.printStackT
////                            race();
////                            hud.dismiss();
////                        }
////                    });
//        }else {
//            hud.dismiss();
//        }

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
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
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
        String gender = binding.spinnerGender.getSelectedItem().toString();
        String nic = binding.editNic.getText().toString();
        Date dob = ComLib.getDateObject(binding.editDob.getText().toString());
        String about = binding.editAbout.getText().toString();
        String price = binding.editPrice.getText().toString();


//        int gnDivisionId = gnDivisionsList.get(binding.spinnerGnDivisions.getSelectedItemPosition());


        System.out.println("update detauilsssssssssssss");

        if (fullName.equals("") || fullName == null) {
            Toast.makeText(requireActivity(), "Please Enter Your Name!", Toast.LENGTH_SHORT).show();
        } else if (mobile.equals("") || mobile == null) {
            Toast.makeText(requireActivity(), "Please Enter Your Mobile No!", Toast.LENGTH_SHORT).show();
        } else {
            hud.show();

            JSONObject jsonRequest = new JSONObject();
            try {
                jsonRequest.put("id", doctorActivity.getCurrentUser().getId());
                jsonRequest.put("name", fullName);
                jsonRequest.put("mobile", mobile);
                jsonRequest.put("gender", gender);
                jsonRequest.put("nic", nic);
//                jsonRequest.put("dob", dob);
                jsonRequest.put("about", about);
                jsonRequest.put("price", Double.parseDouble(price));

            } catch (JSONException e) {
                e.printStackTrace();
            }

            System.out.println(jsonRequest.toString());


            RequestQueue queue = Volley.newRequestQueue(requireActivity());
            String url = URL + UPDATEDOCTORACCOUNT;

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