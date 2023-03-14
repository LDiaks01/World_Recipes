package com.example.worldrecipes;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.worldrecipes.manager.UserManager;
import com.example.worldrecipes.recyclerviews.categoryView;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

public class LoginTabFragment extends Fragment {


    Button connecter;
    TextView txinfo;
    Button btn_fp, hors_conn;
    float v = 0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root1 = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        connecter = root1.findViewById(R.id.connexion);
        txinfo = root1.findViewById(R.id.info);
        hors_conn=root1.findViewById(R.id.hors_connexion);
        btn_fp = root1.findViewById(R.id.empreinteConn);

        connecter.setTranslationX(800);
        hors_conn.setTranslationX(800);
        btn_fp.setTranslationX(800);

        connecter.setAlpha(v);
        hors_conn.setAlpha(v);
        btn_fp.setAlpha(v);

        connecter.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        hors_conn.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        btn_fp.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();





        Button btn2_open = (Button) root1.findViewById(R.id.connexion);
        btn2_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userManager.isCurrentUserLogged()){
                    Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getActivity().getApplicationContext(), userManager.getCurrentUser().getDisplayName().toString() + " est connectée", Toast.LENGTH_SHORT).show();

                }else{
                    startSignInActivity();
                }


            }
        });

        Button btn3_open = (Button) root1.findViewById(R.id.hors_connexion);
        btn3_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity().getApplicationContext(), R.string.hors_connexion_toast, Toast.LENGTH_SHORT).show();

            }
        });


        CheckBiometricSupported();
        Executor executor = ContextCompat.getMainExecutor(getActivity().getApplicationContext());
        BiometricPrompt biometricPrompt = new BiometricPrompt(LoginTabFragment.this, executor,
                new BiometricPrompt.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                        super.onAuthenticationError(errorCode, errString);
                        Toast.makeText(getActivity().getApplicationContext(), "auth error" + errString, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);
                        Toast.makeText(getActivity().getApplicationContext(), "auth succes", Toast.LENGTH_SHORT).show();
                        getActivity().finish();
                        Intent intent=new Intent(getActivity().getApplicationContext(),MainActivity.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onAuthenticationFailed() {
                        super.onAuthenticationFailed();
                        Toast.makeText(getActivity().getApplicationContext(), "auth failed", Toast.LENGTH_SHORT).show();
                    }
                });
        btn_fp.setOnClickListener(view -> {
            BiometricPrompt.PromptInfo.Builder promptInfo = dialogMetric();
            // promptInfo.setNegativeButtonText("cancel");
            promptInfo.setDeviceCredentialAllowed(true);

            biometricPrompt.authenticate(promptInfo.build());
        });



        return root1;


    }


    BiometricPrompt.PromptInfo.Builder dialogMetric() {
        return new BiometricPrompt.PromptInfo.Builder().
                setTitle("Connexion avec empreinte").
                setSubtitle("utiliser votre empreinte");
    }

    private void CheckBiometricSupported() {
        String info = "";
        BiometricManager manager = BiometricManager.from(getActivity().getApplicationContext());
        switch (manager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK
                | BiometricManager.Authenticators.BIOMETRIC_STRONG)) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                info = "app can authentificate using biometrics";
                enableButton(true);
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                info = "no biometric features";
                enableButton(false);
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                info = "unvailable";
                enableButton(false);
                break;

            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                info = "npn enrolled";
                enableButton(false, true);
                break;
            default:
                info = "unknown cause";
                break;
        }
        txinfo.setText(info);
    }

    void enableButton(boolean enable) {
        btn_fp.setEnabled(enable);
    }

    void enableButton(boolean enable, boolean enroll) {
        enableButton(enable);
        if (!enroll) return;
        Intent enrollIntent = new Intent(Settings.ACTION_BIOMETRIC_ENROLL);
        enrollIntent.putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                BiometricManager.Authenticators.BIOMETRIC_STRONG |
                        BiometricManager.Authenticators.BIOMETRIC_WEAK);
        startActivity(enrollIntent);

    }


    //Auth, added by LDiaks01

    private static final int RC_SIGN_IN = 123;
    private UserManager userManager = UserManager.getInstance();

    private void startSignInActivity() {

        // Choose authentication providers
        List<AuthUI.IdpConfig> providers =
                Arrays.asList(
                        new AuthUI.IdpConfig.GoogleBuilder().build(),
                        new AuthUI.IdpConfig.EmailBuilder().build());

        // Launch the activity
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setTheme(com.firebase.ui.auth.R.style.FirebaseUI_DefaultMaterialTheme)
                        .setAvailableProviders(providers)
                        //.setIsSmartLockEnabled(false, true)
                        .setLogo(R.drawable.logo)
                        .build(),
                RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       // super.onActivityResult(requestCode, resultCode, data);
        this.handleResponseAfterSignIn(requestCode, resultCode, data);
    }

    // Method that handles response after SignIn Activity close
    private void handleResponseAfterSignIn(int requestCode, int resultCode, Intent data) {

        IdpResponse response = IdpResponse.fromResultIntent(data);

        if (requestCode == RC_SIGN_IN) {
            // SUCCESS
            if (resultCode == RESULT_OK) {
                Toast.makeText(getActivity().getApplicationContext(), "Authentification Réussie", Toast.LENGTH_SHORT).show();
                getActivity().finish();
                Intent intent=new Intent(getActivity().getApplicationContext(),MainActivity.class);
                startActivity(intent);

            } else {
                // ERRORS
                if (response == null) {
                    Toast.makeText(getActivity().getApplicationContext(), "Authentification Annulée", Toast.LENGTH_SHORT).show();

                } else if (response.getError() != null) {
                    if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                        Toast.makeText(getActivity().getApplicationContext(), "Aucun Accès Internet", Toast.LENGTH_SHORT).show();

                    } else if (response.getError().getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                        Toast.makeText(getActivity().getApplicationContext(), "Une erreur est survenue lors de l'authentification", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        }
    }
}