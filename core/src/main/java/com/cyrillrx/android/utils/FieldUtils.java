package com.cyrillrx.android.utils;

import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import com.cyrillrx.android.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Cyril Leroux
 *         Created on 15/12/2014.
 */
public class FieldUtils {

    private static String errorFieldRequired;
    private static String errorInvalidPassword;
    private static String errorInvalidEmail;

    public static void init(Resources resources) {
        errorFieldRequired = resources.getString(R.string.error_field_required);
        errorInvalidPassword = resources.getString(R.string.error_invalid_password);
        errorInvalidEmail = resources.getString(R.string.error_invalid_email);
    }

    public static void validateFields(ValidationCallback callback, ValidationEntry... entries) {

        boolean cancel = false;
        View focusView = null;

        List<String> values = new ArrayList<>();
        String value;
        for (ValidationEntry entry : entries) {

            value = validateEditText(entry.getEditText(), entry.getPattern(), entry.getErrorMessage());
            values.add(value);

            if (value == null || value.isEmpty()) {
                if (focusView == null) { focusView = entry.getEditText(); }
                cancel |= true;
            }
        }

        if (cancel) {
            // Focused the first field in error.
            focusView.requestFocus();
            callback.onFailure();

        } else {
            callback.onSuccess(values);
        }
    }

    private static String validateEditText(EditText editText, Pattern pattern, String errorMessage) {

        // Reset error
        editText.setError(null);

        // Cache the value
        final String value = editText.getText().toString();

        // Check emptiness
        if (TextUtils.isEmpty(value)) {
            editText.setError(errorFieldRequired);
            return "";
        }

        // If there is no pattern or if the pattern matches
        if (pattern == null || pattern.matcher(value).matches()) {
            return value;
        }

        editText.setError(errorMessage);
        return "";
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public static void validateCredentials(EditText etEmail, EditText etPassword, ValidationCallback callback) {
        validateFields(callback,
                ValidationEntry.email(etEmail),
                new ValidationEntry(etPassword, Pattern.compile(".{4,}"), errorInvalidPassword));
    }

    public static class ValidationEntry {
        private final EditText editText;
        private final Pattern pattern;
        private final String errorMessage;

        public static ValidationEntry email(EditText etEmail) {
            etEmail.setText(etEmail.getText().toString().trim());
            return new ValidationEntry(etEmail, Patterns.EMAIL_ADDRESS, errorInvalidEmail);
        }

        public ValidationEntry(EditText editText, Pattern pattern, String errorMessage) {
            this.editText = editText;
            this.pattern = pattern;
            this.errorMessage = errorMessage;
        }

        public ValidationEntry(EditText editText) {
            this.editText = editText;
            pattern = null;
            errorMessage = null;
        }

        public EditText getEditText() { return editText; }

        public Pattern getPattern() { return pattern; }

        public String getErrorMessage() { return errorMessage; }
    }

    public interface ValidationCallback {
        void onSuccess(List<String> validValues);

        void onFailure();
    }
}
