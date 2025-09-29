# CS 501 Individual Assignment 3 Question 5

## Explanation
- Implements a simple **login form** using Jetpack Compose and **Material 3**.
- Includes two fields: **Username** and **Password**.
- Styled with Material 3 theme colors and typography (`OutlinedTextField`, `Button`, `MaterialTheme`).
- **Validation**: shows error text if either field is empty when submitting.
- **Password toggle**: trailing “Show/Hide” button to reveal or mask the password.
- Shows a **Snackbar** message on successful submission.

## How to Use
1. Run the app (or use the `@Preview`).
2. Enter a username and password.
3. Tap **Submit**:
   - If either field is empty, an error message appears under that field.
   - If both are filled, a Snackbar shows **“Login submitted.”**
4. Use the **Show/Hide** toggle to reveal or mask the password text.

## Implementation
- `OutlinedTextField` for username and password fields.
- Validation with `isError` and `supportingText` to display error messages.
- A `Button` submits the form and checks for empty fields.
- A `SnackbarHostState` inside `Scaffold` displays a Snackbar when the form is valid.
- `MaterialTheme.typography.headlineSmall` styles the “Login” title.
