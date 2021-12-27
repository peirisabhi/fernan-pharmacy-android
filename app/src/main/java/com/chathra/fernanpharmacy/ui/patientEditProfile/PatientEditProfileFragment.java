package com.chathra.fernanpharmacy.ui.patientEditProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.chathra.fernanpharmacy.databinding.FragmentPatientEditProfileBinding;
import com.kaopiz.kprogresshud.KProgressHUD;

public class PatientEditProfileFragment extends Fragment {

    FragmentPatientEditProfileBinding binding;

//    AnimDialog ImagePickerDialog;
//    AnimDialog responseAnimDialog;
    KProgressHUD hud;
//    private Uri profileImageUri;
//
//    private final int PICK_IMAGE_REQUEST = 10;
//    private final int CAPTURE_IMAGE_REQUEST = 11;
//
//    private final int WRITE_STORAGE_REQUEST_CODE = 101;
//
//    public Customer customer;
//    public String customerDocumentId;
//
//    FirebaseFirestore db;
//    StorageReference storageRef;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        customer = ((MainActivity) getActivity()).customer;
//        customerDocumentId = ((MainActivity) getActivity()).customerDocumentId;
//
//        db = FirebaseFirestore.getInstance();
//        storageRef = FirebaseStorage.getInstance().getReference();

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

        hud = KProgressHUD.create(requireActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

//        init();

//        binding.profileImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showImagePickerDialog();
//            }
//        });

//        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                updateDetails();
//            }
//        });

    }


//    void init() {
//        hud.show();
//
//        binding.editName.setText(customer.getName());
//        binding.editMobile.setText(customer.getMobile());
//        binding.editEmail.setText(customer.getEmail());
//        binding.editNic.setText(customer.getNic());
//        binding.editDob.setText(ComLib.getDate(customer.getDob()));
//        binding.spinnerGender.setSelection(((ArrayAdapter<String>) binding.spinnerGender.getAdapter()).getPosition(customer.getGender()));
//        binding.spinnerCountry.setSelection(((ArrayAdapter<String>) binding.spinnerCountry.getAdapter()).getPosition(customer.getCountry()));
//        binding.editDistrict.setText(customer.getDistrict());
//        binding.editCity.setText(customer.getCity());
//        binding.editAddress.setText(customer.getAddress());
//        binding.editZipCode.setText(customer.getZipCode());
//        binding.spinnerBloodGroup.setSelection(((ArrayAdapter<String>) binding.spinnerBloodGroup.getAdapter()).getPosition(customer.getBloodGroup()));
//
//
//
//        if (customer.getImage() != null) {
//            storageRef.child("customerImages/" + customer.getImage()).getDownloadUrl()
//                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
//                        @Override
//                        public void onSuccess(Uri uri) {
//                            Glide.with(requireActivity()).load(uri).listener(new RequestListener<Drawable>() {
//                                @Override
//                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                                    return false;
//                                }
//
//                                @Override
//                                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                                    hud.dismiss();
//                                    return false;
//                                }
//                            }).into(binding.profileImage);
////                            hud.dismiss();
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            e.printStackTrace();
//                            hud.dismiss();
//                        }
//                    });
//        }else {
//            hud.dismiss();
//        }
//
//    }
//
//
//    void showImagePickerDialog() {
//        if (ImagePickerDialog == null) {
//            ImagePickerDialog = new AnimDialog(requireActivity())
//                    .createAnimatedDualDialog()
//                    .setAnimation(R.raw.camera)
//                    .setTitle("Select Image")
//                    .setContent("You can either select an image from the Gallery or Capture an image from the camera.\nCapturing images from camera requires Storage permissions make sure to provide them.")
//                    .setButton1("Camera", 0, new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            checkStoragePermissionsAndOpenCamera(CAPTURE_IMAGE_REQUEST);
//                            ImagePickerDialog.dismiss();
//                        }
//                    })
//                    .setButton2("Gallery", 0, new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            openFileChooser(PICK_IMAGE_REQUEST);
//                            ImagePickerDialog.dismiss();
//                        }
//                    });
//        }
//
//        ImagePickerDialog.show();
//    }
//
//    private void checkStoragePermissionsAndOpenCamera(int requestCode) {
//
//        boolean permissionStatus = ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED;
//
//        if (ContextCompat.checkSelfPermission(requireActivity(),
//                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_STORAGE_REQUEST_CODE);
//
//        } else {
//            dispatchTakePictureIntent(requestCode);
//        }
//    }
//
//
//    private void dispatchTakePictureIntent(int requestCode) {
//
//        // Check Camera
//        if (requireActivity().getPackageManager().hasSystemFeature(
//                PackageManager.FEATURE_CAMERA)) {
//
//            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            startActivityForResult(takePictureIntent, requestCode);
//        }
//    }
//
//
//    private void openFileChooser(int requestCode) {
//        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(pickPhoto, requestCode);
//    }
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        switch (requestCode) {
//            case WRITE_STORAGE_REQUEST_CODE: {
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    if (ContextCompat.checkSelfPermission(requireActivity(),
//                            Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
//                        Toast.makeText(requireActivity(), "Permission Granted", Toast.LENGTH_SHORT).show();
//
//                        checkStoragePermissionsAndOpenCamera(CAPTURE_IMAGE_REQUEST);
//
//                    }
//                } else {
//                    Toast.makeText(requireActivity(), "Permission Denied", Toast.LENGTH_SHORT).show();
//                }
//                return;
//            }
//        }
//    }
//
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK && data != null) {
//
//            if (requestCode == PICK_IMAGE_REQUEST) { //  gallery
//                Glide.with(this).load(data.getData()).into(binding.profileImage);
//
//                profileImageUri = data.getData();
//
//            } else if (requestCode == CAPTURE_IMAGE_REQUEST) {  //  camera
//                Bitmap photo = (Bitmap) data.getExtras().get("data");
//                Glide.with(this).load(photo).into(binding.profileImage);
//
//                profileImageUri = getImageUri(requireActivity(), photo);
//
//                System.out.println(profileImageUri);
//
//            }
//
//        }
//    }
//
//
//    public Uri getImageUri(Context inContext, Bitmap inImage) {
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
//        return Uri.parse(path);
//    }
//
//    void updateDetails() {
//
//        hud.show();
//
//        if (responseAnimDialog == null) {
//            responseAnimDialog = new AnimDialog(requireActivity());
//        }
//
//        HashMap<String, Object> updateData = new HashMap<>();
//        updateData.put("name", binding.editName.getText().toString());
//        updateData.put("mobile", binding.editMobile.getText().toString());
//        updateData.put("dob", ComLib.getDateObject(binding.editDob.getText().toString()));
//        updateData.put("gender", binding.spinnerGender.getSelectedItem().toString());
//        updateData.put("country", binding.spinnerCountry.getSelectedItem().toString());
//        updateData.put("district", binding.editDistrict.getText().toString());
//        updateData.put("city", binding.editCity.getText().toString());
//        updateData.put("address", binding.editAddress.getText().toString());
//        updateData.put("zipCode", binding.editZipCode.getText().toString());
//
//        updateData.put("bloodGroup", binding.spinnerBloodGroup.getSelectedItem().toString());
//
//
//
//        String path = customer.getNic() + "_" + System.currentTimeMillis() + ".png";
//
//        if (profileImageUri != null) {
//
//            StorageReference storageReference = storageRef.child("customerImages/" + path);
//            storageReference.putFile(profileImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                    updateData.put("image", path);
//
//                    db.collection("customers").document(customerDocumentId).update(updateData)
//                            .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void aVoid) {
////                                    Toast.makeText(requireActivity(), "Success", Toast.LENGTH_SHORT).show();
//                                    hud.dismiss();
//
//                                    updateCurrentCustomerObject();
//
//                                    responseAnimDialog.createAnimatedSingleDialog()
//                                            .setAnimation(R.raw.success)
//                                            .setTitle("Success")
//                                            .setContent("")
//                                            .setButton1(
//                                                    "Ok", 0, new View.OnClickListener() {
//                                                        @Override
//                                                        public void onClick(View v) {
//                                                            responseAnimDialog.dismiss();
//                                                        }
//                                                    });
//                                    responseAnimDialog.show();
//
//                                }
//                            })
//                            .addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
////                                    Toast.makeText(requireActivity(), "Error on firestore", Toast.LENGTH_SHORT).show();
//                                    hud.dismiss();
//
//                                    responseAnimDialog.createAnimatedSingleDialog()
//                                            .setAnimation(R.raw.error)
//                                            .setTitle("Error")
//                                            .setContent("")
//                                            .setButton1(
//                                                    "Ok", 0, new View.OnClickListener() {
//                                                        @Override
//                                                        public void onClick(View v) {
//                                                            responseAnimDialog.dismiss();
//                                                        }
//                                                    });
//                                    responseAnimDialog.show();
//                                }
//                            });
//
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
////                    Toast.makeText(requireActivity(), "Errror", Toast.LENGTH_SHORT).show();
//                    hud.dismiss();
//
//                    responseAnimDialog.createAnimatedSingleDialog()
//                            .setAnimation(R.raw.error)
//                            .setTitle("Error")
//                            .setContent("")
//                            .setButton1(
//                                    "Ok", 0, new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//                                            responseAnimDialog.dismiss();
//                                        }
//                                    });
//                    responseAnimDialog.show();
//                }
//            });
//
//        } else {
//
//            db.collection("customers").document(customerDocumentId).update(updateData)
//                    .addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
////                            Toast.makeText(requireActivity(), "Success", Toast.LENGTH_SHORT).show();
//                            updateCurrentCustomerObject();
//                            hud.dismiss();
//
//                            responseAnimDialog.createAnimatedSingleDialog()
//                                    .setAnimation(R.raw.success)
//                                    .setTitle("Success")
//                                    .setContent("")
//                                    .setButton1(
//                                            "Ok", 0, new View.OnClickListener() {
//                                                @Override
//                                                public void onClick(View v) {
//                                                    responseAnimDialog.dismiss();
//                                                }
//                                            });
//                            responseAnimDialog.show();
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
////                            Toast.makeText(requireActivity(), "Error on firestore", Toast.LENGTH_SHORT).show();
//                            hud.dismiss();
//
//                            responseAnimDialog.createAnimatedSingleDialog()
//                                    .setAnimation(R.raw.error)
//                                    .setTitle("Error")
//                                    .setContent("")
//                                    .setButton1(
//                                            "Ok", 0, new View.OnClickListener() {
//                                                @Override
//                                                public void onClick(View v) {
//                                                    responseAnimDialog.dismiss();
//                                                }
//                                            });
//                            responseAnimDialog.show();
//                        }
//                    });
//
//        }
//
//
//    }
//
//
//    void updateCurrentCustomerObject() {
//        customer.setName(binding.editName.getText().toString());
//        customer.setMobile(binding.editMobile.getText().toString());
//        customer.setDob(ComLib.getDateObject(binding.editDob.getText().toString()));
//        customer.setGender(binding.spinnerGender.getSelectedItem().toString());
//        customer.setCountry(binding.spinnerCountry.getSelectedItem().toString());
//        customer.setDistrict(binding.editDistrict.getText().toString());
//        customer.setCity(binding.editCity.getText().toString());
//        customer.setAddress(binding.editAddress.getText().toString());
//        customer.setZipCode(binding.editZipCode.getText().toString());
//        customer.setBloodGroup(binding.spinnerBloodGroup.getSelectedItem().toString());
//
//    }

}